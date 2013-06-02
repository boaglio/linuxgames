<%@ page import="java.util.*" %>
<%@ include file="header.jsp" %>

<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
<title>Properties</title>
</head>
<body bgcolor="#FFFFFF">
 <h1>Properties</h1>
<table width="70%" border="4">
<br/><br/>
<%
 Properties pr = System.getProperties();
 TreeSet propKeys = new TreeSet(pr.keySet());
 for (Iterator it = propKeys.iterator(); it.hasNext(); ) {
  String key = (String)it.next();
%>
 <tr>
  <td align="right"><b><%=key%></v></td>
  <td><%=pr.get(key)%></td>
 </tr>
<%
  }
%>
</table>
</body>
</html>