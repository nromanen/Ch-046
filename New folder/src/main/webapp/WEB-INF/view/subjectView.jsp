<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Teachers subjects</title>
</head>
<body>
<h1>${name}</h1><br>
<table id="subjectView">
<tr>
    <th>Title </th>
     <th>Type </th>
      <th>Course </th>
   </tr>
<c:forEach var="subject" items="${subjects}">
   
   <tr><td> ${subject.name}</td><td>${subject.type}</td><td>${subject.course} </td></tr>
  <%--  <> ${subject.id} ${subject.name} ${subject.type} ${subject.course}  --%>
   </c:forEach>
 </table>
</body>
</html>