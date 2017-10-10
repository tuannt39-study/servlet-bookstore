package com.vietjack.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vietjack.core.Book;
import com.vietjack.core.Category;
import com.vietjack.dao.BookDAO;
import com.vietjack.dao.CategoryDAO;

/**
 * Servlet implementation class BookControllerServlet
 */
@WebServlet("/BookControllerServlet")
public class BookControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static CategoryDAO categoryDAO = new CategoryDAO();
	public static BookDAO bookDAO = new BookDAO();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing Books
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "NEW":
				newBook(request, response);
				break;
			case "LIST":
				listBooks(request, response);
				break;
				
			case "ADD":
				addBook(request, response);
				break;
				
			case "LOAD":
				loadBook(request, response);
				break;
				
			case "UPDATE":
				updateBook(request, response);
				break;
			
			case "DELETE":
				deleteBook(request, response);
				break;
			default:
				listBooks(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	

	private void deleteBook(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read Book id from form data
		String theBookId = request.getParameter("bookId");
		
		// delete Book from database
		bookDAO.deleteBookById(Long.parseLong(theBookId));
		
		// send them back to "list Books" page
		listBooks(request, response);
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read Book info from form data
		int id = Integer.parseInt(request.getParameter("bookId"));
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String categoryId = request.getParameter("categoryName");
		Category category=categoryDAO.findCategoryById(Long.parseLong(categoryId));
		// create a new Book object
		Book theBook = new Book(id, name,Double.parseDouble(price),category);
		
		// perform update on database
		bookDAO.modifyBook(theBook);
		
		// send them back to the "list Books" page
		listBooks(request, response);
		
	}
	private void newBook(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			ArrayList<Category> category_List=categoryDAO.findAllCatergory();
			
			request.setAttribute("category_List", category_List);
			// send to jsp page: update-Book-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/book/add-book-form.jsp");
			dispatcher.forward(request, response);		
		}
	private void loadBook(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// read Book id from form data
		String theBookId = request.getParameter("bookId");
		
		// get Book from database (db util)
		Book theBook = bookDAO.findBookById(Long.parseLong(theBookId));
		
		// place Book in the request attribute
		request.setAttribute("the_Book", theBook);
		ArrayList<Category> category_List=categoryDAO.findAllCatergory();
		
		request.setAttribute("category_List", category_List);
		// send to jsp page: update-Book-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/book/update-book-form.jsp");
		dispatcher.forward(request, response);		
	}

	private void addBook(HttpServletRequest request, HttpServletResponse response) throws Exception {

		long id = bookDAO.generateId();
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String categoryId = request.getParameter("categoryName");
		Category category=categoryDAO.findCategoryById(Long.parseLong(categoryId));
		// create a new Book object
		Book theBook = new Book(id, name,Double.parseDouble(price),category);
		
		// perform update on database
		bookDAO.addNewBook(theBook);
		
		// send them back to the "list Books" page
		listBooks(request, response);
	}

	private void listBooks(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get Books from db util
		List<Book> Books = bookDAO.findBook();
		
		// add Books to the request
		request.setAttribute("BOOK_LIST", Books);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/book/list-book.jsp");
		dispatcher.forward(request, response);
	}

}
