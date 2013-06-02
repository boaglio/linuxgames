<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

<c:import url="/cabecalho.jsp"/>
<c:import url="/menuTopo.jsp"/>
<c:import url="/menuEsq.jsp"/>

 <div id="sugestao">
  <div class="c">
   <h3> <mtw:i18n key="artigos.titulo"/> </h3>
  </div>
  <mtw:list value="artigos">
  <mtw:isEmpty>
   <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
  </mtw:isEmpty>
  <mtw:loop var="registro" counter="contador">
   <div class="linhaE">
    <a class="loginLink" href="<mtw:contextPath/>/games/infoArtigo-artigo/<mtw:out value="id"/>"><mtw:out value="titulo"/></a>
    <img src="/img/artigo.gif" alt="" width="18" height="18"/><fmt:formatDate value="${registro.dataPublic}" pattern="dd/MM/yyyy"/>
    &nbsp;
    <c:choose><c:when test="${registro.notaGeral>0}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
    <c:choose><c:when test="${registro.notaGeral>2}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
    <c:choose><c:when test="${registro.notaGeral>4}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
    <c:choose><c:when test="${registro.notaGeral>6}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
    <c:choose><c:when test="${registro.notaGeral>8}"><img src="<mtw:contextPath/>/img/star_on.gif" class="star" alt=""/></c:when><c:otherwise><img src="<mtw:contextPath/>/img/star_off.gif" class="star" alt=""/></c:otherwise></c:choose>
   </div>
   </mtw:loop>
  </mtw:list>

 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>

  <div class="c">
   <h3> <mtw:i18n key="artigosExternos.titulo"/> </h3>
  </div>
  <mtw:list value="artigosExternos">
  <mtw:isEmpty>
   <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
  </mtw:isEmpty>
  <mtw:loop var="registro" counter="contador">
   <div class="linhaE">
    <a class="loginLink" href="<mtw:out value="link"/>" alt="_BLANK"><mtw:out value="titulo"/></a>
    <img src="<mtw:contextPath/>/img/artigo.gif" alt="" width="18" height="18"/><mtw:out value="fonte"/>
    &nbsp;
   </div>
   </mtw:loop>
  </mtw:list>


 </div>

 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
 <div class="spacer">&nbsp;</div>
</div>
<c:import url="/menuDir.jsp"/>
<c:import url="/rodape.jsp"/>

</body>
</html>
