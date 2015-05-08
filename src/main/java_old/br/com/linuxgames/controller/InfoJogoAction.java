package br.com.linuxgames.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.core.InputException;
import org.mentawai.core.RequestInput;
import org.mentawai.filter.AuthenticationFree;
import org.mentawai.list.ListData;
import org.mentawai.list.ListManager;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.cache.JogoCache;
import br.com.linuxgames.model.dao.BibliotecaXjogoDAO;
import br.com.linuxgames.model.dao.ColaboradoresDAO;
import br.com.linuxgames.model.dao.JogoDAO;
import br.com.linuxgames.model.dao.JogoTemplateDAO;
import br.com.linuxgames.model.dao.JogosFavoritosDAO;
import br.com.linuxgames.model.dao.ReviewDeJogoDAO;
import br.com.linuxgames.model.dao.RoteiroInstalacaoJogoDAO;
import br.com.linuxgames.model.dao.TelaDeJogoDAO;
import br.com.linuxgames.model.dao.TextoDeJogoDAO;
import br.com.linuxgames.model.dao.UsuariosDAO;
import br.com.linuxgames.model.dao.VersaoDeJogoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.AjudaJogo;
import br.com.linuxgames.model.vo.FabricanteVO;
import br.com.linuxgames.model.vo.InfoVO;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.model.vo.JogoTemplate;
import br.com.linuxgames.model.vo.JogosFavoritosVO;
import br.com.linuxgames.model.vo.KeyValueVO;
import br.com.linuxgames.model.vo.LicencaVO;
import br.com.linuxgames.model.vo.RoteiroInstalacaoJogoVO;
import br.com.linuxgames.model.vo.SolicitacaoVO;
import br.com.linuxgames.model.vo.TelaDeJogoVO;
import br.com.linuxgames.model.vo.TextoDeJogoVO;
import br.com.linuxgames.model.vo.Usuario;
import br.com.linuxgames.model.vo.UsuarioSession;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.EmailHelper;
import br.com.linuxgames.util.InfoHelper;
import br.com.linuxgames.util.LocaleHelper;
import br.com.linuxgames.util.TopHelper;
import br.com.linuxgames.util.UserHelper;


public class InfoJogoAction extends BaseAction implements AuthenticationFree {

	Logger logger = Logger.getLogger(this.getClass());
	private InfoVO vo;
	private int jogoId;

	public static final String AJUDA = "ajuda";
	public static final String AJUDA_CONCLUIDA = "ajudaConcluida";


	public boolean bypassAuthentication(String arg0) {
		return true;
	}

	public String execute() throws Exception
	{
	 InfoHelper.setExtraInfo(this);
	 return SUCCESS;
	}

    /**
     * action que busca um jogo
     * @return
     * @throws ActionException
     */
    public String jogo() throws ActionException {

    	String localeDoUsuario = input.getString("loc");
    	Locale localeDB = LocaleHelper.getUserLocaleForDBListData(localeDoUsuario);
    	Locale locale = LocaleHelper.getUserLocale(input.getString("loc"));

		InfoHelper.setExtraInfo(this);
		try {
    	 setJogoId(input.getInt("id"));
		}catch (InputException ie) {
			return NULL;
		}
    	setJogoFavorito();
    	setVotoJogo(getJogoId());
//    	JogoCache jogoCache = (JogoCache) LocaleHelper.getJogoCacheByLocale(userLocale);
    	Jogo storedVO = InfoHelper.buscarJogoDoCache(input.getInt("id"),localeDoUsuario);
//    	Jogo storedVO = (Jogo) jogoCache.getCacheComoHashMap().get(new Integer(getJogoId()));
    	if (storedVO==null) return NULL;
		logger.info("vo lido ID="+storedVO.getId()+" Nome="+storedVO.getNome());
		output.setValue("id",  String.valueOf(storedVO.getId()));
		output.setValue("nome", storedVO.getNome());
		output.setValue("siteOficial", storedVO.getSiteOficial());
		output.setValue("siteCompra", storedVO.getSiteCompra());
		output.setValue("descricao", storedVO.getDescricao());
		output.setValue("votos", storedVO.getVotos());
		output.setValue("notaGeral", storedVO.getNotaGeral());

		ListData listSimNao = ListManager.getList("simOuNao");
		String jogaEmRede = listSimNao.getValue(storedVO.getJogaEmRede(),locale);
		output.setValue("jogaEmRede",  jogaEmRede);
		String precisa3d = listSimNao.getValue(storedVO.getPrecisa3d(), locale);
		output.setValue("precisa3d",  precisa3d);
		String temSom = listSimNao.getValue(storedVO.getTemSom(), locale);
		output.setValue("temSom",  temSom);
		String aberto = listSimNao.getValue(storedVO.getAberto(), locale);
		output.setValue("aberto",  aberto);

		ListData listConsoleOuX11 = ListManager.getList("consoleOuX11");
		String consoleOuX11 = listConsoleOuX11.getValue(storedVO.getConsoleOuX11(), locale);
		output.setValue("consoleOuX11",  consoleOuX11);

		ListData listTiposDeJogo = ListManager.getList("tiposDeJogo");
		String tipo = listTiposDeJogo.getValue(storedVO.getTipo(), locale);
		output.setValue("tipo",  tipo);

		ListData listLicenca = ListManager.getList("LICENCAS");
		String licenca = listLicenca.getValue(storedVO.getLicenca().getId(), localeDB);
		output.setValue("licenca",  licenca);

		ListData listFabricantes = ListManager.getList("FABRICANTES");
		String fabricante = listFabricantes.getValue(storedVO.getFabricante().getId(), localeDB);
		output.setValue("fabricante",fabricante);

		output.setValue("aba1","S");
		TopHelper.updateJogoCache(getJogoId(),output);
        return SUCCESS;
    }


    /**
     * action que grava o jogo como favorito
     * @return
     * @throws ActionException
     */
    public String jogoFavorito() throws ActionException {

    	// opcao selecionada
    	String opcaoFavoritos = input.getString("jogoFavorito");

        int idDoUsuarioLogado = UserHelper.mantemUsuarioNaRequisicao(this);

        setJogoId(input.getInt("id"));

    	if (idDoUsuarioLogado > 0)
    	{
    	 JogosFavoritosDAO jogosFavoritosDAO = JogosFavoritosDAO.getinstance();
    	 JogosFavoritosVO jogosFavoritosVO = new JogosFavoritosVO();
    	 jogosFavoritosVO.getJogo().setId(getJogoId());
    	 jogosFavoritosVO.getUsuario().setId(idDoUsuarioLogado);
	 	 try {
    	      if (opcaoFavoritos.equals(Constantes.OPCAO_SIM))
				jogosFavoritosDAO.adiciona(jogosFavoritosVO);
    		  else
  		    	jogosFavoritosDAO.remove(jogosFavoritosVO);
			  }
	 	 catch (DAOException e) {
			   logger.error("Erro ao gravar o jogo favorito",e);
			}
    	}

    	return SUCCESS;
    }


    /**
     * aba de reviews
     * @return
     * @throws ActionException
     */
    public String review() throws ActionException {
    	int idUsuario = InfoHelper.setExtraInfo(this);
		boolean esseUsuarioJaVotou = false ;
		try {
		setJogoId(input.getInt("id"));
		setJogoFavorito();
		}catch (InputException ie) {
			return NULL;
		}
    	boolean estaLogado =  UserHelper.usuarioEstaLogado(this);

    	// le o site de compra
    	JogoCache jogoCache = (JogoCache) CacheManager.getCacheDeJogos();
    	Jogo storedVO = (Jogo) jogoCache.getCacheComoHashMap().get(new Integer(getJogoId()));
    	if (storedVO==null) return NULL;

		output.setValue("siteCompra", storedVO.getSiteCompra());

		// buscar reviews
		ReviewDeJogoDAO reviewDeJogoDAO =ReviewDeJogoDAO.getinstance();
		Collection<?> reviews;
		try {
			reviews = reviewDeJogoDAO.buscaPorJogo(getJogoId());
			output.setValue("reviews", reviews);

			if (estaLogado)
			{
			 ReviewDeJogoDAO daoReviewDeJogo = ReviewDeJogoDAO.getinstance();
			 esseUsuarioJaVotou = daoReviewDeJogo.usuarioFezReviewDeJogo(idUsuario,getJogoId());

			 if (esseUsuarioJaVotou)
			  output.setValue("jaVotou", "S");
			 else
			  output.setValue("jaVotou", "N");
			}
			else
			{
			 output.setValue("jaVotou", "N");
			}

		} catch (DAOException e) {
			logger.debug("Erro na busca de reviews...",e);
		}

		output.setValue("aba2","S");
		TopHelper.updateJogoCache(getJogoId(),output);
		return SUCCESS;
    }

    /**
     * aba de roteiros de instala&ccedil;&atilde;o
     * @return
     * @throws ActionException
     */
    public String inst() throws ActionException {
    	InfoHelper.setExtraInfo(this);
    	try {
    	 setJogoId(input.getInt("id"));
		}catch (InputException ie) {
			return NULL;
		}
    	setJogoFavorito();
    	JogoCache jogoCache = (JogoCache) CacheManager.getCacheDeJogos();
    	Jogo storedVO = (Jogo) jogoCache.getCacheComoHashMap().get(new Integer(getJogoId() ));
    	if (storedVO==null) return NULL;

    	// le o site de compra
		output.setValue("siteCompra", storedVO.getSiteCompra());

    	// busca bibliotecas utilizadas
		try {
			BibliotecaXjogoDAO bibliotecaXjogoDAO = BibliotecaXjogoDAO.getinstance();
			Collection<?> libs = (Collection<?>) bibliotecaXjogoDAO.buscaPorJogo(getJogoId());
			output.setValue("libs", libs);
		} catch (DAOException e) {
			logger.error("Erro na busca de libs...",e);
		}

    	// busca instalacoes
		try {
			RoteiroInstalacaoJogoDAO roteiroInstalacaoJogoDAO = RoteiroInstalacaoJogoDAO.getinstance();
			Collection<?> roteiros = (Collection<?>) roteiroInstalacaoJogoDAO.buscaPorJogo(getJogoId());
			output.setValue("roteiros", roteiros);
		} catch (DAOException e) {
			logger.error("Erro na busca de roteiros...",e);
		}

		output.setValue("aba3","S");
		output.setValue("nome", storedVO.getNome());
		output.setValue("notaGeral", storedVO.getNotaGeral());
		TopHelper.updateJogoCache(getJogoId(),output);
		return SUCCESS;
    }

    /**
     * aba de dicas
     * @return
     * @throws ActionException
     */
    public String dica() throws ActionException {
    	InfoHelper.setExtraInfo(this);
    	try {
    	 setJogoId(input.getInt("id"));
		}catch (InputException ie) {
			return NULL;
		}
    	setJogoFavorito();
    	JogoCache jogoCache = (JogoCache) CacheManager.getCacheDeJogos();
    	Jogo storedVO = (Jogo) jogoCache.getCacheComoHashMap().get(new Integer(getJogoId() ));
    	if (storedVO==null) return NULL;


    	// le o site de compra
		output.setValue("siteCompra", storedVO.getSiteCompra());

       // buscar dicas
		try {
			TextoDeJogoDAO textoDeJogoDAO = TextoDeJogoDAO.getInstance();
			Collection<?> dicas = (Collection<?>) textoDeJogoDAO.buscaPorJogo(getJogoId());
			output.setValue("dicas", dicas);
		} catch (DAOException e) {
			logger.error("Erro na busca de dicas...",e);
		}

		output.setValue("aba4","S");
		output.setValue("id", String.valueOf(getJogoId()));
		output.setValue("nome", storedVO.getNome());
		output.setValue("notaGeral", storedVO.getNotaGeral());
		TopHelper.updateJogoCache(getJogoId(),output);
		return SUCCESS;
    }

    /**
     * aba de telas
     * @return
     * @throws ActionException
     */
    public String tela() throws ActionException {
    	InfoHelper.setExtraInfo(this);
    	try {
    	 setJogoId(input.getInt("id"));
		}catch (InputException ie) {
			return NULL;
		}
    	setJogoFavorito();
    	JogoCache jogoCache = (JogoCache) CacheManager.getCacheDeJogos();
    	Jogo storedVO = (Jogo) jogoCache.getCacheComoHashMap().get(new Integer(getJogoId() ));
    	if (storedVO==null) return NULL;


    	// le o site de compra
		output.setValue("siteCompra", storedVO.getSiteCompra());

        try {
    	 TelaDeJogoVO vo = new TelaDeJogoVO();
         vo.getJogo().setId(getJogoId());
    	 TelaDeJogoDAO telaDeJogoDAO = TelaDeJogoDAO.getInstance();
    	 Collection<?> telas = (Collection<?>) telaDeJogoDAO.buscaTelasPorJogo(vo);
    	 output.setValue("telas", telas);
		} catch (DAOException e) {
			logger.error("Erro na busca de telas...",e);
		}

		output.setValue("aba5","S");
		output.setValue("id", String.valueOf(getJogoId()));
		output.setValue("nome", storedVO.getNome());
		output.setValue("notaGeral", storedVO.getNotaGeral());
		TopHelper.updateJogoCache(getJogoId(),output);
		return SUCCESS;
    }

    /**
     * aba de downloads
     * @return
     * @throws ActionException
     */
    public String download() throws ActionException {
    	InfoHelper.setExtraInfo(this);
    	try {
    	 setJogoId(input.getInt("id"));
		}catch (InputException ie) {
			return NULL;
		}
    	setJogoFavorito();
    	JogoCache jogoCache = (JogoCache) CacheManager.getCacheDeJogos();
    	Jogo storedVO = (Jogo) jogoCache.getCacheComoHashMap().get(new Integer(jogoId ));
    	if (storedVO==null) return NULL;

    	// le o site de compra
		output.setValue("siteCompra", storedVO.getSiteCompra());

    	try {
    	 VersaoDeJogoDAO versaoDeJogoDAO = VersaoDeJogoDAO.getinstance();
    	 Collection<?> links = versaoDeJogoDAO.buscaPorJogo(jogoId);
    	 output.setValue("links", links);
		} catch (DAOException e) {
			logger.error("Erro na busca de links...",e);
		}

		output.setValue("aba6","S");
		output.setValue("id", String.valueOf(jogoId));
		output.setValue("nome", storedVO.getNome());
		output.setValue("notaGeral", storedVO.getNotaGeral());
		TopHelper.updateJogoCache(jogoId,output);
		return SUCCESS;
    }

    /**
     * Atualiza Roteiro do Jogo
     * @return
     * @throws ActionException
     */
	public String atualizaRoteiro() throws ActionException {
    	int idUsuario = InfoHelper.setExtraInfo(this);
    	try {
    	 setJogoId(input.getInt("id"));
		}catch (InputException ie) {
			return NULL;
		}
    	setJogoFavorito();
    	JogoCache jogoCache = (JogoCache) CacheManager.getCacheDeJogos();
    	Jogo storedVO = (Jogo) jogoCache.getCacheComoHashMap().get(new Integer(jogoId ));
    	if (storedVO==null) return NULL;

    	// le o site de compra
		output.setValue("siteCompra", storedVO.getSiteCompra());

    	//	Grava roteiro (reprovado por padrao)
    	RoteiroInstalacaoJogoVO vo = new RoteiroInstalacaoJogoVO();
    	vo.getJogo().setId(input.getInt("id"));
    	vo.getDistro().setId(input.getInt("distro_id"));
    	vo.setDescricao(input.getString("descricao"));
    	vo.getUsuario().setId(idUsuario);
    	vo.setAprovado(Constantes.REPROVADO);

    	try {
    		RoteiroInstalacaoJogoDAO roteiroInstalacaoJogoDAO = RoteiroInstalacaoJogoDAO.getinstance();
    		roteiroInstalacaoJogoDAO.adiciona(vo);
    		int roteiroId = roteiroInstalacaoJogoDAO.buscaMaxId();
    		// manda email de solicitacao
    		ArrayList<KeyValueVO> colaboradores = buscaColaboradores();
    		SolicitacaoVO solicitacao = new SolicitacaoVO();
    		solicitacao.setNome(storedVO.getNome());
    		solicitacao.setId(roteiroId);
    		solicitacao.setTipoDeSolicitacao(Constantes.ROTEIRO_INSTALACAO);
    		solicitacao.setCategoria(Constantes.JOGO);
    		EmailHelper.mandaEmailSolicitacao(colaboradores,solicitacao);
		} catch (DAOException e) {
			logger.error("DAO:Cadastrando novo roteiro de solicitacao...",e);
		} catch (Exception e) {
			logger.error("Cadastrando novo roteiro de solicitacao...",e);
		}

		output.setValue("aba3","S");
		output.setValue("id", String.valueOf(jogoId));
		output.setValue("nome", storedVO.getNome());
		output.setValue("notaGeral", storedVO.getNotaGeral());
		TopHelper.updateJogoCache(jogoId,output);
		return SUCCESS;
    }

	private ArrayList<KeyValueVO> buscaColaboradores() throws DAOException {
		ColaboradoresDAO colaboradoresDAO = ColaboradoresDAO.getinstance();
		ArrayList<KeyValueVO> colaboradores = (ArrayList<KeyValueVO>) colaboradoresDAO.buscaColaboradores();
		return colaboradores;
	}

    /**
     * Atualiza Dica do Jogo
     * @return
     * @throws ActionException
     */
    public String atualizaDica() throws ActionException {
    	int idUsuario = InfoHelper.setExtraInfo(this);
    	try {
    	 setJogoId(input.getInt("id"));
		}catch (InputException ie) {
			return NULL;
		}
    	setJogoFavorito();
    	JogoCache jogoCache = (JogoCache) CacheManager.getCacheDeJogos();
    	Jogo storedVO = (Jogo) jogoCache.getCacheComoHashMap().get(new Integer(jogoId ));
    	if (storedVO==null) return NULL;

    	// le o site de compra
		output.setValue("siteCompra", storedVO.getSiteCompra());

    	//	Grava Dica (reprovada por padrao)
    	TextoDeJogoVO vo = new TextoDeJogoVO();
    	vo.getJogo().setId(input.getInt("id"));
    	vo.setTipo(input.getInt("tipo"));
    	vo.setTexto(input.getString("descricao"));
    	vo.setLink(input.getString("link"));
    	vo.getUsuario().setId(idUsuario);
    	vo.setDataPublic(new Date());
    	vo.setAprovado(Constantes.REPROVADO);

    	try {
    		TextoDeJogoDAO textoDeJogoDAO = TextoDeJogoDAO.getInstance();
    		textoDeJogoDAO.adiciona(vo);
    		int textoId = textoDeJogoDAO.buscaMaxId();
    		ArrayList<KeyValueVO> colaboradores = buscaColaboradores();
    		SolicitacaoVO solicitacao = new SolicitacaoVO();
    		solicitacao.setNome(storedVO.getNome());
    		solicitacao.setId(textoId);
    		solicitacao.setTipoDeSolicitacao(vo.getTipo());
    		solicitacao.setCategoria(Constantes.JOGO);
    		EmailHelper.mandaEmailSolicitacao(colaboradores,solicitacao);
		} catch (DAOException e) {
			logger.error("DAO:Cadastrando nova dica...",e);
		} catch (Exception e) {
			logger.error("Cadastrando nova dica...",e);
		}

		output.setValue("aba4","S");
		output.setValue("id", String.valueOf(jogoId));
		output.setValue("nome", storedVO.getNome());
		output.setValue("notaGeral", storedVO.getNotaGeral());
		TopHelper.updateJogoCache(jogoId,output);
		return SUCCESS;
    }



    /**
     * Ajuda a alterar a informacao do jogo
     * @return
     * @throws ActionException
     */
    public String ajuda() throws ActionException {

    	InfoHelper.setExtraInfo(this);

    	String localeDoUsuario = input.getString("loc");


    	int idDoJogo = 0;

    	try {
    		idDoJogo = input.getInt("id");
		}catch (InputException ie) {
			return NULL;
		}

    	Jogo storedVO = null;
    	AjudaJogo  ajudaJogo = new AjudaJogo();

    	if (idDoJogo>0)
    		storedVO = InfoHelper.buscarJogoDoCache(idDoJogo,localeDoUsuario);
    	else
    	   ajudaJogo.setNovoJogo(true);


    	 	// carrega os dados do jogo e joga na tela para alterar

    		if (storedVO!=null) {
	    		output.setValue("nome", storedVO.getNome());
	    		output.setValue("tipo", storedVO.getTipo());
	    		output.setValue("licenca_id",  String.valueOf(storedVO.getLicenca().getId()));
	    		output.setValue("aberto", storedVO.getAberto());
	    		output.setValue("jogaEmRede", storedVO.getJogaEmRede());
	    		output.setValue("precisa3d", storedVO.getPrecisa3d());
	    		output.setValue("temSom", storedVO.getTemSom());
	    		output.setValue("consoleOuX11", storedVO.getConsoleOuX11());
	    		output.setValue("siteOficial", storedVO.getSiteOficial());
	    		output.setValue("descricao", storedVO.getDescricao());
	    		output.setValue("votos", storedVO.getVotos());
	    		output.setValue("hits", storedVO.getHits());
	    		output.setValue("fabricante_id",  String.valueOf(storedVO.getFabricante().getId()));
	    		output.setValue("idioma", storedVO.getIdioma());

	   		    output.setValue("id", String.valueOf(storedVO.getId()));
	     		output.setValue("jogo_id",  String.valueOf(storedVO.getJogoId()));
    		}

    		return AJUDA;

    }



    /**
     * Ajuda a alterar a informacao do jogo
     * @return
     * @throws ActionException
     */
    public String ajudaManda() throws ActionException {

    	int idUsuario = InfoHelper.setExtraInfo(this);

    	String localeDoUsuario = input.getString("loc");

    	int idDoJogo = 0;

    	try {
    		idDoJogo = input.getInt("id");
		}catch (InputException ie) {
			return NULL;
		}

    	Jogo storedVO = null;
    	AjudaJogo  ajudaJogo = new AjudaJogo();

    	if (idDoJogo>0)
    		storedVO = InfoHelper.buscarJogoDoCache(idDoJogo,localeDoUsuario);
    	else
    	   ajudaJogo.setNovoJogo(true);

    	 // grava a solicitacao na tabela de template e envia o email avisando

    	 JogoTemplateDAO jogoTemplateDAO = JogoTemplateDAO.getInstance();

    	 String obs = input.getString("jogoTemplateObs");

    	 JogoTemplate jogoAlterado = new JogoTemplate();
     	 jogoAlterado.setId( input.getInt("id"));
     	 String fabricanteID= input.getString("fabricante_id");
     	 FabricanteVO fabricanteVO = new FabricanteVO();
     	 fabricanteVO.setId(Integer.parseInt(fabricanteID));
     	 jogoAlterado.setFabricante(fabricanteVO);

     	 String licencaID= input.getString("licenca_id");
     	 LicencaVO licencaVO = new LicencaVO();
     	 licencaVO.setId(Integer.parseInt(licencaID));
     	 jogoAlterado.setLicenca(licencaVO);
     	 jogoAlterado.setAberto(input.getInt("aberto"));
     	 jogoAlterado.setTipo(input.getInt("tipo"));
     	 jogoAlterado.setJogaEmRede(input.getInt("jogaEmRede"));
     	 jogoAlterado.setPrecisa3d(input.getInt("precisa3d"));
     	 jogoAlterado.setTemSom(input.getInt("temSom"));
     	 jogoAlterado.setConsoleOuX11(input.getInt("consoleOuX11"));
     	 jogoAlterado.setVotos(0);
     	 jogoAlterado.setHits(0);
     	 jogoAlterado.setNotaGeral(0);
     	 jogoAlterado.setNome(input.getString("nome"));
     	 jogoAlterado.setDescricao(input.getString("descricao"));
     	 jogoAlterado.setSiteOficial(input.getString("siteOficial"));
     	 jogoAlterado.setUsuario(new Usuario(idUsuario));
     	 jogoAlterado.setIdioma(localeDoUsuario);
     	 jogoAlterado.setAprovado(Constantes.REPROVADO);
     	 if (storedVO!=null)
     	  jogoAlterado.setJogoId(storedVO.getId());
     	 jogoAlterado.setObservacao(obs);

     	 try {

        	UsuariosDAO usuariosDAO = UsuariosDAO.getInstance();
        	Usuario usuarioLogado = null;
        	if (idUsuario==0) {
        		usuarioLogado =new Usuario(idUsuario,"ninguem!");
        	}
        	else
        	{
        		usuarioLogado = (Usuario) usuariosDAO.buscaUm(new Usuario(idUsuario));
        	}
        	ajudaJogo.setJogoTemplateObs(obs);
        	ajudaJogo.setUsuario(usuarioLogado);
        	ajudaJogo.setLocaleDoUsuario(localeDoUsuario);

        	 // grava o jogo na tabela de template (por padrao reprovado)
			jogoTemplateDAO.adiciona(jogoAlterado);
			int jogoTemplateId = jogoTemplateDAO.buscaMaxId();
			ajudaJogo.setIdJogoTemplate(jogoTemplateId);
			ajudaJogo.setDataPublic(new Date());
			ajudaJogo.setAprovado(Constantes.REPROVADO);
			if (storedVO!=null && storedVO.getJogoId()>0) {
			 storedVO.setId(storedVO.getJogoId());
			 ajudaJogo.setJogo(new JogoTemplate(storedVO));
			}
			else
			{
			 ajudaJogo.setJogo(jogoAlterado);
			}

	    	ArrayList<KeyValueVO> colaboradores = buscaColaboradores();
			EmailHelper.mandaEmailAjudaJogo(colaboradores,ajudaJogo);

		 } catch (DAOException e) {
			logger.error("DAO:Cadastrando jogo template...",e);
		 } catch (Exception e) {
			logger.error("Cadastrando jogo template...",e);
		 }

       // redireciona para a tela de agradecimentos

       output.setValue("id", String.valueOf(jogoId));
       if (storedVO!=null) {
	    output.setValue("nome", storedVO.getNome());
	    output.setValue("notaGeral", storedVO.getNotaGeral());
       }
       if (jogoId>0)
        TopHelper.updateJogoCache(jogoId,output);

	   return AJUDA_CONCLUIDA;

    }


    // getters e setters
	public InfoVO getVo() {
		return vo;
	}

	public void setVo(InfoVO vo) {
		this.vo = vo;
	}

    public int getJogoId() {
		return jogoId;
	}

	public void setJogoId(int jogoId) {
		this.jogoId = jogoId;
	}

	//	 metodos privados
    private void setVotoJogo(int id) {
    	boolean esseIPjaVotou =false;
    	try {
    	JogoDAO daoJogo = JogoDAO.getInstance();
    	HttpServletRequest req = ( (RequestInput) input).getRequest();
    	String IPdoCliente = 	req.getRemoteAddr();
		esseIPjaVotou = daoJogo.esseIPjaVotou(IPdoCliente,id);
		}
    	 catch (DAOException e) {
			logger.error("erro na leitura de IP:", e);
		}

		if (esseIPjaVotou)
		 output.setValue("jaVotou", "S");
		else
		 output.setValue("jaVotou", "N");
    }


    /**
     * verifica se o jogo favorito esta cadastrado
     */
    private void setJogoFavorito() {

        int idDoUsuarioLogado = 0;

        UsuarioSession userSession = UserHelper.getUserFromSession(this);
        if (UserHelper.usuarioEstaLogado(this))
         {
          idDoUsuarioLogado = userSession.getId();
          this.getOutput().setValue("idUsuario",idDoUsuarioLogado);
         }

        setJogoId(input.getInt("id"));

    	if (idDoUsuarioLogado > 0)
    	{
    	 JogosFavoritosDAO jogosFavoritosDAO = JogosFavoritosDAO.getinstance();
    	 JogosFavoritosVO jogosFavoritosVO = new JogosFavoritosVO();
    	 jogosFavoritosVO.getJogo().setId(getJogoId());
    	 jogosFavoritosVO.getUsuario().setId(idDoUsuarioLogado);
	 	 try {
	 		 if (jogosFavoritosDAO.existeFavoritoParaEsseJogo(jogosFavoritosVO))
	 		 {
	 			logger.info(getJogoId()+" ta no favoritos!");
	 			output.setValue("jogoFavorito",Constantes.OPCAO_SIM);
	 		 }
	 		 else
	 		 {
	 			logger.info(getJogoId()+" NAO ta no favoritos!");
	 			output.setValue("jogoFavorito",Constantes.OPCAO_NAO);
	 		 }
	 	 }
	 	 catch (DAOException e) {
			   logger.error("Erro ao gravar o jogo favorito",e);
			}
    	}
    }
}