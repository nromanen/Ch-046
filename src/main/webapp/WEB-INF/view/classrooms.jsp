<%--&lt;%&ndash;--%>
  <%--Created by IntelliJ IDEA.--%>
  <%--User: Admin--%>
  <%--Date: 06.11.16--%>
  <%--Time: 0:50--%>
  <%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%--<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>--%>

<%--<html>--%>
<%--<head>--%>
    <%--<title>${message}</title>--%>
    <%--<page:css></page:css>--%>
<%--</head>--%>
<%--<body>--%>

<%--<page:header department="${data}"/>--%>

<%--<div class="container">--%>

    <%--<c:if test="${isResult == 1}">--%>

    <%--<h1 style="text-align: center">${message}</h1>--%>
    <%--<hr>--%>


    <%--<table class="table table-striped">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th>Firstname</th>--%>
            <%--<th>Lastname</th>--%>
            <%--<th>Position</th>--%>
            <%--<th>Department</th>--%>
            <%--<th>Action</th>--%>
            <%--<th></th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>

        <%--<c:forEach var="employee" items="${employeeList}">--%>
            <%--<tr>--%>
                <%--<td> ${employee.firstName}</td>--%>
                <%--<td> ${employee.lastName}</td>--%>
                <%--<td> ${employee.position} </td>--%>
                <%--<td> <a <c:if test="${employee.department.depId == 1}">style="color: black; pointer-events: none; cursor: default;" </c:if> href="/department?&pk=${employee.department.depId}" >${employee.department}</a></td>--%>
                <%--<td>--%>

                    <%--<a href="/employee?pk=${employee.emId}" class="btn btn-default btn-sm" role="button">Detail</a>--%>
                    <%--</td>--%>
                <%--<td>--%>
                    <%--<a href="/employeeUpdate?pk=${employee.emId}" class="btn btn-info btn-sm">--%>
                        <%--<span class="glyphicon glyphicon-pencil"></span> Edit--%>
                    <%--</a>--%>
                <%--</td>--%>
                <%--<td>--%>

                    <%--<form method="post" action="/employee">--%>
                        <%--<input type="number" hidden name="pk" value="${employee.emId}">--%>

                        <%--<input type="submit" name="submit" value="Remove" class="btn btn-danger btn-sm">--%>


                    <%--</form>--%>


                <%--</td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
    <%--</table>--%>
    <%--</c:if>--%>
    <%--<c:if test="${isResult == 0}">--%>
        <%--<h1 style="text-align: center">No results</h1>--%>
        <%--<hr>--%>
    <%--</c:if>--%>
<%--</div>--%>



<%--<page:footer/>--%>
<%--<page:js/>--%>

<%--</body>--%>
<%--</html>--%>


<h1>CLASSROOMS.JSP</h1>