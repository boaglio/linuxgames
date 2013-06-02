<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

<div id="rightcontent">

 <div id="artigos">
  <div class="titulo">
   <mtw:i18n key="artigo.lista"/>
  </div>
  <mtw:list value="artigos">
  <mtw:isEmpty>
   <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
  </mtw:isEmpty>
  <mtw:loop var="registro" counter="contador">
   <div class="linhaE">
    <a class="loginLink" href="/games/info-artigo/<mtw:out value="id"/>"><mtw:out value="titulo"/></a>
   </div>
   </mtw:loop>
  </mtw:list>
 </div>
</div>