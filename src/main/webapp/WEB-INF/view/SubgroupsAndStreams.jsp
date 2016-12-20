<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 09.12.16
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@  page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>The followin entities are gonna be created:</h1>
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

<form method="get" action="${pageContext.request.contextPath}/confirm">
<input id="createSub" type="submit" onclick="return(addSubAndStreams())" value="create">
</form>
<%--<script>--%>
    <%--function addSubAndStreams() {--%>
        <%--alert("hf");--%>
        <%--if (${requestScope.streamsAndSubgroupsExist}){--%>
             <%--return confirm("Are U sure U wanna redefine subgroups and streams");--%>
        <%--}--%>
    <%--}--%>
    <%--<c:if test="${requestScope.streamsAndSubgroupsExist}">--%>
 <%--document.getElementById("createSub").addEventListener("click",returnValue(addSubAndStreams),false);--%>
    <%--</c:if>--%>
<%--</script>--%>
</body>
</html>
