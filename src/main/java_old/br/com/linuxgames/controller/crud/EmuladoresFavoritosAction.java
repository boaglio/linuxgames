package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.model.dao.EmuladoresFavoritosDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.EmuladoresFavoritosVO;
import br.com.linuxgames.util.Constantes;


public class EmuladoresFavoritosAction extends BaseAction implements RedirectAfterLogin  {

    EmuladoresFavoritosDAO dao = EmuladoresFavoritosDAO.getinstance();
	Logger logger = Logger.getLogger(EmuladoresFavoritosAction.class);

	private EmuladoresFavoritosVO vo;
	private Collection<EmuladoresFavoritosVO> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<EmuladoresFavoritosVO> listaDeEmuladoresFavoritos() {
    	Collection<EmuladoresFavoritosVO> collection = null;
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
    	output.setValue("emuladoresFavoritos", listaDeEmuladoresFavoritos());
    	return SUCCESS;
    }

    /**
     * action que busca um registro
     * @return
     * @throws ActionException
     */
    public String buscaUm() throws ActionException {

    	output.setValue("actionCRUD", String.valueOf(Constantes.ACTION_BUSCA));

    	vo.getUsuario().setId(input.getInt("usuario_id"));
    	vo.getEmulador().setId(input.getInt("emu_id"));

    	try {
    		EmuladoresFavoritosVO storedVO = (EmuladoresFavoritosVO) dao.buscaUm(vo);
    		// setando os valores lidos
    		output.setValue("usuario_id", storedVO.getUsuario().getId());
    		output.setValue("emu_id", storedVO.getEmulador().getId());
    		output.setValue("oldUsuario", storedVO.getUsuario().getId());
    		output.setValue("oldEmulador", storedVO.getEmulador().getId());

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("emuladoresFavoritos", listaDeEmuladoresFavoritos());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	vo.getUsuario().setId(input.getInt("usuario_id"));
    	vo.getEmulador().setId(input.getInt("emu_id"));

    	// chama o dao e traz o resultado
    	try {
    		dao.adiciona(vo);
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("emuladoresFavoritos", listaDeEmuladoresFavoritos());
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
    	vo.getUsuario().setId(input.getInt("usuario_id"));
    	vo.getEmulador().setId(input.getInt("emu_id"));
    	vo.setOldUsuario(input.getInt("oldUsuario"));
    	vo.setOldEmulador(input.getInt("oldEmulador"));

    	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar EmuladoresFavoritos: ");
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover EmuladoresFavoritos: id=");
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("emuladoresFavoritos", listaDeEmuladoresFavoritos());
		resetForm();
        return SUCCESS;
    }


    /**
     * action que remove um registro
     * @return
     * @throws Exception
     */
    public String remove() throws Exception {

    	vo.getUsuario().setId(input.getInt("usuario_id"));
    	vo.getEmulador().setId(input.getInt("emu_id"));

    	logger.info(" Remover EmuladoresFavoritos");
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("emuladoresFavoritos", listaDeEmuladoresFavoritos());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("usuario_id", "");
		output.setValue("emu_id","");
    }

    // getters e setters
	public EmuladoresFavoritosVO getVo() {
		return vo;
	}

	public void setVo(EmuladoresFavoritosVO vo) {
		this.vo = vo;
	}

	public Collection<EmuladoresFavoritosVO> getListaVO() {
		return listaVO;
	}

	public void setListaVO(Collection<EmuladoresFavoritosVO> listaVO) {
		this.listaVO = listaVO;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}