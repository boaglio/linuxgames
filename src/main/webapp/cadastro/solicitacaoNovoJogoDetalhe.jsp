<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
 <!-- solicitacaoNovoJogoDetalhe.jsp -->
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization permission="aprovador"/>
<c:import url="cabecalho.jsp"/>
 <script language="JavaScript" type="text/javascript" src="<c:out value="${param.base}"/>js/colab.js"></script>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="solicitacaoAjudaJogo.titulo"/></h1>
 <br/><br/>
  <b><mtw:i18n key="solicitacaoAjudaJogo.nome"/>:</b>
  <c:out value="${nome}"/>
 <br/><br/>
 <b><mtw:i18n key="solicitacaoAjudaJogo.usuario"/>:</b>
  <a href="/user/profile/<c:out value="${texto.usuario.id}"/>.games">
   - <c:out value="${usuario.nome}"/> - <c:out value="${usuario.email}"/>
  </a>
  <br/><br/>
  <b><mtw:i18n key="solicitacaoAjudaJogo.texto"/>:</b>
  <br/>
    <pre><c:out value="${obs}"/></pre>
  <br/><br/>
  <form method="post" id="form" action="admin.alteraSolicitacaoAjudaNovoJogo.action">
   <mtw:input type="hidden" name="id" id="id"/>
   <mtw:input type="hidden" value="${usuario.nome}" id="usuario"  name="usuario"/>
   <mtw:input type="hidden" value="${usuario.email}" id="email" name="email"/>

  <div class="c">
   <mtw:input type="radio" name="opt" id="optApr" value="1"/><mtw:i18n key="solicitacaoAjudaJogo.aprova"/>
   &nbsp;&nbsp;&nbsp;
   <mtw:input type="radio" name="opt" id="optRep" value="2"/><mtw:i18n key="solicitacaoAjudaJogo.reprova"/>
  </div>
 <br/><br/>
 <div class="rowTitle">
   <mtw:i18n key="jogo.idioma"/>:
    <mtw:input type="text" name="idioma" id="idioma" size="5" maxlength="150"  />
 </div>
 <div class="rowTitle">
    <mtw:i18n key="jogo.nome"/>:
    <mtw:input type="text" name="nome" id="nome" size="30" maxlength="150"  />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="nome" /></font>
   </mtw:hasError>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.licenca"/>:
   <mtw:select id="licenca_id"  name="licenca_id" list="LICENCAS" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="licenca_id"/></font>
   </mtw:hasError>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.tipo"/>:
   <mtw:select id="tipo"  name="tipo" list="tiposDeJogo" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="tipo"/></font>
   </mtw:hasError>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.aberto"/>:
    <mtw:radiobuttons id="aberto"  name="aberto" list="simOuNao"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="aberto" /></font>
   </mtw:hasError>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.jogaEmRede"/>:
    <mtw:radiobuttons id="jogaEmRede" name="jogaEmRede" list="simOuNao"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="jogaEmRede" /></font>
   </mtw:hasError>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.precisa3d"/>:
    <mtw:radiobuttons  id="precisa3d" name="precisa3d" list="simOuNao"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="precisa3d" /></font>
   </mtw:hasError>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.temSom"/>:
    <mtw:radiobuttons id="temSom" name="temSom" list="simOuNao"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="temSom" /></font>
   </mtw:hasError>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.consoleOuX11"/>:
    <mtw:radiobuttons  id="consoleOuX11" name="consoleOuX11" list="consoleOuX11"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="consoleOuX11" /></font>
   </mtw:hasError>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.siteOficial"/>:
   <mtw:input type="text" id="siteOficial" name="siteOficial" size="50" maxlength="150" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="siteOficial" /></font>
   </mtw:hasError>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.descricao"/>:
   <mtw:textarea id="descricao" name="descricao" cols="50" rows="10"  extra="disabled" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="descricao"/></font>
   </mtw:hasError>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.fabricante"/>:
   <mtw:select id="fabricante_id" name="fabricante_id" list="FABRICANTES"  extra="disabled" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="fabricante_id"/></font>
   </mtw:hasError>
 </div>

   <br/><br/>
   <b><mtw:i18n key="solicitacaoAjudaJogo.obs"/></b>
   <br/>
   <mtw:textarea id="justificativa" name="justificativa" cols="50"></mtw:textarea>
   <br/><br/>
   <input type="button" onclick="if (validateApr()) document.getElementById('form').submit();" value="<mtw:i18n key="solicitacaoAjudaJogo.submit"/>"/>
  </form>
 </div>
</body>
</html>
