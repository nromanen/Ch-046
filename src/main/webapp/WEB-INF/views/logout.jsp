<%--
  Created by IntelliJ IDEA.
  User: vyach
  Date: 03.01.2017
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Logout</title>
</head>
<body>
    <h1>It's a logout page</h1><br>
    <spring:url value="/logout" var="logoutUrl"/>
    <a href="${logoutUrl}">logout</a>
</body>
</html>