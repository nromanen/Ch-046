
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

    <spring:url value="/css" var="cssUrl"/>
    <link href="${cssUrl}/flag-icon.min.css" rel="stylesheet"/>

    <link rel="stylesheet" href="css/materialize.min.css">
    <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
    <script src="js/materialize.min.js"></script>
    <script src="js/loginPasswordValidation.js"></script>

</head>

<body>

<nav>
    <div class="nav-wrapper">
        <a class="brand-logo" a href="login">Travian</a>
        <ul class="right hide-on-med-and-down">
            <li><a href="login"><spring:message code="submitButton.loginForm"/></a></li>
        </ul>
    </div>
</nav>

<div class="container row">
    <spring:url value="/login" var="loginUrl"/>
    <a href="${loginUrl}?locale=en">
        <span class="flag-icon flag-icon-gb" title="English"></span>
    </a> |
    <a href="${loginUrl}?locale=uk">
        <span class="flag-icon flag-icon-ua" title="Ukraine"></span>
    </a>
</div>

<div class="container">
    <div class="row col s12" style="margin-top: 50px">
        <div class="col s3 offset-s4 center-align">
            <form action="${loginUrl}" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <c:if test="${param.error != null}">
                    <div class="card red lighten-4">
                        <div class="card-content red-text">
                            <p class="center-align">
                                <spring:message code="login.errorMessage"/>
                            </p>
                        </div>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="card green lighten-4">
                        <div class="card-content green-text">
                            <p class="center-align">
                                <spring:message code="logout.successMessage"/>
                            </p>
                        </div>
                    </div>
                </c:if>
                <c:if test="${email_send != null}">
                    <div class="card green lighten-4">
                        <div class="card-content green-text">
                            <p class="center-align">
                                <spring:message code="passRestor.emailSend"/>
                            </p>
                        </div>
                    </div>
                </c:if>
                <c:if test="${pass_change != null}">
                    <div class="card green lighten-4">
                        <div class="card-content green-text">
                            <p class="center-align">
                                <spring:message code="passRestor.passChange"/>
                            </p>
                        </div>
                    </div>
                </c:if>
                <div class="row">
                    <div class="input-field">
                        <label for="username">
                            <spring:message code="userName.loginForm"/>
                        </label>
                        <input type="text" id="username" name="username" class="validate" required/>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field">
                        <label for="password">
                            <spring:message code="password.loginForm"/>
                        </label>
                        <input type="password" id="password" name="password" class="validate" required/>
                    </div>
                    <div class="card red lighten-4">
                        <div class="card-content red-text" id="passError" hidden>
                            <spring:message code="passValidation.loginForm"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <button type="submit" class="btn waves-effect waves-teal">
                        <spring:message code="submitButton.loginForm"/>
                    </button>
                </div>
            </form>
            <br>
            <p style="color: red;"><a href="forgotPassword">
                <spring:message code="forgotPassword.loginForm"/>
            </a></p>
        </div>
    </div>
</div>
</body>

</html>
