<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization groups="admins" />
 <c:set var="buscaUm"><%=String.valueOf(Constantes.ACTION_BUSCA)%></c:set>
<c:import url="cabecalho.jsp"/>
 <script language="JavaScript" src="../js/calendar.js"></script>
<html>
<body>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="banner.titulo"/></h1>
 
<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="banner.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="banner.novo.action" method="post">
 </c:otherwise> 
</c:choose>

 <div class="row">
  <span class="label">
   <mtw:i18n key="banner.imagem"/>:
  </span>
  <mtw:input type="text" name="imagem" maxlength="50"/>
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="imagem"/></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="banner.fabricante"/>:
  </span>
  <mtw:select name="fabricante_id" list="FABRICANTES" />  
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="fabricante" /></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="banner.inicio"/>:
  </span>
  <mtw:input type="text" name="dtInicio" id="dtInicio" size="10" maxlength="10" />
  <a href="javascript:calendario1.popup();" ><img src="../img/calendar.gif" width="16" height="16" border="0"></a>
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="dtInicio" /></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="banner.fim"/>:
  </span>
  <mtw:input type="text" name="dtFim" id="dtFim" size="10" maxlength="10" />
  <a href="javascript:calendario2.popup();" ><img src="../img/calendar.gif" width="16" height="16" border="0"></a>
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="dtFim" /></font>
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
    <form name="formulario" action="banner.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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

<mtw:list value="banners">
 <h1 class="cadastro"><mtw:i18n key="banner.lista"/></h1>
 <mtw:isEmpty>
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </mtw:isEmpty>
 <mtw:loop  var="registro" counter="contador">
   <ul id="resultlist">
     <li> 
       (<fmt:formatDate value="${registro.dtInicio}"/> 
       <c:choose>
        <c:when test="${empty registro.dtFim}">
         - <mtw:i18n key="banner.ainda"/>
        </c:when>  
        <c:otherwise>
		 <mtw:i18n key="banner.ate"/> <fmt:formatDate value="${registro.dtFim}"/>          
        </c:otherwise>
       </c:choose>
       ) - <a href="banner.buscaUm.action?id=<mtw:out value="id"/>"><mtw:out value="imagem"/></a>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="banner.remove.action?id=<mtw:out value="id"/>">X</a> ] 
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
  var calendario1 = new calendar1(document.getElementById('dtInicio'));
  calendario1.year_scroll = true;
  calendario1.time_comp = false;
  var calendario2 = new calendar1(document.getElementById('dtFim'));
  calendario2.year_scroll = true;
  calendario2.time_comp = false;
 </script>				
</body>
</html>