package com.vietjack.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vietjack.core.Author;
import com.vietjack.core.Book;

public class BookAuthorDAO {
	private Connection conn;
	private static AuthorDAO authorDAO = new AuthorDAO();

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

	public boolean addNewBookAuthor(Author author, Book book) throws SQLException {

		String query = "insert into bs_book_author(id,book_id,author_id) values (" + generateId() + ",'" + book.getID()
				+ "','" + author.getID() + "')";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		// return (n!=0);
		if (n != 0)
			return true;
		return false;
	}

	public boolean modifyBookAuthor(Author author, Book book) throws SQLException {

		String query = "update bs_book_author set author_id='"+author.getID()+"', book_id='"+book.getID()+"'";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		// return (n!=0);
		if (n != 0)
			return true;
		return false;
	}

	public ArrayList<Author> findAuthors4EachBook(Book book) throws SQLException {
		String query = "select author_id from bs_book_author where book_id =" + book.getID();
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Author> authorList = new ArrayList<Author>();

		if (resultSet.next()) {
			long authorId = resultSet.getLong("author_id");
			Author author = authorDAO.findAuthorById(authorId);
			authorList.add(author);
		}
		return authorList;
	}

	public long generateId() throws SQLException {
		String query = "select max(id) as maxId from bs_book_author ";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		if (resultSet.next()) {
			return resultSet.getLong("maxId") + 1;
		} else {
			return 0;
		}
	}
}
