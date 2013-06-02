package br.com.linuxgames.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Classe respons&aacute;vel por centralizar os m&eacute;todos de tratamento de requisi&ccedil;&atilde;o
 * @author Fernando Boaglio
 * @version $Revision: 1.1 $ $Date: 2006/05/27 00:05:37 $ $Author: fb $
 *
 */
public class HttpHelper {

    /**
     * M&eacute;todo repons&aacute;vel por retornar a URL da aplica&ccedil;&atilde;o
     * @param req
     * @return
	 */
    public static String getApplicationURL(HttpServletRequest req) {

        // nome do servidor
        String hostname = req.getServerName();
        // porta do servidor
        String port = String.valueOf(req.getServerPort());
        // porta do servidor
        String virtualPath = req.getRequestURI().substring(0,req.getRequestURI().indexOf("/", 2));

        return  "http://" + hostname + ":" + port + virtualPath+"/";
    }

    /**
     * retorna IP do visitante da aplica&ccedil;&atilde;o
     * @param req
     * @return
     */
    public static String getRemoteIP(HttpServletRequest req) {
        return  req.getRemoteAddr();
    }

}