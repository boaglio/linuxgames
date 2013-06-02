
// contagem regressiva
var countDownInterval=60;
var c_reloadwidth=200
var countDownTime=countDownInterval+1;

function countDown(){
	countDownTime--;
 	if (countDownTime <=0){
 		countDownTime=countDownInterval;
 		clearTimeout(counter)
 		window.location.reload()
 		return
 	}
 	document.getElementById("countDownText").innerHTML=countDownTime+" "
 	counter=setTimeout("countDown()", 1000);
}

function startit(){
	document.getElementById("counterReload").innerHTML='<a href="javascript:window.location.reload()">refresh</a> em <b id="countDownText">'+countDownTime+' </b> segundos';
	countDown();
}
