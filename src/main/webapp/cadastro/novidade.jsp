<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization groups="admins,colaboradores,editores"/>
<c:set var="buscaUm"><%=String.valueOf(Constantes.ACTION_BUSCA)%></c:set>
<c:import url="cabecalho.jsp"/>
<mtw:inputDateConfig/>
<html>
<body>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="novidade.titulo"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="/games/novidade-atualiza" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="/games/novidade-novo" method="post">
 </c:otherwise>
</c:choose>

 <div class="row">
  <span class="label">
   <mtw:i18n key="novidade.texto"/>:
  </span>
  <mtw:textarea name="texto" cols="50" rows="10"/>
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="texto"/></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="novidade.link"/>:
  </span>
  <mtw:input type="text" name="link" size="50" maxlength="150" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="link" /></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="novidade.data"/>:
  </span>
  <mtw:inputDate id="dataPublic" name="dataPublic" size="50" maxlength="50" dateFormat="dd-mm-yyyy"/>
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="dataPublic" /></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="novidade.usuario"/>:
  </span>
  <mtw:select name="user_id" list="USUARIOS" />
 </div>

 <div class="spacer">
  &nbsp;
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="novidade.postaTwitter"/>:
  </span>
  <span class="form">
    <mtw:radiobuttons name="postaTwitter" list="simOuNao" defValue="2"/>
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
    <form name="formulario" action="/games/novidade" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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

<mtw:list value="novidades">
 <h1 class="cadastro"><mtw:i18n key="novidade.lista"/></h1>
 <mtw:isEmpty>
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </mtw:isEmpty>
  <mtw:paginator size="10" value="jogos" >
   <ul id="resultlist">
    <mtw:loop var="registro">
     <li>
       (<mtw:out value="dataPublic"/>) - <a href="/games/novidade-buscaUm/<mtw:out value="id"/>"><mtw:out value="texto"/> - <mtw:out value="link"/></a>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       [ <a href="/games/novidade-twittar/<mtw:out value="id"/>">twittar!</a> ]
       [ <a href="/games/novidade-remove/<mtw:out value="id"/>">X</a> ]
     </li>
    </mtw:loop>
   </ul>
   <br><br>
   <div class="c">
    <mtw:hasPrevious><a href="novidade?page=<mtw:out />"><img src="/img/anterior.png" alt="" width="16" height="16"/></a></mtw:hasPrevious>
    <mtw:pageNumbers pagesToShow="5">
     <mtw:isCurrPage><b><font size="+1"><mtw:out /></font></b></mtw:isCurrPage>
     <mtw:isCurrPage negate="true"><a href="novidade?page=<mtw:out />"><mtw:out /></a></mtw:isCurrPage>
    </mtw:pageNumbers>
    <mtw:hasNext><a href="novidade?page=<mtw:out />"><img src="/img/posterior.png" alt="" width="16" height="16"/></a></mtw:hasNext>
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