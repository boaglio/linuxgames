package br.com.linuxgames.controller.crud;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mentawai.converter.Converter;
import org.mentawai.converter.DateConverter;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.model.dao.UpdateLogDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.UpdateLogVO;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.DateHelper;


public class UpdateLogAction extends BaseAction implements RedirectAfterLogin {

    UpdateLogDAO dao = UpdateLogDAO.getInstance();
	Logger logger = Logger.getLogger(this.getClass());

	private UpdateLogVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeUpdateLogs() {
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
    	output.setValue("updatelogs", listaDeUpdateLogs());
    	return SUCCESS;
    }

    /**
     * action que busca um registro
     * @return
     * @throws ActionException
     */
    public String buscaUm() throws ActionException {

    	output.setValue("actionCRUD", String.valueOf(Constantes.ACTION_BUSCA));

    	vo.setId( input.getInt("id"));

    	try {
    		UpdateLogVO storedVO = (UpdateLogVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId()+" texto="+storedVO.getDescricao());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("descricao", storedVO.getDescricao());
    		output.setValue("data", DateHelper.mysqlFormat2EuropeanFormat(storedVO.getData().toString()));

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("updatelogs", listaDeUpdateLogs());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	// chama o dao e traz o resultado
    	logger.info(" UpdateLog: "+vo.getDescricao());

    	String dateStr = input.getString("data");
    	Date data = (Date) DateHelper.formatStrDate(dateStr);
     	vo.setData(new java.sql.Date( data.getTime()));

    	try {
    		dao.adiciona(vo);
    		CacheManager.getCacheDeUpdates().refreshNoCache();
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("updatelogs", listaDeUpdateLogs());
		resetForm();
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

    	String dateStr = input.getString("data");
    	Date data = (Date) DateHelper.formatStrDate(dateStr);
     	vo.setData(new java.sql.Date( data.getTime()));

    	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar UpdateLog: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
    		CacheManager.getCacheDeUpdates().refreshNoCache();
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover UpdateLog: id="+vo.getId());
    	 try {
    		dao.remove(vo);
    		CacheManager.getCacheDeUpdates().refreshNoCache();
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("updatelogs", listaDeUpdateLogs());
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
    	logger.info(" Remover UpdateLog: id="+vo.getId());
    	try {
    		dao.remove(vo);
    		CacheManager.getCacheDeUpdates().refreshNoCache();
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}

		output.setValue("updatelogs", listaDeUpdateLogs());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("id", "");
		output.setValue("descricao","");
		output.setValue("data","");
    }

    // getters e setters
	public UpdateLogVO getVo() {
		return vo;
	}

	public void setVo(UpdateLogVO vo) {
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


	public void prepareConverters(Map<String, Converter> converters, String innerAction) {

	 converters.put("data", new DateConverter());

	}


}