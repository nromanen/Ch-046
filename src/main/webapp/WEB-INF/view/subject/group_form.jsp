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
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.js"></script>
    <link href="/resources/css/skins/square/green.css" rel="stylesheet">
    <script src="/resources/js/icheck.js"></script>
    <link href="/resources/css/custom.css" rel="stylesheet">

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
            <li class="active"><a href="#">Groups</a></li>
            <li><a href="#">Page 2</a></li>
            <li><a href="#">Page 3</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">Add or Update Group</h1>
    </div>

    <form method="post" action="/groups/add" class="form-horizontal">

        <input type="hidden" name="group_id" value="${group.id}">

        <div class="form-group">
            <label class="control-label col-sm-2" for="group_name">Group name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="group_name" name="gr_name" placeholder="Enter group name"
                       value="${group.name}">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="count">Student count:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="count" name="gr_count" placeholder="Enter group count"
                       value="${group.count eq 0 ? "" : group.count}">
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
                            <div class="well">
                                <input type="checkbox" name="gr_subject" id="${subject.name}(${subject.type})"
                                       value="${subject.name} ${subject.type}" checked>
                                <label for="${subject.name}(${subject.type})">${subject.name}</label>
                                <span class="badge">${subject.type}</span>
                            </div>
                        </label>
                    </c:forEach>
                    <c:forEach var="subject" items="${subjects}">
                        <label class="col-sm-4">
                            <div class="well">
                                <input type="checkbox" name="gr_subject" id="${subject.name}(${subject.type})"
                                       value="${subject.name} ${subject.type}">
                                <label for="${subject.name}(${subject.type})">${subject.name}</label>
                                <span class="badge">${subject.type}</span>
                            </div>
                        </label>
                    </c:forEach>
                </div>
            </div>
        </c:if>

        <div class="form-group col-sm-12 text-center">
            <button type="submit" class="btn btn-primary btn-lg btn-submit">Submit</button>
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
