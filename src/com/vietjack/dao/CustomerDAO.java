package com.vietjack.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vietjack.core.Customer;

public class CustomerDAO {

	private Connection conn;

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

	public ArrayList<Customer> findAllCustomer() throws SQLException {
		String query = "select * from bs_customer ";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Customer> customerList = new ArrayList<Customer>();

		while (resultSet.next()) {
			Customer customer = new Customer();
			customer.setId(resultSet.getLong("id"));
			customer.setName(resultSet.getString("name"));
			customer.setDob(resultSet.getDate("dob"));
			customer.setPhone(resultSet.getString("phone"));
			customerList.add(customer);
		}
		return customerList;
	}

	public boolean addNewCustomer(Customer customer) throws SQLException {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String query = "insert into bs_customer(id,name,phone,dob) values (" + customer.getId() + ",'" + customer.getName()
				+ "','"+ customer.getPhone() +"','" + sdf.format(customer.getDob()) + "')";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		// return (n!=0);
		if (n != 0)
			return true;
		return false;
	}

	public boolean modifyCustomer(Customer customer) throws SQLException {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String query = "update bs_customer set name='" + customer.getName() + "' , dob='"
				+ sdf.format(customer.getDob()) + "' , phone ='"+customer.getPhone()+"' where id=" + customer.getId();
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		// return (n!=0);
		if (n != 0)
			return true;
		return false;
	}

	public boolean deleteCustomerByName(String name) throws SQLException {
		String query = "delete from bs_customer where name = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, name);
		int n = stmt.executeUpdate();
		// return (n!=0);
		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}
	public boolean deleteCustomerById(Long id) throws SQLException {
		String query = "delete from bs_customer where id = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, id);
		int n = stmt.executeUpdate();
		// return (n!=0);
		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}

	public Customer findCustomerByName(String name) throws SQLException {
		String query = "select * from bs_customer where name='" + name + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Customer customer = new Customer();
			customer.setId(resultSet.getLong("id"));
			customer.setName(resultSet.getString("name"));
			customer.setDob(resultSet.getDate("dob"));
			customer.setPhone(resultSet.getString("phone"));
			return customer;
		} else
			return null;

	}

	public Customer findCustomerById(long id) throws SQLException {
		String query = "select * from bs_customer where id='" + id + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Customer customer = new Customer();
			customer.setId(resultSet.getLong("id"));
			customer.setName(resultSet.getString("name"));
			customer.setDob(resultSet.getDate("dob"));
			customer.setPhone(resultSet.getString("phone"));
			return customer;
		} else
			return null;

	}

	public long generateId() throws SQLException {
		String query = "select max(id) as maxId from bs_customer ";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		if (resultSet.next()) {
			return resultSet.getLong("maxId") + 1;
		} else {
			return 0;
		}
	}
}
