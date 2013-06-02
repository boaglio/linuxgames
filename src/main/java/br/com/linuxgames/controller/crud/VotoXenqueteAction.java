package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.model.dao.VotoXenqueteDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.VotoXenqueteVO;
import br.com.linuxgames.util.Constantes;


public class VotoXenqueteAction extends BaseAction implements RedirectAfterLogin {

    VotoXenqueteDAO dao = VotoXenqueteDAO.getInstance();
	Logger logger = Logger.getLogger("br.com.linuxgames.controller.VotoXenqueteAction");

	private VotoXenqueteVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeVotoXenquetes() {
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
    	output.setValue("votoXenquetes", listaDeVotoXenquetes());
    	return SUCCESS;
    }

    /**
     * action que busca um registro
     * @return
     * @throws ActionException
     */
    public String buscaUm() throws ActionException {

    	output.setValue("actionCRUD", String.valueOf(Constantes.ACTION_BUSCA));

    	vo.getEnquete().setId(input.getInt("idEnquete"));

    	try {
    		VotoXenqueteVO storedVO = (VotoXenqueteVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getEnquete().getId()+" IP="+storedVO.getIP());
    		// setando os valores lidos
    		output.setValue("IP", storedVO.getIP());
    		output.setValue("idEnquete", storedVO.getEnquete().getId());
    		output.setValue("titulo", storedVO.getEnquete().getTitulo());
    		output.setValue("oldIP", storedVO.getIP());
    		output.setValue("oldIdEnquete", storedVO.getEnquete().getId());

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("votoXenquetes", listaDeVotoXenquetes());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	vo.getEnquete().setId(input.getInt("idEnquete"));
    	// chama o dao e traz o resultado
    	logger.info(" VotoXenquete");
    	try {
    		dao.adiciona(vo);
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}
		output.setValue("votoXenquetes", listaDeVotoXenquetes());
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
    	vo.getEnquete().setId(input.getInt("idEnquete"));
    	vo.setOldIdEnquete(input.getInt("oldIdEnquete"));

    	String tipoCRUD = input.getString("tipoCRUD");

    	// ajusta o VO
//    	vo.getBiblioteca().setId(input.getInt("biblioteca_id"));
//    	vo.getEmulador().setId(input.getInt("emulador_id"));
//    	vo.setOldBiblioteca(input.getInt("oldBiblioteca"));
//    	vo.setOldEmulador(input.getInt("oldEmulador"));

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar VotoXenquete: id="+vo.getEnquete().getId());
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover VotoXenquete: id="+vo.getEnquete().getId());
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}
		output.setValue("votoXenquetes", listaDeVotoXenquetes());
		resetForm();
        return SUCCESS;
    }


    /**
     * action que remove um registro
     * @return
     * @throws Exception
     */
    public String remove() throws Exception {
     vo.getEnquete().setId( input.getInt("idEnquete"));
    	logger.info(" Remover VotoXenquete: id="+vo.getEnquete().getId());
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("votoXenquetes", listaDeVotoXenquetes());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("IP", "");
		output.setValue("idEnquete","");
    }

    // getters e setters
	public VotoXenqueteVO getVo() {
		return vo;
	}

	public void setVo(VotoXenqueteVO vo) {
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