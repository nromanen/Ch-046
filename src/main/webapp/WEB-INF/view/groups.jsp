<%--
  Created by vyach
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Groups</title>
</head>
<body>
    <h1>Groups</h1>
    <table border="1">
        <thead>
        <tr>
            <th>Group name</th>
            <th>Amount</th>
            <th>Subjects</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="group" items="${groups}">
            <tr>
                <th>${group.name}</th>
                <th>${group.count}</th>
                <th>
                    <ul>
                    <c:forEach var="subject" items="${group.subjects}">
                        <li>${subject.name}(${subject.type}) ${subject.course}</li>
                    </c:forEach>
                        </ul>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
