<%@ page isErrorPage="true" %>
<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%
    Object statusCode = request.getAttribute("javax.servlet.error.status_code");
    Object exceptionType = request.getAttribute("javax.servlet.error.exception_type");
    Object message = request.getAttribute("javax.servlet.error.message");
%>

<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

 <!-- erro.jsp -->

<html>
 <body bgcolor="#000000" text="#FFFFFF">
  <head>
	<title><mtw:i18n key="erro.titulo"/></title>
	<script language="JavaScript">
	 function showErr() {
	   document.getElementById('err').style.display='block'
	 }
	</script>
  </head>
<br/>
<center>
<br/>
<hr>
<h2><mtw:i18n key="erro.msgPrincipal"/></h2>
<br/>
 <p align="right">
  <img src="/img/babycry1.gif" />
 </p>
<br/>
<h1><mtw:i18n key="erro.msgSecundaria"/></h1>
<br/>
<hr>
 <a href="javascript:showErr()" style="color:white;text-decoration:none"><mtw:i18n key="erro.detalheDoErro"/></a>
<br/><br/>
</center>

<div id="err" style="display:none">
<h2>Error Page</h2>
<P><a href="<%=response.encodeURL(request.getContextPath()) %>">Back</A></P>
<hr>
<table cellpadding="2" CELLSPACING="2" BORDER="1" width="100%">
    <tr>
	<td width="20%"><b>Status Code</b></td>
	<td width="80%"><%= statusCode %></td>
    </tr>
    <tr>
	<td width="20%"><b>Exception Type</b></td>
	<td width="80%"><%= exceptionType %></td>
    </tr>
    <tr>
	<td width="20%"><b>Message</b></td>
	<td width="80%"><%= message %></td>
    </tr>
    <tr>
	<td width="20%"><b>Exception</b></td>
	<td width="80%">
	    <%
		if( exception != null )
		{
		    out.print("<pre>");
		    exception.printStackTrace(new PrintWriter(out));
		    out.print("</pre>");
		}
	    %>
	</td>
    </tr>
    <tr>
	<td width="20%"><b>Root Cause</b></td>
	<td>
	    <%
		if( (exception != null) && (exception instanceof ServletException) )
		{
		    Throwable cause = ((ServletException)exception).getRootCause();
		    if( cause != null )
		    {
			out.print("<pre>");
			cause.printStackTrace(new PrintWriter(out));
			out.print("</pre>");
		    }
		}
	    %>
	</td>
    </tr>
</table>

<hr>
Header List
<table border=3>
<tr>
 <td>Name</td>
 <td>Value</td>
</tr>
<%
String name  = "";
String value = "";

java.util.Enumeration headers = request.getHeaderNames();
while(headers.hasMoreElements())
{
 name  = (String) headers.nextElement();
 value = request.getHeader(name);
%>
<tr>
 <td><%=name%></td>
 <td><%=value%></td>
</tr>
<%
}
%>
</table>

Attribute List
<!-- "javax.servlet.jsp.jspException" for getting an Exception -->
<table border=3>
<%
java.util.Enumeration attributes = request.getAttributeNames();
while(attributes.hasMoreElements())
{
 name  = (String) attributes.nextElement();

 if (request.getAttribute(name) == null)
 {
  value = "null";
 }
 else
 {
  value = request.getAttribute(name).toString();
 }
%>
<tr>
 <td><%=name%></td>
 <td><%=value%></td>
</tr>
<%
}
%>
</table>

</div>

</body>


</html>
