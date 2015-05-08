package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;
import org.mentawai.list.ListManager;

import br.com.linuxgames.controller.datalist.DBListDataExtended;
import br.com.linuxgames.model.dao.DistroDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.DistroVO;
import br.com.linuxgames.model.vo.FabricanteVO;
import br.com.linuxgames.util.Constantes;


public class DistroAction extends BaseAction implements RedirectAfterLogin {

    DistroDAO dao = DistroDAO.getinstance();
	Logger logger = Logger.getLogger("br.com.linuxgames.controller.DistroAction");

	private DistroVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}


	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeDistros() {
    	Collection<?> collection = null;
    	try {
    		collection = dao.buscaTodos();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
	    return collection;
	}

	private void atualistaCombo() {
    	try {
            DBListDataExtended distros = new DBListDataExtended("DISTROS","distro.buscaTudo", "id", "nome");
            ListManager.addList(distros);
	    } catch (Exception e) {
		  logger.error("erro no cadastro!",e);
	    }
	}

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {
    	output.setValue("distros", listaDeDistros());
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
    		DistroVO storedVO = (DistroVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId()+" Nome="+storedVO.getNome());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("nome", storedVO.getNome());
    		output.setValue("abreviacao", storedVO.getAbreviacao());
    		output.setValue("siteOficial", storedVO.getSiteOficial());
    		output.setValue("linkLogo", storedVO.getLinkLogo());
    		output.setValue("descricao", storedVO.getDescricao());
    		output.setValue("fabricante_id",  String.valueOf(storedVO.getFabricante().getId()));

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("distros", listaDeDistros());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

     	String fabricanteID= input.getString("fabricante_id");
     	FabricanteVO fabricanteVO = new FabricanteVO();
     	fabricanteVO.setId(Integer.parseInt(fabricanteID));
     	vo.setFabricanteVO(fabricanteVO);

    	// chama o dao e traz o resultado
    	logger.info(" Distro: nome="+vo.getNome());
    	try {
    		dao.adiciona(vo);
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("distros", listaDeDistros());
		resetForm();
		atualistaCombo();
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
    	 logger.info(" Atualizar Distro: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover Distro: id="+vo.getId());
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("distros", listaDeDistros());
		resetForm();
		atualistaCombo();
        return SUCCESS;
    }


    /**
     * action que remove um registro
     * @return
     * @throws Exception
     */
    public String remove() throws Exception {
        vo.setId( input.getInt("id"));
    	logger.info(" Remover Distro: id="+vo.getId());
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("distros", listaDeDistros());
    	resetForm();
    	atualistaCombo();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("id", "");
		output.setValue("nome","");
		output.setValue("abreviacao","");
		output.setValue("siteOficial","");
		output.setValue("linkLogo","");
		output.setValue("descricao","");
    }

    // getters e setters
	public DistroVO getVo() {
		return vo;
	}

	public void setVo(DistroVO vo) {
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