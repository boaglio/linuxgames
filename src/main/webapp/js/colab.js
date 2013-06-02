

/* valida solicitacao */
function validateApr()
{
 var optRepr = document.getElementById('optRep').checked;
 var optApr = document.getElementById('optApr').checked;
 var txt = document.getElementById('justificativa').value.length;

  if (!optRepr && !optApr)
  {
   alert("Selecione aprovado ou reprovado!");
   return false;
  }

  if (optRepr && txt==0)
  {
   alert("Toda reprovacao precisa de justificativa!");
   document.getElementById('justificativa').focus();
   return false;
  }
  return true;
}

/* desliga os campos sugeridos */

function desligaCampos() {
	document.getElementById('idioma').disabled=true;
	document.getElementById('tidioma').disabled=true;
	 document.getElementById('tnome').disabled=true;
	 document.getElementById('tdescricao').disabled=true;
	 document.getElementById('tfabricante_id').disabled=true;
	 document.getElementById('tlicenca_id').disabled=true;
	 document.getElementById('ttipo').disabled=true;
	 document.getElementById('taberto').disabled=true;
	 document.getElementById('tjogaEmRede').disabled=true;
	 document.getElementById('tprecisa3d').disabled=true;
	 document.getElementById('ttemSom').disabled=true;
	 document.getElementById('tconsoleOuX11').disabled=true;
	 document.getElementById('tsiteOficial').disabled=true;
}


function verificaAlteracao(campo1, campo2, campoResultado) {

	var resultado ;
	if (document.getElementById(campo1).value==document.getElementById(campo2).value) {
		resultado="<font color=green>Iguais</font>"
	}
	else	{
		resultado="<font color=red>Diferentes</font>"
	}

	document.getElementById(campoResultado).innerHTML=resultado;

}


function verificaAlteracaoCombo(combo1, combo2, campoResultado) {

	var resultado ;
	var combo1 = document.getElementById(combo1).selectedIndex ;
	var combo2 = document.getElementById(combo2).selectedIndex ;

	if (combo1==combo2) {
		resultado="<font color=green>Iguais</font>"
	}
	else	{
		resultado="<font color=red>Diferentes</font>"
	}

	document.getElementById(campoResultado).innerHTML=resultado;

}


function verificaAlteracaoRadio(radio1, radio2, campoResultado) {

	var resultado ;
	var radio1ck = document.getElementById(radio1).checked ;
	var radio1value = document.getElementById(radio1).value;
	var radio2ck = document.getElementById(radio2).checked ;
	var radio2value = document.getElementById(radio2).value ;

	if ( (radio1ck==radio2ck) && (radio1value==radio2value) ) {
		resultado="<font color=green>Iguais</font>"
	}
	else	{
		resultado="<font color=red>Diferentes</font>"
	}

	document.getElementById(campoResultado).innerHTML=resultado;

}

function copiaCampos(origem,destino) {

	document.getElementById(destino).value=document.getElementById(origem).value;

}
