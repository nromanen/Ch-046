<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Devcolibri.com</title>
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
<form action="${pageContext.request.contextPath}/c" method="get">
    <select name="group">
        <option>${group}</option>
    </select>

    <select name="day_of_week">
        <c:forEach var="dayOfWeek" items="${requestScope.days_of_week}">
            <option>${dayOfWeek.name()}</option>
        </c:forEach>
    </select>
    <select name="oddness_of_week">
        <c:forEach var="oddness_of_week" items="${oddness_of_weeks}">
            <option>${oddness_of_week.name()}</option>
        </c:forEach>
    </select>

   <input type="submit" name="subm" value="Send">

</form>
<c:if test="${requestScope.keySet().size()!=0}">
    <p>dfghjkl</p>
</c:if>
</body>
</html>
