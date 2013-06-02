function Iterator(obj){
	if(! (obj instanceof Array) ) {
		throw new SyntaxError("Iterator Only works with Array");	
	}
	
	this.values	= obj;
	this.current = 0;
	
	this.hasNext = function(){
		if(this.current > this.values.length -1 ) {
			return false;
		} else {
			return true;
		}
	}
	
	this.next = function(){
		this.current++;
		return this.values[this.current - 1];
	}
}


function HashMap()
{
    this.array = new Array();
    
    this.Entry = function( key, value ) {
	    this.key = key;
	    this.value = value;
	}
    
    this.put = function( key, value )	{
	    if( ( typeof key != "undefined" ) && ( typeof value != "undefined" ) )
	    {
	    	if(! (this.containsKey(key) )) {
	        	this.array.push( new this.Entry(key, value) );
	    	} else {
	    		for(var i=0; i<this.array.length; i++) {
	    			if(this.array[i].key == key) {
	    				this.array[i].value = value;
	    			}
	    		}
	    	}
	    }
	}
    
    this.get = function( key )	{
    	for(var i=0; i<this.array.length; i++) {
    		if(this.array[i].key == key)
    			return this.array[i].value;
    	}
	    return null;
	}
	
	this.keys = function() {
		var k = new Array();
		for(var i=0; i<this.array.length; i++) {
			k[i] = this.array[i].key;			
		}
		return k;
	}
	
	this.size = function(){
		return this.array.length;
	}
	
	this.containsKey = function(key){
		for(var i=0; i<this.array.length; i++) {
			if(this.array[i].key == key)
				return true;		
		}
		return false;
	}
	
	this.containsValue = function(value) {
		for(var i=0; i<this.array.length; i++) {
			if(this.array[i].value == value)
				return true;
		}
		return false;
	}
	
	this.iterator = function(){
		return new Iterator(this.array);
	}
}

// Objeto AJAX para comunicação Assincrona com um servidor de aplicações WEB
var mtw = {
	Version	:	'2.5',
	
	removeOptions: function(id) {
		document.getElementById(id).options.length = 0;
	},
	
	addOptions: function(id, response, keyDef, valDef){
		comp = document.getElementById(id);
		comp.options.length = 0;
		
		if(keyDef !== undefined && valDef !== undefined){
			comp.options[comp.length] = new Option(valDef, keyDef);
		}
		
		for(i=0;i<response.size();i++){
			map = response.getObject(i);
			comp.options[comp.length] = new Option(map.value, map.key);			
		}
	}
}

mtw.request = function(){
	this.url				=	null;
	this.charset			=	_MENTA_DEFAULT_CHARSET;		// Default: UTF-8
	this.metodo				=	"POST";
	this.processaresultado	=	null;
	this.Header				=	[];
	this.processaErro		=	null;
	this.FORMV				=	null;
	this.PARS				=	null;
	this.asynchronous		=	true;
	this.useMessage			=	null;
	this.hashHeader			=	{};
	this.alive				=	false;
	this.aborted			=	false;
	this.processAbort		=	null;
}

mtw.request.prototype = {
	
	coc: function() {
		
		var hasInnerActionPattern = /\x2E*\x2E/g;
		if(hasInnerActionPattern.test(this.url)) {
			
			var innerHTMLPattern = /(\.)+(innerHTML).*/;
			var innerHTMLSplit = /(\.)+(innerHTML)/;
		
			if( innerHTMLPattern.test(this.url) ) {
				var splited = this.url.split( innerHTMLSplit );
				var idFinded = splited[3].replace(/(\.)+(mtw)$/, "");
				
				this.processaresultado = function(trans) {
					new mtw.response(trans).innerHTML(idFinded);
				}
				
			} else {
				
				var innerAction = this.url.split(hasInnerActionPattern);
				innerAction = innerAction[1];
				
				try {
					// Procura uma função com o mesmo nome da innerAction
					eval(innerAction);
					this.processaresultado = eval(innerAction);
					
				} catch (e) {
				
				// não faz nada com o resultado
				// envia um requisição sem resposta
				this.processaresultado = function(){};
					
				}
				
			}
			
		}
	},
	
	
	setUrl: function(url) {
			this.url = url;
	},
	
	setCharset: function(charset){
		this.charset = charset;
	},
	
	setMethod: function(method){
			if(method == "GET" || method == "POST") {
				this.metodo = method;
			} else {
				this.metodo = 'POST';
			}
	},
	
	onSuccess: function(fn){
			this.processaresultado = fn;
	},
	
	onError: function(fn){
			this.processaErro = fn;
	},
	
	onAbort: function(fn){
			this.processAbort = fn;
	},
	
	addHeader:	function(h,v) {
					this.Header[h] = v;
				},
	
	delHeader:	function(h) {
					delete(this.Header[h]);
				},
	
	setHeader:	function() {
					if(this.httprequest==null) { return;}
					var m = ""; 
					for(var h in this.Header) {
						try {
							this.httprequest.setRequestHeader(h,this.Header[h]);	
						} catch (e) {
							// this try is necessary if somebody change Array Object.
						}
					}
				},
	
	noCache:	function(){
		this.addHeader("Cache-Control", "no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
		this.addHeader("Pragma", "no-cache");
	},
	
	waitComplete: function() {
		this.asynchronous = false;
	},
	
	send:	function() {
		if(this.url==null) {
			alert("url not setted!");
			return; 
		}
		if(this.processaresultado == null){
			this.coc();
		}
		
		this.httprequest = null;
	   	if (window.XMLHttpRequest) { // Mozilla, Safari,...
         	this.httprequest = new XMLHttpRequest();
    	} else if (window.ActiveXObject) { // IE
         	try {
		     	 this.httprequest = new ActiveXObject("Msxml2.XMLHTTP");
	     	} catch (e) {
           		try {
	           	 this.httprequest = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
		if(this.httprequest!=null&&this.httprequest!=undefined) {
			var obj = this;
			this.httprequest.onreadystatechange = 	function() {
														obj.processaretorno.call(obj);
													}
			if(this.metodo == "GET") {
				this.url += "?" + this.PARS;
				this.addHeader("Content-Type", "charset=" + this.charset );
			} else {
				this.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=" + this.charset );
			}
			
			this.addHeader('MentaAjax-Version', mtw.Version);
			
			if(this.useMessage != null) {
				this.useLoadingMessageFunction(this.useMessage);
			}
			
        	this.httprequest.open(this.metodo,this.url, this.asynchronous);
			this.setHeader();
			this.alive = true;
			this.aborted = false;
	        this.httprequest.send(this.PARS);
		}
	},
	
	processaretorno:	function() {
		if(this.httprequest.readyState==4) {
			this.alive = false;
			
			// if be aborted, find onAbort method
			if(this.aborted){
				if(this.processAbort != null || ( typeof this.processAbort == 'function' )) {
					this.processAbort(this.httprequest);						
				}
				return;
			}
			
			if(this.httprequest.status==200) {
				this.hideMessageFunction();
				this.processaresultado(this.httprequest);
			} else { 
				this.hideMessageFunction();
				if(this.processaErro == null || ( typeof this.processaErro != 'function' )) {
					this.processaErro = function(){
						alert("Error\nCode: "+ this.httprequest.status + " " + this.httprequest.statusText + "\nURL: " + this.url);						
					};
				}
				this.processaErro(this.httprequest);
			}
		}
	},
	
	isAlive: function(){
		return this.alive;
	},
	
	abort: function(){
		
		if( !(this.httprequest == null || this.httprequest === undefined) ) {
			if(this.isAlive()){
				this.aborted = true;
				this.httprequest.abort();	
			}
		}
		
	},
	
	addParameter: function(key, value){
		if(this.FORMV == null) {
			this.FORMV = document.createElement("form");
			this.FORMV.setAttribute("id", "mtwFormVirtual");
			this.FORMV.style.display = 'none';
			document.body.appendChild(this.FORMV);
		}
		var node = document.createElement("input");
		node.setAttribute("name", key);
		node.setAttribute("value", value);
		this.FORMV.appendChild(node);
		this.PARS = this.serialize();
	},
	
	// só para ser utilizada neste objeto
	serialize:	function(){
		var elements = this.FORMV.childNodes;
		var e;
		var result = "";
		for(var i=0; i<elements.length; i++) {
			e = elements.item(i);
			result += encodeURIComponent(e.name) + "=" + encodeURIComponent(e.value);
			if(elements.length > i + 1) {
				result += "&";
			}		
		}
		return result;
	},

	useLoadingMessage: function(message) {
		if(typeof message == 'function')
			this.useLoadingMessageFunction = message;
			
		this.useMessage = (message == undefined) ? "Loading..." : message ;
	},
	
	hideMessage: function(fn) {
		if(typeof fn == 'function')
			this.hideMessageFunction = fn;
	},

	useLoadingMessageFunction: function(message) {
	  var loadingMessage = message;
	  
	    var disabledZone = document.getElementById('disabledZone');
	    if (!disabledZone) {
	      disabledZone = document.createElement('div');
	      disabledZone.setAttribute('id', 'disabledZone');
	      disabledZone.style.position = "absolute";
	      disabledZone.style.zIndex = "1000";
	      disabledZone.style.left = "0px";
	      disabledZone.style.top = "0px";
	      disabledZone.style.width = "100%";
	      disabledZone.style.height = "100%";
	      document.body.appendChild(disabledZone);
	      var messageZone = document.createElement('div');
	      messageZone.setAttribute('id', 'messageZone');
	      messageZone.style.position = "absolute";
	      messageZone.style.top = "0px";
	      messageZone.style.right = "0px";
	      messageZone.style.background = "blue";
	      messageZone.style.color = "white";
	      messageZone.style.fontFamily = "Arial,Helvetica,sans-serif";
	      messageZone.style.padding = "4px";
	      disabledZone.appendChild(messageZone);
	      var text = document.createTextNode(loadingMessage);
	      messageZone.appendChild(text);
	    }
	    else {
	      document.getElementById('disabledZone').innerHTML = loadingMessage;
	      disabledZone.style.visibility = 'visible';
	    }
	},
    
    hideMessageFunction:  function() {
	   	if(document.getElementById('disabledZone')){
	   		document.getElementById('disabledZone').style.visibility = 'hidden';
	   	}
    }
}

mtw.response = function(transport){
	this.jsonData = null;
	this.textData = null;
	
	this.textData = transport.responseText;
	
	this.constructor = function() {
		if(this.textData != null ) {
			if( this.isJSON(this.textData) ) {
				try {
					this.jsonData = eval('(' + this.textData + ')');	
				} catch (e) { }
			}
		}
	}

	this.isJSON = function(string) {
    	var str = string.replace(/\\./g, '@').replace(/"[^"\\\n\r]*"/g, '');
    	return (/^[,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t]*$/).test(str);
  	},

	this.getObject = function(index){
		if(typeof index == 'undefined') { index = 0 };
		return this.jsonData.obj[index];
	},
	
	this.getValue = function(key){
		var data = this.jsonData.obj;
		for(var i = 0; i < this.size(); i++){
			if(data[i].key == key) {
				return data[i].value;
			}
		}
		return '';
	}, 
	
	this.size = function(){
		if(this.jsonData != null) {
			return this.jsonData.obj.length;	
		} else {
			return 0;
		}
	},
	
	this.getString = function(){
		return this.textData;
	},
	
	this.innerHTML = function(id){
		document.getElementById(id).innerHTML = this.getString();	
	},
	
	this.replaceOptions = function(id){
		mtw.removeOptions(id);
		mtw.addOptions(id, this);
	},
	
	this.isMap = function(){
		var obj;
		var result = false;
		for(var i=0; i<this.size(); i++) {
			obj = this.getObject(i);
			if(obj.key && obj.value) {
				result = true;
				if(i > 5) break;
			} else {
				result = false;
				break;
			}
		}
		return result;
	},
	
	this.hashMap = function(){
		var map = new HashMap();
		if(this.isMap()) {
			for(var i=0; i<this.size(); i++) {
				map.put(this.getObject(i).key, this.getObject(i).value)
			}	
		}
		return map;
	}
	
	this.iterator = function() {
		if(this.isMap()) {
			return this.hashMap().iterator();
		} else {
			// Is List
			var amostra = this.getObject();
			var keys = new Array();
			for(var k in amostra) {
				keys[keys.length] = k;
			}

			var list = new Array();
			for(var i=0; i<this.size(); i++) {
				list[i] = this.getObject(i);					
			}
			
			return new Iterator(list);
		}
	}
	
	this.constructor();
}