<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
<body style = "background : skyblue">

<h2 style = "text-align : center"><b>Track My Time</b></h2>

<p style = "color : Brown">Note : After successful sign up, You will be redirected to sign in page</p>

<div id = signupResult></div>


<label id = "labels" >Enter User Name</label><br>
   <input type = "text" name = "userName" id = "userName"><br><br>
   
   <label id = "labels" >Mail ID</label><br>
   <input type = "email" name = "mailId" id = "mailId"><br><br><br>
   
   <label id = "labels" >Enter Password</label><br>
   <input type = "password" name = "Password" id = "password"><br>
   
   <p style = "color : red">*minimum 8 characters</p>   
   
   <input type="submit" value="Create ID" onclick = "signUp()"><br><br>
   
         
   
   
   <a href = 'Login.jsp'><input type="submit" value="Return to Login page"></a>
   
<script type="text/javascript" src = "Timer.js"></script>
</body>
</html>