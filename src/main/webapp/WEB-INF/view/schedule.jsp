

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>


<html>
<head>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <link rel="stylesheet" href="/assets/css/main.css">

</head>
<body>

<div class="container">

    <nav class="navbar navbar-default navbar-inverse" style="border-radius:0px !important; margin-bottom:0px;">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="/classrooms">UNIVERSITY</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Services<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">All Groups</a></li>
                            <li><a href="#">All classrooms</a></li>
                            <li><a href="#">All Subjects</a></li>

                            <li><a href="#">Add new classroom</a></li>
                            <li><a href="#">Add new Group</a></li>
                            <li><a href="#">Add new Subject</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Schedule</a></li>
                        </ul>
                    </li>
                    <%--<li><a href="#">News</a></li>--%>
                    <li><a href="contact.jsp">Contact</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <form class="navbar-form navbar-left" role="search" method="get" action="#">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search employee" name="search">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>

                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
    <h1  class="text-center" >SCHEDULE MAKER</h1>
    <c:if test = "${isResult  == false}">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <h3 class="text-center">Select Group add Subject to get available Classrooms</h3>
            <form class="form-horizontal" action="/schedule" method="post">
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
                <div class="form-group form-group-lg">
                    <div class="col-sm-2 control-label" >
                        <input type="submit" name="submit" value="Get classrooms" class="btn btn-info " id="submitRoom">
                    </div>
                </div>
            </form>
        </div>
    </c:if>

    <c:if test = "${isResult  == true}">
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
                                        <c:if test = "${d.id == currentDay.id}"> selected</c:if>

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
                                        <c:if test = "${p.id == currentPair.id}"> selected</c:if>
                                >${p}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group form-group-lg">
                    <c:if test="${errors.teacherError != null}"><page:error title="${errors.teacherError}"/></c:if>
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
    </c:if>



    <page:js/>
    <script>
        $( document ).ready(function(){

            console.log('work');

            var $cat = $("#group"),
                    $subcat = $("#subject");

            $cat.on("change",function(){
                var _rel = $(this).val();
                console.log(_rel);
                $subcat.find("option").attr("style","");
                $subcat.val("");
                if(!_rel) return $subcat.prop("disabled",true);

                $subcat.find("[rel="+_rel+"]").show();

                $subcat.prop("disabled",false);
            });

        });

    </script>

    <page:footer/>
</div>

</body>
</html>


