<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
  <meta charset="utf-8">
  <title>List of teachers</title>
  <style>
  
  </style>
 </head>

<h1>List of teachers</h1>
<form action="/TeacherInfo" method="get">
<table border="0">
  <c:forEach var="teacher" items="${teachers}">
    <tr >
      <td>  ${teacher.id} ${teacher.lastName} ${teacher.firstName} </td>
        <td>
        <a href="TeacherInfo?teacher=${teacher.id}" >Details</a></td>
    </tr>
  </c:forEach>
  
</table>
</form>
<br>

