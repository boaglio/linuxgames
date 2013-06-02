
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="br.com.linuxgames.util.*" %>
<%
Statement statement = null;
ResultSet rs = null;
DataSource ds = null;
String dataSource="jdbc/linuxgames"; // fica no Constantes.JNDI_DATASOURCE;
%>

 <jsp:include page="menuAdmin.jsp"/>

 <h1>Dados com conexao DataSource: [ <%=dataSource%> ]</h1>
  <hr>
<%
 try
 {
  InitialContext initialcontext = new InitialContext();
  ds = (DataSource)initialcontext.lookup("java:/comp/env/"+dataSource);
%>
<%
 Connection conexao = ds.getConnection();
 statement = conexao.createStatement();
rs = statement.executeQuery("SELECT * FROM SITELOG order by DATA desc");
while (rs.next()) {
 out.println(rs.getString("data")+"- <b>Versao:"+rs.getString("versao")+"</b>-"+rs.getString("descricao")+"<hr>");
}
rs.close();
 }
 catch (Exception e)
 {
%>
 <h2><font color='red'>Erro na chamada do DataSource</font></h2>
 <b>Erro:</b>
 <br><br>
 <%= e.getMessage() %>
<%
}
%>
<br/>
<br/>

</body></html>