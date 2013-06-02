//<label><input type="checkbox" onclick="javascript:checkUncheckAllCB(this, 'applicationIds');"/>select all</label>
function checkUncheckAllCB(theElementCB, name) {
	var elements = theElementCB.form.elements;
	for(i = 0; i < elements.length; ++i) {
		theField = elements[i];
		if(theField.type == 'checkbox' && theField.name == name) {
			theField.checked = theElementCB.checked;
		}
	}
}

function hasCheckedCB(theElementCB, name) {
	var elements = theElementCB.form.elements;
	for(i = 0; i < elements.length; ++i) {
		theField = elements[i];
		if(theField.type == 'checkbox' && theField.name == name && theField.checked) {
			return true;
		}
	}
	return false;
}

/*****************************************************************************/
/*****************************************************************************/
/*****************************************************************************/

function addWindowOnLoadHandler(handler) {
	var oldHandler = window.onload;
	if (typeof oldHandler != 'function') {
		window.onload = handler;
	} else {
		window.onload = function() {
			oldHandler();
			handler();
		};
	}
}

/*****************************************************************************/
/*****************************************************************************/
/*****************************************************************************/

function showElementWithId(id) {
	var node = document.getElementById(id);
	if (node) {
		node.style.display='inline';
	}
}
function hideElementWithId(id) {
	var node = document.getElementById(id);
	if (node) {
		node.style.display='none';
	}
}

/*****************************************************************************/
/*****************************************************************************/
/*****************************************************************************/

/* http://www.ccs.neu.edu/home/dherman/javascript/behavior/ */
myrules = {
	'.infoballoonable': {
		onmouseover: function() {
			showElementWithId(this.id + '-infoballoon');
		},
		onmouseout: function() {
			hideElementWithId(this.id + '-infoballoon');
		}
	}
}
Behavior.register(myrules);

/*****************************************************************************/
/*****************************************************************************/
/*****************************************************************************/

// page refresh:
// define reloadPage()
// <body onload="setAutorefresh(document.getElementById('autorefresh'))">
// <input type="text" name="autorefresh" id="autorefresh" value="<core:out value='<%=request.getAttribute("autorefresh")%>'/>" size="3" maxlength="3" onchange="setAutorefresh(this); return false;"/>
var shouldRefreshPage = false;
function setAutorefresh(textfield) {
	shouldRefreshPage = false;
	if (textfield == null) {
		return false;
	}
	var delay = -1;
	if (isNaN(parseInt(textfield.value))) {
		textfield.value = '';
	} else {
		delay = parseInt(textfield.value);
		if (delay > 0 && delay < 5) {
			delay = 5;
		}
		textfield.value = delay;
	}
	if (delay > 0) {
		shouldRefreshPage = true;
		window.setTimeout('autoReloadPage()', 1000*delay);
	}
	return true;
}
function autoReloadPage() {
	if (shouldRefreshPage) {
		reloadPage();
	}
}
//function reloadPage() {
//}

/*****************************************************************************/
/*****************************************************************************/
/*****************************************************************************/

// Source: http://www.alistapart.com/articles/zebratables
// <body onload="stripeTable('playlist', '#fff', '#edf3fe');">
// <table id="playlist" cellspacing="0">

// this function is needed to work around 
// a bug in IE related to element attributes
function hasClass(obj) {
	var result = false;
	if (obj.getAttributeNode("class") != null) {
		result = obj.getAttributeNode("class").value;
	}
	return result;
}

function stripeTable(id) {

	// the flag we'll use to keep track of 
	// whether the current row is odd or even
	var even = false;

	// if arguments are provided to specify the colours
	// of the even & odd rows, then use the them;
	// otherwise use the following defaults:
	var evenColor = arguments[1] ? arguments[1] : "#fff";
	var oddColor = arguments[2] ? arguments[2] : "#eee";

	// obtain a reference to the desired table
	// if no such table exists, abort
	var table = document.getElementById(id);
	if (! table) { return; }

	// by definition, tables can have more than one tbody
	// element, so we'll have to get the list of child
	// &lt;tbody&gt;s 
	var tbodies = table.getElementsByTagName("tbody");

	// and iterate through them...
	for (var h = 0; h < tbodies.length; h++) {

		// find all the &lt;tr&gt; elements... 
		var trs = tbodies[h].getElementsByTagName("tr");

		// ... and iterate through them
		for (var i = 0; i < trs.length; i++) {

			// avoid rows that have a class attribute
			// or backgroundColor style
			if (! hasClass(trs[i]) && ! trs[i].style.backgroundColor) {

				// get all the cells in this row...
				var tds = trs[i].getElementsByTagName("td");

				// and iterate through them...
				for (var j = 0; j < tds.length; j++) {
 
 					var mytd = tds[j];

					// avoid cells that have a class attribute
					// or backgroundColor style
					if (! hasClass(mytd) && ! mytd.style.backgroundColor) {

						mytd.style.backgroundColor = even ? evenColor : oddColor;

					}
				}
			}
			// flip from odd to even, or vice-versa
			even = ! even;
		}
	}
}

addWindowOnLoadHandler(function () {
	var strippable = document.getElementsBySelector('.strippable');
	for (var i = 0; i < strippable.length; ++i) {
		var myElement = strippable[i];
		stripeTable(myElement.id);
	}
})
