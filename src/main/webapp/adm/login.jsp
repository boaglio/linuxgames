<center>
<%
  String usuarioId=(String)request.getSession().getAttribute("usuario_id");
  String usuario = request.getParameter("usuario");
  String senha = request.getParameter("senha");

  // checa se usuario expirou
  if ((usuarioId==null)&&(usuario==null)&&(senha==null))
  {
   %>
   <BR>
   <BR>
<form action="login.jsp" method="post">
 <table border width=40% >
 <tr bgcolor="#7f7f7f"><td><b>Entrada no Sistema</b></td> </tr>
<tr bgcolor="#b7b7b7"> <td align=center><table cellpadding=3>
<tr> <td colspan=2 align=center>
 </tr>
<tr> <td align="right"><b>Usu&aacute;rio:</b></td>
<td><input name="usuario" size=20 value=''></td> </tr>
<tr> <td align="right"><b>Senha:</b></td>
<td><input name="senha" size=20 type=password></td> </tr>
<tr> <td colspan=2 align=center><input type=submit value="Entrar">
</td> </tr>
</table></td></tr></table>
</form>
<br>
 <script language="JavaScript">
   document.forms[0].usuario.focus();
 </script>
   <%
  }
  else
  {
   if (usuarioId==null)
   {
    if (usuario.equals("fb") && senha.equals("lgrocks") )
     {
     // grava a variavel global do usuario_id
	 request.getSession().setAttribute("usuario_id","fb");
     %>
     <jsp:forward page="index.jsp"/>
     <%
	 }
	else
	{
	 %>
	   <h2><font color="red">Senha errada, seu besta! </font></h2>
  	   <h3><font color="blue">Por que nao tenta hackear a NASA? </font></h2>
	 <%
	}
   }
  }
  %>
</center>
