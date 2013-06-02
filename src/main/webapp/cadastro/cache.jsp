<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization groups="admins"/>
<c:import url="cabecalho.jsp"/>
<html>
<body>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="cache.titulo"/></h1>

 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.tamanhoCacheDeNovidades"/>
  </span>
  <span class="form">
   <mtw:out value="tamanhoCacheDeNovidades"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.hits"/>
  </span>
  <span class="form">
   <mtw:out value="hitsDeNovidades"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.ultimoRefreshDoCache"/>
  </span>
  <span class="form">
   <fmt:formatDate value="${ultimoRefreshDoCacheDeNovidades}"  pattern="E','dd/MM/yy hh'h'mm'm'ss's' a"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.diffTempo"/>
  </span>
  <span class="form">
   <mtw:out value="diffTempoDeNovidades"/>
  </span>
 </div>
 <div class="row">
  <span class="botoes">
   <form name="formulario"  id="formulario" action="/games/cache-refreshNovidades" method="post">
    <input type="submit" value="<mtw:i18n key="cache.refreshDeNovidades"/>" />
   </form>
  </span>
 </div>
  <div class="row">
   <hr>
  </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.tamanhoCacheDeUpdates"/>
  </span>
  <span class="form">
   <mtw:out value="tamanhoCacheDeUpdates"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.hits"/>
  </span>
  <span class="form">
   <mtw:out value="hitsDeUpdates"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.ultimoRefreshDoCache"/>
  </span>
  <span class="form">
   <fmt:formatDate value="${ultimoRefreshDoCacheDeUpdates}"  pattern="E','dd/MM/yy hh'h'mm'm'ss's' a"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.diffTempo"/>
  </span>
  <span class="form">
   <mtw:out value="diffTempoDeUpdates"/>
  </span>
 </div>
 <div class="row">
  <span class="botoes">
   <form name="formulario"  id="formulario" action="/games/cache-refreshUpdates" method="post">
    <input type="submit" value="<mtw:i18n key="cache.refreshDeUpdates"/>" />
   </form>
  </span>
 </div>
  <div class="row">
  <hr>
  </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.tamanhoCacheDeArtigos"/>
  </span>
  <span class="form">
   <mtw:out value="tamanhoCacheDeArtigos"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.hits"/>
  </span>
  <span class="form">
   <mtw:out value="hitsDeArtigos"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.ultimoRefreshDoCache"/>
  </span>
  <span class="form">
   <fmt:formatDate value="${ultimoRefreshDoCacheDeArtigos}"  pattern="E','dd/MM/yy hh'h'mm'm'ss's' a"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.diffTempo"/>
  </span>
  <span class="form">
   <mtw:out value="diffTempoDeArtigos"/>
  </span>
 </div>
 <div class="row">
  <span class="botoes">
   <form name="formulario"  id="formulario" action="/games/cache-refreshArtigos" method="post">
    <input type="submit" value="<mtw:i18n key="cache.refreshDeArtigos"/>" />
   </form>
  </span>
 </div>
  <div class="row">
  <hr>
  </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.tamanhoCacheDeArtigosExternos"/>
  </span>
  <span class="form">
   <mtw:out value="tamanhoCacheDeArtigosExternos"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.tamanhoCacheDeArtigosExternos"/>
  </span>
  <span class="form">
   <mtw:out value="tamanhoCacheDeArtigosExternos"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.hits"/>
  </span>
  <span class="form">
   <mtw:out value="hitsDeArtigosExternos"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.ultimoRefreshDoCache"/>
  </span>
  <span class="form">
   <fmt:formatDate value="${ultimoRefreshDoCacheDeArtigosExternos}"  pattern="E','dd/MM/yy hh'h'mm'm'ss's' a"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.diffTempo"/>
  </span>
  <span class="form">
   <mtw:out value="diffTempo"/>
  </span>
 </div>
 <div class="row">
  <span class="botoes">
   <form name="formulario"  id="formulario" action="/games/cache-refreshArtigosExternos" method="post">
    <input type="submit" value="<mtw:i18n key="cache.refreshDeArtigosExternos"/>" />
   </form>
  </span>
 </div>
  <div class="row">
   <hr>
  </div>
  <div class="row">
  <span class="label">
   <mtw:i18n key="cache.tamanhoCacheDeJogos"/>
  </span>
  <span class="form">
   <mtw:out value="tamanhoCacheDeJogos"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.hits"/>
  </span>
  <span class="form">
   <mtw:out value="hitsDeJogos"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.ultimoRefreshDoCache"/>
  </span>
  <span class="form">
   <fmt:formatDate value="${ultimoRefreshDoCacheDeJogos}"  pattern="E','dd/MM/yy hh'h'mm'm'ss's' a"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.diffTempo"/>
  </span>
  <span class="form">
   <mtw:out value="diffTempoDeJogos"/>
  </span>
 </div>
  <div class="row">
  <span class="label">
   <mtw:i18n key="cache.tamanhoCacheDeJogos"/> EN
  </span>
  <span class="form">
   <mtw:out value="tamanhoCacheDeJogosEN"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.hits"/>
  </span>
  <span class="form">
   <mtw:out value="hitsDeJogosEN"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.ultimoRefreshDoCache"/>
  </span>
  <span class="form">
   <fmt:formatDate value="${ultimoRefreshDoCacheDeJogosEN}"  pattern="E','dd/MM/yy hh'h'mm'm'ss's' a"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.diffTempo"/>
  </span>
  <span class="form">
   <mtw:out value="diffTempoDeJogosEN"/>
  </span>
 </div>
 <div class="row">
  <span class="botoes">
   <form name="formulario"  id="formulario" action="/games/cache-refreshJogos" method="post">
    <input type="submit" value="<mtw:i18n key="cache.refreshDeJogos"/>" />
   </form>
  </span>
 </div>
  <div class="row">
   <hr>
  </div>
  <div class="row">
  <span class="label">
   <mtw:i18n key="cache.tamanhoCacheDeEmuladores"/>
  </span>
  <span class="form">
   <mtw:out value="tamanhoCacheDeEmuladores"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.hits"/>
  </span>
  <span class="form">
   <mtw:out value="hitsDeEmuladores"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.ultimoRefreshDoCache"/>
  </span>
  <span class="form">
   <fmt:formatDate value="${ultimoRefreshDoCacheDeEmuladores}"  pattern="E','dd/MM/yy hh'h'mm'm'ss's' a"/>
  </span>
 </div>
 <div class="row">
  <span class="label">
   <mtw:i18n key="cache.diffTempo"/>
  </span>
  <span class="form">
   <mtw:out value="diffTempoDeEmuladores"/>
  </span>
 </div>

 <div class="spacer">
  &nbsp;
 </div>

 <div class="row">
  <span class="botoes">
   <form name="formulario"  id="formulario" action="/games/cache-refreshEmuladores" method="post">
    <input type="submit" value="<mtw:i18n key="cache.refreshDeEmuladores"/>" />
   </form>
  </span>
 </div>

</div>


 </body>
</html>