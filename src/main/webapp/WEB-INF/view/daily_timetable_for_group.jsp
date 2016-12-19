<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 07.12.16
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

    <!-- Bootstrap -->
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">--%>

    <!-- Optional theme -->
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">--%>

    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/daily.css">
</head>
<body>
<h2>${param.day_of_week}</h2>
<h3>${requestScope.group}</h3>
<c:choose>
    <c:when test="${param.day_of_week ne ''}">
        <table style="float: left" >
            <tr><td>Pair</td></tr>
            <c:forEach var="pair" items="${requestScope.allPairs}">
                <tr><td>${pair}</td></tr>
            </c:forEach>
        </table>
            <table style="float: left" >
                <tr><td>${param.day_of_week}</td></tr>
                <c:forEach var="timetable" items="${timetables}">
                    <c:choose>
                        <c:when test="${timetable ne null}">
                            <tr>
                                <td>${timetable.subject.name}</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td>window</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </table>

    </c:when>
    <c:otherwise>
        <table style="float: left">
            <tr><td>Pair</td></tr>
            <c:forEach var="pair" items="${allPairs}">
                <tr><td>${pair}</td></tr>
            </c:forEach>
        </table>
        <c:forEach var="entry" items="${timetables.entrySet()}">
            <table style="float: left" >
                <tr><td>${entry.getKey()}</td></tr>
                <c:forEach var="timetable" items="${entry.getValue()}">
                    <c:choose>
                        <c:when test="${timetable ne null}">
                    <tr>
                        <td>${timetable.subject.name}</td>
                    </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td>window</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </table>
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>
