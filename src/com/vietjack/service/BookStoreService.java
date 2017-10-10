package com.vietjack.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import com.vietjack.core.Author;
import com.vietjack.core.Category;
import com.vietjack.dao.AuthorDAO;
import com.vietjack.dao.CategoryDAO;
import com.vietjack.dao.OrderDAO;

public class BookStoreService {
	public static AuthorDAO authorDAO = new AuthorDAO();
	public static CategoryDAO categoryDAO = new CategoryDAO();
	public static OrderDAO orderDAO = new OrderDAO();
	public static Scanner scanner = new Scanner(System.in);

	public static void topAuthorByRevenue() {
		try {
			ArrayList<Author> authorList = authorDAO.findAllAuthor();
			Collections.sort(authorList, Author.compare);
			System.out.println("Top Author by revenue");
			for (Author author : authorList) {
				System.out.println(author + " revenue= " + author.getRevenue() + "VND");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void topCategoryByRevenue() {
		try {
			ArrayList<Category> categoryList = categoryDAO.findAllCatergory();
			Collections.sort(categoryList, Category.compare);
			System.out.println("Top Category by revenue");
			for (Category category : categoryList) {
				System.out.println(category + " revenue= " + category.getRevenue() + "VND");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void dayRevenue() {

		try {
			System.out.println("Input date");
			String d = scanner.nextLine();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date = sdf.parse(d);
			System.out.println("Revenue= " + orderDAO.calculateRevenueOfDay(date));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void timeRevenue() {
		try {
			System.out.println("Input the start date");
			String d1 = scanner.nextLine();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date1 = sdf.parse(d1);
			System.out.println("Input the end date");
			String d2 = scanner.nextLine();
			Date date2 = sdf.parse(d2);

			System.out.println("Revenue= " + orderDAO.calculateRevenueOfTime(date1, date2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void printBookStoreMenu() {
		System.out.println("1.Top Author by revenue");
		System.out.println("2.Top Category by revenue");
		System.out.println("3.Calculate revenue of day");
		System.out.println("4.Calculate revenue of time");
		System.out.println("5.Exit");
	}
}
