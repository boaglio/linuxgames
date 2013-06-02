<%@page session="false" %>
<%@page import="clime.messadmin.core.MessAdmin" %>
<%@taglib prefix="core" uri="http://messadmin.sf.net/core" %>

<%--div style="display: none;">
<p>
	<a href="http://validator.w3.org/check?uri=referer"><img
		src="http://www.w3.org/Icons/valid-html401"
		alt="Valid HTML 4.01!" height="31" width="88"></a>
	<a href="http://validator.w3.org/check?uri=referer"><img
		src="http://www.w3.org/Icons/valid-xhtml10"
		alt="Valid XHTML 1.0!" height="31" width="88" /></a>
	<a href="http://validator.w3.org/check?uri=referer"><img
		src="http://www.w3.org/Icons/valid-xhtml11"
		alt="Valid XHTML 1.1!" height="31" width="88" /></a>
</p>
</div--%>

<div style="font-size: xx-small; font-weight: lighter; color: gray;">
<br/>
<a href="http://messadmin.sourceforge.net" target="_new">MessAdmin</a> <core:out value="<%= MessAdmin.getVersion() %>"/> &copy; 2005-2006 C&eacute;drik LIME, all rights reserved.
</div>
