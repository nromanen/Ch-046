<!DOCTYPE html>
<html>
<head>
	<title></title>
	 <!--Import Google Icon Font-->
      <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
     
	 <!-- Compiled and minified CSS -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.css">
  <!-- Compiled and minified JavaScript -->
   <!--Import jQuery before materialize.js-->
      <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.js"></script>
     <script type="text/javascript">
     $( document ).ready(function(){
$(".button-collapse").sideNav();
     });
     </script>     

</head>
<body >  
    <nav>
      <div class="nav-wrapper">
        <ul class="right hide-on-med-and-down"> </ul>
      </div>
    </nav>
 
 
 <br> <br>
      <div >
      
         <form class="col s6">
            <div class="row">
               <div class="input-field col s3 offset-s1">
                  <!-- <i class="material-icons prefix">account_circle</i> -->
                  <input id="Username" type="text" class="validate" required>
                  <label for="Username">Username</label>
               </div>

               </div> 
               <div class="row"> 
               <div class="input-field col s3 offset-s1">      
                  <label for="password">Password</label>
                  <input id="password" type="text" class="validate" required>    
                  <br> <br> <br>
                  <button class="btn waves-effect waves-teal ">Submmit</button>
               </div>
            </div>    
             
         </form>       
      </div>
   </body>   
</html>