<?xml version="1.0" encoding="ISO-8859-1"?>
<%@page session="false" contentType="text/html; charset=ISO-8859-1" %>
<%@page import="clime.messadmin.core.MessAdmin" %>
<%@page import="clime.messadmin.core.Constants" %>
<%@page import="clime.messadmin.model.IApplicationInfo" %>
<%@page import="clime.messadmin.model.ISessionInfo" %>
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
<% String context = (String) request.getAttribute("context");
   IApplicationInfo webAppStats = (IApplicationInfo) request.getAttribute("webAppStats");
   ISessionInfo currentSession = (ISessionInfo)request.getAttribute("currentSession");
   String currentSessionId = currentSession.getId();
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
	<title>Sessions Administration: details for <%= currentSessionId %></title>
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
<a href="<%=submitUrl%>?action=webAppStats&amp;context=<%=context%>">WebApp Stats for <%=webAppStats.getContextPath()%></a>
|
<a href="<%=submitUrl%>?action=sessionsList&amp;context=<%=context%>">Session List for <%=webAppStats.getContextPath()%></a>
]
</div>

<h1>Details for Session <%= currentSessionId %></h1>

<div style="<core:if test='<%=currentSession.isSecure()%>'> background-color: #F5F6BE;</core:if>">
<table style="text-align: left;" border="0">
  <tr>
    <th>Session Id</th>
    <td colspan="3"><%= currentSessionId %></td>
  </tr>
  <tr>
    <th nowrap="nowrap">Last Request URL</th>
    <td colspan="3"><span id="url" class="infoballoonable"><core:out value="<%= currentSession.getLastRequestURL() %>"/></span>
    	<div id="url-infoballoon" class="infoballoon">
    		<strong>Last Response Status</strong> <%= currentSession.getLastResponseStatus() > 0 ? ""+currentSession.getLastResponseStatus() : "" %><br/>
    		<strong>Referer</strong> <core:out value="<%= currentSession.getReferer() %>"/><br/>
    		<strong>User Agent</strong> <core:out value="<%= currentSession.getUserAgent() %>"/>
    	</div>
    </td>
  </tr>
</table>
<table style="text-align: left;" border="0">
  <tr>
    <th>Message pending?</th>
    <td>
<core:if test="<%=currentSession.getAttribute(Constants.SESSION_MESSAGE_KEY) != null%>">Yes</core:if>
<core:if test="<%=currentSession.getAttribute(Constants.SESSION_MESSAGE_KEY) == null%>">No</core:if>
    </td>
    <th>Remote Host</th>
    <td><%= currentSession.getRemoteHost() %></td>
  </tr>
  <tr>
    <th>Guessed User</th>
    <td><span title="Authentication scheme: <core:out value='<%=currentSession.getAuthType()%>' default='(none)'/>"><core:out value="<%= currentSession.getGuessedUser() %>"/></span></td>
    <th>Guessed Locale</th>
    <td><core:out value="<%= currentSession.getGuessedLocale() %>"/></td>
  </tr>
<core:if test="<%= currentSession.isSecure() %>">
  <tr>
    <th>SSL Cipher Suite</th>
    <td><core:out value="<%= currentSession.getSslCipherSuite() %>"/></td>
    <th>SSL Algorithm Size</th>
    <td title="bit size of the algorithm"><format:formatNumber value="<%= currentSession.getSslAlgorithmSize() %>"/></td>
  </tr>
</core:if>
  <tr>
    <td colspan="4"><hr/></td>
  </tr>
  <tr>
    <th>Creation Time</th>
    <td><format:formatDate value="<%= currentSession.getCreationTime() %>" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    <th>Last Accessed Time</th>
    <td><format:formatDate value="<%= currentSession.getLastAccessedTime() %>" pattern="yyyy-MM-dd HH:mm:ss"/></td>
  </tr>
  <tr>
    <th>Session Max Inactive Interval</th>
    <td><format:formatTimeInterval value="<%= currentSession.getMaxInactiveInterval()*1000 %>"/></td>
    <th><span title="Time To Live">TTL</span></th>
    <td><format:formatTimeInterval value="<%= currentSession.getTTL() %>"/></td>
  </tr>
  <tr>
    <th>Total Used Server Time</th>
    <td><span title="and <%= currentSession.getTotalUsedTime() % 1000 %> ms"><format:formatTimeInterval value="<%= currentSession.getTotalUsedTime() %>"/></span></td>
    <th>Idle Time</th>
    <td><format:formatTimeInterval value="<%= currentSession.getIdleTime() %>"/></td>
  </tr>
  <tr>
    <th>Last Used Server Time</th>
    <td><format:formatNumber value="<%= currentSession.getLastUsedTime() %>" type="number"/> ms</td>
    <th>Min / Max time to service a request</th>
    <td><span id="servertime" class="infoballoonable">
    	<span title="<format:formatDate value='<%= currentSession.getMinUsedTimeDate() %>' type='both' pattern='yyyy-MM-dd HH:mm:ss'/>"><format:formatNumber value="<%= currentSession.getMinUsedTime() %>" type="number"/></span> /
        <span title="<format:formatDate value='<%= currentSession.getMaxUsedTimeDate() %>' type='both' pattern='yyyy-MM-dd HH:mm:ss'/>"><format:formatNumber value="<%= currentSession.getMaxUsedTime() %>" type="number"/></span>
        ms</span>
    	<div id="servertime-infoballoon" class="infoballoon">
			<table border="0">
				<caption></caption>
				<tr>
					<th>Mean</th>
					<td class="number"><format:formatNumber value="<%= currentSession.getMeanUsedTime() %>" type="bytes"/></td>
				</tr>
				<tr>
					<th>StdDev</th>
					<td class="number"><format:formatNumber value="<%= currentSession.getStdDevUsedTime() %>" type="bytes"/></td>
				</tr>
			</table>
    	</div>
    </td>
  </tr>
  <tr>
    <td colspan="4"><hr/></td>
  </tr>
  <tr>
    <th>Number of hits</th>
    <td><format:formatNumber value="<%= currentSession.getHits() %>" type="number"/></td>
    <th>Session size</th>
    <td><format:formatNumber type="bytes"><core:sizeof id="currentSession"/></format:formatNumber></td>
  </tr>
  <tr>
    <th>Last Request size</th>
    <td><span id="requestSize1" class="infoballoonable"><format:formatNumber value="<%= currentSession.getRequestLastLength() %>" type="bytes"/></span>
    	<div id="requestSize1-infoballoon" class="infoballoon">
			<table border="0">
				<caption></caption>
				<tr>
					<th>Min</th>
					<td class="number"><format:formatNumber value="<%= currentSession.getRequestMinLength() %>" type="bytes"/></td>
				</tr>
				<tr>
					<th>Max</th>
					<td class="number"><format:formatNumber value="<%= currentSession.getRequestMaxLength() %>" type="bytes"/></td>
				</tr>
			</table>
    	</div>
    </td>
    <th>Total Request size</th>
    <td><span id="requestSize2" class="infoballoonable"><format:formatNumber value="<%= currentSession.getRequestTotalLength() %>" type="bytes"/></span>
    	<div id="requestSize2-infoballoon" class="infoballoon">
			<table border="0">
				<caption></caption>
				<tr>
					<th>Mean</th>
					<td class="number"><format:formatNumber value="<%= currentSession.getRequestMeanLength() %>" type="bytes"/></td>
				</tr>
				<tr>
					<th>StdDev</th>
					<td class="number"><format:formatNumber value="<%= currentSession.getRequestStdDevLength() %>" type="bytes"/></td>
				</tr>
			</table>
    	</div>
    </td>
  </tr>
  <tr>
    <th>Last Response size</th>
    <td><span id="responseSize1" class="infoballoonable"><format:formatNumber value="<%= currentSession.getResponseLastLength() %>" type="bytes"/></span>
    	<div id="responseSize1-infoballoon" class="infoballoon">
			<table border="0">
				<caption></caption>
				<tr>
					<th>Min</th>
					<td class="number"><format:formatNumber value="<%= currentSession.getResponseMinLength() %>" type="bytes"/></td>
				</tr>
				<tr>
					<th>Max</th>
					<td class="number"><format:formatNumber value="<%= currentSession.getResponseMaxLength() %>" type="bytes"/></td>
				</tr>
			</table>
    	</div>
    </td>
    <th>Total Response size</th>
    <td><span id="responseSize2" class="infoballoonable"><format:formatNumber value="<%= currentSession.getResponseTotalLength() %>" type="bytes"/></span>
    	<div id="responseSize2-infoballoon" class="infoballoon">
			<table border="0">
				<caption></caption>
				<tr>
					<th>Mean</th>
					<td class="number"><format:formatNumber value="<%= currentSession.getResponseMeanLength() %>" type="bytes"/></td>
				</tr>
				<tr>
					<th>StdDev</th>
					<td class="number"><format:formatNumber value="<%= currentSession.getResponseStdDevLength() %>" type="bytes"/></td>
				</tr>
			</table>
    	</div>
    </td>
  </tr>
</table>
</div>

<p style="text-align: center;">
<button type="button" onclick="window.location.reload()">Refresh</button>
</p>

<div class="error"><core:out value='<%= request.getAttribute("error") %>'/></div>
<div class="message"><core:out value='<%= request.getAttribute("message") %>'/></div>

<table id="sessionAttributesTable" class="strippable" style="text-align: left;" border="1" cellpadding="2" cellspacing="2">
	<caption style="font-variant: small-caps;"><format:formatNumber value="<%= /*${fn:length(currentSession.attributeNames)}*/ currentSession.getAttributes().size() %>" type="number"/> attributes</caption>
	<thead>
		<tr>
			<th>Remove Attribute</th>
			<th>Attribute size</th>
			<th>Attribute name</th>
			<th>Attribute value</th>
		</tr>
	</thead>
	<%--tfoot>
		<tr>
			<td colspan="4" style="text-align: center;">
				TODO: set (String/OGNL) attributes and Max Inactive Interval on sessions
			</td>
		</tr>
	</tfoot--%>
	<tbody>
<core:forEach items="<%= currentSession.getAttributes() %>" var="attribute" varStatus="status">
<%	java.util.Map.Entry attribute = (java.util.Map.Entry) pageContext.getAttribute("attribute"); %>
		<tr style="<core:notSerializable object='<%= attribute.getValue() %>'>background-color: #EE0000;</core:notSerializable>"><%-- class="${status.count%2==0?'even':'odd'}"--%>
			<td align="center"><form action="<%= submitUrl %>"><div><input type="hidden" name="action" value="removeSessionAttribute" /><input type="hidden" name="context" value="<%= context %>" /><input type="hidden" name="sessionId" value="<%= currentSessionId %>" /><input type="hidden" name="attributeName" value="<%= attribute.getKey() %>" /><input type="submit" value="Remove" /></div></form></td>
			<td><format:formatNumber type="bytes"><core:sizeof object="<%= attribute.getValue() %>"/></format:formatNumber></td>
			<td><core:out value='<%= attribute.getKey() %>'/></td>
			<td><core:outWithClass value="<%= attribute.getValue() %>"/></td>
		</tr>
</core:forEach>
	</tbody>
</table>

<div id="extraSessionAttributes">
<core:forEach items="<%= currentSession.getApplicationSpecificData() %>" var="applicationSpecificData" varStatus="status">
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