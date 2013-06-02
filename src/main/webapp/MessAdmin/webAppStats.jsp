<?xml version="1.0" encoding="ISO-8859-1"?>
<%@page session="false" contentType="text/html; charset=ISO-8859-1" %>
<%@page import="clime.messadmin.model.IApplicationInfo" %>
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
<% IApplicationInfo webAppStats = (IApplicationInfo) request.getAttribute("webAppStats");
   String context = (String) request.getAttribute("context");
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
	<title>Sessions Administration: WebApp statistics for <core:out value="<%= webAppStats.getContextPath() %>"/></title>
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
<a href="<%=submitUrl%>?action=serverInfos">Server Informations</a>
|
<a href="<%=submitUrl%>?action=webAppsList">Web Applications list</a>
|
WebApp Stats for <%=webAppStats.getContextPath()%>
|
<a href="<%=submitUrl%>?action=sessionsList&amp;context=<%=context%>">Session List for <%=webAppStats.getContextPath()%></a>
]
</div>

<h1>Details for WebApp <core:out value="<%= webAppStats.getContextPath() %>"/> (<core:out value="<%= webAppStats.getServletContextName() %>"/>)</h1>

<table style="text-align: left;" border="0">
  <tr>
    <th>Platform</th>
    <td><core:out value="<%= webAppStats.getServerInfo() %>"/></td>
  </tr>
  <tr>
    <th>Startup date</th>
    <td><format:formatDate value="<%= webAppStats.getStartupTime() %>" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
  </tr>
  <tr>
    <th>Active sessions</th>
    <td><format:formatNumber value="<%= webAppStats.getActiveSessionsCount() %>" type="number"/></td>
  </tr>
  <tr>
    <th>Passive sessions</th>
    <td><format:formatNumber value="<%= webAppStats.getPassiveSessionsCount() %>" type="number"/></td>
  </tr>
  <tr>
    <td colspan="2"><hr/></td>
  </tr>
  <tr>
    <th>Maximum concurrent Sessions (peak)</th>
    <td><format:formatNumber value="<%= webAppStats.getMaxConcurrentSessions() %>" type="number"/></td>
  </tr>
  <tr>
    <th align="right">at&nbsp;</th>
    <td><format:formatDate value="<%= webAppStats.getMaxConcurrentSessionsDate() %>" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
  </tr>
  <tr>
    <th>Total number of created Sessions (cumulative)</th>
    <td><format:formatNumber value="<%= webAppStats.getTotalCreatedSessions() %>" type="number"/></td>
  </tr>
  <tr>
    <td colspan="2"><hr/></td>
  </tr>
  <tr>
    <th>Total number of hits</th>
    <td><format:formatNumber value="<%= webAppStats.getHits() %>" type="number"/></td>
  </tr>
  <tr>
    <th>Total Request size</th>
    <td><span id="request" class="infoballoonable"><format:formatNumber value="<%= webAppStats.getRequestTotalLength() %>" type="bytes"/></span>
    	<div id="request-infoballoon" class="infoballoon">
			<table border="0">
				<caption></caption>
				<tr>
					<th>Max</th>
					<td class="number"><format:formatNumber value="<%= webAppStats.getRequestMaxLength() %>" type="bytes"/></td>
				</tr>
				<tr>
					<th>Mean</th>
					<td class="number"><format:formatNumber value="<%= webAppStats.getRequestMeanLength() %>" type="bytes"/></td>
				</tr>
				<tr>
					<th>StdDev</th>
					<td class="number"><format:formatNumber value="<%= webAppStats.getRequestStdDevLength() %>" type="bytes"/></td>
				</tr>
			</table>
    	</div>
    </td>
  </tr>
  <tr>
    <th>Total Response size</th>
    <td><span id="response" class="infoballoonable"><format:formatNumber value="<%= webAppStats.getResponseTotalLength() %>" type="bytes"/></span>
    	<div id="response-infoballoon" class="infoballoon">
			<table border="0">
				<caption></caption>
				<tr>
					<th>Max</th>
					<td class="number"><format:formatNumber value="<%= webAppStats.getResponseMaxLength() %>" type="bytes"/></td>
				</tr>
				<tr>
					<th>Mean</th>
					<td class="number"><format:formatNumber value="<%= webAppStats.getResponseMeanLength() %>" type="bytes"/></td>
				</tr>
				<tr>
					<th>StdDev</th>
					<td class="number"><format:formatNumber value="<%= webAppStats.getResponseStdDevLength() %>" type="bytes"/></td>
				</tr>
			</table>
    	</div>
    </td>
  </tr>
  <tr>
    <th>Used Server Time</th>
    <td><span id="servertime" class="infoballoonable" title="and <%= webAppStats.getUsedTimeTotal() % 1000 %> ms"><format:formatTimeInterval value="<%= webAppStats.getUsedTimeTotal() %>"/></span>
    	<div id="servertime-infoballoon" class="infoballoon">
			<table border="0">
				<caption></caption>
				<tr>
					<th>Max</th>
					<td class="number"><format:formatNumber value="<%= webAppStats.getUsedTimeMax() %>" type="number"/> ms</td>
				</tr>
				<tr>
					<th>Mean</th>
					<td class="number"><format:formatNumber value="<%= webAppStats.getUsedTimeMean() %>" type="number"/> ms</td>
				</tr>
				<tr>
					<th>StdDev</th>
					<td class="number"><format:formatNumber value="<%= webAppStats.getUsedTimeStdDev() %>" type="number"/> ms</td>
				</tr>
			</table>
    	</div>
    </td>
  </tr>
</table>

<p style="text-align: center;"><button type="button" onclick="window.location.reload()">Refresh</button></p>

<div class="error"><core:out value="<%= request.getAttribute("error") %>"/></div>
<div class="message"><core:out value="<%= request.getAttribute("message") %>"/></div>

<table id="applicationAttributesTable" class="strippable" style="text-align: left;" border="1" cellpadding="2" cellspacing="2">
	<caption style="font-variant: small-caps;"><format:formatNumber value="<%=/*${fn:length(applicationScope)}*/webAppStats.getAttributes().size()%>" type="number"/> attributes</caption>
	<thead>
		<tr>
			<th>Remove Attribute</th>
			<th>Attribute size</th>
			<th>Attribute name</th>
			<th>Attribute value</th>
		</tr>
	</thead>
	<tbody>
<core:forEach items="<%= webAppStats.getAttributes() %>" var="attribute" varStatus="status">
<%	java.util.Map.Entry attribute = (java.util.Map.Entry) pageContext.getAttribute("attribute"); %>
		<tr><%-- class="${status.count%2==0?'even':'odd'}"--%>
			<td align="center"><form action="<%= submitUrl %>"><div><input type="hidden" name="action" value="removeServletContextAttribute" /><input type="hidden" name="context" value="<%= context %>" /><input type="hidden" name="attributeName" value="<%= attribute.getKey() %>" /><input type="submit" value="Remove" /></div></form></td>
			<td><format:formatNumber type="bytes"><core:sizeof object="<%=attribute.getValue()%>"/></format:formatNumber></td>
			<td><core:out value="<%=attribute.getKey()%>"/></td>
			<td><core:outWithClass value="<%=attribute.getValue()%>" default=""/></td>
		</tr>
</core:forEach>
	</tbody>
</table>

<div id="extraApplicationAttributes">
<core:forEach items="<%= webAppStats.getApplicationSpecificData() %>" var="applicationSpecificData" varStatus="status">
<%	java.util.Map.Entry applicationSpecificData = (java.util.Map.Entry) pageContext.getAttribute("applicationSpecificData"); %>
	<fieldset>
		<legend><%= applicationSpecificData.getKey() %></legend>
		<%= applicationSpecificData.getValue() %>
	</fieldset>
</core:forEach>
</div>

<jsp:include page="inc/footer.jsp"/>

</body>
</html>