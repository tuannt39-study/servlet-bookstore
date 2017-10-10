<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
<title>Update Order</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">

</head>

<body>
	<%@ include file = "/header/header.jsp" %>

	<div id="container">
		<h3>Update Order</h3>

		<form action="OrderControllerServlet" method="GET">

			<input type="hidden" name="command" value="UPDATE" /> <input
				type="hidden" name="orderId" value="${the_Order.id}" />

			<table>
				<tbody>
					<tr>
						<td><label>Date:</label></td>
						<td><input type="text" name="orderDateString"
							value="${the_Order.orderDateString}" /></td>
					</tr>
					<tr>
						<td><label>Customer:</label></td>
						<td><select name="customerName">
								<option value="${the_Order.customer.id}" selected>${the_Order.customer.name}</option>
								<c:forEach var="row" items="${customer_List}">
									<c:if test="${row.id != the_Order.customer.id}">
										<option value='<c:out value="${row.id}" />'>${row.name}</option>
									</c:if>
								</c:forEach>
						</select></td>
					</tr>
					<tr><td>
							<c:url var="tempAddLink" value="OrderControllerServlet">
										<c:param name="command" value="NEW_DETAIL" />
										<c:param name="orderId" value="${the_Order.id}" />
									</c:url>
									<a href="${tempAddLink}">Add new OrderDetail</a>
						</td></tr>
					<tr>
						<td colspan="2">
							<table>
								<tr>
									<th>Book</th>
									<th>Quantity</th>
									<th>Action</th>
								</tr>
								<c:forEach var="tempOrderDetail" items="${ORDER_DETAIL_LIST}">
									<!-- set up a link for each student -->
									<c:url var="tempLink" value="OrderControllerServlet">
										<c:param name="command" value="LOAD_ORDER_DETAIL" />
										<c:param name="orderId" value="${the_Order.id}" />
										<c:param name="orderDetailId" value="${tempOrderDetail.id}" />
									</c:url>
				
									<c:url var="deleteLink" value="OrderControllerServlet">
										<c:param name="command" value="DELETE_ORDER_DETAIL" />
										<c:param name="orderId" value="${the_Order.id}" />
										<c:param name="orderDetailId" value="${tempOrderDetail.id}" />
									</c:url>
																						
									<tr>
										<td> ${tempOrderDetail.book.name} </td>
										<td> ${tempOrderDetail.quantity} </td>
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
						</td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				</tbody>
			</table>
		</form>

		<div style="clear: both;"></div>

		<p>
			<a href="OrderControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>











