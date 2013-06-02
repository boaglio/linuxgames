<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<%@ page import="java.util.*, javax.servlet.*, javax.servlet.http.*" isELIgnored="true" contentType="text/html; charset=iso-8859-1" %>
<%!

    public static Locale EN = new Locale("en");

    public static Locale PT = new Locale("pt", "BR");

    public static void addCookie(HttpServletResponse response, String key, String value) {
        Cookie c = new Cookie(key, value);
        c.setMaxAge(31104000);
        c.setPath("/");
        response.addCookie(c);
    }

    public static String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
	        for (int i = 0; i < cookies.length; i++) {
    	        if (cookies[i].getName().equals(key)) {
        	        return cookies[i].getValue();
                }
            }
        }
        return null;
   	}

    public static boolean isSupported(String param) {
        if (param.startsWith("pt") || param.startsWith("en")) return true;
        return false;
    }
%>
<%
String param = request.getParameter("loc");
if (param != null && isSupported(param)) {
    addCookie(response, "linuxgames-language", param);
    if (param.startsWith("en")) {
        session.setAttribute(org.mentawai.action.BaseLoginAction.LOCALE_KEY, EN);
    } else {
        session.setAttribute(org.mentawai.action.BaseLoginAction.LOCALE_KEY, PT);
    }
} else {
    String cookie = getCookie(request, "linuxgames-language");
    if (cookie != null) {
        if (cookie.startsWith("en")) {
             session.setAttribute(org.mentawai.action.BaseLoginAction.LOCALE_KEY, EN);
        } else {
            session.setAttribute(org.mentawai.action.BaseLoginAction.LOCALE_KEY, PT);
        }
    }
}
%>