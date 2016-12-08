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


    <c:if test="${message!=null}">
        <div class="alert alert-success">${message}</div>
    </c:if>

    <c:if test="${errorMessage!=null}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <h3 class="text-center">All classrooms</h3>

    <table class="table table-striped">
        <tr>
            <td>Name</td>
            <td>Capacity</td>
            <td>Type</td>
            <td>Description</td>
            <td colspan="2">Action</td>


        </tr>
        <c:forEach var="room" items="${classrooms}">
            <tr
                    <c:if test="${room.id == updateClassroom.id}">class="success"</c:if>
                     id = "room_${room.id}"
            >
                <td>Classroom ${room.name}</td>
                <td>${room.capacity}</td>
                <td>${room.types}</td>
                <td>${room.description}</td>
                <td>
                    <a href="/classroomUpdate?id=${room.id}" class="btn btn-info btn-sm">
                        <span class="glyphicon glyphicon-pencil"></span> Edit
                    </a>
                </td>
                <td>
                    <button class="btn btn-danger btn-sm" onclick="Confirm.render('Are you sure you want to delete classroom?','delete_room','room_${room.id}', '${room.id}')" > Remove</button>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div class="col-md-12">
        <form method="get" action="/classroomUpdate">
            <input type="number" hidden name="action" value="new">
            <input type="number" hidden name="id" value="0">
            <input type="submit" name="submit" value="Add new Classroom" class="btn btn-default">
        </form>
    </div>
    <div class="col-md-12">
        <a id="option" class="btn btn-default btn-sm">More options</a>
    </div>
    <br>
    <div class="row" id="moreOption"  style="display: none">
        <br>
        <hr>
        <br>
        Add classrooms to DB from file(supported formats .json, .xml, .txt):
        <form action="/upp" method="post" enctype="multipart/form-data" class="form-inline">
            <div class="col-md-4">
                <input type="file" name="file" size="50" class="filestyle" data-buttonName="btn-primary" id = "fileId"/>
            </div>
            <div class="col-md-4">
                <input type="submit" value=" Add to DB"  class="btn btn-default" id = "addFile" disabled/>
            </div>
        </form>
        <div class="col-md-4"></div>
        <br>
        <hr>
        <br>
        <div class="col-md-5">
            <a href="/DownloadFileServlet" class="btn btn-default"> Download classrooms</a>
        </div>

    </div>

</div>

<div id="dialogoverlay"></div>
<div id="dialogbox">
    <div>
        <div id="dialogboxhead"></div>
        <div id="dialogboxbody"></div>
        <div id="dialogboxfoot"></div>
    </div>
</div>



<page:footer/>
<page:js/>


  <script>

  window.addEventListener("load", init, false);

          var isFile = false;


          function init () {

                 console.log('init');
              fileId.addEventListener("change", validationFile, false);
              option.addEventListener("click", show, false);
          }

          function validationFile() {
              var fileId = document.getElementById("fileId").value;
              console.log(fileId);
              if (fileId == "" || fileId == null) {
                  isFile = false;
              } else {
                  isFile = true;
              }

              if (isFile){
                              document.getElementById("addFile").removeAttribute("disabled");
                          } else {
                              document.getElementById("addFile").setAttribute("disabled","true");
                          }
           }



      function deletePost(id, classroomId){
          var db_id = id.replace("room_", "");
          $.post(
            "/classrooms",
            {
              id: classroomId
            }
          );


          // Run Ajax request here to delete post from database
           document.getElementById(id).style.display = "none";
          //document.body.removeChild(document.getElementById(id));
      }
      function CustomConfirm(){
          this.render = function(dialog,op,id,classroomId){
              var winW = window.innerWidth;
              var winH = window.innerHeight;
              var dialogoverlay = document.getElementById('dialogoverlay');
              var dialogbox = document.getElementById('dialogbox');
              dialogoverlay.style.display = "block";
              dialogoverlay.style.height = winH+"px";
              dialogbox.style.left = (winW/2) - (550 * .5)+"px";
              dialogbox.style.top = "100px";
              dialogbox.style.display = "block";

              document.getElementById('dialogboxhead').innerHTML = "Confirm that action";
              document.getElementById('dialogboxbody').innerHTML = dialog;
              document.getElementById('dialogboxfoot').innerHTML = '<button onclick="Confirm.yes(\''+op+'\',\''+id+'\',\''+classroomId+'\')">Yes</button> <button onclick="Confirm.no()">No</button>';
          }
          this.no = function(){
              document.getElementById('dialogbox').style.display = "none";
              document.getElementById('dialogoverlay').style.display = "none";
          }
          this.yes = function(op,id,classroomId){
              if(op == "delete_room"){
                  deletePost(id, classroomId);
              }
              document.getElementById('dialogbox').style.display = "none";
              document.getElementById('dialogoverlay').style.display = "none";
          }
      }
      var Confirm = new CustomConfirm();


    function show () {

        document.getElementById('moreOption').style.display = (document.getElementById('moreOption').style.display == 'none') ? '' : 'none';
    }

    //$(":file").filestyle({buttonName: "btn-primary"});
</script>
</body>