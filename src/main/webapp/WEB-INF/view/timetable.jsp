<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/my.js"></script>

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
                        <li><a href="chooseTimetable">Watch timetable</a></li>
                        <li role="separator" class="divider"></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="groupsAndStreams">Watch subgroups</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="col-md-2"></div>
<div class="col-md-8">
    <h3 class="text-center">Select Group/subgroup, day and oddness in order to get the timetable</h3>
    <form class="form-horizontal" action="${pageContext.request.contextPath}/forGroup" method="post">
        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="group">Group </label>
            <div class="col-sm-10">
                <select name="group" id="group" class="form-control" onclick="f()">
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
            <label class="col-sm-2 control-label" for="oddness_of_week" id="oddness_label">Oddness </label>
            <div class="col-sm-10">
                <select  id="oddness_of_week" name="oddness_of_week" class="form-control">
                    <option value>Select oddness</option>
                    <c:forEach var="oddness_of_week" items="${requestScope.oddness_of_weeks}">
                        <option>${oddness_of_week.name()}</option>
                    </c:forEach>
                </select>

            </div>
        </div>
        <div class="form-group form-group-lg">
            <div class="col-sm-2 control-label" >
                <input type="submit" name="submit" value="Get shedule" class="btn btn-info " id="submitTimetable" >
            </div>
        </div>
    </form>
</div>

</body>
</html>
