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
            <h4>Restoring password form</h4>
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
                        <label for="password">Password *</label>
                        <input type="password" id="password" name="password" class="validate" required/>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field">
                        <label for="password1">Confirm password *</label>
                        <input type="password" id="password1" name="password1" class="validate" required/>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field">
                        <input type="text" id="id" name="id" class="validate" hidden value="${id}"/>
                    </div>
                </div>
                <div class="row">
                    <button type="submit" class="btn waves-effect waves-teal" disabled>Change password</button>
                </div>
            </form>
            <p style="color: red; font-size: 12px;">* Password should contains at least one uppercase and one lowercase letters, one digit and one special symbol and contains from 8 to 32 characters.</p>

        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $(document).on('input', function(){
            if(validatePassword($("#password").val()) && $("#password").val()==$("#password1").val()) {
                $(':input[type="submit"]').prop('disabled', false);
            } else{
                $(':input[type="submit"]').prop('disabled', true);
            }
        });
    });

    function validatePassword(pass) {

        var re = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@!%_*#?&])[A-Za-z\d$@_!%*#?&]{8,32}$/;
        return re.test(pass);
    }
</script>

</body>
</html>



