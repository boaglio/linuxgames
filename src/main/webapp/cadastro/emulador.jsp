<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization groups="admins,colaboradores"/>
<c:set var="buscaUm"><%=String.valueOf(Constantes.ACTION_BUSCA)%></c:set>
<c:import url="cabecalho.jsp"/>
<html>
<body>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="emulador.titulo"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="emulador.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="emulador.novo.action" method="post">
 </c:otherwise>
</c:choose>

 <div class="row">
  <span class="label">
   <mtw:i18n key="emulador.nome"/>:
  </span>
  <span class="form">
   <mtw:input type="text" name="nome" size="50" maxlength="150" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="nome" /></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="emulador.tipo"/>:
  </span>
  <span class="form">
   <mtw:select name="tipo" list="tiposDeEmulador" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="tipo_id"/></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="emulador.licenca"/>:
  </span>
  <span class="form">
   <mtw:select name="licenca_id" list="LICENCAS" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="licenca_id"/></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="emulador.jogaEmRede"/>:
  </span>
  <span class="form">
    <mtw:radiobuttons name="jogaEmRede" list="simOuNao"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="jogaEmRede" /></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="emulador.temSom"/>:
  </span>
  <span class="form">
    <mtw:radiobuttons name="temSom" list="simOuNao"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="temSom" /></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="emulador.consoleOuX11"/>:
  </span>
  <span class="form">
    <mtw:radiobuttons name="consoleOuX11" list="consoleOuX11"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="consoleOuX11" /></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="emulador.siteOficial"/>:
  </span>
  <span class="form">
   <mtw:input type="text" name="siteOficial" size="50" maxlength="150" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="siteOficial" /></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="emulador.descricao"/>:
  </span>
  <span class="form">
   <mtw:textarea name="descricao" cols="50" rows="10"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="descricao"/></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="emulador.votos"/>:
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
   <mtw:i18n key="emulador.hits"/>:
  </span>
  <span class="form">
   <mtw:input type="text" name="hits"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="hits"/></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="emulador.notaGeral"/>:
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
   <mtw:i18n key="emulador.fabricante"/>:
  </span>
  <span class="form">
   <mtw:select name="fabricante_id" list="FABRICANTES" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="fabricante_id"/></font>
   </mtw:hasError>
  </span>
 </div>

  <div class="row">
  <span class="label">
   <mtw:i18n key="emulador.destaque"/>:
  </span>
  <span class="form">
   <mtw:select name="destaque" list="destaqueOuNao" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="destaque"/></font>
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
    <form name="formulario" action="emulador.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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
 <mtw:list value="emuladores">
  <h1 class="cadastro"><mtw:i18n key="emulador.lista"/></h1>
  <mtw:isEmpty>
   <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
  </mtw:isEmpty>
  <mtw:paginator size="50" value="emuladores" >
   <ul id="resultlist">
    <mtw:loop var="registro" >
     <li>
       <a href="emulador.buscaUm.action?id=<mtw:out value="id"/>"><mtw:out value="nome"/> - <mtw:out value="tipoNome"/></a>
       <mtw:if test="destaque">&nbsp;&nbsp;<font color="red"><mtw:i18n key="emulador.destaqueDesc"/></font></mtw:if>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="emulador.remove.action?id=<mtw:out value="id"/>">X</a> ]
     </li>
    </mtw:loop>
   </ul>
   <br><br>
   <div class="c">
    <mtw:hasPrevious><a href="emulador.action?page=<mtw:out />"><img src="/img/anterior.png" alt="" width="16" height="16"/></a></mtw:hasPrevious>
    <mtw:pageNumbers pagesToShow="10">
     <mtw:isCurrPage><b><font size="+1"><mtw:out /></font></b></mtw:isCurrPage>
     <mtw:isCurrPage negate="true"><a href="emulador.action?page=<mtw:out />"><mtw:out /></a></mtw:isCurrPage>
    </mtw:pageNumbers>
    <mtw:hasNext><a href="emulador.action?page=<mtw:out />"><img src="/img/posterior.png" alt="" width="16" height="16"/></a></mtw:hasNext>
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