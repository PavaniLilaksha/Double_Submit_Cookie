<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<form method="post" action="LoginController">
<h1 style="color:#1F618D; text-align: center">LOGIN</h1>

<br/>
<p style="color:#1F618D; text-align: center">USERNAME : <input type="text" name="username"/></p>
<p style="color:#1F618D; text-align: center">PASSWORD : <input type="password" name="password"/></p>
<br/>
<c:if test="${not empty sessionScope.invalidCredentials}">
			<div id="message" style="color:red;">Incorrect username & password!</div>
		</c:if>
<p style="color:#1F618D; align: center; padding-left:46.2%">
<button type="submit" value="Login"style="color:#1F618D; text-align: center">LOGIN</button>
</p>
</form>
	
</body>
</html>