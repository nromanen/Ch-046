<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>List of teachers</h1>

<form action="hello" method="post">
<!-- 
<select id="isTitles" name="isTitles"> -->
<select name="t">
 <c:forEach var="teacher" items="${teachers}">
   <option name="teacher" value="${teacher.id}">${teacher.id} ${teacher.lastName} ${teacher.firstName}</option>
    </c:forEach>
</select>
<input type="submit" value="Check">
</form>
<br>
${id}