package com.vietjack.app;

import java.util.Scanner;

import com.vietjack.service.MenuService;

public class MainApp {
	private static MenuService menuService = new MenuService();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		/*authorService.top3AuthorByRevenue();
		System.out.println("-----------------------");
		categoryService.top3CategoryByRevenue();*/

		boolean flag = true;
		while (flag) {
			menuService.printMenuMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				MenuService.authorMenus();
				break;
			case 2:
				MenuService.categoryMenus();
				break;
			case 3:
				MenuService.bookMenus();
				break;
			case 4:
				MenuService.ordersMenus();
				break;
			case 5:
				MenuService.bookStoreMenus();
				break;
			case 6:
				System.out.println("System end");
				flag = false;
				break;
			}

		}

	}
}
