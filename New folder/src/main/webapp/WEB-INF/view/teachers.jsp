<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

<select name="t">
 <c:forEach var="teacher" items="${teachers}">
   <option name="teacher" value="${teacher.id}">${teacher.id} ${teacher.lastName} ${teacher.firstName}</option>
    </c:forEach>
</select>
<input type="submit" value="Check">

<tr align="center">
<td valign="bottom" colspan="6" style="height: 45px; ">
<input type="button" id="submit" name="submit" value="Save" style="width: 80px ; height:24px; text-align: center;border-radius: 10px 10px 10px 10px;"/> 
<input type="button" id="revert" name="revert" value="Revert" style="width: 80px ; height:24px;text-align: center;border-radius: 10px 10px 10px 10px;"/></td>
</tr>
 <input type="submit" value="Edit" id="${teacher.id}"></td>