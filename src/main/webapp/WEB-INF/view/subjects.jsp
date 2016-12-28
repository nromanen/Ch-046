<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

 <link rel="stylesheet" href="style.css">
<title>Teachers info</title>


</head>
<body>
<h1>Teacher's info </h1>
<h1>${name}</h1><br>
 <form action="TeacherInfo" method="post">
       <table border="0">
       
          <tr>
             <td> Firstname</td>
             <td><input type="text" name="firstname" value= "${firstname}" /> </td>
          </tr>
          <tr>
             <td> Lastname</td>
             <td><input type="text" name="lastname" value= "${lastname}" /> </td>
          </tr>
           <tr>
             <td>Id</td>
             <td> ${id} </td>
            <input type="text" hidden name="teacherId" value="${id}" />
          </tr>
          
          <tr>
             <td colspan ="2">
            
                <input type="submit" value="Save" class="button1"/> </form>
                            
             </td>
          </tr>
       </table>
   

<p>List of subjects<p>
<form action="SubjectEdit" method="post">
<table class="table2">
<tr>
    <th>Title </th>
     <th>Type </th>
      <th>Course </th>
       <th>Action </th>
   </tr>
<c:forEach var="subject" items="${subjects}">
   <tr><td> ${subject.name}</td><td>${subject.type}</td><td>${subject.course} </td>
   <td> <a class="btn waves-effect waves-light indigo" href="SubjectEdit?subjectId=${subject.id}&teacherId=${id}"
    onclick="return confirm('Are you sure you want to delete ${subject.name} ${subject.type} course:${subject.course} ')" style="background-color:#f21036" >Delete</a></td></tr>
     
   </c:forEach>
 </table>
 </form>
 <p>Add subject from full list</p>
 <form action="hello" method="post">
 <select name="subject">
 <c:forEach var="subject" items="${allsubjects}">
   <option  value="${subject.id}">${subject.id} ${subject.name} ${subject.type} ${subject.course}</option>
    </c:forEach>
</select>
<input type="text" hidden name="teacherId" value="${id}" />
  <input type="submit" class="button1" id="submit" name="submit" value="Add subject" /> 
 </form>

</body>
</html>