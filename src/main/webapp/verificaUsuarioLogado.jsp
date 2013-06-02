<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

<c:set var="usuarioLogado"><mtw:out value="logado"/></c:set>
<c:if test="${usuarioLogado != 'S'}">
 <script language="JavaScript" type="text/javascript">
   window.location='/user/login.games';
 </script>
</c:if>
