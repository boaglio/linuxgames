<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<mtw:useI18N />
<c:import url="lang-cookie.jsp"/>
<c:import url="cabecalho.jsp"/>
<c:import url="menuTopo.jsp"/>
<c:import url="menuEsqHome.jsp"/>

<!--
 <c:out value="${indexVO.versaoVO.versao}"/> - <c:out value="${indexVO.versaoVO.descricao}"/>
 -->
<div id="centercontent">

<%-- usuario
<form action="logon" id="formLogon" method="post">
<div id="account">
 <span class="linhaC">
  <span class="esq">
    <mtw:i18n key="index.usuario"/><input type="text" class="accountBox" name="usuario"/>
    <mtw:i18n key="index.senha"/><input type="password" class="accountBox" name="senha"/>
    <a href="javascript:void(0);" onclick="form.submit();" class="button" id="buttonOK"><mtw:i18n key="aplicacao.ok"/></a>
    </form>
  </span>
  <span class="dir">
   <a href="NovaConta" class="loginLink" onmouseover="return overlib('<mtw:i18n key="index.novoUsuario.desc"/>',CAPTION,'<mtw:i18n key="index.novoUsuario"/>');" onmouseout="return nd();"><mtw:i18n key="index.novoUsuario"/></a>
  </span>
 </span>
</div>
</form>
--%>

 <form id="formBusca" action="">
 <div id="busca">
  <div class="buscaDesc">
   <img src="<mtw:contextPath/>/img/penguin-walker.gif" align="center" width="23" height="30" alt=""/>
   &nbsp;&nbsp;<mtw:i18n key="index.busca"/>&nbsp;&nbsp;
   <img src="<mtw:contextPath/>/img/penguin-walker2.gif" align="center" width="23" height="30" alt=""/>
  </div>
  <div class="buscaDesc">
    <input type="text" class="searchBox" autocomplete="off" name="buscaStr" id="buscaStr" onkeyup="getValues();"/>
  </div>
  <div class="buscaDesc">
    <select size="4" id="listaBusca" style="display:none;" class="searchList" onchange="redirectCombo(this.selectedIndex)">
     <option></option>
    </select>
  </div>
  <font class="totalGamesNumber"><c:out value="${indexVO.totalDeJogos}"/></font>
  <font class="totalGames"><mtw:i18n key="index.total.jogos"/></font>
  &nbsp;&nbsp;
  <font class="totalGamesNumber"><c:out value="${indexVO.totalDeEmuladores}"/></font>
  <font class="totalGames"><mtw:i18n key="index.total.emus"/></font>
 </div>

  <%--
      <a href="javascript:void(0);" onclick="form.submit();" class="button" id="buttonOK"><mtw:i18n key="aplicacao.ok"/></a>
    --%>
 </form>


<a class="twitter-timeline" href="https://twitter.com/linuxgamesbr" data-widget-id="244962432567615488">Tweets by @linuxgamesbr</a>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>

<a class="twitter-timeline" data-dnt=true href="https://twitter.com/search?q=linux+games" data-widget-id="244964316573470720">Tweets about "linux games"</a>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>

  <div class="linhaC">
    <c:import url="/lang-links.jsp">
     <c:param name="url">/games/index-home</c:param>
    </c:import>
  </div>

 <div class="jd_news_scroll" id="elm1">
  <ul style="top: 0pt;">
  <c:forEach items="${novidades}" var="novidade">
   <li class="">
    <span style="font-weight: bold;">&#187;<fmt:formatDate pattern="dd/MM/yyyy" value="${novidade.dataPublic}"/>&#187;</span>
   <a href="<c:out value="${novidade.link}"/>" target="_BLANK"><c:out value="${novidade.texto}"/></a>
   </li>
  </c:forEach>
  </ul>
 </div>

   <c:set var="usuarioLogado"><mtw:out value="logado"/></c:set>
	<c:choose>
	 <c:when test="${usuarioLogado == 'S'}">
	   <a href="/games/index-ajuda"><mtw:i18n key="novidade.postar"/>
        <img src="/img/smiley_help.gif" width="40" height="42"  />
	   </a>

	  <div class="linhaC">
	    <a href="/games/infoJogo-ajuda/<mtw:out value="id"/>/<mtw:isLocale value="pt_BR">pt</mtw:isLocale><mtw:isLocale value="en">en</mtw:isLocale>" alt="<mtw:i18n key="jogo.alterarPopup"/>" class="bandeiras"
	     onmouseover="return overlib('<mtw:i18n key="jogo.adicionarPopupTitulo"/>',CAPTION,'<mtw:i18n key="jogo.adicionarPopup"/>');" onmouseout="return nd();">
	     <mtw:i18n key="jogo.postarNovoJogo"/>
	     <img src="/img/smiley_help.gif" width="40" height="42"  />
	    </a>
	  </div>

	 </c:when>
	 <c:otherwise>
	  <a id="login_link_home" href="#">
	   <mtw:i18n key="novidade.postarNaoLogado"/>
       <img src="/img/smiley_help.gif" width="40" height="42"  />
	  </a>

	  <div class="linhaC">
	    <a id="new_game_link_home"  href="#">
	     <mtw:i18n key="jogo.postarNovoJogo"/>
	     <img src="/img/smiley_help.gif" width="40" height="42"  />
	    </a>
	  </div>

	 </c:otherwise>
    </c:choose>

 <div id="jogoDoDia">
  <div class="linhaC">
    <h3><mtw:i18n key="index.jogoDoDia"/></h3>
  </div>
  <div class="linhaC">
    <a class="tituloLink" href="/games/infoJogo-jogo/<c:out value="${indexVO.jogoDoDia.id}"/>/1/">
  	 <c:out value="${indexVO.jogoDoDia.nome}"/>
	</a>
  </div>
  <div class="linhaC">
   <a class="tituloLink" href="/games/infoJogo-jogo/<c:out value="${indexVO.jogoDoDia.id}"/>">
    <img src="/img/games/<c:out value="${indexVO.imagemDoJogoDoDia}"/>" width="280" height="210" alt=""/>
   </a>
  </div>
  <div class="linhaC">
    <c:out value="${indexVO.jogoDoDia.descricao}"/>
  </div>
  <div class="spacer">
  &nbsp;
  </div>
 </div>

 <%-- ultimas atualizacoes --%>
 <div class="spacer">&nbsp;</div>
  <div id="lastUpdates">
   <mtw:list value="top5updates">
    <h2><mtw:i18n key="updatelog.top"/></h2>
    <mtw:loop var="registro">
     <ul>
      <li>
       [<fmt:formatDate value="${registro.data}" pattern="dd/MM/yyyy"/>]&nbsp;
       <mtw:out value="descricao"/>
      </li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
  <div class="spacer">
  &nbsp;
  </div>

 <div id="emuDoDia">
  <div class="linhaC">
    <h3><mtw:i18n key="index.emuDoDia"/></h3>
  </div>
  <div class="linhaC">
    <a class="tituloLink" href="/games/infoEmulador-emulador/<c:out value="${indexVO.emuladorDoDia.id}"/>/2/">
  	 <c:out value="${indexVO.emuladorDoDia.nome}"/>
	</a>
  </div>
  <div class="linhaC">
    <img src="/img/emu/<c:out value="${indexVO.imagemDoEmuladorDoDia}"/>" width="280" height="210" alt=""/>
  </div>
  <div class="linhaE">
    <c:out value="${indexVO.emuladorDoDia.descricao}"/>
  </div>
  <div class="spacer">
  &nbsp;
  </div>
 </div>

<%-- nao ajuda, eu nao ajudo

  <div id="LM">
   <span class="esq">
    <img src="<mtw:i18n key="index.botton.img"/>" alt=""/>
   </span>
   <span class="dir">
     <span class="linhaLM">
      <mtw:i18n key="index.botton.line1"/>
     </span>
     <span class="linhaLM">
      <mtw:i18n key="index.botton.line2"/>
     </span>
     <span class="linhaLM">
      <a href="<mtw:i18n key="index.botton.link.url"/>">
       <mtw:i18n key="index.botton.link.msg"/>
      </a>
     </span>
   </span>
   <div class="spacer">
   &nbsp;
   </div>
  </div>
 --%>

</div>

<c:import url="rodape.jsp"/>

</body>
</html>
