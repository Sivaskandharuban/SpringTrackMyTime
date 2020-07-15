<%@ page language="java" session="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

</head>
<body style="background: skyblue">

	<h2 style="text-align: center">
		<b>Track My Time</b>
	</h2>
<div id = loginResult></div>
	<div id="Login">

		<label id="labels">E-Mail Id</label><br> 
		<input type="email" name="mail" id = "mailId"><br><br>
		
		<label id="labels">Password</label><br> 
		<input	type="password" name="pass" id = "Password"><br><br> 
		
		<button onclick = "login()">Sign In</button><br><br> 
		<p>New to Track My Time? Sign up to create account</p>
		<a href='SignUp.jsp'><input type="submit" value="Sign up"></a>

	
	</div>
	<script type="text/javascript" src = "Timer.js"></script>
</body>
</html>