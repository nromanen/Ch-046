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

    <h3>This subject have to be added to Timetable</h3>

    <hr>
    <table class="table table-striped">
        <tr>
            <td>Group</td>
            <td>Subject</td>

            <td>Action</td>


        </tr>
        <c:forEach var="item" items="${groupsubject}">
            <tr>
                <td>Group ${item.group.name}</td>
                <td>${item.subject.name} (${item.subject.type})</td>
                <td>
                    <button class="btn btn-info " onclick="goToUrl('post','schedule', { group : ${item.group.id}, subject: ${item.subject.id}})">Add</button>
                </td>
            </tr>
        </c:forEach>
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

<script src="assets/js/confirm.js" ></script>

</body>
</html>
