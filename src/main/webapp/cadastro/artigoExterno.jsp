<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization groups="admins,colaboradores,editores"/>

<c:set var="buscaUm"><%=String.valueOf(Constantes.ACTION_BUSCA)%></c:set>
<c:import url="cabecalho.jsp"/>
<html>
 <head>

 </head>
<body >
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="artigoExterno.tituloPrincipal"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="artigoExterno.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="artigoExterno.novo.action" method="post">
 </c:otherwise>
</c:choose>

 <div class="row">
  <span class="label">
   <mtw:i18n key="artigoExterno.titulo"/>:
  </span>
  <span class="form">
   <mtw:input type="text" name="titulo" size="50" maxlength="150" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="titulo" /></font>
   </mtw:hasError>
  </span>
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="artigoExterno.link"/>:
  </span>
  <span class="form">
   <mtw:input type="text" name="link" size="50" maxlength="150" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="link" /></font>
   </mtw:hasError>
  </span>
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="artigoExterno.fonte"/>:
  </span>
  <span class="form">
   <mtw:input type="text" name="fonte" size="50" maxlength="150" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="fonte" /></font>
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
    <form name="formulario" action="artigoExterno.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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

<mtw:list value="artigosExternos">
 <h1 class="cadastro"><mtw:i18n key="artigoExterno.lista"/></h1>
 <mtw:isEmpty>
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </mtw:isEmpty>
 <mtw:loop var="registro" counter="contador">
   <ul id="resultlist">
     <li>
       (<c:out value="${registro.fonte}"/>)-<a href="artigoExterno.buscaUm.action?id=<mtw:out value="id"/>"><mtw:out value="titulo"/></a>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="artigoExterno.remove.action?id=<mtw:out value="id"/>">X</a> ]
     </li>
   </ul>
   <c:set var="total"><mtw:out value="contador"/></c:set>
 </mtw:loop>
 <h1 class="cadastro"><mtw:i18n key="aplicacao.total"/>&nbsp;<c:out value="${(total+1)}"/>&nbsp;<mtw:i18n key="aplicacao.registros"/></h1>
</mtw:list>

</div>
 </c:otherwise>
</c:choose>
</form>
 <script language="JavaScript">
  var calendario1 = new calendar1(document.getElementById('dataPublic'));
  calendario1.year_scroll = true;
  calendario1.time_comp = false;
 </script>
</body>
</html>