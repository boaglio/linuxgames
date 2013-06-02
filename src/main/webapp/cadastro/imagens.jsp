<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<%--  
<mtw:requiresAuthorization groups="admins,colaboradores"/>
--%>
<c:set var="buscaUm"><%=String.valueOf(Constantes.ACTION_BUSCA)%></c:set>
<c:import url="cabecalho.jsp"/>
<html>
<body>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="imagem.titulo"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="imagens.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="imagens.novo.action"  method="post" enctype="multipart/form-data">
 </c:otherwise>
</c:choose>
<%-- 
 <div class="row">
  <span class="label">
   <mtw:i18n key="imagem.fileName"/>:
  </span>
  <mtw:input type="text" name="fileName" size="50" maxlength="150" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="fileName" /></font>
  </mtw:hasError>
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="imagem.fileMimeType"/>:
  </span>
  <mtw:select name="fileMimeType" list="mimeTypes" emptyField="true"/>
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="fileMimeType" /></font>
  </mtw:hasError>
 </div>
 <div class="spacer">
  &nbsp;
  </div>
    --%> 
 <div class="row">
  <span class="label">
   <mtw:i18n key="imagem.jogo"/>:
  </span>
  <mtw:select name="jogo_id" list="JOGOS" emptyField="true" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="jogo_id" /></font>
  </mtw:hasError>
 </div>
 <div class="spacer">
  &nbsp;
  </div>
  
 <div class="row">
  <span class="label">
   <mtw:i18n key="imagem.emu"/>:
  </span>
  <mtw:select name="emu_id" list="EMULADORES" emptyField="true" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="emu_id" /></font>
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
    <mtw:input type="hidden" name="jogoOld" />
    <mtw:input type="hidden" name="emuOld" />
    <input type="submit" name="tipoCRUD" value="Remover" /></form>
    <form name="formulario" action="imagens.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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
<%-- 
imagens=<mtw:out value="imagens"/>
 --%>
<mtw:list value="imagens">
 <h1 class="cadastro"><mtw:i18n key="imagem.lista"/></h1>
 <mtw:isEmpty>
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </mtw:isEmpty>
 <mtw:paginator size="30" value="versoesDeEmuladores" >
 <mtw:loop  var="registro">
   <ul id="resultlist">
     <li> 
       <a href="imagens.buscaUm.action?jogo_id=<mtw:out value="jogo.id"/>&emu_id=<mtw:out value="emu.id"/>">
        <mtw:out value="fileName"/>
       </a>
       - <mtw:out value="jogo.nome"/>
        [<a target="_BLANK" href="<mtw:contextPath/>/imagem?nocache=y&jogo_id=<mtw:out value="jogo.id"/>&emu_id=<mtw:out value="emu.id"/>">
        exibir imagem
        </a>]
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="imagens.remove.action?jogo_id=<mtw:out value="jogo.id"/>&emu_id=<mtw:out value="emu.id"/>">X</a> ]
     </li>
   </ul>
 </mtw:loop>
   <div class="c">
    <mtw:hasPrevious><a href="imagens.action?page=<mtw:out />"><img src="/img/anterior.png" alt="" width="16" height="16"/></a></mtw:hasPrevious>
    <mtw:pageNumbers pagesToShow="10">
     <mtw:isCurrPage><b><font size="+1"><mtw:out /></font></b></mtw:isCurrPage>
     <mtw:isCurrPage negate="true"><a href="imagens.action?page=<mtw:out />"><mtw:out /></a></mtw:isCurrPage>
    </mtw:pageNumbers>
    <mtw:hasNext><a href="imagens.action?page=<mtw:out />"><img src="/img/posterior.png" alt="" width="16" height="16"/></a></mtw:hasNext>
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