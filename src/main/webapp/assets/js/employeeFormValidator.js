/**
 * Created by Admin on 21.11.16.
 */


/**
 * Created by Admin on 21.11.16.
 */


window.addEventListener("load", init, false);

function init () {

    firstName.addEventListener("blur", validation, false);
    lastName.addEventListener("blur", validation, false);
    bDay.addEventListener("blur", validation, false);
    ssn.addEventListener("blur", validation, false);
    email.addEventListener("blur", validation, false);
    rate.addEventListener("blur", validation, false);
    characteristic.addEventListener("blur", validation, false);

}

function validation () {
    var min = 2;
    var max = 20;
    var ssnMin = 100000;
    var ssnMax = 999999;
    var minEmail = 8;
    var maxEmail = 65;

    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var bDay = document.getElementById("bDay").value;
    var ssn = document.getElementById("ssn").value;
    var email = document.getElementById("email").value;
    var rate = document.getElementById("rate").value;
    var characteristic = document.getElementById("characteristic").value;

    console.log(firstName, lastName, bDay, ssn, email, rate, characteristic);


    var isCorrectFirstName = true;
    var isCorrectLastName = true;
    var isCorrectBDay = true;
    var isCorrectSsn = true;
    var isCorrectEmail = true;
    var isCorrectRate = true;
    var isCorrectCharacteristic = true;

    if (firstName.length < min || firstName.length > max){
        firstNameError.innerHTML = "Error! First name should be between 2 sand 20 latter!";
        isCorrectFirstName = false;
    } else {
        isCorrectFirstName = true;
        firstNameError.innerHTML = "";

    }

    if (lastName.length < min || lastName.length > max){
        lastNameError.innerHTML = "Error! Last name should be between 2 sand 20 latter!";
        isCorrectLastName = false;
    } else {
        isCorrectLastName = true;
        lastNameError.innerHTML = "";
    }

    if (ssn < ssnMin || ssn > ssnMax){
        ssnError.innerHTML = "Error! SSN should has 6 digits!";
        isCorrectSsn = false;
    } else {
        isCorrectSsn = true;
        ssnError.innerHTML = "";
    }

    if (rate < 0){
        rateError.innerHTML = "Error! Rate can't be negative!";
        isCorrectRate = false;
    } else {
        isCorrectRate = true;
        rateError.innerHTML = "";
    }

    if (characteristic.length == 0){
        characteristicError.innerHTML = "Error! Characteristic can't be empty!";
        isCorrectCharacteristic = false;
    } else {
        isCorrectCharacteristic = true;
        rateError.innerHTML = "";
    }

    var re = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;

    if (email.length == 0 || !re.test(email)){
        emailError.innerHTML = "Error! Incorrect email!";
        isCorrectEmail = false;
    } else {
        isCorrectEmail = true;
        emailError.innerHTML = "";
    }

    var IsoDateRe = new RegExp("^([0-9]{2})-([0-9]{2})-([0-9]{4})$");
    var matches = IsoDateRe.exec(this);
    if (!matches) return false;
    var composedDate = new Date(matches[3], (matches[1] - 1), matches[2]);
    return ((composedDate.getMonth() == (matches[1] - 1)) &&
    (composedDate.getDate() == matches[2]) &&
    (composedDate.getFullYear() == matches[3]));


    var re = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;

    if (email.length == 0 || !re.test(email)){
        emailError.innerHTML = "Error! Incorrect email!";
        isCorrectEmail = false;
    } else {
        isCorrectEmail = true;
        emailError.innerHTML = "";
    }



    if (isCorrectTitle && isCorrectDisposition && isCorrectDescription){
        document.getElementById("submitDepartment").removeAttribute("disabled");
    } else {
        document.getElementById("submitDepartment").setAttribute("disabled","true");
    }



}