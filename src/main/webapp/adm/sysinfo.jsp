
<%@include file="menuAdmin.jsp" %>

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>


 <!-- sysinfo.jsp -->
<%
 // busca a data de criacao do arquivo "index.jsp" (não é muito confiável...)
 String root=application.getRealPath("/");
 String infoFile=root+"/admin/index.jsp";
 File 	fileArq	 = new File(infoFile);
 Date 	fileDate = new Date(fileArq.lastModified());
 String	horaDoSistema = DateFormat.getTimeInstance().format(fileDate);
 String dataDoSistema = DateFormat.getDateInstance().format(fileDate);

 // calculo de recursos do sistema
 long memTotal = Runtime.getRuntime().totalMemory()/1024/1024;
 long memLivre = Runtime.getRuntime().freeMemory()/1024/1024;
 long memUsada = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() )/1024/1024;
 long memMaxima = Runtime.getRuntime().maxMemory()/1024/1024;
 double totalLivreEmB=0;
 double totalLivreEmMB=0;
 double totalLivreEmGB=0;
 NumberFormat formatador = new java.text.DecimalFormat("#########.###");
 String sistemaOperacional = System.getProperty("os.name");
 String comandoParaVerEspacoLivre=null;
 String comandoParaVerMemoria=null;
 String resultadoDiscoFinal=null;
 String resultadoMemoriaFinal=null;
 int tipoSO = 2; // 1= Windows, 2= UNIX

 if ((sistemaOperacional.length()>7)&&(sistemaOperacional.substring(0, 7).equalsIgnoreCase("windows"))) {
  comandoParaVerEspacoLivre = "cmd /c dir";
  tipoSO = 1;
 }
 else
  {
  comandoParaVerEspacoLivre = "df -h";
  comandoParaVerMemoria = "vmstat 1 10";
  }
 // calculo do espaco livre
 StringBuffer resultado = new StringBuffer();
 try {
  Process child = Runtime.getRuntime().exec(comandoParaVerEspacoLivre);
  InputStream in = child.getInputStream();
  int c;
  while ((c = in.read()) != -1) {
	resultado.append((char) c);
  }
  in.close();
  }
  catch (IOException e) {
		}

if (tipoSO == 1) {
 resultadoDiscoFinal = resultado.substring(resultado.lastIndexOf("(s)") + 4);
 String totalLivreEmBytes = resultadoDiscoFinal.substring(0,resultadoDiscoFinal.indexOf(" "));
 totalLivreEmBytes = totalLivreEmBytes.replaceAll("\\.+", "");
 if (!totalLivreEmBytes.equals(""))
  totalLivreEmB = Double.parseDouble(totalLivreEmBytes);

 totalLivreEmMB = totalLivreEmB / 1024 / 1024;
 totalLivreEmGB = totalLivreEmMB / 1024;
} else {
  resultadoDiscoFinal = resultado.toString();

 // calculo de VMSTATUS do unix
 resultado = new StringBuffer();
 try {
  Process child = Runtime.getRuntime().exec(comandoParaVerMemoria);
  InputStream in = child.getInputStream();
  int c;
  while ((c = in.read()) != -1) {
	resultado.append((char) c);
  }
  in.close();
  }
  catch (IOException e) {
		}
  resultadoMemoriaFinal = resultado.toString();
}
%>

<center>
<table width=780  border=0 align="center" cellpadding="0" cellspacing="0">
  <tr>
   <td align=center>
    <fieldset>
     <table border=0>
      <tr>
       <td align="center">
        <img src="../images/server.gif" width="47" height="35" border="0" align="middle"/>
        &nbsp;&nbsp;<strong>Recursos do Sistema</strong>
       </td>
      </tr>
      <tr>
       <td>Sistema Operacional: <font color="green"><%=sistemaOperacional%></font></td>
      </tr>
      <tr>
       <td>Sa&iacute;da total da JVM: <font color="green"><%=memTotal%></font> Mb</td>
      </tr>
      <tr>
       <td>Sa&iacute;da utilizada da JVM: <font color="green"><%=memUsada%></font> Mb</td>
      </tr>
      <tr>
       <td>Mem&oacute;ria livre dentro da JVM: <font color="green"><%=memLivre%></font> Mb</td>
      </tr>
      <tr>
       <td>Mem&oacute;ria m&aacute;xima que a JVM inteira pode usar: <font color="green"><%=memMaxima%></font> Mb
        <br>(se passar disso d&aacute; OutOfMemoryException)</td>
      </tr>
      <% if (tipoSO == 1) {%>
      <tr>
       <td>Espa&ccedil;o em disco Dispon&iacute;vel: <font color="green"><%=formatador.format(totalLivreEmMB)%></font> Mb ou
        <font color="green"><%=formatador.format(totalLivreEmGB)%></font> Gb
       </td>
      </tr>
	  <% } else {%>
      <tr>
       <td>Sa&iacute;da do comando: <font color="green"><%=comandoParaVerEspacoLivre%></font>
        <pre> <%=resultadoDiscoFinal%></pre>
       </td>
      </tr>
      <tr>
       <td>Sa&iacute;da do comando: <font color="green"><%=comandoParaVerMemoria%></font>
        <pre> <%=resultadoMemoriaFinal%></pre>
       </td>
      </tr>
      <% } %>
     </table>
    </filedset>
   </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
  <tr>
   <td align=center>&copy; 2008 Sumus Inform&aacute;tica </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
</table>
</center>
</body>
</html>