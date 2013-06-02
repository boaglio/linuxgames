<?xml version="1.0" encoding="ISO-8859-1"?>
<%@page session="false" contentType="text/html; charset=ISO-8859-1" %>
<%@taglib prefix="core" uri="http://messadmin.sf.net/core" %>
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
<% String webFilesRoot = (String) request.getAttribute("WebFilesRoot"); %>
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
	<title>Web Applications list</title>
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
		function checkApplications(theElementCB, name) {
			if (hasCheckedCB(theElementCB, name)) {
				return true;
			} else {
				alert('Please check at least one application first!');
				return false;
			}
		}
	//]]>
	</script>
</head>
<body>

<div id="menu" style="font-size: small;">
[
<a href="<%=submitUrl%>?action=serverInfos">Server Informations</a>
|
Web Applications list
]
</div>

<h1>Web Applications list</h1>

<div class="error"><core:out value='<%= request.getAttribute("error") %>'/></div>
<div class="message"><core:out value='<%= request.getAttribute("message") %>'/></div>

<p style="text-align: center;"><button type="button" title="Refresh Applications list" onclick="window.location.reload()">Refresh</button></p>

<form action="<%= submitUrl %>" method="post" id="applicationsForm">
	<fieldset><legend>Send message to some applications</legend>
		<input type="hidden" name="action" id="applicationsFormAction" value="injectApplications"/>
		<table id="applicationsListTable" class="strippable" border="1" cellpadding="2" cellspacing="2" width="100%">
			<tbody>
<% String context; %>
<core:forEach items='<%= request.getAttribute("contexts") %>' var="context" varStatus="status">
<% context = (String) pageContext.getAttribute("context"); %>
				<tr><%-- class="${status.count%2==0?'even':'odd'}"--%>
					<td><label><input type="checkbox" name="applicationIds" value="<%= context %>" /><%= context %></label></td>
					<td style="text-align: center;"><a href="<%= submitUrl %>?action=sessionsList&amp;context=<%= context %>">Session List</a></td>
					<td style="text-align: center;"><a href="<%= submitUrl %>?action=webAppStats&amp;context=<%= context %>">WebApp Stats</a></td>
				</tr>
</core:forEach>
			</tbody>
		</table>
		<label><input type="checkbox" onclick="javascript:checkUncheckAllCB(this, 'applicationIds');"/>select all</label>
		<table border="0">
			<tr>
				<td>
					<div style="text-align: center;">
						Message to send (HTML):<br/>
						<textarea rows="4" cols="70" id="applicationsMessage" name="message"></textarea><br/>
						<input type="submit" value="Send Message" title="Send Message to selected Applications" onclick="return checkApplications(this, 'applicationIds');"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label title="Set permanent (checked) or once-only (unchecked) Message"><input type="checkbox" id="permanent" name="permanent"/>Permanent Message</label>
					</div>
				</td>
				<td>
					Tips:
					<ul>
						<li>Send an empty message to remove previously set message.</li>
					</ul>
				</td>
			</tr>
		</table>
	</fieldset>
</form>

<jsp:include page="inc/footer.jsp"/>

</body>
</html>