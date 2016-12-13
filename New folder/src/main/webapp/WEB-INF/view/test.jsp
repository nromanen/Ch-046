<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
  <meta charset="utf-8">
   <link rel="stylesheet" href="style.css">
  <title>List of teachers</title>
   
 </head>
 <body>
 <div class="mainView">
<h1>List of teachers</h1>
<form action="/TeacherInfo" method="get">
<table id="firstTab">
<tr >
    <th>ID </th>
     <th>Lastname </th>
      <th>Name </th>
       <th style: text-align:center>Action </th>
   </tr>
  <c:forEach var="teacher" items="${teachers}">
      <tr>
      <td>  ${teacher.id}</td> <td>   ${teacher.lastName} </td> <td>  ${teacher.firstName} </td>
        <td>
        <a href="TeacherInfo?teacher=${teacher.id}" >Edit</a></td>
        <td>
        
     <a href="DeleteTeacher?teacher=${teacher.id}"  onclick="return confirm('Are you sure you want to delete this teacher')" >Delete</a></td> 
    </tr>
  </c:forEach>
  
</table>
 </form>
 <button onclick="window.location.href='TeacherAdd'">Add new</button> 
 </div>
 <br>
 
 
<br>
 
</body>
