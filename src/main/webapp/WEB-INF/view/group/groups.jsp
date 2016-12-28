<%--
  Created by vyach
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <spring:url value="/resources" var="resourcesUrl"/>
    <!-- Bootstrap -->
    <link href="${resourcesUrl}/css/bootstrap.css" rel="stylesheet">
    <script src="${resourcesUrl}/js/jquery.min.js"></script>
    <script src="${resourcesUrl}/js/bootstrap.js"></script>
    <link href="${resourcesUrl}/css/custom.css" rel="stylesheet">

    <script src="${resourcesUrl}/js/delete-group.js"></script>

    <title>Groups</title>
</head>

<body>

<jsp:include page="../fragments/nav_bar.jsp"/>

<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">Groups</h1>
    </div>

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <div class="add col-sm-6">
        <spring:url value="/groups/add" var="groupsAddUrl"/>
        <form:form method="get" action="${groupsAddUrl}">
            <button type="submit" class="btn btn-lg btn-primary btn-add">Add Group</button>
        </form:form>
    </div>
    <div class="add col-sm-6">
        <spring:url value="/groups/unused-subjects" var="unusedSubjectsUrl"/>
        <form:form method="get" action="${unusedSubjectsUrl}" class="text-right">
            <button type="submit" class="btn btn-lg btn-primary btn-add btn-unused">Unused Subjects</button>
        </form:form>
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
                                <li class="list-group-item">
                                        ${subject.name}<span class="badge">${subject.type}</span>
                                </li>
                            </c:forEach>
                        </ul>
                    </th>
                    <th class="vertical-text-center text-center">
                        <spring:url value="/groups/${group.id}/update" var="updateGroupUrl"/>
                        <form:form method="get" action="${updateGroupUrl}">
                            <button class="btn btn-success btn-update btn-action" type="submit" name="group_id">
                                Update
                            </button>
                        </form:form>

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
                    <spring:url value="/groups/delete" var="deleteGroupUrl"/>
                    <form:form method="post" action="${deleteGroupUrl}" class="modal-form">
                        <button id="conf-button" class="btn btn-success btn-update conf-button" type="submit"
                                name="group_id">
                            Yes
                        </button>
                    </form:form>
                    <button type="button" class="btn btn-danger conf-button" data-dismiss="modal">No</button>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
