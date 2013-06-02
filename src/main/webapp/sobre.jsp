<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

<c:import url="cabecalho.jsp"/>
<c:import url="menuTopo.jsp"/>
<c:import url="menuEsq.jsp"/>
 
<div id="sugestao">
 <div class="c">
  <h3> <mtw:i18n key="sobre.titulo"/> </h3>
 </div> 
 <div class="spacer">
  &nbsp;
 </div> 
 <div class="c">
  <img src="<mtw:contextPath/>/img/linux-gaming-console.jpg" alt=""/>
 </div>
 <div class="spacer">
  &nbsp;
 </div> 
 <div class="c">
  <mtw:i18n key="sobre.site"/>
 </div>  
 <div class="c">
  <mtw:i18n key="sobre.site2"/>
 </div>  
 <div class="spacer">
  &nbsp;
 </div> 
 <div class="c">
  <img src="<mtw:contextPath/>/img/mysql.gif" alt="MySQL"/>
  <img src="<mtw:contextPath/>/img/mentawai.png" alt="Mentawai Web Framework"/>
  <img src="<mtw:contextPath/>/img/tomcat.gif" alt="Apache Tomcat"/>
 </div> 
 <div class="spacer">
  &nbsp;
 </div>   
 <div class="c">
  <mtw:i18n key="sobre.lg1"/>
 </div>  
 <div class="spacer">
  &nbsp;
 </div> 
 <div class="c">
   <img src="<mtw:contextPath/>/img/hard-tux.gif" alt=""/>
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
