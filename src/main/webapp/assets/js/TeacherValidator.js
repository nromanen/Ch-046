/**
 * Created by mmaksymtc on 21.12.16.
 */



window.addEventListener("load", init, false);

function init () {

    console.log('init');

    document.getElementById('firstname').addEventListener('blur', firstNameValid, false);
    document.getElementById('lastname').addEventListener('blur', lastNameValid, false);
    $('#teacherEdit').click(function(){
        $('#teacherLastName').attr('contenteditable','true');
        $('#teacherFirstName').attr('contenteditable','true');  }); 
   /* document.getElementById('teacherEdit').addEventListener('click', teacherEdit, false);*/
}

function firstNameValid() {
	
	var i = document.getElementById('firstname').value;
	var re = /[A-Z]{1}[a-z]{1,20}/g;
	var result = re.test(i);
	if (!result) {
		document.getElementById('firstName_error').innerHTML = 'wrong name, example: Ivan';
		document.getElementById('whitespace').setAttribute('disabled', 'true');
		
	}
	else {
		document.getElementById('firstName_error').innerHTML = '';
		document.getElementById('whitespace').removeAttribute('disabled');
		document.getElementById("whitespace").className = "btn waves-effect waves-light light-blue darken-4";
	}
}

function lastNameValid() {
	
	var i = document.getElementById('lastname').value;
	var re = /[A-Z]{1}[a-z]{1,20}/g;
	var result = re.test(i);
	if (!result) {
		document.getElementById('lastName_error').innerHTML = ' wrong lastname, example: Ivanov';
		document.getElementById('whitespace').setAttribute('disabled', 'true');
		
	}
	else {
		document.getElementById('lastName_error').innerHTML = '';
		document.getElementById('whitespace').removeAttribute('disabled');
		document.getElementById("whitespace").className = "btn waves-effect waves-light light-blue darken-4";
	}
}

function teacherEdit(){
	document.getElementById('hiddenName').removeAttribute('hidden');
	/*document.getElementById('teacherLastName').setAttribute('contentEditable', true);*/
}
