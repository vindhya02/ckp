<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items List</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<a href="newItem">Add New Item</a>
	<h3>Items</h3>
	
	<c:choose>
		<c:when test="${items==null || items.isEmpty() }">
			<p>No Items Found</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Desc</th>
					<th>Cost</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${items }" var="item">
					<tr>
					<td>${item.id }</td>
					<td>${item.productName }</td>
					<td>${item.productDescription }</td>
					<td>${item.cost }</td>
					<td>
						<a href = "deleteItem?pid=${item.id }">DELETE</a> <span>|</span>
						<a href="editItem?pid=${item.id }">EDIT</a> <span>|</span>
					</td>
				</tr>				
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
		<jsp:include page="footer.jsp"/>
</body>
</html>