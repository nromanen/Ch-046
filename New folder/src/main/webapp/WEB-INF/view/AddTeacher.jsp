<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Add Teacher</title>
</head>
<body>
<div class="teachAdd">
<h1>Add new teacher</h1><br>
<form action="TeacherAdd" method="post">
       <table border="0">
               <tr>
             <td> Firstname *</td>
              <td><input type="text" name="firstame" placeholder="Ivan" value= "${firstname}" /> </td>
          </tr>
          <tr>
             <td>Lastname *</td>
              <td><input type="text" name="lastname" placeholder="Ivanov" value= "${lastname}" /> </td> 
          </tr>
        
       </table>
       <br>
        <div class="teach">
        <input type="submit" value="Add" class="button"/> </div>
   </form>
  </div>
</body>
</html>