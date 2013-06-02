
<%@include file="menuAdmin.jsp" %>

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>


 <!-- appserver.jsp -->

 <fieldset style="width:80%">
  <legend>Application Server Info</legend>
   <table width="80%">
        <tr>
         <td>
          <%
                  ServletContext sc = pageContext.getServletContext();
                  String server = sc.getServerInfo();
                  String vrServlet = ""+sc.getMajorVersion()+"."+sc.getMinorVersion();
                  String vrJsp = JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion();
       %>
        <table>
         <tr>
          <td align="right"><b>Servidor</b></td>
          <td>&nbsp;</td>
          <td><%= server %></td>
         </tr>
         <tr>
          <td align="right"><b>Servlet</b></td>
          <td>&nbsp;</td>
          <td><%= vrServlet %></td>
         </tr>
         <tr>
          <td align="right"><b>JSP</b></td>
          <td>&nbsp;</td>
          <td><%= vrJsp %></td>
         </tr>
        </table>
         </td>
        </tr>
   </table>
  </fieldset>
  <fieldset style="width:80%">
   <legend>VM Info</legend>
   <table width="80%">
        <tr>
         <th>Java VM</th>
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
