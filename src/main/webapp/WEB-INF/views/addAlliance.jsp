<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.css">
    <!-- Compiled and minified JavaScript -->
    <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.js"></script>
    <script src="../js/my.js"></script>
    <script type="text/javascript">
        $( document ).ready(function(){
            $(".button-collapse").sideNav();
            $('select').material_select();

            $('#addAlliance').prop('disabled',true);
            $('#addLeader').prop('disabled',true);
            populateLeader();
        });
    </script>



    <style type="text/css">
        .itm {
            background: #DDD;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<nav>
    <div class="nav-wrapper">
        <a href="#!" class="brand-logo">Logo</a>
        <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
        <ul class="right hide-on-med-and-down">
            <li><a href="sass.html">Show users</a></li>
            <li><a href="badges.html">Add users</a></li>
            <li><a href="collapsible.html">Jingle</a></li>
            <li><a href="mobile.html">Mobile</a></li>
        </ul>
        <ul class="side-nav" id="mobile-demo">
            <li><a href="sass.html">Show users</a></li>
            <li><a href="badges.html">Add users</a></li>
            <li><a href="collapsible.html">Javascript</a></li>
            <li><a href="mobile.html">Mobile</a></li>
        </ul>
    </div>
</nav>
<form:form method="POST" modelAttribute="allianceDTO" action="alliance">
    <div class="row container">
        <!-- <div class="input-field col s6 offset-s3">
          <select id="number" onchange="populateUsers()">
            <option value="" disabled selected>Select number</option>
            <option value="1">Option 1</option>
            <option value="2">Option 2</option>
            <option value="3">Option 3</option>
          </select>
          <label>Select number of users</label>
        </div> -->


    </div>
    <div class="row container">
        <div class="input-field col s3">
            <form:input type="text" path="name" id="name" class="validate"/>
            <label for="name">Alliance</label>
            <form:errors path="name" class="help-inline"/>
        </div>

        <div class="input-field col s3">
            <form:input type="text" path="leaderLogin" id="leaderLogin" class="validate"/>
            <label for="leaderLogin">Leader's login</label>
            <form:errors path="leaderLogin" class="help-inline"/>
        </div>

        <div class="input-field col s3">
            <form:input type="text" path="leaderEmail" id="leaderEmail" class="validate"/>
            <label for="leaderEmail">Leader's e-mail</label>
            <form:errors path="leaderEmail" class="help-inline"/>
        </div>
        <div class="input-field col s3">
            <button class="btn waves-effect waves-light col offset-s5 " type="submit" name="action">Send
                <i class="material-icons right">send</i>
            </button>
        </div>
    </div>
</form:form>
<div class="row container">
    <div class="col s12">
        <table class="highlight">
            <thead>
            <tr>
                <th data-field="id">Alliance name</th>
                <th data-field="name">Leader's login</th>
                <th data-field="price">Leader's email</th>
                <th></th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <tr>
                <td>Avalon</td>
                <td>Gektor</td>
                <td>gektor@mail.com</td>
                <td>
                    <button class="btn waves-effect waves-light col offset-s5 " type="submit" name="action">Edit</button>
                </td>
                <td>
                    <button class="btn waves-effect waves-light col offset-s5 " type="submit" name="action">Delete</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>