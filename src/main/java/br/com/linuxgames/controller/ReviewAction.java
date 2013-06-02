package br.com.linuxgames.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.core.RequestInput;
import org.mentawai.filter.AuthenticationFree;

import br.com.linuxgames.controller.cache.ArtigoCache;
import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.cache.EmuladorCache;
import br.com.linuxgames.controller.cache.JogoCache;
import br.com.linuxgames.controller.cache.JogoCachePTBR;
import br.com.linuxgames.model.dao.ReviewDeArtigoDAO;
import br.com.linuxgames.model.dao.ReviewDeEmuladorDAO;
import br.com.linuxgames.model.dao.ReviewDeJogoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.ArtigoVO;
import br.com.linuxgames.model.vo.Colaborador;
import br.com.linuxgames.model.vo.EmuladorVO;
import br.com.linuxgames.model.vo.EnqueteVO;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.model.vo.ReviewDeArtigoVO;
import br.com.linuxgames.model.vo.ReviewDeEmuladorVO;
import br.com.linuxgames.model.vo.ReviewDeJogoVO;
import br.com.linuxgames.util.InfoHelper;


public class ReviewAction extends BaseAction implements AuthenticationFree {

	Logger logger = Logger.getLogger(this.getClass());

	private EnqueteVO vo;
	private int action;

    // consequencia de gente q ja votou
	public static final String JA_VOTOU = "javotou";

	// consequencia dos resultados
	public static final String RESULTADOS = "resultados";


	public boolean bypassAuthentication(String arg0) {
		return true;
	}

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {
    	InfoHelper.setExtraInfo(this);
    	return SUCCESS;
    }

    /**
     * action que busca o resultado
     * @return
     * @throws Exception
     */
    public String resultado() throws Exception {
    	InfoHelper.setExtraInfo(this);
        return SUCCESS;
    }

    /**
     * Voto de um jogo
     * @param opcao
     * @return
     * @throws Exception
     */
    public String jogo() throws Exception {
    	InfoHelper.setExtraInfo(this);
    	int idJogo = input.getInt("id");
    	int idUsuario = input.getInt("idUsuario");
    	int idVoto = input.getInt("idVoto");
    	String comentario = input.getString("comentario");
    	ReviewDeJogoDAO daoReviewDeJogo = ReviewDeJogoDAO.getinstance();
    	ReviewDeJogoVO reviewDeJogoVO = new ReviewDeJogoVO();
    	Jogo jogo = new Jogo();
    	jogo.setId(idJogo);
    	jogo.setNotaGeral(idVoto);
    	reviewDeJogoVO.setJogo(jogo);
    	Colaborador usuario = new Colaborador();
    	usuario.setId(idUsuario);
    	reviewDeJogoVO.setComentario(comentario);
    	reviewDeJogoVO.setNota(idVoto);
    	reviewDeJogoVO.setUsuario(usuario);

    	try {

    		// grava o voto
    		daoReviewDeJogo.adiciona(reviewDeJogoVO);

    		// atualiza cache
    		JogoCache jogoCache = JogoCachePTBR.getInstance();
    		jogoCache.updateNotaJogo(jogo);

    		// coloca o ID do Jogo na request
    		output.setValue("id",idJogo);

      		output.setValue("jaVotou", "S");

		} catch (DAOException e) {
			logger.error("erro ao votar no jogo!",e);
			return ERROR;
		}

    	JogoCache jogoCache = CacheManager.getCacheDeJogos();
    	Jogo storedVO = (Jogo) jogoCache.getCacheComoHashMap().get(new Integer(idJogo));
		output.setValue("id",  String.valueOf(storedVO.getId()));
		output.setValue("nome", storedVO.getNome());
		output.setValue("notaGeral", storedVO.getNotaGeral());

		// resultados
        return SUCCESS;
    }

    /**
     * Voto de um emulador
     * @return
     * @throws Exception
     */
    public String emulador() throws Exception {
    	InfoHelper.setExtraInfo(this);
    	HttpServletRequest req = ( (RequestInput) input).getRequest();
    	String IPdoCliente = 	req.getRemoteAddr();
    	int idEmulador = input.getInt("id");
    	int idUsuario = input.getInt("idUsuario");
    	int idVoto = input.getInt("idVoto");
    	String comentario = input.getString("comentario");

    	ReviewDeEmuladorDAO daoReviewDeEmulador = ReviewDeEmuladorDAO.getinstance();
    	ReviewDeEmuladorVO reviewDeEmuladorVO = new ReviewDeEmuladorVO();
    	EmuladorVO emulador = new EmuladorVO();
    	emulador.setId(idEmulador);
    	emulador.setNotaGeral(idVoto);
    	reviewDeEmuladorVO.setEmulador(emulador);
    	Colaborador usuario = new Colaborador();
    	usuario.setId(idUsuario);
    	reviewDeEmuladorVO.setComentario(comentario);
    	reviewDeEmuladorVO.setNota(idVoto);
    	reviewDeEmuladorVO.setUsuario(usuario);
    	logger.info(" Voto Emulador : IP "+IPdoCliente);
    	try {

    		// grava o voto
    		daoReviewDeEmulador.adiciona(reviewDeEmuladorVO);

    		// atualiza cache
    		EmuladorCache emuladorCache = EmuladorCache.getInstance();
    		emuladorCache.updateNotaEmulador(emulador);

    		// coloca o ID do Jogo na request
    		output.setValue("id",idEmulador);

      		output.setValue("jaVotou", "S");

		} catch (DAOException e) {
			logger.error("erro ao votar no emulador!",e);
			return ERROR;
		}
		// resultados
        return SUCCESS;
    }

    /**
     * Voto de um artigo
     * @param opcao
     * @return
     * @throws Exception
     */
    public String artigo() throws Exception {
    	InfoHelper.setExtraInfo(this);
    	int idArtigo = input.getInt("id");
    	int idUsuario = input.getInt("idUsuario");
    	int idVoto = input.getInt("idVoto");
    	String comentario = input.getString("comentario");
    	ReviewDeArtigoDAO daoReviewDeArtigo = ReviewDeArtigoDAO.getinstance();
    	ReviewDeArtigoVO reviewDeArtigoVO = new ReviewDeArtigoVO();
    	ArtigoVO artigo = new ArtigoVO();
    	artigo.setId(idArtigo);
    	artigo.setNotaGeral(idVoto);
    	reviewDeArtigoVO.setArtigo(artigo);
    	Colaborador usuario = new Colaborador();
    	usuario.setId(idUsuario);
    	reviewDeArtigoVO.setComentario(comentario);
    	reviewDeArtigoVO.setNota(idVoto);
    	reviewDeArtigoVO.setUsuario(usuario);

    	try {

    		// grava o voto
    		daoReviewDeArtigo.adiciona(reviewDeArtigoVO);

    		// atualiza cache
    		ArtigoCache artigoCache = ArtigoCache.getInstance();
    		artigoCache.updateNotaArtigo(artigo);

    		// coloca o ID do Artigo na request
    		output.setValue("id",idArtigo);

      		output.setValue("jaVotou", "S");

		} catch (DAOException e) {
			logger.error("erro ao votar no artigo!",e);
			return ERROR;
		}

    	ArtigoCache artigoCache = CacheManager.getCacheDeArtigos();
    	ArtigoVO storedVO = (ArtigoVO) artigoCache.getCacheComoHashMap().get(new Integer(idArtigo));
		output.setValue("id",  String.valueOf(storedVO.getId()));
		output.setValue("titulo", storedVO.getTitulo());
		output.setValue("texto", storedVO.getTexto());
		output.setValue("notaGeral", storedVO.getNotaGeral());

		// resultados
        return SUCCESS;
    }
    // getters e setters
	public EnqueteVO getVo() {
		return vo;
	}

	public void setVo(EnqueteVO vo) {
		this.vo = vo;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}