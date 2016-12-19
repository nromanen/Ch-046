

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
    <h1  class="text-center" >SCHEDULE MAKER</h1>
    <c:if test = "${isResult  == false}">

        <div class="col-md-2"></div>
        <div class="col-md-8">
            <c:if test="${message!=null}">
                <div class="alert alert-success">${message}<br>
                    <h4>${timeTable}</h4>
                </div>
            </c:if>
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
            <div class="col-sm-12 control-label" >
                <button class="btn btn-info " id="checkSubject" disabled>Next</button>
            </div>
        </div>
    </c:if>

    <c:if test = "${isResult  == true}">

        <div class="row" >
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
                <h3 class="text-center">Timetable for ${groupName} (${capacity} students) and ${subject.name}(${subject.type})</h3>
                <c:forEach var="error" items="${errors}">
                    <c:if test="${error.groupError != null}"><page:error title="${error.groupError}"/></c:if>
                    <c:if test="${error.classroomError != null}"><page:error title="${error.classroomError}"/></c:if>
                    <c:if test="${error.teacherError != null}"><page:error title="${error.teacherError}"/></c:if>
                </c:forEach>

                <form class="form-horizontal"
                <c:if test="${link != null}">
                      action=${link}
                </c:if>
                <c:if test="${link == null}">
                    action="pair"
                </c:if>
                      method="post">
                    <c:if test="${group != null}"><input type="hidden" id="groupAdd" name="groupAdd" value="${group.id}"></c:if>


                    <input type="hidden" id="subjectAdd" name="subjectAdd" value="${subject.id}">
                    <div class="form-group form-group-lg">
                        <label class="col-sm-2 control-label" for="day">Day </label>
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
                        <label class="col-sm-2 control-label" for="pair">Pair </label>
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

                    <div class="form-group form-group-lg">
                        <label class="col-sm-2 control-label" for="teacher">Teacher </label>
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
                    <div class="form-group form-group-lg">
                        <label class="col-sm-2 control-label">Week </label>
                        <div class="col-sm-10">
                            <c:forEach var="o" items="${oddness}">
                                <div class="col-sm-2">
                                    <label class = "radio-inline" ><input type="radio" name="oddnes" id="${o}" value="${o}"
                                    <c:if test = "${o.id == currentOddness.id}"> checked</c:if>
                                    >${o}</label>
                                </div>

                            </c:forEach>
                            <div class="col-sm-7"></div>
                        </div>
                    </div>
                    <hr>
                    <h3 class="text-center">Available classroom</h3>
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
        <c:if test="${link != null}">
            <input type="submit" value="Update Pair" class="btn btn-info " id="submitSchedule">
        </c:if>
                        <c:if test="${link == null}">
                            <input type="submit" value="Create new Pair" class="btn btn-info " id="submitSchedule">
                        </c:if>


                    </div>
                    <c:if test="${stream != null}">
                        <select class="form-control" name="stream" id="types" multiple style="visibility: hidden">
                            <c:forEach var="groupId" items="${stream}">
                                <option value="${groupId}" selected>${groupId}</option>
                            </c:forEach>
                        </select>
                    </c:if>
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
        <div id="dialogboxfoot"><button class='btn btn-success' onclick="Confirm.yes()">Confirm</button>&nbsp;&nbsp;&nbsp;&nbsp;<button class='btn btn-danger' onclick="Confirm.no()">Cancel</button></div>
    </div>
</div>

<page:js/>
<script src="assets/js/schedule.js" ></script>
<script src="assets/js/confirm.js" ></script>

</body>
</html>


