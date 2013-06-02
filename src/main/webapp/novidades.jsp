<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
function setupLinks() {
 <c:forEach items="${novidades}" var="novidade" varStatus="contador">
  arrLinks[<c:out value="${contador}"/>] = "<a href="<c:out value="${novidade.link}"/>";
  arrTitles[<c:out value="${contador}"/>] = "<img src="../../img/novidades/pcgames.gif" alt=""><br/><c:out value="${novidade.texto}"/>";
</c:forEach>
}
