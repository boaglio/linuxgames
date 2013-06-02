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
 <h1 class="cadastro"><mtw:i18n key="jogo.tituloUploadImagem"/></h1>

<mtw:hasMessage>
<h4><mtw:message/></h4>
 Imagem: <a href="<mtw:out value="imagemNova"/>" style="color:red;text-decoration:none" target="_BLANK"><mtw:out value="imagemNova"/></a>
</mtw:hasMessage>

<form action="jogoImagem.action" method="post" enctype="multipart/form-data"><br>

 <div class="row">
  <span class="label">
   <mtw:i18n key="jogo.destinoDaImagem"/>:
  </span>
  <span class="form">
   <select name="destinoDaImagem">
    <option value="//img//games//">Jogo</option>
    <option value="//img//emu//">Emulador</option>
   </select>
  </span>
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="jogo.arquivo"/>:
  </span>
  <span class="form">
  <input name="arquivo" type="file"/>
  </span>
 </div>

 <div class="spacer">
  &nbsp;
 </div>

 <div class="row">
  <span class="botoes">
   <input type="submit" value="Enviar">
  </span>
 </div>

</form>

</body>
</html>