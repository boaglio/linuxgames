<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
 <h1 class="cadastro"><mtw:i18n key="enquete.tituloPrincipal"/></h1>
 
<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="enqueteCRUD.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="enqueteCRUD.novo.action" method="post">
 </c:otherwise> 
</c:choose>
 <div class="row">
  <span class="label">
   <mtw:i18n key="enquete.titulo"/>:
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
   <mtw:i18n key="enquete.opt1"/>:
  </span>
  <span class="form">  
   <mtw:input type="text" size="20"  name="opt1"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="opt1"/></font>
   </mtw:hasError>
  </span>   
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="enquete.qt1"/>:
  </span>
  <span class="form">  
   <mtw:input type="text" size="10" name="qt1"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="qt1"/></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="enquete.opt2"/>:
  </span>
  <span class="form">  
   <mtw:input type="text" size="20"   name="opt2"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="opt2"/></font>
   </mtw:hasError>
  </span>   
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="enquete.qt2"/>:
  </span>
  <span class="form">  
   <mtw:input type="text" size="10"  name="qt2"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="qt2"/></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="enquete.opt3"/>:
  </span>
  <span class="form">  
   <mtw:input type="text"  size="20"  name="opt3"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="opt3"/></font>
   </mtw:hasError>
  </span>   
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="enquete.qt3"/>:
  </span>
  <span class="form">  
   <mtw:input type="text" size="10"  name="qt3"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="qt3"/></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="enquete.opt4"/>:
  </span>
  <span class="form">  
   <mtw:input type="text" size="20"   name="opt4"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="opt4"/></font>
   </mtw:hasError>
  </span>   
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="enquete.qt4"/>:
  </span>
  <span class="form">  
   <mtw:input type="text"  size="10" name="qt4"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="qt4"/></font>
   </mtw:hasError>
  </span>
 </div>
  
 <div class="row">
  <span class="label">
   <mtw:i18n key="enquete.ativo"/>:
  </span>
  <span class="form">
   <mtw:radiobuttons name="ativo" list="ativadoOuDesativado" />
    <mtw:hasError>
     <font class="errorMessage"><mtw:error field="ativo" /></font>
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
    <form name="formulario" action="enqueteCRUD.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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

<mtw:list value="enquetes">
 <h1 class="cadastro"><mtw:i18n key="enquete.lista"/></h1>
 <mtw:isEmpty>
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </mtw:isEmpty>
 <mtw:loop  var="registro" counter="contador">
   <ul id="resultlist">
     <li> 
       <a href="enqueteCRUD.buscaUm.action?id=<mtw:out value="id"/>"><mtw:out value="titulo"/> - <mtw:out value="qt"/> <mtw:i18n key="enquete.votos"/> </a>
        <c:choose>
         <c:when test="${registro.ativo}">
          <font color="red">::ativado::</font>
         </c:when>
         <c:otherwise>
          <font color="green">::desativado::</font>         
         </c:otherwise>
        </c:choose>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="enqueteCRUD.remove.action?id=<mtw:out value="id"/>">X</a> ] 
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
</body>
</html>