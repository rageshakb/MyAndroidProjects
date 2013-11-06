package com.example.loginapp.models;

public class User {
	private long id;
	private String department;
	private String name;		
	
	public User(long id, String department, String name) {		
		this.id = id;
		this.department = department;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
