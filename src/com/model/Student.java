package com.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {

	private String id;
	private String name;
	private Date dob;
	private String gender;
	private int departmentId;
//	private int courseId;
	
	public Student() {}

	public Student(String name, Date dob, String gender, int departmentId) {
		super();
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.departmentId = departmentId;
//		this.courseId = courseId;
	}

	public Student(String id, String name, Date dob, String gender, int departmentId) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.departmentId = departmentId;
		
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDob1()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dob);
	}

	

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", departmentId="
				+ departmentId + "]";
	}
	
}
