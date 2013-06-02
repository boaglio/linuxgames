<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
 <!-- solicitacaoAjudaJogoDetalhe.jsp -->
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization permission="aprovador"/>
<c:import url="cabecalho.jsp"/>
 <script language="JavaScript" type="text/javascript" src="<c:out value="${param.base}"/>js/colab.js"></script>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="solicitacaoAjudaJogo.titulo"/></h1>
 <br/><br/>
  <b><mtw:i18n key="solicitacaoAjudaJogo.nome"/>:</b>
 <a href="/info.jogoInst.action?id=<c:out value="${texto.id}"/>">
  <c:out value="${tnome}"/>
 </a>
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
  <form method="post" id="form" action="admin.alteraSolicitacaoAjudaJogo.action">
   <mtw:input type="hidden" name="id" id="id"/>
   <mtw:input type="hidden" name="jogo_id" id="jogo_id"/>
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
   <div id="feedback0"></div>
 </div>
 <div class="row">
    <mtw:i18n key="solicitacaoAjudaJogo.sugerido"/>
    <mtw:input type="text" name="tidioma" id="tidioma" size="5" maxlength="150"  />
 </div>
 <div class="row">
    <mtw:i18n key="solicitacaoAjudaJogo.armazenado"/>
    <mtw:input type="text" name="idioma" id="idioma" size="5" maxlength="150" />
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.nome"/>:
   <div id="feedback1"></div>
 </div>
 <div class="row">
    <mtw:i18n key="solicitacaoAjudaJogo.sugerido"/>
    <mtw:input type="text" name="tnome" id="tnome" size="30" maxlength="150"  />
    <input type="button" onclick="copiaCampos('tnome','nome')" value="copiar"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="nome" /></font>
   </mtw:hasError>
 </div>
 <div class="row">
    <mtw:i18n key="solicitacaoAjudaJogo.armazenado"/>
    <mtw:input type="text" name="nome" id="nome" size="30" maxlength="150" />
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.licenca"/>:
   <div id="feedback2"></div>
 </div>
 <div class="row">
   <mtw:i18n key="solicitacaoAjudaJogo.sugerido"/>
   <mtw:select id="tlicenca_id"  name="tlicenca_id" list="LICENCAS" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="licenca_id"/></font>
   </mtw:hasError>
 </div>
 <div class="row">
   <mtw:i18n key="solicitacaoAjudaJogo.armazenado"/>
   <mtw:select name="licenca_id" id="licenca_id" list="LICENCAS" />
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.tipo"/>:
   <div id="feedback3"></div>
 </div>
 <div class="row">
   <mtw:i18n key="solicitacaoAjudaJogo.sugerido"/>
   <mtw:select id="ttipo"  name="ttipo" list="tiposDeJogo" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="tipo"/></font>
   </mtw:hasError>
 </div>
 <div class="row">
   <mtw:i18n key="solicitacaoAjudaJogo.armazenado"/>
   <mtw:select name="tipo" id="tipo" list="tiposDeJogo" />
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.aberto"/>:
   <div id="feedback4"></div>
 </div>
 <div class="row">
    <mtw:i18n key="solicitacaoAjudaJogo.sugerido"/>
    <mtw:radiobuttons id="taberto"  name="taberto" list="simOuNao"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="aberto" /></font>
   </mtw:hasError>
 </div>
 <div class="row">
    <mtw:i18n key="solicitacaoAjudaJogo.armazenado"/>
    <mtw:radiobuttons name="aberto" id="aberto" list="simOuNao"/>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.jogaEmRede"/>:
   <div id="feedback5"></div>
 </div>
 <div class="row">
    <mtw:i18n key="solicitacaoAjudaJogo.sugerido"/>
    <mtw:radiobuttons id="tjogaEmRede" name="tjogaEmRede" list="simOuNao"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="jogaEmRede" /></font>
   </mtw:hasError>
 </div>
 <div class="row">
    <mtw:i18n key="solicitacaoAjudaJogo.armazenado"/>
    <mtw:radiobuttons name="jogaEmRede" id="jogaEmRede"  list="simOuNao"/>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.precisa3d"/>:
   <div id="feedback6"></div>
 </div>
 <div class="row">
    <mtw:i18n key="solicitacaoAjudaJogo.sugerido"/>
    <mtw:radiobuttons  id="tprecisa3d" name="tprecisa3d" list="simOuNao"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="precisa3d" /></font>
   </mtw:hasError>
 </div>
 <div class="row">
    <mtw:i18n key="solicitacaoAjudaJogo.armazenado"/>
    <mtw:radiobuttons name="precisa3d" id="precisa3d" list="simOuNao"/>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.temSom"/>:
   <div id="feedback7"></div>
 </div>
 <div class="row">
    <mtw:i18n key="solicitacaoAjudaJogo.sugerido"/>
    <mtw:radiobuttons id="ttemSom" name="ttemSom" list="simOuNao"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="temSom" /></font>
   </mtw:hasError>
 </div>
 <div class="row">
    <mtw:i18n key="solicitacaoAjudaJogo.armazenado"/>
    <mtw:radiobuttons name="temSom" id="temSom" list="simOuNao"/>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.consoleOuX11"/>:
   <div id="feedback8"></div>
 </div>
 <div class="row">
    <mtw:i18n key="solicitacaoAjudaJogo.sugerido"/>
    <mtw:radiobuttons  id="tconsoleOuX11" name="tconsoleOuX11" list="consoleOuX11"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="consoleOuX11" /></font>
   </mtw:hasError>
 </div>
 <div class="row">
   <mtw:i18n key="solicitacaoAjudaJogo.armazenado"/>
    <mtw:radiobuttons name="consoleOuX11"  id="consoleOuX11" list="consoleOuX11"/>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.siteOficial"/>:
   <div id="feedback9"></div>
 </div>
 <div class="row">
   <mtw:i18n key="solicitacaoAjudaJogo.sugerido"/>
   <mtw:input type="text" id="tsiteOficial" name="tsiteOficial" size="50" maxlength="150" />
   <input type="button" onclick="copiaCampos('tsiteOficial','siteOficial')" value="copiar"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="siteOficial" /></font>
   </mtw:hasError>
 </div>
 <div class="row">
   <mtw:i18n key="solicitacaoAjudaJogo.armazenado"/>
   <mtw:input type="text" name="siteOficial" id="siteOficial" size="50" maxlength="150" />
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.descricao"/>:
   <div id="feedback10"></div>
 </div>
 <div class="row">
   <mtw:i18n key="solicitacaoAjudaJogo.sugerido"/>
   <mtw:textarea id="tdescricao" name="tdescricao" cols="50" rows="10"  extra="disabled" />
   <input type="button" onclick="copiaCampos('tdescricao','descricao')" value="copiar"/>
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="descricao"/></font>
   </mtw:hasError>
 </div>
 <div class="row">
   <mtw:i18n key="solicitacaoAjudaJogo.armazenado"/>
   <mtw:textarea name="descricao" id="descricao" cols="50" rows="10"/>
 </div>
 <div class="rowTitle">
   <mtw:i18n key="jogo.fabricante"/>:
   <div id="feedback11"></div>
 </div>
 <div class="row">
   <mtw:i18n key="solicitacaoAjudaJogo.sugerido"/>
   <mtw:select id="tfabricante_id" name="tfabricante_id" list="FABRICANTES"  extra="disabled" />
   <mtw:hasError>
    <font class="errorMessage"><mtw:error field="fabricante_id"/></font>
   </mtw:hasError>
 </div>
 <div class="row">
   <mtw:i18n key="solicitacaoAjudaJogo.armazenado"/>
   <mtw:select name="fabricante_id" id="fabricante_id" list="FABRICANTES"/>
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
<script language="JavaScript">
 desligaCampos();
      verificaAlteracao('idioma','tidioma','feedback0');
      verificaAlteracao('nome','tnome','feedback1');
 verificaAlteracaoCombo('licenca_id','tlicenca_id','feedback2');
 verificaAlteracaoCombo('tipo','ttipo','feedback3');
 verificaAlteracaoRadio('aberto','taberto','feedback4');
 verificaAlteracaoRadio('jogaEmRede','tjogaEmRede','feedback5');
 verificaAlteracaoRadio('precisa3d','tprecisa3d','feedback6');
 verificaAlteracaoRadio('temSom','ttemSom','feedback7');
 verificaAlteracaoRadio('consoleOuX11','tconsoleOuX11','feedback8');
      verificaAlteracao('siteOficial','tsiteOficial','feedback9');
      verificaAlteracao('descricao','tdescricao','feedback10');
 verificaAlteracaoCombo('fabricante_id','tfabricante_id','feedback11');
</script>