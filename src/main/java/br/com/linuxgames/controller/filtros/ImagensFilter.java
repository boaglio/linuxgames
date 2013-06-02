package br.com.linuxgames.controller.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Fernando Boaglio
 *
 */
public class ImagensFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
 
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpSession session = httpRequest.getSession();
//        String uri = httpRequest.getRequestURI();
//        String expiredSession = "/pages/expiredSession.jsp";
//
//        try {
//            validateOperation(httpRequest, session);
//        }
//        catch (PermissionDeniedException pde) {
//            //redirectToDeniedPage(httpRequest, httpResponse);
//            redirectToDeniedPage(request, response);
//        }
//
//        if (uri.indexOf("error.jsp") != -1) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        try {
//            if (session.getAttribute("username") != null) {
//                // O usuario ja foi validado anteriormente, passa direto...
//                chain.doFilter(request, response);
//                return;
//            }
//        }
//        catch (Exception e) {
//            // Ocorreu um erro no processamento da solicitacao.
//            log.error(this.getClass().getName(), "Redirecting To App Error Page", e.getMessage());
//            log.error(this.getClass().getName(), "doFilter", "StackTrace do erro: ", e);
//            e.printStackTrace();
//            request.setAttribute("exception", e);
//            redirectToAppErrorPage(request, response);
//            return;
//        }
//
//        try {
//            // Busca usuario do dominio atraves do filtro NtlmHttpFilter
//            String username = httpRequest.getRemoteUser();
//
//            if (null == username) {
//                // throw new NullPointerException(Messages.getString("AuthenticatorFilter.userNotFound"));
//            	// sem usuario logado, redirecionando para a tela inicial
//            	request.getRequestDispatcher(expiredSession).forward(request, response);
//            	return;
//            }
//
//            int domainLen = username.indexOf('\\');
//
//            if (domainLen != -1) {
//                username = username.substring(domainLen + 1).toLowerCase();
//            }
//
//            log.info(this.getClass().getName(), "doFilter", "SystemUser = " + username);
//
//            SystemUserDelegate delegate = SystemUserDelegate.getInstance();
//
//            SystemUserVO systemUserVO = new SystemUserVO();
//            systemUserVO.setNxNmLogin(username);
//
//            systemUserVO = delegate.findUserByLogin(systemUserVO);
//
//            if (null == systemUserVO || (!systemUserVO.getScomSystem().booleanValue())) {
//                log.alert(this.getClass().getName(), "System Permission Denied", "SystemUser",systemUserVO.getIdUser());
//                request.setAttribute("errorMessage", Messages.getString("AuthenticatorFilter.systemPermissionDenied"));
//                redirectToErrorPage(httpRequest, httpResponse);
//            }
//
//            session.setAttribute("username", username.toUpperCase());
//            // adiciona informacao do usuario na session
//            session.setAttribute(Constants.SYSTEM_USER_SESSION_VO, systemUserVO);
//
//            if (uri.indexOf("Logout") == -1) {
//             // adiciona informacao do usuario no cache
//             HashMap userHashMap = CacheManager.getSystemUserCache().getCacheAsHashMap();
//             userHashMap.put(systemUserVO.getIdUser(), systemUserVO.getNxNmLogin());
//            }
//            try {
//                chain.doFilter(request, response);
//            }
//            catch (Exception e) {
//                // Ocorreu um erro no processamento da solicitacao.
//                request.setAttribute("exception", e);
//                redirectToAppErrorPage(request, response);
//            }
//
//            // atualiza informa??o do servidor
//            ServerInfoCache serverInfoCache = ServerInfoCache.getInstance();
//            serverInfoCache.setServer(httpRequest);
//        }
//        catch (SocketException se) {
//            chain.doFilter(request, response);
//        }
//        catch (SmbException smbe) {
//            chain.doFilter(request, response);
//        }
//        catch (NullPointerException e) {
//            log.error(this.getClass().getName(), "doFilter", Messages.getString("AuthenticatorFilter.userNotFound"), e);
//            request.setAttribute("errorMessage", Messages.getString("AuthenticatorFilter.userNotFound"));
//            redirectToErrorPage(request, response);
//        }
//        catch (IllegalStateException e) {
//            log.error(this.getClass().getName(), "doFilter", "Usuario invalido", e);
//            request.setAttribute("exception", e);
//            redirectToAppErrorPage(request, response);
//        }
//        catch (DatabaseException de) {
//            log.error(this.getClass().getName(), "doFilter", "Erro generico", de);
//            request.setAttribute("errorMessageDetails", de);
//            redirectToAppErrorPage(request, response,Messages.getString("database.error.jndi.desc"));
//        }
//        catch (Exception e) {
//            log.error(this.getClass().getName(), "doFilter", "Erro generico", e);
//            request.setAttribute("exception", e);
//            redirectToAppErrorPage(request, response);
//        }
//    }
//
//    public void destroy() {
////        filterConfig = null;
//    }
//
//    private void redirectToDeniedPage(ServletRequest request, ServletResponse response) {
//        try {
//            log.alert(this.getClass().getName(), "doFilter", Messages.getString("AuthenticatorFilter.permissionDenied"));
//            request.setAttribute("errorMessage", Messages.getString("AuthenticatorFilter.permissionDenied"));
//            request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
//        }
//        catch (Exception e) {
//        }
    }

	public void destroy() {
		
	}
  
}