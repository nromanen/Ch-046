<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
  <meta charset="utf-8">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.min.css">
   <link rel="stylesheet" href="style.css">
  <title>List of teachers</title>
   
 </head>
 <body>
 <div class="mainView">
<h1>List of teachers</h1>
<form action="/TeacherInfo" method="get">
<table class="striped">
<tr >
    <th>ID </th>
     <th>Lastname </th>
      <th>Name </th>
       <th colspan="2" id="thaction">Action </th>
   </tr>
  <c:forEach var="teacher" items="${teachers}">
      <tr>
      <td>  ${teacher.id}</td> <td>   ${teacher.lastName} </td> <td>  ${teacher.firstName} </td>
        <td>
        <a class="waves-effect waves-light  btn" href="TeacherInfo?teacher=${teacher.id}" style="background-color:#9cd1af">Edit</a>
      <%--   <a href="TeacherInfo?teacher=${teacher.id}" >Edit</a></td> --%>
        <td>
        
     <a class="waves-effect waves-light  btn" href="DeleteTeacher?teacher=${teacher.id}" 
      onclick="return confirm('Are you sure you want to delete ${teacher.lastName} ${teacher.firstName} ID:${teacher.id} ')" style="background-color:#f21036">Delete</a></td> 
    </tr>
  </c:forEach>
  
</table>
 </form>
 <button id="addbutt" onclick="window.location.href='TeacherAdd'">Add new</button> 
 </div>
 <br>
 
 
<br>
 <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js"></script>
</body>
