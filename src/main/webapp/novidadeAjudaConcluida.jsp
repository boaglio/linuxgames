<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

<c:import url="/head.jsp"/>

<body>

<div id="overDiv"></div>
<!-- jogoAjudaConcluida.jsp  -->

<div class="c">
 <span class="logo"><a href="http://www.linuxgames.com.br/"><img id="imglogo" alt="LinuxGames.com.br" src="/img/linuxgames-logo.gif"/></a></span>
</div>

 <div class="spacer">&nbsp;</div>


<html>
 <script language="JavaScript">

 </script>
<body>
<div class="formCadastro">

 <form action="/games/index-home" method="post">

 <div class="spacer">&nbsp;</div>

  <div class="c">
   <img src="/img/tux-mario.gif" alt=""  />
  </div>

 <div class="spacer">&nbsp;</div>

 <div class="row">
   <mtw:i18n key="jogo.ajudaConcluidaTexto"/>
 </div>

 <div class="spacer">
  &nbsp;
 </div>

 <div class="c">
   <mtw:i18n key="jogo.ajudaConcluidaTexto2"/>
 </div>

 <div class="spacer">
  &nbsp;
 </div>

 <div class="c">
   <mtw:i18n key="jogo.ajudaConcluidaObrigado"/>
 </div>

 <div class="spacer">
  &nbsp;
 </div>

 <div class="c">
   <input type="submit" value="<mtw:i18n key="jogo.ajudaConcluidaSubmit"/>" onclick="javascript:window.location('/')" />
 </div>


</div>
<br><br>

</form>
</body>
</html>