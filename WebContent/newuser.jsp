<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Form</title>
</head>
<body>

	<jsp:include page="header.jsp" />
	
	<h3>New User</h3>
	
	<form action="saveuser" method="POST">
		<div>
			<label>Id: </label>
			<input type="number" value="${item.id }" name="userid" required />
		</div>
		<div>
			<label>Name: </label>
			<input type="text" value="${item.personName }" name="username" minlength="3" maxlength="20" required />
		</div>
		<div>
		<div>
			<label>Email: </label>
			<input type="text" value="${item.email }" name="email" minlength="3" maxlength="20" required />
		</div>
		<div>
			<label>Contact: </label>
			<input type="text" value="${item.contactNumber }" name="contact" required />
		</div>
		<a href ="showproducts"><button>SAVE</button></a>		
	</form>
		<jsp:include page="footer.jsp"/>
</body>
</html>