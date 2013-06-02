<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization groups="admins"/> 
<c:set var="buscaUm"><%=String.valueOf(Constantes.ACTION_BUSCA)%></c:set>
<c:import url="cabecalho.jsp"/>
<html>
<body>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="votoXemulador.titulo"/></h1>
 
<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="votoXemulador.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="votoXemulador.novo.action" method="post">
 </c:otherwise> 
</c:choose>

 <div class="row">
  <span class="label">
   <mtw:i18n key="votoXemulador.IP"/>:
  </span>
  <mtw:input type="text" name="IP" size="50" maxlength="150" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="IP" /></font>
  </mtw:hasError>
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="votoXemulador.emulador"/>:
  </span>
  <span class="form">  
   <mtw:select name="idEmulador" list="EMULADORES" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="idEmulador"/></font>
   </mtw:hasError>
  </span>
 </div>
 
 <div class="row">
  <span class="botoes">

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
    <input type="submit" name="tipoCRUD" value="Alterar" />
    <mtw:input type="hidden" name="oldIP" />
    <mtw:input type="hidden" name="oldIdEmulador" />        
    <input type="submit" name="tipoCRUD" value="Remover" /></form>
    <form name="formulario" action="votoXemulador.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
  </span>
 </div>

</div>
 </c:when>
 <c:otherwise>
   <input type="submit" value="Cadastrar" />
  </span>
 </div>

</div>
  
<br><br>
<div class="formCadastro">

 <h1 class="cadastro"><mtw:i18n key="votoXemulador.lista"/></h1>
<c:choose>
 <c:when test="${empty votoXemuladors}">
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </c:when>
 <c:otherwise>
	<c:forEach items="${votoXemuladors}" var="linha" varStatus="status">
     <ul id="resultlist">
      <li>
        <a href="votoXemulador.buscaUm.action?IP=<c:out value="${linha.IP}"/>&idEmulador=<c:out value="${linha.emulador.id}"/>">
         <c:out value="${linha.emulador.nome}"/> - IP <c:out value="${linha.IP}"/></a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="votoXemulador.remove.action?IP=<c:out value="${linha.IP}"/>&idEmulador=<c:out value="${linha.emulador.id}"/>">X</a> ]
      </li>
     </ul>
      <c:set var="total"><c:out value="${status.count}"/></c:set>
	</c:forEach>
 </c:otherwise>
</c:choose>
 <h1 class="cadastro"><mtw:i18n key="aplicacao.total"/>&nbsp;<c:out value="${total}"/>&nbsp;<mtw:i18n key="aplicacao.registros"/></h1>
</div>
 </c:otherwise> 
</c:choose>
</form>
			
</body>
</html>