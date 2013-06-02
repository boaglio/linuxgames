<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

<c:import url="cabecalho.jsp"/>
<c:import url="menuTopo.jsp"/>
<c:import url="menuEsq.jsp"/>

<!-- artigo.jsp -->

<c:set var="usuarioLogado"><mtw:out value="logado"/></c:set>
<div id="artigo">
 <div class="spacer">&nbsp;</div>
 <div class="c">
  <mtw:i18n key="artigo.data"/>:&nbsp;<b><mtw:out value="dataPublic"/></b>&nbsp;
  <c:choose><c:when test="${notaGeral>0}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
  <c:choose><c:when test="${notaGeral>2}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
  <c:choose><c:when test="${notaGeral>4}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
  <c:choose><c:when test="${notaGeral>6}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
  <c:choose><c:when test="${notaGeral>8}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
 </div>
 <div class="row">
  <h3><mtw:out value="titulo"/></h3>
 </div>
 <div class="row">
   <mtw:out value="texto"/>
 </div>
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
         <c:set var="notaArtigo"><mtw:out value="nota"/></c:set>
	     <c:choose><c:when test="${notaArtigo>0}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
	     <c:choose><c:when test="${notaArtigo>2}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
	     <c:choose><c:when test="${notaArtigo>4}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
	     <c:choose><c:when test="${notaArtigo>6}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
	     <c:choose><c:when test="${notaArtigo>8}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
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
	 </mtw:loop>
	</div>
  </mtw:list>
  <div id="postReview">
	<c:choose>
	 <c:when test="${usuarioLogado == 'S'}">
	  <c:if test="${(jaVotou == 'N')}">
       <form id="votoReviewFrm" action="/games/review-artigo" method="POST">
       <input type="hidden" name="idUsuario" id="idUsuario" value="<mtw:out value="idUsuario"/>"/>
       <input type="hidden" name="id" id="id" value="<mtw:out value="id"/>"/>
       <div class="spacer">&nbsp;</div>
  	   <font class="jb"><mtw:i18n key="artigo.review.comentarios"/></font>
       <div class="spacer">&nbsp;</div>
  	    <mtw:textarea name="comentario" id="comentario" cols="26" rows="2"></mtw:textarea>
        <div class="spacer">&nbsp;</div>
  	     <font class="jb"><mtw:i18n key="artigo.nota"/></font>
	     <select name="idVoto" id="idVoto" >
		  <option value="0"><mtw:i18n key="artigo.avaliar"/></option>
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

	 </c:otherwise>
	</c:choose>
   </div>
  </div>
 </div>
</div>

<c:import url="menuDirArtigo.jsp"/>
<c:import url="rodape.jsp"/>

</body>
</html>
