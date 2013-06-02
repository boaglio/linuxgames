var m_iInterval;
var m_Height;
//window.onload = wl;
var iScroll=0;
var arrLinks;
var arrTitles;
var arrCursor = 0;
var arrMax;
window.onload=wl;

function wl() {
  m_iInterval = setInterval(ontimer, 10);

  var base = document.getElementById("jump_base");

  if ((base==null) || (typeof(base)=="undefined")) return;

  m_Height = base.offsetHeight;

  var divi = parseInt(m_Height/5);
  m_Height = divi*5;

  var td1 = document.getElementById("td1");
  var td2 = document.getElementById("td2");
  var td3 = document.getElementById("td3");
  td1.height = m_Height-5;
  td2.height = m_Height-5;
  td3.height = m_Height-5;

  arrLinks = new Array();
  arrTitles = new Array();

  setupLinks();
  arrMax = arrLinks.length-1;
  setLink();
}

function setLink() {
  var ilink = document.getElementById("jump_link");
  ilink.innerHTML = arrTitles[arrCursor];
  ilink.href = arrLinks[arrCursor];
}

function ontimer() {
  var base = document.getElementById("jump_base");

  if ((base==null) || (typeof(base)=="undefined")) return;

  iScroll+=5;
  if (iScroll>(m_Height*2)) {
    iScroll=0;
    arrCursor++;
    if (arrCursor>arrMax)
      arrCursor=0;
    setLink();
  }
  if (iScroll==m_Height) {
    pause();
    m_iInterval = setTimeout(resume, 4000);
  }
  base.scrollTop=iScroll;
}

function pause() {
  clearInterval(m_iInterval);
}

function resume() {
  m_iInterval = setInterval(ontimer, 10);
}