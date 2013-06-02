<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<c:import url="lang-cookie.jsp"/>
<c:import url="cabecalho.jsp"/>
<c:import url="menuTopo.jsp"/>
<c:import url="menuEsq.jsp"/>

<!-- jogo.jsp -->

<c:set var="usuarioLogado"><mtw:out value="logado"/></c:set>
<c:set var="buyNow"><mtw:out value="siteCompra"/></c:set>
<div id="jogo">
 <div class="spacer">&nbsp;</div>
 <div id="votoJogo">
  <c:set var="usuarioLogado"><mtw:out value="logado"/></c:set>
  <c:if test="${usuarioLogado == 'S'}">
   <div class="row">
    <span class="c">
     <form method="post" action="/games/favoritos-jogoFavorito" id="formFavorito">
      <font class="nomeJogo"><mtw:i18n key="jogosFavoritos.combo"/></font>
      <mtw:select list="simOuNao" name="jogoFavorito" id="jogoFavorito" extra=" onChange=alteraJogoFavorito()" />
      <mtw:input name="id" type="hidden"/>
     </form>
    </span>
   </div>
  </c:if>
  <div class="row">
   <span class="left">
    <font class="nomeJogo"><mtw:out value="nome"/></font>
    <c:if test="${(!(empty buyNow)) &&(buyNow != '<!-- siteCompra has null value or cannot be found! -->')}">
     &nbsp;<a href="<c:out value="${buyNow}"/>"><img src="/img/compreAgora.gif" alt=""/></a>
    </c:if>
    <c:import url="/lang-links.jsp">
     <c:param name="id"><mtw:out value="id"/></c:param>
     <c:param name="url">/games/infoJogo-jogo</c:param>
    </c:import>
    <img src="/img/nothing.gif" alt="" height="20" width="20"/>
    <a href="/games/infoJogo-ajuda/<mtw:out value="id"/>/<mtw:isLocale value="pt_BR">pt</mtw:isLocale><mtw:isLocale value="en">en</mtw:isLocale>" alt="<mtw:i18n key="jogo.alterarPopup"/>" class="bandeiras"
     onmouseover="return overlib('<mtw:i18n key="jogo.alterarPopup"/>',CAPTION,'<mtw:i18n key="jogo.alterarPopupTitulo"/>');" onmouseout="return nd();">
     <img src="/img/smiley_help.gif" width="40" height="42"  />
    </a>
   </span>
   <span class="right">
    <c:choose><c:when test="${notaGeral>0}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
    <c:choose><c:when test="${notaGeral>2}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
    <c:choose><c:when test="${notaGeral>4}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
    <c:choose><c:when test="${notaGeral>6}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
    <c:choose><c:when test="${notaGeral>8}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
   </span>
  </div>
  <div class="spacer">&nbsp;</div>
 </div>

<div id="headerJogo">
  <ul>
    <li<mtw:if test="aba1" value="S"> id="atual"</mtw:if>><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:i18n key="jogo.aba.resumo"/></a></li>
    <li<mtw:if test="aba2" value="S"> id="atual"</mtw:if>><a href="/games/infoJogo-review/<mtw:out value="id"/>"><mtw:i18n key="jogo.aba.review"/></a></li>
    <li<mtw:if test="aba3" value="S"> id="atual"</mtw:if>><a href="/games/infoJogo-inst/<mtw:out value="id"/>"><mtw:i18n key="jogo.aba.inst"/></a></li>
    <li<mtw:if test="aba4" value="S"> id="atual"</mtw:if>><a href="/games/infoJogo-dica/<mtw:out value="id"/>"><mtw:i18n key="jogo.aba.dica"/></a></li>
    <li<mtw:if test="aba5" value="S"> id="atual"</mtw:if>><a href="/games/infoJogo-tela/<mtw:out value="id"/>"><mtw:i18n key="jogo.aba.tela"/></a></li>
    <li<mtw:if test="aba6" value="S"> id="atual"</mtw:if>><a href="/games/infoJogo-download/<mtw:out value="id"/>"><mtw:i18n key="jogo.aba.download"/></a></li>
  </ul>
</div>
 <div id="abaJogo">
  <%-- aba do jogo  --%>
  <mtw:if test="aba1" value="S">
  <h2><mtw:i18n key="jogo.aba.resumo"/></h2>
  <div class="spacer">&nbsp;</div>
  <label><mtw:i18n key="jogo.tipo"/></label><mtw:out value="tipo"/>
  <div class="spacer">&nbsp;</div>
  <label><mtw:i18n key="jogo.licenca"/></label><mtw:out value="licenca"/>
  <div class="spacer">&nbsp;</div>
  <label><mtw:i18n key="jogo.aberto"/></label><mtw:out value="aberto"/>
  <div class="spacer">&nbsp;</div>
  <label><mtw:i18n key="jogo.jogaEmRede"/></label><mtw:out value="jogaEmRede"/>
  <div class="spacer">&nbsp;</div>
  <label><mtw:i18n key="jogo.precisa3d"/></label><mtw:out value="precisa3d"/>
  <div class="spacer">&nbsp;</div>
  <label><mtw:i18n key="jogo.temSom"/></label><mtw:out value="temSom"/>
  <div class="spacer">&nbsp;</div>
  <label><mtw:i18n key="jogo.consoleOuX11"/></label><mtw:out value="consoleOuX11"/>
  <div class="spacer">&nbsp;</div>
  <label><mtw:i18n key="jogo.siteOficial"/></label><a href="<mtw:out value="siteOficial"/>" target="_BLANK"><mtw:out value="siteOficial"/></a>
  <div class="spacer">&nbsp;</div>
  <label><mtw:i18n key="jogo.descricao"/></label><mtw:out value="descricao"/>
  <div class="spacer">&nbsp;</div>
  <label><mtw:i18n key="jogo.votos"/></label><mtw:out value="votos"/>
  <div class="spacer">&nbsp;</div>
  <label><mtw:i18n key="jogo.notaGeral"/></label><fmt:formatNumber value="${notaGeral}" maxFractionDigits="2"/>
  <div class="spacer">&nbsp;</div>
  <label><mtw:i18n key="jogo.fabricante"/></label><mtw:out value="fabricante"/>
  <div class="spacer">&nbsp;</div>
  </mtw:if>
  <%-- aba de reviews  --%>

  <mtw:if test="aba2" value="S">
   <h2><mtw:i18n key="jogo.aba.review"/></h2>
   <div class="spacer">&nbsp;</div>
   <mtw:list value="reviews">
    <div id="reviews">
	 <mtw:isEmpty>
 	  <div class="spacer">&nbsp;</div>
	  <mtw:i18n key="aplicacao.resultado.nenhumaReview"/>
	  <div class="spacer">&nbsp;</div>
	 </mtw:isEmpty>
	 <mtw:loop  var="registro">
      <div style="position:relative;width:400px" >
       <div class="tcomm_author">
        <div class="comm_rating">
         <c:set var="notaJogo"><mtw:out value="nota"/></c:set>
	     <c:choose><c:when test="${notaJogo>0}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
	     <c:choose><c:when test="${notaJogo>2}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
	     <c:choose><c:when test="${notaJogo>4}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
	     <c:choose><c:when test="${notaJogo>6}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
	     <c:choose><c:when test="${notaJogo>8}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
        </div>
        <div class="avatar"><img src="<mtw:contextPath/>/img/review_avatar.gif" class="avatar" alt="avatar"/></div>
        <strong><mtw:out value="usuario.nome"/></strong><br/>
       </div>
       <div class="tcomment">
        <div class="tcomm_top">
         <div class="tcomm_topright"></div>
        </div>
        <div class="tcomm_text">
         <div style="overflow:hidden">
          <mtw:out value="comentario"/>
         </div>
        </div>
       </div>
      </div>
	 </mtw:loop>
    </div>
	</mtw:list>
	<c:choose>
	 <c:when test="${usuarioLogado == 'S'}">
	  <c:if test="${(jaVotou == 'N')}">
       <form id="votoReviewFrm" action="/games/review-jogo" method="POST">
       <input type="hidden" name="idUsuario" id="idUsuario" value="<mtw:out value="idUsuario"/>"/>
       <input type="hidden" name="id" id="id" value="<mtw:out value="id"/>"/>
       <div class="spacer">&nbsp;</div>
  	   <font class="jb"><mtw:i18n key="jogo.review.comentarios"/></font>
       <div class="spacer">&nbsp;</div>
  	   <mtw:textarea name="comentario" id="comentario" cols="26" rows="2"></mtw:textarea>
       <div class="spacer">&nbsp;</div>
  	   <font class="jb"><mtw:i18n key="jogo.nota"/></font>
	   <select name="idVoto" id="idVoto" >
		<option value="0"><mtw:i18n key="jogo.avaliar"/></option>
		<option value="2">1</option>
		<option value="4">2</option>
		<option value="6">3</option>
		<option value="8">4</option>
		<option value="10">5</option>
	   </select>
       <div class="spacer">&nbsp;</div>
       <a href="javascript:void(0);" onclick="fazReview();" class="button" id="buttonResultados">&nbsp;<mtw:i18n key="aplicacao.ok"/>&nbsp;</a>
       <div class="spacer">&nbsp;</div>
       </form>
      </c:if>
	 </c:when>
	 <c:otherwise>
 	  <div class="spacer">&nbsp;</div>
	  <a href="<mtw:i18n key="menuTopo.href2"/>"><mtw:i18n key="jogo.review.nao"/></a>
	  <div class="spacer">&nbsp;</div>
	 </c:otherwise>
	</c:choose>
  </mtw:if>
  <%-- aba de instalacao  --%>
  <mtw:if test="aba3" value="S">
   <h2><img src="<mtw:contextPath/>/img/books.gif" alt="<mtw:i18n key="jogo.libs"/>"/><mtw:i18n key="jogo.libs"/></h2>
   <mtw:list value="libs">
	 <mtw:isEmpty>
	  <div class="c">
	   <mtw:i18n key="aplicacao.resultado.nenhumaLib"/>
	  </div>
	 </mtw:isEmpty>
 	 <mtw:loop  var="registro">
 	  <div class="libs">
 	   <a href="<mtw:out value="biblioteca.link"/>" onmouseover="return overlib('<mtw:out value="biblioteca.descricao"/>');" onmouseout="return nd();"><mtw:out value="biblioteca.nome"/></a>
 	  </div>
	 </mtw:loop>
   </mtw:list>
    <h2><mtw:i18n key="jogo.inst"/></h2>
	<mtw:list value="roteiros">
    <div id="reviews">
	 <mtw:isEmpty>
 	  <div class="spacer">&nbsp;</div>
	  <mtw:i18n key="jogo.install.nao"/>
	  <div class="spacer">&nbsp;</div>
	 </mtw:isEmpty>
	 <mtw:loop  var="registro">
      <div style="position:relative;">
       <div class="tcomm_author">
        <div class="comm_rating">
         <a href="<mtw:out value="distro.siteOficial"/>" onmouseover="return overlib('<img src=\'/img/<mtw:out value="distro.linkLogo"/>\'/>',FULLHTML);" onmouseout="return nd();"><mtw:out value="distro.nome"/></a>
        </div>
        <div class="avatar"><img src="<mtw:contextPath/>/img/review_avatar.gif" class="avatar" alt="avatar"/></div>
        <strong><mtw:out value="usuario.nome"/></strong><br/>
       </div>
       <div class="tcomment">
        <div class="tcomm_top">
         <div class="tcomm_topright"></div>
        </div>
        <div class="tcomm_text">
         <div style="overflow:hidden;white-space:pre;background:#ffffff;filter:alpha(opacity=75);-moz-opacity: 0.75">
          <mtw:out value="descricao"/>
         </div>
        </div>
       </div>
	  </div>
	 </mtw:loop>
	</mtw:list>
    <c:choose>
  	 <c:when test="${usuarioLogado == 'S'}">
  	  <div class="spacer">&nbsp;</div>
  	  <div class="c">
  	   <a href="javascript:mudaDIV(document.getElementById('novaInst'))" alt=""><mtw:i18n key="jogo.install.contrib"/></a>
  	  </div>
  	  <div id="novaInst" style="visibility:hidden;display:none;">
  	   <form name="formularioRotInst"  id="formularioRotInst" action="/games/infoJogo-atualizaRoteiro" method="post">
  	    <input type="hidden" name="id" value="<mtw:out value="id"/>"/>
  	    <div class="spacer">&nbsp;</div>
		<mtw:i18n key="roteiroInstalacaoJogo.distro"/>:
		<mtw:select name="distro_id" id="distro_id" list="DISTROS" />
		<div class="spacer">&nbsp;</div>
		<mtw:i18n key="roteiroInstalacaoJogo.descricao"/>:
		<mtw:textarea name="descricao" id="descricao" cols="40" rows="10"/>
		<div class="spacer">&nbsp;</div>
		<input type="button" value="Enviar roteiro" onclick="javascript:if (enviaNovoRoteiro()) { document.getElementById('formularioRotInst').submit(); } "/>
		<div class="spacer">&nbsp;</div>
	   </form>
  	  </div>
	 </c:when>
	 <c:otherwise>

	 </c:otherwise>
	</c:choose>
   </div>
  </mtw:if>
  <%-- aba de dicas  --%>
  <mtw:if test="aba4" value="S">
    <h2><mtw:i18n key="jogo.dicas"/></h2>
	<mtw:list value="dicas">
    <div id="reviews">
	 <mtw:isEmpty>
 	  <div class="spacer">&nbsp;</div>
	  <mtw:i18n key="jogo.dicas.nao"/>
	  <div class="spacer">&nbsp;</div>
	 </mtw:isEmpty>
	 <mtw:loop  var="registro">
      <div style="position:relative;" >
       <div class="tcomm_author">
        <div class="comm_rating">
         <c:set var="tipoDica"><mtw:out value="tipo"/></c:set>
 	     <c:choose>
		  <c:when test="${tipoDica == 1}">
		   <img src="<mtw:contextPath/>/img/dica-tip.gif" alt=""/>
		  </c:when>
		  <c:when test="${tipoDica == 2}">
		   <img src="<mtw:contextPath/>/img/dica-video.gif" alt=""/>
		  </c:when>
		  <c:when test="${tipoDica == 3}">
		   <img src="<mtw:contextPath/>/img/dica-download.png" alt=""/>
		  </c:when>
		 </c:choose>
        </div>
        <div class="avatar"><img src="<mtw:contextPath/>/img/review_avatar.gif" class="avatar" alt="avatar"/></div>
        <strong><mtw:out value="usuario.nome"/></strong><br/>
       </div>
       <div class="tcomment">
        <div class="tcomm_top">
         <div class="tcomm_topright"></div>
        </div>
        <div class="tcomm_text">
         <div style="overflow:hidden;white-space:pre;background:#ffffff;filter:alpha(opacity=75);-moz-opacity: 0.75">
          <a href="<mtw:out value="link"/>"><mtw:out value="texto"/></a>
         </div>
        </div>
       </div>
	  </div>
	 </mtw:loop>
	</mtw:list>

    <c:choose>
  	 <c:when test="${usuarioLogado == 'S'}">
  	  <div class="spacer">&nbsp;</div>
  	  <div class="c">
   	   <a href="javascript:mudaDIV(document.getElementById('novaInst'))" alt=""><mtw:i18n key="jogo.dicas.contrib"/></a>
   	  </div>
  	  <div id="novaInst" style="visibility:hidden;display:none;">
  	   <form name="formularioDica"  id="formularioDica" action="/games/infoJogo-atualizaDica" method="post">
  	    <input type="hidden" name="id" value="<mtw:out value="id"/>"/>
  	    <div class="spacer">&nbsp;</div>
		<mtw:i18n key="jogo.tipo"/>:
		<mtw:select name="tipo"  id="tipo" list="tiposDeTexto" />
		<div class="spacer">&nbsp;</div>
		<mtw:i18n key="textoDeJogo.texto"/>:
		<mtw:textarea name="descricao" id="descricao" cols="40" rows="10"/>
		<div class="spacer">&nbsp;</div>
		<mtw:i18n key="textoDeJogo.link"/>:
		<mtw:inputText name="link" id="link" size="60"/>
		<div class="spacer">&nbsp;</div>
		<input type="button" value="Enviar" onclick="javascript:if (enviaNovaDica()) { document.getElementById('formularioDica').submit(); } "/>
		<div class="spacer">&nbsp;</div>
	   </form>
  	  </div>
	 </c:when>
	 <c:otherwise>

	 </c:otherwise>
	</c:choose>
   </div>
  </mtw:if>
  <%-- aba de telas  --%>
  <mtw:if test="aba5" value="S">
    <h2><mtw:i18n key="jogo.aba.tela"/></h2>
    <mtw:list value="telas">
    <div class="c">
	 <mtw:isEmpty>
	  <div class="spacer">&nbsp;</div>
	  <mtw:i18n key="aplicacao.resultado.nenhumaTela"/>
	  <div class="spacer">&nbsp;</div>
	 </mtw:isEmpty>
 	 <mtw:loop  var="registro">
 	  <a href="javascript:void(0)" onMouseOver="return overlib('<mtw:out value="descricao"/>',BORDER, 1,TEXTSIZE, 1)" onMouseOut="return nd();"><img class="screenshot" src='<mtw:contextPath/>/img/games/<mtw:out value="nome"/>' alt=""/></a>
	 </mtw:loop>
    </div>
    </mtw:list>
   </mtw:if>
   <%-- aba de download  --%>
   <mtw:if test="aba6" value="S">
    <h2><mtw:i18n key="jogo.aba.download"/></h2>
    <mtw:list value="links">
    <div id="reviews">
	 <mtw:isEmpty>
      <div class="spacer">&nbsp;</div>
	  <mtw:i18n key="aplicacao.resultado.nenhumDownload"/>
      <div class="spacer">&nbsp;</div>
	 </mtw:isEmpty>
  	  <ul class="download">
       <mtw:loop var="registro">
 	    <li>
 	     <fmt:formatDate value="${registro.dataLancamento}" pattern="dd/MM/yyyy"/>
 	      [ <a href="<mtw:out value="link"/>"><mtw:out value="release"/></a> ]
 	      <c:if test="${!(empty registro.obs)}">
 	      (<a href="<mtw:out value="link"/>"><mtw:out value="obs"/></a>)
 	      </c:if>
 	      <a href="<mtw:out value="link"/>"><img src="<mtw:contextPath/>/img/download.gif" alt=""/></a>
 	    </li>
 	   </mtw:loop>
 	  </ul>
     </div>
    </mtw:list>
  </mtw:if>
 </div>
 <%-- top list jogos --%>
 <div class="spacer">&nbsp;</div>
  <div id="lista">
   <mtw:list value="jogosTop">
    <h2><mtw:i18n key="jogo.top"/></h2>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a>&nbsp;&nbsp;(<mtw:out value="hits"/>)</li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 <%-- top list emuladores --%>
 <div class="spacer">&nbsp;</div>
  <div id="lista">
   <mtw:list value="emuladoresTop">
    <h2><mtw:i18n key="emulador.top"/></h2>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a>&nbsp;&nbsp;(<mtw:out value="hits"/>)</li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
</div>


 </div>
</div>
<c:import url="rodape.jsp"/>
</body>
</html>
