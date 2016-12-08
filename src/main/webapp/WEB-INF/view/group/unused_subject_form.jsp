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
        <h1 class="text-center">
            Groups Selection for<br>
            ${subject.name} ${subject.type}, course ${subject.course}
        </h1>
    </div>

    <form method="post" action="/groups/unused-subjects/add">
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
                                    <li class="list-group-item">${gSubject.name}<span
                                            class="badge">${gSubject.type}</span>
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
