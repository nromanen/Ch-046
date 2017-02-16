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
    <title>Forgot password</title>

    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.css">
    <!-- Compiled and minified JavaScript -->
    <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.js"></script>
    <script src="js/emailValidation.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $(".button-collapse").sideNav();
        });
    </script>

</head>

<body>

<nav>
    <div class="nav-wrapper">
        <a class="brand-logo" a href="login">Travian</a>
        <ul class="right hide-on-med-and-down">
            <li><a href="login">Login</a></li>
        </ul>
    </div>
</nav>


<div class="container">
    <div class="row col s12" style="margin-top: 50px">
        <div class="col s4 offset-s4 center-align">
            <h5><spring:message code="passRestor.emailForm"/></h5>
            <br>
            <spring:url value="/login" var="loginUrl"/>
            <form action="forgotPassword" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <c:if test="${error != null}">
                <div class="card red lighten-4">
                    <div class="card-content red-text">
                        <p class="center-align">
                            <spring:message code="passRestor.emailError"/>
                        </p>
                    </div>
                </div>
                </c:if>
                <c:if test="${errorLink != null}">
                <div class="card red lighten-4">
                    <div class="card-content red-text">
                        <p class="center-align">
                            <spring:message code="passRestor.linkError"/>
                        </p>
                    </div>
                </div>
                </c:if>
                <div class="input-field">
                    <label for="email">Email</label>
                    <input type="text" id="email" name="email" class="validate" required/>
                </div>
                <button type="submit" class="btn waves-effect waves-teal" disabled><spring:message code="passRestor.send"/></button>
        </div>
        </form>
    </div>
</div>
</div>
</body>


</html>
