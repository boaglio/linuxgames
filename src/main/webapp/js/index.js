<!-- Original:  Tarjei Davidsen (the@rescueteam.com) -->

var max=0;
function textlist() {
max=textlist.arguments.length;
for (i=0; i<max; i++)
this[i]=textlist.arguments[i];
}

tl = new textlist(
"[eu@meumicro ]$ ",
"[eu@meumicro ]$ cd /games",
"[eu@meumicro ]$ ./quero-me-divertir.sh",
"[eu@meumicro ]$ ...",
"[eu@meumicro ]$ links http://www.linuxgames.com.br"
);

var x = 0; pos = 0;
var l = tl[0].length;

function textticker() {
document.tickform.tickfield.value = tl[x].substring(0, pos) + "_";
if(pos++ == l) {
pos = 0;
setTimeout("textticker()", 2000);
if(++x == max) x = 0;
l = tl[x].length;
} else
setTimeout("textticker()", 100);
}
//  End -->

// scrooling

if (singletext.length>1)
i=1
else
i=0
function start(){
if (document.all){
ieslider1.style.top=sheight
iemarquee(ieslider1)
}
else if (document.layers){
document.ns4slider.document.ns4slider1.top=sheight
document.ns4slider.document.ns4slider1.visibility='show'
ns4marquee(document.ns4slider.document.ns4slider1)
}
else if (document.getElementById&&!document.all){
document.getElementById('ns6slider1').style.top=sheight
ns6marquee(document.getElementById('ns6slider1'))
}
}
function iemarquee(whichdiv){
iediv=eval(whichdiv)
if (iediv.style.pixelTop>0&&iediv.style.pixelTop<=sspeed){
iediv.style.pixelTop=0
setTimeout("iemarquee(iediv)",100)
}
if (iediv.style.pixelTop>=sheight*-1){
iediv.style.pixelTop-=sspeed
setTimeout("iemarquee(iediv)",100)
}
else{
iediv.style.pixelTop=sheight
iediv.innerHTML=singletext[i]
if (i==singletext.length-1)
i=0
else
i++
}
}
function ns4marquee(whichlayer){
ns4layer=eval(whichlayer)
if (ns4layer.top>0&&ns4layer.top<=sspeed){
ns4layer.top=0
setTimeout("ns4marquee(ns4layer)",100)
}
if (ns4layer.top>=sheight*-1){
ns4layer.top-=sspeed
setTimeout("ns4marquee(ns4layer)",100)
}
else{
ns4layer.top=sheight
ns4layer.document.write(singletext[i])
ns4layer.document.close()
if (i==singletext.length-1)
i=0
else
i++
}
}
function ns6marquee(whichdiv){
ns6div=eval(whichdiv)
if (parseInt(ns6div.style.top)>0&&parseInt(ns6div.style.top)<=sspeed){
ns6div.style.top=0
setTimeout("ns6marquee(ns6div)",100)
}
if (parseInt(ns6div.style.top)>=sheight*-1){
ns6div.style.top=parseInt(ns6div.style.top)-sspeed
setTimeout("ns6marquee(ns6div)",100)
}
else{
ns6div.style.top=sheight
ns6div.innerHTML=singletext[i]
if (i==singletext.length-1)
i=0
else
i++
}
}
