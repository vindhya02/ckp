
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

</head>
<body>
	<hr>
<h3>Show Kit Items</h3>

	<hr />
	
	<c:choose>
		<c:when test="${kitList==null}">
			<p>Add items to Kit</p>
		</c:when>
		<c:otherwise>
				<table border = "1">
				<tr><th> PRODUCT ID </th>
					<th> QUANTITY </th>
					<th> AMOUNT </th></tr>
					<c:forEach items="${kitList}" var="kd">
					<tr>
					<td>${kd.id}</td>
					<td>${kd.quantity}</td>
					<td>${kd.amount}</td>
					</tr>
					</c:forEach>
				</table>
		</c:otherwise>
	</c:choose>
	
</body>
</html>


