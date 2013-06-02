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
  <a class="button" href="javascript:resetGames();"><mtw:i18n key="jogo.voltar"/></a>
 </div>
 <div id="A">
  <div id="lista">
   <mtw:list value="jogosComA">
    <h2>A</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="B">
  <div id="lista">
   <mtw:list value="jogosComB">
    <h2>B</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="C">
  <div id="lista">
   <mtw:list value="jogosComC">
    <h2>C</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="D">
  <div id="lista">
   <mtw:list value="jogosComD">
    <h2>D</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="E">
  <div id="lista">
   <mtw:list value="jogosComE">
    <h2>E</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="F">
  <div id="lista">
   <mtw:list value="jogosComF">
    <h2>F</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="G">
  <div id="lista">
   <mtw:list value="jogosComG">
    <h2>G</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="H">
  <div id="lista">
   <mtw:list value="jogosComH">
    <h2>H</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="I">
  <div id="lista">
   <mtw:list value="jogosComI">
    <h2>I</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="J">
  <div id="lista">
   <mtw:list value="jogosComJ">
    <h2>J</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="K">
  <div id="lista">
   <mtw:list value="jogosComK">
    <h2>K</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="L">
  <div id="lista">
   <mtw:list value="jogosComL">
    <h2>L</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="M">
  <div id="lista">
   <mtw:list value="jogosComM">
    <h2>M</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="N">
  <div id="lista">
   <mtw:list value="jogosComN">
    <h2>N</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="O">
  <div id="lista">
   <mtw:list value="jogosComO">
    <h2>O</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="P">
  <div id="lista">
   <mtw:list value="jogosComP">
    <h2>P</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="Q">
  <div id="lista">
   <mtw:list value="jogosComQ">
    <h2>Q</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="R">
  <div id="lista">
   <mtw:list value="jogosComR">
    <h2>R</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="S">
  <div id="lista">
   <mtw:list value="jogosComS">
    <h2>S</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="T">
  <div id="lista">
   <mtw:list value="jogosComT">
    <h2>T</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="U">
  <div id="lista">
   <mtw:list value="jogosComU">
    <h2>U</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="V">
  <div id="lista">
   <mtw:list value="jogosComV">
    <h2>V</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="W">
  <div id="lista">
   <mtw:list value="jogosComW">
    <h2>W</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="X">
  <div id="lista">
   <mtw:list value="jogosComX">
    <h2>X</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="Y">
  <div id="lista">
   <mtw:list value="jogosComY">
    <h2>Y</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="Z">
  <div id="lista">
   <mtw:list value="jogosComZ">
    <h2>Z</h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop>
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div class="spacer"></div>
 <h1> </h1>
 <div class="tituloLista">
  <a class="button" href="javascript:toggleLayer('tipoAcao');">&nbsp;<mtw:i18n key="jogo.tipoAcao"/>&nbsp;</a>
  <a class="button" href="javascript:toggleLayer('tipoAdventure');"><mtw:i18n key="jogo.tipoAdventure"/></a>
  <a class="button" href="javascript:toggleLayer('tipoArcade');"><mtw:i18n key="jogo.tipoArcade"/></a>
  <a class="button" href="javascript:toggleLayer('tipoEsporte');"><mtw:i18n key="jogo.tipoEsporte"/></a>
  <a class="button" href="javascript:toggleLayer('tipoEstrategia');"><mtw:i18n key="jogo.tipoEstrategia"/></a>
  <a class="button" href="javascript:toggleLayer('tipoFPS');"><mtw:i18n key="jogo.tipoFPS"/></a>
  <a class="button" href="javascript:toggleLayer('tipoInfantil');"><mtw:i18n key="jogo.tipoInfantil"/></a>
 </div>
 <div class="spacer">&nbsp;</div>
 <div class="tituloLista">
  <a class="button" href="javascript:toggleLayer('tipoLuta');"><mtw:i18n key="jogo.tipoLuta"/></a>
  <a class="button" href="javascript:toggleLayer('tipoMMORPG');"><mtw:i18n key="jogo.tipoMMORPG"/></a>
  <a class="button" href="javascript:toggleLayer('tipoQuebraCabeca');"><mtw:i18n key="jogo.tipoQuebraCabeca"/></a>
  <a class="button" href="javascript:toggleLayer('tipoRPG');"><mtw:i18n key="jogo.tipoRPG"/></a>
  <a class="button" href="javascript:toggleLayer('tipoSimulacao');"><mtw:i18n key="jogo.tipoSimulacao"/></a>
 </div>
 <div class="spacer">&nbsp;</div>

 <div id="tipoAcao">
  <div id="lista">
   <mtw:list value="tipoAcao">
    <h2><mtw:i18n key="jogo.tipoAcao"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoAcaoContador">
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoAcaoTotal"><mtw:out value="tipoAcaoContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoAdventure">
  <div id="lista">
   <mtw:list value="tipoAdventure">
    <h2><mtw:i18n key="jogo.tipoAdventure"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoAdventureContador">
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoAdventureTotal"><mtw:out value="tipoAdventureContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoArcade">
  <div id="lista">
   <mtw:list value="tipoArcade">
    <h2><mtw:i18n key="jogo.tipoArcade"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoArcadeContador">
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoArcadeTotal"><mtw:out value="tipoArcadeContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoEsporte">
  <div id="lista">
   <mtw:list value="tipoEsporte">
    <h2><mtw:i18n key="jogo.tipoEsporte"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoEsporteContador">
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoEsporteTotal"><mtw:out value="tipoEsporteContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoEstrategia">
  <div id="lista">
   <mtw:list value="tipoEstrategia">
    <h2><mtw:i18n key="jogo.tipoEstrategia"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoEstrategiaContador">
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoEstrategiaTotal"><mtw:out value="tipoEstrategiaContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoFPS">
  <div id="lista">
   <mtw:list value="tipoFPS">
    <h2><mtw:i18n key="jogo.tipoFPS"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoFPSContador">
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoFPSTotal"><mtw:out value="tipoFPSContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoInfantil">
  <div id="lista">
   <mtw:list value="tipoInfantil">
    <h2><mtw:i18n key="jogo.tipoInfantil"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoInfantilContador">
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoInfantilTotal"><mtw:out value="tipoInfantilContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoLuta">
  <div id="lista">
   <mtw:list value="tipoLuta">
    <h2><mtw:i18n key="jogo.tipoLuta"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoLutaContador">
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoLutaTotal"><mtw:out value="tipoLutaContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoMMORPG">
  <div id="lista">
   <mtw:list value="tipoMMORPG">
    <h2><mtw:i18n key="jogo.tipoMMORPG"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoMMORPGContador">
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoMMORPGTotal"><mtw:out value="tipoMMORPGContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoQuebraCabeca">
  <div id="lista">
   <mtw:list value="tipoQuebraCabeca">
    <h2><mtw:i18n key="jogo.tipoQuebraCabeca"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoQuebraCabecaContador">
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoQuebraCabecaTotal"><mtw:out value="tipoQuebraCabecaContador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoRPG">
  <div id="lista">
   <mtw:list value="tipoRPG">
    <h2><mtw:i18n key="jogo.tipoRPG"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop  counter="tipoRPGcontador">
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoRPGtotal"><mtw:out value="tipoRPGcontador"/></c:set>
    </mtw:loop>
   </mtw:list>
  </div>
 </div>
 <div id="tipoSimulacao">
  <div id="lista">
   <mtw:list value="tipoSimulacao">
    <h2><mtw:i18n key="jogo.tipoSimulacao"/></h2>
    <mtw:isEmpty><mtw:i18n key="aplicacao.resultado.nenhumRegistro"/></mtw:isEmpty>
    <mtw:loop counter="tipoSimulacaoContador">
     <ul>
      <li><a href="/games/infoJogo-jogo/<mtw:out value="id"/>"><mtw:out value="nome"/></a></li>
     </ul>
     <c:set var="tipoSimulacaoTotal"><mtw:out value="tipoSimulacaoContador"/></c:set>
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

  var tipoAcaoTotal=+(<mtw:out value="tipoAcaoTotal"/>)+1;
  var tipoAdventureTotal=+(<mtw:out value="tipoAdventureTotal"/>)+1;
  var tipoArcadeTotal=+(<mtw:out value="tipoArcadeTotal"/>)+1;
  var tipoEsporteTotal=+(<mtw:out value="tipoEsporteTotal"/>)+1;
  var tipoEstrategiaTotal=+(<mtw:out value="tipoEstrategiaTotal"/>)+1;
  var tipoFPSTotal=+(<mtw:out value="tipoFPSTotal"/>)+1;
 // var tipoInfantilTotal=+(<mtw:out value="tipoInfantilTotal"/>)+1;
  var tipoInfantilTotal=0;
  var tipoLutaTotal=+(<mtw:out value="tipoLutaTotal"/>)+1;
  var tipoMMORPGTotal=+(<mtw:out value="tipoMMORPGTotal"/>)+1;
  var tipoQuebraCabecaTotal=+(<mtw:out value="tipoQuebraCabecaTotal"/>)+1;
  var tipoRPGtotal=+(<mtw:out value="tipoRPGtotal"/>)+1;
  var tipoSimulacaoTotal=+(<mtw:out value="tipoSimulacaoTotal"/>)+1;

  var dadosXMLinicio ="<graph showNames='1' showValues='0' decimalPrecision='0' rotateValues='1' >";
  var dadosXML1="<set name='<mtw:i18n key="jogo.tipoAcao"/>' value='"+tipoAcaoTotal+"'/>";
  var dadosXML2="<set name='<mtw:i18n key="jogo.tipoAdventure"/>' value='"+tipoAdventureTotal+"'/>";
  var dadosXML3="<set name='<mtw:i18n key="jogo.tipoArcade"/>' value='"+tipoArcadeTotal+"'/>";
  var dadosXML4="<set name='<mtw:i18n key="jogo.tipoEsporte"/>' value='"+tipoEsporteTotal+"'/>";
  var dadosXML5="<set name='<mtw:i18n key="jogo.tipoEstrategia"/>' value='"+tipoEstrategiaTotal+"'/>";
  var dadosXML6="<set name='<mtw:i18n key="jogo.tipoFPS"/>' value='"+tipoFPSTotal+"'/>";
  var dadosXML7="<set name='<mtw:i18n key="jogo.tipoInfantil"/>' value='"+tipoInfantilTotal+"'/>";
  var dadosXML8="<set name='<mtw:i18n key="jogo.tipoLuta"/>' value='"+tipoLutaTotal+"'/>";
  var dadosXML9="<set name='<mtw:i18n key="jogo.tipoMMORPG"/>' value='"+tipoMMORPGTotal+"'/>";
  var dadosXML10="<set name='<mtw:i18n key="jogo.tipoQuebraCabeca"/>' value='"+tipoQuebraCabecaTotal+"'/>";
  var dadosXML11="<set name='<mtw:i18n key="jogo.tipoRPG"/>' value='"+tipoRPGtotal+"'/>";
  var dadosXML12="<set name='<mtw:i18n key="jogo.tipoSimulacao"/>' value='"+tipoSimulacaoTotal+"'/>";
  var dadosXMLfim ="</graph>";

  chart.setDataXML(dadosXMLinicio+dadosXML1+dadosXML2+dadosXML3+dadosXML4+dadosXML5+dadosXML6+
		  dadosXML7+dadosXML8+dadosXML9+dadosXML10+dadosXML11+dadosXML12+dadosXMLfim);
  chart.render("chartdiv");
</script>
