<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
 <!-- solicitacaoTextoEmuladorDetalhe.jsp -->
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization permission="aprovador"/>
<c:import url="cabecalho.jsp"/>
 <script language="JavaScript" type="text/javascript" src="<c:out value="${param.base}"/>js/colab.js"></script>  
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="solicitacaoTextoEmulador.titulo"/></h1>
 <br/><br/>
  <b><mtw:i18n key="solicitacaoTextoEmulador.nome"/>:</b>
 <a href="/info.emuladorInst.action?id=<c:out value="${texto.id}"/>">
  <c:out value="${texto.nome}"/> 
 </a>
 <br/><br/>
 <b><mtw:i18n key="solicitacaoTextoEmulador.tipo"/>:</b>
 <c:choose>
  <c:when test="${texto.texto.tipo==1}"> <mtw:i18n key="solicitacaoTextoEmulador.tipo.1"/></c:when>
  <c:when test="${texto.texto.tipo==2}"> <mtw:i18n key="solicitacaoTextoEmulador.tipo.2"/></c:when>
  <c:when test="${texto.texto.tipo==3}"> <mtw:i18n key="solicitacaoTextoEmulador.tipo.3"/></c:when>
 </c:choose>       
 <br/><br/>
 <b><mtw:i18n key="solicitacaoTextoEmulador.usuario"/>:</b>
  <a href="/user/profile/<c:out value="${texto.usuario.id}"/>.games">
   - <c:out value="${texto.usuario.nome}"/> - <c:out value="${texto.usuario.email}"/> - 
  </a>
  <br/><br/>
  <b><mtw:i18n key="solicitacaoTextoEmulador.texto"/>:</b>
  <br/>
    <pre><c:out value="${texto.texto.texto}"/></pre> 
  <br/><br/>
  <b><mtw:i18n key="solicitacaoTextoEmulador.link"/>:</b>
   <a href="<c:out value="${texto.texto.link}"/>" target="_blank">
    <c:out value="${texto.texto.link}"/>
   </a>
  <br/><br/>
  <form method="post" id="form" action="admin.alteraSolicitacaoEmulador.action">
   <mtw:input type="radio" name="opt" id="optApr" value="1"/><mtw:i18n key="solicitacaoTextoEmulador.aprova"/>
   <mtw:input type="radio" name="opt" id="optRep" value="2"/><mtw:i18n key="solicitacaoTextoEmulador.reprova"/>
   <input type="hidden" name="tipoSolicitacao" value="<c:out value="${texto.texto.tipo}"/>"/>
   <input type="hidden" name="emulador" value="<c:out value="${texto.nome}"/>"/>
   <input type="hidden" name="usuario" value="<c:out value="${texto.usuario.nome}"/>"/>
   <input type="hidden" name="email" value="<c:out value="${texto.usuario.email}"/>"/>
   <input type="hidden" name="id" value="<c:out value="${texto.texto.id}"/>"/>
   <input type="hidden" name="idEmulador" value="<c:out value="${texto.id}"/>"/>
   <br/><br/>
   <b><mtw:i18n key="solicitacaoTextoEmulador.obs"/></b>
   <br/>
   <mtw:textarea id="justificativa" name="justificativa" cols="50"></mtw:textarea>
   <br/><br/>
   <input type="button" onclick="if (validateApr()) document.getElementById('form').submit();" value="<mtw:i18n key="solicitacaoTextoEmulador.submit"/>"/>
  </form>
 </div>
</body>
</html>