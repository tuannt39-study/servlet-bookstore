package com.vietjack.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.vietjack.core.Book;
import com.vietjack.core.Order;
import com.vietjack.core.OrderDetail;
import com.vietjack.dao.BookDAO;
import com.vietjack.dao.OrderDAO;
import com.vietjack.dao.OrderDetailDAO;

public class OrderDetailService {
	private static Scanner scanner = new Scanner(System.in);
	private static OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
	private static OrderDAO orderDAO = new OrderDAO();
	private static BookDAO bookDAO = new BookDAO();

	public static void addNewRealOrderDetail() {
		System.out.println("Add new Order Detail function");
		try {
			long id = orderDetailDAO.generateId();
			
			System.out.println("Input quantity");
			Long quantity = scanner.nextLong();

			System.out.println("Choose order ID:");
			ArrayList<Order> orderList = orderDAO.findAllOrder();
			for (Order order : orderList) {
				System.out.println(order);
			}

			Long orderId = scanner.nextLong();
			Order selectedOrder = orderDAO.findOrderById(orderId);

			System.out.println("Choose book ID:");
			ArrayList<Book> bookList = bookDAO.findBook();
			for (Book book : bookList) {
				System.out.println(book);
			}
			Long bookId = scanner.nextLong();
			Book selectedBook = bookDAO.findBookById(bookId);

			OrderDetail orderDetail = new OrderDetail(id, selectedOrder, selectedBook, quantity);
			orderDetailDAO.addNewOrderDetail(orderDetail);

		} catch (Exception e) {
			System.out.println("There is an error when adding new Order Detail");
			e.printStackTrace();
		}
	}

	public static void displayOrderDetail() {
		try {
			ArrayList<OrderDetail> orderDetailList = orderDetailDAO.findAllOrderDetail();
			for (OrderDetail orderDetail : orderDetailList) {
				System.out.println(orderDetail);
			}
		} catch (Exception e) {
			System.out.println("There is an error when display all Order");
			e.printStackTrace();
		}
	}

	public static void updateOrderDetail() {
		try {
			System.out.println("Update Order detail by id function");
			System.out.println("Input id");
			Long id = scanner.nextLong();
			OrderDetail orderDetail = orderDetailDAO.findOrderDetailById(id);
			if (orderDetail == null) {
				System.out.println("Couldn't find the Order detail id=" + id);
			} else {
				System.out.println("Found Order Detail :" + id);
				System.out.println("With info:" + orderDetail);
				System.out.println("Input new quantity");
				Long quantity = scanner.nextLong();
				System.out.println("Choose order ID:");
				ArrayList<Order> orderList = orderDAO.findAllOrder();
				for (Order order : orderList) {
					System.out.println(order);
				}

				Long orderId = scanner.nextLong();
				Order selectedOrder = orderDAO.findOrderById(orderId);

				System.out.println("Choose book ID:");
				ArrayList<Book> bookList = bookDAO.findBook();
				for (Book book : bookList) {
					System.out.println(book);
				}

				Long bookId = scanner.nextLong();
				Book selectedBook = bookDAO.findBookById(bookId);

				OrderDetail newOrderDetail = new OrderDetail(id, selectedOrder, selectedBook, quantity);

				orderDetailDAO.modifyOrderDetail(newOrderDetail);

			}

		} catch (Exception e) {
			System.out.println("There is an error when updating a Customer");
			e.printStackTrace();
		}
	}

	public static void deleteOrderDetailById() {
		System.out.println("Delete Order detail by id function");
		System.out.println("Input the id");
		Long orderDetailId = scanner.nextLong();
		try {
			orderDetailDAO.deleteOrderDetailByID(orderDetailId);
		} catch (Exception e) {
			System.out.println("There is an error when deleting a Order detail");
			e.printStackTrace();
		}
	}

	public static void findOrderDetailById() {
		System.out.println("Find Order detail by id function");
		System.out.println("Input the id");
		Long orderDetailId = scanner.nextLong();
		OrderDetail orderDetail = null;
		try {
			orderDetail = orderDetailDAO.findOrderDetailById(orderDetailId);
		} catch (Exception e) {
			System.out.println("There is an error when finding a Order detail");
			e.printStackTrace();
		}
		if (orderDetail != null) {
			System.out.println(orderDetail);
		} else {
			System.out.println("Couldn't find the Order with id: " + orderDetailId);
		}
	}

	public void printOrderDetailMenu() {
		System.out.println("The Order detail management program");
		System.out.println("1. Add new Order detail");
		System.out.println("2. Find Order detail by id");
		System.out.println("3. Display Order detail");
		System.out.println("4. Update Order detail");
		System.out.println("5. Delete Order detail by id");
		System.out.println("6. Exit");
	}
}
