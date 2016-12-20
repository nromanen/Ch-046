<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 07.12.16
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/daily.css"/>">
    <script src="resources/js/updateAndDelete.js"></script>
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

        <div class="container">
            <h3>Group: ${requestScope.group.name}</h3>
            <div class="tableDiv">
        <table style="float: left" class="table table-bordered">
            <thead>
            <tr><th>Pair</th></tr>
            </thead>
            <c:forEach var="pair" items="${allPairs}">
                <tr><td>${pair}</td></tr>
            </c:forEach>
        </table>
            </div>
        <c:forEach var="entry" items="${timetables.entrySet()}">
        <div class="tableDiv">
            <table style="float: left" class="table table-bordered">
                <thead>
                <tr><th>${entry.getKey()}</th></tr>
                </thead>
                <c:forEach var="timetable" items="${entry.getValue()}">
                    <c:choose>
                        <c:when test="${timetable ne null}">
                    <tr>
                        <td>${timetable.subject.name}
                            <a href="#">
                                <span id="${timetable.id}" onclick="test(${timetable.id})" class="glyphicon glyphicon-remove"></span></a>
                            <a href="#">
                                <span onclick="getSelectedTimetable(${timetable.id})" class="glyphicon glyphicon-edit" ></span>
                            </a>
                        </td>
                    </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td>window
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </table>
    </div>
        </c:forEach>
        </div>

<div id="tableEditBox">
    <div class="col-md-2"></div>
    <div class="col-md-8">
        <h3 class="text-center">Select Group/subgroup, day and oddness in order to edit the timetable</h3>
        <form id="updateForm" class="form-horizontal" action="${pageContext.request.contextPath}/forGroup" method="get">
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="group">Day</label>
                <div class="col-sm-10">
                    <select name="day_of_week" id="day_of_week" class="form-control">
                        <option value="">Select day</option>
                        <c:forEach var="day" items="${requestScope.days}">
                            <option>${day}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="oddness" id="oddness_label">Oddness </label>
                <div class="col-sm-10">
                    <select  id="oddness" name="oddness_of_week" class="form-control">
                        <option value>Select oddness</option>
                        <c:forEach var="oddness_of_week" items="${requestScope.oddnesses}">
                            <option>${oddness_of_week}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="pair" id="pair_label">Pair </label>
                <div class="col-sm-10">
                    <select  id="pair" name="pair" class="form-control">
                        <option value>Select pair</option>
                        <c:forEach var="pair" items="${requestScope.pairs}">
                            <option>${pair}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="group">Group </label>
                <div class="col-sm-10">
                    <select name="group" id="group" class="form-control" onclick="getSubgroups()">
                        <option value="">Select Group</option>
                        <c:forEach var="group" items="${requestScope.groups}">
                            <option value="${group.id}">Group ${group.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="subgroup" id="subgroup_label">Subgroup</label>
                <div class="col-sm-10">
                    <select name="subgroup" id="subgroup" class="form-control">
                        <option value="0">Select subgroup</option>
                        <c:forEach var="subgroup" items="${requestScope.group.subgroups}">
                            <option value="${subgroup.id}">${subgroup.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="teacher" id="teacher_label">Teacher</label>
                <div class="col-sm-10">
                    <select name="teacher" id="teacher" class="form-control">
                        <option value="0">Select teacher</option>
                        <c:forEach var="teacher" items="${requestScope.teachers}">
                            <option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="subject" id="subject_label">Subject</label>
                <div class="col-sm-10">
                    <select name="teacher" id="subject" class="form-control">
                        <option value="0">Select subject</option>
                        <c:forEach var="subject" items="${requestScope.subjects}">
                            <option value="${subject.id}">${subject.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="subgroup" id="classroom_label">Classroom</label>
                <div class="col-sm-10">
                    <select name="teacher" id="classroom" class="form-control">
                        <option value="0">Select classroom</option>
                        <c:forEach var="classroom" items="${requestScope.classrooms}">
                            <option value="${classroom.id}">${classroom.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group form-group-lg">
                <div class="col-sm-2 control-label" >
                    <input type="submit" onclick="update()" name="submit" value="Save" class="btn btn-info " id="submitTimetable" >
                </div>
            </div>
            <div id="hiddenDiv"></div>
        </form>
    </div>
</div>
<form id="hiddenDelete" method="post" action="deleteTimetable">
    <select id="timetable" name="timetable">
        <c:forEach var="tabble" items="${timetables1}">
        <option value="${tabble.id}"></option>
        </c:forEach>
    </select>
    <input type="submit" value="delete">
</form>
</body>
</html>
