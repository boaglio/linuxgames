<?xml version="1.0" encoding="ISO-8859-1"?>
<%@page session="false" contentType="text/html; charset=ISO-8859-1" %>
<%@page import="clime.messadmin.model.IServerInfo" %>
<%@taglib prefix="core" uri="http://messadmin.sf.net/core" %>
<%@taglib prefix="format" uri="http://messadmin.sf.net/fmt" %>
<%--!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"--%>
<%--!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"--%>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"--%>
<%--!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
 "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"--%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<% IServerInfo serverInfos = (IServerInfo) request.getAttribute("serverInfos");
   String webFilesRoot = (String) request.getAttribute("WebFilesRoot"); %>
<%--c:url value="${pageContext.request.servletPath}" var="submitUrl" scope="page"/--%><%-- can use value="${pageContext.request.servletPath}" because this JSP is include()'ed --%>
<%-- or use directly ${pageContext.request.requestURI} --%>
<% String submitUrl = request.getContextPath() + request.getServletPath(); /* Can use +request.getServletPath() because this JSP is include()'ed */ %>
<head>
    <meta http-equiv="content-type" content="text/html; charset=iso-8859-1"/>
	<meta http-equiv="pragma" content="no-cache"/><!-- HTTP 1.0 -->
	<meta http-equiv="cache-control" content="no-cache,must-revalidate"/><!-- HTTP 1.1 -->
	<meta http-equiv="expires" content="0"/><!-- 0 is an invalid value and should be treated as 'now' -->
	<meta http-equiv="content-language" content="en"/><%-- fr-FR --%>
	<meta name="author" content="Cedrik LIME"/>
	<meta name="copyright" content="copyright 2005-2006 Cedrik LIME"/>
	<meta name="robots" content="noindex,nofollow,noarchive"/>
	<title>Server System Informations</title>
	<link rel="stylesheet" type="text/css" href="<%=webFilesRoot%>MessAdmin.css"/>
	<style type="text/css">
	</style>
	<script type="text/javascript" src="<%=webFilesRoot%>js/getElementsBySelector.js"></script>
	<script type="text/javascript" src="<%=webFilesRoot%>js/behavior.js"></script>
	<script type="text/javascript" src="<%=webFilesRoot%>js/MessAdmin.js"></script>
	<script type="text/javascript">//<![CDATA[
		function reloadPage() {
			window.location.reload();
		}
	//]]>
	</script>
</head>
<body>

<div id="menu" style="font-size: small;">
[
Server Informations
|
<a href="<%=submitUrl%>?action=webAppsList">Web Applications list</a>
]
</div>

<h1>Server System Informations</h1>

<table style="text-align: left;" border="0">
	<tr>
		<th>Server Info</th>
		<td>
			<%= getServletConfig().getServletContext().getServerInfo() %>, Servlet version
			<%= getServletConfig().getServletContext().getMajorVersion() %>.<%= getServletConfig().getServletContext().getMinorVersion() %>
		</td>
	</tr>
	<tr>
		<th>Startup date</th>
		<td><format:formatDate value="<%= serverInfos.getStartupTime() %>" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
</table>

<fieldset>
	<legend>CPU and Memory</legend>
<table style="text-align: left;" border="0">
	<tr>
		<th title="maximum number of processors available to the Java virtual machine">Number of CPUs</th>
		<td><format:formatNumber value="<%= serverInfos.getCpuCount() %>" type="number"/></td>
	</tr>
	<tr>
		<th title="amount of free memory in the system">Free Memory</th>
		<td><format:formatNumber value="<%= serverInfos.getFreeMemory() %>" type="bytes"/></td>
	</tr>
	<tr>
		<th title="maximum amount of memory that the Java virtual machine will attempt to use">Max Memory</th>
		<td><format:formatNumber value="<%= serverInfos.getMaxMemory() %>" type="bytes"/></td>
	</tr>
	<tr>
		<th title="total amount of memory in the Java Virtual Machine">Total Memory</th>
		<td><format:formatNumber value="<%= serverInfos.getTotalMemory() %>" type="bytes"/></td>
	</tr>
</table>
</fieldset>

<fieldset>
	<legend>VM Info</legend>
<!-- extracted properties from System.getProperties() (see JavaDoc) -->
<table style="text-align: left;" border="0">
	<%--caption>VM Info</caption--%>
	<tr>
		<th>Java VM</th>
		<td>
			<%= serverInfos.getSystemProperties().get("java.vm.vendor") %>
			<%= serverInfos.getSystemProperties().get("java.vm.name") %>
			<%= serverInfos.getSystemProperties().get("java.vm.version") %>
		</td>
	</tr>
	<tr>
		<th>Java RE</th>
		<td>
			<a href="<%= serverInfos.getSystemProperties().get("java.vendor.url") %>"><%= serverInfos.getSystemProperties().get("java.vendor") %></a>
			<%= serverInfos.getSystemProperties().get("java.version") %> @ <%= serverInfos.getSystemProperties().get("java.home") %>
		</td>
	</tr>
	<tr>
		<th>Platform</th>
		<td>
			<%= serverInfos.getSystemProperties().get("os.name") %>/<%= serverInfos.getSystemProperties().get("os.arch") %> <%= serverInfos.getSystemProperties().get("os.version") %>
		</td>
	</tr>
</table>
</fieldset>

<p style="text-align: center;"><button type="button" onclick="window.location.reload()">Refresh</button></p>

<div class="error"><core:out value='<%= request.getAttribute("error") %>'/></div>
<div class="message"><core:out value='<%= request.getAttribute("message") %>'/></div>

<fieldset>
<legend>System Properties</legend>
<table id="systemPropertiesTable" class="strippable" style="text-align: left;" border="1" cellpadding="2" cellspacing="2">
	<caption style="font-variant: small-caps;"><format:formatNumber value="<%=/*${fn:length(applicationScope)}*/serverInfos.getSystemProperties().size()%>" type="number"/> properties</caption>
	<thead>
		<tr>
			<th>key</th>
			<th>value</th>
		</tr>
	</thead>
	<tbody>
<core:forEach items="<%= serverInfos.getSystemProperties() %>" var="systemProperty" varStatus="status">
<%	java.util.Map.Entry property = (java.util.Map.Entry) pageContext.getAttribute("systemProperty"); %>
		<tr><%-- class="${status.count%2==0?'even':'odd'}"--%>
			<td><core:out value="<%=property.getKey()%>"/></td>
			<td><core:out value="<%=property.getValue()%>" default=""/></td>
		</tr>
</core:forEach>
	</tbody>
</table>
</fieldset>

<core:if test="<%= ! serverInfos.getSystemEnv().isEmpty() %>">
<fieldset>
<legend>System Environment variables</legend>
<table id="systemEnvTable" class="strippable" style="text-align: left;" border="1" cellpadding="2" cellspacing="2">
	<caption style="font-variant: small-caps;"><format:formatNumber value="<%=/*${fn:length(applicationScope)}*/serverInfos.getSystemEnv().size()%>" type="number"/> environment variables</caption>
	<thead>
		<tr>
			<th>name</th>
			<th>value</th>
		</tr>
	</thead>
	<tbody>
<core:forEach items="<%= serverInfos.getSystemEnv() %>" var="envData" varStatus="status">
<%	java.util.Map.Entry envData = (java.util.Map.Entry) pageContext.getAttribute("envData"); %>
		<tr><%-- class="${status.count%2==0?'even':'odd'}"--%>
			<td><core:out value="<%=envData.getKey()%>"/></td>
			<td><core:out value="<%=envData.getValue()%>" default=""/></td>
		</tr>
</core:forEach>
	</tbody>
</table>
</fieldset>
</core:if>

<jsp:include page="inc/footer.jsp"/>

</body>
</html>