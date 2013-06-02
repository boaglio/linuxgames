
<SCRIPT type=text/javascript>
<!--
window.onload=buildMenu;
function buildMenu(id) {
var d = document.getElementById(id);
	for (var i = 0; i<=10; i++) {
		if (document.getElementById('smenu'+i)) {document.getElementById('smenu'+i).style.display='none';}
	}
if (d) {d.style.display='block';}
}

function shrink(id) {
 document.getElementById(id).style.display='none';
}
//-->
</SCRIPT>

<!-- start menu HTML -->
<div id="menu">
<DIV id=menu>
<DL>
 <DT onmouseover="javascript:buildMenu('smenu0');"><a href="/">Aplica&ccedil;&atilde;o</a>
  <DD id=smenu0>
  <UL>
     <li>
   	  <a onmouseover="javascript:buildMenu('smenu0');" onmouseout="shrink('smenu0')" href="http://www.linuxgames.com.br/cadastro/" target="_BLANK">CRUDs</a>
     </li>
     <li>
   	  <a onmouseover="javascript:buildMenu('smenu0');" onmouseout="shrink('smenu0')" href="properties.jsp">Properties</a>
     </li>
     <li>
	  <a onmouseover="javascript:buildMenu('smenu0');" onmouseout="shrink('smenu0')" href="datasource.jsp">Teste do Data Source</a>
     </li>
     <li>
	  <a onmouseover="javascript:buildMenu('smenu0');" onmouseout="shrink('smenu0')" href="dbtest.jsp">Teste de acesso &agrave;s tabelas</a>
     </li>
     <li>
   	  <a onmouseover="javascript:buildMenu('smenu0');" onmouseout="shrink('smenu0')" href="/AdminMessageInject">Sessions</a>
     </li>
   </UL>
  </DD>
 </DT>
</DL>

</DIV>
<br/> <br/> <br/> <br/>
