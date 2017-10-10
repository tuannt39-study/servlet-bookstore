package com.vietjack.core;

import java.util.ArrayList;

public class Book {
	private long ID;
	private String name;
	private double price;
	private long soldNumber;
	private ArrayList<Author> author;
	private Category category;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(long iD, String name, double price, Category category) {
		super();
		ID = iD;
		this.name = name;
		this.price = price;
		this.category = category;
	}

	public Book(long iD, String name, double price, ArrayList<Author> author, Category category) {
		super();
		ID = iD;
		this.name = name;
		this.price = price;
		this.author = author;
		this.category = category;
	}

	public Book(long iD, String name, double price, long soldNumber, ArrayList<Author> author, Category category) {
		super();
		ID = iD;
		this.name = name;
		this.price = price;
		this.soldNumber = soldNumber;
		this.author = author;
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ID ^ (ID >>> 32));
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (soldNumber ^ (soldNumber >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (ID != other.ID)
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (soldNumber != other.soldNumber)
			return false;
		return true;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getSoldNumber() {
		return soldNumber;
	}

	public void setSoldNumber(long soldNumber) {
		this.soldNumber = soldNumber;
	}

	public ArrayList<Author> getAuthor() {
		return author;
	}

	public void setAuthor(ArrayList<Author> author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Book [ID=" + ID + ", name=" + name + ", price=" + price + ", author=" + author + ", category="
				+ category + "]";
	}

}
