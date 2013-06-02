<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization groups="admins,colaboradores"/>
<c:set var="buscaUm"><%=String.valueOf(Constantes.ACTION_BUSCA)%></c:set>
<c:import url="cabecalho.jsp"/>
<html>
 <script language="JavaScript">
  function changeJogo() {
   if (document.getElementById('id').selectedIndex==0) return;
   document.getElementById('formularioBusca').submit()
  }
 </script>
<body>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="jogo.titulo"/></h1>

<div class="c">
 <form name="formularioBusca"  id="formularioBusca" action="jogo.buscaUm.action" method="post">
  <mtw:select emptyField="true" id="id" name="id" list="JOGOS" extra=" onChange=changeJogo()"/>
 </form>
</div>
 <%-- actionCRUD = <c:out value="${actionCRUD}"/>  --%>
<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="jogo.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="jogo.novo.action" method="post">
 </c:otherwise>
</c:choose>

 <h1 class="cadastro"><mtw:i18n key="jogo.titulo"/></h1>

 <div class="row">
  <span class="label">
   <mtw:i18n key="jogo.nome"/>:
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
   <mtw:i18n key="jogo.licenca"/>:
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
   <mtw:i18n key="jogo.tipo"/>:
  </span>
  <span class="form">
   <mtw:select name="tipo" list="tiposDeJogo" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="tipo"/></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="jogo.aberto"/>:
  </span>
  <span class="form">
    <mtw:radiobuttons name="aberto" list="simOuNao"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="aberto" /></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="jogo.jogaEmRede"/>:
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
   <mtw:i18n key="jogo.precisa3d"/>:
  </span>
  <span class="form">
    <mtw:radiobuttons name="precisa3d" list="simOuNao"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="precisa3d" /></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="jogo.temSom"/>:
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
   <mtw:i18n key="jogo.consoleOuX11"/>:
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
   <mtw:i18n key="jogo.siteOficial"/>:
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
   <mtw:i18n key="jogo.siteCompra"/>:
  </span>
  <span class="form">
   <mtw:input type="text" name="siteCompra" size="50" maxlength="150" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="siteCompra"/></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="jogo.descricao"/>:
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
   <mtw:i18n key="jogo.votos"/>:
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
   <mtw:i18n key="jogo.hits"/>:
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
   <mtw:i18n key="jogo.notaGeral"/>:
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
   <mtw:i18n key="jogo.fabricante"/>:
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
   <mtw:i18n key="jogo.destaque"/>:
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

  <c:if test="${actionCRUD == buscaUm}">
   <div class="row">
	<mtw:list value="textosDeJogos">
	 <h1 class="cadastro"><mtw:i18n key="jogo.dicas"/></h1>
	 <mtw:isEmpty>
	  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
	 </mtw:isEmpty>
	 <mtw:loop  var="registro" counter="contador">
	   <ul id="resultlist">
	     <li>
	       (<fmt:formatDate value="${registro.dataPublic}"/>)-<a href="textoDeJogo.buscaUm.action?id=<mtw:out value="id"/>"><c:out value="${registro.jogo.nome}"/>-<c:out value="${registro.usuario.nome}"/></a>
	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="textoDeJogo.remove.action?id=<mtw:out value="id"/>">X</a> ]
	     </li>
	   </ul>
	   <c:set var="total"><mtw:out value="contador"/></c:set>
	 </mtw:loop>
	 <h1 class="cadastro"><mtw:i18n key="aplicacao.total"/>&nbsp;<c:out value="${(total+1)}"/>&nbsp;<mtw:i18n key="aplicacao.registros"/></h1>
	</mtw:list>
   </div>
  </c:if>

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
    <form name="formulario" action="jogo.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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
<%--
<div class="formCadastro">
 <mtw:list value="jogos">
  <h1 class="cadastro"><mtw:i18n key="jogo.lista"/></h1>
  <mtw:isEmpty>
   <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
  </mtw:isEmpty>
  <mtw:paginator size="50" value="jogos" >
   <ul id="resultlist">
    <mtw:loop var="registro">
     <li>
      <a href="jogo.buscaUm.action?id=<mtw:out value="id"/>"><mtw:out value="nome"/></a>
      <c:if test="${registro.destaque}">&nbsp;&nbsp;<font color="red"><mtw:i18n key="jogo.destaqueDesc"/></font></c:if>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="jogo.remove.action?id=<mtw:out value="id"/>">X</a> ]
     </li>
    </mtw:loop>
   </ul>
   <br><br>
   <div class="c">
    <mtw:hasPrevious><a href="jogo.action?page=<mtw:out />"><img src="/img/anterior.png" alt="" width="16" height="16"/></a></mtw:hasPrevious>
    <mtw:pageNumbers pagesToShow="10">
     <mtw:isCurrPage><b><font size="+1"><mtw:out /></font></b></mtw:isCurrPage>
     <mtw:isCurrPage negate="true"><a href="jogo.action?page=<mtw:out />"><mtw:out /></a></mtw:isCurrPage>
    </mtw:pageNumbers>
    <mtw:hasNext><a href="jogo.action?page=<mtw:out />"><img src="/img/posterior.png" alt="" width="16" height="16"/></a></mtw:hasNext>
    <mtw:isEmpty negate="true">
     <h1 class="cadastro"><mtw:i18n key="aplicacao.resultados"/> <mtw:resultFrom /> - <mtw:resultTo /> <mtw:i18n key="aplicacao.resultadosDe"/> <mtw:resultTotal /></h1>
    </mtw:isEmpty>
   </div>
  </mtw:paginator>
 </mtw:list>
</div>
 --%>
 </c:otherwise>
</c:choose>
</form>
</body>
</html>