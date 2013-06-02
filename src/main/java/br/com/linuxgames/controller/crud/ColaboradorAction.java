package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.model.dao.ColaboradoresDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.Colaborador;
import br.com.linuxgames.util.Constantes;


public class ColaboradorAction extends BaseAction implements RedirectAfterLogin {

	ColaboradoresDAO dao = ColaboradoresDAO.getinstance();
	Logger logger = Logger.getLogger(this.getClass());

	private Colaborador vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeColaboradores() {
    	Collection<?> collection = null;
    	try {
    		collection = dao.buscaTodos();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
	    return collection;
	}

	private Collection<?> listaDeColaboradoresAtivos() {
    	Collection<?> collection = null;
    	try {
    		collection = dao.buscaColaboradores();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
	    return collection;
	}

	private Collection<?> listaDeAdminAtivos() {
    	Collection<?> collection = null;
    	try {
    		collection = dao.buscaAdmins();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
	    return collection;
	}


	/**
	 * action inicial
	 */
    public String execute() throws ActionException {
    	output.setValue("colaboradores", listaDeColaboradores());
    	output.setValue("colabAtivos", listaDeColaboradoresAtivos());
    	output.setValue("admins", listaDeAdminAtivos());
    	return SUCCESS;
    }

    /**
     * action que busca um registro
     * @return
     * @throws ActionException
     */
    public String buscaUm() throws ActionException {

    	output.setValue("actionCRUD", String.valueOf(Constantes.ACTION_BUSCA));
    	vo.setId(input.getInt("id"));

    	try {
    		Colaborador storedVO = (Colaborador) dao.buscaUm(vo);
    		// setando os valores lidos
    		output.setValue("id", storedVO.getId());
    		output.setValue("nome", storedVO.getEmail());
    		output.setValue("email", storedVO.getSenha());
    		output.setValue("ativo", storedVO.getAtivo());
    		output.setValue("admin", storedVO.getAdmin());
	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("colaboradores", listaDeColaboradores());
    	output.setValue("colabAtivos", listaDeColaboradoresAtivos());
    	output.setValue("admins", listaDeAdminAtivos());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	vo.setId(input.getInt("id"));
    	vo.setEmail(input.getString("nome"));
    	vo.setSenha(input.getString("email"));
    	vo.setAtivo(input.getInt("ativo"));
    	vo.setAdmin(input.getInt("admin"));

    	// chama o dao e traz o resultado
    	try {
    		dao.adiciona(vo);
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}
		output.setValue("colaboradores", listaDeColaboradores());
    	output.setValue("colabAtivos", listaDeColaboradoresAtivos());
    	output.setValue("admins", listaDeAdminAtivos());
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
    	vo.setIdOld(input.getInt("id_old"));
    	vo.setId(input.getInt("id"));
    	vo.setEmail(input.getString("nome"));
    	vo.setSenha(input.getString("email"));
    	vo.setAtivo(input.getInt("ativo"));
    	vo.setAdmin(input.getInt("admin"));

    	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar roteiro: ");
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover roteiro: id=");
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("colaboradores", listaDeColaboradores());
    	output.setValue("colabAtivos", listaDeColaboradoresAtivos());
    	output.setValue("admins", listaDeAdminAtivos());
		resetForm();
        return SUCCESS;
    }


    /**
     * action que remove um registro
     * @return
     * @throws Exception
     */
    public String remove() throws Exception {

    	vo.setId(input.getInt("id"));

    	logger.info(" Remover colaborador");
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("colaboradores", listaDeColaboradores());
    	output.setValue("colabAtivos", listaDeColaboradoresAtivos());
    	output.setValue("admins", listaDeAdminAtivos());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("id","");
		output.setValue("ativo","");
		output.setValue("id","");
		output.setValue("admin","");
		output.setValue("nome","");
    }

    // getters e setters
	public Colaborador getVo() {
		return vo;
	}

	public void setVo(Colaborador vo) {
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