package com.model;

public class Department {

//	CREATE TABLE DEPARTMENT
//	(
//	    ID INT PRIMARY KEY,
//	    NAME VARCHAR2(50)
//	);
	private int id;
	private String name;
	public Department(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	public Department(String name) {
		super();
		this.name = name;
	}


	public Department() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
	
	
}
