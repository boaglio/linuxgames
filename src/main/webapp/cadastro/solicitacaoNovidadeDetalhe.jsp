<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
 <!-- solicitacaoNovidadeDetalhe.jsp -->
 <mtw:inputDateConfig/>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization permission="aprovador"/>
<c:import url="cabecalho.jsp"/>
 <script language="JavaScript" type="text/javascript" src="<c:out value="${param.base}"/>js/colab.js"></script>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="solicitacaoNoticia.titulo"/></h1>
 <br/><br/>

 <form method="post" id="form" action="admin.alteraSolicitacaoNovidade.action">
 <mtw:input type="hidden" name="id" id="id"/>

 <div class="row">
  <span class="label">
   <mtw:i18n key="solicitacaoNoticia.usuario"/>
  </span>
  <a href="/user/profile/<c:out value="${usuario.id}"/>.games"><c:out value="${usuario.nome}"/></a>
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="novidade.texto"/>:
  </span>
  <mtw:textarea name="texto" cols="50" rows="10"/>
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="texto"/></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="novidade.link"/>:
  </span>
  <mtw:input type="text" name="link" size="50" maxlength="150" />
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="link" /></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="novidade.data"/>:
  </span>
  <mtw:inputDate id="dataPublic" name="dataPublic" size="50" maxlength="50" dateFormat="dd-mm-yyyy"/>
  <mtw:hasError>
   <font class="errorMessage"><mtw:error field="dataPublic" /></font>
  </mtw:hasError>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="novidade.usuario"/>:
  </span>
  <mtw:select name="usuario_id" list="USUARIOS" />
 </div>

 <div class="row">
  <span class="label">
    <mtw:i18n key="solicitacaoNoticia.obs"/>
  </span>
   <mtw:textarea id="justificativa" name="justificativa" cols="50"></mtw:textarea>
 </div>

 <div class="c">
   <mtw:input type="radio" name="opt" id="optApr" value="1"/><mtw:i18n key="solicitacaoNoticia.aprova"/>
   &nbsp;&nbsp;&nbsp;
   <mtw:input type="radio" name="opt" id="optRep" value="2"/><mtw:i18n key="solicitacaoNoticia.reprova"/>
 </div>
 <br/><br/>

   <input type="button" onclick="if (validateApr()) { document.getElementById('form').submit();}" value="<mtw:i18n key="solicitacaoNoticia.submit"/>"/>
  </form>
 </div>
</body>
</html>
