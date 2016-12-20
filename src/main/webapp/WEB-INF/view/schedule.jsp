

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
<page:header/>
<div class="container">

    <h1  class="text-center"  id="ttt">SCHEDULE MAKER</h1>
    <c:if test = "${isResult  == false}">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <h3 class="text-center">Select Group add Subject to create Time Table</h3>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/populateTimetable" method="post">
                <div class="form-group form-group-lg">
                    <label class="col-sm-2 control-label" for="group">Group </label>
                    <div class="col-sm-10">
                        <select name="group" id="group" class="form-control" onclick="f()">
                            <option value="">Select Group</option>
                            <c:forEach var="group" items="${requestScope.groups}">
                                <option value="${group.id}">Group ${group.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group form-group-lg">
                    <label class="col-sm-2 control-label" for="subject" id="subgroup_label">Subgroup</label>
                    <div class="col-sm-10">
                        <select name="subgroup" id="subgroup" class="form-control">
                            <option value="0">Select subgroup</option>
                            <c:forEach var="subgroup" items="${requestScope.group.subgroups}">
                                <option value="${subgroup.id}">${subgroup.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group form-group-lg">
                    <label class="col-sm-2 control-label" for="subject" id="subject_label">Subject </label>
                    <div class="col-sm-10">
                        <select disabled="disabled" id="subject" name="subject" class="form-control">
                            <option value>Select Subject</option>
                        </select>

                    </div>
               </div>
                <div class="form-group form-group-lg">
                    <div class="col-sm-2 control-label" >
                        <input type="submit" name="submit" value="Get classrooms" class="btn btn-info " id="submitRoom" disabled>
                    </div>
                </div>
            </form>
        </div>

    </c:if>

    <c:if test = "${isResult  == true}">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <h3 class="text-center">Available classroom(s) for Group-${group.name}(${group.count} students) and ${subject.name}(${subject.type})</h3>
            <c:if test="${requestScope.streamGroups.size() ne null}">
            <h2>Selected subject is a stream. Therefore new timetables for the following groups were created</h2>
                <c:forEach var="group" items="${requestScope.streamGroups}">
                    ${group.name}
                </c:forEach>
            </c:if>
            <c:if test="${errors.groupError != null}"><page:error title="${errors.groupError}"/></c:if>
            <c:if test="${errors.classroomError != null}"><page:error title="${errors.classroomError}"/></c:if>
            <h3 class="text-center">Select Classroom, Day of week add Pair Number to create new Pair</h3>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/addTimetable" method="post">
                <input type="hidden" id="groupAdd" name="groupAdd" value="${group.id}">
                <input type="hidden" id="subjectAdd" name="subjectAdd" value="${subject.id}">
                <input type="hidden" id="subgroupAdd" name="subgroupAdd" value="${requestScope.subgroup.id}">
                <input type="hidden" id="studentCommunityAdd" name="studentCommunityAdd" value="${requestScope.studentCommunity.id}">
                <div class="form-group form-group-lg">
                    <label class="col-sm-2 control-label" for="day">Select Day </label>
                    <div class="col-sm-10">
                        <select id="day" name="day" class="form-control">
                            <c:forEach var="d" items="${requestScope.days_of_week}">
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

                            <c:forEach var="p" items="${requestScope.pairs}">
                                <option value="${p}"
                                        <c:if test = "${p == currentPair}"> selected</c:if>
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
                            <c:forEach var="t" items="${requestScope.teachers}">
                                <option value="${t.id}"
                                        <c:if test = "${t.id == currentTeacher.id}"> selected</c:if>
                                >${t.firstName} ${t.lastName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group form-group-lg">
                    <label class="col-sm-2 control-label" for="day">Select Week </label>
                    <div class="col-sm-10">
                        <select name="oddnessAdd" class="form-control">
                            <c:forEach var="oddness" items="${oddneses_of_week}">
                                <option>${oddness}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <hr>
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
            classroomValidation();
            $('input:radio').click(function () {
                document.getElementById("submitSchedule").removeAttribute("disabled");
            });
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
    <script>
        window.addEventListener("load", init, false);
        function init () {
            console.log('init');
            subgroup.addEventListener("change",validation,false);
            subject.addEventListener("change", validation, false);
            group.addEventListener("change",getSubjects,false);
            subgroup.addEventListener("change",getSubjects,false);

        }

        function validation () {
            var group = $("#group option:selected").text();
            var subject = $("#subject option:selected").text();
            if (group != "Select group" && subject!= "Select subject" ){
                document.getElementById("submitRoom").removeAttribute("disabled");
            } else {
                document.getElementById("submitRoom").setAttribute("disabled","true");
            }
        }

        function classroomValidation() {
            try {
                if ($('input[name=classroom]:checked').val() === undefined) {
                    document.getElementById("submitSchedule").setAttribute("disabled", "true");
                } else document.getElementById("submitSchedule").removeAttribute("disabled");
            } catch (exc){}
        }
    </script>


    <script type="text/javascript">
        function f(){
            $.ajax({
                url: "subgroups",
                 data: {
                 "group": jQuery("#group").val()
                 },
                cache: false,
                type: "get",
                success: function(response) {
                    $('#subgroup').empty().append($("<option/>",{
                        text: "Select subgroup",
                        value:"0"
                    }));
                    var obj=JSON.parse(response);
                    for (var i=0; i<obj.subgroups.length; i++){
                        $('#subgroup').append($("<option/>", {
                            value: obj.subgroups[i].id,
                            text: obj.subgroups[i].name + " "+obj.subgroups[i].subjects[0].type
                        }));
                    }
                },
                error: function(xhr) {
                }
            });
        }
        $("#subject").hide();
        $("#subject_label").hide();
        function getSubjects() {
            var group_id;
            if ($("#subgroup").val()==0)
            group_id=$("#group").val();
            else group_id=$("#subgroup").val();

                $("#subject").show();
                $("#subject_label").show();
                $.ajax(
                        {
                           url: "subjectsOfStudCommunity",
                            data: {
                                "group_id": group_id
                            },
                            cache:false,
                            type:"get",
                            success: function (response) {
                                $('#subject').empty().append($("<option/>",{
                                    text:"Select subject",
                                    value:"0"
                                }));
                                var obj=JSON.parse(response);
                                console.log(obj);
                                for (var i=0;i<obj.length;i++){

                                    $('#subject').append($("<option/>",{
                                        text: obj[i].name +"("+obj[i].type+")",
                                        value:obj[i].id
                                    }));
                                }
                            },
                            error: function (xhr) {

                            }
                        });
        }
    </script>


    <page:footer/>
</div>

</body>
</html>


