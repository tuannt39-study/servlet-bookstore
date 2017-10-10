package com.vietjack.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.vietjack.core.Customer;
import com.vietjack.core.Order;
import com.vietjack.dao.CustomerDAO;
import com.vietjack.dao.OrderDAO;

public class OrderService {
	private static Scanner scanner = new Scanner(System.in);
	private static OrderDAO orderDAO = new OrderDAO();
	private static CustomerDAO customerDAO = new CustomerDAO();

	public static void addNewRealOrder() {
		System.out.println("Add new Order function");
		try {
			long id = orderDAO.generateId();
			System.out.println("Input order date");
			String date = scanner.nextLine();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date orderDate = sdf.parse(date);
			System.out.println("Choose customer ID:");
			ArrayList<Customer> customerList = customerDAO.findAllCustomer();
			for (Customer customer : customerList) {
				System.out.println(customer);
			}
			String customerId = scanner.nextLine();
			Long customerIdLong = Long.parseLong(customerId);
			Customer selectedCustomer = customerDAO.findCustomerById(customerIdLong);
			Order order = new Order(id, orderDate, selectedCustomer);
			orderDAO.addNewOrder(order);
		} catch (Exception e) {
			System.out.println("There is an error when adding new Customer");
			e.printStackTrace();
		}
	}

	public static void findOrderById() {
		System.out.println("Find Order by id function");
		System.out.println("Input the id");
		Long orderId = scanner.nextLong();
		Order order = null;
		try {
			order = orderDAO.findOrderById(orderId);
		} catch (Exception e) {
			System.out.println("There is an error when finding a Order");
			e.printStackTrace();
		}
		if (order != null) {
			System.out.println(order);
		} else {
			System.out.println("Couldn't find the Order with id: " + orderId);
		}
	}

	public static void displayOrder() {
		try {
			ArrayList<Order> orderList = orderDAO.findAllOrder();
			for (Order order : orderList) {
				System.out.println(order);
			}
		} catch (Exception e) {
			System.out.println("There is an error when display all Order");
			e.printStackTrace();
		}
	}

	public static void updateOrder() {
		try {
			System.out.println("Update Order by id function");
			System.out.println("Input id");
			String id = scanner.nextLine();
			Order order = orderDAO.findOrderById(Long.parseLong(id));
			if (order == null) {
				System.out.println("Couldn't find the Order id=" + id);
			} else {
				System.out.println("Found Order :" + id);
				System.out.println("With info:" + order);
				System.out.println("Input new order date");
				String date = scanner.nextLine();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date orderDate = sdf.parse(date);
				System.out.println("Choose customer ID:");
				ArrayList<Customer> customerList = customerDAO.findAllCustomer();
				for (Customer customer : customerList) {
					System.out.println(customer);
				}
				String customerId = scanner.nextLine();
				Long customerIdLong = Long.parseLong(customerId);
				Customer selectedCustomer = customerDAO.findCustomerById(customerIdLong);
				Order newOrder = new Order(Long.parseLong(id), orderDate, selectedCustomer);

				orderDAO.modifyOrder(newOrder);

			}

		} catch (Exception e) {
			System.out.println("There is an error when updating a Customer");
			e.printStackTrace();
		}
	}

	public static void deleteOrderById() {
		System.out.println("Delete Order by id function");
		System.out.println("Input the id");
		Long orderId = scanner.nextLong();
		try {
			orderDAO.deleteOrderByID(orderId);
		} catch (Exception e) {
			System.out.println("There is an error when deleting a Order");
			e.printStackTrace();
		}
	}

	public void printOrderMenu() {
		System.out.println("The Order management program");
		System.out.println("1. Add new Order");
		System.out.println("2. Find Order by id");
		System.out.println("3. Display Order");
		System.out.println("4. Update Order");
		System.out.println("5. Delete Customer by id");
		System.out.println("6. Exit");
	}
}
