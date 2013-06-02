package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.model.dao.BibliotecaXemuladorDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.BibliotecaXemuladorVO;
import br.com.linuxgames.util.Constantes;


public class BibliotecaXemuladorAction extends BaseAction implements RedirectAfterLogin  {

    BibliotecaXemuladorDAO dao = BibliotecaXemuladorDAO.getinstance();
	Logger logger = Logger.getLogger("br.com.linuxgames.controller.BibliotecaXemuladorAction");

	private BibliotecaXemuladorVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeBibliotecaXemuladors() {
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
    	output.setValue("bibliotecaXemuladors", listaDeBibliotecaXemuladors());
    	return SUCCESS;
    }

    /**
     * action que busca um registro
     * @return
     * @throws ActionException
     */
    public String buscaUm() throws ActionException {

    	output.setValue("actionCRUD", String.valueOf(Constantes.ACTION_BUSCA));

    	vo.getBiblioteca().setId(input.getInt("biblioteca_id"));
    	vo.getEmulador().setId(input.getInt("emulador_id"));

    	try {
    		BibliotecaXemuladorVO storedVO = (BibliotecaXemuladorVO) dao.buscaUm(vo);
    		// setando os valores lidos
    		output.setValue("biblioteca_id", storedVO.getBiblioteca().getId());
    		output.setValue("emulador_id", storedVO.getEmulador().getId());
    		output.setValue("oldBiblioteca", storedVO.getBiblioteca().getId());
    		output.setValue("oldEmulador", storedVO.getEmulador().getId());

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("bibliotecaXemuladors", listaDeBibliotecaXemuladors());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	vo.getBiblioteca().setId(input.getInt("biblioteca_id"));
    	vo.getEmulador().setId(input.getInt("emulador_id"));

    	// chama o dao e traz o resultado
    	try {
    		dao.adiciona(vo);
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("bibliotecaXemuladors", listaDeBibliotecaXemuladors());
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
    	vo.getBiblioteca().setId(input.getInt("biblioteca_id"));
    	vo.getEmulador().setId(input.getInt("emulador_id"));
    	vo.setOldBiblioteca(input.getInt("oldBiblioteca"));
    	vo.setOldEmulador(input.getInt("oldEmulador"));

    	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar BibliotecaXemulador: ");
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover BibliotecaXemulador: id=");
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("bibliotecaXemuladors", listaDeBibliotecaXemuladors());
		resetForm();
        return SUCCESS;
    }


    /**
     * action que remove um registro
     * @return
     * @throws Exception
     */
    public String remove() throws Exception {

    	vo.getBiblioteca().setId(input.getInt("biblioteca_id"));
    	vo.getEmulador().setId(input.getInt("emulador_id"));

    	logger.info(" Remover BibliotecaXemulador");
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("bibliotecaXemuladors", listaDeBibliotecaXemuladors());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("biblioteca_id", "");
		output.setValue("emulador_id","");
    }

    // getters e setters
	public BibliotecaXemuladorVO getVo() {
		return vo;
	}

	public void setVo(BibliotecaXemuladorVO vo) {
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