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
	<a href = "showproducts"><h3>Show Products to Add</h3></a>
	
	<c:choose>
		<c:when test="${items==null || items.isEmpty() }">
			<p></p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Desc</th>
					<th>Cost</th>
					<th>Quantity</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${items }" var="item">
					<tr>
					<form action="include">
					<td><input type =" text" name ="pid" value="${item.id }" readonly/></td>
					<td><input type = "text" name = "pname" value ="${item.productName }" readonly/></td>
					<td><input type = "text" name = "pdesc" value="${item.productDescription }" readonly/></td>
					<td><input type = "text " name = "cost" value ="${item.cost }" readonly/></td>
					<td><input type="text" name="quantity" id="${item.id}"/></td>
					<td><Button>ADD</Button></a>
					</td>
					</form>
					</tr> 
					</c:forEach>
					</table>
			</c:otherwise>
	</c:choose>
<jsp:include page="showkit.jsp" />
<br>
<a href = "placeorder" ><button>Place Order</button></a>
 	<jsp:include page="footer.jsp"/>
</body>
</html>