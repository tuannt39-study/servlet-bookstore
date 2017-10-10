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

			<input type="hidden" name="command" value="UPDATE_DETAIL" /> <input
				type="hidden" name="orderDetailId" value="${the_Order_Detail.id}" />
			 <input
				type="hidden" name="orderId" value="${the_Order_Detail.order.id}" />
			<table>
				<tbody>
					<tr>
						<td><label>Quantity:</label></td>
						<td><input type="text" name="quantityString"
							value="${the_Order_Detail.quantity}" /></td>
					</tr>
					<tr>
						<td><label>Book:</label></td>
						<td><select name="bookName">
								<option value="${the_Order_Detail.book.ID}" selected>${the_Order_Detail.book.name}</option>
								<c:forEach var="row" items="${book_List}">
									<c:if test="${row.ID != the_Order_Detail.book.ID}">
										<option value='<c:out value="${row.ID}" />'>${row.name}</option>
									</c:if>
								</c:forEach>
						</select></td>
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
			<a href="StudentControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>











