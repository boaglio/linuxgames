<%@ page import="java.util.*" %>

<%@include file="menuAdmin.jsp" %>

<h1>Properties List</h1>
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