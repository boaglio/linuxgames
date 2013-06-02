<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization groups="admins"/> 
<c:import url="cabecalho.jsp"/>
<html>
<body>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="usuario.titulo"/></h1>
 <br>
 <div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="usuario.lista"/></h1>
<c:choose>
 <c:when test="${empty usuarios}">
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </c:when>
 <c:otherwise>
	<c:forEach items="${usuarios}" var="linha" varStatus="status">
     <ul id="resultlist">
      <li>
        <a href="/user/profile/<c:out value="${linha.id}"/>.games">
         <c:out value="${linha.nome}"/> - <c:out value="${linha.email}"/> - [<c:out value="${linha.dataDoCadastro}"/>] 
        </a> 
      </li>
     </ul>
      <c:set var="total"><c:out value="${status.count}"/></c:set>
	</c:forEach>
 </c:otherwise>
</c:choose>
 <h1 class="cadastro"><mtw:i18n key="aplicacao.total"/>&nbsp;<c:out value="${total}"/>&nbsp;<mtw:i18n key="aplicacao.registros"/></h1>
</div>

</form>
</body>
</html>