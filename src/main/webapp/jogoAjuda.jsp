<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

<c:import url="/head.jsp"/>

<body>

<div id="overDiv"></div>
<!-- jogoAjuda.jsp -->

<div class="c">
 <span class="logo"><a href="http://www.linuxgames.com.br/"><img id="imglogo" alt="LinuxGames.com.br" src="/img/linuxgames-logo.gif"/></a></span>
</div>

 <div class="spacer">&nbsp;</div>


<html>
 <script language="JavaScript">

 </script>
<body>
<div class="formCadastro">

 <form name="formulario"  id="formulario" action="/games/infoJogo-ajudaManda" method="post">
 <input type="hidden" name="upt" value="1">

<div class="spacer">&nbsp;</div>

  <div class="c">
   <mtw:i18n key="jogo.ajudaIntro"/>
  </div>

<div class="spacer">&nbsp;</div>

 <h1 class="cadastro"><mtw:i18n key="jogo.titulo"/></h1>

<div class="c">
 <img src="/img/Brasil.png" border="0" width="50" height="50" alt="pt_BR"/>
  <mtw:isLocale value="pt_BR"><img src="/img/left-arrow.gif" alt="" height="50" width="50"/></mtw:isLocale>
  <mtw:isLocale value="en"><img src="/img/nothing.gif" alt="" height="50" width="50"/></mtw:isLocale>
 <img src="/img/nothing.gif" alt="" height="50" width="50"/>
 <img src="/img/USA.png" width="50" height="50" border="0" alt="en_US"/>
 <mtw:isLocale value="pt_BR"><img src="/img/nothing.gif" alt="" height="50" width="50"/></mtw:isLocale>
 <mtw:isLocale value="en"><img src="/img/left-arrow.gif" alt="" height="50" width="50"/></mtw:isLocale>
</div>

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
   <mtw:i18n key="jogo.fabricante"/>:
  </span>
  <span class="form">
   <mtw:select name="fabricante_id" list="FABRICANTES" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="fabricante_id"/></font>
   </mtw:hasError>
  </span>
 </div>

 <div class="spacer">
  &nbsp;
 </div>

 <h1 class="cadastro"><mtw:i18n key="jogo.tituloObs"/></h1>

 <div class="spacer">
  &nbsp;
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="jogo.jogoTemplateObs"/>
  </span>
  <span class="form">
   <mtw:textarea name="jogoTemplateObs" cols="50" rows="5"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="jogoTemplateObs"/></font>
   </mtw:hasError>
  </span>
 </div>

 <div class="spacer">
  &nbsp;
 </div>

 <div class="c">
   <mtw:input type="hidden" name="id"/>
   <mtw:input type="hidden" name="jogo_id"/>
   <mtw:input type="hidden" name="loc"/>
   <input type="submit" value="<mtw:i18n key="jogo.ajudarSubmit"/>" />
 </div>

</div>
<br><br>

</form>
</body>
</html>