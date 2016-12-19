<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 10.12.16
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form method="post" action="${pageContext.request.contextPath}/deleteGroup">
    <select name="groupToDelete">
        <option>ChooseGroup</option>
        <c:forEach var="group" items="${requestScope.groups}">
        <option value="${group.id}">${group.name}</option>
        </c:forEach>
    </select>
    <input type="submit" value="delete">
</form>
</body>
</html>
