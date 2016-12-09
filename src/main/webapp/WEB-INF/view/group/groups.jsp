<%--
  Created by vyach
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
    <link href="/resources/css/custom.css" rel="stylesheet">

    <script src="/resources/js/delete-group.js"></script>

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
            <li class="active"><a href="/groups">Groups</a></li>
            <li><a href="#">Page 2</a></li>
            <li><a href="#">Page 3</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">Groups</h1>
    </div>

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>${msg}</strong>
            <c:remove var="msg"/>
        </div>
    </c:if>

    <div class="add col-sm-6">
        <form method="get" action="/groups/add">
            <button type="submit" class="btn btn-lg btn-primary btn-add">Add Group</button>
        </form>
    </div>
    <div class="add col-sm-6">
        <form method="get" action="/groups/unused-subjects" class="text-right">
            <button type="submit" class="btn btn-lg btn-primary btn-add btn-unused">Unused Subjects</button>
        </form>
    </div>

    <div class="col-sm-12">
        <table class="table table-striped">
            <thead>
            <tr>
                <th class="text-center col-sm-2">Group name</th>
                <th class="text-center col-sm-2">Amount</th>
                <th class="text-center col-sm-6">Subjects</th>
                <th class="text-center col-sm-2">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="group" items="${groups}">
                <tr>
                    <th id="group${group.id}" class="vertical-text-center text-center gr-name">${group.name}</th>
                    <th class="vertical-text-center text-center gr-amount">${group.count}</th>
                    <th class="vertical-text-center">
                        <ul class="list-group">
                            <c:forEach var="subject" items="${group.subjects}">
                                <li class="list-group-item">${subject.name}<span class="badge">${subject.type}</span>
                                </li>
                            </c:forEach>
                        </ul>
                    </th>
                    <th class="vertical-text-center text-center">
                        <form method="get" action="/groups/update">
                            <button class="btn btn-primary btn-update btn-action" type="submit" name="group_id"
                                    value="${group.id}">Update
                            </button>
                        </form>

                        <button id="del-btn" class="btn btn-danger btn-action" type="button" name="group_id"
                                onclick="confirm(${group.id})" data-toggle="modal" data-target="#delete_popup">
                            Delete
                        </button>

                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div id="delete_popup" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <p id="conf-message"></p>
                </div>
                <div class="modal-footer">
                    <form method="post" action="/groups/delete" class="modal-form">
                        <button id="conf-button" class="btn btn-primary btn-update" type="submit"
                                name="group_id">
                            Confirm
                        </button>
                    </form>

                    <button type="button" class="btn btn-danger" data-dismiss="modal">Decline</button>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>

</body>
</html>
