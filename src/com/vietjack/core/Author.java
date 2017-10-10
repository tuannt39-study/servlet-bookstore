package com.vietjack.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Author {
	private long ID;
	private String name;
	private long revenue;
	private Date dob;

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Author(long iD, String name, Date dob) {
		super();
		ID = iD;
		this.name = name;
		this.dob = dob;
	}

	public Author(long iD, String name, long revenue, Date dob) {
		super();
		ID = iD;
		this.name = name;
		this.revenue = revenue;
		this.dob = dob;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getDobString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(dob);
	}

	public void setDobString(String dob) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");	
		try {
			this.dob = sdf.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ID ^ (ID >>> 32));
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
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
		Author other = (Author) obj;
		if (ID != other.ID)
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
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

	public static Comparator<Author> compare = new Comparator<Author>() {
		public int compare(Author a1, Author a2) {
			return (int) -(a1.getRevenue() - a2.getRevenue());
		}
	};

	@Override
	public String toString() {
		return "Author [ID= " + ID + ", name= " + name + ", dob= " + dob +"]";
	}

}
