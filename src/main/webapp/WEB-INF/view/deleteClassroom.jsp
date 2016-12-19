<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>

<html>
<head>
    <title>Delete classroom</title>
    <page:css/>
</head>
<body>

<div class="container">

    <page:header/>

    <div class="alert alert-danger">${errorMessage}</div>

    <hr>
    <table class="table table-striped">
        <tr>
            <td>Group</td>
            <td>Subject</td>
            <td>Teacher</td>
            <td>Day</td>
            <td>Pair</td>
            <td>Oddes</td>
            <td colspan="2">Action</td>


        </tr>
        <c:forEach var="tt" items="${timeTables}">
            <td>Group ${tt.group.name}</td>
            <td>${tt.subject.name}</td>
            <td>${tt.teacher.firstName} ${tt.teacher.lastName}</td>
            <td>${tt.day}</td>
            <td>${tt.pair}</td>
            <td>${tt.oddnessOfWeek}</td>
            <td>
                <a href="timetableUpdate?id=${tt.id}" class="btn btn-info btn-sm">
                    <span class="glyphicon glyphicon-pencil"></span> Edit
                </a>
            </td>
            <td>
                <button class="btn btn-danger btn-sm" onclick="Confirm.render('Are you sure you want to delete timetable?','post','timetables', {'id': '${tt.id}'})"><span class="glyphicon glyphicon-remove"></span> Remove</button>
            </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <button class="btn btn-danger btn-sm" onclick="Confirm.render('Are you sure you want to delete all this timetables?','post','timetables', {'classroomId': '${classroomId}'})"><span class="glyphicon glyphicon-remove"></span> Remove all</button>
            </td>
        </tr>
    </table>



    <page:footer/>

</div>



<div id="dialogoverlay"></div>
<div id="dialogbox">
    <div>
        <div id="dialogboxhead"></div>
        <div id="dialogboxbody"></div>
        <div id="dialogboxfoot"><button class='btn btn-danger' onclick="Confirm.yes()">Confirm</button>&nbsp;&nbsp;&nbsp;&nbsp;<button class='btn btn-success' onclick="Confirm.no()">Cancel</button></div>
    </div>
</div>


<page:js/>

<script src="../../../assets/js/confirm.js" ></script>

</body>
</html>
