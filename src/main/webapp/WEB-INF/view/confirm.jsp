<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 09.12.16
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${requestScope.streamsAndSubgroupsExist == true}">
    <h2> Are U sure U wanna redefine subgroups and streams? If so</h2>
</c:if>
    <form method="post" action="${pageContext.request.contextPath}/success">
        <input type="submit" value="create">
    </form>
    <h2>The following groups are gonna be created</h2>
    <h2>Subgroups:</h2>
    <c:forEach var="entry"  items="${requestScope.subgroups.entrySet()}">
        <c:set var="group" value="${entry.getKey()}"/>
        <h3>${group.name} </h3>
        <c:forEach var="subgroup" items="${group.subgroups}">
            <p>${subgroup.name} ${subgroup.subjects.get(0).getType()}</p>
        </c:forEach>
    </c:forEach>

    <h2>Streams:</h2>
    <c:forEach var="entry" items="${requestScope.streams.entrySet()}">
        <h3>${entry.getKey().name}</h3>
        <p>${entry.getValue()}</p>
    </c:forEach>


</body>
</html>
