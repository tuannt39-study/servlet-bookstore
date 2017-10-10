package com.vietjack.core;

public class OrderDetail {
	private long id;
	private Order order;
	private Book book;
	private long quantity;

	public OrderDetail() {
		super();
	}

	public OrderDetail(long id, Order order, Book book, long quantity) {
		super();
		this.id = id;
		this.order = order;
		this.book = book;
		this.quantity = quantity;
	}
	public OrderDetail(long id, Book book, long quantity) {
		super();
		this.id = id;
		this.book = book;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", order=" + order + ", book=" + book + ", quantity=" + quantity + "]";
	}

}
