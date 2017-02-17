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
    <title>restore password</title>

    <link rel="stylesheet" href="css/materialize.min.css">
    <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
    <script src="js/materialize.min.js"></script>
    <script src="js/checkPassword.js"></script>

</head>

<body>

<nav>
    <div class="nav-wrapper">
        <a class="brand-logo">Travian</a>
        <ul class="right hide-on-med-and-down">
            <li><a href="login"><spring:message code="submitButton.loginForm"/></a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row col s12" style="margin-top: 50px">
        <div class="col s3 offset-s4 center-align">
            <h4>
                <spring:message code="passSave.form"/>
            </h4>
            <form action="savePassword" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <c:if test="${error != null}">
                    <div class="card red lighten-4">
                        <div class="card-content red-text">
                            <p class="center-align">${error}</p>
                        </div>
                    </div>
                </c:if>
                <div class="row">
                    <div class="input-field">
                        <label for="password">
                            <spring:message code="password.loginForm"/> *
                        </label>
                        <input type="password" id="password" name="password" class="validate" required/>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field">
                        <label for="confirmPassword">
                            <spring:message code="confirmPassword.resetForm"/> *
                        </label>
                        <input type="password" id="confirmPassword" name="confirmPassword" class="validate" required/>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field">
                        <input type="text" id="id" name="id" class="validate" hidden value="${id}"/>
                    </div>
                </div>
                <div class="row">
                    <button type="submit" class="btn waves-effect waves-teal" disabled>
                        <spring:message code="passSave.change"/>
                    </button>
                </div>
            </form>
            <p style="color: red; font-size: 12px;">*
                <spring:message code="passRestor.passFormRequired"/>
            </p>

        </div>
    </div>
</div>

</body>
</html>



