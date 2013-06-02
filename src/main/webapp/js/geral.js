
function printpage() {
window.print();
}

function winBRopen(theURL, Name, popW, popH, scroll) {
	var winleft = (screen.width - popW) / 2;
	var winUp = (screen.height - popH) / 2;
	winProp = 'width=' + popW + ',height=' + popH + ',left=' + winleft + ',top=' + winUp + ',scrollbars=' + scroll + ', resizable';
	Win = window.open(theURL, Name, winProp);
	if (Number(navigator.appVersion) >= 4) {
		Win.window.focus();
	}
}

/* exibe e esconde um elemento do HTML */
function mudaDIV(me)
{
  if ((me.style.visibility=="hidden")||(me.style.visibility=="collapse"))
  {
   me.style.visibility="visible";
   me.style.display="block";
  }
  else
  {
   me.style.visibility="hidden";
   me.style.display="none";
  }
}
