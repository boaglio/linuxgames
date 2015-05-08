package br.com.linuxgames.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.core.InputException;
import org.mentawai.core.RequestInput;
import org.mentawai.filter.AuthenticationFree;
import org.mentawai.i18n.LocaleManager;
import org.mentawai.list.ListData;
import org.mentawai.list.ListManager;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.cache.EmuladorCache;
import br.com.linuxgames.model.dao.BibliotecaXemuladorDAO;
import br.com.linuxgames.model.dao.ColaboradoresDAO;
import br.com.linuxgames.model.dao.EmuladorDAO;
import br.com.linuxgames.model.dao.EmuladoresFavoritosDAO;
import br.com.linuxgames.model.dao.ReviewDeEmuladorDAO;
import br.com.linuxgames.model.dao.RoteiroInstalacaoEmuDAO;
import br.com.linuxgames.model.dao.TelaDeEmuladorDAO;
import br.com.linuxgames.model.dao.TextoDeEmuladorDAO;
import br.com.linuxgames.model.dao.VersaoDeEmuladorDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.EmuladorVO;
import br.com.linuxgames.model.vo.EmuladoresFavoritosVO;
import br.com.linuxgames.model.vo.InfoVO;
import br.com.linuxgames.model.vo.KeyValueVO;
import br.com.linuxgames.model.vo.RoteiroInstalacaoEmuVO;
import br.com.linuxgames.model.vo.SolicitacaoVO;
import br.com.linuxgames.model.vo.TelaDeEmuladorVO;
import br.com.linuxgames.model.vo.TextoDeEmuladorVO;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.EmailHelper;
import br.com.linuxgames.util.InfoHelper;
import br.com.linuxgames.util.TopHelper;
import br.com.linuxgames.util.UserHelper;


public class InfoEmuladorAction extends BaseAction implements AuthenticationFree {

	Logger logger = Logger.getLogger(this.getClass());
	private InfoVO vo;

	public boolean bypassAuthentication(String arg0) {
		return true;
	}

	public String execute() throws Exception
	{
	 InfoHelper.setExtraInfo(this);
	 return SUCCESS;
	}

    /**
     * action que busca um emulador
     * @return
     * @throws ActionException
     */
    public String emulador() throws ActionException {
		InfoHelper.setExtraInfo(this);
    	int idEmulador = 0;

    	try {
    		idEmulador = input.getInt("id");
		}catch (InputException ie) {
			return NULL;
		}

    	setVotoEmulador(idEmulador);
    	setEmuladorFavorito();
    	EmuladorCache emuladorCache = CacheManager.getCacheDeEmuladores();
    	EmuladorVO storedVO = (EmuladorVO) emuladorCache.getCacheComoHashMap().get(new Integer(idEmulador));
    	if (storedVO==null) return NULL;

		logger.info("vo lido ID="+storedVO.getId()+" Nome="+storedVO.getNome());
		output.setValue("id",  String.valueOf(storedVO.getId()));
		output.setValue("nome", storedVO.getNome());
		output.setValue("siteOficial", storedVO.getSiteOficial());
		output.setValue("descricao", storedVO.getDescricao());
		output.setValue("votos", storedVO.getVotos());
		output.setValue("notaGeral", storedVO.getNotaGeral());

		ListData listSimNao = ListManager.getList("simOuNao");
		String jogaEmRede = listSimNao.getValue(storedVO.getJogaEmRede(), LocaleManager.getDefaultLocale());
		output.setValue("jogaEmRede",  jogaEmRede);
		String temSom = listSimNao.getValue(storedVO.getTemSom(), LocaleManager.getDefaultLocale());
		output.setValue("temSom",  temSom);

		ListData listConsoleOuX11 = ListManager.getList("consoleOuX11");
		String consoleOuX11 = listConsoleOuX11.getValue(storedVO.getConsoleOuX11(), LocaleManager.getDefaultLocale());
		output.setValue("consoleOuX11",  consoleOuX11);

		ListData listTiposDeEmulador = ListManager.getList("tiposDeEmulador");
		String tipo = listTiposDeEmulador.getValue(storedVO.getTipo(), LocaleManager.getDefaultLocale());
		output.setValue("tipo",  tipo);

		ListData listLicenca = ListManager.getList("LICENCAS");
		String licenca = listLicenca.getValue(storedVO.getLicenca().getId(), LocaleManager.getDefaultLocale());
		output.setValue("licenca",  licenca);

		ListData listFabricantes = ListManager.getList("FABRICANTES");
		String fabricante = listFabricantes.getValue(storedVO.getFabricante().getId(), LocaleManager.getDefaultLocale());
		output.setValue("fabricante",fabricante);

		output.setValue("aba1","S");
		TopHelper.updateEmuladorHelper(idEmulador, output);
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
    	int idEmulador = 0;

    	try {
    		idEmulador = input.getInt("id");
		}catch (InputException ie) {
			return NULL;
		}

    	boolean estaLogado =  UserHelper.usuarioEstaLogado(this);
    	setEmuladorFavorito();
		// buscar reviews
		ReviewDeEmuladorDAO reviewDeEmuladorDAO =ReviewDeEmuladorDAO.getinstance();
		Collection<?> reviews;
		try {
			reviews = reviewDeEmuladorDAO.buscaPorEmulador(idEmulador);
			output.setValue("reviews", reviews);

			if (estaLogado)
			{
			 ReviewDeEmuladorDAO daoReviewDeEmulador = ReviewDeEmuladorDAO.getinstance();
			 esseUsuarioJaVotou = daoReviewDeEmulador.usuarioFezReviewDeEmulador(idUsuario,idEmulador);

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
		TopHelper.updateEmuladorHelper(idEmulador, output);
		return SUCCESS;
    }

    /**
     * aba de roteiros de instala&ccedil;&atilde;o
     * @return
     * @throws ActionException
     */
    public String inst() throws ActionException {
    	InfoHelper.setExtraInfo(this);
    	int idEmulador = 0;

    	try {
    		idEmulador = input.getInt("id");
		}catch (InputException ie) {
			return NULL;
    	}

    	EmuladorCache emuladorCache = CacheManager.getCacheDeEmuladores();
    	EmuladorVO storedVO = (EmuladorVO) emuladorCache.getCacheComoHashMap().get(new Integer(idEmulador ));
    	if (storedVO==null) return NULL;

    	setEmuladorFavorito();
    	// busca bibliotecas utilizadas
		try {
			BibliotecaXemuladorDAO bibliotecaXemuladorDAO = BibliotecaXemuladorDAO.getinstance();
			Collection<?> libs = (Collection<?>) bibliotecaXemuladorDAO.buscaPorEmulador(idEmulador);
			output.setValue("libs", libs);
		} catch (DAOException e) {
			logger.error("Erro na busca de libs...",e);
		}

    	// busca instalacoes
		try {
			RoteiroInstalacaoEmuDAO roteiroInstalacaoEmuDAO = RoteiroInstalacaoEmuDAO.getinstance();
			Collection<?> roteiros = (Collection<?>) roteiroInstalacaoEmuDAO.buscaPorEmulador(idEmulador);
			output.setValue("roteiros", roteiros);
		} catch (DAOException e) {
			logger.error("Erro na busca de roteiros...",e);
		}

		output.setValue("aba3","S");
		output.setValue("nome", storedVO.getNome());
		output.setValue("notaGeral", storedVO.getNotaGeral());
		TopHelper.updateEmuladorHelper(idEmulador, output);
		return SUCCESS;
    }

    /**
     * aba de dicas
     * @return
     * @throws ActionException
     */
    public String dica() throws ActionException {
    	InfoHelper.setExtraInfo(this);
    	int idEmulador = 0;

    	try {
    		idEmulador = input.getInt("id");
		}catch (InputException ie) {
			return NULL;
		}

    	EmuladorCache emuladorCache = CacheManager.getCacheDeEmuladores();
    	EmuladorVO storedVO = (EmuladorVO) emuladorCache.getCacheComoHashMap().get(new Integer(idEmulador ));
    	if (storedVO==null) return NULL;

    	setEmuladorFavorito();
       // buscar dicas
		try {
			TextoDeEmuladorDAO textoDeEmuladorDAO = TextoDeEmuladorDAO.getinstance();
			Collection<?> dicas = (Collection<?>) textoDeEmuladorDAO.buscaPorEmulador(idEmulador);
			output.setValue("dicas", dicas);
		} catch (DAOException e) {
			logger.error("Erro na busca de dicas...",e);
		}

		output.setValue("aba4","S");
		output.setValue("id", String.valueOf(idEmulador));
		output.setValue("nome", storedVO.getNome());
		output.setValue("notaGeral", storedVO.getNotaGeral());
		TopHelper.updateEmuladorHelper(idEmulador, output);
		return SUCCESS;
    }

    /**
     * aba de telas
     * @return
     * @throws ActionException
     */
    public String tela() throws ActionException {
    	InfoHelper.setExtraInfo(this);
    	int idEmulador = 0;

    	try {
    		idEmulador = input.getInt("id");
		}catch (InputException ie) {
			return NULL;
		}

    	EmuladorCache emuladorCache = CacheManager.getCacheDeEmuladores();
    	EmuladorVO storedVO = (EmuladorVO) emuladorCache.getCacheComoHashMap().get(new Integer(idEmulador ));
    	if (storedVO==null) return NULL;

    	setEmuladorFavorito();
        try {
    	 TelaDeEmuladorVO vo = new TelaDeEmuladorVO();
         vo.getEmulador().setId(idEmulador);
    	 TelaDeEmuladorDAO telaDeEmuladorDAO = TelaDeEmuladorDAO.getInstance();
    	 Collection<?> telas = (Collection<?>) telaDeEmuladorDAO.buscaTelasPorEmulador(vo);
    	 output.setValue("telas", telas);
		} catch (DAOException e) {
			logger.error("Erro na busca de telas...",e);
		}

		output.setValue("aba5","S");
		output.setValue("id", String.valueOf(idEmulador));
		output.setValue("nome", storedVO.getNome());
		output.setValue("notaGeral", storedVO.getNotaGeral());
		TopHelper.updateEmuladorHelper(idEmulador, output);
		return SUCCESS;
    }

    /**
     * aba de downloads
     * @return
     * @throws ActionException
     */
    public String download() throws ActionException {
    	InfoHelper.setExtraInfo(this);
    	int idEmulador = 0;

    	try {
    		idEmulador = input.getInt("id");
		}catch (InputException ie) {
			return NULL;
		}

    	setEmuladorFavorito();
    	EmuladorCache emuladorCache = CacheManager.getCacheDeEmuladores();
    	EmuladorVO storedVO = (EmuladorVO) emuladorCache.getCacheComoHashMap().get(new Integer(idEmulador ));
    	if (storedVO==null) return NULL;


    	try {
    	 VersaoDeEmuladorDAO versaoDeEmuladorDAO = VersaoDeEmuladorDAO.getinstance();
    	 Collection<?> links = versaoDeEmuladorDAO.buscaPorEmulador(idEmulador);
    	 output.setValue("links", links);
		} catch (DAOException e) {
			logger.error("Erro na busca de links...",e);
		}

		output.setValue("aba6","S");
		output.setValue("id", String.valueOf(idEmulador));
		output.setValue("nome", storedVO.getNome());
		output.setValue("notaGeral", storedVO.getNotaGeral());
		TopHelper.updateEmuladorHelper(idEmulador, output);
		return SUCCESS;
    }

    /**
     * Atualiza Roteiro do Emulador
     * @return
     * @throws ActionException
     */
	public String atualizaRoteiro() throws ActionException {
    	int idUsuario = InfoHelper.setExtraInfo(this);
    	int idEmulador = 0;

    	try {
    		idEmulador = input.getInt("id");
		}catch (InputException ie) {
			return NULL;
		}

    	EmuladorCache emuladorCache = CacheManager.getCacheDeEmuladores();
    	EmuladorVO storedVO = (EmuladorVO) emuladorCache.getCacheComoHashMap().get(new Integer(idEmulador ));
    	if (storedVO==null) return NULL;

    	setEmuladorFavorito();
    	//	Grava roteiro (reprovado por padrao)
    	RoteiroInstalacaoEmuVO vo = new RoteiroInstalacaoEmuVO();
    	vo.getEmulador().setId(input.getInt("id"));
    	vo.getDistro().setId(input.getInt("distro_id"));
    	vo.setDescricao(input.getString("descricao"));
    	vo.getUsuario().setId(idUsuario);
    	vo.setAprovado(Constantes.REPROVADO);

    	try {
    		RoteiroInstalacaoEmuDAO roteiroInstalacaoEmuDAO = RoteiroInstalacaoEmuDAO.getinstance();
    		roteiroInstalacaoEmuDAO.adiciona(vo);
    		int roteiroId = roteiroInstalacaoEmuDAO.buscaMaxId();
    		// manda email de solicitacao
    		ColaboradoresDAO colaboradoresDAO = ColaboradoresDAO.getinstance();
    		ArrayList<KeyValueVO> colaboradores = (ArrayList<KeyValueVO>) colaboradoresDAO.buscaColaboradores();
    		SolicitacaoVO solicitacao = new SolicitacaoVO();
    		solicitacao.setNome(storedVO.getNome());
    		solicitacao.setId(roteiroId);
    		solicitacao.setTipoDeSolicitacao(Constantes.ROTEIRO_INSTALACAO);
    		solicitacao.setCategoria(Constantes.EMULADOR);
    		EmailHelper.mandaEmailSolicitacao(colaboradores,solicitacao);
		} catch (DAOException e) {
			logger.error("DAO:Cadastrando novo roteiro de solicitacao de Emulador...",e);
		} catch (Exception e) {
			logger.error("Cadastrando novo roteiro de solicitacao...",e);
		}

		output.setValue("aba3","S");
		output.setValue("id", String.valueOf(idEmulador));
		output.setValue("nome", storedVO.getNome());
		output.setValue("notaGeral", storedVO.getNotaGeral());
		TopHelper.updateEmuladorHelper(idEmulador, output);
		return SUCCESS;
    }

    /**
     * Atualiza Dica do Emulador
     * @return
     * @throws ActionException
     */
	public String atualizaDica() throws ActionException {
    	int idUsuario = InfoHelper.setExtraInfo(this);
    	int idEmulador = 0;

    	try {
    		idEmulador = input.getInt("id");
		}catch (InputException ie) {
			return NULL;
		}

    	EmuladorCache emuladorCache = CacheManager.getCacheDeEmuladores();
    	EmuladorVO storedVO = (EmuladorVO) emuladorCache.getCacheComoHashMap().get(new Integer(idEmulador ));
    	if (storedVO==null) return NULL;

    	setEmuladorFavorito();
    	//	Grava Dica (reprovada por padrao)
    	TextoDeEmuladorVO vo = new TextoDeEmuladorVO();
    	vo.getEmulador().setId(input.getInt("id"));
    	vo.setTipo(input.getInt("tipo"));
    	vo.setTexto(input.getString("descricao"));
    	vo.setLink(input.getString("link"));
    	vo.getUsuario().setId(idUsuario);
    	vo.setDataPublic(new Date());
    	vo.setAprovado(Constantes.REPROVADO);

    	try {
    		TextoDeEmuladorDAO textoDeEmuladorDAO = TextoDeEmuladorDAO.getinstance();
    		textoDeEmuladorDAO.adiciona(vo);
    		int textoId = textoDeEmuladorDAO.buscaMaxId();
    		// manda email de solicitacao
    		ColaboradoresDAO colaboradoresDAO = ColaboradoresDAO.getinstance();
    		ArrayList<KeyValueVO> colaboradores = (ArrayList<KeyValueVO>) colaboradoresDAO.buscaColaboradores();
    		SolicitacaoVO solicitacao = new SolicitacaoVO();
    		solicitacao.setNome(storedVO.getNome());
    		solicitacao.setId(textoId);
    		solicitacao.setTipoDeSolicitacao(vo.getTipo());
    		solicitacao.setCategoria(Constantes.EMULADOR);
    		EmailHelper.mandaEmailSolicitacao(colaboradores,solicitacao);
		} catch (DAOException e) {
			logger.error("DAO:Cadastrando nova dica...",e);
		} catch (Exception e) {
			logger.error("Cadastrando nova dica...",e);
		}

		output.setValue("aba4","S");
		output.setValue("id", String.valueOf(idEmulador));
		output.setValue("nome", storedVO.getNome());
		output.setValue("notaGeral", storedVO.getNotaGeral());
		TopHelper.updateEmuladorHelper(idEmulador, output);
		return SUCCESS;
    }

    // getters e setters
	public InfoVO getVo() {
		return vo;
	}

	public void setVo(InfoVO vo) {
		this.vo = vo;
	}

//	 metodos privados
    private void setVotoEmulador(int id) {
    	boolean esseIPjaVotou =false;
    	try {
    	EmuladorDAO daoEmulador = EmuladorDAO.getInstance();
    	HttpServletRequest req = ( (RequestInput) input).getRequest();
    	String IPdoCliente = 	req.getRemoteAddr();
		esseIPjaVotou = daoEmulador.esseIPjaVotou(IPdoCliente,id);
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
     * verifica se o emulador favorito esta cadastrado
     */
    private void setEmuladorFavorito() {

    	int idDoUsuarioLogado = UserHelper.mantemUsuarioNaRequisicao(this);

    	if (idDoUsuarioLogado > 0)
    	{
    	 EmuladoresFavoritosDAO jogosFavoritosDAO = EmuladoresFavoritosDAO.getinstance();
    	 EmuladoresFavoritosVO emuladoresFavoritosVO = new EmuladoresFavoritosVO();
    	 emuladoresFavoritosVO.getEmulador().setId(input.getInt("id"));
    	 emuladoresFavoritosVO.getUsuario().setId(idDoUsuarioLogado);
	 	 try {
	 		 if (jogosFavoritosDAO.existeFavoritoParaEsseEmulador(emuladoresFavoritosVO))
	 		 {
	 			logger.info(input.getInt("id")+" ta no favoritos!");
	 			output.setValue("emuladorFavorito",Constantes.OPCAO_SIM);
	 		 }
	 		 else
	 		 {
	 			logger.info(input.getInt("id")+" NAO ta no favoritos!");
	 			output.setValue("emuladorFavorito",Constantes.OPCAO_NAO);
	 		 }
	 	 }
	 	 catch (DAOException e) {
			   logger.error("Erro ao gravar o emulador favorito",e);
			}
    	}
    }

}