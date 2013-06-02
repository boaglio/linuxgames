
// This function does the actual browser detection
function hasIE_hasIE() {
  var ua = navigator.userAgent.toLowerCase();
  return ((ua.indexOf('msie') != -1) && (ua.indexOf('opera') == -1) &&
          (ua.indexOf('webtv') == -1) &&
          (location.href.indexOf('seenIEPage') == -1));
}
// redireciona pagina
function redir(url)
{
 window.location=url;
}

// verifica browser
function verificaBrowser() {
 if (hasIE_hasIE())
  redir("/pleaseTryFirefox.jsp");
 else
  redir("/games/index-home")
}
