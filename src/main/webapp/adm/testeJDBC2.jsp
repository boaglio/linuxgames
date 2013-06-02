
<%@ page import="java.sql.*" %>
<%
String userName = "postgres";
String password = "*****";
String url = "jdbc:postgresql://127.0.0.1:5432/lg";
Class.forName ("org.postgresql.Driver").newInstance ();
Connection connection = DriverManager.getConnection (url, userName, password);
Statement statement = null;
ResultSet rs = null;
%>

<html><body>
 <h1>Dados com conexao JDBC</h1>
 <hr>
<%
try
{
Class.forName("org.postgresql.Driver").newInstance();
statement = connection.createStatement();
rs = statement.executeQuery("SELECT * FROM SITELOG order by DATA desc");
while (rs.next()) {
 out.println(rs.getString("data")+"- <b>Versao:"+rs.getString("versao")+"</b>-"+rs.getString("descricao")+"<hr>");
}
rs.close();
 }
 catch (Exception e)
 {
%>
 <h2><font color='red'>Erro na chamada JDBC</font></h2>
 <b>Erro:</b>
 <br><br>
 <%= e.getMessage() %>
<%
}
%>
<br/>
<br/>
<center>[<a href="javascript:history.back();"> voltar </a>]</center>
<br/>
<br/>

</body></html>