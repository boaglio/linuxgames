<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

<c:import url="cabecalho.jsp"/>
<c:import url="menuTopo.jsp"/>
<c:import url="menuEsq.jsp"/>

<div id="sugestao">
 <div class="c">
  <h3> <mtw:i18n key="jogosFavoritos.title"/> </h3>
 </div>
 <div class="spacer">
  &nbsp;
 </div>
 <div class="c">
   <img src="<mtw:contextPath/>/img/tux_gaming_md_wht.gif" width="120" height="92">
 </div>
 <div class="spacer">
  &nbsp;
 </div>
 <div class="c">
	 <mtw:list value="jogosFavoritos">
	  <mtw:isEmpty>
	   <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
	  </mtw:isEmpty>
	   <ul style="list-style:none;list-style-position:outside">
	    <mtw:loop var="linha">
	     <a href="/games/infoJogo-jogo/<c:out value="${linha.jogo.id}"/>">
	      <li><c:out value="${linha.jogo.nome}"/></li>
	     </a>
	    </mtw:loop>
	   </ul>
	  </mtw:list>
 </div>
 <div class="spacer">
  &nbsp;
 </div>
 <div class="c">
  <h3> <mtw:i18n key="emuladoresFavoritos.title"/> </h3>
 </div>
 <div class="c">
   <img src="<mtw:contextPath/>/img/tux_gaming_md_wht.gif" width="120" height="92">
 </div>
 <div class="spacer">
  &nbsp;
 </div>
  <div class="c">
	 <mtw:list value="emuladoresFavoritos">
	  <mtw:isEmpty>
	   <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
	  </mtw:isEmpty>
	   <ul style="list-style:none;list-style-position:outside">
	    <mtw:loop var="linha">
	     <a href="/games/infoEmulador-emulador/<c:out value="${linha.emulador.id}"/>">
	      <li><c:out value="${linha.emulador.nome}"/></li>
	     </a>
	    </mtw:loop>
	   </ul>
	  </mtw:list>
  </div>
 </div>

 <div class="spacer">
  &nbsp;
 </div>
 <div class="spacer">
  &nbsp;
 </div>
 <div class="spacer">
  &nbsp;
 </div>
 <div class="spacer">
  &nbsp;
 </div>
 <div class="spacer">
  &nbsp;
 </div>
</div>
<c:import url="menuDir.jsp"/>
<c:import url="rodape.jsp"/>

</body>
</html>
