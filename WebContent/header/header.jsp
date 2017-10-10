<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div id="wrapper">
		<div id="header" class="header overflow">
			<div class="container">
				<div class="pull-left">
					<h1>FooBar University</h1>
				</div>
				<div class="pull-right">
					<ul class="menu-1">
						<li>
							<a href="#">Management</a>
							<ul class="menu-2">
								<li><a href="OrderControllerServlet">Order Management</a></li>
								<li><a href="CategoryControllerServlet">Category Management</a></li>
								<li><a href="AuthorServletController">Author Management</a></li>
								<li><a href="BookControllerServlet">Book Management</a></li>
								<li><a href="CustomerControllerServlet">Customer Management</a></li>
							</ul>
						</li>
						<li><a href=#">Report</a>
						<ul class="menu-2">
								<li><a href="BookStoreControllerServlet?command=TOP_AUTHOR_REVENUE">Top Author</a></li>
								<li><a href="BookStoreControllerServlet?command=TOP_CATEGORY_REVENUE">Top Category</a></li>
								<li><a href="BookStoreControllerServlet">All Report</a></li>
								
							</ul>
						
						<li class="login"><span class="pull-left">Nguyen Tuyen</span> <a href="#">Log out</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>