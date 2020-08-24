<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Form</title>
</head>
<body>

	<jsp:include page="header.jsp" />
	
	
	<h3>New Item</h3>
	
	<form action="addItem" method="POST">
		<div>
			<label>Id: </label>
			<input type="number" value="${item.id }" name="pid" required />
		</div>
		<div>
			<label>Name: </label>
			<input type="text" value="${item.productName }" name="pname" minlength="3" maxlength="20" required />
		</div>
		<div>
		<div>
			<label>Description: </label>
			<input type="text" value="${item.productDescription }" name="pdesc" minlength="3" maxlength="20" required />
		</div>
		<div>
			<label>Cost Price: </label>
			<input type="decimal" value="${item.cost }" name="cost" required />
		</div>
		<button>SAVE</button>		
	</form>
		<jsp:include page="footer.jsp"/>
</body>
</html>