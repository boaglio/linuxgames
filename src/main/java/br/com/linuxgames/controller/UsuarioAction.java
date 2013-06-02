package br.com.linuxgames.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.model.dao.UsuariosDAO;
import br.com.linuxgames.model.dao.core.DAOException;


public class UsuarioAction extends BaseAction implements RedirectAfterLogin {

	UsuariosDAO dao = UsuariosDAO.getInstance();
	Logger logger = Logger.getLogger(this.getClass());

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {
    	Collection<?> collection = null;
    	try {
    		collection = dao.buscaTodos();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
    	output.setValue("usuarios", collection);
    	return SUCCESS;
    }

}