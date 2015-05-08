package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.model.dao.BibliotecaXjogoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.BibliotecaXjogoVO;
import br.com.linuxgames.util.Constantes;


public class BibliotecaXjogoAction extends BaseAction implements RedirectAfterLogin  {

    BibliotecaXjogoDAO dao = BibliotecaXjogoDAO.getinstance();
	Logger logger = Logger.getLogger("br.com.linuxgames.controller.BibliotecaXjogoAction");

	private BibliotecaXjogoVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeBibliotecaXjogos() {
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
    	output.setValue("bibliotecaXjogos", listaDeBibliotecaXjogos());
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
    	vo.getJogo().setId(input.getInt("jogo_id"));

    	try {
    		BibliotecaXjogoVO storedVO = (BibliotecaXjogoVO) dao.buscaUm(vo);
    		// setando os valores lidos
    		output.setValue("biblioteca_id", storedVO.getBiblioteca().getId());
    		output.setValue("jogo_id", storedVO.getJogo().getId());
    		output.setValue("oldBiblioteca", storedVO.getBiblioteca().getId());
    		output.setValue("oldJogo", storedVO.getJogo().getId());

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("bibliotecaXjogos", listaDeBibliotecaXjogos());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	vo.getBiblioteca().setId(input.getInt("biblioteca_id"));
    	vo.getJogo().setId(input.getInt("jogo_id"));

    	// chama o dao e traz o resultado
    	try {
    		dao.adiciona(vo);
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("bibliotecaXjogos", listaDeBibliotecaXjogos());
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
    	vo.getJogo().setId(input.getInt("jogo_id"));
    	vo.setOldBiblioteca(input.getInt("oldBiblioteca"));
    	vo.setOldJogo(input.getInt("oldJogo"));

    	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar BibliotecaXjogo: ");
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover BibliotecaXjogo: id=");
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("bibliotecaXjogos", listaDeBibliotecaXjogos());
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
    	vo.getJogo().setId(input.getInt("jogo_id"));

    	logger.info(" Remover BibliotecaXjogo");
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("bibliotecaXjogos", listaDeBibliotecaXjogos());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("biblioteca_id", "");
		output.setValue("jogo_id","");
    }

    // getters e setters
	public BibliotecaXjogoVO getVo() {
		return vo;
	}

	public void setVo(BibliotecaXjogoVO vo) {
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