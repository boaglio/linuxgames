
/*****************************************
 *  (C) LinuxGames - desde 2001
 *****************************************/

function votar() {

 var opt=-1;
 var idEnquete = document.getElementById('idEnquete').value;

 if (document.getElementById('optEnquete1').checked)
  opt=1;
 else if (document.getElementById('optEnquete2').checked)
  opt=2;
 else if (document.getElementById('optEnquete3').checked)
  opt=3;
 else if (document.getElementById('optEnquete4').checked)
  opt=4;

 if (opt==-1)
  {
   alert(WARNING_SELECT_OPT_FIRST);
   return;
  }

  winBRopen('/games/enquete-opt'+opt+'/'+idEnquete,'vote', 440, 350,'no');
}

function resultados() {
 winBRopen('/games/enquete-resultado','resultados', 440, 350,'no');
}

var req;
var comp;

function createAjaxRequest() {
    var ajaxReq;
    if (window.XMLHttpRequest) { // Non-IE browsers
       ajaxReq = new XMLHttpRequest();
   }
    else if (window.ActiveXObject) { // IE
            ajaxReq = new ActiveXObject("Microsoft.XMLHTTP");
        }
    return ajaxReq;
}

function processStateChange() {
    if (req.readyState == 4) {
        if (req.status == 200) { // OK response
            var xml = req.responseXML;
            var entries = xml.getElementsByTagName("entry");
            for (var i = 0; i < entries.length; i++) {
                var entry = entries[i];
                 comp.options[comp.length] = new Option(entry.firstChild.nodeValue,entry.getAttribute("key"));
            }
        }
        else {
           alert("Problem: " + req.statusText);
       }
    }
}


function clearTable() {
     var comp = document.getElementById("listaBusca");
      for (loop = comp.childNodes.length -1; loop >= 0 ; loop--) {
        comp.removeChild(comp.childNodes[loop]);
      }
}


function getValues() {

  var buscaStr = document.getElementById('buscaStr').value;
  if (buscaStr.length<=1)
	return;
  clearTable();
 // exibe combo
 document.getElementById('listaBusca').style.display='inline';
 // carrega combo

  var url = "/games/busca/"+buscaStr;
  comp = document.getElementById("listaBusca");
  req = createAjaxRequest();
   if (window.XMLHttpRequest) { // Non-IE browsers
     req.onreadystatechange = processStateChange;
    try {
       req.open("GET", url, true);
       } catch (e) {
        alert(e);
       }
       req.send(null);
    }
    else if (window.ActiveXObject) { // IE
       if (req) {
           req.onreadystatechange = processStateChange;
            req.open("GET", url, true);
            req.send();
        }
    }
}


function processStateChange() {
    if (req.readyState == 4) {
        if (req.status == 200) { // OK response
            var xml = req.responseXML;
            var entries =xml.getElementsByTagName("entry");
			if (entries.length==0)
			{
			 comp.options[0] = new Option(WARNING_NO_GAME,"0");
			 }
			else
			{
             comp.options[0] = new Option(WARNING_SELECT_ONE_GAME,"0");
             comp.options[0].style.background='black';
            }
            for (var i = 0; i < entries.length; i++) {
                var entry = entries[i];
                 comp.options[comp.length] = new Option(entry.firstChild.nodeValue,entry.getAttribute("key"));
            }
        }
        else {
           alert(WARNING_PROBLEM + req.statusText);
       }
    }
}

function redirectCombo(index) {
 if (index>0)
  {
   var list = document.getElementById('listaBusca');
   window.location="/games/infoJogo-jogo/"+list.options[index].value;
  }
}


function votaJogo() {

 var opt=-1;
 var idVoto = document.getElementById('idVoto').value;
 var idJogo = document.getElementById('idJogo').value;
 var idVotoOpt = idVoto.selectedIndex;

 if (idVotoOpt==-1)
  {
   alert(WARNING_SELECT_OPT_FIRST);
   return;
  }
  //winBRopen('voto.jogo.action?idJogo='+idJogo+'&idVoto='+idVoto,'vote', 210, 300,'no');
  document.getElementById('votoJogo').submit();
}

function toggleLayer(whichLayer)
{
 if (document.getElementById)
 {
  var style2 = document.getElementById(whichLayer).style;
  style2.display = style2.display? "":"block";
 }
 else if (document.all)
 {
  var style2 = document.all[whichLayer].style;
  style2.display = style2.display? "":"block";
 }
 else if (document.layers)
 {
  var style2 = document.layers[whichLayer].style;
  style2.display = style2.display? "":"block";
 }
}

function resetGames() {
 document.getElementById('A').style.display="";
 document.getElementById('B').style.display="";
 document.getElementById('C').style.display="";
 document.getElementById('D').style.display="";
 document.getElementById('E').style.display="";
 document.getElementById('F').style.display="";
 document.getElementById('G').style.display="";
 document.getElementById('H').style.display="";
 document.getElementById('I').style.display="";
 document.getElementById('J').style.display="";
 document.getElementById('K').style.display="";
 document.getElementById('L').style.display="";
 document.getElementById('M').style.display="";
 document.getElementById('N').style.display="";
 document.getElementById('O').style.display="";
 document.getElementById('P').style.display="";
 document.getElementById('Q').style.display="";
 document.getElementById('R').style.display="";
 document.getElementById('S').style.display="";
 document.getElementById('T').style.display="";
 document.getElementById('U').style.display="";
 document.getElementById('V').style.display="";
 document.getElementById('W').style.display="";
 document.getElementById('X').style.display="";
 document.getElementById('Y').style.display="";
 document.getElementById('Z').style.display="";
 document.getElementById('tipoAcao').style.display="";
 document.getElementById('tipoAdventure').style.display="";
 document.getElementById('tipoArcade').style.display="";
 document.getElementById('tipoEsporte').style.display="";
 document.getElementById('tipoFPS').style.display="";
 document.getElementById('tipoInfantil').style.display="";
 document.getElementById('tipoLuta').style.display="";
 document.getElementById('tipoMMORPG').style.display="";
 document.getElementById('tipoQuebraCabeca').style.display="";
 document.getElementById('tipoRPG').style.display="";
 document.getElementById('tipoSimulacao').style.display="";
}

function resetEmuladores() {
 document.getElementById('A').style.display="";
 document.getElementById('B').style.display="";
 document.getElementById('C').style.display="";
 document.getElementById('D').style.display="";
 document.getElementById('E').style.display="";
 document.getElementById('F').style.display="";
 document.getElementById('G').style.display="";
 document.getElementById('H').style.display="";
 document.getElementById('I').style.display="";
 document.getElementById('J').style.display="";
 document.getElementById('K').style.display="";
 document.getElementById('L').style.display="";
 document.getElementById('M').style.display="";
 document.getElementById('N').style.display="";
 document.getElementById('O').style.display="";
 document.getElementById('P').style.display="";
 document.getElementById('Q').style.display="";
 document.getElementById('R').style.display="";
 document.getElementById('S').style.display="";
 document.getElementById('T').style.display="";
 document.getElementById('U').style.display="";
 document.getElementById('V').style.display="";
 document.getElementById('W').style.display="";
 document.getElementById('X').style.display="";
 document.getElementById('Y').style.display="";
 document.getElementById('Z').style.display="";
 document.getElementById('tipoAmiga').style.display="";
 document.getElementById('tipoAtari').style.display="";
 document.getElementById('tipoGameBoy').style.display="";
 document.getElementById('tipoInfinity').style.display="";
 document.getElementById('tipoMacintosh').style.display="";
 document.getElementById('tipoMasterSystem').style.display="";
 document.getElementById('tipoMegaDrive').style.display="";
 document.getElementById('tipoMSX').style.display="";
 document.getElementById('tipoN64').style.display="";
 document.getElementById('tipoNeoGeo').style.display="";
 document.getElementById('tipoNES').style.display="";
 document.getElementById('tipoPlaystation').style.display="";
 document.getElementById('tipoSCI').style.display="";
 document.getElementById('tipoSNES').style.display="";
 document.getElementById('tipoTK90').style.display="";
 document.getElementById('tipoWindows').style.display="";
 document.getElementById('tipoVarios').style.display="";
}

function fazReview() {

 var idVoto = document.getElementById('idVoto').value;
 var idJogo = document.getElementById('id').value;
 var comentario = document.getElementById('comentario').value;

 if (comentario.length==0)
  {
   alert(WARNING_WRITE_COMMENT);
   document.getElementById('comentario').focus();
   return;
  }

 if (idVoto==0)
  {
   alert(WARNING_SELECT_GRADE);
   return;
  }

  document.getElementById('votoReviewFrm').submit();
}

function enviaNovoRoteiro() {

 var distroId = document.getElementById('distro_id').selectedIndex ;
 var descricao = document.getElementById('descricao').value;

 if (distroId==-1)
 {
   alert(WARNING_SELECT_DISTRO);
   document.getElementById('distro_id').focus();
   return false;
 }

 if (descricao.length==0)
 {
   alert(WARNING_WRITE_INSTALL_INFO);
   document.getElementById('descricao').focus();
   return false;
 }

 alert(WARNING_THANKS_FOR_TEXT);
 return true;

}

function enviaNovaDica() {

 var tipoId = document.getElementById('tipo').selectedIndex ;
 var descricao = document.getElementById('descricao').value;

 if (tipoId==-1)
 {
   alert(WARNING_SELECT_TYPE);
   document.getElementById('tipo').focus();
   return false;
 }

 if (descricao.length==0)
 {
   alert(WARNING_WRITE_TIP);
   document.getElementById('descricao').focus();
   return false;
 }
 alert(WARNING_THANKS_FOR_TIP);
 return true;

}

function alteraJogoFavorito() {
 var jogoFavorito = document.getElementById('jogoFavorito').selectedIndex;

 document.getElementById('formFavorito').submit();
}

function alteraEmuFavorito() {
 var emuFavorito = document.getElementById('emuladorFavorito').selectedIndex;

 document.getElementById('formFavorito').submit();
}


function enviaNovaNoticia() {

 var link = document.getElementById('link').value;
 var texto = document.getElementById('texto').value;

 if (texto.length==0)
 {
   alert(WARNING_WRITE_NOV_TXT);
   document.getElementById('texto').focus();
   return false;
 }

 if (link.length==0)
 {
   alert(WARNING_WRITE_NOV_LINK);
   document.getElementById('link').focus();
   return false;
 }

 if (!isUrl(document.getElementById('link').value)) {
  alert(WARNING_NOT_VALID_LINK);
  document.getElementById('link').focus();
  return false;
 }

 alert(WARNING_THANKS_FOR_NOV);
 return true;

}

function textCounter(field, countfield, maxlimit) {
 if (field.value.length > maxlimit)
  field.value = field.value.substring(0, maxlimit);
 else
  countfield.value = maxlimit - field.value.length;
}

function cutNews() {
	textCounter(document.getElementById('texto'),document.getElementById('cont'),120);
}

function isUrl(s) {
	var regexp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/
	return regexp.test(s);
}

