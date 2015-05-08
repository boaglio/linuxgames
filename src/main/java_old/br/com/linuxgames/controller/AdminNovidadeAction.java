package br.com.linuxgames.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mentawai.converter.Converter;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.filtros.conversores.DateConverterNullAllowed;
import br.com.linuxgames.model.dao.ColaboradoresDAO;
import br.com.linuxgames.model.dao.NovidadeDAO;
import br.com.linuxgames.model.dao.UsuariosLGDAO;
import br.com.linuxgames.model.dao.UsuariosDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.KeyValueVO;
import br.com.linuxgames.model.vo.Novidade;
import br.com.linuxgames.model.vo.SolicitacaoVO;
import br.com.linuxgames.model.vo.TextoDeJogoVO;
import br.com.linuxgames.model.vo.TextoVO;
import br.com.linuxgames.model.vo.Usuario;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.DateHelper;
import br.com.linuxgames.util.EmailHelper;
import br.com.linuxgames.util.TwitterUtil;


public class AdminNovidadeAction extends BaseAction implements RedirectAfterLogin {

	public static final java.lang.String AJUDA = "ajuda";

	NovidadeDAO dao = NovidadeDAO.getInstance();
	Logger logger = Logger.getLogger(this.getClass());

	public boolean shouldRedirect(String innerAction) {
	     return true;
	    }

	/**
	 * action inicial
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String solicitacaoNovidade() throws ActionException {
    	Collection<?> collection = null;
    	try {
    		collection = dao.buscaAjudasReprovadas();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
    	output.setValue("textos", collection);
    	return SUCCESS;
    }

	/**
	 * efetiva alteracao
	 * @return
	 * @throws ActionException
	 */
	public String alteraSolicitacaoNovidade() throws ActionException {

		int id = input.getInt("id");
    	// ajusta o VO de novidade
		Novidade vo = new Novidade();
    	vo.setId(id);
     	String userID= input.getString("usuario_id");
     	Usuario usuarioVO = new Usuario();
     	usuarioVO.setId(Integer.parseInt(userID));
     	vo.setLink(input.getString("link"));
     	vo.setTexto(input.getString("texto"));
     	vo.setDataPublic(DateHelper.formatStrDate(input.getString("dataPublic")));

		int tipoSolicitacao = input.getInt("tipoSolicitacao");
		int opt = input.getInt("opt");
		String justificativa = input.getString("justificativa");

		UsuariosDAO usuariosDao =  UsuariosDAO.getInstance();
		try {
			Object obj = usuariosDao.buscaUm(usuarioVO);
			if (obj instanceof Usuario) {
	     	 vo.setUsuario(usuarioVO);
			}
		} catch (DAOException e1) {
			logger.error("erro na atualizacao!",e1);
		}

		SolicitacaoVO solicitacao = new SolicitacaoVO();
		solicitacao.setCategoria(Constantes.NOVIDADE);
		solicitacao.setId(vo.getId());
		Usuario usuario = vo.getUsuario();
		TextoVO texto = new TextoVO();
		texto.setTipo(tipoSolicitacao);
		texto.setTexto(justificativa);
		if (opt == Constantes.APROVADO)
		 {
		  texto.setAprovado(true);
		 }
		else
 		 texto.setAprovado(false);
		solicitacao.setTexto(texto);
		solicitacao.setUsuario(usuario);

		// altera o status para aprovado/reprovado
		try {
			TextoDeJogoVO tvo = new TextoDeJogoVO();
			tvo.setAprovado(solicitacao.getTexto().getAprovado());
			tvo.setId(solicitacao.getId());
			if (tvo.isAprovado())
			{
			 // muda status
			 dao.atualizaStatus(tvo);
			 // adiciona credito
			 if (usuario.getSenha()!=null) {
			  UsuariosLGDAO usuarioDao = UsuariosLGDAO.getInstance();
			  usuarioDao.atualizaNoticiasContribuidas(vo.getUsuario().getId());
			 }
			}
			else
			 dao.remove(new Novidade(id));
		} catch (DAOException e) {
			logger.error("erro na atualizacao!",e);
		} catch (SQLException e) {
			logger.error("erro na atualizacao!",e);
		}

		// se aprovado, faz o update
		if (opt == Constantes.APROVADO) {
			try {
				vo.setAprovado(true);
				dao.atualiza(vo);
				// atualiza o twitter
				TwitterUtil.postar(vo.getTexto(),vo.getLink());
				// atualiza cache de news
				CacheManager.getCacheDeNovidades().refreshNoCache();

			} catch (DAOException e) {
				logger.error("erro ao atualizar a novidade!",e);
			}
		}

		// manda email para o solicitante/colaboradores
		if (usuario.getSenha()!=null && usuario.getSenha().length()>7 && !usuario.getSenha().equals("sem email!"))
		 {
		 ColaboradoresDAO colaboradoresDAO = ColaboradoresDAO.getinstance();
		 try {
			ArrayList<KeyValueVO> colaboradores = (ArrayList<KeyValueVO>) colaboradoresDAO.buscaColaboradores();
			ArrayList<KeyValueVO> solicitantes = new  ArrayList<KeyValueVO>();
			solicitantes.add(new KeyValueVO(usuario.getEmail(),usuario.getSenha()));
			EmailHelper.mandaEmailAlteracaoSolicitacao(solicitantes,colaboradores,solicitacao);
		  }  catch (DAOException e) {
			logger.error("erro no DAO do envio de emails!",e);
		  } catch (Exception e) {
			 logger.error("erro no envio de emails!",e);
		  }
		 }

    	Collection<?> collection = null;
    	try {
    		collection = dao.buscaAjudasReprovadas();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
    	output.setValue("textos", collection);

    	return SUCCESS;
    }

	/**
	 * exibe dados da solicitacao de novidade
	 * @return
	 * @throws ActionException
	 */
	public String solicitacaoNovidadeDetalhe() throws ActionException {
    	int id= input.getInt("id");
    	Novidade vo =null;
    	try {

    		// armazenado
    		vo = (Novidade) dao.buscaUm(new Novidade(id));
    		output.setValue("id",  String.valueOf(vo.getId()));
    		output.setValue("link", vo.getLink());
    		output.setValue("texto", vo.getTexto());
    		output.setValue("usuario_id", vo.getUsuario().getId());
    		output.setValue("dataPublic", vo.getDataPublic());

    		UsuariosDAO usuariosDao = UsuariosDAO.getInstance();
    		try {
				vo.getUsuario().setEmail(usuariosDao.buscaNomePorId(vo.getUsuario().getId()));
			} catch (SQLException e) {
				logger.error("erro na busca!",e);
			}

    		output.setValue("usuario",(Usuario) vo.getUsuario());

    	} catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
    	output.setValue("novidade", vo);
    	return SUCCESS;
    }



	public void prepareConverters(Map<String, Converter> converters, String innerAction) {

		converters.put("dataPublic", new DateConverterNullAllowed());

	}
}