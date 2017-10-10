package com.vietjack.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vietjack.core.Category;

public class CategoryDAO {
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

	public long calculateRevenueOfCategory(long categoryId) throws SQLException {
		long revenue = 0l;
		String query = "SELECT bs_book.PRICE, bs_order_detail.quantity FROM bs_book, bs_order_detail, bs_category WHERE  bs_book.ID = bs_order_detail.book_id and bs_book.CATEGORY_ID = "
				+ categoryId + " and bs_category.id = "+categoryId;
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		while (resultSet.next()) {
			revenue += resultSet.getLong("bs_book.PRICE") * resultSet.getLong("bs_order_detail.quantity");
		}
		return revenue;
	}

	public ArrayList<Category> findAllCatergory() throws SQLException {
		String query = "select * from bs_category ";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Category> categoryList = new ArrayList<Category>();

		while (resultSet.next()) {
			Category category = new Category();
			category.setID(resultSet.getLong("id"));
			category.setName(resultSet.getString("name"));
			category.setRevenue(calculateRevenueOfCategory(resultSet.getLong("id")));
			categoryList.add(category);
		}
		return categoryList;
	}

	public Category findCategoryOfBook(long categoryId) throws SQLException {
		String query = "SELECT bs_category.id, bs_category.name FROM bs_book, bs_category WHERE  " + "bs_category.ID = "
				+ categoryId;
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		Category category = new Category();
		if (resultSet.next()) {
			category.setID(resultSet.getLong("bs_category.id"));
			category.setName(resultSet.getString("bs_category.name"));
			category.setRevenue(calculateRevenueOfCategory(resultSet.getLong("bs_category.id")));
		}
		return category;
	}

	public boolean addNewCategory(Category category) throws SQLException {
		String query = "insert into bs_category(id,name) values (" + category.getID() + ",'" + category.getName()
				+ "')";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);

		if (n != 0)
			return true;
		return false;
	}

	public boolean modifyCategory(Category category) throws SQLException {
		String query = "update bs_category set name='" + category.getName() + "' where id=" + category.getID();
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);

		if (n != 0)
			return true;
		return false;
	}
	public boolean deleteCategoryById(Long categoryId) throws SQLException {
		String query = "delete from bs_category where id = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, categoryId);
		int n = stmt.executeUpdate();

		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}
	public boolean deleteCategoryByName(String name) throws SQLException {
		String query = "delete from bs_category where name = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, name);
		int n = stmt.executeUpdate();

		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}

	public Category findCategoryByName(String name) throws SQLException {
		String query = "select * from bs_category where name='" + name + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Category category = new Category();
			category.setID(resultSet.getLong("id"));
			category.setName(resultSet.getString("name"));
			return category;
		} else
			return null;

	}

	public Category findCategoryById(long id) throws SQLException {
		String query = "select * from bs_category where id='" + id + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Category category = new Category();
			category.setID(resultSet.getLong("id"));
			category.setName(resultSet.getString("name"));
			return category;
		} else
			return null;

	}

	public long generateId() throws SQLException {
		String query = "select max(id) as maxId from bs_category ";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			return resultSet.getLong("maxId") + 1;
		} else {
			return 0;
		}
	}
}
