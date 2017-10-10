<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>BookStore Tracker App</title>
	
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">

</head>

<body>

	<%@ include file = "/header/header.jsp" %>

	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add BookStore -->
			
			<a href="OrderControllerServlet?command=NEW">Add new Order</a>
			<table>
			
				<tr>
					<th>Order Date</th>
					<th>Customer</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempOrder" items="${ORDER_LIST}">
					
					<!-- set up a link for each student -->
					<c:url var="tempLink" value="OrderControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="orderId" value="${tempOrder.id}" />
					</c:url>

					<c:url var="deleteLink" value="OrderControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="orderId" value="${tempOrder.id}" />
					</c:url>
																		
					<tr>
						<td> ${tempOrder.orderDateString} </td>
						<td> ${tempOrder.customer.name} </td>
						<td> 
							<a href="${tempLink}">Update</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this order?'))) return false">
							Delete</a>	
						</td>
					</tr>
					
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>








