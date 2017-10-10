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
			
			<a href="BookControllerServlet?command=NEW">Add new Book</a>
			
			<table>
			
				<tr>
					<th>Book Name</th>
					<th>Book Price</th>
					<th>Book Book</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempBook" items="${BOOK_LIST}">
					
					<!-- set up a link for each student -->
					<c:url var="tempLink" value="BookControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="bookId" value="${tempBook.ID}" />
					</c:url>

					<c:url var="deleteLink" value="BookControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="bookId" value="${tempBook.ID}" />
					</c:url>
																		
					<tr>
						<td> ${tempBook.name} </td>
						<td> ${tempBook.price} </td>
						<td> ${tempBook.category.name} </td>
						<td> 
							<a href="${tempLink}">Update</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this book?'))) return false">
							Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>








