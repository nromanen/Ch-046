<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>

<html>
<head>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <link rel="stylesheet" href="/assets/css/main.css">

</head>
<body>

<div class="container">

    <nav class="navbar navbar-default navbar-inverse" style="border-radius:0px !important; margin-bottom:0px;">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="/classrooms">UNIVERSITY</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Services<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">All Groups</a></li>
                            <li><a href="#">All classrooms</a></li>
                            <li><a href="#">All Subjects</a></li>

                            <li><a href="#">Add new classroom</a></li>
                            <li><a href="#">Add new Group</a></li>
                            <li><a href="#">Add new Subject</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Schedule</a></li>
                        </ul>
                    </li>
                    <%--<li><a href="#">News</a></li>--%>
                    <li><a href="contact.jsp">Contact</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <form class="navbar-form navbar-left" role="search" method="get" action="#">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search employee" name="search">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>

                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>



    <h2>${message} </h2>
    <form method="post" action="/${link}">

        <input type="number" hidden name="id" value="${classroom.id}">
        <div class="form-group col-md-8">
            <label for="name">Name:</label>
            <c:if test="${errors.fNameError != null}"><page:error title="${errors.fNameError}"/></c:if>
            <input type="text" class="form-control" name="name" value="${classroom.name}" id="name">
            <span class="error text-danger" id="firstNameError"></span>
        </div>
        <div class="form-group col-md-8">
            <label for="capacity">Capacity:</label>
            <c:if test="${errors.lNameError != null}"><page:error title="${errors.lNameError}"/></c:if>
            <input type="text" class="form-control" name="capacity" value="${classroom.capacity}" id="capacity">
            <span class="error text-danger" id="lastNameError"></span>
        </div>

        <div class="form-group col-md-8">
            <label for="types" class="control-label">Types</label>
            <select class="form-control" name="types" id="types" multiple>
                <c:forEach var="type" items="${types}">
                    <option value="${type}"

                    <c:forEach var="roomType" items="${classroom.types}">
                        <c:if test="${roomType.id == type.id}">selected</c:if>
                    </c:forEach>


                    >${type}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group col-md-8">
            <label for="description">Description:</label>
            <c:if test="${errors.ssnError != null}"><page:error title="${errors.ssnError}"/></c:if>
            <textarea  class="form-control" name="description" id="description">${classroom.description}</textarea>
            <span class="error text-danger" id="ssnError"></span>
        </div>
        <div class="col-md-12">
            <input type="submit" name="submit" value="Submit" class="btn btn-info">
        </div>
    </form>

    <page:footer/>
    <page:js/>
    <%--<script src = "../../assets/js/employeeFormValidator.js"></script>--%>


</body>
</html>
