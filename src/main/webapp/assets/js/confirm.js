window.addEventListener("load", init, false);


function init () {
    console.log('init');
    checkSubject.addEventListener("click", check, false);




}

$(function() {
    // setTimeout() function will be fired after page is loaded
    // it will wait for 5 sec. and then will fire
    // $("#successMessage").hide() function
    setTimeout(function() {
        $(".alert-success").fadeOut('fast');
    }, 5000);
});


function check(){

    var group = document.getElementById("group").value;
    var subject = document.getElementById("subject").value;

    console.log("ajax");
    $.ajax({
        type : "GET", // http method
        url : "checkTimetable", // the endpoint
        data : {
            group : group,
            subject: subject
        },

        success : function(json) {

            console.log(json);
            console.log("success");
            if(json.result == "true"){
                console.log("true");
                Confirm.render('Do you want add subject to timetable ?','post','schedule', { group : group, subject: subject});

            } else{
                console.log("false");
                goToUrl('post', 'schedule', { group : group, subject: subject});
            }


        }
    });

}






function goToUrl(method, url, obj){

    if (method == "get"){
        window.location.href = url;
    } else if( method == "post"){

        var form = '';
        $.each( obj, function( key, value ) {
            form += '<input type="hidden" name="'+key+'" value="'+value+'">';
        });
        console.log(form);
        $('<form action="' + url + '" method="POST">' + form + '</form>').appendTo($(document.body)).submit();
    } else {
        throw new Error("ERROR! Not supported method's type!")
    }

}

function CustomConfirm(){
    var method, url, args;

    this.render = function(message, method1, url1, args1){

        console.log("render");
        method = method1;
        url = url1;
        args = args1;

        var winW = window.innerWidth;
        var winH = window.innerHeight;
        var dialogoverlay = document.getElementById('dialogoverlay');
        var dialogbox = document.getElementById('dialogbox');
        dialogoverlay.style.display = "block";
        dialogoverlay.style.height = winH+"px";
        dialogbox.style.left = (winW/2) - (550 * .5)+"px";
        dialogbox.style.top = "100px";
        dialogbox.style.display = "block";

        document.getElementById('dialogboxhead').innerHTML = "Confirm that action";
        document.getElementById('dialogboxbody').innerHTML = message;
        // document.getElementById('dialogboxfoot').innerHTML = '<button class=\'btn-md btn-success\' onclick="Confirm.yes()">Yes</button>&nbsp;&nbsp;&nbsp;&nbsp;<button class=\'btn-md btn-danger\' onclick="Confirm.no()">No</button>';
    };
    this.no = function(){
        document.getElementById('dialogbox').style.display = "none";
        document.getElementById('dialogoverlay').style.display = "none";
    };
    this.yes = function(){

        goToUrl(method, url, args);

        document.getElementById('dialogbox').style.display = "none";
        document.getElementById('dialogoverlay').style.display = "none";
    }
}
var Confirm = new CustomConfirm();
