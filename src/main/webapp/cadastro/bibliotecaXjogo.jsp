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
 <h1 class="cadastro"><mtw:i18n key="bibliotecaXjogo.titulo"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="bibliotecaXjogo.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="bibliotecaXjogo.novo.action" method="post">
 </c:otherwise>
</c:choose>

 <div class="row">
  <span class="label">
   <mtw:i18n key="bibliotecaXjogo.biblioteca"/>:
  </span>
  <mtw:select name="biblioteca_id" list="BIBLIOTECAS" />
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="bibliotecaXjogo.jogo"/>:
  </span>
  <mtw:select name="jogo_id" list="JOGOS" />
 </div>

 <div class="spacer">
  &nbsp;
  </div>

 <div class="row">
  <span class="botoes">

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
    <input type="submit" name="tipoCRUD" value="Alterar" />
    <mtw:input type="hidden" name="oldJogo" />
    <mtw:input type="hidden" name="oldBiblioteca" />
    <input type="submit" name="tipoCRUD" value="Remover" /></form>
    <form name="formulario" action="bibliotecaXjogo.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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
 <mtw:list value="bibliotecaXjogos">
  <h1 class="cadastro"><mtw:i18n key="bibliotecaXjogo.lista"/></h1>
  <mtw:isEmpty>
   <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
  </mtw:isEmpty>
  <mtw:paginator size="30" value="bibliotecaXjogos">
   <ul id="resultlist">
    <mtw:loop var="linha">
      <li>
        <a href="bibliotecaXjogo.buscaUm.action?biblioteca_id=<c:out value="${linha.biblioteca.id}"/>&jogo_id=<c:out value="${linha.jogo.id}"/>">
         <c:out value="${linha.biblioteca.nome}"/> - <c:out value="${linha.jogo.nome}"/></a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="bibliotecaXjogo.remove.action?biblioteca_id=<c:out value="${linha.biblioteca.id}"/>&jogo_id=<c:out value="${linha.jogo.id}"/>">X</a> ]
      </li>
    </mtw:loop>
   </ul>
   <br><br>
   <div class="c">
    <mtw:hasPrevious><a href="bibliotecaXjogo.action?page=<mtw:out />"><img src="/img/anterior.png" alt="" width="16" height="16"/></a></mtw:hasPrevious>
    <mtw:pageNumbers pagesToShow="10">
     <mtw:isCurrPage><b><font size="+1"><mtw:out /></font></b></mtw:isCurrPage>
     <mtw:isCurrPage negate="true"><a href="bibliotecaXjogo.action?page=<mtw:out />"><mtw:out /></a></mtw:isCurrPage>
    </mtw:pageNumbers>
    <mtw:hasNext><a href="bibliotecaXjogo.action?page=<mtw:out />"><img src="/img/posterior.png" alt="" width="16" height="16"/></a></mtw:hasNext>
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