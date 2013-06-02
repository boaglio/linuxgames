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
 <h1 class="cadastro"><mtw:i18n key="textoDeJogo.titulo"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="textoDeJogo.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="textoDeJogo.novo.action" method="post">
 </c:otherwise>
</c:choose>

 <div class="row">
  <span class="label">
   <mtw:i18n key="textoDeJogo.texto"/>:
  </span>
  <span class="form">
   <mtw:textarea name="texto" cols="50" rows="10"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="texto"/></font>
   </mtw:hasError>
  </span>
 </div>

  <div class="row">
  <span class="label">
   <mtw:i18n key="textoDeJogo.link"/>:
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
   <mtw:i18n key="textoDeJogo.data"/>:
  </span>
  <span class="form">
   <mtw:inputDate id="dataPublic" name="dataPublic" size="50" maxlength="50" dateFormat="dd-mm-yyyy"/>
   <%--
   <a href="javascript:calendario1.popup();" ><img src="../img/calendar.gif" width="16" height="16" border="0"></a>
    --%>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="dataPublic" /></font>
   </mtw:hasError>
  </span>
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="textoDeJogo.tipo"/>:
  </span>
  <span class="form">
   <mtw:select name="tipo_id" list="JOGOS" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="tipo_id" /></font>
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
   <mtw:i18n key="textoDeJogo.tipo.texto"/>:
  </span>
  <span class="form">
   <mtw:select name="tipo" list="tiposDeTexto" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="tipo" /></font>
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
    <form name="formulario" action="textoDeJogo.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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

<mtw:list value="textosDeJogos">
 <h1 class="cadastro"><mtw:i18n key="textoDeJogo.lista"/></h1>
 <mtw:isEmpty>
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </mtw:isEmpty>
 <mtw:paginator size="30" value="textosDeJogos" >
 <mtw:loop  var="registro">
   <ul id="resultlist">
     <li>
       (<fmt:formatDate value="${registro.dataPublic}"/>)-<a href="textoDeJogo.buscaUm.action?id=<mtw:out value="id"/>"><c:out value="${registro.jogo.nome}"/>
        - <c:out value="${registro.usuario.nome}"/></a> -<b>
         <c:choose>
          <c:when test="${registro.aprovado}">
           <mtw:i18n key="aplicacao.aprovado"/>
          </c:when>
          <c:otherwise>
           <mtw:i18n key="aplicacao.reprovado"/>
          </c:otherwise>
         </c:choose>       </b>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="textoDeJogo.remove.action?id=<mtw:out value="id"/>">X</a> ]
     </li>
   </ul>
 </mtw:loop>
   <br><br>
   <div class="c">
    <mtw:hasPrevious><a href="textoDeJogo.action?page=<mtw:out />"><img src="/img/anterior.png" alt="" width="16" height="16"/></a></mtw:hasPrevious>
    <mtw:pageNumbers pagesToShow="10">
     <mtw:isCurrPage><b><font size="+1"><mtw:out /></font></b></mtw:isCurrPage>
     <mtw:isCurrPage negate="true"><a href="textoDeJogo.action?page=<mtw:out />"><mtw:out /></a></mtw:isCurrPage>
    </mtw:pageNumbers>
    <mtw:hasNext><a href="textoDeJogo.action?page=<mtw:out />"><img src="/img/posterior.png" alt="" width="16" height="16"/></a></mtw:hasNext>
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
  var calendario1 = new calendar1(document.getElementById('dataPublic'));
  calendario1.year_scroll = true;
  calendario1.time_comp = false;
 </script>
 --%>
</body>
</html>