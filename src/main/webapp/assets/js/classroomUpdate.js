/**
 * Created by Admin on 10.12.16.
 */
window.addEventListener("load", init, false);

var isCorrectName = false;
var isCorrectCapacity = false;
var isCorrectTypes= false;


function init () {

    validation();
    roomName.addEventListener("blur", validationName, false);
    description.addEventListener("blur", validationDescription, false);
    capacity.addEventListener("blur", validationCapacity, false);
    types.addEventListener("change", validationTypes, false);
}

function validation() {
    var name = document.getElementById("roomName").value;
    if (name == "" || name.length > 20) {
        isCorrectName = false;
    } else {
        isCorrectName = true;
    }
    var types = document.getElementById("types").value;

    if (types == "") {
        isCorrectTypes = false;
    } else {
        isCorrectTypes = true;
    }
    var capacity = document.getElementById("capacity").value;

    if (capacity < 5 || capacity > 200) {
        isCorrectCapacity = false;
    } else {
        isCorrectCapacity = true;
    }
    isCorrect();
}

function validationName () {

    var name = document.getElementById("roomName").value;

    name = name.replace(/</g, "&lt;").replace(/>/g, "&gt;");

    if (name == "" || name.length > 20) {
        nameError.innerHTML = "Error! Name can not be empty";
        isCorrectName = false;
    } else {
        isCorrectName = true;
        document.getElementById("roomName").value = name;
        nameError.innerHTML = "";
    }
    isCorrect();
}

function validationCapacity () {
    var capacity = document.getElementById("capacity").value;

    if (capacity < 5 || capacity > 200) {
        capacityError.innerHTML = "Error! Capacity should be between 5 and 200";
        isCorrectCapacity = false;
    } else {
        isCorrectCapacity = true;
        capacityError.innerHTML = "";
    }
    isCorrect();
}

function validationTypes() {

    var types = document.getElementById("types").value;

    if (types == "") {
        typesError.innerHTML = "Error! Select type(s) please";
        isCorrectTypes = false;
    } else {
        isCorrectTypes = true;
        typesError.innerHTML = "";
    }
    isCorrect();
}

function validationDescription () {

    var description = document.getElementById("description").value;

    description = description.replace(/</g, "&lt;").replace(/>/g, "&gt;");

    if (description.length > 250) {
        descriptionError.innerHTML = "Error! To long description";
        isCorrectName = false;
    } else {
        isCorrectName = true;
        document.getElementById("description").value = description;
        descriptionError.innerHTML = "";
    }
    isCorrect();
}


function isCorrect() {
    if (isCorrectCapacity && isCorrectName && isCorrectTypes){
        document.getElementById("submitRoom").removeAttribute("disabled");
    } else {
        document.getElementById("submitRoom").setAttribute("disabled","true");
    }

}
