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
			
			<a href="CustomerControllerServlet?command=NEW">Add new Customer</a>
			
			<table>
			
				<tr>
					<th>Customer Name</th>
					<th>Customer Phone</th>
					<th>Customer DOB</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempCustomer" items="${CUSTOMER_LIST}">
					
					<!-- set up a link for each student -->
					<c:url var="tempLink" value="CustomerControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>

					<c:url var="deleteLink" value="CustomerControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
																		
					<tr>
						<td> ${tempCustomer.name} </td>
						<td> ${tempCustomer.phone} </td>
						<td> ${tempCustomer.dobString} </td>
						<td> 
							<a href="${tempLink}">Update</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">
							Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>








