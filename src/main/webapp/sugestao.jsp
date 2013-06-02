
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

<c:import url="cabecalho.jsp"/>
<c:import url="menuTopo.jsp"/>
<c:import url="menuEsq.jsp"/>

 <form action="/games/sugestao-enviar" method="POST" id="sugestaoForm">
<div id="sugestao">
 <div class="spacer">
  &nbsp;
 </div>
 <div class="c">
   <img src="<mtw:contextPath/>/img/email.jpg" width="150" height="150" alt=""/>
 </div>
 <div class="spacer">
  &nbsp;
 </div>
  <c:choose>
   <c:when test="${emailEnviado=='ok'}">
     <div class="c">
      <mtw:i18n key="sugestoes.emailEnviado1"/>
	 </div>
	 <div class="spacer">
	  &nbsp;
	 </div>
	 <div class="c">
      <mtw:i18n key="sugestoes.emailEnviado2"/>
	 </div>
	 <div class="spacer">
	  &nbsp;
	 </div>
   </c:when>
   <c:otherwise>

 <div class="c">
  <mtw:i18n key="sugestoes.texto1"/>
 </div>
 <div class="spacer">
  &nbsp;
 </div>
 <div class="c">
  <mtw:i18n key="sugestoes.texto2"/>
 </div>
 <div class="spacer">
  &nbsp;
 </div>

<fieldset>
   <p>
     <mtw:select name="tipo_id" list="sugestoes" />
   </p>
   <p>
   <textarea name="msg" id="msg" cols="60" rows="20" class="required"></textarea>
   </p>
   <p>
     <label for="cemail"><mtw:i18n key="sugestoes.texto3"/></label>
     <input type="text" name="email" id="email" size="35" class="email" />
   </p>
   <p>
     &nbsp;
   </p>
   <p>
     <input type="submit" name="opt" value="<mtw:i18n key="sugestoes.enviar"/>" />
   </p>
 </fieldset>

   </c:otherwise>
  </c:choose>

 <div class="spacer">
  &nbsp;
 </div>
</div>
 </form>
<c:import url="menuDir.jsp"/>

 <c:if test="${emailEnviado!='ok'}">
  <c:import url="rodape.jsp"/>
 </c:if>

  <script>
  $(document).ready(function(){
    $("#sugestaoForm").validate();
  });
  jQuery.extend(jQuery.validator.messages, {
		required: "<mtw:i18n key="sugestoes.warning.required"/>",
		email: "<mtw:i18n key="sugestoes.warning.email"/>",
  });
  </script>

</body>
</html>
