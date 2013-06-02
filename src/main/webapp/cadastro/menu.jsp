<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <title><mtw:i18n key="aplicacao.cadastro.titulo"/></title>
 <style type="text/css"> @import "../css/estilos.css";</style>
</head>
<body>
<script language="JavaScript" src="tree.js"></script>
<mtw:hasAuthorization groups="admins">
 <script language="JavaScript" src="tree_items.js"></script>
</mtw:hasAuthorization>
<mtw:hasAuthorization groups="colaboradores">
 <script language="JavaScript" src="tree_colab.js"></script>
</mtw:hasAuthorization>
<mtw:hasAuthorization groups="editores">
 <script language="JavaScript" src="tree_edit.js"></script>
</mtw:hasAuthorization>
<script language="JavaScript" src="tree_tpl.js"></script>
<div id="Header">
 <a href="../index.home.action"><mtw:i18n key="aplicacao.cadastro.titulo"/></a>
  &nbsp;&nbsp;<a href="../Sair.action"><mtw:i18n key="aplicacao.sair"/></a>
  <mtw:hasAuthorization groups="admins">
   <a href="/testes/">testes ::</a>
  </mtw:hasAuthorization>
 <br/><br/>
 <b>
  <mtw:hasAuthorization groups="admins">[<mtw:i18n key="aplicacao.admin"/>]</mtw:hasAuthorization>
  <mtw:hasAuthorization groups="colaboradores">[<mtw:i18n key="aplicacao.colab"/>]</mtw:hasAuthorization>
  <mtw:hasAuthorization groups="editores">[<mtw:i18n key="aplicacao.editor"/>]</mtw:hasAuthorization>
 </b>
</div>
<div id="Menu">
 <script language="JavaScript">
  new tree (TREE_ITEMS, tree_tpl);
 </script>
</div>
</body>
</html>