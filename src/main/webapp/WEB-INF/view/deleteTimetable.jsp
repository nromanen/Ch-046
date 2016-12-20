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
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Brand</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Go to <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="populateTimetable">Add timetable</a></li>
                        <li><a href="deleteTimetable">Remove timetable</a></li>
                        <li><a href="chooseTimetable">Watch timetable</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="updateTimetable">Update timetable</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Watch subgroups</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<form method="post" action="${pageContext.request.contextPath}/deleteTimetable">
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
<select name="subgroup" id="subgroup" >
    <option value="0">Select subgroup</option>
</select>
<p>Select timetable</p>
<select id="timetable" name="timetable" >
<option>Select timetable</option>
</select>
    <input type="submit" name="Delete" value="Delete" >
</form>

<page:js/>
<script>
    window.addEventListener("load", init, false);
    function init() {
        console.log('init');
        group.addEventListener("change",f,false);
        group.addEventListener("change",getTimetables,false);
        subgroup.addEventListener("change",getTimetables,false);
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

</script>
</body>
</html>
