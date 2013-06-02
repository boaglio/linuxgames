package br.com.linuxgames.controller.crud;

import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mentawai.converter.Converter;
import org.mentawai.converter.DateConverter;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.model.dao.InfoDoDiaDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.EmuladorVO;
import br.com.linuxgames.model.vo.InfoDoDiaVO;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.DateHelper;


public class InfoDoDiaAction extends BaseAction implements RedirectAfterLogin {

    InfoDoDiaDAO dao = InfoDoDiaDAO.getInstance();
	Logger logger = Logger.getLogger("br.com.linuxgames.controller.InfoDoDiaAction");

	private InfoDoDiaVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeInfoDoDias() {
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
    	output.setValue("infosDoDia", listaDeInfoDoDias());
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
    		InfoDoDiaVO storedVO = (InfoDoDiaVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("data",	DateHelper.mysqlFormat2EuropeanFormat(storedVO.getData().toString()));
    		output.setValue("jogo_id",  String.valueOf(storedVO.getJogoVO().getId()));
    		output.setValue("emu_id",  String.valueOf(storedVO.getJogoVO().getId()));

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("infosDoDia", listaDeInfoDoDias());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	// chama o dao e traz o resultado
    	logger.info(" InfoDoDia: texto="+vo.getTexto());

     	String jogoID= input.getString("jogo_id");
     	Jogo jogoVO = new Jogo();
     	jogoVO.setId(Integer.parseInt(jogoID));
     	vo.setJogoVO(jogoVO);
     	String emuID=input.getString("emu_id");
     	EmuladorVO emuVO = new EmuladorVO();
     	emuVO.setId(Integer.parseInt(emuID));
     	vo.setEmuladorVO(emuVO);

    	try {
    		dao.adiciona(vo);
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("infosDoDia", listaDeInfoDoDias());
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
     	String jogoID= input.getString("jogo_id");
     	Jogo jogoVO = new Jogo();
     	jogoVO.setId(Integer.parseInt(jogoID));
     	vo.setJogoVO(jogoVO);
     	String emuID=input.getString("emu_id");
     	EmuladorVO emuVO = new EmuladorVO();
     	emuVO.setId(Integer.parseInt(emuID));
     	vo.setEmuladorVO(emuVO);

    	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar InfoDoDia: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover InfoDoDia: id="+vo.getId());
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("infosDoDia", listaDeInfoDoDias());
		resetForm();

		// refresh
		CacheManager.getCacheDeNovidades().refreshNoCache();

        return SUCCESS;
    }


    /**
     * action que remove um registro
     * @return
     * @throws Exception
     */
    public String remove() throws Exception {
        vo.setId( input.getInt("id"));
    	logger.info(" Remover InfoDoDia: id="+vo.getId());
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("infosDoDia", listaDeInfoDoDias());
    	resetForm();

		// refresh
		CacheManager.getCacheDeNovidades().refreshNoCache();

        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("id", "");
		output.setValue("data","");
		output.setValue("jogo_id","");
		output.setValue("tipo_id","");
    }

    // getters e setters
	public InfoDoDiaVO getVo() {
		return vo;
	}

	public void setVo(InfoDoDiaVO vo) {
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