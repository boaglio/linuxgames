package br.com.linuxgames.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.model.dao.ColaboradoresDAO;
import br.com.linuxgames.model.dao.RoteiroInstalacaoEmuDAO;
import br.com.linuxgames.model.dao.TextoDeEmuladorDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.KeyValueVO;
import br.com.linuxgames.model.vo.RoteiroInstalacaoEmuVO;
import br.com.linuxgames.model.vo.SolicitacaoVO;
import br.com.linuxgames.model.vo.TextoDeEmuladorVO;
import br.com.linuxgames.model.vo.TextoVO;
import br.com.linuxgames.model.vo.Usuario;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.EmailHelper;
import br.com.linuxgames.util.UpdateLogHelper;


public class AdminEmuladorAction extends BaseAction implements RedirectAfterLogin {

	public static final String ROTEIRO = "roteiro";
	public static final String TEXTO = "texto";

	TextoDeEmuladorDAO daoTexto = TextoDeEmuladorDAO.getinstance();
	RoteiroInstalacaoEmuDAO daoRoteiro = RoteiroInstalacaoEmuDAO.getinstance();
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

	/**
	 * trata solicitacoes de roteiros de instalacao
	 * @return
	 * @throws ActionException
	 */
	public String solicitacaoRoteiroEmulador() throws ActionException {
    	Collection<?> collection = null;
    	try {
    		collection = daoRoteiro.buscaTextosReprovados();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
    	output.setValue("textos", collection);
    	return SUCCESS;
    }

	/**
	 * trata solicitacoes de roteiros de instalacao
	 * @return
	 * @throws ActionException
	 */
	public String solicitacaoRoteiroEmuladorDetalhe() throws ActionException {
    	int idTexto = input.getInt("id");
    	SolicitacaoVO vo =null;
    	try {
    		vo = daoRoteiro.buscaTextoReprovado(idTexto);
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
    	output.setValue("texto", vo);
    	return SUCCESS;
    }

	/**
	 * trata solicitacoes de dicas, videos e links de download
	 * @return
	 * @throws ActionException
	 */
	public String solicitacaoTextoEmulador() throws ActionException {
    	Collection<?> collection = null;
    	try {
    		collection = daoTexto.buscaTextosReprovados();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
    	output.setValue("textos", collection);
    	return SUCCESS;
    }

	/**
	 * trata solicitacoes de dicas, videos e links de download
	 * @return
	 * @throws ActionException
	 */
	public String solicitacaoTextoEmuladorDetalhe() throws ActionException {
    	int idTexto = input.getInt("id");
    	SolicitacaoVO vo =null;
    	try {
    		vo = daoTexto.buscaTextoReprovado(idTexto);
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
    	output.setValue("texto", vo);
    	return SUCCESS;
    }

	/**
	 * efetiva alteracao
	 * @return
	 * @throws ActionException
	 */
	public String alteraSolicitacaoEmulador() throws ActionException {
		int idTexto = input.getInt("id");
		int idEmulador= input.getInt("idEmulador");
		int tipoSolicitacao = input.getInt("tipoSolicitacao");
		int opt = input.getInt("opt");
		String justificativa = input.getString("justificativa");
		String emuladorNome = input.getString("emulador");
		String usuarioNome = input.getString("usuario");
		String usuarioEmail = input.getString("email");

		SolicitacaoVO solicitacaoDeEmulador = new SolicitacaoVO();
		solicitacaoDeEmulador.setId(idEmulador);
		solicitacaoDeEmulador.setNome(emuladorNome);
		Usuario usuario = new Usuario();
		usuario.setEmail(usuarioNome);
		usuario.setSenha(usuarioEmail);
		TextoVO texto = new TextoVO();
		texto.setId(idTexto);
		texto.setTipo(tipoSolicitacao);
		texto.setTexto(justificativa);
		if (opt == Constantes.APROVADO)
		 {
		  texto.setAprovado(true);
		 }
		else
 		 texto.setAprovado(false);
		solicitacaoDeEmulador.setTexto(texto);
		solicitacaoDeEmulador.setUsuario(usuario);
		solicitacaoDeEmulador.setCategoria(Constantes.EMULADOR);

		logger.debug("tipoSolicitacao="+tipoSolicitacao);

		switch (tipoSolicitacao) {

		case 10: // roteiro

				// altera o status para aprovado/reprovado
				try {
					RoteiroInstalacaoEmuVO vo = new RoteiroInstalacaoEmuVO();
					vo.setAprovado(solicitacaoDeEmulador.getTexto().getAprovado());
					vo.setId(solicitacaoDeEmulador.getTexto().getId());
					if (vo.isAprovado())
					{
					 daoRoteiro.atualizaStatus(vo);
					 UpdateLogHelper.setUpdateLog("Adicionado novo roteiro de instala&ccedil;&atilde;o do emulador ["+emuladorNome+"]");
					}
					else
					 daoRoteiro.remove(vo);
				} catch (DAOException e) {
					logger.error("erro na atualizacao!",e);
				}

				break;

		case 1: // dica
		case 2: // video
		case 3: // download

			// altera o status para aprovado/reprovado
			try {
				TextoDeEmuladorVO vo = new TextoDeEmuladorVO();
				vo.setAprovado(solicitacaoDeEmulador.getTexto().getAprovado());
				vo.setId(solicitacaoDeEmulador.getTexto().getId());
				if (vo.isAprovado())
				{
				 daoTexto.atualizaStatus(vo);
				 UpdateLogHelper.setUpdateLog("Adicionada nova dica do emulador ["+emuladorNome+"]");
				}
				else
				 daoTexto.remove(vo);
			} catch (DAOException e) {
				logger.error("erro na atualizacao!",e);
			}

			   break;

		}

		// manda email para o solicitante/colaboradores
		if (usuarioEmail!=null && usuarioEmail.length()>7)
		 {
		 ColaboradoresDAO colaboradoresDAO = ColaboradoresDAO.getinstance();
		 try {
			ArrayList<KeyValueVO> colaboradores = (ArrayList<KeyValueVO>) colaboradoresDAO.buscaColaboradores();
			ArrayList<KeyValueVO> solicitantes = new  ArrayList<KeyValueVO>();
			solicitantes.add(new KeyValueVO(usuarioNome,usuarioEmail));
			EmailHelper.mandaEmailAlteracaoSolicitacao(solicitantes,colaboradores,solicitacaoDeEmulador);
		  }  catch (DAOException e) {
			logger.error("erro no DAO do envio de emails!",e);
		  } catch (Exception e) {
			 logger.error("erro no envio de emails!",e);
		  }
		 }

    	Collection<?> collection = null;

		switch (tipoSolicitacao) {

		case 10: // roteiro

	    	try {
	    		collection = daoRoteiro.buscaTextosReprovados();
		    } catch (DAOException e) {
			  logger.error("erro no cadastro!",e);
		    }
	    	output.setValue("textos", collection);

	    	return ROTEIRO;

		case 1: // dica
		case 2: // video
		case 3: // download

	    	try {
	    		collection = daoTexto.buscaTextosReprovados();
		    } catch (DAOException e) {
			  logger.error("erro no cadastro!",e);
		    }
	    	output.setValue("textos", collection);

	    	return TEXTO;

		}
    	return SUCCESS;
    }


}