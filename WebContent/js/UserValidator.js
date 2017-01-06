/**
 * Created by mmaksymtc on 06.01.17.
 */


window.addEventListener("load", init, false);

function init () {

    console.log('init');   
    document.getElementById('mail').addEventListener('blur', MailValid, false);
   
}

function MailValid() {
	
	var mail = document.getElementById('mail').value;
	var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/g;
	var result = re.test(mail);
	
	
	
	if (mail!='' && !result) {
		document.getElementById('mail_error').innerHTML = 'e-mail is not correct';
		document.getElementById('whitespace').setAttribute('disabled', 'true');
	}
	
	else {
		document.getElementById('mail_error').innerHTML = '';
		document.getElementById('whitespace').removeAttribute('disabled');
		
}	
}
function showToast(message, duration){
    Materialize.toast(message, duration);         
}	