package com.vietjack.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vietjack.core.Customer;
import com.vietjack.dao.CustomerDAO;

/**
 * Servlet implementation class CustomerControllerServlet
 */
@WebServlet("/CustomerControllerServlet")
public class CustomerControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static CustomerDAO customerDAO = new CustomerDAO();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing Customers
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listCustomers(request, response);
				break;
				
			case "ADD":
				addCustomer(request, response);
				break;
			case "NEW":
				newCustomer(request, response);
				break;
			case "LOAD":
				loadCustomer(request, response);
				break;
				
			case "UPDATE":
				updateCustomer(request, response);
				break;
			
			case "DELETE":
				deleteCustomer(request, response);
				break;
			default:
				listCustomers(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read Customer id from form data
		String theCustomerId = request.getParameter("customerId");
		
		// delete Customer from database
		customerDAO.deleteCustomerById(Long.parseLong(theCustomerId));
		
		// send them back to "list Customers" page
		listCustomers(request, response);
	}

	private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read Customer info from form data
		int id = Integer.parseInt(request.getParameter("customerId"));
		String name = request.getParameter("name");
		String dobString = request.getParameter("dobString");
		String phone = request.getParameter("phone");
		Date dob=sdf.parse(dobString);
		// create a new Customer object
		Customer theCustomer = new Customer(id, name,phone,dob);
		
		// perform update on database
		customerDAO.modifyCustomer(theCustomer);
		
		// send them back to the "list Customers" page
		listCustomers(request, response);
		
	}

	private void loadCustomer(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// read Customer id from form data
		String theCustomerId = request.getParameter("customerId");
		
		// get Customer from database (db util)
		Customer theCustomer = customerDAO.findCustomerById(Long.parseLong(theCustomerId));
		
		// place Customer in the request attribute
		request.setAttribute("the_Customer", theCustomer);
		
		// send to jsp page: update-Customer-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/customer/update-customer-form.jsp");
		dispatcher.forward(request, response);		
	}
	private void newCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {

		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/customer/add-customer-form.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Customer info from form data
		String name = request.getParameter("name");
		String dobString = request.getParameter("dobString");
		String phone = request.getParameter("phone");
		Date dob=sdf.parse(dobString);

		// create a new Customer object
		Customer theCustomer = new Customer(customerDAO.generateId(),name,phone,dob);
		
		// add the Customer to the database
		customerDAO.addNewCustomer(theCustomer);
				
		// send back to main page (the Customer list)
		listCustomers(request, response);
	}

	private void listCustomers(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get Customers from db util
		List<Customer> Customers = customerDAO.findAllCustomer();
		
		// add Customers to the request
		request.setAttribute("CUSTOMER_LIST", Customers);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/list-customer.jsp");
		dispatcher.forward(request, response);
	}

}
