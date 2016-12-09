<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Teacher's info </h1>
<h1>${name}</h1><br>
 <form action="TeacherInfo" method="post">
       <table border="0">
          <tr>
             <td>Edit firstname</td>
             <td><input type="text" name="firstame" value= "${firstname}" /> </td>
          </tr>
          <tr>
             <td>Edit lastname</td>
             <td><input type="text" name="lastname" value= "${lastname}" /> </td>
          </tr>
           <tr>
             <td>Id</td>
             <td> ${id} </td>
          </tr>
          
          <tr>
             <td colspan ="2">
            
                <input type="submit" id = "submit" value="Save" style="width: 80px ; height:24px; text-align: center;border-radius: 10px 10px 10px 10px;"/> 
               
             </td>
          </tr>
       </table>
   </form>

<p>List of subjects<p>
<form action="SubjectEdit" method="post">
<table border="0">
<c:forEach var="subject" items="${subjects}">
   <tr>
    <td> ${subject.id} ${subject.name} ${subject.type} ${subject.course} </td>
   <td> <a href="SubjectEdit?subjectId=${subject.id}&teacherId=${id}" >Delete</a></td>
    <%-- <input type="text" hidden name="teacherId" value="${id}" > --%>
   
   <!--  <th> <input type="submit" id="submit" name="teacherId" value="Delete" style="width: 80px ; height:24px; text-align: center;border-radius: 10px 10px 10px 10px;"/> </th> -->
   </tr>
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
<input type="text" hidden name="teacher" value="${id}" >
  <input type="submit" id="submit" name="submit" value="Add subject" style="width: 100px ; height:24px; text-align: center;border-radius: 10px 10px 10px 10px;"/> 
 </form>
</body>
</html>