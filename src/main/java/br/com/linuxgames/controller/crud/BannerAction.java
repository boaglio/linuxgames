package br.com.linuxgames.controller.crud;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mentawai.converter.Convertable;
import org.mentawai.converter.Converter;
import org.mentawai.converter.DateConverter;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.model.dao.BannerDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.BannerVO;
import br.com.linuxgames.model.vo.FabricanteVO;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.DateHelper;


public class BannerAction extends BaseAction implements RedirectAfterLogin ,Convertable {

    BannerDAO dao = BannerDAO.getInstance();
	Logger logger = Logger.getLogger(this.getClass().getName());

	private BannerVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeBanners() {
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
    	output.setValue("banners", listaDeBanners());
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
    		BannerVO storedVO = (BannerVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId()+" imagem="+storedVO.getImagem());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("imagem", storedVO.getImagem());
    		output.setValue("dtInicio", DateHelper.mysqlFormat2EuropeanFormat(storedVO.getDtInicio().toString()));
    	    Date dtFim = storedVO.getDtFim();
    	    if (dtFim!=null)
    		 output.setValue("dtFim", DateHelper.mysqlFormat2EuropeanFormat(dtFim.toString()));
    		output.setValue("fabricante_id",  String.valueOf(storedVO.getFabricanteVO().getId()));

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("banners", listaDeBanners());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	// chama o dao e traz o resultado
    	logger.info(" Banner: imagem="+vo.getImagem());
     	String fabricanteID= input.getString("fabricante_id");
     	FabricanteVO fabricanteVO = new FabricanteVO();
     	fabricanteVO.setId(Integer.parseInt(fabricanteID));
     	vo.setFabricanteVO(fabricanteVO);

    	try {
    		dao.adiciona(vo);
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("banners", listaDeBanners());
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
     	String fabricanteID= input.getString("fabricante_id");
     	FabricanteVO fabricanteVO = new FabricanteVO();
     	fabricanteVO.setId(Integer.parseInt(fabricanteID));
     	vo.setFabricanteVO(fabricanteVO);

    	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar Banner: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover Banner: id="+vo.getId());
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("banners", listaDeBanners());
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
    	logger.info(" Remover Banner: id="+vo.getId());
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("banners", listaDeBanners());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("id", "");
		output.setValue("imagem","");
		output.setValue("dtInicio","");
		output.setValue("dtFim","");
		output.setValue("fabricante_id","");
    }

    // getters e setters
	public BannerVO getVo() {
		return vo;
	}

	public void setVo(BannerVO vo) {
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

		 converters.put("dtInicio", new DateConverter());
		 converters.put("dtFim", new DateConverter());
    }

}