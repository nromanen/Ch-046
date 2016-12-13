

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
    <h1  class="text-center" >SCHEDULE MAKER without web.xml</h1>
    <c:if test = "${isResult  == false}">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <h3 class="text-center">Select Group add Subject to create Time Table</h3>
            <form class="form-horizontal">
                <div class="form-group form-group-lg">
                    <label class="col-sm-2 control-label" for="group">Group </label>
                    <div class="col-sm-10">
                        <select name="group" id="group" class="form-control">
                            <option value="">Select Group</option>
                            <c:forEach var="group" items="${groups}">
                                <option value="${group.id}">Group ${group.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group form-group-lg">
                    <label class="col-sm-2 control-label" for="subject">Subject </label>
                    <div class="col-sm-10">
                        <select disabled="disabled" id="subject" name="subject" class="form-control">
                            <option value>Select Subject</option>
                            <c:forEach var="group" items="${groups}">
                                <c:forEach var="subject" items="${group.subjects}">
                                    <option rel="${group.id}" value="${subject.id}" >${subject.name}(${subject.type})</option>
                                </c:forEach>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </form>
            <div class="col-sm-2 control-label" >
                <button class="btn btn-info " id="checkSubject" disabled>Next</button>
            </div>
        </div>
    </c:if>

    <c:if test = "${isResult  == true}">
        <c:if test="${warningMessage!=null}">
            <div class="row" id="warning">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <div class="alert alert-warning">${warningMessage}&nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="button" class="btn btn-success" id="warningYes">&nbsp;&nbsp;&nbsp;Yes&nbsp;&nbsp;&nbsp;</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a type="button" class="btn btn-warning" href="/">&nbsp;&nbsp;&nbsp;No&nbsp;&nbsp;&nbsp;</a>
                    </div>
                </div>
            </div>
        </c:if>

        <div class="row" id="mainContent" <c:if test="${warningMessage!=null}">style="display: none;" </c:if>>
            <c:if test="${warningStreamMessage!=null}">
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <div class="alert alert-warning">${warningStreamMessage}</div>
                    </div>
                </div>
            </c:if>

            <div class="col-md-2"></div>
            <div class="col-md-8">
                <h3 class="text-center">Available classroom(s) for Group-${group.name}(${group.count} students) and ${subject.name}(${subject.type})</h3>

                <c:if test="${errors.groupError != null}"><page:error title="${errors.groupError}"/></c:if>
                <c:if test="${errors.classroomError != null}"><page:error title="${errors.classroomError}"/></c:if>
                <h3 class="text-center">Select Classroom, Day of week add Pair Number to create new Pair</h3>
                <form class="form-horizontal" action="/pair" method="post">
                    <input type="hidden" id="groupAdd" name="groupAdd" value="${group.id}">
                    <input type="hidden" id="subjectAdd" name="subjectAdd" value="${subject.id}">
                    <div class="form-group form-group-lg">
                        <label class="col-sm-2 control-label" for="day">Select Day </label>
                        <div class="col-sm-10">
                            <select id="day" name="day" class="form-control">
                                <c:forEach var="d" items="${days}">
                                    <option value="${d}"
                                            <c:if test = "${d == currentDay}"> selected</c:if>

                                    >${d}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group form-group-lg">
                        <label class="col-sm-2 control-label" for="pair">Select Pair </label>
                        <div class="col-sm-10">
                            <select id="pair" name="pair" class="form-control">

                                <c:forEach var="p" items="${pairs}">
                                    <option value="${p}"
                                            <c:if test = "${p == currentPair}"> selected</c:if>
                                    >${p}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <c:if test="${errors.teacherError != null}"><page:error title="${errors.teacherError}"/></c:if>
                    <div class="form-group form-group-lg">
                        <label class="col-sm-2 control-label" for="teacher">Select Teacher </label>
                        <div class="col-sm-10">
                            <select id="teacher" name="teacher" class="form-control">

                                <c:forEach var="t" items="${teachers}">
                                    <option value="${t.id}"
                                            <c:if test = "${t.id == currentTeacher.id}"> selected</c:if>
                                    >${t.firstName} ${t.lastName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <hr>
                    <div class="col-sm-2">
                        Select Week
                    </div>
                    <c:forEach var="o" items="${oddness}">
                        <div class="col-sm-1">
                            <label><input type="radio" name="oddnes" id="${o}" value="${o}"
                            <c:if test = "${o.id == currentOddness.id}"> checked</c:if>
                            >${o}</label>
                        </div>
                        <div class="col-sm-1"></div>
                    </c:forEach>

                    <hr>
                    <hr>
                    <h3 class="text-center">Select classroom</h3>
                    <table class="table table-striped">
                        <tr>
                            <td>Name</td>
                            <td>Capacity</td>
                            <td>Type</td>
                            <td>Description</td>
                        </tr>
                        <c:forEach var="room" items="${classrooms}">
                            <tr>
                                <td>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="classroom" id="${room.id}" value="${room.id}"
                                            <c:if test = "${room.id == currentClassroom.id}"> checked</c:if>
                                            >
                                            Classroom ${room.name}
                                        </label>
                                    </div>
                                </td>
                                <td>${room.capacity}</td>
                                <td>${subject.type}</td>
                                <td>${room.description}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="col-sm-2 control-label" >
                        <input type="submit" value="Create new Pair" class="btn btn-info " id="submitSchedule">
                    </div>
                </form>
            </div>
        </div>

    </c:if>
    <page:footer/>
</div>


<div id="dialogoverlay"></div>
<div id="dialogbox">
    <div>
        <div id="dialogboxhead"></div>
        <div id="dialogboxbody"></div>
        <div id="dialogboxfoot"></div>
    </div>
</div>

<page:js/>
<script src="../../assets/js/schedule.js" ></script>
<script src="../../assets/js/confirm.js" ></script>

<script>

    window.addEventListener("load", init, false);

    function init () {
        warningYes.addEventListener("click", show, false);

    }

    function show () {

        document.getElementById('mainContent').style.display = '';
        document.getElementById('warning').style.display = 'none';


    }


</script>
</body>
</html>


