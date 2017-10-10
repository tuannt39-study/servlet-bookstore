package com.vietjack.service;

import java.util.Scanner;

public class MenuService {
	private static AuthorService authorService=new AuthorService();
	private static CategoryService categoryService=new CategoryService();
	private static BookService bookService=new BookService();
	private static CustomerService customerService = new CustomerService();
	private static OrderService orderService = new OrderService();
	private static OrderDetailService orderDetailService = new OrderDetailService();
	private static BookStoreService bookStoreService = new BookStoreService();
	private static Scanner scanner = new Scanner(System.in);

	public static void categoryMenus(){
		categoryService.printCategoryMenu();
		boolean flag = true;
		while (flag) {
			System.out.println("Welcome category menu");
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				CategoryService.addNewRealCategory();
				break;
			case 2:
				CategoryService.findCategoryByName();
				break;
			case 3:
				CategoryService.displayCategory();
				break;
			case 4:
				CategoryService.updateCategory();
				break;
			case 5:
				CategoryService.deleteCategoryByName();
				break;
			case 6:
				System.out.println("Return main menu");
				flag = false;
				break;
			}

		}
	}
	
	public static void bookMenus(){
		bookService.printBookMenu();
		boolean flag = true;
		while (flag) {
			System.out.println("Welcome bookService menu");
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				BookService.addNewRealBook();
				break;
			case 2:
				BookService.findBookByName();
				break;
			case 3:
				BookService.displayBook();
				break;
			case 4:
				BookService.updateBook();
				break;
			case 5:
				BookService.deleteBookByName();
				break;
			case 6:
				System.out.println("Return main menu");
				flag = false;
				break;
			}

		}
	}
	public static void authorMenus(){
		authorService.printAuthorMenu();

		boolean flag = true;
		while (flag) {
			System.out.println("Welcome author menu");
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				AuthorService.addNewRealAuthor();
				break;
			case 2:
				AuthorService.findAuthorByName();
				break;
			case 3:
				AuthorService.displayAuthor();
				break;
			case 4:
				AuthorService.updateAuthor();
				break;
			case 5:
				AuthorService.deleteAuthorByName();
				break;
			case 6:
				System.out.println("Return main menu");
				flag = false;
				break;
			}

		}
	}
	
	public static void customerMenus(){
		customerService.printCustomerMenu();
		boolean flag = true;
		while (flag) {
			System.out.println("Welcome customer menu");
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				CustomerService.addNewRealCustomer();
				break;
			case 2:
				CustomerService.findCustomerByName();
				break;
			case 3:
				CustomerService.displayCustomer();
				break;
			case 4:
				CustomerService.updateCustomer();
				break;
			case 5:
				CustomerService.deleteCustomerByName();
				break;
			case 6:
				System.out.println("Return orders menu");
				flag = false;
				break;
			}

		}
	}
	
	public static void orderMenus(){
		orderService.printOrderMenu();
		boolean flag = true;
		while (flag) {
			System.out.println("Welcome order menu");
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				OrderService.addNewRealOrder();
				break;
			case 2:
				OrderService.findOrderById();
				break;
			case 3:
				OrderService.displayOrder();
				break;
			case 4:
				OrderService.updateOrder();
				break;
			case 5:
				OrderService.deleteOrderById();
				break;
			case 6:
				System.out.println("Return orders menu");
				flag = false;
				break;
			}

		}
	}
	
	public static void orderDetailMenus(){
		orderDetailService.printOrderDetailMenu();
		boolean flag = true;
		while (flag) {
			System.out.println("Welcome order detail menu");
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				OrderDetailService.addNewRealOrderDetail();
				break;
			case 2:
				OrderDetailService.findOrderDetailById();
				break;
			case 3:
				OrderDetailService.displayOrderDetail();
				break;
			case 4:
				OrderDetailService.updateOrderDetail();
				break;
			case 5:
				OrderDetailService.deleteOrderDetailById();
				break;
			case 6:
				System.out.println("Return orders menu");
				flag = false;
				break;
			}

		}
	}
	
	public static void ordersMenus() {
		System.out.println("1. To Manage customers");
		System.out.println("2. To Manage order");
		System.out.println("3. To Manage order detail");
		boolean flag = true;
		while (flag) {
			System.out.println("Welcome orders menu");
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				customerMenus();
				break;
			case 2:
				orderMenus();
				break;
			case 3:
				orderDetailMenus();
				break;
			case 4:
				System.out.println("Return main menu");
				flag = false;
				break;
			}

		}
	}
	
	public static void bookStoreMenus() {
		bookStoreService.printBookStoreMenu();
		boolean flag = true;
		while (flag) {
			System.out.println("Welcome book store menu");
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				BookStoreService.topAuthorByRevenue();
				break;
			case 2:
				BookStoreService.topCategoryByRevenue();
				break;
			case 3:
				BookStoreService.dayRevenue();
				break;
			case 4:
				BookStoreService.timeRevenue();
				break;
			case 5:
				System.out.println("Return main menu");
				flag = false;
				break;
			}

		}
	}
	
	public void printMenuMenu(){
		System.out.println("The bookstore management program");
		System.out.println("1.To Manage authors");
		System.out.println("2.To Manage categorys");
		System.out.println("3.To Manage books");
		System.out.println("4.To Manage orders");
		System.out.println("5.To Manage sales");
		System.out.println("6.System end");
	}
}
