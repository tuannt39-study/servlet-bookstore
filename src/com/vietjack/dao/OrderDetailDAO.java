package com.vietjack.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vietjack.core.OrderDetail;

public class OrderDetailDAO {
	private Connection conn;
	private static BookDAO bookDAO = new BookDAO();
	private static OrderDAO orderDAO = new OrderDAO();

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

	public boolean addNewOrderDetail(OrderDetail orderDetail) throws SQLException {
		String query = "insert into bs_order_detail(id, order_id, quantity, book_id) values (" + orderDetail.getId()
				+ ", " + orderDetail.getOrder().getId() + ", " + orderDetail.getQuantity() + ", "
				+ orderDetail.getBook().getID() + ")";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		if (n != 0)
			return true;
		return false;
	}

	public ArrayList<OrderDetail> findAllOrderDetail() throws SQLException {
		String query = "select * from bs_order_detail ";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

		while (resultSet.next()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setId(resultSet.getLong("id"));
			orderDetail.setQuantity(resultSet.getLong("quantity"));
			Long bookId = resultSet.getLong("book_id");
			orderDetail.setBook(bookDAO.findBookById(bookId));
			Long orderId = resultSet.getLong("order_id");
			orderDetail.setOrder(orderDAO.findOrderById(orderId));
			orderDetailList.add(orderDetail);
		}
		return orderDetailList;
	}
	public  ArrayList<OrderDetail> findOrderDetailByOrderId(long id) throws SQLException {
		String query = "select * from bs_order_detail where order_id='" + id + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		while (resultSet.next()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setId(resultSet.getLong("id"));
			orderDetail.setQuantity(resultSet.getLong("quantity"));
			Long bookId = resultSet.getLong("book_id");
			orderDetail.setBook(bookDAO.findBookById(bookId));
			Long orderId = resultSet.getLong("order_id");
			orderDetail.setOrder(orderDAO.findOrderById(orderId));
			orderDetailList.add(orderDetail);
		}
		return orderDetailList;

	}
	public OrderDetail findOrderDetailById(long id) throws SQLException {
		String query = "select * from bs_order_detail where id='" + id + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setId(resultSet.getLong("id"));
			orderDetail.setQuantity(resultSet.getLong("quantity"));
			Long orderId = resultSet.getLong("order_id");
			orderDetail.setOrder(orderDAO.findOrderById(orderId));
			Long bookId = resultSet.getLong("book_id");
			orderDetail.setBook(bookDAO.findBookById(bookId));

			return orderDetail;
		} else
			return null;

	}

	public boolean modifyOrderDetail(OrderDetail orderDetail) throws SQLException {
		String query = "update bs_order_detail set order_id='" + orderDetail.getOrder().getId() + "', book_id='"
				+ orderDetail.getBook().getID() + "', quantity='" + orderDetail.getQuantity() + "' where id="
				+ orderDetail.getId();
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		// return (n!=0);
		if (n != 0)
			return true;
		return false;
	}

	public boolean deleteOrderDetailByID(Long orderDetailId) throws SQLException {
		String query = "delete from bs_order_detail where id = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, orderDetailId);
		int n = stmt.executeUpdate();
		// return (n!=0);
		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}

	public long generateId() throws SQLException {
		String query = "select max(id) as maxId from bs_order_detail";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			return resultSet.getLong("maxId") + 1;
		} else {
			return 0;

		}
	}

}
