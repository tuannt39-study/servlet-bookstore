<!DOCTYPE html>
<html>

<head>
	<title>Add Customer</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">

</head>

<body>
	<%@ include file = "/header/header.jsp" %>
	
	<div id="container">
		<h3>Add Customer</h3>
		
		<form action="CustomerControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Customer Name:</label></td>
						<td><input type="text" name="name" /></td>
					</tr>
					<tr>
						<td><label>Customer Phone:</label></td>
						<td><input type="text" name="phone" /></td>
					</tr>
					<tr>
						<td><label>Customer DOB:</label></td>
						<td><input type="text" name="dobString" /></td>
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
			<a href="CustomerControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>











