<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization groups="admins,colaboradores,editores"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <title><mtw:i18n key="aplicacao.cadastro.titulo"/></title>
</head>
 <frameset cols="300,*" border="0" >
  <frame src="menu.jsp" name="menu">
  <frame name="cadastro">
 </frameset>
</html>