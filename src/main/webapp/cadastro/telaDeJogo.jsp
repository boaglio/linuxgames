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
 <h1 class="cadastro"><mtw:i18n key="telaDeJogo.titulo"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="telaDeJogo.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="telaDeJogo.novo.action" method="post" enctype="multipart/form-data">
 </c:otherwise>
</c:choose>
<%-- 
 <div class="row">
  <span class="label">
   <mtw:i18n key="telaDeJogo.nome"/>:
  </span>
  <mtw:select list="imagensDeJogos" name="nome"/>
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="nome" /></font>
  </mtw:hasError>
 </div>
 --%> 
 <div class="row">
  <span class="label">
   <mtw:i18n key="telaDeJogo.descricao"/>:
  </span>
  <mtw:textarea name="descricao" cols="50" rows="10"/>
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="descricao"/></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="telaDeJogo.tipo"/>:
  </span>
  <mtw:select name="tipo_id" list="JOGOS" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="tipo_id" /></font>
  </mtw:hasError>
 </div>
 <div class="spacer">
  &nbsp;
  </div>

 <c:if test="${actionCRUD != buscaUm}"> 
  <div class="row">
  <span class="label">
   <mtw:i18n key="imagem.fileContent"/>:
  </span>
  <input type="file" name="fileContent" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="fileContent" /></font>
  </mtw:hasError>
  <mtw:hasMessage>
   <h4><mtw:message/></h4>
  </mtw:hasMessage>
 </div> 
</c:if>  

 <div class="row">
  <span class="botoes">

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
    <input type="submit" name="tipoCRUD" value="Alterar" />
    <mtw:input type="hidden" name="id" />
    <input type="submit" name="tipoCRUD" value="Remover" /></form>
    <form name="formulario" action="telaDeJogo.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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

<mtw:list value="telasDeJogos">
 <h1 class="cadastro"><mtw:i18n key="telaDeJogo.lista"/></h1>
 <mtw:isEmpty>
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </mtw:isEmpty>
 <mtw:paginator size="50" value="telasDeJogos">
  <mtw:loop var="registro">
   <ul id="resultlist">
     <li>
       <a href="telaDeJogo.buscaUm.action?id=<mtw:out value="id"/>">[ <c:out value="${registro.jogo.nome}"/> ] - <mtw:out value="nome"/> - <mtw:out value="descricao"/></a>
       &nbsp;
       <a href="<mtw:contextPath/>/imagem?nocache=y&id=<mtw:out value="id"/>" target="_BLANK"> + + + </a>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="telaDeJogo.remove.action?id=<mtw:out value="id"/>">X</a> ]
     </li>
   </ul>
 </mtw:loop>
   <br/>
   <br/>
   <div class="c">
    <mtw:hasPrevious><a href="telaDeJogo.action?page=<mtw:out />"><img src="/img/anterior.png" alt="" width="16" height="16"/></a></mtw:hasPrevious>
    <mtw:pageNumbers pagesToShow="10">
     <mtw:isCurrPage><b><font size="+1"><mtw:out /></font></b></mtw:isCurrPage>
     <mtw:isCurrPage negate="true"><a href="telaDeJogo.action?page=<mtw:out />"><mtw:out /></a></mtw:isCurrPage>
    </mtw:pageNumbers>
    <mtw:hasNext><a href="telaDeJogo.action?page=<mtw:out />"><img src="/img/posterior.png" alt="" width="16" height="16"/></a></mtw:hasNext>
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