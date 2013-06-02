<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

<div id="rightcontent">
<%--
 <div class="jd_news_scroll" id="elm1">
  <ul style="top: 0pt;">
  <c:forEach items="${novidades}" var="novidade">
   <li class="">
    <span style="font-weight: bold;">»<fmt:formatDate pattern="dd/MM/yyyy" value="${novidade.dataPublic}"/>»</span>
   <a href="<c:out value="${novidade.link}"/>" target="_BLANK"><c:out value="${novidade.texto}"/></a>
   </li>
  </c:forEach>
  </ul>
 </div>

 <div id="enquete">
  <form id="formEnquete" action="/enquete.action">
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
    <a href="javascript:void(0);" onclick="resultados();" class="button" id="buttonResultados">&nbsp;<mtw:i18n key="enquete.resultados"/>&nbsp;</a>
   </div>
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
    <a class="loginLink" href="infoArtigo.artigo.action?id=<mtw:out value="id"/>"><mtw:out value="titulo"/></a>
   </div>
   </mtw:loop>
  </mtw:list>
 </div>
 --%>
</div>