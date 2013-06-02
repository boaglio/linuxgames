package br.com.linuxgames.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.core.RequestInput;
import org.mentawai.filter.AuthenticationFree;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.model.dao.EnqueteDAO;
import br.com.linuxgames.model.dao.VotoXenqueteDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.EnqueteVO;
import br.com.linuxgames.model.vo.VotoXenqueteVO;


public class EnqueteAction extends BaseAction implements AuthenticationFree {

    EnqueteDAO dao = EnqueteDAO.getInstance();
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
     * action que grava a opcao 1 do voto
     * @return
     * @throws Exception
     */
    public String opt1() throws Exception {
        return optGeral(1);
    }

    public String opt2() throws Exception {
        return optGeral(2);
    }

    public String opt3() throws Exception {
        return optGeral(3);
    }

    public String opt4() throws Exception {
        return optGeral(4);
    }


    /**
     * action que busca o resultado
     * @return
     * @throws Exception
     */
    public String resultado() throws Exception {
    	logger.info(" Resultado da  Enquete");
		// busca dados de uma enquete
		EnqueteDAO daoEnquete = null;
		EnqueteVO storedVO = null;
		try {
			daoEnquete = EnqueteDAO.getInstance();
			storedVO = (EnqueteVO) daoEnquete.buscaEnqueteAtual();
		} catch (Exception e) {
			logger.error("erro na leitura de enquetes:", e);
			return ERROR;
		}
		// setando os valores lidos
		output.setValue("titulo",  storedVO.getTitulo());
		output.setValue("id",  String.valueOf(storedVO.getId()));
		output.setValue("opt1", storedVO.getOpt1());
		output.setValue("qt1", String.valueOf(storedVO.getQt1()));
		output.setValue("opt2", storedVO.getOpt2());
		output.setValue("qt2", String.valueOf(storedVO.getQt2()));
		output.setValue("opt3", storedVO.getOpt3());
		output.setValue("qt3", String.valueOf(storedVO.getQt3()));
		output.setValue("opt4", storedVO.getOpt4());
		output.setValue("qt4", String.valueOf(storedVO.getQt4()));
		output.setValue("qt", String.valueOf(storedVO.getQt()));

        return SUCCESS;
    }

    /**
     * Voto em qualquer opcao
     * @param opcao
     * @return
     * @throws Exception
     */
    private String optGeral(int opcao) throws Exception {

    	HttpServletRequest req = ( (RequestInput) input).getRequest();
    	String IPdoCliente = 	req.getRemoteAddr();
    	int idEnquete = input.getInt("idEnquete");
    	EnqueteDAO daoEnquete = EnqueteDAO.getInstance();
    	logger.info(" Voto Enquete : IP "+IPdoCliente);
    	try {

    		boolean esseIPjaVotou = daoEnquete.esseIPjaVotou(IPdoCliente);

    		if (esseIPjaVotou)
    		 return JA_VOTOU;

    		// grava o voto
    		dao.opt(opcao);

    		// grava o IP do Voto
    		VotoXenqueteDAO votoXenqueteDAO = VotoXenqueteDAO.getInstance();
    		VotoXenqueteVO votoXenqueteVO = new VotoXenqueteVO();
    		votoXenqueteVO.setIP(IPdoCliente);
    		EnqueteVO enqueteVO = new EnqueteVO();
    		enqueteVO.setId(idEnquete);
    		votoXenqueteVO.setEnquete(enqueteVO);
    		votoXenqueteDAO.adiciona(votoXenqueteVO);

    		// refresh
    		CacheManager.getCacheDeNovidades().refreshNoCache();

		} catch (DAOException e) {
			logger.error("erro ao votar no "+opcao+"!",e);
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