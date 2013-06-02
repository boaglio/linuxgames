<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />

<c:import url="/head.jsp"/>

<body>

<div id="overDiv"></div>
<%--
 <!--[if IE]>
<div id="warning">
<p><mtw:i18n key="cabecalho.warningIE"/></p>
</div>
<![endif]-->
-->
<%--
<div id="warning" style="display:disabled">
<p><mtw:i18n key="cabecalho.melhorVisualizado"/></p>
</div>
--%>
<!-- google stuff -->


<div id="banner">
 <span class="logo"><a href="http://www.linuxgames.com.br/"><img id="imglogo" alt="LinuxGames.com.br" src="/img/linuxgames-logo.gif"/></a></span>
 <span class="banner">
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-40494725-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>
 <script type="text/javascript">
  google_ad_client = "pub-3816383189082763";
  google_ad_slot = "0173387953";
  google_ad_width = 468;
  google_ad_height = 60;
</script>
<script type="text/javascript" src="http://pagead2.googlesyndication.com/pagead/show_ads.js"></script>
 </span>
</div>
