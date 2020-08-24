<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Place Order(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

	<form action="saveaddress" method="POST">

		<div>
			<label>Delivery Address: </label>
			<input type="Text" name="address" required />
		</div>
		<br>
		<br>
		<div>
		<button>Save and Proceed</button>
		</div>
		
	</form>
	
<%-- Required View Template --%>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>