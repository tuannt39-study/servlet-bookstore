package com.vietjack.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
	private long id;
	private String name;
	private String phone;
	private Date dob;
	
	
	
	public Customer() {
		super();
	}

	public Customer(long id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}

	public Customer(long id, String name, String phone, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.dob = dob;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
	

}
