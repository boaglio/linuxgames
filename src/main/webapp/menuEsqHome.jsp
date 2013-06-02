<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<div id="leftcontent">
  <div id="campoLogin">
   <div style="border: 1px red">
    <a href="/games/entrevista" alt="">
    <mtw:i18n key="entrevista.link"/>
    <img src="/img/question.gif" alt=""/>
    </a>
   </div>
   <div class="spacer">&nbsp;</div>
   <c:set var="usuarioLogado"><mtw:out value="logado"/></c:set>
	<c:choose>
	 <c:when test="${usuarioLogado == 'S'}">
	  <mtw:i18n key="aplicacao.saudacao1"/><b><mtw:out value="nomeLogado"/></b><mtw:i18n key="aplicacao.saudacao2"/>
	  <mtw:hasAuthorization groups="admins">[<mtw:i18n key="aplicacao.admin"/>]</mtw:hasAuthorization>
	  <mtw:hasAuthorization groups="colaboradores">[<mtw:i18n key="aplicacao.colab"/>]</mtw:hasAuthorization>
	  <mtw:hasAuthorization groups="editores">[<mtw:i18n key="aplicacao.editor"/>]</mtw:hasAuthorization>
      <div id="listaFavoritos">
	   <a href="/games/favoritos-lista"><mtw:i18n key="jogosFavoritos.link"/></a>
	   <a href="/games/painel"><mtw:i18n key="painel.link"/></a>
      </div>
	  <a href="/games/index-logout"><img src="<mtw:contextPath/>/img/logoff.gif" alt="<mtw:i18n key="aplicacao.sair"/>"/></a>
	 </c:when>
	 <c:otherwise>
	  <a id="register_link" href="<mtw:contextPath/>/games/index-register"><mtw:i18n key="login.novo"/></a>
	  <div class="spacer">&nbsp;</div>
      <a id="login_link" href="#"><mtw:i18n key="aplicacao.naoLogado"/></a>
      <div id="login_form" style='display:none'>
       <div id="status" align="center" style="margin: 20px; width: 250px;text-align:center">
       <div id="loginTitle">
        <img src="/css/key.png" align="absmiddle"><mtw:i18n key="login.entrar"/>
       </div>
       <form id="login" action="javascript:alert('success!');">
        <div class="row">
         <label><mtw:i18n key="login.user"/></label>
         <input type="text" name="emaillg" size="15" maxlength="50"/>
        </div>
        <div class="row">
         <label><mtw:i18n key="login.pass"/></label>
         <input type="password" name="passwordlg" size="15" maxlength="50"/>
        </div>
        <div class="row">
         <label>&nbsp;</label>
         <input value="<mtw:i18n key="login.submit"/>" name="Login" id="submit" class="big" type="submit" />
        </div>
        <div id="ajax_loading">
         <img align="absmiddle" src="/css/clock.gif"><mtw:i18n key="login.processando"/>
        </div>
        <div class="row" style="text-align:center">
         <div id="login_response"><!-- spanner --></div>
        </div>
       </form>
      </div>
     </div>
	 </c:otherwise>
    </c:choose>
  </div>

 <div class="menuEsq">
  <div class="opcoes">
   <a href="http://feeds2.feedburner.com/Linuxgamescombr" target="_blank">
    <img alt="RSS" src="/img/rss.png" width="24"/>
    <mtw:i18n key="aplicacao.rss"/>
   </a>
  </div>
 </div>

 <div class="menuEsq">
  <div class="opcoes">
   <a href="http://twitter.com/linuxgamesbr" target="_blank">
    <img alt="RSS" src="/img/twitter.png" width="24"/>
    <mtw:i18n key="aplicacao.twitter"/>
   </a>
  </div>
 </div>

 <div class="menuEsq">
  <div class="opcoes">
   <font class="menuTitulo"><mtw:i18n key="menuEsq.titulo"/></font>
   <a href="/games/todos-jogos" ><mtw:i18n key="menuEsq.jogos"/></a>
   <a href="/games/todos-emuladores" ><mtw:i18n key="menuEsq.emu"/></a>
   <a href="/games/howto"><mtw:i18n key="menuEsq.howto"/></a>
   <a href="/games/todos-artigos"><mtw:i18n key="menuEsq.artigos"/></a>
   <%--
   <a href="/forums/list.games"><mtw:i18n key="menuEsq.forum"/></a>
    --%>
   <a href="/games/sugestao"><mtw:i18n key="menuEsq.sugestoes"/></a>
   <a href="/games/sobre"><mtw:i18n key="menuEsq.sobre"/></a>
  </div>
 </div>

 <div id="usuariosTop">
  <div class="titulo">
   <mtw:i18n key="usuarios.top"/>
  </div>
  <div class="linhaE">
  <c:forEach items="${topUsers}" var="usuario">
   <li class="">
    <c:out value="${usuario}"/>
   </li>
  </c:forEach>
  </div>
 </div>

 <div id="enquete">
  <form id="formEnquete" action="/games/enquete">
   <input type="hidden" name="idEnquete" id="idEnquete" value="<c:out value="${indexVO.enqueteVO.id}"/>"/>
   <div class="titulo">
  	 <c:out value="${indexVO.enqueteVO.titulo}"/>
   </div>
   <div class="linhaE">
    <input type="radio" name="optEnquete" id="optEnquete1" value="1"/>
    <c:out value="${indexVO.enqueteVO.opt1}"/>
   </div>
   <div class="linhaE">
    <input type="radio" name="optEnquete" id="optEnquete2" value="2"/>
    <c:out value="${indexVO.enqueteVO.opt2}"/>
   </div>
   <c:if test="${!empty indexVO.enqueteVO.opt3}">
    <div class="linhaE">
     <input type="radio" name="optEnquete" id="optEnquete3" value="3"/>
     <c:out value="${indexVO.enqueteVO.opt3}"/>
    </div>
   </c:if>
   <c:if test="${!empty indexVO.enqueteVO.opt4}">
    <div class="linhaE">
     <input type="radio" name="optEnquete" id="optEnquete4" value="4"/>
     <c:out value="${indexVO.enqueteVO.opt4}"/>
    </div>
   </c:if>
  <div class="spacer">&nbsp;</div>
   <div class="linhaC">
    <a href="javascript:void(0);" onclick="votar();" class="button" id="buttonVotar">&nbsp;<mtw:i18n key="enquete.votar"/>&nbsp;</a>
   </div>
<%--
   <div class="linhaC">
    <a href="javascript:void(0);" onclick="resultados();" class="button" id="buttonResultados">&nbsp;<mtw:i18n key="enquete.resultados"/>&nbsp;</a>
   </div>
 --%>
  <div class="spacer">&nbsp;</div>
  </form>
 </div>


 <div id="artigos">
  <div class="titulo">
   <mtw:i18n key="artigo.last"/>
  </div>
  <mtw:list value="artigos">
  <mtw:isEmpty>
   <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
  </mtw:isEmpty>
  <mtw:loop var="registro" counter="contador">
   <div class="linhaE">
    <a class="loginLink" href="/games/infoArtigo-artigo/<mtw:out value="id"/>"><mtw:out value="titulo"/></a>
   </div>
   </mtw:loop>
  </mtw:list>
 </div>

<%--
   <font class="menuTitulo"><mtw:i18n key="menuEsq.destaque.titulo "/> </font>
   <a href="/planeta.doom.action"><mtw:i18n key="menuEsq.destaque.doom"/></a>
   <a href="/planeta.quake.action"><mtw:i18n key="menuEsq.destaque.quake"/></a>
   <a href="/planeta.et.action"><mtw:i18n key="menuEsq.destaque.et"/></a>
 --%>

 <div class="menuEsq">
  <div class="opcoes">
   <font class="menuTitulo"><mtw:i18n key="menuEsq.emuladores"/></font>
   <a href="http://x.mame.net/" target="_BLANK">MAME</a>
   <a href="http://nestra.linuxgames.com/" target="_BLANK">NEStra</a>
   <a href="http://www.linuxgames.com/snes9express/ " target="_BLANK">Snes9express</a>
   <a href="http://freesci.linuxgames.com/" target="_BLANK">FreeSCI</a>
   <a href="http://glmame.linuxgames.com/" target="_BLANK">GLMame</a>
   <a href="http://www.scummvm.org/" target="_BLANK">SCUMM</a>
   <font class="menuTitulo"><mtw:i18n key="menuEsq.lgw"/></font>
   <a href="http://www.ubuntugames.org/" target="_BLANK">Ubuntu Games</a>
   <a href="http://www.linuxgames.com/" target="_BLANK">Linux Games</a>
   <a href="http://www.happypenguin.org/" target="_BLANK">Linux Game Tome</a>
   <a href="http://games.linux.sk/" target="_BLANK">Games for Linux</a>
   <a href="http://www.linux-gamers.net/" target="_BLANK">linuX-gamers.net</a>
   <a href="http://www.tuxgames.com/" target="_BLANK">TuxGames</a>
   <a href="http://en.wikipedia.org/wiki/Category:Linux_games" target="_BLANK">Linux Games @ Wikipedia</a>
   <a href="http://linuxemu.linuxgames.com/" target="_BLANK">Linux Emu </a>
   <a href="http://www.idsoftware.com/" target="_BLANK">id Software</a>
   <a href="http://www.epicgames.com/" target="_BLANK">Epic Games</a>
  </div>
 </div>

</div>