window.addEventListener("load", init, false);
function init () {
    console.log('init');
    // subgroup.addEventListener("change",validation,false);
    // group.addEventListener("change",getSubjects,false);
    // subgroup.addEventListener("change",getSubjects,false);

}

function validation () {
    var group = $("#group option:selected").text();
    var subject = $("#subject option:selected").text();
    if (group != "Select group" && subject!= "Select subject" ){
        document.getElementById("submitRoom").removeAttribute("disabled");
    } else {
        document.getElementById("submitRoom").setAttribute("disabled","true");
    }
}

function classroomValidation() {
    try {
        if ($('input[name=classroom]:checked').val() === undefined) {
            document.getElementById("submitSchedule").setAttribute("disabled", "true");
        } else document.getElementById("submitSchedule").removeAttribute("disabled");
    } catch (exc){}
}

function f(){
    $.ajax({
        url: "subgroups",
        data: {
            "group": jQuery("#group").val()
        },
        cache: false,
        type: "get",
        success: function(response) {
            $('#subgroup').empty().append($("<option/>",{
                text: "Select subgroup",
                value:"0"
            }));
            var obj=JSON.parse(response);
            for (var i=0; i<obj.subgroups.length; i++){
                $('#subgroup').append($("<option/>", {
                    value: obj.subgroups[i].id,
                    text: obj.subgroups[i].name + " "+obj.subgroups[i].subjects[0].type
                }));
            }
        },
        error: function(xhr) {
        }
    });
}
$("#subject").hide();
$("#subject_label").hide();
function getSubjects() {
    var group_id;
    if ($("#subgroup").val()==0)
        group_id=$("#group").val();
    else group_id=$("#subgroup").val();

    $("#subject").show();
    $("#subject_label").show();
    $.ajax(
        {
            url: "subjectsOfStudCommunity",
            data: {
                "group_id": group_id
            },
            cache:false,
            type:"get",
            success: function (response) {
                $('#subject').empty().append($("<option/>",{
                    text:"Select subject",
                    value:"0"
                }));
                var obj=JSON.parse(response);
                console.log(obj);
                for (var i=0;i<obj.length;i++){

                    $('#subject').append($("<option/>",{
                        text: obj[i].name +"("+obj[i].type+")",
                        value:obj[i].id
                    }));
                }
            },
            error: function (xhr) {

            }
        });
}