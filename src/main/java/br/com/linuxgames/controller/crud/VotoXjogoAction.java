package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.model.dao.VotoXjogoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.VotoXjogoVO;
import br.com.linuxgames.util.Constantes;


public class VotoXjogoAction extends BaseAction implements RedirectAfterLogin {

    VotoXjogoDAO dao = VotoXjogoDAO.getInstance();
	Logger logger = Logger.getLogger(this.getClass());

	private VotoXjogoVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeVotoXjogos() {
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
    	output.setValue("votoXjogos", listaDeVotoXjogos());
    	return SUCCESS;
    }

    /**
     * action que busca um registro
     * @return
     * @throws ActionException
     */
    public String buscaUm() throws ActionException {

    	output.setValue("actionCRUD", String.valueOf(Constantes.ACTION_BUSCA));

    	vo.setIdJogo(input.getInt("idJogo"));
    	vo.setIdUsuario(input.getInt("idUsuario"));

    	try {
    		VotoXjogoVO storedVO = (VotoXjogoVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getIdJogo()+" IP="+storedVO.getIP());
    		// setando os valores lidos
    		output.setValue("IP", storedVO.getIP());
    		output.setValue("idJogo", storedVO.getIdJogo());
    		output.setValue("nome", storedVO.getNomeJogo());
    		output.setValue("oldIdUsuario", storedVO.getIdUsuario());
    		output.setValue("oldIdJogo", storedVO.getIdJogo());

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("votoXjogos", listaDeVotoXjogos());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	vo.setIdJogo(input.getInt("idJogo"));
    	vo.setIdUsuario(input.getInt("idUsuario"));

    	// chama o dao e traz o resultado
    	logger.info(" VotoXjogo");
    	try {
    		dao.adiciona(vo);
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}
		output.setValue("votoXjogos", listaDeVotoXjogos());
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
    	vo.setIdJogo(input.getInt("idJogo"));
    	vo.setIdUsuario(input.getInt("idUsuario"));
    	vo.setOldIdJogo(input.getInt("oldIdJogo"));
    	vo.setOldIdUsuario(input.getInt("oldIdUsuario"));

    	String tipoCRUD = input.getString("tipoCRUD");

    	// ajusta o VO
    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar VotoXjogo: id="+vo.getIdJogo());
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover VotoXjogo: id="+vo.getIdJogo());
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}
		output.setValue("votoXjogos", listaDeVotoXjogos());
		resetForm();
        return SUCCESS;
    }


    /**
     * action que remove um registro
     * @return
     * @throws Exception
     */
    public String remove() throws Exception {

     vo.setIdJogo( input.getInt("idJogo"));
     vo.setIdUsuario(input.getInt("idUsuario"));

    	logger.info(" Remover VotoXjogo: id="+vo.getIdJogo());
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("votoXjogos", listaDeVotoXjogos());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("IP", "");
		output.setValue("idJogo","");
		output.setValue("idUsuario","");
    }

    // getters e setters
	public VotoXjogoVO getVo() {
		return vo;
	}

	public void setVo(VotoXjogoVO vo) {
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