package br.com.linuxgames.util;

import org.apache.log4j.Logger;
import org.mentawai.core.Action;

import br.com.linuxgames.model.type.SessionVars;
import br.com.linuxgames.model.vo.Usuario;
import br.com.linuxgames.model.vo.UsuarioSession;

public class UserHelper {

	static Logger logger = Logger.getLogger(UserHelper.class);

	public static void setUserOnSession(Usuario usu, Action action) {
		action.getSession().setAttribute(SessionVars.usuarioLG.description(),
				new UsuarioSession(usu.getId(),usu.getEmail()));
	}


	public static void removeUserFromSession(Action action) {

		action.getSession().removeAttribute(SessionVars.usuarioLG.description());

	}

	public static UsuarioSession getUserFromSession(Action action) {

		return (UsuarioSession) action.getSession().getAttribute(SessionVars.usuarioLG.description());
	}

	public static boolean usuarioEstaLogado(Action action) {

		UsuarioSession usu = (UsuarioSession) action.getSession().getAttribute(SessionVars.usuarioLG.description());

	    if (usu==null || usu.getId()==0) return false;

	    return true;
    }

	public static int mantemUsuarioNaRequisicao(Action action) {

		UsuarioSession usuarioSession = (UsuarioSession) action.getSession().getAttribute(SessionVars.usuarioLG.description());

		int idDoUsuarioLogado = 0;

        if (usuarioSession!=null && usuarioSession.getId()>0)
        {
         idDoUsuarioLogado = usuarioSession.getId();
         action.getOutput().setValue("logado","S");
         action.getOutput().setValue("nomeLogado",usuarioSession.getEmail());
         action.getOutput().setValue("idUsuario",idDoUsuarioLogado);
		  logger.info("usuario ["+usuarioSession.getEmail()+"] logado!");
        }
       else
        {
    	   action.getOutput().setValue("logado","N");
         logger.info("usuario anonimo!");
        }

        return idDoUsuarioLogado;

	}

}
