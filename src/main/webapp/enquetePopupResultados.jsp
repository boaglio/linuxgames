<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

 <c:set var="total"><mtw:out value="qt"/></c:set>
 <c:set var="votosOp1"><c:out value="${100*qt1/total}"/></c:set>
 <c:set var="votosOp2"><c:out value="${100*qt2/total}"/></c:set>
 <c:set var="votosOp3"><c:out value="${100*qt3/total}"/></c:set>
 <c:set var="votosOp4"><c:out value="${100*qt4/total}"/></c:set>

<html>
 <head>
  <title><mtw:i18n key="enquete.titulo"/></title>
  <style type="text/css"> @import "<mtw:contextPath/>/css/bar.css";</style>
 </head>
 <body>
   <div class="titulo">
  	 <c:out value="${titulo}"/>
   </div>
  <div class="c">
   <div class="linha">
     <c:out value="${opt1}"/>
     <div class="graph">
       <strong class="bar" style="width:<fmt:formatNumber value="${votosOp1}" maxFractionDigits="0"/>%;"><fmt:formatNumber value="${votosOp1}" maxFractionDigits="2"/>%</strong>
     </div>
   </div>
   <div class="linha">
     <c:out value="${opt2}"/>
     <div class="graph">
       <strong class="bar" style="width:<fmt:formatNumber value="${votosOp2}" maxFractionDigits="0"/>%;"><fmt:formatNumber value="${votosOp2}" maxFractionDigits="2"/>%</strong>
     </div>
   </div>

   <c:if test="${!empty opt3}">
    <div class="linha">
     <c:out value="${opt3}"/>
     <div class="graph">
      <strong class="bar" style="width:<fmt:formatNumber value="${votosOp3}" maxFractionDigits="0"/>%;"><fmt:formatNumber value="${votosOp3}" maxFractionDigits="2"/>%</strong>
     </div>
    </div>
   </c:if>
   <c:if test="${!empty opt4}">
    <div class="linha">
     <c:out value="${opt4}"/>
     <div class="graph">
      <strong class="bar" style="width:<fmt:formatNumber value="${votosOp4}" maxFractionDigits="0"/>%;"><fmt:formatNumber value="${votosOp4}" maxFractionDigits="2"/>%</strong>
     </div>
    </div>
   </c:if>
   <div class="linhaC">
     <mtw:out value="qt"/>&nbsp;<mtw:i18n key="enquete.votos"/>
   </div>
   <div class="linhaC">
     <a href="javascript:self.close()"><img width="120" border="0" height="25" src="/img/linuxgames-fechar.gif" alt="[X]"/></a>
   </div>
  </div>
  </body>
 </html>