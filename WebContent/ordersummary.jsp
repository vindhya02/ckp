<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Order Summary(user)</title>
</head>
<body>
<hr>
<jsp:include page="header.jsp"/>

<h3>Order Summary</h3>
	
	<c:choose>
		<c:when test="${user==null || kitList==null}">
			<p>Session Timed-Out</p>
		</c:when>
		<c:otherwise>
		<Label>User Name :</Label> ${user.personName} <br>
		<Label>User Email :</Label> ${user.email}<br>
		<Label>Contact Number :</Label>${user.contactNumber} <br>
		<Label>Ship To:</Label> ${address}
		<br>
		<br>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>ID</th>
					<th>Cost</th>
					<th>Quantity</th>
					<th>Amount</th>
				</tr>
				<c:forEach items="${kitList}" var="item">
					<tr>
					<td>${item.id}</td>
					<td>${item.cost}</td>
					<td>${item.quantity}</td>
					<td>${item.amount }</td>
				</tr>				
				</c:forEach>
	<tr><td><Label><b>Grand Total</b></Label></td><td>=</td><td><b>${total}</b></td></tr>


		</c:otherwise>
	</c:choose>
</table>
	
	<jsp:include page="footer.jsp"/>
</body>
</html>