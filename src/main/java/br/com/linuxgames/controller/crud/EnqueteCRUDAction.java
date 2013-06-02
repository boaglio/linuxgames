package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.model.dao.EnqueteDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.EnqueteVO;
import br.com.linuxgames.util.Constantes;


public class EnqueteCRUDAction extends BaseAction implements RedirectAfterLogin {

    EnqueteDAO dao = EnqueteDAO.getInstance();
	Logger logger = Logger.getLogger(this.getClass());

	private EnqueteVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeEnquetes() {
    	Collection<?> collection = null;
    	try {
    		collection = dao.buscaTodos();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
	    return collection;
	}

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {
    	output.setValue("qt1", "0");
		output.setValue("qt2", "0");
		output.setValue("qt3", "0");
		output.setValue("qt4", "0");
    	output.setValue("enquetes", listaDeEnquetes());
    	return SUCCESS;
    }

    /**
     * action que busca um registro
     * @return
     * @throws ActionException
     */
    public String buscaUm() throws ActionException {

    	output.setValue("actionCRUD", String.valueOf(Constantes.ACTION_BUSCA));
		output.setValue("opt1", "");
		output.setValue("qt1", "0");
		output.setValue("opt2", "");
		output.setValue("qt2", "0");
		output.setValue("opt3", "");
		output.setValue("qt3", "0");
		output.setValue("opt4", "");
		output.setValue("qt4", "0");
		output.setValue("qt", "");
    	vo.setId( input.getInt("id"));
		output.setValue("opt1", "");
		output.setValue("qt1", "0");
		output.setValue("opt2", "");
		output.setValue("qt2", "0");
		output.setValue("opt3", "");
		output.setValue("qt3", "0");
		output.setValue("opt4", "");
		output.setValue("qt4", "0");
		output.setValue("qt", "");
    	try {
    		EnqueteVO storedVO = (EnqueteVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId()+" titulo="+storedVO.getTitulo());
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
    		if (storedVO.isAtivo())
    		 output.setValue("ativo",  String.valueOf(1));
    		else
    		 output.setValue("ativo",  String.valueOf(0));

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("enquetes", listaDeEnquetes());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	// ajusta o VO
		vo.setQt1(input.getInt("qt1"));
		vo.setQt2(input.getInt("qt2"));
		vo.setQt3(input.getInt("qt3"));
		vo.setQt4(input.getInt("qt4"));
		int ativo = input.getInt("ativo");
		if (ativo==1)
			vo.setAtivo(true);

    	// chama o dao e traz o resultado
    	logger.info(" Enquete: titulo="+vo.getTitulo());
    	try {
    		dao.adiciona(vo);
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("enquetes", listaDeEnquetes());
		resetForm();

		// refresh
		CacheManager.getCacheDeNovidades().refreshNoCache();

		return SUCCESS;
    }


	/**
	 *action que atualiza um registro
	 * @return
	 * @throws Exception
	 */
    public String atualiza() throws Exception {

    	// ajusta o VO
    	vo.setId( input.getInt("id"));
    	String tipoCRUD = input.getString("tipoCRUD");
		vo.setQt1(input.getInt("qt1"));
		vo.setQt2(input.getInt("qt2"));
		vo.setQt3(input.getInt("qt3"));
		vo.setQt4(input.getInt("qt4"));
		vo.setQt(input.getInt("qt"));
		int ativo = input.getInt("ativo");
		if (ativo==1)
			vo.setAtivo(true);

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar Enquete: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover Enquete: id="+vo.getId());
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("enquetes", listaDeEnquetes());

		// refresh
		CacheManager.getCacheDeNovidades().refreshNoCache();

		resetForm();
        return SUCCESS;
    }


    /**
     * action que remove um registro
     * @return
     * @throws Exception
     */
    public String remove() throws Exception {
        vo.setId( input.getInt("id"));
    	logger.info(" Remover Enquete: id="+vo.getId());
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("enquetes", listaDeEnquetes());

		// refresh
		CacheManager.getCacheDeNovidades().refreshNoCache();

    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("id","");
		output.setValue("opt1", "");
		output.setValue("qt1", "0");
		output.setValue("opt2", "");
		output.setValue("qt2", "0");
		output.setValue("opt3", "");
		output.setValue("qt3", "0");
		output.setValue("opt4", "");
		output.setValue("qt4", "0");
		output.setValue("qt", "");
    }

    // getters e setters
	public EnqueteVO getVo() {
		return vo;
	}

	public void setVo(EnqueteVO vo) {
		this.vo = vo;
	}

	public Collection<?> getListaVO() {
		return listaVO;
	}

	public void setListaVO(Collection<?> listaVO) {
		this.listaVO = listaVO;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}