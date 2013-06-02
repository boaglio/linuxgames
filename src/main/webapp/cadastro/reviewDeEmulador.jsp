<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization groups="admins,colaboradores"/>
<c:set var="buscaUm"><%=String.valueOf(Constantes.ACTION_BUSCA)%></c:set>
<c:import url="cabecalho.jsp"/>
<html>
<body>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="reviewDeEmulador.titulo"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="reviewDeEmulador.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="reviewDeEmulador.novo.action" method="post">
 </c:otherwise>
</c:choose>

 <div class="row">
  <span class="label">
   <mtw:i18n key="reviewDeEmulador.comentario"/>:
  </span>
  <mtw:textarea name="comentario" cols="50" rows="5"/>
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="comentario" /></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="reviewDeEmulador.nota"/>:
  </span>
  <mtw:input type="text" name="nota" size="5" maxlength="5" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="nota"/></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="reviewDeEmulador.emulador"/>:
  </span>
  <mtw:select name="emu_id" list="EMULADORES" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="tipo_id" /></font>
  </mtw:hasError>
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="reviewDeEmulador.usuario"/>:
  </span>
  <mtw:select name="usuario_id" list="USUARIOS" />
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
    <form name="formulario" action="reviewDeEmulador.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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

<mtw:list value="reviewsDeEmuladores">
 <h1 class="cadastro"><mtw:i18n key="reviewDeEmulador.lista"/></h1>
 <mtw:isEmpty>
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </mtw:isEmpty>
 <mtw:paginator size="30" value="reviewsDeEmuladores" >
 <mtw:loop  var="registro">
   <ul id="resultlist">
     <li>
       <a href="reviewDeEmulador.buscaUm.action?id=<mtw:out value="id"/>">[ <c:out value="${registro.emulador.nome}"/> - <c:out value="${registro.usuario.nome}"/> ] - <mtw:out value="comentario"/> - <mtw:out value="nota"/></a>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="reviewDeEmulador.remove.action?id=<mtw:out value="id"/>">X</a> ]
     </li>
   </ul>
 </mtw:loop>
   <br><br>
   <div class="c">
    <mtw:hasPrevious><a href="reviewDeEmulador.action?page=<mtw:out />"><img src="/img/anterior.png" alt="" width="16" height="16"/></a></mtw:hasPrevious>
    <mtw:pageNumbers pagesToShow="10">
     <mtw:isCurrPage><b><font size="+1"><mtw:out /></font></b></mtw:isCurrPage>
     <mtw:isCurrPage negate="true"><a href="reviewDeEmulador.action?page=<mtw:out />"><mtw:out /></a></mtw:isCurrPage>
    </mtw:pageNumbers>
    <mtw:hasNext><a href="reviewDeEmulador.action?page=<mtw:out />"><img src="/img/posterior.png" alt="" width="16" height="16"/></a></mtw:hasNext>
    <mtw:isEmpty negate="true">
     <h1 class="cadastro"><mtw:i18n key="aplicacao.resultados"/> <mtw:resultFrom /> - <mtw:resultTo /> <mtw:i18n key="aplicacao.resultadosDe"/> <mtw:resultTotal /></h1>
    </mtw:isEmpty>
   </div>
  </mtw:paginator>
</mtw:list>
</div>
 </c:otherwise>
</c:choose>
</form>
</body>
</html>