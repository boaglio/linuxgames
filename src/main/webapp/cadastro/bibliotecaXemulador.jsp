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
 <h1 class="cadastro"><mtw:i18n key="bibliotecaXemulador.titulo"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="bibliotecaXemulador.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="bibliotecaXemulador.novo.action" method="post">
 </c:otherwise>
</c:choose>

 <div class="row">
  <span class="label">
   <mtw:i18n key="bibliotecaXemulador.biblioteca"/>:
  </span>
  <mtw:select name="biblioteca_id" list="BIBLIOTECAS" />
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="bibliotecaXemulador.emu"/>:
  </span>
  <mtw:select name="emulador_id" list="EMULADORES" />
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
    <form name="formulario" action="bibliotecaXemulador.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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

 <h1 class="cadastro"><mtw:i18n key="bibliotecaXemulador.lista"/></h1>
<c:choose>
 <c:when test="${empty bibliotecaXemuladors}">
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </c:when>
 <c:otherwise>
	<c:forEach items="${bibliotecaXemuladors}" var="linha" varStatus="status">
     <ul id="resultlist">
      <li>
        <a href="bibliotecaXemulador.buscaUm.action?biblioteca_id=<c:out value="${linha.biblioteca.id}"/>&emulador_id=<c:out value="${linha.emulador.id}"/>">
         <c:out value="${linha.biblioteca.nome}"/> - <c:out value="${linha.emulador.nome}"/></a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="bibliotecaXemulador.remove.action?biblioteca_id=<c:out value="${linha.biblioteca.id}"/>&emulador_id=<c:out value="${linha.emulador.id}"/>">X</a> ]
      </li>
     </ul>
      <c:set var="total"><c:out value="${status.count}"/></c:set>
	</c:forEach>
 </c:otherwise>
</c:choose>
 <h1 class="cadastro"><mtw:i18n key="aplicacao.total"/>&nbsp;<c:out value="${total}"/>&nbsp;<mtw:i18n key="aplicacao.registros"/></h1>
</div>
 </c:otherwise>
</c:choose>
</form>
</body>
</html>