/**
 * Created by Admin on 21.11.16.
 */


window.addEventListener("load", init, false);

function init () {

    title.addEventListener("blur", validation, false);
    disposition.addEventListener("blur", validation, false);
    description.addEventListener("blur", validation, false);

}

function validation () {
    var title = document.getElementById("title").value;
    var disposition = document.getElementById("disposition").value;
    var description = document.getElementById("description").value;

    console.log(title, disposition, description);
    var isCorrectTitle = true;
    var isCorrectDisposition = true;
    var isCorrectDescription = true;

     if (title.length < 2 || title.length > 30){
         titleError.innerHTML = "Error! Title should be between 2 sand 30 latter";
         isCorrectTitle = false;
     } else {
         isCorrectTitle = true;
         titleError.innerHTML = "";
    }

    if (disposition.length < 5 || disposition.length > 60){
        dispositionError.innerHTML = "Error! Disposition should be between 5 sand 60 latter";
        isCorrectDisposition = false;
    } else {
        isCorrectDisposition = true;
        dispositionError.innerHTML = "";
    }

    if (description.length == 0 ){
        descriptionError.innerHTML = "Error! Description  can't be empty";
        isCorrectDescription = false;
    } else {
        isCorrectDescription = true;
        descriptionError.innerHTML = "";
    }

    if (isCorrectTitle && isCorrectDisposition && isCorrectDescription){
        document.getElementById("submitDepartment").removeAttribute("disabled");
    } else {
        document.getElementById("submitDepartment").setAttribute("disabled","true");
    }



}