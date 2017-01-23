<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
    <script src="/js/my.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".button-collapse").sideNav();
            $('select').material_select();

            $('#addAlliance').prop('disabled', true);
            $('#addLeader').prop('disabled', true);
            populateLeader();
        });
    </script>


    <link rel="stylesheet" href="/htmlViews/style.css" type="text/css">
</head>
<body>
<nav>
    <div class="nav-wrapper">
        <a href="#" class="brand-logo">Logo</a>
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

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Login</th>
        <th>Password</th>
        <th>E-mail</th>
        <th>Alliance</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>User1</td>
        <td>Login1</td>
        <td>Password1</td>
        <td>Email1</td>
        <td>Alliance1</td>
    </tr>
    </tbody>
</table>
</body>
</html>