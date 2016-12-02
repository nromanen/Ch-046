<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>


<html>
<head>
  <!-- Bootstrap -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

  <!-- Optional theme -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

  <link rel="stylesheet" href="/assets/css/main.css">

</head>
<body>

<div class="container">

  <nav class="navbar navbar-default navbar-inverse" style="border-radius:0px !important; margin-bottom:0px;">
    <div class="container">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <a class="navbar-brand" href="/classrooms">UNIVERSITY</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Services<span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">All Groups</a></li>
              <li><a href="#">All classrooms</a></li>
              <li><a href="#">All Subjects</a></li>

              <li><a href="#">Add new classroom</a></li>
              <li><a href="#">Add new Group</a></li>
              <li><a href="#">Add new Subject</a></li>
              <li role="separator" class="divider"></li>
              <li><a href="#">Schedule</a></li>
            </ul>
          </li>
          <%--<li><a href="#">News</a></li>--%>
          <li><a href="contact.jsp">Contact</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <form class="navbar-form navbar-left" role="search" method="get" action="#">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Search employee" name="search">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
          </form>

        </ul>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>



<h3 class="text-center">All classrooms</h3>

      <table class="table table-striped">
        <tr>
          <td>Name</td>
          <td>Capacity</td>
          <td>Type</td>
          <td>Description</td>
        </tr>
        <c:forEach var="room" items="${classrooms}">
          <tr>
            <td>Classroom ${room.name}</td>
            <td>${room.capacity}</td>
            <td>${room.types}</td>
            <td>${room.description}</td>
          </tr>
        </c:forEach>
      </table>
    <form action = "/classrooms" method = "post">
      <div class="form-group">
          <label for="filePath">Upload classrooms from file to DB. (support formats .json, .xml, .txt)</label>
          <input type="file" id = "filePath" name = "filePath">
      </div>
      <div class="col-sm-2 control-label" >
          <input type="submit" value="Add to DB" class="btn btn-info " id="addClassrooms">
      </div>
    </form>