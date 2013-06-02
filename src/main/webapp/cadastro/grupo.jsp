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
 <h1 class="cadastro"><mtw:i18n key="grupo.titulo"/></h1>
 
<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="grupo.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="grupo.novo.action" method="post">
 </c:otherwise> 
</c:choose>

 <div class="row">
  <span class="label">
   <mtw:i18n key="grupo.nome"/>:
  </span>
  <span class="form">
  <mtw:input type="text" name="nome" size="50" maxlength="150" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="nome" /></font>
  </mtw:hasError>
  </span>
 </div>
 <div class="spacer">
  &nbsp;
  </div>

 <div class="row">
  <span class="botoes">

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
    <input type="submit" name="tipoCRUD" value="Alterar" />
    <mtw:input type="hidden" name="id" />
    <input type="submit" name="tipoCRUD" value="Remover" /></form>
    <form name="formulario" action="grupo.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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

<mtw:list value="grupos">
 <h1 class="cadastro"><mtw:i18n key="grupo.lista"/></h1>
 <mtw:isEmpty>
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </mtw:isEmpty>
 <mtw:loop counter="contador">
   <ul id="resultlist">
     <li> 
       <a href="grupo.buscaUm.action?id=<mtw:out value="id"/>"><mtw:out value="nome"/></a>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="grupo.remove.action?id=<mtw:out value="id"/>">X</a> ] 
     </li>
   </ul>
 <c:set var="total"><mtw:out value="contador"/></c:set>
 </mtw:loop>
 <h1 class="cadastro"><mtw:i18n key="aplicacao.total"/>&nbsp;<c:out value="${total+1}"/>&nbsp;<mtw:i18n key="aplicacao.registros"/></h1> 
</mtw:list>

</div>
 </c:otherwise> 
</c:choose>
</form>
</body>
</html>