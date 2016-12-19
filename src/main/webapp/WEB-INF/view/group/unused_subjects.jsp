<%--
  Created by vyach
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Bootstrap -->
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.js"></script>
    <link href="${contextPath}/resources/css/custom.css" rel="stylesheet">

    <title>Groups</title>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">University</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="#">Main</a></li>
            <li class="active"><a href="${contextPath}/groups">Groups</a></li>
            <li><a href="#">Subjects</a></li>
            <li><a href="#">Schedule</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">Unused Subjects</h1>
    </div>

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>${msg}</strong>
            <c:remove var="msg"/>
        </div>
    </c:if>

    <div class="col-sm-12">
        <table class="table table-striped">
            <thead>
            <tr>
                <th class="text-center col-sm-4">Subject name</th>
                <th class="text-center col-sm-4">Subject type</th>
                <th class="text-center col-sm-1">Course</th>
                <th class="text-center col-sm-2">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="subject" items="${subjects}">
                <tr>
                    <th class="vertical-text-center text-center">${subject.name}</th>
                    <th class="vertical-text-center text-center">${subject.type}</th>
                    <th class="vertical-text-center text-center">${subject.course}</th>
                    <th class="vertical-text-center text-center">
                        <form method="get" action="${contextPath}/groups/unused-subjects/add">
                            <button class="btn btn-primary" type="submit" name="subject_id"
                                    value="${subject.id}">Assign groups
                            </button>
                        </form>
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
