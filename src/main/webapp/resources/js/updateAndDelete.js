window.addEventListener("load", init, false);
function init () {
    console.log('init');
    // subgroup.addEventListener("change",validation,false);
    // subject.addEventListener("change", validation, false);
    group.addEventListener("change",getSubjects,false);
    subgroup.addEventListener("change",getSubjects,false);
    $("#tableEditBox").hide();
    $("#hiddenDelete").hide();
}

function getSubgroups(){
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

function getSelectedTimetable(table_id) {
    $.ajax({
        url: "timetablesOfGroupByDay",
        data: {
            "timetable": table_id
        },
        cache: false,
        type: "post",
        success: function(response) {
            $("#tableEditBox").show();
            var obj=JSON.parse(response);
            console.log(obj);
            if(obj.studentCommunity.group===undefined){
                $("#group").val(obj.studentCommunity.id);
            } else {
                $("#group").val(obj.studentCommunity.group.id);
                $("#subgroup").val(obj.studentCommunity.id);
            }
            $("#day_of_week").val(obj.day);
            $("#pair").val(obj.pair);
            $("#classroom").val(obj.classroom.id);
            $("#oddness").val(obj.oddnessOfWeek);
            $("#teacher").val(obj.teacher.id);
            getSubjects();
            $("#subject").val(obj.subject.id);
            $("#hiddenDiv").empty().hide();
            $("#hiddenDiv").append($("<select/>",
                {
                    name:"table_id",
                    id:"table_id"
                }).append($("<option/>",
                {
                    value:obj.id
                }))).append($("<select/>",
                {
                    name:"teacher_id"
                }).append($("<option/>",{
                value:obj.teacher.id
            })));
        },
        error: function(xhr) {
        }
    });
}

function update() {
    $.ajax({
        url: "updateTimetable",
        data: {
            "table_id": $("#table_id").val(),
            "day_of_week":$("#day_of_week").val(),
            "oddness":$("#oddness").val(),
            "subject_box":$("#subject").val(),
            "teacher_id":$("#teacher").val(),
            "classroom":$("#classroom").val(),
            "pair":$("#pair").val(),
            "group":$("#group").val(),
            "subgroup":$("#subgroup").val()

        },
        cache: false,
        type: "post",
        success: function(response) {
            $('#tableEditBox').show();
            var obj=JSON.parse(response);
            console.log(obj);
            var id=obj.id;
            var str_id='#'+id;
            $("#"+str_id).text("kjjkjk");
            document.getElementById(''+id).innerHTML="kjkjkjkjk";

        },
        error: function(xhr) {
        }
    });


}

function deleteT(table_id) {
    $("#timetable").val(table_id);
    alert("dkl");
   alert(table_id);
    alert($("#timetable").val());
    //$("#hiddenDelete").submit();
}

function test(id) {
    alert(id);
    $("#timetable").val(id);
    alert($("#timetable").val());
    $("#hiddenDelete").submit();
}