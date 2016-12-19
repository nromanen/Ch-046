

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>


<html>
<head>
    <page:css/>
</head>
<body>

<div class="container">

    <page:header/>

    <c:if test = "${isResult  == false}">
        <h1  class="text-center" >Group Schedule</h1>

        <div class="col-md-2"></div>
        <div class="col-md-8">
            <form class="form-horizontal" action="groupSchedule" method="post">
                <div class="form-group form-group-lg" >
                    <label class="col-sm-2 control-label" for="group">Group </label>
                    <div class="col-sm-10">
                        <select name="group" id="group" class="form-control">

                            <c:forEach var="group" items="${groups}">
                                <option value="${group.id}">Group ${group.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group form-group-lg">
                    <label class="col-sm-2 control-label">Week </label>
                    <div class="col-sm-10">
                        <div class="col-sm-2">
                            <label class = "radio-inline" ><input type="radio" name="oddnes"  value="1" checked>ODD</label>
                        </div>
                        <div class="col-sm-2">
                            <label class = "radio-inline" ><input type="radio" name="oddnes"  value="2">EVEN</label>
                        </div>
                        <div class="col-sm-7"></div>
                    </div>
                </div>
                <input type="submit" class="btn btn-info" value="Get schedule">
            </form>


        </div>
    </c:if>

    <c:if test = "${isResult  == true}">
        <h1  class="text-center" >Schedule for Group ${groupName} for ${oddnes} week</h1>
        <table class="table">
            <thead>
            <tr>
                <th class="col-sm-2">Pair</th>
                <th class="col-sm-2">Monday</th>
                <th class="col-sm-2">Tuesday</th>
                <th class="col-sm-2">Wednesday</th>
                <th class="col-sm-2">Thursday</th>
                <th class="col-sm-2">Friday</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <table class="table table-striped">
                        <c:forEach var="pair" items="${pairs}">
                            <tr>
                                <td>
                                    <br>
                                        ${pair}
                                    <br>
                                    <br>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </td>
                <td>
                    <table class="table table-striped">
                        <c:forEach var="tt" items="${timeTable1}">
                            <tr>
                                <td>
                                    <c:if test = "${tt  != null}">${tt.subject.name}(${tt.subject.type})
                                        <br>Classroom ${tt.classroom.name}<br>
                                        ${tt.teacher.firstName} ${tt.teacher.lastName}</c:if>

                                    <c:if test = "${tt  == null}"><br>
                                        -----
                                    <br>
                                    <br>
                                    </c:if>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </td>
                <td>
                    <table class="table table-striped">
                        <c:forEach var="tt" items="${timeTable2}">
                            <tr>
                                <td>
                                    <c:if test = "${tt  != null}">${tt.subject.name}(${tt.subject.type})
                                        <br>Classroom ${tt.classroom.name}<br>
                                        ${tt.teacher.firstName} ${tt.teacher.lastName}</c:if>

                                    <c:if test = "${tt  == null}"><br>
                                        -----
                                        <br>
                                        <br>
                                    </c:if>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </td>
                <td>
                    <table class="table table-striped">
                        <c:forEach var="tt" items="${timeTable3}">
                            <tr>
                                <td>
                                    <c:if test = "${tt  != null}">${tt.subject.name}(${tt.subject.type})
                                        <br>Classroom ${tt.classroom.name}<br>
                                        ${tt.teacher.firstName} ${tt.teacher.lastName}</c:if>

                                    <c:if test = "${tt  == null}"><br>
                                        -----
                                        <br>
                                        <br>
                                    </c:if>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </td>
                <td>
                    <table class="table table-striped">
                        <c:forEach var="tt" items="${timeTable4}">
                            <tr>
                                <td>
                                    <c:if test = "${tt  != null}">${tt.subject.name}(${tt.subject.type})
                                        <br>Classroom ${tt.classroom.name}<br>
                                        ${tt.teacher.firstName} ${tt.teacher.lastName}</c:if>

                                    <c:if test = "${tt  == null}"><br>
                                        -----
                                        <br>
                                        <br>
                                    </c:if>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </td>
                <td>
                    <table class="table table-striped">
                        <c:forEach var="tt" items="${timeTable5}">
                            <tr>
                                <td>
                                    <c:if test = "${tt  != null}">${tt.subject.name}(${tt.subject.type})
                                        <br>Classroom ${tt.classroom.name}<br>
                                        ${tt.teacher.firstName} ${tt.teacher.lastName}</c:if>

                                    <c:if test = "${tt  == null}"><br>
                                        -----
                                        <br>
                                        <br>
                                    </c:if>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </td>
            </tr>
            </tbody>
        </table>

        <a class="btn btn-default" href="groupSchedule">Get schedule for another group</a><br><br>
        <a class="btn btn-default" href="teacherSchedule">Get schedule for teacher</a>

    </c:if>
    <page:footer/>
</div>


<page:js/>
<script src="../../../assets/js/schedule.js" ></script>
<script src="../../../assets/js/confirm.js" ></script>


</body>
</html>


