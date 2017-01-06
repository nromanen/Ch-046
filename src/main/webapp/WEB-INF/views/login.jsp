<%--
  Created by IntelliJ IDEA.
  User: vyach
  Date: 03.01.2017
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<spring:url value="/login" var="loginUrl"/>
<form action="${loginUrl}" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <c:if test="${param.error != null}">
        <div>
            <h1>Invalid username or password</h1>
        </div>
    </c:if>
    <c:if test="${param.logout != null}">
        <div>
            <h1>You have been logged out</h1>
        </div>
    </c:if>
    Login:<input type="text" id="username" name="username"/>
    Password:<input type="password" id="password" name="password"/>
    <button type="submit">Submit</button>
</form>
</body>
</html>
