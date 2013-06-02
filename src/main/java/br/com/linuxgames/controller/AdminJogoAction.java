package br.com.linuxgames.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.model.dao.ColaboradoresDAO;
import br.com.linuxgames.model.dao.JogoDAO;
import br.com.linuxgames.model.dao.JogoTemplateDAO;
import br.com.linuxgames.model.dao.RoteiroInstalacaoJogoDAO;
import br.com.linuxgames.model.dao.TextoDeJogoDAO;
import br.com.linuxgames.model.dao.UsuariosLGDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.FabricanteVO;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.model.vo.JogoTemplate;
import br.com.linuxgames.model.vo.KeyValueVO;
import br.com.linuxgames.model.vo.LicencaVO;
import br.com.linuxgames.model.vo.RoteiroInstalacaoJogoVO;
import br.com.linuxgames.model.vo.SolicitacaoVO;
import br.com.linuxgames.model.vo.TextoDeJogoVO;
import br.com.linuxgames.model.vo.TextoVO;
import br.com.linuxgames.model.vo.Usuario;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.EmailHelper;
import br.com.linuxgames.util.UpdateLogHelper;


public class AdminJogoAction extends BaseAction implements RedirectAfterLogin {

	public static final java.lang.String ROTEIRO = "roteiro";
	public static final java.lang.String TEXTO = "texto";
	public static final java.lang.String AJUDA = "ajuda";

	TextoDeJogoDAO daoTexto = TextoDeJogoDAO.getInstance();
	JogoTemplateDAO daoJogoTemplate = JogoTemplateDAO.getInstance();
	JogoDAO daoJogo = JogoDAO.getInstance();
	RoteiroInstalacaoJogoDAO daoRoteiro = RoteiroInstalacaoJogoDAO.getinstance();
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
	public String solicitacaoRoteiroJogo() throws ActionException {
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
	public String solicitacaoRoteiroJogoDetalhe() throws ActionException {
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
	public String solicitacaoTextoJogo() throws ActionException {
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
	public String solicitacaoTextoJogoDetalhe() throws ActionException {
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
	public String alteraSolicitacaoJogo() throws ActionException {
		int idTexto = input.getInt("id");
		int idJogo= input.getInt("idJogo");
		int tipoSolicitacao = input.getInt("tipoSolicitacao");
		int opt = input.getInt("opt");
		String justificativa = input.getString("justificativa");
		String jogoNome = input.getString("jogo");
		String usuarioNome = input.getString("usuario");
		String usuarioEmail = input.getString("email");

		SolicitacaoVO solicitacaoDeJogo = new SolicitacaoVO();
		solicitacaoDeJogo.setId(idJogo);
		solicitacaoDeJogo.setNome(jogoNome);
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
		solicitacaoDeJogo.setTexto(texto);
		solicitacaoDeJogo.setUsuario(usuario);
		solicitacaoDeJogo.setCategoria(Constantes.JOGO);

		logger.debug("tipoSolicitacao="+tipoSolicitacao);

		switch (tipoSolicitacao) {

		case 10: // roteiro

				// altera o status para aprovado/reprovado
				try {
					RoteiroInstalacaoJogoVO vo = new RoteiroInstalacaoJogoVO();
					vo.setAprovado(solicitacaoDeJogo.getTexto().getAprovado());
					vo.setId(solicitacaoDeJogo.getTexto().getId());
					if (vo.isAprovado())
					 {
					  daoRoteiro.atualizaStatus(vo);
					  UpdateLogHelper.setUpdateLog("Adicionado novo roteiro de instala&ccedil;&atilde;o do jogo ["+jogoNome+"]");
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
				TextoDeJogoVO vo = new TextoDeJogoVO();
				vo.setAprovado(solicitacaoDeJogo.getTexto().getAprovado());
				vo.setId(solicitacaoDeJogo.getTexto().getId());
				if (vo.isAprovado())
				{
				 daoTexto.atualizaStatus(vo);
				 UpdateLogHelper.setUpdateLog("Adicionada nova dica do jogo ["+jogoNome+"]");
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
			EmailHelper.mandaEmailAlteracaoSolicitacao(solicitantes,colaboradores,solicitacaoDeJogo);
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


	/**
	 * trata solicitacoes de alteracao de jogo
	 * @return
	 * @throws ActionException
	 */
	public String solicitacaoAlteracaoJogo() throws ActionException {
    	Collection<?> collection = null;
    	try {
    		collection = daoJogoTemplate.buscaAjudasReprovadas();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
    	output.setValue("textos", collection);
    	return SUCCESS;
    }

	/**
	 * exibe dados da solicitacao de alteracao de jogo
	 * @return
	 * @throws ActionException
	 */
	public String solicitacaoAlteracaoJogoDetalhe() throws ActionException {
    	int idTemplate= input.getInt("id");
    	JogoTemplate vo =null;
    	Jogo jogoVo =null;
    	try {

    		// template
    		vo = (JogoTemplate) daoJogoTemplate.buscaUm(new JogoTemplate(idTemplate));
    		output.setValue("id",  String.valueOf(vo.getId()));
    		output.setValue("tnome", vo.getNome());
    		output.setValue("ttipo", vo.getTipo());
    		output.setValue("tlicenca_id",  String.valueOf(vo.getLicenca().getId()));
    		output.setValue("taberto", vo.getAberto());
    		output.setValue("tjogaEmRede", vo.getJogaEmRede());
    		output.setValue("tprecisa3d", vo.getPrecisa3d());
    		output.setValue("ttemSom", vo.getTemSom());
    		output.setValue("tconsoleOuX11", vo.getConsoleOuX11());
    		output.setValue("tsiteOficial", vo.getSiteOficial());
    		output.setValue("tdescricao", vo.getDescricao());
    		output.setValue("tfabricante_id",  String.valueOf(vo.getFabricante().getId()));
    		output.setValue("tidioma", vo.getIdioma());

    		// jogo armazenado
    		jogoVo = (Jogo) daoJogo.buscaUm(new Jogo(vo.getJogoId()));
    		output.setValue("jogo_id",  String.valueOf(jogoVo.getId()));
    		output.setValue("nome", jogoVo.getNome());
    		output.setValue("tipo", jogoVo.getTipo());
    		output.setValue("licenca_id",  String.valueOf(jogoVo.getLicenca().getId()));
    		output.setValue("aberto", jogoVo.getAberto());
    		output.setValue("jogaEmRede", jogoVo.getJogaEmRede());
    		output.setValue("precisa3d", jogoVo.getPrecisa3d());
    		output.setValue("temSom", jogoVo.getTemSom());
    		output.setValue("consoleOuX11", jogoVo.getConsoleOuX11());
    		output.setValue("siteOficial", jogoVo.getSiteOficial());
    		output.setValue("descricao", jogoVo.getDescricao());
    		output.setValue("fabricante_id",  String.valueOf(jogoVo.getFabricante().getId()));
    		output.setValue("idioma", jogoVo.getIdioma());

    		// usuario
    		output.setValue("usuario", vo.getUsuario());
    		output.setValue("obs", vo.getObservacao());

    	} catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
    	output.setValue("vo", vo);
    	output.setValue("jogoVo", jogoVo);
    	return SUCCESS;
    }



	/**
	 * trata solicitacoes de novo jogo
	 * @return
	 * @throws ActionException
	 */
	public String solicitacaoNovoJogo() throws ActionException {
    	Collection<?> collection = null;
    	try {
    		collection = daoJogoTemplate.buscaAjudasNovasReprovadas();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
    	output.setValue("textos", collection);
    	return SUCCESS;
    }



	/**
	 * exibe dados da solicitacao de alteracao de jogo
	 * @return
	 * @throws ActionException
	 */
	public String solicitacaoNovoJogoDetalhe() throws ActionException {
    	int idTemplate= input.getInt("id");
    	JogoTemplate vo =null;
    	Jogo jogoVo =null;
    	try {

    		// template
    		vo = (JogoTemplate) daoJogoTemplate.buscaUm(new JogoTemplate(idTemplate));
    		output.setValue("id",  String.valueOf(vo.getId()));
    		output.setValue("nome", vo.getNome());
    		output.setValue("tipo", vo.getTipo());
    		output.setValue("licenca_id",  String.valueOf(vo.getLicenca().getId()));
    		output.setValue("aberto", vo.getAberto());
    		output.setValue("jogaEmRede", vo.getJogaEmRede());
    		output.setValue("precisa3d", vo.getPrecisa3d());
    		output.setValue("temSom", vo.getTemSom());
    		output.setValue("consoleOuX11", vo.getConsoleOuX11());
    		output.setValue("siteOficial", vo.getSiteOficial());
    		output.setValue("descricao", vo.getDescricao());
    		output.setValue("fabricante_id",  String.valueOf(vo.getFabricante().getId()));
    		output.setValue("idioma", vo.getIdioma());

    		// usuario
    		output.setValue("usuario", vo.getUsuario());
    		output.setValue("obs", vo.getObservacao());

    	} catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
    	output.setValue("vo", vo);
    	output.setValue("jogoVo", jogoVo);
    	return SUCCESS;
    }

	/**
	 * efetiva alteracao
	 * @return
	 * @throws ActionException
	 */
	public String alteraSolicitacaoAjudaJogo() throws ActionException {

		int id = input.getInt("id");
		int idJogo= input.getInt("jogo_id");

    	// ajusta o VO de jogo
		Jogo vo = new Jogo();
    	vo.setId(idJogo);
     	String fabricanteID= input.getString("fabricante_id");
     	FabricanteVO fabricanteVO = new FabricanteVO();
     	fabricanteVO.setId(Integer.parseInt(fabricanteID));
     	vo.setFabricante(fabricanteVO);
     	String licencaID= input.getString("licenca_id");
     	LicencaVO licencaVO = new LicencaVO();
     	licencaVO.setId(Integer.parseInt(licencaID));
     	vo.setLicenca(licencaVO);
     	vo.setAberto(input.getInt("aberto"));
     	vo.setTipo(input.getInt("tipo"));
     	vo.setJogaEmRede(input.getInt("jogaEmRede"));
     	vo.setPrecisa3d(input.getInt("precisa3d"));
     	vo.setTemSom(input.getInt("temSom"));
     	vo.setConsoleOuX11(input.getInt("consoleOuX11"));
     	vo.setIdioma(input.getString("idioma"));
     	vo.setSiteOficial(input.getString("siteOficial"));
     	vo.setDescricao(input.getString("descricao"));

     	try {
			Jogo storedVO = (Jogo) daoJogo.buscaUm(new Jogo(idJogo));
			vo.setDestaque(storedVO.getDestaque());
			vo.setHits(storedVO.getHits());
			vo.setNotaGeral(storedVO.getNotaGeral());
			vo.setSiteCompra(storedVO.getSiteCompra());
			vo.setVotos(storedVO.getVotos());
			vo.setIdioma(storedVO.getIdioma());
			vo.setJogoId(storedVO.getJogoId());
		} catch (DAOException e1) {
			logger.error("erro ao buscar o jogo!",e1);
		}

		int tipoSolicitacao = input.getInt("tipoSolicitacao");
		int opt = input.getInt("opt");
		String justificativa = input.getString("justificativa");

		String jogoNome = input.getString("nome");
		vo.setNome(jogoNome);
		String usuarioNome = input.getString("usuario");
		String usuarioEmail = input.getString("email");

		SolicitacaoVO solicitacaoDeJogo = new SolicitacaoVO();
		solicitacaoDeJogo.setId(idJogo);
		solicitacaoDeJogo.setNome(jogoNome);
		Usuario usuario = new Usuario();
		usuario.setEmail(usuarioNome);
		usuario.setSenha(usuarioEmail);
		TextoVO texto = new TextoVO();
		texto.setId(id);
		texto.setTipo(tipoSolicitacao);
		texto.setTexto(justificativa);
		if (opt == Constantes.APROVADO)
		 {
		  texto.setAprovado(true);
		 }
		else
 		 texto.setAprovado(false);
		solicitacaoDeJogo.setTexto(texto);
		solicitacaoDeJogo.setUsuario(usuario);
		solicitacaoDeJogo.setCategoria(Constantes.JOGO);

		// altera o status para aprovado/reprovado
		try {
			TextoDeJogoVO tvo = new TextoDeJogoVO();
			tvo.setAprovado(solicitacaoDeJogo.getTexto().getAprovado());
			tvo.setId(solicitacaoDeJogo.getTexto().getId());
			if (tvo.isAprovado())
			{
			 // muda status
			 daoJogoTemplate.atualizaStatus(tvo);
			 UpdateLogHelper.setUpdateLog("Alteradas informações do jogo ["+jogoNome+"]");
			 // adiciona credito
			 if (usuarioNome!=null) {
 			  JogoTemplate jogoTemplate = (JogoTemplate) daoJogoTemplate.buscaUm(new JogoTemplate(id));
			  UsuariosLGDAO usuarioDao = UsuariosLGDAO.getInstance();
			  usuarioDao.atualizaJogosContribuidos(jogoTemplate.getUsuario().getId());
			 }
			}
			else
			 daoJogoTemplate.remove(new JogoTemplate(id));
		} catch (DAOException e) {
			logger.error("erro na atualizacao!",e);
		} catch (SQLException e) {
			logger.error("erro na atualizacao!",e);
		}

		// se aprovado, faz o update
		if (opt == Constantes.APROVADO) {
			JogoDAO jogoDAO = JogoDAO.getInstance();
			try {
				vo.setId(idJogo);
				jogoDAO.atualiza(vo);
				CacheManager.getCacheDeJogos().refreshNoCache();
				CacheManager.getCacheDeJogosEN().refreshNoCacheEN();
			} catch (DAOException e) {
				logger.error("erro ao atualizar o jogo!",e);
			}
		}

		// manda email para o solicitante/colaboradores
		if (usuarioEmail!=null && usuarioEmail.length()>7 && !usuarioEmail.equals("sem email!"))
		 {
		 ColaboradoresDAO colaboradoresDAO = ColaboradoresDAO.getinstance();
		 try {
			ArrayList<KeyValueVO> colaboradores = (ArrayList<KeyValueVO>) colaboradoresDAO.buscaColaboradores();
			ArrayList<KeyValueVO> solicitantes = new  ArrayList<KeyValueVO>();
			solicitantes.add(new KeyValueVO(usuarioNome,usuarioEmail));
			EmailHelper.mandaEmailAlteracaoSolicitacao(solicitantes,colaboradores,solicitacaoDeJogo);
		  }  catch (DAOException e) {
			logger.error("erro no DAO do envio de emails!",e);
		  } catch (Exception e) {
			 logger.error("erro no envio de emails!",e);
		  }
		 }

    	Collection<?> collection = null;
    	try {
    		collection = daoJogoTemplate.buscaAjudasReprovadas();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
    	output.setValue("textos", collection);

    	return SUCCESS;
    }


	/**
	 * efetiva cadastro de novo jogo
	 * @return
	 * @throws ActionException
	 */
	public String alteraSolicitacaoAjudaNovoJogo() throws ActionException {

		int id = input.getInt("id");
		int idJogo= input.getInt("jogo_id");

    	// ajusta o VO de jogo
		Jogo vo = new Jogo();
    	vo.setId(idJogo);
     	String fabricanteID= input.getString("fabricante_id");
     	FabricanteVO fabricanteVO = new FabricanteVO();
     	fabricanteVO.setId(Integer.parseInt(fabricanteID));
     	vo.setFabricante(fabricanteVO);
     	String licencaID= input.getString("licenca_id");
     	LicencaVO licencaVO = new LicencaVO();
     	licencaVO.setId(Integer.parseInt(licencaID));
     	vo.setLicenca(licencaVO);
     	vo.setAberto(input.getInt("aberto"));
     	vo.setTipo(input.getInt("tipo"));
     	vo.setJogaEmRede(input.getInt("jogaEmRede"));
     	vo.setPrecisa3d(input.getInt("precisa3d"));
     	vo.setTemSom(input.getInt("temSom"));
     	vo.setConsoleOuX11(input.getInt("consoleOuX11"));
     	vo.setIdioma(input.getString("idioma"));
     	vo.setSiteOficial(input.getString("siteOficial"));
     	vo.setDescricao(input.getString("descricao"));

     	// padrao
		vo.setDestaque(false);
		vo.setHits(0);
		vo.setNotaGeral(5);
		vo.setSiteCompra(null);
		vo.setVotos(0);

		int tipoSolicitacao = input.getInt("tipoSolicitacao");
		int opt = input.getInt("opt");
		String justificativa = input.getString("justificativa");

		String jogoNome = input.getString("nome");
		vo.setNome(jogoNome);
		String usuarioNome = input.getString("usuario");
		String usuarioEmail = input.getString("email");

		SolicitacaoVO solicitacaoDeJogo = new SolicitacaoVO();
		solicitacaoDeJogo.setId(idJogo);
		solicitacaoDeJogo.setNome(jogoNome);
		Usuario usuario = new Usuario();
		usuario.setEmail(usuarioNome);
		usuario.setSenha(usuarioEmail);
		TextoVO texto = new TextoVO();
		texto.setId(id);
		texto.setTipo(tipoSolicitacao);
		texto.setTexto(justificativa);
		if (opt == Constantes.APROVADO)
		 {
		  texto.setAprovado(true);
		 }
		else
 		 texto.setAprovado(false);
		solicitacaoDeJogo.setTexto(texto);
		solicitacaoDeJogo.setUsuario(usuario);
		solicitacaoDeJogo.setCategoria(Constantes.JOGO);

		// altera o status para aprovado/reprovado
		try {
			TextoDeJogoVO tvo = new TextoDeJogoVO();
			tvo.setAprovado(solicitacaoDeJogo.getTexto().getAprovado());
			tvo.setId(solicitacaoDeJogo.getTexto().getId());
			if (tvo.isAprovado())
			{
			 // muda status
			 daoJogoTemplate.atualizaStatus(tvo);
			 UpdateLogHelper.setUpdateLog("Alteradas informações do jogo ["+jogoNome+"]");
			 // adiciona credito
			 if (usuarioNome!=null) {
 			  JogoTemplate jogoTemplate = (JogoTemplate) daoJogoTemplate.buscaUm(new JogoTemplate(id));
			  UsuariosLGDAO usuarioDao = UsuariosLGDAO.getInstance();
			  if (jogoTemplate !=null && jogoTemplate.getUsuario() !=null && jogoTemplate.getUsuario().getId()>0)
			  usuarioDao.atualizaJogosContribuidos(jogoTemplate.getUsuario().getId());
			 }
			}
			else
			 daoJogoTemplate.remove(new JogoTemplate(id));
		} catch (DAOException e) {
			logger.error("erro na atualizacao!",e);
		} catch (SQLException e) {
			logger.error("erro na atualizacao!",e);
		}

		// se aprovado, cadastra
		if (opt == Constantes.APROVADO) {
			JogoDAO jogoDAO = JogoDAO.getInstance();
			try {
				vo.setIdioma(Constantes.IDIOMA_STR_PT_BR);
				int jogoId = jogoDAO.adicionaVoltandoID(vo);
				vo.setJogoId(jogoId);
				vo.setIdioma(Constantes.IDIOMA_STR_EN_US);
				jogoDAO.adiciona(vo);
				CacheManager.getCacheDeJogos().refreshNoCache();
				CacheManager.getCacheDeJogosEN().refreshNoCacheEN();
			} catch (DAOException e) {
				logger.error("erro ao atualizar o jogo!",e);
			}
		}

		// manda email para o solicitante/colaboradores
		if (usuarioEmail!=null && usuarioEmail.length()>7 && !usuarioEmail.equals("sem email!"))
		 {
		 ColaboradoresDAO colaboradoresDAO = ColaboradoresDAO.getinstance();
		 try {
			ArrayList<KeyValueVO> colaboradores = (ArrayList<KeyValueVO>) colaboradoresDAO.buscaColaboradores();
			ArrayList<KeyValueVO> solicitantes = new  ArrayList<KeyValueVO>();
			solicitantes.add(new KeyValueVO(usuarioNome,usuarioEmail));
			EmailHelper.mandaEmailAlteracaoSolicitacao(solicitantes,colaboradores,solicitacaoDeJogo);
		  }  catch (DAOException e) {
			logger.error("erro no DAO do envio de emails!",e);
		  } catch (Exception e) {
			 logger.error("erro no envio de emails!",e);
		  }
		 }

    	Collection<?> collection = null;
    	try {
    		collection = daoJogoTemplate.buscaAjudasReprovadas();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
    	output.setValue("textos", collection);

    	return SUCCESS;
    }
}