<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>.
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
  <title>Schedule maker</title>
<page:css></page:css>

</head>
<body>
<div class="conteiner">
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Brand</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
          <li><a href="#">Link</a></li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">Action</a></li>
              <li><a href="#">Another action</a></li>
              <li><a href="#">Something else here</a></li>
              <li role="separator" class="divider"></li>
              <li><a href="#">Separated link</a></li>
              <li role="separator" class="divider"></li>
              <li><a href="#">One more separated link</a></li>
            </ul>
          </li>
        </ul>
        <form class="navbar-form navbar-left">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="Search">
          </div>
          <button type="submit" class="btn btn-default">Submit</button>
        </form>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#">Link</a></li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">Action</a></li>
              <li><a href="#">Another action</a></li>
              <li><a href="#">Something else here</a></li>
              <li role="separator" class="divider"></li>
              <li><a href="#">Separated link</a></li>
            </ul>
          </li>
        </ul>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>
  <c:if test = "${isResult  == false}">
    <h1>SCHEDULE MAKER</h1>

<h2>${groups}</h2>


    <form method="post" action="/classrooms">
      <table width="50%" border="0" cellspacing="0" cellpadding="5">
        <tr>
          <td width="41%" align="right" valign="middle">Group :</td>
          <td width="59%" align="left" valign="middle">
            <select name="group" id="group">
              <option value="">Select Group</option>
              <option value="11">Grpop 11</option>
              <option value="12">Grpop 12</option>
              <option value="13">Grpop 13</option>
              <option value="14">Grpop 14</option>
              <option value="21">Grpop 21</option>
              <option value="22">Grpop 22</option>
              <option value="23">Grpop 23</option>
              <option value="24">Grpop 24</option>
              <option value="31">Grpop 31</option>
              <option value="32">Grpop 32</option>
              <option value="33">Grpop 33</option>
              <option value="34">Grpop 34</option>
            </select>
          </td>
        </tr>
        <tr>
          <td align="right" valign="middle">Subject:</td>
          <td align="left" valign="middle">
            <select disabled="disabled" id="subject" name="subject">
              <option value>Select Subject</option>
              <!-- Grpop 11 -->
              <option rel="11" value="LECTURE">Algebra(LECTURE)</option>
              <option rel="11" value="PRACTICE">Algebra(PRACTICE)</option>
              <option rel="11" value="LECTURE">Philosophy(LECTURE)</option>
              <option rel="11" value="SEMINAR">Philosophy(SEMINAR)</option>

              <!-- Grpop 12 -->
              <option rel="12" value="LECTURE">Algebra(LECTURE)</option>
              <option rel="12" value="PRACTICE">Algebra(PRACTICE)</option>
              <option rel="12" value="LECTURE">Philosophy(LECTURE)</option>
              <option rel="12" value="SEMINAR">Philosophy(SEMINAR)</option>

              <!-- Grpop 13 -->
              <option rel="13" value="LECTURE">Algebra(LECTURE)</option>
              <option rel="13" value="PRACTICE">Algebra(PRACTICE)</option>
              <option rel="13" value="LECTURE">Philosophy(LECTURE)</option>
              <option rel="13" value="SEMINAR">Philosophy(SEMINAR)</option>

              <!-- Grpop 14 -->
              <option rel="14" value="LECTURE">Algebra(LECTURE)</option>
              <option rel="14" value="PRACTICE">Algebra(PRACTICE)</option>
              <option rel="14" value="LECTURE">Philosophy(LECTURE)</option>
              <option rel="14" value="SEMINAR">Philosophy(SEMINAR)</option>

              <!-- Grpop 21 -->
              <option rel="21" value="LECTURE">Python(LECTURE)</option>
              <option rel="21" value="LAB">Python(LAB)</option>
              <option rel="21" value="PRACTICE">Python(PRACTICE)</option>

              <!-- Grpop 22 -->
              <option rel="22" value="LECTURE">Python(LECTURE)</option>
              <option rel="22" value="LAB">Python(LAB)</option>
              <option rel="22" value="PRACTICE">Python(PRACTICE)</option>

              <!-- Grpop 23 -->
              <option rel="23" value="LECTURE">Algebra(LECTURE)</option>
              <option rel="23" value="PRACTICE">Algebra(PRACTICE)</option>

              <!-- Grpop 24 -->
              <option rel="24" value="LECTURE">Algebra(LECTURE)</option>
              <option rel="24" value="PRACTICE">Algebra(PRACTICE)</option>

              <!-- Grpop 31 -->
              <option rel="31" value="LECTURE">.NET(LECTURE)</option>
              <option rel="31" value="PRACTICE">.NET(PRACTICE)</option>

              <!-- Grpop 32 -->
              <option rel="32" value="LECTURE">.NET(LECTURE)</option>
              <option rel="32" value="PRACTICE">.NET(PRACTICE)</option>

              <!-- Grpop 33 -->
              <option rel="33" value="SEMINAR">Transformation(SEMINAR)</option>

              <!-- Grpop 33 -->
              <option rel="34" value="PRACTICE">MA(PRACTICE)</option>

            </select>
          </td>
        </tr>
      </table>

      <div class="col-md-12">
        <input type="submit" name="submit" value="Submit" class="btn btn-info " id="submitDepartment">
      </div>

    </form>

  </c:if>

  <c:if test = "${isResult  == true}">
    <h2>Available classroom(s) for  ${group} and ${subject}</h2>

    ${classrooms}

  </c:if>


</div>



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


</body>
</html>


