package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.model.dao.VotoXemuladorDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.VotoXemuladorVO;
import br.com.linuxgames.util.Constantes;


public class VotoXemuladorAction extends BaseAction implements RedirectAfterLogin {

    VotoXemuladorDAO dao = VotoXemuladorDAO.getInstance();
	Logger logger = Logger.getLogger(this.getClass());

	private VotoXemuladorVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeVotoXemuladors() {
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
    	output.setValue("votoXemuladors", listaDeVotoXemuladors());
    	return SUCCESS;
    }

    /**
     * action que busca um registro
     * @return
     * @throws ActionException
     */
    public String buscaUm() throws ActionException {

    	output.setValue("actionCRUD", String.valueOf(Constantes.ACTION_BUSCA));

    	vo.getEmulador().setId(input.getInt("idEmulador"));

    	try {
    		VotoXemuladorVO storedVO = (VotoXemuladorVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getEmulador().getId()+" IP="+storedVO.getIP());
    		// setando os valores lidos
    		output.setValue("IP", storedVO.getIP());
    		output.setValue("idEmulador", storedVO.getEmulador().getId());
    		output.setValue("nome", storedVO.getEmulador().getNome());
    		output.setValue("oldIP", storedVO.getIP());
    		output.setValue("oldIdEmulador", storedVO.getEmulador().getId());

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("votoXemuladors", listaDeVotoXemuladors());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	vo.getEmulador().setId(input.getInt("idEmulador"));
    	// chama o dao e traz o resultado
    	logger.info(" VotoXemulador");
    	try {
    		dao.adiciona(vo);
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}
		output.setValue("votoXemuladors", listaDeVotoXemuladors());
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
    	vo.getEmulador().setId(input.getInt("idEmulador"));
    	vo.setOldIdEmulador(input.getInt("oldIdEmulador"));

    	String tipoCRUD = input.getString("tipoCRUD");

    	// ajusta o VO
    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar VotoXemulador: id="+vo.getEmulador().getId());
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover VotoXemulador: id="+vo.getEmulador().getId());
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}
		output.setValue("votoXemuladors", listaDeVotoXemuladors());
		resetForm();
        return SUCCESS;
    }


    /**
     * action que remove um registro
     * @return
     * @throws Exception
     */
    public String remove() throws Exception {
     vo.getEmulador().setId( input.getInt("idEmulador"));
    	logger.info(" Remover VotoXemulador: id="+vo.getEmulador().getId());
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("votoXemuladors", listaDeVotoXemuladors());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("IP", "");
		output.setValue("idEmulador","");
    }

    // getters e setters
	public VotoXemuladorVO getVo() {
		return vo;
	}

	public void setVo(VotoXemuladorVO vo) {
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