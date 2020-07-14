<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

</head>
<body>
<%     
session = request.getSession(false);

if(session==null){
	response.sendRedirect("Login.jsp");
}
else{
	response.sendRedirect("/Dashboard");
}


  
%> 
</body>
</html>