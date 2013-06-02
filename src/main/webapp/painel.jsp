<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

<c:import url="/cabecalho.jsp"/>
<c:import url="/menuTopo.jsp"/>
<c:import url="/menuEsq.jsp"/>

<div id="sugestao">
  <h3>
   <img src="<mtw:contextPath/>/img/control-panel.png" width="48" height="48" alt=""/>
   <mtw:i18n key="painel.link"/>
  </h3>
 <div class="spacer">
  &nbsp;
 </div>
 <div class="row">
  <span class="label2">
    <mtw:i18n key="painel.email"/>:
  </span>
  <span class="form2">
    <mtw:out value="usuario.email"/>
  </span>
 </div>
 <div class="row">
  <span class="label2">
   <mtw:i18n key="painel.nrJogosContribuidos"/>:
  </span>
  <span class="form2">
   <mtw:out value="usuario.nrJogosContribuidos"/>
  </span>
 </div>
 <div class="row">
  <span class="label2">
   <mtw:i18n key="painel.nrNoticiasContribuidas"/>:
  </span>
  <span class="form2">
   <mtw:out value="usuario.nrNoticiasContribuidas"/>
  </span>
 </div>
 <div class="row">
  <span class="label2">
   <mtw:i18n key="painel.nrEmuladoresContribuidos"/>:
  </span>
  <span class="form2">
   <mtw:out value="usuario.nrEmuladoresContribuidos"/>
  </span>
 </div>
 <div class="row">
  <span class="label2">
   <mtw:i18n key="painel.totalContribuicoes"/>:
  </span>
  <span class="form2">
   <mtw:out value="usuario.totalContribuicoes"/>
  </span>
 </div>
 <div class="row">
  <span class="label2">
   <mtw:i18n key="painel.rank"/>:
  </span>
  <span class="form2">
   <mtw:out value="usuarioPosicao"/>
  </span>
 </div>

 <div class="spacer">
  &nbsp;
 </div>
</div>

<c:import url="/rodape.jsp"/>

</body>
</html>
