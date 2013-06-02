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
 <h1 class="cadastro"><mtw:i18n key="roteiroInstalacaoEmu.titulo"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="roteiroInstalacaoEmu.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="roteiroInstalacaoEmu.novo.action" method="post">
 </c:otherwise>
</c:choose>

 <div class="row">
  <span class="label">
   <mtw:i18n key="roteiroInstalacaoEmu.emu"/>:
  </span>
  <span class="form">
   <mtw:select name="emulador_id" list="EMULADORES" />
    <mtw:hasError>
     <font class="errorMessage"><mtw:error field="emulador_id" /></font>
    </mtw:hasError>
  </span>
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="roteiroInstalacaoEmu.distro"/>:
  </span>
  <span class="form">
  <mtw:select name="distro_id" list="DISTROS" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="distro_id" /></font>
   </mtw:hasError>
  </span>
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="roteiroInstalacaoEmu.usuario"/>:
  </span>
  <span class="form">
   <mtw:select name="usuario_id" list="USUARIOS" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="usuario_id" /></font>
   </mtw:hasError>
  </span>
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="roteiroInstalacaoEmu.descricao"/>:
  </span>
  <span class="form">
   <mtw:textarea name="descricao" cols="50" rows="10"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="descricao" /></font>
   </mtw:hasError>
  </span>
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="roteiroInstalacaoEmu.aprovado"/>:
  </span>
  <span class="form">
   <mtw:radiobuttons name="aprovado" list="aprovadoOuReprovado" />
    <mtw:hasError>
     <font class="errorMessage"><mtw:error field="aprovado" /></font>
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
    <form name="formulario" action="roteiroInstalacaoEmu.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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

 <h1 class="cadastro"><mtw:i18n key="roteiroInstalacaoEmu.lista"/></h1>
<c:choose>
 <c:when test="${empty roteirosDeInstalacaoEmu}">
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </c:when>
 <c:otherwise>
	<c:forEach items="${roteirosDeInstalacaoEmu}" var="linha" varStatus="status">
     <ul id="resultlist">
      <li>
        <a href="roteiroInstalacaoEmu.buscaUm.action?id=<c:out value="${linha.id}"/>">
         <c:out value="${linha.emulador.nome}"/> - <c:out value="${linha.distro.nome}"/></a> -
         <c:choose>
          <c:when test="${linha.aprovado}">
           <mtw:i18n key="aplicacao.aprovado"/>
          </c:when>
          <c:otherwise>
           <mtw:i18n key="aplicacao.reprovado"/>
          </c:otherwise>
         </c:choose>

        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="roteiroInstalacaoEmu.remove.action?id=<c:out value="${linha.id}"/>">X</a> ]
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