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
 <form name="formularioBusca"  id="formularioBusca" action="jogoEN.buscaUm.action" method="post">
  <mtw:select emptyField="true" id="id" name="id" list="JOGOS_EN" extra=" onChange=changeJogo()"/>
 </form>
</div>
 <%-- actionCRUD = <c:out value="${actionCRUD}"/>  --%>
<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="jogoEN.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="jogoEN.novo.action" method="post">
 </c:otherwise>
</c:choose>

 <h1 class="cadastro"><mtw:i18n key="jogo.titulo"/></h1>

 <div class="row">
  <span class="label">
   <mtw:i18n key="jogo.nome"/>:
  </span>
  <span class="form">
     <span class="form">
   <mtw:input type="text" name="nome" size="50" maxlength="150" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="nome" /></font>
   </mtw:hasError>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="jogo.idioma"/>:
  </span>
  <span class="form">
   <mtw:out value="idioma" />
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
   <mtw:i18n key="jogo.pai"/>:
  </span>
  <span class="form">
   <mtw:select name="jogo_id" emptyField="true" list="JOGOS" />
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
    <form name="formulario" action="jogoEN.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
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

 </c:otherwise>
</c:choose>
</form>
</body>
</html>