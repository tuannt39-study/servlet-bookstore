package com.vietjack.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.vietjack.core.Author;
import com.vietjack.core.Book;
import com.vietjack.core.Category;
import com.vietjack.dao.AuthorDAO;
import com.vietjack.dao.BookAuthorDAO;
import com.vietjack.dao.BookDAO;
import com.vietjack.dao.CategoryDAO;

public class BookService {
	private static Scanner scanner = new Scanner(System.in);
	private static BookDAO bookDAO = new BookDAO();
	private static CategoryDAO categoryDAO = new CategoryDAO();
	private static AuthorDAO authorDAO = new AuthorDAO();
	private static BookAuthorDAO bookAuthorDAO = new BookAuthorDAO();

	public static void addNewRealBook() {
		System.out.println("Add new book function");
		try {
			long id = bookDAO.generateId();
			System.out.println("Input name");
			String name = scanner.nextLine();
			System.out.println("Input price");
			String price = scanner.nextLine();
			Long priceLong = Long.parseLong(price);
			System.out.println("Choose category ID:");
			ArrayList<Category> categoryList = categoryDAO.findAllCatergory();
			for (Category category : categoryList) {
				System.out.println(category);
			}
			String categoryId = scanner.nextLine();
			Long categoryIdLong = Long.parseLong(categoryId);
			Category selectedCategory = categoryDAO.findCategoryById(categoryIdLong);
			System.out.println("Choose author List, split by - character:");

			ArrayList<Author> authorList = authorDAO.findAllAuthor();
			for (Author author : authorList) {
				System.out.println(author);
			}
			// 3232-21-3221
			String authorIDList = scanner.nextLine();
			String[] authors = authorIDList.split("-");
			Book book = new Book(id, name, priceLong, selectedCategory);
			bookDAO.addNewBook(book);
			for (String author : authors) {
				Long authorIdLong = Long.parseLong(author);
				Author selectedAuthor = authorDAO.findAuthorById(authorIdLong);
				bookAuthorDAO.addNewBookAuthor(selectedAuthor, book);
			}
		} catch (Exception e) {
			System.out.println("There is an error when adding new book");
			e.printStackTrace();
		} 
	}

	public static void findBookByName() {
		System.out.println("Find book by name function");
		System.out.println("Input the name");
		String name = scanner.nextLine();
		Book book = null;
		try {
			book = bookDAO.findBookByName(name);
		} catch (Exception e) {
			System.out.println("There is an error when finding a book");
			e.printStackTrace();
		}
		if (book != null) {
			System.out.println(book);
		} else {
			System.out.println("Couldn't find the book with name: " + name);
		}
	}

	public static void displayBook() {
		try {
			ArrayList<Book> bookList = bookDAO.findBook();
			for (Book book : bookList) {
				System.out.println(book);
			}
		} catch (Exception e) {
			System.out.println("There is an error when display all books");
			e.printStackTrace();
		}
	}

	public static void updateBook() {
		try {
			System.out.println("Update book by id function");
			System.out.println("Input id");
			String id = scanner.nextLine();
			Book book = bookDAO.findBookById(Long.parseLong(id));
			if (book == null) {
				System.out.println("Couldn't find the book id=" + id);
			} else {
				System.out.println("Found book :" + id);
				System.out.println("With info:" + book);
				System.out.println("Input new name");
				String name = scanner.nextLine();
				System.out.println("Input new price");
				String price = scanner.nextLine();
				Long priceLong = Long.parseLong(price);
				System.out.println("Choose category ID:");
				ArrayList<Category> categoryList = categoryDAO.findAllCatergory();
				for (Category category : categoryList) {
					System.out.println(category);
				}
				String categoryId = scanner.nextLine();
				Long categoryIdLong = Long.parseLong(categoryId);
				Category selectedCategory = categoryDAO.findCategoryById(categoryIdLong);
				System.out.println("Choose author List, split by - character:");

				ArrayList<Author> authorList = authorDAO.findAllAuthor();
				for (Author author : authorList) {
					System.out.println(author);
				}
				// 3232-21-3221
				String authorIDList = scanner.nextLine();
				String[] authors = authorIDList.split("-");
				Book newBook = new Book(Long.parseLong(id), name, priceLong, selectedCategory);
				for (String author : authors) {
					Long authorIdLong = Long.parseLong(author);
					Author selectedNewAuthor = authorDAO.findAuthorById(authorIdLong);
					bookAuthorDAO.modifyBookAuthor(selectedNewAuthor, newBook);
				}
				
				bookDAO.modifyBook(newBook);

			}

		} catch (Exception e) {
			System.out.println("There is an error when updating a book");
			e.printStackTrace();
		}
	}

	public static void deleteBookByName() {
		System.out.println("Delete book by name function");
		System.out.println("Input the name");
		String name = scanner.nextLine();
		try {
			bookDAO.deleteBookByName(name);
		} catch (Exception e) {
			System.out.println("There is an error when deleting a book");
			e.printStackTrace();
		}
	}

	public void printBookMenu() {
		System.out.println("The book management program");
		System.out.println("1. Add new book");
		System.out.println("2. Find book by name");
		System.out.println("3. Display books");
		System.out.println("4. Update book");
		System.out.println("5. Delete book by name");
		System.out.println("6. Exit");
	}
}
