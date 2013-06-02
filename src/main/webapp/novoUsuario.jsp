<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

<c:import url="cabecalho.jsp"/>
<c:import url="menuTopo.jsp"/>
<c:import url="menuEsq.jsp"/>

<div id="sugestao">
 <div class="c">
  <h3><mtw:i18n key="login.entrar"/></h3>
  <form id="formReg" action="/games/index-novo" method="post">


 <fieldset>
   <p>
      <mtw:i18n key="login.user"/>
     <input type="text" name="emaillg" id="emaillg" size="35" class="email required" />
   </p>
   <p>
     <mtw:i18n key="login.pass"/>
     <input type="text" name="passwordlg" id="passwordlg" size="35" class="required" />
   </p>
   <p>
     <mtw:i18n key="login.pergunta"/>
     <mtw:i18n key="login.pergunta2"/>
     <mtw:select list="diasDoMes" name="hoje" id="hoje" emptyField="true" klass="required"/>
   </p>
   <p>
     &nbsp;
   </p>
   <p>
     <input type="submit" value="<mtw:i18n key="login.save"/>" />
   </p>
 </fieldset>

    <mtw:hasMessage>
      <font color="green"><mtw:message /></font>
    </mtw:hasMessage>

  </form>
 </div>
 <div class="spacer">
  &nbsp;
 </div>
 <h3>
  <mtw:i18n key="login.esqueceuSenha"/>
 </h3>
 <form id="formEsqueceuSenha" action="/games/index-esqueceu" method="post">
 <div class="c">
   <div class="row">
   <mtw:i18n key="login.esqueceuSenha2"/>
 </div>

 <fieldset>
   <p>
     <mtw:i18n key="login.user"/>
     <input type="text" name="emaillg2" id="emaillg2" size="35" class="email required" />
   </p>
   <p>
     &nbsp;
   </p>
   <p>
     <input type="submit" value="<mtw:i18n key="login.enviar"/>" />
   </p>
 </fieldset>

  <div class="row">
   <mtw:i18n key="login.esqueceuEmail"/>&nbsp;<a href="/games/sugestao"><mtw:i18n key="login.esqueceuEmail2"/></a>.
  </div>
 </form>
 <div class="spacer">
  &nbsp;
 </div>
 </div>

</div>
<c:import url="menuDir.jsp"/>
<c:import url="rodape.jsp"/>


  <script>
  $(document).ready(function(){
    $("#formEsqueceuSenha").validate();
    $("#formReg").validate();
  });
  jQuery.extend(jQuery.validator.messages, {
		required: "<mtw:i18n key="sugestoes.warning.required"/>",
		email: "<mtw:i18n key="sugestoes.warning.email"/>",
  });
  </script>
</body>
</html>
