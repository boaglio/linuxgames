<?xml version="1.0" encoding="ISO-8859-1"?>
<%@page session="false" contentType="text/html; charset=ISO-8859-1" %>
<%@page import="clime.messadmin.core.MessAdmin" %>
<%@page import="java.util.Collection" %>
<%@page import="clime.messadmin.model.IApplicationInfo" %>
<%@page import="clime.messadmin.model.ISessionInfo" %>
<%@page import="clime.messadmin.core.Constants" %>
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
   Collection activeSessions = (Collection)request.getAttribute("activeSessions");
   Collection passiveSessionsIds = (Collection)request.getAttribute("passiveSessionsIds");
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
	<title>Sessions Administration for <%= webAppStats.getContextPath() %></title>
	<link rel="stylesheet" type="text/css" href="<%=webFilesRoot%>MessAdmin.css"/>
	<style type="text/css">
	</style>
	<script type="text/javascript" src="<%=webFilesRoot%>js/getElementsBySelector.js"></script>
	<script type="text/javascript" src="<%=webFilesRoot%>js/behavior.js"></script>
	<script type="text/javascript" src="<%=webFilesRoot%>js/MessAdmin.js"></script>
	<script type="text/javascript">//<![CDATA[
		function reloadPage() {
			document.getElementById('sessionsFormAction').value='refreshSessions';
			document.getElementById('sessionsForm').method='GET';
			document.getElementById('refreshButton').click();
		}
		function checkSessions(theElementCB, name) {
			if (hasCheckedCB(theElementCB, name)) {
				return true;
			} else {
				alert('Please check at least one session first!');
				return false;
			}
		}
		addWindowOnLoadHandler(function() {
			setAutorefresh(document.getElementById('autorefresh'));
		});
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
Session List for <%=webAppStats.getContextPath()%>
]
</div>

<h1>Sessions Administration: list for <%= webAppStats.getContextPath() %></h1>

<form action="<%= submitUrl %>" method="post" id="applicationForm">
	<input type="hidden" name="context" value="<%= context %>" />
	<input type="hidden" id="applicationFormAction" name="action" value="injectApplication"/>
		<table border="0">
			<tr>
				<td>
					<fieldset><legend>Global Application Message (permanent, for everyone)</legend>
						<label>Message to send (HTML):<br/><textarea rows="4" cols="70" id="applicationMessage" name="message"></textarea></label><br/>
						<input type="submit" value="Set permanent Message"/>
						<core:if test="<%= webAppStats.getAttribute(Constants.GLOBAL_MESSAGE_KEY) != null %>"><br/>Application Message is set!</core:if>
					</fieldset>
				</td>
				<td>
					Tips:
					<ul>
						<li>Send an empty message to remove previously set message.</li>
						<li>Click on a column to sort.</li>
					</ul>
				</td>
			</tr>
		</table>
</form>

<div class="error"><core:out value='<%= request.getAttribute("error") %>'/></div>
<div class="message"><core:out value='<%= request.getAttribute("message") %>'/></div>

<form action="<%= submitUrl %>" method="post" id="sessionsForm">
	<input type="hidden" name="context" value="<%= context %>" />
	<input type="hidden" name="action" id="sessionsFormAction" value="injectSessions"/>
	<input type="hidden" name="sort" id="sessionsFormSort" value="<core:out value='<%=request.getAttribute("sort")%>'/>"/>
	<input type="hidden" name="order" id="sessionsFormSortOrder" value="<core:out value='<%=request.getAttribute("order")%>' default='ASC'/>"/>
	<div style="text-align: center;">
		<input type="text" name="autorefresh" id="autorefresh" title="Auto-refresh (in seconds)" value="<core:out value='<%=request.getAttribute("autorefresh")%>'/>" size="3" maxlength="3" onchange="setAutorefresh(this); return false;"/>
		<input type="submit" name="refresh" id="refreshButton" value="Refresh" title="Refresh Sessions list" onclick="document.getElementById('sessionsFormAction').value='refreshSessions'; document.getElementById('sessionsForm').method='GET'; return true;"/>
	</div>
	<fieldset><legend>Send message to some sessions</legend>
		<format:formatNumber value='<%= /*${fn:length(activeSessions)}*/activeSessions.size() %>' type="number"/> active Sessions,
		<format:formatNumber value="<%= /*${fn:length(passiveSessionsIds)}*/passiveSessionsIds.size() %>" type="number"/> passivated Sessions
		(<a href="<%= submitUrl %>?action=webAppStats&amp;context=<%=context%>">more stats...</a>)<br/>
		<table id="sessionsListTable" class="strippable" border="1" cellpadding="2" cellspacing="2" width="100%">
			<thead>
				<tr>
					<th><a onclick="document.getElementById('sessionsFormSort').value='id'; document.getElementById('refreshButton').click(); return true;">Session Id</a></th>
					<th>Message pending?</th>
					<th><a onclick="document.getElementById('sessionsFormSort').value='locale'; document.getElementById('refreshButton').click(); return true;">Guessed Locale</a></th>
					<th><a onclick="document.getElementById('sessionsFormSort').value='user'; document.getElementById('refreshButton').click(); return true;">Guessed User name</a></th>
					<th><a onclick="document.getElementById('sessionsFormSort').value='CreationTime'; document.getElementById('refreshButton').click(); return true;">Creation Time</a></th>
					<th><a onclick="document.getElementById('sessionsFormSort').value='LastAccessedTime'; document.getElementById('refreshButton').click(); return true;">Last Accessed Time</a></th>
					<th><a onclick="document.getElementById('sessionsFormSort').value='UsedTime'; document.getElementById('refreshButton').click(); return true;">Used Server Time</a></th>
					<th><a onclick="document.getElementById('sessionsFormSort').value='IdleTime'; document.getElementById('refreshButton').click(); return true;">Idle Time</a></th>
					<th><a onclick="document.getElementById('sessionsFormSort').value='TTL'; document.getElementById('refreshButton').click(); return true;"><span title="Time To Live">TTL</span></a></th>
				</tr>
			</thead>
			<core:if test='<%= /*${fn:length(activeSessions) > 10}*/ activeSessions.size() > 10 %>'>
			<tfoot><%-- <tfoot> is the same as <thead> --%>
				<tr>
					<th><a onclick="document.getElementById('sessionsFormSort').value='id'; document.getElementById('refreshButton').click(); return true;">Session Id</a></th>
					<th>Message pending?</th>
					<th><a onclick="document.getElementById('sessionsFormSort').value='locale'; document.getElementById('refreshButton').click(); return true;">Guessed Locale</a></th>
					<th><a onclick="document.getElementById('sessionsFormSort').value='user'; document.getElementById('refreshButton').click(); return true;">Guessed User name</a></th>
					<th><a onclick="document.getElementById('sessionsFormSort').value='CreationTime'; document.getElementById('refreshButton').click(); return true;">Creation Time</a></th>
					<th><a onclick="document.getElementById('sessionsFormSort').value='LastAccessedTime'; document.getElementById('refreshButton').click(); return true;">Last Accessed Time</a></th>
					<th><a onclick="document.getElementById('sessionsFormSort').value='UsedTime'; document.getElementById('refreshButton').click(); return true;">Used Server Time</a></th>
					<th><a onclick="document.getElementById('sessionsFormSort').value='IdleTime'; document.getElementById('refreshButton').click(); return true;">Idle Time</a></th>
					<th><a onclick="document.getElementById('sessionsFormSort').value='TTL'; document.getElementById('refreshButton').click(); return true;"><span title="Time To Live">TTL</span></a></th>
				</tr>
			</tfoot>
			</core:if>
			<tbody>
<% ISessionInfo currentSession; %>
<core:forEach items="<%=activeSessions%>" var="currentSession" varStatus="status">
<% currentSession = (ISessionInfo) pageContext.getAttribute("currentSession"); %>
				<tr style="<core:if test='<%=currentSession.isSecure()%>'>background-color: #F5F6BE;</core:if>"><%-- class="${status.count%2==0?'even':'odd'}"--%>
					<td>
<input type="checkbox" name="sessionIds" value="<%= currentSession.getId() %>" /><a href="<%=submitUrl%>?action=sessionDetail&amp;context=<%=context%>&amp;sessionId=<%=currentSession.getId()%>"><%= currentSession.getId() %></a>
					</td>
					<td style="text-align: center;" title="<core:out value='<%= currentSession.getLastRequestURL() %>'/>">
<core:if test="<%= currentSession.getAttribute(Constants.SESSION_MESSAGE_KEY) != null %>">M</core:if>
					</td>
					<td style="text-align: center;" title="<core:out value='<%= currentSession.getUserAgent() %>'/>"><core:out value="<%= currentSession.getGuessedLocale() %>"/></td>
					<td style="text-align: center;" title="<core:out value='<%= currentSession.getRemoteHost() %>'/>"><core:out value="<%= currentSession.getGuessedUser() %>"/></td>
					<td style="text-align: center;"><format:formatDate value="<%= currentSession.getCreationTime() %>" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td style="text-align: center;"><format:formatDate value="<%= currentSession.getLastAccessedTime() %>" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td style="text-align: center;"><format:formatTimeInterval value="<%=currentSession.getTotalUsedTime() %>"/></td>
					<td style="text-align: center;"><format:formatTimeInterval value="<%= currentSession.getIdleTime() %>"/></td>
					<td style="text-align: center;"><format:formatTimeInterval value="<%= currentSession.getTTL() %>"/></td>
				</tr>
</core:forEach>
			</tbody>
		</table>
		<label><input type="checkbox" onclick="javascript:checkUncheckAllCB(this, 'sessionIds');"/>select all</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" name="invalidate" value="Invalidate" title="Invalidate selected Sessions" onclick="if (window.confirm('Are you sure?')) {document.getElementById('sessionsFormAction').value='invalidateSessions'; return checkSessions(this, 'sessionIds');} else {return false;}"/>
		<div style="text-align: center;">
			Message to send (HTML):<br/>
			<textarea rows="4" cols="70" id="sessionsMessage" name="message"></textarea><br/>
			<input type="submit" name="submit" value="Send Message" title="Send Message to selected Sessions" onclick="document.getElementById('sessionsFormAction').value='injectSessions'; return checkSessions(this, 'sessionIds');"/>
		</div>
	</fieldset>
</form>

<jsp:include page="inc/footer.jsp"/>

</body>
</html>