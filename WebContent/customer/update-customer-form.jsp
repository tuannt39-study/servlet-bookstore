<!DOCTYPE html>
<html>

<head>
<title>Update Customer</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">

</head>

<body>
	<%@ include file = "/header/header.jsp" %>

	<div id="container">
		<h3>Update Customer</h3>

		<form action="CustomerControllerServlet" method="GET">

			<input type="hidden" name="command" value="UPDATE" /> <input
				type="hidden" name="customerId" value="${the_Customer.id}" />

			<table>
				<tbody>
					<tr>
						<td><label>Name:</label></td>
						<td><input type="text" name="name"
							value="${the_Customer.name}" /></td>
					</tr>
					<tr>
						<td><label>Phone:</label></td>
						<td><input type="text" name="phone"
							value="${the_Customer.phone}" /></td>
					</tr>
					<tr>
						<td><label>DOB:</label></td>
						<td><input type="text" name="dobString"
							value="${the_Customer.dobString}" /></td>
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











