package br.com.linuxgames.controller;

import org.mentawai.action.BaseLoginAction;

public class LoginAction extends BaseLoginAction {

	private static final String GRUPO_EDITORES = "editores";
	private static final String GRUPO_COLABORADORES = "colaboradores";
	private static final String GRUPO_ADMINS = "admins";

	private String ADMIN_LOGIN="fb";
	private String ADMIN_PASSWD="****";

	private String COLABORADOR_LOGIN="colaborador";
	private String COLABORADOR_PASSWD="****";

	private String EDITOR_LOGIN="editor";
	private String EDITOR_PASSWD="***";


	public boolean bypassAuthentication(String arg0) {
		return true;
	}


    public String execute() throws Exception {
            String user = input.getString("usuario");
            String pass = input.getString("senha");

            if (user == null || user.trim().equals("")) {
                    return ERROR;
            }

            if (pass == null || pass.trim().equals("")) {
                    return ERROR;
            }

            // admin
            if (user.equals(ADMIN_LOGIN) && pass.equals(ADMIN_PASSWD)) {
            	setUserSession(user);
            	setUserGroup(GRUPO_ADMINS);
            	return SUCCESS;
            }

            // colaborador
            if (user.equals(COLABORADOR_LOGIN) && pass.equals(COLABORADOR_PASSWD)) {
            	setUserSession(user);
            	setUserGroup(GRUPO_COLABORADORES);
            	return SUCCESS;
            }

            // editor
            if (user.equals(EDITOR_LOGIN) && pass.equals(EDITOR_PASSWD)) {
            	setUserSession(user);
            	setUserGroup(GRUPO_EDITORES);
            	return SUCCESS;
            }

            return ERROR;

    }

}
