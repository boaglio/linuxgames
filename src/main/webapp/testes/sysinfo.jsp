<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ page import="br.com.linuxgames.util.Constantes" %>
 <h1>
  <font color="blue">CRUZADA</font>
 </h1>
 <fieldset style="width:80%">
  <legend>SIC Info</legend>
   <table width="80%">
    <tr>
     <th align=right><font class="text8b">SIC Release:</font></th>
     <td>&nbsp;<font class="text8"><%=Constantes.VERSAO_DO_SISTEMA%></font></td>
     <th align=right><font class="text8b">Build Date:</font></th>
     <td><font class="text8"><%=Constantes.DATA_DA_VERSAO_DO_SISTEMA%></font></td>
    </tr>
   </table>
  </fieldset>
 <fieldset style="width:80%">
  <legend>Servidor de Aplica&ccedil;&atilde;o</legend>
   <table width="80%">
	<tr>
	 <td>
	  <%= getServletConfig().getServletContext().getServerInfo() %>, Servlet version
	  <%= getServletConfig().getServletContext().getMajorVersion() %>.<%= getServletConfig().getServletContext().getMinorVersion() %>
	 </td>
	</tr>
   </table>
  </fieldset>
  <fieldset style="width:80%">
   <legend>VM Info</legend>
   <table width="80%">
	<tr>
  	 <th>Java Virtual Machine</th>
	  <td>
	   <%= System.getProperties().get("java.vm.vendor") %>
	   <%= System.getProperties().get("java.vm.name") %>
	   <%= System.getProperties().get("java.vm.version") %>
	  </td>
	 </tr>
	 <tr>
	  <th>Java RE</th>
	  <td>
	   <a href="<%= System.getProperties().get("java.vendor.url") %>"><%= System.getProperties().get("java.vendor") %></a>
	   <%= System.getProperties().get("java.version") %> @ <%= System.getProperties().get("java.home") %>
	  </td>
	 </tr>
	 <tr>
	  <th>Platform</th>
	  <td>
	   <%= System.getProperties().get("os.name") %>/<%= System.getProperties().get("os.arch") %> <%= System.getProperties().get("os.version") %>
	  </td>
	 </tr>
    </table>
   </fieldset>
 <br/>
</body>
</html>