<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 18.12.16
  Time: 0:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/timetablesOfGroupByDay" >
    <p>Select day</p>
    <select name="day_of_week" id="day_of_week">
        <option>Select day</option>
        <c:forEach var="day" items="${days}">
            <option>${day}</option>
        </c:forEach>
    </select>
    <p>Select oddness</p>
    <select name="oddness" id="oddness">
        <option>Select oddness</option>
        <c:forEach var="oddness" items="${oddnesses}">
            <option>${oddness}</option>
        </c:forEach>
    </select>
    <p>Select group</p>
    <select id="group" name="group">
        <option>Select group</option>
        <c:forEach var="group" items="${groups}" varStatus="loop">
            <option value="${group.id}">${group.name}</option>
        </c:forEach>
    </select>
    <p>Select subgroup</p>
    <select name="subgroup" id="subgroup">
        <option value="0">Select subgroup</option>
    </select>
    <p>Select timetable</p>
    <select id="timetable" name="timetable" >
        <option>Select timetable</option>
    </select>
    <input type="submit" name="Edit" value="Edit" >
</form>


<div id="tableEditBox" style="display: none;background: #9d9d9d">
    <form action="${pageContext.request.contextPath}/updateTimetable" method="post" id="updateForm">
        <p>Select day</p>
        <select name="day_of_week" id="day_of_week_box">
            <option>Select day</option>
            <c:forEach var="day" items="${days}">
                <option>${day}</option>
            </c:forEach>
        </select>
        <p>Select oddness</p>
        <select name="oddness" id="oddness_box">
            <option>Select oddness</option>
            <c:forEach var="oddness" items="${oddnesses}">
                <option>${oddness}</option>
            </c:forEach>
        </select>
        <p>Select pair</p>
        <select name="pair" id="pair">
            <option>Select pair</option>
            <c:forEach var="pair" items="${allPairs}">
                <option>${pair}</option>
            </c:forEach>
        </select>
        <p>Select subject</p>
        <select name="subject_box" id="subject_box">
            <option>Select subject</option>

        </select>
        <p>Select group</p>
        <select id="group_box" name="group">
            <option>Select group</option>
            <c:forEach var="group" items="${groups}" varStatus="loop">
                <option value="${group.id}">${group.name}</option>
            </c:forEach>
        </select>
        <p>Select subgroup</p>
        <select name="subgroup" id="subgroup_box">
            <option value="0">Select subgroup</option>
        </select>
        <p>Select classroom</p>
        <select name="classroom" id="classroom_box">
            <option>Select classroom</option>
            <c:forEach var="classroom" items="${allClassrooms}">
                <option value="${classroom.id}">${classroom.name}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Save changes">
    </form>
</div>
<page:js/>
<script>
    window.addEventListener("load", init, false);
    function init () {
        console.log('init');
        group.addEventListener("change",f,false);
        group.addEventListener("change",getTimetables,false);
        subgroup.addEventListener("change",getTimetables,false);
        group.addEventListener("change",getSubjects,false);
        subgroup.addEventListener("change",getSubjects,false);
        timetable.addEventListener("change",getSelectedTimetable,false);
        group_box.addEventListener("change",f1,false);
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
                        text: obj.subgroups[i].name + " " + obj.subgroups[i].subjects[0].type
                    }));

                }
            },
            error: function(xhr) {
            }
        });
    }

    function f1(){
        $.ajax({
            url: "subgroups",
            data: {
                "group": jQuery("#group_box").val()
            },
            cache: false,
            type: "get",
            success: function(response) {
                $('#subgroup_box').empty().append($("<option/>",{
                    text: "Select subgroup",
                    value:"0"
                }));
                var obj=JSON.parse(response);
                for (var i=0; i<obj.subgroups.length; i++){
                    $('#subgroup_box').append($("<option/>", {
                        value: obj.subgroups[i].id,
                        text: obj.subgroups[i].name + " " + obj.subgroups[i].subjects[0].type
                    }));

                }
            },
            error: function(xhr) {
            }
        });
    }

    function getTimetables(){
        var group_id;
        if ($("#subgroup").val()==0)
            group_id=$("#group").val();
        else group_id=$("#subgroup").val();
        $.ajax({
            url: "timetablesOfGroupByDay",
            data: {
                "group": group_id,
                "oddness":$("#oddness option:selected").text(),
                "day_of_week":$("#day_of_week option:selected").text()
            },
            cache: false,
            type: "get",
            success: function(response) {
                $('#timetable').empty().append($("<option/>",{
                    text: "Select timetable",
                    value:"0"
                }));
                var obj=JSON.parse(response);
                for (var i=0; i<obj.length; i++){
                    if (obj[i]!==null) {
                        $('#timetable').append($("<option/>", {
                            value: obj[i].id,
                            text: obj[i].pair + " " + obj[i].subject.name
                        }))
                    }
                }
            },
            error: function(xhr) {
            }
        });
    }

    function getSelectedTimetable() {
        $.ajax({
            url: "timetablesOfGroupByDay",
            data: {
                "timetable": $("#timetable").val()
            },
            cache: false,
            type: "post",
            success: function(response) {
                $('#tableEditBox').show();
                var obj=JSON.parse(response);
                console.log(obj);
                if(obj.studentCommunity.group===undefined){
                    $("#group_box").val(obj.studentCommunity.id);
                } else {
                    $("#group_box").val(obj.studentCommunity.group.id);
                    $("#group_box").val(obj.studentCommunity.id);
                }
                $("#day_of_week_box").val(obj.day);
                $("#pair").val(obj.pair);
                $("#classroom_box").val(obj.classroom.id);
                $("#oddness_box").val(obj.oddnessOfWeek);
                $("#subject_box").val(obj.subject.id);
                $("#updateForm").append($("<select/>",
                        {
                            name:"table_id"
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
                        $('#subject_box').empty().append($("<option/>",{
                            text:"Select subject",
                            value:"0"
                        }));
                        var obj=JSON.parse(response);
                        for (var i=0;i<obj.length;i++){
                            $('#subject_box').append($("<option/>",{
                                text: obj[i].name +"("+obj[i].type+")",
                                value:obj[i].id
                            }));
                        }
                    },
                    error: function (xhr) {

                    }
                });
    }

</script>
</body>
</html>
