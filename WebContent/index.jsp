<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<title>BookStore Tracker App</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<%@ include file = "/header/header.jsp" %>
	
	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add BookStore -->
			
			<input type="button" value="Add Category" 
				   onclick="window.location.href='./category/add-category-form.jsp'; return false;"
				   class="add-student-button"
			/>
			
			<table>
			
				<tr>
					<th>Category Name</th>
					<th>Revenue</th>
					
				</tr>
				
				<c:forEach var="tempCategory" items="${CATEGORY_LIST}">
					<tr>
						<td> ${tempCategory.name} </td>
						<td> ${tempCategory.revenue}</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>
