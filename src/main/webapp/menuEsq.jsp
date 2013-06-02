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
	  <a href="/user/logout.games"><img src="<mtw:contextPath/>/img/logoff.gif" alt="<mtw:i18n key="aplicacao.sair"/>"/></a>
	  <mtw:hasAuthorization groups="admins">[<mtw:i18n key="aplicacao.admin"/>]</mtw:hasAuthorization>
	  <mtw:hasAuthorization groups="colaboradores">[<mtw:i18n key="aplicacao.colab"/>]</mtw:hasAuthorization>
	  <mtw:hasAuthorization groups="editores">[<mtw:i18n key="aplicacao.editor"/>]</mtw:hasAuthorization>
      <div id="listaFavoritos">
	   <a href="/games/favoritos-lista"><mtw:i18n key="jogosFavoritos.link"/></a>
	   <a href="/games/painel"><mtw:i18n key="painel.link"/></a>
      </div>
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
         <input type="text" name="emaillg" size="10" maxlength="50"/>
        </div>
        <div class="row">
         <label><mtw:i18n key="login.pass"/></label>
         <input type="password" name="passwordlg" size="10" maxlength="50"/>
        </div>
        <div class="row">
         <label>&nbsp;</label>
         <input value="<mtw:i18n key="login.submit"/>" name="Login" id="submit" class="big" type="submit" />
        </div>
        <div id="ajax_loading">
         <img align="absmiddle" src="/css/spinner.gif">&nbsp;<mtw:i18n key="login.processando"/>
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

 <br/>
 <div class="c">
  <script type="text/javascript">
  google_ad_client = "pub-3816383189082763";
  google_ad_slot = "6332499899";
  google_ad_width = 120;
  google_ad_height = 240;
</script>
<script type="text/javascript" src="http://pagead2.googlesyndication.com/pagead/show_ads.js"></script>
</div>


</div>