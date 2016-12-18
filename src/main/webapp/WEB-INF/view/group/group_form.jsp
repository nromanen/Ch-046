<%--
  Created by IntelliJ IDEA.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>

    <link href="${pageContext.request.contextPath}/resources/css/skins/square/green.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/js/icheck.js"></script>

    <link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/js/group-form-validator.js"></script>

    <title>Groups</title>
</head>
<body onload="initGroupName('${group.name}')">

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">University</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="#">Main</a></li>
            <li class="active"><a href="${pageContext.request.contextPath}/groups">Groups</a></li>
            <li><a href="#">Subjects</a></li>
            <li><a href="#">Schedule</a></li>
        </ul>
    </div>
</nav>

<c:set var="mainName" value="${groupName eq null ? group.name : groupName}"/>
<c:remove var="groupName"/>


<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">${action} Group ${action eq "Update" ? mainName : ""}</h1>
    </div>

    <div id="subject-alert" style="display: ${msg eq null ? "none" : "block"}"
         class="alert alert-danger alert-message">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <span id="alert-subject">${msg}</span>
        <c:remove var="msg"/>
    </div>

    <form method="post" action="${pageContext.request.contextPath}/groups/add" class="form-horizontal"
          onsubmit="return validate('${action}')"
          name="group_form">

        <input type="hidden" name="group_id" value="${group.id}">
        <input type="hidden" name="main_name" value="${mainName}">

        <div class="form-group">
            <label class="control-label col-sm-2" for="group_name">Group name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="group_name" name="gr_name" placeholder="Enter group name"
                       value="${group.name eq "0" ? "" : group.name}">
                <div id="name-alert" style="display: ${gr_name_error eq null ? "none" : "block"}"
                     class="alert alert-danger alert-message">
                    <span id="alert-name">${gr_name_error}</span>
                    <c:remove var="gr_name_error"/>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="count">Student count:</label>
            <div class="col-sm-10">
                <input type="number" class="form-control" id="count" name="gr_count" placeholder="Enter group count"
                       value="${group.count eq 0 ? "" : group.count}">
                <div id="count-alert" style="display: ${gr_count_error eq null ? "none" : "block"}"
                     class="alert alert-danger alert-message">
                    <span id="alert-count">${gr_count_error}</span>
                    <c:remove var="gr_count_error"/>
                </div>
            </div>
        </div>

        <c:if test="${group.id != 0}">
            <div class="form-group">
                <div class="control-label col-sm-2">
                    <label>Subjects:</label>
                </div>
                <div class="col-sm-offset-2">
                    <c:forEach var="subject" items="${group_subjects}">
                        <label class="col-sm-4">
                            <div class="well subject-well">
                                <input type="checkbox" name="gr_subject" id="${subject.name}(${subject.type})"
                                       value="${subject.name} ${subject.type} ${subject.course}" checked>
                                <label for="${subject.name}(${subject.type})">${subject.name}</label>
                                <span class="badge">${subject.type}</span>
                            </div>
                        </label>
                    </c:forEach>
                    <c:forEach var="subject" items="${subjects}">
                        <label class="col-sm-4">
                            <div class="well subject-well">
                                <input type="checkbox" name="gr_subject" id="${subject.name}(${subject.type})"
                                       value="${subject.name} ${subject.type} ${subject.course}">
                                <label for="${subject.name}(${subject.type})">${subject.name}</label>
                                <span class="badge">${subject.type}</span>
                            </div>
                        </label>
                    </c:forEach>
                </div>
            </div>
        </c:if>

        <div class="col-sm-offset-2">
            <div class="form-group col-sm-12 text-center">
                <button type="submit" class="btn btn-primary btn-lg">${action} Group</button>
            </div>
        </div>

    </form>
</div>

<script>
    $(document).ready(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    });
</script>

</body>
</html>
