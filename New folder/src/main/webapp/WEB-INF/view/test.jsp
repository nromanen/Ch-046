<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Hello</h1>
${teachers}
<br>
<form action="/hello" method="post">
<select name="teacher">
 <c:forEach var="teacher" items="${teachers}">
   <option value="${teacher.id}">${teacher.id} ${teacher.lastName} ${teacher.firstName}</option>
    </c:forEach>
</select>
<input type="submit" value="Check">
</form>
<br>
${id}