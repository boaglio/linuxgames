<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization groups="admins,colaboradores,editores"/>
<style type="text/css">
 html, body {
  font-family: Verdana,sans-serif;
  background-color: #fea;
  color: #000;
}
a:link, a:visited { color: #00f; }
a:hover { color: #048; }
a:active { color: #f00; }
textarea { background-color: #fff;
border:1px solid #00f; }
</style>

<c:set var="buscaUm"><%=String.valueOf(Constantes.ACTION_BUSCA)%></c:set>
<c:import url="cabecalho.jsp"/>
<html>
 <head>
  <script type="text/javascript" language="JavaScript" src="../js/calendar.js"></script>
  <script type="text/javascript" language="JavaScript"src="../htmlarea/htmlarea.js"></script>
  <script type="text/javascript" language="JavaScript">
   HTMLArea.loadPlugin("TableOperations");
   var editor = null;
  </script>
 </head>
<body onload="initEditor()">
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="artigo.tituloPrincipal"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="/games/artigo-atualiza" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="/games/artigo-novo" method="post">
 </c:otherwise>
</c:choose>
 <div class="row">
  <span class="label">
   <mtw:i18n key="artigo.titulo"/>:
  </span>
  <span class="form">
   <mtw:input type="text" name="titulo" size="50" maxlength="150" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="titulo" /></font>
   </mtw:hasError>
  </span>
 </div>
 <%--
 <div class="row">
  <span class="label">
   <mtw:i18n key="artigo.texto"/>:
  </span>
  <span class="form">
   <mtw:textarea name="texto" cols="50" rows="10"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="texto"/></font>
   </mtw:hasError>
  </span>
 </div>
 --%>

 <div class="row">

 <mtw:i18n key="artigo.texto"/>
  <mtw:textarea id="ta" name="texto" rows="24" cols="80"/>

 <%--
<input type="submit" name="ok" value="  submit  " />
<input type="button" name="ins" value="  insert html  " onclick="return insertHTML();" />
<input type="button" name="hil" value="  highlight text  " onclick="return highlight();" />

<a href="javascript:mySubmit()">submit</a>

<script type="text/javascript">
function mySubmit() {
// document.edit.save.value = "yes";
document.edit.onsubmit(); // workaround browser bugs.
document.edit.submit();
};
</script>
--%>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="texto"/></font>
   </mtw:hasError>
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="artigo.data"/>:
  </span>
  <span class="form">
   <mtw:input type="text" name="dataPublic" id="dataPublic" size="50" maxlength="50" />
   <a href="javascript:calendario1.popup();" ><img src="../img/calendar.gif" width="16" height="16" border="0"></a>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="dataPublic" /></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="artigo.usuario"/>:
  </span>
  <span class="form">
   <mtw:select name="usuarioId" list="USUARIOS" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="usuarioId" /></font>
   </mtw:hasError>
  </span>
 </div>
  <div class="row">
  <span class="label">
   <mtw:i18n key="artigo.votos"/>:
  </span>
  <span class="form">
   <mtw:input type="text" name="votos"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="votos"/></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="artigo.notaGeral"/>:
  </span>
  <span class="form">
   <mtw:input type="text" name="notaGeral"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="notaGeral"/></font>
   </mtw:hasError>
  </span>
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="artigo.aprovado"/>:
  </span>
  <span class="form">
   <mtw:radiobuttons name="aprovado" list="aprovadoOuReprovado" />
    <mtw:hasError>
     <font class="errorMessage"><mtw:error field="aprovado" /></font>
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
    <form name="formulario" action="/games/artigo" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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

<mtw:list value="artigos">
 <h1 class="cadastro"><mtw:i18n key="artigo.lista"/></h1>
 <mtw:isEmpty>
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </mtw:isEmpty>
 <mtw:loop var="registro" counter="contador">
   <ul id="resultlist">
     <li>
       (<fmt:formatDate value="${registro.dataPublic}"/>)-<a href="/games/artigo-buscaUm/<mtw:out value="id"/>"><mtw:out value="titulo"/></a>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="/games/artigo-remove/<mtw:out value="id"/>">X</a> ]
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