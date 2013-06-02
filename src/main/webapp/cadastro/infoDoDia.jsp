<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization groups="admins"/>
<c:set var="buscaUm"><%=String.valueOf(Constantes.ACTION_BUSCA)%></c:set>
<c:import url="cabecalho.jsp"/>
 <script language="JavaScript" src="../js/calendar.js"></script>
<html>
<body>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="infoDoDia.titulo"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="infoDoDia.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="infoDoDia.novo.action" method="post">
 </c:otherwise>
</c:choose>
 
 <div class="row">
  <span class="label">
   <mtw:i18n key="infoDoDia.data"/>:
  </span>
  <mtw:input type="text" name="data" id="data" size="50" maxlength="50" />
  <a href="javascript:calendario1.popup();" ><img src="../img/calendar.gif" width="16" height="16" border="0"></a>
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="data" /></font>
  </mtw:hasError>
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="infoDoDia.jogo"/>:
  </span>
  <mtw:select name="jogo_id" list="JOGOS" />
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="infoDoDia.emu"/>:
  </span>
  <mtw:select name="emu_id" list="EMULADORES" />
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
    <form name="formulario" action="infoDoDia.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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

<mtw:list value="infosDoDia">
 <h1 class="cadastro"><mtw:i18n key="infoDoDia.lista"/></h1>
 <mtw:isEmpty>
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </mtw:isEmpty>
 <mtw:loop counter="contador" var="linha">
   <ul id="resultlist">
     <li> 
       (<mtw:out value="data"/>) - <a href="infoDoDia.buscaUm.action?id=<mtw:out value="id"/>"> JOGO: <c:out value="${linha.jogoVO.nome}"/> - EMULADOR: <c:out value="${linha.emuladorVO.nome}"/></a>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="infoDoDia.remove.action?id=<mtw:out value="id"/>">X</a> ] 
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
  var calendario1 = new calendar1(document.getElementById('data'));
  calendario1.year_scroll = true;
  calendario1.time_comp = false;
 </script>				
</body>
</html>