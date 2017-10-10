<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Add Order</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">

</head>

<body>
	<%@ include file = "/header/header.jsp" %>
	
	<div id="container">
		<h3>Add Order Detail</h3>
		
		<form action="OrderControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="ADD_DETAIL" />
			<input type="hidden" name="orderId" value="${order_ID}" />
			<table>
				<tbody>
					<tr>
						<td><label>Book:</label></td>
						<td><select name="bookName">
								<c:forEach var="row" items="${book_List}">
										<option value='<c:out value="${row.ID}" />'>${row.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>Quantity:</label></td>
						<td><input type="text" name="quantityString" /></td>
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
			<a href="./OrderControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>











