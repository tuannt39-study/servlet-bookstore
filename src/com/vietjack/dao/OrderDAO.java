package com.vietjack.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.vietjack.core.Order;

public class OrderDAO {
	private Connection conn;
	private static CustomerDAO customerDAO = new CustomerDAO();

	public Connection getConnection() throws SQLException {
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "123456");
			return conn;
		}
		return conn;
	}

	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean addNewOrder(Order order) throws SQLException {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String query = "insert into bs_order(id, customer_id, order_date) values (" + order.getId() + ", "
				+ order.getCustomer().getId() + ",'" + sdf.format(order.getOrderDate()) + "')";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		// return (n!=0);
		if (n != 0)
			return true;
		return false;
	}

	public ArrayList<Order> findAllOrder() throws SQLException {
		String query = "select * from bs_order ";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Order> orderList = new ArrayList<Order>();

		while (resultSet.next()) {
			Order order = new Order();
			order.setId(resultSet.getLong("id"));
			Long customerId = resultSet.getLong("customer_id");
			order.setCustomer(customerDAO.findCustomerById(customerId));
			order.setOrderDate(resultSet.getDate("order_date"));
			orderList.add(order);
		}
		return orderList;
	}

	public Order findOrderById(long id) throws SQLException {
		String query = "select * from bs_order where id='" + id + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Order order = new Order();
			order.setId(resultSet.getLong("id"));
			Long customerId = resultSet.getLong("customer_id");
			order.setCustomer(customerDAO.findCustomerById(customerId));
			order.setOrderDate(resultSet.getDate("order_date"));
			return order;
		} else
			return null;

	}

	public boolean modifyOrder(Order order) throws SQLException {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String query = "update bs_order set customer_id='" + order.getCustomer().getId() + "', order_date='"
				+ sdf.format(order.getOrderDate()) + "' where id=" + order.getId();
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		// return (n!=0);
		if (n != 0)
			return true;
		return false;
	}

	public boolean deleteOrderByID(Long orderId) throws SQLException {
		String query = "delete from bs_order where id = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, orderId);
		int n = stmt.executeUpdate();
		// return (n!=0);
		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}

	public long calculateRevenueOfDay(Date date) throws SQLException {
		long revenue = 0l;

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String query = "SELECT bs_book.PRICE, bs_order_detail.quantity FROM bs_book, bs_order_detail, bs_order WHERE bs_book.id = bs_order_detail.book_id and bs_order_detail.order_id = bs_order.ID and bs_order.order_date = '"
				+ sdf.format(date) + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		while (resultSet.next()) {
			revenue += resultSet.getLong("bs_book.PRICE") * resultSet.getLong("bs_order_detail.quantity");
		}
		return revenue;
	}

	public long calculateRevenueOfTime(Date date1, Date date2) throws SQLException {
		long revenue = 0l;

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String query = "SELECT bs_book.PRICE, bs_order_detail.quantity FROM bs_book, bs_order_detail, bs_order WHERE bs_book.id = bs_order_detail.book_id and bs_order_detail.order_id = bs_order.ID and bs_order.order_date between '"
				+ sdf.format(date1) + "' and '" + sdf.format(date2) + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		while (resultSet.next()) {
			revenue += resultSet.getLong("bs_book.PRICE") * resultSet.getLong("bs_order_detail.quantity");
		}
		return revenue;
	}

	public long generateId() throws SQLException {
		String query = "select max(id) as maxId from bs_order";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			return resultSet.getLong("maxId") + 1;
		} else {
			return 0;

		}
	}

}
