<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
  <meta charset="utf-8">
  <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
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
      <td>  ${teacher.id}</td> <td> <span id='teacherLastName${teacher.id}'>  ${teacher.lastName} </span> </td>
       <td><span id='teacherFirstName${teacher.id}'>  ${teacher.firstName}</span> </td>
        <td>
        <span class="waves-effect waves-light  btn" onclick='edit(${teacher.id})'  id='teacherEdit'  style="background-color:#9cd1af">Edit</span>
        
         <script>
         function edit(id){
        	 var teachL = '#teacherLastName'+id;
        	 var teachF = '#teacherFirstName'+id;
        	 $(teachL).attr('contenteditable','true');
             $(teachF).attr('contenteditable','true');
             $(this).html('save');
         }
         $(document).ready(function() {
        	 $('#teacherEdit').click(function(){
         $('#teacherLastName').attr('contenteditable','true');
         $('#teacherFirstName').attr('contenteditable','true');
         $(this).html('save');
        /*  $('tr').css('border', 1);    */ 
        	/*  $('#teacherLastName').contentEditable = "true"; */
		/* 	 $('#teacherLastName').prop("contentEditable", "true"); */
        	
         
        	   /*  $('#teacherEdit').click(function(){
        	    	/* $('#hiddenName').editable(); */
        	    	/*  $('#teacherLastName').html('<p>Hello</p>');  
        	    	$('#teacherLastName').toggle('slow');
        	    	
        	    	
        	    	$('#teacherEdit').bind('click',
        	    		    function(){
        	    		        $('#teacherLastName').attr('contentEditable',true); */
        	    		    });
        	    	
        	    }); 
        	

    </script>
         
       <%--  <a class="waves-effect waves-light  btn" id='teacherEdit' href="TeacherInfo?teacher=${teacher.id}" style="background-color:#9cd1af">Edit</a> --%>
     
        <td>
        
     <a class="waves-effect waves-light  btn"  href="DeleteTeacher?teacher=${teacher.id}" 
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
