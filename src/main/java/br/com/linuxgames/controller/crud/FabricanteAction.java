package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;
import org.mentawai.list.ListManager;

import br.com.linuxgames.controller.datalist.DBListDataExtended;
import br.com.linuxgames.model.dao.FabricanteDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.FabricanteVO;
import br.com.linuxgames.util.Constantes;


public class FabricanteAction extends BaseAction implements RedirectAfterLogin  {

    FabricanteDAO dao = FabricanteDAO.getinstance();
	Logger logger = Logger.getLogger(this.getClass());

	private FabricanteVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeFabricantes() {
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
            DBListDataExtended fabricantes = new DBListDataExtended("FABRICANTES","fabricante.buscaTudo", "id", "nome");
            ListManager.addList(fabricantes);
	    } catch (Exception e) {
		  logger.error("erro no cadastro!",e);
	    }
	}

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {
    	output.setValue("fabricantes", listaDeFabricantes());
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
    		FabricanteVO storedVO = (FabricanteVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId()+" Nome="+storedVO.getNome());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("nome", storedVO.getNome());
    		output.setValue("descricao", storedVO.getDescricao());

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("fabricantes", listaDeFabricantes());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	// chama o dao e traz o resultado
    	logger.info(" Fabricante: nome="+vo.getNome());
    	try {
    		dao.adiciona(vo);
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("fabricantes", listaDeFabricantes());
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

    	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar Fabricante: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover Fabricante: id="+vo.getId());
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("fabricantes", listaDeFabricantes());
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
    	logger.info(" Remover Fabricante: id="+vo.getId());
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("fabricantes", listaDeFabricantes());
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
		output.setValue("descricao","");
    }

    // getters e setters
	public FabricanteVO getVo() {
		return vo;
	}

	public void setVo(FabricanteVO vo) {
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