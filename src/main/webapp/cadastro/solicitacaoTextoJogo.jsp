<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization permission="aprovador"/>
<c:import url="cabecalho.jsp"/>
<html>
<body>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="solicitacaoTextoJogo.titulo"/></h1>
 <br>
<c:choose>
 <c:when test="${empty textos}">
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </c:when>
 <c:otherwise>
	<c:forEach items="${textos}" var="linha" varStatus="status">
     <ul id="resultlist">
      <li>
        <a href="/infoJogo.inst.action?id=<c:out value="${linha.id}"/>">
         [<c:out value="${linha.nome}"/>] 
        </a> 
        - <b>
         <c:choose>
          <c:when test="${linha.texto.tipo==1}"> <mtw:i18n key="solicitacaoTextoJogo.tipo.1"/></c:when>
          <c:when test="${linha.texto.tipo==2}"> <mtw:i18n key="solicitacaoTextoJogo.tipo.2"/></c:when>
          <c:when test="${linha.texto.tipo==3}"> <mtw:i18n key="solicitacaoTextoJogo.tipo.3"/></c:when>
         </c:choose>       
         </b>
        - <a href="/user/profile/<c:out value="${linha.usuario.id}"/>.games">
         <c:out value="${linha.usuario.nome}"/> (<c:out value="${linha.usuario.email}"/>)  
        </a>- 
         <a href="/admin.solicitacaoTextoJogoDetalhe.action?id=<c:out value="${linha.texto.id}"/>">
          <mtw:i18n key="solicitacaoTextoJogo.alterar"/>
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