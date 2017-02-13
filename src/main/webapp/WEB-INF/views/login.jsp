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

    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.css">
    <!-- Compiled and minified JavaScript -->
    <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $(".button-collapse").sideNav();
        });
    </script>

</head>

<body>

<nav>
    <div class="nav-wrapper">
        <a class="brand-logo">Travian</a>
        <ul class="right hide-on-med-and-down">
            <li><a href="login">Login</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row col s12" style="margin-top: 50px">
        <div class="col s3 offset-s4 center-align">
            <spring:url value="/login" var="loginUrl"/>
            <form action="${loginUrl}" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <c:if test="${param.error != null}">
                    <div class="card red lighten-4">
                        <div class="card-content red-text">
                            <p class="center-align">Invalid username or password</p>
                        </div>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="card green lighten-4">
                        <div class="card-content green-text">
                            <p class="center-align">You have been logged out</p>
                        </div>
                    </div>
                </c:if>
                <c:if test="${email_send != null}">
                    <div class="card green lighten-4">
                        <div class="card-content green-text">
                            <p class="center-align">${email_send}</p>
                        </div>
                    </div>
                </c:if>
                <div class="row">
                    <div class="input-field">
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username" class="validate" required/>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" class="validate" required/>
                    </div>
                    <div class="card red lighten-4">
                        <div class="card-content red-text" id="passError" hidden></div>
                    </div>

                </div>
                <div class="row">
                    <button type="submit" class="btn waves-effect waves-teal">Login</button>
                </div>
            </form>
            <br>
            <p style="color: red;"><a href="forgotPassword"> Forgot password?</a></p>

        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $(":password").keyup(function(){
            if($("#password").val().length > 320){
                $("#passError").show().html("Length of password can't be more than 32 character");
                $(':input[type="submit"]').prop('disabled', true);
            }else{
                $("#passError").html("").hide();
                $(':input[type="submit"]').prop('disabled', false);
            }
        });
    });
</script>

</body>
</html>
