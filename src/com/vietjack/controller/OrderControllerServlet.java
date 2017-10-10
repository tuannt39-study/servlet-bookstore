package com.vietjack.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vietjack.core.Book;
import com.vietjack.core.Customer;
import com.vietjack.core.Order;
import com.vietjack.core.OrderDetail;
import com.vietjack.dao.BookDAO;
import com.vietjack.dao.CustomerDAO;
import com.vietjack.dao.OrderDAO;
import com.vietjack.dao.OrderDetailDAO;

/**
 * Servlet implementation class OrderControllerServlet
 */
@WebServlet("/OrderControllerServlet")
public class OrderControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static OrderDAO orderDAO = new OrderDAO();
	public static CustomerDAO customerDAO = new CustomerDAO();
	public static OrderDetailDAO orderdetailDAO = new OrderDetailDAO();
	public static BookDAO bookDAO = new BookDAO();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");

			// if the command is missing, then default to listing Orders
			if (theCommand == null) {
				theCommand = "LIST";
			}

			// route to the appropriate method
			switch (theCommand) {

			case "NEW":
				newOrder(request, response);
				break;
			case "LIST":
				listOrders(request, response);
				break;

			case "ADD":
				addOrder(request, response);
				break;

			case "LOAD":
				loadOrder(request, response);
				break;

			case "UPDATE":
				updateOrder(request, response);
				break;
			
			case "DELETE":
				deleteOrder(request, response);
				break;
			case "UPDATE_DETAIL":
				updateOrderDetail(request, response);
				break;
			case "NEW_DETAIL":
				newOrderDetail(request, response);
				break;
			case "ADD_DETAIL":
				addOrderDetail(request, response);
			break;
			case "LOAD_ORDER_DETAIL":
				loadOrderDetail(request, response);
				break;
			case "DELETE_ORDER_DETAIL":
				deleteOrderDetail(request, response);
				break;
			default:
				listOrders(request, response);
			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read Order id from form data
		String theOrderId = request.getParameter("orderId");
		// delete Order from database
		orderDAO.deleteOrderByID(Long.parseLong(theOrderId));
		listOrders(request, response);
	}

	private void deleteOrderDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read Order id from form data
		String theOrderId = request.getParameter("orderDetailId");
		// delete Order from database
		orderdetailDAO.deleteOrderDetailByID(Long.parseLong(theOrderId));
		listOrders(request, response);
	}

	private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("orderId"));
		String stringDate = request.getParameter("orderDateString");
		Date date = sdf.parse(stringDate);
		String customerId = request.getParameter("customerName");
		Customer customer = customerDAO.findCustomerById(Long.parseLong(customerId));
		Order order = new Order(id, date, customer);
		orderDAO.modifyOrder(order);
		listOrders(request, response);

	}

	private void updateOrderDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String theOrderDetailId = request.getParameter("orderDetailId");
		// get Order from database (db util)
		@SuppressWarnings("unused")
		OrderDetail theOrderDetail = orderdetailDAO.findOrderDetailById(Long.parseLong(theOrderDetailId));
		String theOrderId = request.getParameter("orderId");
		// get Order from database (db util)
		Order theOrder = orderDAO.findOrderById(Long.parseLong(theOrderId));
		String quantity = request.getParameter("quantityString");
		String bookId = request.getParameter("bookName");
		Book book = bookDAO.findBookById(Long.parseLong(bookId));
		OrderDetail orderDetail = new OrderDetail(Long.parseLong(theOrderDetailId), theOrder, book,
				Long.parseLong(quantity));
		orderdetailDAO.modifyOrderDetail(orderDetail);

		// place Order in the request attribute
		request.setAttribute("the_Order", theOrder);
		ArrayList<Customer> customer_List = customerDAO.findAllCustomer();
		ArrayList<OrderDetail> orderDetailList = orderdetailDAO.findOrderDetailByOrderId(Long.parseLong(theOrderId));
		request.setAttribute("ORDER_DETAIL_LIST", orderDetailList);
		request.setAttribute("customer_List", customer_List);
		// send to jsp page: update-Order-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/order/update-order-form.jsp");
		dispatcher.forward(request, response);

	}
	private void addOrderDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orderId = request.getParameter("orderId");
		// get Order from database (db util)
	
		Order theOrder = orderDAO.findOrderById(Long.parseLong(orderId));
		String quantity = request.getParameter("quantityString");
		String bookId = request.getParameter("bookName");
		Book book = bookDAO.findBookById(Long.parseLong(bookId));
		OrderDetail orderDetail = new OrderDetail(orderdetailDAO.generateId(), theOrder, book,
				Long.parseLong(quantity));
		orderdetailDAO.addNewOrderDetail(orderDetail);
		// place Order in the request attribute
		request.setAttribute("order_ID", orderId);
		ArrayList<Customer> customer_List = customerDAO.findAllCustomer();
		ArrayList<OrderDetail> orderDetailList = orderdetailDAO.findOrderDetailByOrderId(Long.parseLong(orderId));
		request.setAttribute("ORDER_DETAIL_LIST", orderDetailList);
		request.setAttribute("customer_List", customer_List);
		// send to jsp page: update-Order-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/order/update-order-form.jsp");
		dispatcher.forward(request, response);
	}
	private void newOrderDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Order id from form data
		String theOrderId = request.getParameter("orderId");
		// get Order from database (db util)

		// place Order in the request attribute
		request.setAttribute("order_ID", theOrderId);
		ArrayList<Book> book_List = bookDAO.findBook();

		request.setAttribute("book_List", book_List);
		// send to jsp page: update-Order-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/order/add-order-detail-form.jsp");
		dispatcher.forward(request, response);
	}
	private void loadOrderDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Order id from form data
		String theOrderId = request.getParameter("orderDetailId");
		// get Order from database (db util)
		OrderDetail theOrderDetail = orderdetailDAO.findOrderDetailById(Long.parseLong(theOrderId));

		// place Order in the request attribute
		request.setAttribute("the_Order_Detail", theOrderDetail);
		ArrayList<Book> book_List = bookDAO.findBook();

		request.setAttribute("book_List", book_List);
		// send to jsp page: update-Order-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/order/update-order-detail-form.jsp");
		dispatcher.forward(request, response);
	}

	private void newOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Customer> customer_List = customerDAO.findAllCustomer();
		request.setAttribute("customer_List", customer_List);
		// send to jsp page: update-Order-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/order/add-order-form.jsp");
		dispatcher.forward(request, response);
	}

	private void loadOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Order id from form data
		String theOrderId = request.getParameter("orderId");
		// get Order from database (db util)
		Order theOrder = orderDAO.findOrderById(Long.parseLong(theOrderId));

		// place Order in the request attribute
		request.setAttribute("the_Order", theOrder);
		ArrayList<Customer> customer_List = customerDAO.findAllCustomer();
		ArrayList<OrderDetail> orderDetailList = orderdetailDAO.findOrderDetailByOrderId(Long.parseLong(theOrderId));
		request.setAttribute("ORDER_DETAIL_LIST", orderDetailList);
		request.setAttribute("customer_List", customer_List);
		// send to jsp page: update-Order-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/order/update-order-form.jsp");
		dispatcher.forward(request, response);
	}

	private void addOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		long id = orderDAO.generateId();
		String stringDate = request.getParameter("orderDateString");
		Date date = sdf.parse(stringDate);
		String customerId = request.getParameter("customerId");
		Customer customer = customerDAO.findCustomerById(Long.parseLong(customerId));
		// create a new Order object
		Order theOrder = new Order(id, date, customer);
		orderDAO.addNewOrder(theOrder);

		// send them back to the "list Orders" page
		listOrders(request, response);
	}

	private void listOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get Orders from db util
		List<Order> Orders = orderDAO.findAllOrder();
		// add Orders to the request
		request.setAttribute("ORDER_LIST", Orders);
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/order/list-order.jsp");
		dispatcher.forward(request, response);
	}

}
