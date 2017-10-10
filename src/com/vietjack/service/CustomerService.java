package com.vietjack.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.vietjack.core.Customer;
import com.vietjack.dao.CustomerDAO;

public class CustomerService {
	public static Scanner scanner = new Scanner(System.in);
	public static CustomerDAO customerDAO = new CustomerDAO();
	
	public static void addNewRealCustomer() {
		System.out.println("Add new Customer function");
		try {
			long id = customerDAO.generateId();
			System.out.println("Input name");
			String name = scanner.nextLine();
			System.out.println("Input dob");
			String dob = scanner.nextLine();
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			Date dobDate=sdf.parse(dob);
			Customer customer = new Customer(id, name, dobDate);
			customerDAO.addNewCustomer(customer);
		} catch (Exception e) {
			System.out.println("There is an error when adding new Customer");
			e.printStackTrace();
		}
	}

	public static void findCustomerByName() {
		System.out.println("Find Customer by name function");
		System.out.println("Input the name");
		String name = scanner.nextLine();
		Customer customer = null;
		try {
			customer = customerDAO.findCustomerByName(name);
		} catch (Exception e) {
			System.out.println("There is an error when finding a Customer");
			e.printStackTrace();
		}
		if (customer != null) {
			System.out.println(customer);
		} else {
			System.out.println("Couldn't find the Customer with name: " + name);
		}
	}

	public static void displayCustomer() {
		try {
			ArrayList<Customer> customerList = customerDAO.findAllCustomer();
			for (Customer customer : customerList) {
				System.out.println(customer);
			}
		} catch (Exception e) {
			System.out.println("There is an error when display all Customers");
			e.printStackTrace();
		}
	}

	public static void updateCustomer() {
		try {
			System.out.println("Update Customer by id function");
			System.out.println("Input id");
			String id = scanner.nextLine();
			Customer customer = customerDAO.findCustomerById(Long.parseLong(id));
			if (customer == null) {
				System.out.println("Couldn't find the Customer id=" + id);
			} else {
				System.out.println("Found Customer :" + id);
				System.out.println("With info:" + customer);
				System.out.println("Input new name");
				String name = scanner.nextLine();
				System.out.println("Input new email");
				System.out.println("Input dob");
				String dob = scanner.nextLine();
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				Date dobDate=sdf.parse(dob);
				Customer newCustomer = new Customer(Long.parseLong(id), name,
						dobDate);
				customerDAO.modifyCustomer(newCustomer);

			}

		} catch (Exception e) {
			System.out.println("There is an error when updating a Customer");
			e.printStackTrace();
		}
	}

	public static void deleteCustomerByName() {
		System.out.println("Delete Customer by name function");
		System.out.println("Input the name");
		String name=scanner.nextLine();
		try{
			customerDAO.deleteCustomerByName(name);
		}catch(Exception e){
			System.out.println("There is an error when deleting a Customer");
			e.printStackTrace();
		}
	}
	
	public void printCustomerMenu(){
		System.out.println("The Customer management program");
		System.out.println("1. Add new Customer");
		System.out.println("2. Find Customer by name");
		System.out.println("3. Display Customers");
		System.out.println("4. Update Customer");
		System.out.println("5. Delete Customer by name");
		System.out.println("6. Exit");
	}
}
