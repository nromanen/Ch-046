<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>


<html>
<head>
    <page:css/>
</head>
<body>

<div class="container">

    <page:header/>

    <c:if test="${message!=null}">
        <div class="alert alert-success" id = "message">${message}</div>
    </c:if>

    <c:if test="${errorMessage!=null}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <c:if test="${!empty classrooms}">
        <h3 class="text-center">All classrooms</h3>

        <table class="table table-striped">
            <tr>
                <td>Name</td>
                <td>Capacity</td>
                <td>Type</td>
                <td>Description</td>
                <td colspan="2">Action</td>


            </tr>
            <c:forEach var="room" items="${classrooms}">
                <tr
                        <c:if test="${room.id == updateClassroom.id}">class="success"</c:if>
                        id = "room_${room.id}"
                >
                    <td>Classroom ${room.name}</td>
                    <td>${room.capacity}</td>
                    <td>${room.types}</td>
                    <td>${room.description}</td>
                    <td>
                        <a href="classroomUpdate?id=${room.id}" class="btn btn-info btn-sm">
                            <span class="glyphicon glyphicon-pencil"></span> Edit
                        </a>
                    </td>
                    <td>
                        <button class="btn btn-danger btn-sm" onclick="Confirm.render('Are you sure you want to delete classroom?','post','classrooms', {'id': '${room.id}'})"><span class="glyphicon glyphicon-remove"></span> Remove</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty classrooms}">
        <h3 class="text-center">No classrooms now</h3>

    </c:if>

    <div class="col-md-12">
        <form method="get" action="classroomUpdate">
            <input type="number" hidden name="action" value="new">
            <input type="number" hidden name="id" value="0">
            <input type="submit" name="submit" value="Add classroom" class="btn btn-default">
        </form>
    </div>
    <div class="col-md-12">
        <img src="assets/img/arrow_down.png" id="option" height="5%" width="auto" style="cursor:pointer;"

             data-toggle="tooltip" data-placement="bottom" title="More/less options!">
        <%--<a id="option" class="btn btn-default btn-sm">More options</a>--%>
    </div>
    <br>
    <div class="row" id="moreOption"  style="display: none">
        <br>
        <hr>
        <br>
        <%--Add classrooms to DB from file(supported formats .json, .xml, .txt):--%>
        <form action="upload" method="post" enctype="multipart/form-data" class="form-inline">
            <div class="col-md-4">
                <input type="file" name="file" size="50" class="filestyle" data-buttonName="btn-primary" id = "fileId" accept="text/xml, text/plain, application/json"/>
            </div>
            <div class="col-md-4">
                <input type="submit" value=" Add to DB"  class="btn btn-default" id = "addFile" disabled/>
            </div>
        </form>
        <div class="col-md-4"></div>
        <br>
        <hr>
        <br>
        <div class="col-md-3">
            <p>Select file's type:</p>
            <select id="fType" name="fType" class="form-control">
                <option value="json" selected>json file</option>
                <option value="xml" >xml file</option>
                <option value="txt" >txt file</option>
            </select>
            <br>
            <br>
            <a class="btn btn-default" id="getRooms" href = "classroomDownload?fType=json">Get classrooms</a>
        </div>

    </div>

</div>

<div id="dialogoverlay"></div>
<div id="dialogbox">
    <div>
        <div id="dialogboxhead"></div>
        <div id="dialogboxbody"></div>
        <div id="dialogboxfoot"><button class='btn btn-danger' onclick="Confirm.yes()">Confirm</button>&nbsp;&nbsp;&nbsp;&nbsp;<button class='btn btn-success' onclick="Confirm.no()">Cancel</button></div>
    </div>
</div>



<page:footer/>
<page:js/>
<script src="assets/js/classroom.js" ></script>
<script src="assets/js/confirm.js" ></script>


</body>