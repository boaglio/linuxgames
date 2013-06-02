<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ include file="header.jsp" %>

<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
<title>Teste de acesso &agrave;s tabelas...</title>
</head>
<body bgcolor="#FFFFFF">
<h2>Teste de acesso &agrave;s tabelas...</h2>
<table width="70%" border="4">
<%
Context initCtx = new InitialContext();
DataSource ds = (DataSource)  initCtx.lookup("java:comp/env/" + Constantes.JNDI_DATASOURCE);
Connection connection = ds.getConnection();

String tables[] =  {
  "acesso",
  "agencia_bancaria",
  "agencia_bancaria_contato",
  "agencia_bancaria_contato_email",
  "agencia_bancaria_contato_telefo",
  "ata",
  "integrante",
  "reuniao",
  "banco",
  "bem",
  "cargo",
  "conta_corrente",
  "conta_corrente_lancamento",
  "departamento",
  "documento_pagar",
  "emprestimo",
  "emprestimo_parcela",
  "fornecedor",
  "fornecedor_email",
  "fornecedor_endereco",
  "fornecedor_telefone",
  "funcionario",
  "funcionario_email",
  "funcionario_endereco",
  "funcionario_telefone",
  "historico_caixa",
  "usuario",
  "livro_caixa",
  "logerros",
  "mes_extenso",
  "numero_extenso",
  "patrimonio",
  "propriedade",
  "tarefa",
  "tipo_documento",
  "tipo_lancamento",
  "tipo_servico",
  "tipo_unidade",
  "trabalho",
  "unidade",
  "unidade_email",
  "unidade_endereco",
  "unidade_telefone",
  "usuario_unidade"
  };

Statement stmt = null;
ResultSet rset = null;

for (int i=0;i<tables.length;i++)
{
%>
 <tr>
<%
   if (tables[i].equals("")) break;
   try {
       stmt = connection.createStatement();
       rset = stmt.executeQuery("SELECT count(0) FROM "+tables[i]);
       while (rset.next()) {
%>
 <td><%=tables[i]%></td>
 <td><%=rset.getInt(1)%> linhas</td>
 <td><font color="green">Sucesso!</font></td>
<%
         }
		} catch (Exception e) {
%>
 <td><%=tables[i]%></td>
 <td>ERROR=<%=e.getMessage()%></td>
 <td><font color="red">Error!</font></td>
<%
	    }

%>
 </tr>
<%
}
   if (rset != null)
    rset.close();
   if (stmt != null)
    stmt.close();
   if (connection != null)
    connection.close();
%>
</body>
</html>