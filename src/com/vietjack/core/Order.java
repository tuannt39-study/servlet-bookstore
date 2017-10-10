package com.vietjack.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
	private long id;
	private Date orderDate;
	private Customer customer;
	
	private OrderDetail [] orderdetails;
	
	
	
	public OrderDetail[] getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(OrderDetail[] orderdetails) {
		this.orderdetails = orderdetails;
	}

	public Order() {
		super();
	}

	public Order(long id, Date orderDate, Customer customer) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.customer = customer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderDateString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(orderDate);
	}

	public void setOrderDateString(String dob) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");	
		try {
			this.orderDate = sdf.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", customer=" + customer + "]";
	}

}
