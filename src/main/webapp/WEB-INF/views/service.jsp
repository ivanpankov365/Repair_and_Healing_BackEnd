<?xml version="1.0" encoding="UTF-8"?>

<html>
<head>
	<title>Repair & Healing</title>
	 <script type="text/javascript" src="resources/jquery-3.2.1.js"></script>
</head>
<body>

<div
	xmlns:jsp="http://java.sun.com/JSP/Page" 
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	version="2.0"
>
	
	
    <jsp:directive.page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" />
	
    
	${newMaster}
    
	<form action="/service/form" method="POST">
		<table>
			
			<tr><td>NAME</td><td><input name="firstName" type="text" /></td></tr>
			<tr><td>SONAME</td><td><input name="lastName" type="text" /></td></tr>
			<tr><td>TELEPHONE</td><td><input name="telephonNumber" type="text" /></td></tr>
			<tr><td>LOGIN</td><td><input name="login" type="text" /></td></tr>
			<tr><td>PASSWORD</td><td><input name="password" type="text" /></td></tr>
			<tr><td>DEP_ID</td><td><input name="departmentId" type="text" /></td></tr>
		
			<tr><td></td><td><input type="submit" /></td></tr>
		</table>
		
	
		
	</form>
</div>
<div id = "buttom"> buttom
</div>

<script>
$( "#buttom" ).click(function() {
  alert( "Handler for .click() called." );
});
</script>

</body>
</html>