<%--
  Created by IntelliJ IDEA.
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
    <link href="${contextPath}/resources/css/skins/square/green.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/icheck.js"></script>
    <script src="${contextPath}/resources/js/unused-subject-form.js"></script>
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
        <h2 class="text-center bigger">
            Groups Selection for<br>
            ${subject.name} ${subject.type}<br>
            course ${subject.course}
        </h2>
    </div>

    <form method="post" action="${contextPath}/groups/unused-subjects/add" onsubmit="return hasCheckedEvenOneGroup()">
        <input type="hidden" name="subject_id" value="${subject.id}"/>

        <div class="col-sm-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th class="col-sm-1"></th>
                    <th class="text-center col-sm-2">Group name</th>
                    <th class="text-center col-sm-2">Amount</th>
                    <th class="text-center col-sm-7">Subjects</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="group" items="${groups}">
                    <tr>
                        <th class="vertical-text-center text-center">
                            <input type="checkbox" name="group_id" id="group${group.id}" value="${group.id}">
                        </th>
                        <th class="vertical-text-center text-center gr-name">${group.name}</th>
                        <th class="vertical-text-center text-center gr-amount">${group.count}</th>
                        <th class="vertical-text-center">
                            <ul class="list-group">
                                <c:forEach var="gSubject" items="${group.subjects}">
                                    <li class="list-group-item">${gSubject.name}
                                        <span class="badge">${gSubject.type}</span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="form-group col-sm-12 text-center">
            <button type="submit" class="btn btn-primary btn-lg btn-submit" data-toggle="modal">Assign</button>
        </div>

    </form>

    <div id="error_message" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <p id="conf-message">No one group was selected! Please select at least one group.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

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
