<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:set var="strings" value="101"/>
<h1>Hello ${strings} </h1>

<table>
    <c:forEach var="timetable" items="${requestScope.timetablesForGroup}">
    <tr>
        <td><c:out value="${timetable.pair}"/></td>
    </tr>
    </c:forEach>
</table>
<form action="${pageContext.request.contextPath}/forGroup" method="get">
    <select name="group">
        <option value="">Select group</option>
        <c:forEach var="group" items="${requestScope.groups}">
        <option value="${group.id}">${group.getName()}</option>

        </c:forEach>
    </select>

    <select name="day_of_week">
        <option value="">Select day</option>
        <c:forEach var="dayOfWeek" items="${requestScope.days_of_week}">
            <option>${dayOfWeek.name()}</option>
        </c:forEach>
    </select>

    <select name="oddness_of_week">
        <option value="">Select oddness</option>
        <c:forEach var="oddness_of_week" items="${requestScope.oddness_of_weeks}">
            <option>${oddness_of_week.name()}</option>
        </c:forEach>
    </select>

   <input type="submit" name="subm" value="Send">

</form>

</body>
</html>
