<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<c:import url="cabecalho.jsp"/>
<c:import url="menuTopo.jsp"/>
<c:import url="menuEsq.jsp"/>

<script language="JavaScript" type="text/javascript" src="/js/FusionCharts.js"></script>

<div id="centercontentW">
 <img src="<mtw:contextPath/>/img/tux_gaming_md_wht.gif" width="120" height="92">
  <h1> </h1>
 <div class="tituloLista">
 <a class="buttonC" href="javascript:toggleLayer('A');">A</a>
 <a class="buttonC" href="javascript:toggleLayer('B');">B</a>
 <a class="buttonC" href="javascript:toggleLayer('C');">C</a>
 <a class="buttonC" href="javascript:toggleLayer('D');">D</a>
 <a class="buttonC" href="javascript:toggleLayer('E');">E</a>
 <a class="buttonC" href="javascript:toggleLayer('F');">F</a>
 <a class="buttonC" href="javascript:toggleLayer('G');">G</a>
 <a class="buttonC" href="javascript:toggleLayer('H');">H</a>
 <a class="buttonC" href="javascript:toggleLayer('I');">I</a>
 <a class="buttonC" href="javascript:toggleLayer('J');">J</a>
 <a class="buttonC" href="javascript:toggleLayer('K');">K</a>
 <a class="buttonC" href="javascript:toggleLayer('L');">L</a>
 <a class="buttonC" href="javascript:toggleLayer('M');">M</a>
 <a class="buttonC" href="javascript:toggleLayer('N');">N</a>
 <a class="buttonC" href="javascript:toggleLayer('O');">O</a>
 <a class="buttonC" href="javascript:toggleLayer('P');">P</a>
 <a class="buttonC" href="javascript:toggleLayer('Q');">Q</a>
 <a class="buttonC" href="javascript:toggleLayer('R');">R</a>
 <a class="buttonC" href="javascript:toggleLayer('S');">S</a>
 <a class="buttonC" href="javascript:toggleLayer('T');">T</a>
 <a class="buttonC" href="javascript:toggleLayer('U');">U</a>
 <a class="buttonC" href="javascript:toggleLayer('V');">V</a>
 <a class="buttonC" href="javascript:toggleLayer('W');">W</a>
 <a class="buttonC" href="javascript:toggleLayer('X');">X</a>
 <a class="buttonC" href="javascript:toggleLayer('Y');">Y</a>
 <a class="buttonC" href="javascript:toggleLayer('Z');">Z</a>
 <a class="button" href="javascript:resetEmuladores();"><mtw:i18n key="emulador.voltar"/></a>
 </div>
 <div id="A">
  <div id="lista">
   <mtw:list value="emuladoresComA">
   <h2>A</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="B">
  <div id="lista">
   <mtw:list value="emuladoresComB">
   <h2>B</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="C">
  <div id="lista">
   <mtw:list value="emuladoresComC">
   <h2>C</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="D">
  <div id="lista">
   <mtw:list value="emuladoresComD">
   <h2>D</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="E">
  <div id="lista">
   <mtw:list value="emuladoresComE">
   <h2>E</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="F">
  <div id="lista">
   <mtw:list value="emuladoresComF">
   <h2>F</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="G">
  <div id="lista">
   <mtw:list value="emuladoresComG">
   <h2>G</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="H">
  <div id="lista">
   <mtw:list value="emuladoresComH">
   <h2>H</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="I">
  <div id="lista">
   <mtw:list value="emuladoresComI">
   <h2>I</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="J">
  <div id="lista">
   <mtw:list value="emuladoresComJ">
   <h2>J</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="K">
  <div id="lista">
   <mtw:list value="emuladoresComK">
   <h2>K</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="L">
  <div id="lista">
   <mtw:list value="emuladoresComL">
   <h2>L</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="M">
  <div id="lista">
   <mtw:list value="emuladoresComM">
   <h2>M</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="N">
  <div id="lista">
   <mtw:list value="emuladoresComN">
   <h2>N</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="O">
  <div id="lista">
   <mtw:list value="emuladoresComO">
   <h2>O</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="P">
  <div id="lista">
   <mtw:list value="emuladoresComP">
   <h2>P</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="Q">
  <div id="lista">
   <mtw:list value="emuladoresComQ">
   <h2>Q</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="R">
  <div id="lista">
   <mtw:list value="emuladoresComR">
   <h2>R</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="S">
  <div id="lista">
   <mtw:list value="emuladoresComS">
   <h2>S</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="T">
  <div id="lista">
   <mtw:list value="emuladoresComT">
   <h2>T</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="U">
  <div id="lista">
   <mtw:list value="emuladoresComU">
   <h2>U</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="V">
  <div id="lista">
   <mtw:list value="emuladoresComV">
   <h2>V</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="W">
  <div id="lista">
   <mtw:list value="emuladoresComW">
   <h2>W</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="X">
  <div id="lista">
   <mtw:list value="emuladoresComX">
   <h2>X</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="Y">
  <div id="lista">
   <mtw:list value="emuladoresComY">
   <h2>Y</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="Z">
  <div id="lista">
   <mtw:list value="emuladoresComZ">
    <h2>Z</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div class="spacer"></div>
 <h1> </h1>
 <div class="tituloLista">
  <a class="button" href="javascript:toggleLayer('tipoAmiga');">&nbsp;<mtw:i18n key="emulador.tipoAmiga"/>&nbsp;</a>
  <a class="button" href="javascript:toggleLayer('tipoAtari');"><mtw:i18n key="emulador.tipoAtari"/></a>
  <a class="button" href="javascript:toggleLayer('tipoGameBoy');"><mtw:i18n key="emulador.tipoGameBoy"/></a>
  <a class="button" href="javascript:toggleLayer('tipoInfinity');"><mtw:i18n key="emulador.tipoInfinity"/></a>
  <a class="button" href="javascript:toggleLayer('tipoMacintosh');"><mtw:i18n key="emulador.tipoMacintosh"/></a>
  <a class="button" href="javascript:toggleLayer('tipoMasterSystem');"><mtw:i18n key="emulador.tipoMasterSystem"/></a>
 </div>
 <div class="spacer">&nbsp;</div>
 <div class="tituloLista">
  <a class="button" href="javascript:toggleLayer('tipoMegaDrive');"><mtw:i18n key="emulador.tipoMegaDrive"/></a>
  <a class="button" href="javascript:toggleLayer('tipoMSX');"><mtw:i18n key="emulador.tipoMSX"/></a>
  <a class="button" href="javascript:toggleLayer('tipoN64');"><mtw:i18n key="emulador.tipoN64"/></a>
  <a class="button" href="javascript:toggleLayer('tipoNeoGeo');"><mtw:i18n key="emulador.tipoNeoGeo"/></a>
  <a class="button" href="javascript:toggleLayer('tipoNES');"><mtw:i18n key="emulador.tipoNES"/></a>
 </div>
 <div class="spacer">&nbsp;</div>
 <div class="tituloLista">
  <a class="button" href="javascript:toggleLayer('tipoPlaystation');"><mtw:i18n key="emulador.tipoPlaystation"/></a>
  <a class="button" href="javascript:toggleLayer('tipoSCI');"><mtw:i18n key="emulador.tipoSCI"/></a>
  <a class="button" href="javascript:toggleLayer('tipoSNES');"><mtw:i18n key="emulador.tipoSNES"/></a>
  <a class="button" href="javascript:toggleLayer('tipoTK90');"><mtw:i18n key="emulador.tipoTK90"/></a>
  <a class="button" href="javascript:toggleLayer('tipoWindows');"><mtw:i18n key="emulador.tipoWindows"/></a>
  <a class="button" href="javascript:toggleLayer('tipoVarios');"><mtw:i18n key="emulador.tipoVarios"/></a>
 </div>
 <div class="spacer">&nbsp;</div>

 <div id="tipoAmiga">
  <div id="lista">
   <mtw:list value="tipoAmiga">
    <h2><mtw:i18n key="emulador.tipoAmiga"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoAmigaContador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoAmigaTotal"><mtw:out value="tipoAmigaContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoAtari">
  <div id="lista">
   <mtw:list value="tipoAtari">
   <h2><mtw:i18n key="emulador.tipoAtari"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoAtariContador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoAtariTotal"><mtw:out value="tipoAtariContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoGameBoy">
  <div id="lista">
   <mtw:list value="tipoGameBoy">
   <h2><mtw:i18n key="emulador.tipoGameBoy"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoGameBoyContador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoGameBoyTotal"><mtw:out value="tipoGameBoyContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoInfinity">
  <div id="lista">
   <mtw:list value="tipoInfinity">
   <h2><mtw:i18n key="emulador.tipoInfinity"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoInfinityContador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoInfinityTotal"><mtw:out value="tipoInfinityContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoMacintosh">
  <div id="lista">
   <mtw:list value="tipoMacintosh">
   <h2><mtw:i18n key="emulador.tipoMacintosh"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoMacintoshContador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoMacintoshTotal"><mtw:out value="tipoMacintoshContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoMasterSystem">
  <div id="lista">
   <mtw:list value="tipoMasterSystem">
   <h2><mtw:i18n key="emulador.tipoMasterSystem"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoMasterSystemContador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoMasterSystemTotal"><mtw:out value="tipoMasterSystemContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoMegaDrive">
  <div id="lista">
   <mtw:list value="tipoMegaDrive">
   <h2><mtw:i18n key="emulador.tipoMegaDrive"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoMegaDriveContador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoMegaDriveTotal"><mtw:out value="tipoMegaDriveContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoMSX">
  <div id="lista">
   <mtw:list value="tipoMSX">
   <h2><mtw:i18n key="emulador.tipoMSX"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoMSXContador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoMSXTotal"><mtw:out value="tipoMSXContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoN64">
  <div id="lista">
   <mtw:list value="tipoN64">
   <h2><mtw:i18n key="emulador.tipoN64"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoN64Contador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoN64Total"><mtw:out value="tipoN64Contador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoNeoGeo">
  <div id="lista">
   <mtw:list value="tipoNeoGeo">
   <h2><mtw:i18n key="emulador.tipoNeoGeo"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoNeoGeoContador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoNeoGeoTotal"><mtw:out value="tipoNeoGeoContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoNES">
  <div id="lista">
   <mtw:list value="tipoNES">
   <h2><mtw:i18n key="emulador.tipoNES"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoNESContador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoNESTotal"><mtw:out value="tipoNESContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoPlaystation">
  <div id="lista">
   <mtw:list value="tipoPlaystation">
   <h2><mtw:i18n key="emulador.tipoPlaystation"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoPlaystationContador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoPlaystationTotal"><mtw:out value="tipoPlaystationContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoSCI">
  <div id="lista">
   <mtw:list value="tipoSCI">
   <h2><mtw:i18n key="emulador.tipoSCI"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoSCIContador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoSCITotal"><mtw:out value="tipoSCIContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoSNES">
  <div id="lista">
   <mtw:list value="tipoSNES">
   <h2><mtw:i18n key="emulador.tipoSNES"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoSNESContador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoSNESTotal"><mtw:out value="tipoSNESContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoTK90">
  <div id="lista">
   <mtw:list value="tipoTK90">
   <h2><mtw:i18n key="emulador.tipoTK90"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoTK90Contador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoTK90Total"><mtw:out value="tipoTK90Contador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoWindows">
  <div id="lista">
   <mtw:list value="tipoWindows">
   <h2><mtw:i18n key="emulador.tipoWindows"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoWindowsContador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoWindowsTotal"><mtw:out value="tipoWindowsContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoVarios">
  <div id="lista">
   <mtw:list value="tipoVarios">
   <h2><mtw:i18n key="emulador.tipoVarios"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoVariosContador">
     <ul>
      <li><a href="/games/infoEmulador-emulador/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoVariosTotal"><mtw:out value="tipoVariosContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>

 <div id="chartdiv" align="center">????</div>

<c:import url="menuDir.jsp"/>
</body>
</html>


<script type="text/javascript">

  var chart = new FusionCharts("/charts/FCF_Pie3D.swf", "ChartId", "600", "550");

  var tipoAmigaTotal=+(<mtw:out value="tipoAmigaTotal"/>)+1;
  var tipoAtariTotal=+(<mtw:out value="tipoAtariTotal"/>)+1;
  var tipoGameBoyTotal=+(<mtw:out value="tipoGameBoyTotal"/>)+1;
  var tipoInfinityTotal=+(<mtw:out value="tipoInfinityTotal"/>)+1;
  var tipoMacintoshTotal=+(<mtw:out value="tipoMacintoshTotal"/>)+1;
  var tipoMasterSystemTotal=+(<mtw:out value="tipoMasterSystemTotal"/>)+1;
  var tipoMegaDriveTotal=+(<mtw:out value="tipoMegaDriveTotal"/>)+1;
  var tipoMSXTotal=+(<mtw:out value="tipoMSXTotal"/>)+1;
  var tipoN64Total=+(<mtw:out value="tipoN64Total"/>)+1;
  var tipoNeoGeoTotal=+(<mtw:out value="tipoNeoGeoTotal"/>)+1;
  var tipoNESTotal=+(<mtw:out value="tipoNESTotal"/>)+1;
  var tipoPlaystationTotal=+(<mtw:out value="tipoPlaystationTotal"/>)+1;
  var tipoSCITotal=+(<mtw:out value="tipoSCITotal"/>)+1;
  var tipoSNESTotal=+(<mtw:out value="tipoSNESTotal"/>)+1;
  var tipoTK90Total=+(<mtw:out value="tipoTK90Total"/>)+1;
  var tipoWindowsTotal=+(<mtw:out value="tipoWindowsTotal"/>)+1;
  var tipoVariosTotal=+(<mtw:out value="tipoVariosTotal"/>)+1;

  var dadosXMLinicio ="<graph showNames='1' showValues='0' decimalPrecision='0'>";
  var dadosXML1="<set name='<mtw:i18n key="emulador.tipoAmiga"/>' value='"+tipoAmigaTotal+"'/>";
  var dadosXML2="<set name='<mtw:i18n key="emulador.tipoAtari"/>' value='"+tipoAtariTotal+"'/>";
  var dadosXML3="<set name='<mtw:i18n key="emulador.tipoGameBoy"/>' value='"+tipoGameBoyTotal+"'/>";
  var dadosXML4="<set name='<mtw:i18n key="emulador.tipoInfinity"/>' value='"+tipoInfinityTotal+"'/>";
  var dadosXML5="<set name='<mtw:i18n key="emulador.tipoMacintosh"/>' value='"+tipoMacintoshTotal+"'/>";
  var dadosXML6="<set name='<mtw:i18n key="emulador.tipoMasterSystem"/>' value='"+tipoMasterSystemTotal+"'/>";
  var dadosXML7="<set name='<mtw:i18n key="emulador.tipoMegaDrive"/>' value='"+tipoMegaDriveTotal+"'/>";
  var dadosXML8="<set name='<mtw:i18n key="emulador.tipoMSX"/>' value='"+tipoMSXTotal+"'/>";
  var dadosXML9="<set name='<mtw:i18n key="emulador.tipoN64"/>' value='"+tipoN64Total+"'/>";
  var dadosXML10="<set name='<mtw:i18n key="emulador.tipoNeoGeo"/>' value='"+tipoNeoGeoTotal+"'/>";
  var dadosXML11="<set name='<mtw:i18n key="emulador.tipoNES"/>' value='"+tipoNESTotal+"'/>";
  var dadosXML12="<set name='<mtw:i18n key="emulador.tipoPlaystation"/>' value='"+tipoPlaystationTotal+"'/>";
  var dadosXML13="<set name='<mtw:i18n key="emulador.tipoSCI"/>' value='"+tipoSCITotal+"'/>";
  var dadosXML14="<set name='<mtw:i18n key="emulador.tipoSNES"/>' value='"+tipoSNESTotal+"'/>";
  var dadosXML15="<set name='<mtw:i18n key="emulador.tipoTK90"/>' value='"+tipoTK90Total+"'/>";
  var dadosXML16="<set name='<mtw:i18n key="emulador.tipoWindows"/>' value='"+tipoWindowsTotal+"'/>";
  var dadosXML17="<set name='<mtw:i18n key="emulador.tipoVarios"/>' value='"+tipoVariosTotal+"'/>";
  var dadosXMLfim ="</graph>";

  chart.setDataXML(dadosXMLinicio+dadosXML1+dadosXML2+dadosXML3+dadosXML4+dadosXML5+dadosXML6+
		  dadosXML7+dadosXML8+dadosXML9+dadosXML10+dadosXML11+dadosXML12+dadosXML13+dadosXML14+
		  dadosXML15+dadosXML16+dadosXML17+dadosXMLfim);

  chart.render("chartdiv");
</script>
