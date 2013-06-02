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
 <h1 class="cadastro"><mtw:i18n key="emuladoresFavoritos.titulo"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="emuladoresFavoritos.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="emuladoresFavoritos.novo.action" method="post">
 </c:otherwise>
</c:choose>

 <div class="row">
  <span class="label">
   <mtw:i18n key="emuladoresFavoritos.usuario"/>:
  </span>
  <mtw:select name="usuario_id" list="USUARIOS" />
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="emuladoresFavoritos.emulador"/>:
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
    <mtw:input type="hidden" name="oldEmulador" />
    <mtw:input type="hidden" name="oldUsuario" />
    <input type="submit" name="tipoCRUD" value="Remover" /></form>
    <form name="formulario" action="emuladoresFavoritos.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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
 <mtw:list value="emuladoresFavoritos">
  <h1 class="cadastro"><mtw:i18n key="emuladoresFavoritos.lista"/></h1>
  <mtw:isEmpty>
   <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
  </mtw:isEmpty>
  <mtw:paginator size="30" value="emuladoresFavoritos">
   <ul id="resultlist">
    <mtw:loop var="linha">
      <li>
        <a href="emuladoresFavoritos.buscaUm.action?usuario_id=<c:out value="${linha.usuario.id}"/>&emu_id=<c:out value="${linha.emulador.id}"/>">
         <c:out value="${linha.usuario.nome}"/> - <c:out value="${linha.emulador.nome}"/></a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="emuladoresFavoritos.remove.action?usuario_id=<c:out value="${linha.usuario.id}"/>&emu_id=<c:out value="${linha.emulador.id}"/>">X</a> ]
      </li>
    </mtw:loop>
   </ul>
   <br><br>
   <div class="c">
    <mtw:hasPrevious><a href="emuladoresFavoritos.action?page=<mtw:out />"><img src="/img/anterior.png" alt="" width="16" height="16"/></a></mtw:hasPrevious>
    <mtw:pageNumbers pagesToShow="10">
     <mtw:isCurrPage><b><font size="+1"><mtw:out /></font></b></mtw:isCurrPage>
     <mtw:isCurrPage negate="true"><a href="emuladoresFavoritos.action?page=<mtw:out />"><mtw:out /></a></mtw:isCurrPage>
    </mtw:pageNumbers>
    <mtw:hasNext><a href="emuladoresFavoritos.action?page=<mtw:out />"><img src="/img/posterior.png" alt="" width="16" height="16"/></a></mtw:hasNext>
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