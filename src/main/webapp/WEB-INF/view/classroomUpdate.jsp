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
            <label for="roomName">Name <span class="text-danger">*</span></label>
            <c:if test="${errors.nameError != null}"><page:error title="${errors.nameError}"/></c:if>
            <input type="text" class="form-control" name="name" value="${classroom.name}" id="roomName">
            <span class="error text-danger" id="nameError"></span>
        </div>
        <div class="form-group col-md-8">
            <label for="capacity">Capacity <span class="text-danger">*</span></label>
            <c:if test="${errors.capacityError != null}"><page:error title="${errors.capacityError}"/></c:if>
            <input type="number" class="form-control" name="capacity" value="${classroom.capacity}" id="capacity">
            <span class="error text-danger" id="capacityError"></span>
        </div>

        <div class="form-group col-md-8">
            <label for="types" class="control-label">Types <span class="text-danger">*</span></label>
            <c:if test="${errors.typesError != null}"><page:error title="${errors.typesError}"/></c:if>
            <select class="form-control" name="types" id="types" multiple>
                <c:forEach var="type" items="${types}">
                    <option value="${type}"

                            <c:forEach var="roomType" items="${classroom.types}">
                                <c:if test="${roomType.id == type.id}">selected</c:if>
                            </c:forEach>


                    >${type}</option>
                </c:forEach>
            </select>
            <span class="error text-danger" id="typesError"></span>
        </div>
        <div class="form-group col-md-8">
            <label for="description">Description:</label>
            <textarea  class="form-control" name="description" id="description">${classroom.description}</textarea>
            <span class="error text-danger" id="descriptionError"></span>
        </div>

        <div class="col-md-12">
            <span class="text-danger"><strong>* required fields</strong></span><br>
            <input type="submit" name="submit" value="Submit" class="btn btn-info" id="submitRoom" disabled>
        </div>
    </form>

    <page:footer/>
    <page:js/>


    <script>

        window.addEventListener("load", init, false);

        var isCorrectName = false;
        var isCorrectCapacity = false;
        var isCorrectTypes= false;


        function init () {

            validation();
            roomName.addEventListener("blur", validationName, false);
            description.addEventListener("blur", validationDescription, false);
            capacity.addEventListener("blur", validationCapacity, false);
            types.addEventListener("change", validationTypes, false);
        }

        function validation() {
            var name = document.getElementById("roomName").value;
            if (name == "" || name.length > 20) {
                isCorrectName = false;
            } else {
                isCorrectName = true;
            }
            var types = document.getElementById("types").value;

            if (types == "") {
                isCorrectTypes = false;
            } else {
                isCorrectTypes = true;
            }
            var capacity = document.getElementById("capacity").value;

            if (capacity < 5 || capacity > 200) {
                isCorrectCapacity = false;
            } else {
                isCorrectCapacity = true;
            }
            isCorrect();
        }

        function validationName () {

            var name = document.getElementById("roomName").value;

            name = name.replace(/</g, "&lt;").replace(/>/g, "&gt;");

            if (name == "" || name.length > 20) {
                nameError.innerHTML = "Error! Name can not be empty";
                isCorrectName = false;
            } else {
                isCorrectName = true;
                document.getElementById("roomName").value = name;
                nameError.innerHTML = "";
            }
            isCorrect();
        }

        function validationCapacity () {
            var capacity = document.getElementById("capacity").value;

            if (capacity < 5 || capacity > 200) {
                capacityError.innerHTML = "Error! Capacity should be between 5 and 200";
                isCorrectCapacity = false;
            } else {
                isCorrectCapacity = true;
                capacityError.innerHTML = "";
            }
            isCorrect();
        }

        function validationTypes() {

            var types = document.getElementById("types").value;

            if (types == "") {
                typesError.innerHTML = "Error! Select type(s) please";
                isCorrectTypes = false;
            } else {
                isCorrectTypes = true;
                typesError.innerHTML = "";
            }
            isCorrect();
        }

        function validationDescription () {

            var description = document.getElementById("description").value;

            description = description.replace(/</g, "&lt;").replace(/>/g, "&gt;");

            if (description.length > 250) {
                descriptionError.innerHTML = "Error! To long description";
                isCorrectName = false;
            } else {
                isCorrectName = true;
                document.getElementById("description").value = description;
                descriptionError.innerHTML = "";
            }
            isCorrect();
        }


        function isCorrect() {
            if (isCorrectCapacity && isCorrectName && isCorrectTypes){
                document.getElementById("submitRoom").removeAttribute("disabled");
            } else {
                document.getElementById("submitRoom").setAttribute("disabled","true");
            }

        }

    </script>


</body>
</html>
