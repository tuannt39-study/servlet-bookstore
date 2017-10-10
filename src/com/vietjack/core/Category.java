package com.vietjack.core;

import java.util.Comparator;

public class Category {
	private long ID;
	private String name;
	private long revenue;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Category(long iD, String name) {
		super();
		ID = iD;
		this.name = name;
	}



	public Category(long iD, String name, long revenue) {
		super();
		ID = iD;
		this.name = name;
		this.revenue = revenue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ID ^ (ID >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (revenue ^ (revenue >>> 32));
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
		Category other = (Category) obj;
		if (ID != other.ID)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (revenue != other.revenue)
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

	public long getRevenue() {
		return revenue;
	}

	public void setRevenue(long revenue) {
		this.revenue = revenue;
	}

	public static Comparator<Category> compare = new Comparator<Category>() {
		public int compare(Category c1, Category c2) {
			return (int) -(c1.getRevenue() - c2.getRevenue());
		}
	};

	@Override
	public String toString() {
		return "Category [ID= " + ID + ", name= " + name + "]";
	}

}
