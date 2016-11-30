
window.addEventListener("load", init, false);

function init () {

   fName.addEventListener("blur", action, false);

}

function action () {
   var fName = document.getElementById("fName").value;
   var isCorrect = true;
   console.log(fName);
   if (fName.length < 5){
   nameErr.innerHTML = "Error! Name should be more than 5 latter";
   isCorrect = false;
   console.log(isCorrect);
} else {
   isCorrect = true;
   nameErr.innerHTML = "";
}
if (isCorrect){
   console.log("in true");
   document.getElementById("submitButton").removeAttribute("disabled"); 
} else {
   console.log("in false");
   document.getElementById("submitButton").setAttribute("disabled","true");
}



}



