// Preload Images
img1 = new Image(16, 16);
img1.src="/css/spinner.gif";

img2 = new Image(220, 19);
img2.src="/css/clock.gif";

// When DOM is ready
$(document).ready(function(){

// Launch MODAL BOX if the Login Link is clicked
$("#login_link").click(function(){
$('#login_form').modal();
});

$("#login_link_home").click(function(){
	$('#login_form').modal();
});

$("#new_game_link_home").click(function(){
	$('#login_form').modal();
});

// When the form is submitted
$("#status > form").submit(function(){

// Hide 'Submit' Button
$('#submit').hide();

// Show Gif Spinning Rotator
$('#ajax_loading').show();

// 'this' refers to the current submitted form
var str = $(this).serialize();

// -- Start AJAX Call --

$.ajax({
    type: "POST",
    url: "/games/index-login",  // Send the login info to this page
    data: str,
    success: function(msg){

$("#status").ajaxComplete(function(event, request, settings){

 // Show 'Submit' Button
$('#submit').show();

// Hide Gif Spinning Rotator
$('#ajax_loading').hide();

 if( jQuery.trim(msg) == 'OK') // LOGIN OK?
 {
  var login_response = '<div id="logged_in">' +
                       '<div style="width: 150px; float: left; margin-left: 70px;">' +
                        '<div style="width: 50px; text-align:center;">' +
                         '<img align="absmiddle" src="/images/clock.gif">' +
                        '</div>' +
                      ' </div>' +
                     ' </div>';

  $('a.modalCloseImg').hide();

  $('#simplemodal-container').css("width","350px");
  $('#simplemodal-container').css("height","150px");

  $(this).html(login_response); // Refers to 'status'

  // After 3 seconds redirect the
  setTimeout('go_to_private_page()', 3000);
 }
 else // ERROR?
 {
  var login_response = msg;
  $('#login_response').html(login_response);
 }

 });

 }

  });

// -- End AJAX Call --

return false;

}); // end submit event

});

function go_to_private_page()
{
window.location = '/games/index-home'; // Members Area
}


function validaRegistroLogin() {
   document.getElementById('formReg').submit();
}

function validaEsqueceuSenha() {
   document.getElementById('formEsqueceuSenha').submit();
}
