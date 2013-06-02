<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<!-- solicitacaoNovidade.jsp -->
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization permission="aprovador"/>
<c:import url="cabecalho.jsp"/>
<html>
<body>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="solicitacaoNoticia.titulo"/></h1>
 <br>
<c:choose>
 <c:when test="${empty textos}">
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </c:when>
 <c:otherwise>
	<c:forEach items="${textos}" var="linha" varStatus="status">
     <ul id="resultlist">
      <li>
         [<c:out value="${linha.texto}"/>]
        - <a href="/user/profile/<c:out value="${linha.usuario.id}"/>.games">
         <c:out value="${linha.usuario.nome}"/>
        </a>
         <a href="/admin.solicitacaoNovidadeDetalhe.action?id=<c:out value="${linha.id}"/>">
          <mtw:i18n key="solicitacaoNoticia.alterar"/>
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