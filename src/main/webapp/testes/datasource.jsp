<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ include file="header.jsp" %>

<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
<title>Teste do Data Source...</title>
</head>
<body bgcolor="#FFFFFF">

<%
// assim nao funcionou:
//Context envCtx = (Context) initCtx.lookup("java:comp/env");
// (DataSource) ctx.lookup("java:comp/env/" + Constantes.JNDI_DATASOURCE);
//DataSource ds = (DataSource) envCtx.lookup("jdbc/OMC-DS-OMC");
//ctx = new InitialContext();

Context initCtx = new InitialContext();
// DataSource ds = (DataSource) initCtx.lookup("jdbc/linuxgames");
DataSource ds = (DataSource)  initCtx.lookup("java:comp/env/" + Constantes.JNDI_DATASOURCE);
Connection conn = ds.getConnection();
%>
 <center>
  <h1>
   <h2>Data source <font color="blue"><%=Constantes.JNDI_DATASOURCE%></font> acessado com sucesso!
  </h1>
 </center>

</body>
</html>