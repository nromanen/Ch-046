<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.min.css"> 
<link rel="stylesheet" href="style.css">
<title>Add Teacher</title>
</head>
<body>
<div class="teachAdd">
<h1>Add new teacher</h1><br>
<form action="TeacherAdd" method="post">
       <table class="teacherAddTable" border="0">
               <tr>
             <td> Firstname*</td>
              <td><input type="text" id='firstname' name="firstname" placeholder="Ivan" value= "${firstname}" /> <br>
              <p><c:if test="${error!=null}"><div class="teacheraddError">${error}</div></c:if></p>
              <span id='firstName_error'></span> 
              </td>
          </tr>
          <tr>
             <td>Lastname*</td>
              <td><input type="text" id='lastname' name="lastname" placeholder="Ivanov" value= "${lastname}" /> <br>
               <p><c:if test="${error2!=null}"><div class="teacheraddError">${error2}</div></c:if></p> 
                 <span id='lastName_error'></span> 
               </td> 
              
          </tr>
        
       </table>
       <br>
        <input type="submit" class="btn disabled" id="whitespace" disabled value="Add"/>
        
       </form>
  </div>
  <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js"></script> 
  <script src="assets/js/TeacherValidator.js" ></script>
</body>
</html>