package br.com.linuxgames.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.core.RequestInput;
import org.mentawai.filter.AuthenticationFree;

import br.com.linuxgames.controller.cache.EmuladorCache;
import br.com.linuxgames.controller.cache.JogoCache;
import br.com.linuxgames.controller.cache.JogoCachePTBR;
import br.com.linuxgames.model.dao.EmuladorDAO;
import br.com.linuxgames.model.dao.JogoDAO;
import br.com.linuxgames.model.dao.VotoXjogoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.EmuladorVO;
import br.com.linuxgames.model.vo.EnqueteVO;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.model.vo.VotoXjogoVO;


public class VotoAction extends BaseAction implements AuthenticationFree {

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
    	return SUCCESS;
    }

    /**
     * action que busca o resultado
     * @return
     * @throws Exception
     */
    public String resultado() throws Exception {
        return SUCCESS;
    }

    /**
     * Voto de um jogo
     * @param opcao
     * @return
     * @throws Exception
     */
    public String jogo() throws Exception {

    	HttpServletRequest req = ( (RequestInput) input).getRequest();
    	String IPdoCliente = 	req.getRemoteAddr();
    	int idJogo = input.getInt("idJogo");
    	int idVoto = input.getInt("idVoto");
    	JogoDAO daoJogo = JogoDAO.getInstance();
    	Jogo jogo = new Jogo();
    	jogo.setId(idJogo);
    	jogo.setNotaGeral(idVoto);
    	logger.info(" Voto Jogo : IP "+IPdoCliente);
    	try {

    		boolean esseIPjaVotou = daoJogo.esseIPjaVotou(IPdoCliente,idJogo);
    		if (esseIPjaVotou)
    		 output.setValue("jaVotou", "S");
    		else
    		 output.setValue("jaVotou", "N");

    		// grava o voto
    		daoJogo.atualizaVoto(jogo);

    		// grava o IP do Voto
    		VotoXjogoDAO votoXenqueteDAO = VotoXjogoDAO.getInstance();
    		VotoXjogoVO votoXjogoVO = new VotoXjogoVO();
    		votoXjogoVO.setIP(IPdoCliente);
    		votoXjogoVO.setIdJogo(idJogo);
//    		votoXjogoVO.setIdUsuario(idUsuario);
    		votoXenqueteDAO.adiciona(votoXjogoVO);

    		// atualiza cache
    		JogoCache jogoCache = JogoCachePTBR.getInstance();
    		jogoCache.updateNotaJogo(jogo);

    		// coloca o ID do Jogo na request
    		 output.setValue("id",idJogo);

		} catch (DAOException e) {
			logger.error("erro ao votar no jogo!",e);
			return ERROR;
		}
		// resultados
        return SUCCESS;
    }

    /**
     * Voto de um emulador
     * @return
     * @throws Exception
     */
    public String emulador() throws Exception {

    	HttpServletRequest req = ( (RequestInput) input).getRequest();
    	String IPdoCliente = 	req.getRemoteAddr();
    	int idEmulador = input.getInt("idJogo");
    	int idVoto = input.getInt("idVoto");
    	EmuladorDAO daoEmulador = EmuladorDAO.getInstance();
    	EmuladorVO emulador = new EmuladorVO();
    	emulador.setId(idEmulador);
    	emulador.setNotaGeral(idVoto);
    	logger.info(" Voto Jogo : IP "+IPdoCliente);
    	try {

    		boolean esseIPjaVotou = daoEmulador.esseIPjaVotou(IPdoCliente,idEmulador);
    		if (esseIPjaVotou)
    		 output.setValue("jaVotou", "S");
    		else
    		 output.setValue("jaVotou", "N");

    		// grava o voto
    		daoEmulador.atualizaVoto(emulador);

    		// grava o IP do Voto
    		VotoXjogoDAO votoXenqueteDAO = VotoXjogoDAO.getInstance();
    		VotoXjogoVO votoXjogoVO = new VotoXjogoVO();
    		votoXjogoVO.setIP(IPdoCliente);
    		Jogo jogoVO = new Jogo();
    		jogoVO.setId(idEmulador);
//    		votoXjogoVO.setJogo(jogoVO);
    		votoXenqueteDAO.adiciona(votoXjogoVO);

    		// atualiza cache
    		EmuladorCache emuladorCache = EmuladorCache.getInstance();
    		emuladorCache.updateNotaEmulador(emulador);

    		// coloca o ID do Jogo na request
    		 output.setValue("id",idEmulador);

		} catch (DAOException e) {
			logger.error("erro ao votar no jogo!",e);
			return ERROR;
		}
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