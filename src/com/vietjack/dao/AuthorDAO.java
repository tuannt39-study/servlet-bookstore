package com.vietjack.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vietjack.core.Author;

public class AuthorDAO {
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

	public long calculateRevenueOfAuthor(long authorId) throws SQLException {
		long revenue = 0l;
		String query = "SELECT bs_book.PRICE, bs_order_detail.quantity FROM bs_book, bs_author,bs_book_author,bs_order_detail WHERE  bs_book.ID = bs_book_author.book_id and bs_book_author.author_id = "
				+ authorId + " and bs_author.id = " + authorId + " and bs_book.id = bs_order_detail.book_id";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		while (resultSet.next()) {
			revenue += resultSet.getLong("bs_book.PRICE") * resultSet.getLong("bs_order_detail.quantity");
		}
		return revenue;
	}

	public ArrayList<Author> findAllAuthor() throws SQLException {
		String query = "select * from bs_author ";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Author> authorList = new ArrayList<Author>();

		while (resultSet.next()) {
			Author author = new Author();
			author.setID(resultSet.getLong("id"));
			author.setName(resultSet.getString("name"));
			Long revenue = calculateRevenueOfAuthor(resultSet.getLong("id"));
			author.setRevenue(revenue);
			author.setDob(resultSet.getDate("dob"));
			authorList.add(author);
		}

		return authorList;
	}

	public ArrayList<Author> findAuthorOfBook(long bookId) throws SQLException {
		String query = "SELECT bs_author.id, bs_author.NAME, bs_author.dob FROM bs_book, bs_author,bs_book_author WHERE  "
				+ bookId + " = bs_book_author.book_id and bs_author.id = bs_book_author.author_id";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Author> authorList = new ArrayList<Author>();

		if (resultSet.next()) {
			Author author = new Author();
			author.setID(resultSet.getLong("bs_author.id"));
			author.setName(resultSet.getString("bs_author.name"));
			author.setDob(resultSet.getDate("bs_author.dob"));
			authorList.add(author);
		}
		return authorList;
	}

	public boolean addNewAuthor(Author author) throws SQLException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String query = "insert into bs_author(id,name,dob) values (" + author.getID() + ",'" + author.getName() + "','"
				+ sdf.format(author.getDob()) + "')";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		// return (n!=0);
		if (n != 0)
			return true;
		return false;
	}

	public boolean modifyAuthor(Author author) throws SQLException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String query = "update bs_author set name='" + author.getName() + "' , dob='" + sdf.format(author.getDob())
				+ "' where id=" + author.getID();
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		// return (n!=0);
		if (n != 0)
			return true;
		return false;
	}

	public boolean deleteAuthorByName(String name) throws SQLException {
		String query = "delete from bs_author where name = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, name);
		int n = stmt.executeUpdate();
		// return (n!=0);
		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}
	public boolean deleteAuthorById(long id) throws SQLException {
		String query = "delete from bs_author where id = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, id);
		int n = stmt.executeUpdate();
		// return (n!=0);
		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}

	public Author findAuthorByName(String name) throws SQLException {
		String query = "select * from bs_author where name='" + name + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Author author = new Author();
			author.setID(resultSet.getLong("id"));
			author.setName(resultSet.getString("name"));
			author.setDob(resultSet.getDate("dob"));
			return author;
		} else
			return null;

	}

	public Author findAuthorById(long id) throws SQLException {
		String query = "select * from bs_author where id='" + id + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Author author = new Author();
			author.setID(resultSet.getLong("id"));
			author.setName(resultSet.getString("name"));
			author.setDob(resultSet.getDate("dob"));
			return author;
		} else
			return null;

	}

	public long generateId() throws SQLException {
		String query = "select max(id) as maxId from bs_author ";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		if (resultSet.next()) {
			return resultSet.getLong("maxId") + 1;
		} else {
			return 0;
		}
	}

}
