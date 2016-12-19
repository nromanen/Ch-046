

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>


<html>
<head>
    <page:css/>
</head>
<body>

<div class="container">

    <page:header/>

    <div class="col-sm-2"></div>
    <div class="col-sm-8">

        <h2>${message} </h2>
        <form method="post" action="${link}">

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
    </div>
    <div class="col-sm-2"></div>

    <page:footer/>
    <page:js/>

    <script src="assets/js/classroomUpdate.js" ></script>



</body>
</html>
