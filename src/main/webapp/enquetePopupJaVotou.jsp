<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

<html>
 <head>
  <title><mtw:i18n key="enquete.voto.javotou"/></title>
 <style type="text/css"> @import "<c:out value="${param.base}"/>css/bar.css";</style>  
 </head>
 <body>
  <br/><br/><br/>
  <center>
   <h4 style="color:#f00">
    <mtw:i18n key="enquete.voto.javotou"/>
   </h4>
   <br/><br/><br/><br/>
   <a href="javascript:self.close()"><img width="120" border="0" height="25" src="<mtw:contextPath/>/img/linuxgames-fechar.gif"></a>
  </center>
 </body>
</html>
