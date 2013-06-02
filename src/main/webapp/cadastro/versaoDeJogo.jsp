<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization groups="admins,colaboradores"/>
<c:set var="buscaUm"><%=String.valueOf(Constantes.ACTION_BUSCA)%></c:set>
<c:import url="cabecalho.jsp"/>
<%--
 <script language="JavaScript" src="../js/calendar.js"></script>
  --%>
<mtw:inputDateConfig/>
<html>
<body>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="versaoDeJogo.titulo"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="versaoDeJogo.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="versaoDeJogo.novo.action" method="post">
 </c:otherwise>
</c:choose>

 <div class="row">
  <span class="label">
   <mtw:i18n key="versaoDeJogo.release"/>:
  </span>
  <mtw:input type="text" name="release" size="50" maxlength="150" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="release" /></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="versaoDeJogo.obs"/>:
  </span>
  <mtw:textarea name="obs" cols="50" rows="10"/>
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="obs"/></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="versaoDeJogo.link"/>:
  </span>
  <mtw:input type="text" name="link" size="50" maxlength="150" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="link" /></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="versaoDeJogo.dataLancamento"/>:
  </span>
  <mtw:inputDate id="dataLancamento" name="dataLancamento" size="50" maxlength="50" dateFormat="dd-mm-yyyy"/>
 <%--
  <a href="javascript:calendario1.popup();" ><img src="../img/calendar.gif" width="16" height="16" border="0"></a>
  --%>
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="dataLancamento" /></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="versaoDeJogo.tipo"/>:
  </span>
  <mtw:select name="tipo_id" list="JOGOS" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="tipo_id" /></font>
  </mtw:hasError>
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
    <form name="formulario" action="versaoDeJogo.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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

<mtw:list value="versoesDeJogos">
 <h1 class="cadastro"><mtw:i18n key="versaoDeJogo.lista"/></h1>
 <mtw:isEmpty>
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </mtw:isEmpty>
 <mtw:paginator size="50" value="versoesDeJogos" >
 <mtw:loop  var="registro">
   <ul id="resultlist">
     <li>
       (<fmt:formatDate value="${registro.dataLancamento}" pattern="dd/MM/yyyy"/>) - <a href="versaoDeJogo.buscaUm.action?id=<mtw:out value="id"/>"><mtw:out value="release"/> - <mtw:out value="link"/> [ <c:out value="${registro.jogo.nome}"/> ]</a>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="versaoDeJogo.remove.action?id=<mtw:out value="id"/>">X</a> ]
     </li>
   </ul>
  </mtw:loop>
   <br><br>
   <div class="c">
    <mtw:hasPrevious><a href="versaoDeJogo.action?page=<mtw:out />"><img src="/img/anterior.png" alt="" width="16" height="16"/></a></mtw:hasPrevious>
    <mtw:pageNumbers pagesToShow="10">
     <mtw:isCurrPage><b><font size="+1"><mtw:out /></font></b></mtw:isCurrPage>
     <mtw:isCurrPage negate="true"><a href="versaoDeJogo.action?page=<mtw:out />"><mtw:out /></a></mtw:isCurrPage>
    </mtw:pageNumbers>
    <mtw:hasNext><a href="versaoDeJogo.action?page=<mtw:out />"><img src="/img/posterior.png" alt="" width="16" height="16"/></a></mtw:hasNext>
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
 <%--
 <script language="JavaScript">
  var calendario1 = new calendar1(document.getElementById('dataLancamento'));
  calendario1.year_scroll = true;
  calendario1.time_comp = false;
 </script>
  --%>
</body>
</html>