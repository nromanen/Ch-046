/**
 * Created by Admin on 10.12.16.
 */

$( document ).ready(function(){

    console.log('work');

    var $cat = $("#group"),
        $subcat = $("#subject");

    $cat.on("change",function(){
        var _rel = $(this).val();
        console.log(_rel);
        $subcat.find("option").attr("style","");
        $subcat.val("");
        if(!_rel) return $subcat.prop("disabled",true);

        $subcat.find("[rel="+_rel+"]").show();

        $subcat.prop("disabled",false);
    });

});


window.addEventListener("load", init, false);

function init () {

    console.log('init');

    group.addEventListener("change", validation, false);
    subject.addEventListener("change", validation, false);

}

function validation () {

    var group = document.getElementById("group").value;
    var subject = document.getElementById("subject").value;

    console.log(group, subject);

    if (group != "" && subject!= ""){
        document.getElementById("checkSubject").removeAttribute("disabled");
    } else {
        document.getElementById("checkSubject").setAttribute("disabled","true");
    }



}

