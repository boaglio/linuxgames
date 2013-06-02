package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.model.dao.NovidadeAgendadaDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.Novidade;
import br.com.linuxgames.util.Constantes;


public class NovidadeAgendadaAction extends BaseAction implements RedirectAfterLogin {

    NovidadeAgendadaDAO dao = NovidadeAgendadaDAO.getInstance();
	Logger logger = Logger.getLogger(this.getClass());

	private Novidade vo;
	private Collection<Novidade> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	private Collection<Novidade> listaDeNovidades()   {
		NovidadeAgendadaDAO novidadeDAO = NovidadeAgendadaDAO.getInstance();
    	Collection<Novidade> collection=null;
		try {
			collection = novidadeDAO.buscaTodos();
		} catch (DAOException e) {
		  logger.error("erro na busca!",e);
		}
	    return collection;
	}

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {
    	output.setValue("novidades", listaDeNovidades());
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
    		Novidade storedVO = (Novidade) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId()+" texto="+storedVO.getTexto()+" link="+storedVO.getLink());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("texto", storedVO.getTexto());
    		output.setValue("link", storedVO.getLink());

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("novidades", listaDeNovidades());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	// chama o dao e traz o resultado
    	logger.info(" Novidade: texto="+vo.getTexto());

    	try {
    		dao.adiciona(vo);

		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("novidades", listaDeNovidades());
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

    	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar Novidade: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover Novidade: id="+vo.getId());
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		// refresh
		output.setValue("novidades", listaDeNovidades());
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
    	logger.info(" Remover Novidade: id="+vo.getId());
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("novidades", listaDeNovidades());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("id", "");
		output.setValue("texto","");
		output.setValue("link","");
    }

    // getters e setters
	public Novidade getVo() {
		return vo;
	}

	public void setVo(Novidade vo) {
		this.vo = vo;
	}

	public Collection<Novidade> getListaVO() {
		return listaVO;
	}

	public void setListaVO(Collection<Novidade> listaVO) {
		this.listaVO = listaVO;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}