package com.vietjack.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vietjack.core.Author;
import com.vietjack.core.Book;

public class BookDAO {
	private Connection conn;
	private static CategoryDAO categoryDAO = new CategoryDAO();
	private static AuthorDAO authorDAO = new AuthorDAO();
	private static BookAuthorDAO bookAuthorDAO = new BookAuthorDAO();

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


	
	public long caculateSoldNumber(long bookId) throws SQLException{
		long soldNum=0l;
		String query = "SELECT bs_order_detail.quantity FROM bs_book, bs_order_detail WHERE  "+bookId+" = bs_order_detail.book_id";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		
		if (resultSet.next()) {
			soldNum+= resultSet.getLong("bs_order_detail.quantity");
		}
		return soldNum;
	}
	

	public ArrayList<Book> findBook() throws SQLException {
		String query = "SELECT * FROM bs_book";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Book> bookList = new ArrayList<Book>();

		while (resultSet.next()) {
			Book book = new Book();
			book.setID(resultSet.getLong("id"));
			book.setName(resultSet.getString("name"));
			book.setPrice(resultSet.getLong("price"));
			book.setSoldNumber(caculateSoldNumber(resultSet.getLong("id")));
			ArrayList<Author>authorList=bookAuthorDAO.findAuthors4EachBook(book);
			book.setAuthor(authorList);
			Long category=resultSet.getLong("category_id");
			book.setCategory(categoryDAO.findCategoryById(category));
			bookList.add(book);
		}
		return bookList;
	}
	
	public boolean addNewBook(Book book) throws SQLException {
		String query = "insert into bs_book(id,name,price,category_id) values (" + book.getID() + ",'" + book.getName()
				+ "','" + book.getPrice() + "','" + book.getCategory().getID()+ "')";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		if (n != 0)
			return true;
		return false;
	}

	public boolean modifyBook(Book book) throws SQLException {
		String query = "update bs_book set name='" + book.getName() + "' , price='" + book.getPrice() + "' , category_id='"+ book.getCategory().getID()
				+ "' where id=" + book.getID();
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		if (n != 0)
			return true;
		return false;
	}

	public boolean deleteBookByName(String name) throws SQLException {
		String query = "delete from bs_book where name = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, name);
		int n = stmt.executeUpdate();
		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}
	public boolean deleteBookById(Long id) throws SQLException {
		String query = "delete from bs_book where id = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, id);
		int n = stmt.executeUpdate();
		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}

	public Book findBookByName(String name) throws SQLException {
		String query = "select * from bs_book where name='" + name + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Book book = new Book();
			book.setID(resultSet.getLong("id"));
			book.setName(resultSet.getString("name"));
			return book;
		} else
			return null;

	}

	public Book findBookById(long id) throws SQLException {
		String query = "select * from bs_book where id='" + id + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Book book = new Book();
			book.setID(resultSet.getLong("id"));
			book.setName(resultSet.getString("name"));
			book.setPrice(resultSet.getDouble("price"));
			book.setCategory(categoryDAO.findCategoryOfBook(resultSet.getLong("category_id")));
			ArrayList<Author>authorList= authorDAO.findAuthorOfBook(id);
			book.setAuthor(authorList);
			return book;
		} else
			return null;

	}

	public long generateId() throws SQLException {
		String query = "select max(id) as maxId from bs_book";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		if (resultSet.next()) {
			return resultSet.getLong("maxId") + 1;
		} else {
			return 0;
			
		}
	}
}
