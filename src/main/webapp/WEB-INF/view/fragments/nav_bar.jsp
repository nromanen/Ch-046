<%--
  Created by IntelliJ IDEA.
  User: vyach
  Date: 26.12.2016
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">University</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="#">Main</a></li>
            <spring:url value="/groups" var="groupsUrl"/>
            <li class="active"><a href="${groupsUrl}">Groups</a></li>
            <li><a href="#">Subjects</a></li>
            <li><a href="#">Schedule</a></li>
        </ul>
    </div>
</nav>