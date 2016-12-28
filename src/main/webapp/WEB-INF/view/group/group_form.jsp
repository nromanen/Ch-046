<%--
  Created by IntelliJ IDEA.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="mainName" value="${groupName eq null ? group.name : groupName}" scope="page"/>

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

    <link href="${resourcesUrl}/css/skins/square/green.css" rel="stylesheet">
    <script src="${resourcesUrl}/js/icheck.js"></script>

    <link href="${resourcesUrl}/css/custom.css" rel="stylesheet">
    <script src="${resourcesUrl}/js/group-form-validator.js"></script>

    <title>Groups</title>
</head>

<body onload="initGroupName('${mainName}')">

<jsp:include page="../fragments/nav_bar.jsp"/>

<div class="container">
    <div class="jumbotron">
        <h1 class="text-center">${action} Group ${action eq "Update" ? mainName : ""}</h1>
    </div>

    <div id="subject-alert" style="display: none" class="alert alert-danger">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <span id="alert-subject"></span>
    </div>

    <spring:url value="/groups/action" var="groupsActionUrl"/>
    <form:form method="post" action="${groupsActionUrl}" modelAttribute="groupForm" name="group_form"
               class="form-horizontal" onsubmit="return validate('${action}')">

        <form:hidden path="id"/>
        <input type="hidden" name="main_name" value="${mainName}">

        <div class="form-group">
            <spring:bind path="name">
                <label class="control-label col-sm-2" for="group_name">Group name:</label>
                <div class="col-sm-10">
                    <form:input id="group_name" path="name" type="text" class="form-control" name="gr_name"
                                placeholder="Enter group name"/>
                    <form:errors path="name" class="alert alert-danger alert-message" element="div"/>
                    <div id="name-alert" style="display: none" class="alert alert-danger alert-message">
                        <span id="alert-name"></span>
                    </div>
                </div>
            </spring:bind>
        </div>

        <div class="form-group">
            <spring:bind path="count">
                <label class="control-label col-sm-2" for="count">Student count:</label>
                <div class="col-sm-10">
                    <form:input path="count" type="number" class="form-control" id="count" name="gr_count"
                                placeholder="Enter group count"/>
                    <form:errors path="count" class="alert alert-danger alert-message" element="div"/>
                    <div id="count-alert" style="display: none" class="alert alert-danger alert-message">
                        <span id="alert-count"></span>
                    </div>
                </div>
            </spring:bind>
        </div>

        <c:if test="${groupForm.id != 0}">
            <div class="form-group">
                <spring:bind path="subjects">
                    <div class="control-label col-sm-2">
                        <label>Subjects:</label>
                    </div>
                    <div class="col-sm-offset-2">
                        <c:forEach var="subject" items="${subjects}">
                            <label class="col-sm-6">
                                <div class="well subject-well">
                                    <form:checkbox path="subjects" value="${subject}"/>
                                    <label for="${subject.name}(${subject.type})">${subject.name}</label>
                                    <span class="badge">${subject.type}</span>
                                </div>
                            </label>
                        </c:forEach>
                    </div>
                </spring:bind>
            </div>
        </c:if>

        <div class="col-sm-offset-2">
            <div class="form-group col-sm-12 text-center">
                <button type="submit" class="btn btn-primary btn-lg">${action} Group</button>
            </div>
        </div>

    </form:form>
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
