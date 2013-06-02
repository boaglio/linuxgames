<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

<c:import url="verificaUsuarioLogado.jsp"/>

<c:import url="/head.jsp"/>

<body>

<!-- novidadeAjuda.jsp -->

<div class="c">
 <span class="logo"><a href="http://www.linuxgames.com.br/"><img id="imglogo" alt="LinuxGames.com.br" src="/img/linuxgames-logo.gif"/></a></span>
</div>

 <div class="spacer">&nbsp;</div>
<div class="formCadastro">

 <form name="formularioNoticia"  id="formularioNoticia" action="/games/index-ajuda" method="post">

 <input type="hidden" name="upt" value="1">

<div class="spacer">&nbsp;</div>

  <div class="c">
   <mtw:i18n key="novidade.ajudaIntro"/>
  </div>

<div class="spacer">&nbsp;</div>

 <h1 class="cadastro"><mtw:i18n key="novidade.tituloNova"/></h1>

 <div class="row">
  <span class="label">
   <mtw:i18n key="novidade.texto"/>:
  </span>
  <textarea name="texto" id="texto" cols="50" rows="3" wrap="hard" onkeydown="cutNews()" onkeyup="cutNews()"></textarea>
  <input readonly type="text" id="cont" size="3" maxlength="3" value="120"/>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="novidade.link"/>:
  </span>
  <mtw:input type="text" name="link" id="link" size="50"/>
 </div>

 <div class="spacer">
  &nbsp;
 </div>

 <div class="c">
   <mtw:input type="hidden" name="id"/>
   <mtw:input type="hidden" name="loc"/>
   <input type="button" value="<mtw:i18n key="novidade.ajudarSubmit"/>" onclick="javascript:if (enviaNovaNoticia()) { document.getElementById('formularioNoticia').submit(); } "/>
 </div>

</div>
<br><br>

</form>
</body>
</html>
<script language="JavaScript">
 document.getElementById('texto').focus();
</script>
