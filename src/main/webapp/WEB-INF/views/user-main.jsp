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
        <a href="#!" class="brand-logo">Logo</a>
        <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
        <ul class="right hide-on-med-and-down">
            <li><a href="../../htmlViews/addVillage.html">Add village</a></li>
            <li><a href="user-main.jsp">All villages</a></li>
            <!-- <li><a href="allUsers.html">All users</a></li>
            <li><a href="allAlliances.html">All alliances</a></li> -->
        </ul>
        <ul class="side-nav" id="mobile-demo">
            <li><a href="sass.html">Show users</a></li>
            <li><a href="badges.html">Add users</a></li>
            <li><a href="collapsible.html">Javascript</a></li>
            <li><a href="mobile.html">Mobile</a></li>
        </ul>
    </div>
</nav>

<table class="bordered">
    <thead>
    <tr>
        <!-- <th>Players</th> -->
        <th>Village</th>
        <th>Population</th>
        <th>X</th>
        <th>Y</th>
        <th>Capital?</th>
        <th>Wall level</th>
        <th>Armies</th>
        <th>Quantity</th>
    </tr>
    </thead>
    <tr>
        <td rowspan="2">Village1</td>
        <td rowspan="2">30</td>
        <td rowspan="2">35</td>
        <td rowspan="2">70</td>
        <td rowspan="2">true</td>
        <td rowspan="2">50</td>
        <td>type1</td>
        <td>63</td>
    </tr>

    <tr>
        <td>type2</td>
        <td>23</td>
    </tr>


    <tr>
        <td rowspan="2">Village1</td>
        <td rowspan="2">30</td>
        <td rowspan="2">35</td>
        <td rowspan="2">70</td>
        <td rowspan="2">true</td>
        <td rowspan="2">50</td>
        <td>type1</td>
        <td>63</td>
    </tr>

    <tr>
        <td>type2</td>
        <td>23</td>
    </tr>
</table>

</body>
</html>