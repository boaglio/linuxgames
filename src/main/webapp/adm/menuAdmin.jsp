<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <title>LinuxGames - Admin</title>
 <link rel="stylesheet" rev="stylesheet" href="admin.css" type="text/css"/>
 <script src="admin.js" type="text/javascript"></script>
 </head>

<jsp:include page="login.jsp"/>

<%
 String usuarioId=(String)request.getSession().getAttribute("usuario_id");
 if (usuarioId!=null)
  {
  %>
<body>
<div class="menu">
  <ul>
   <li><a href="index.jsp">Index</a></li>
   <li><a href="appserver.jsp">App. Server</a></li>
   <li><a href="ds.jsp">Data Source</a></li>
   <li><a href="sysinfo.jsp">System info</a></li>
   <li><a href="properties.jsp">Properties</a></li>
  </ul>
 <br style="clear: left" />
</div>
</body>
<%
  }
%>

</html>