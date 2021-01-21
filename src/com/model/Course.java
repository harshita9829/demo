package com.model;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Course {

	private String id;
	private String name;
	private String proficiency;
	private Date duration;
	private int price;
	private String description;
	private byte[] logo;
	
	public Course() {}

	public Course(String name, String proficiency, Date duration, int price, String description, byte[] logo) {
		super();
		this.name = name;
		this.proficiency = proficiency;
		this.duration = duration;
		this.price = price;
		this.description = description;
		this.logo = logo;
	}

	public Course(String id, String name, String proficiency, Date duration, int price, byte[] logo, String description) {
		super();
		this.id = id;
		this.name = name;
		this.proficiency = proficiency;
		this.duration = duration;
		this.price = price;
		this.description = description;
		this.logo = logo;
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



	public String getProficiency() {
		return proficiency;
	}



	public void setProficiency(String proficiency) {
		this.proficiency = proficiency;
	}



	public Date getDuration() {
		return duration;
	}



	public void setDuration(Date duration) {
		this.duration = duration;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public byte[] getLogo() {
		return logo;
	}



	public void setLogo(byte[] logo) {
		this.logo = logo;
	} 
    
	public String Duration1() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		return sdf.format(duration);
	}


	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", proficiency=" + proficiency + ", duration=" + duration
				+ ", price=" + price + ", description=" + description + ", logo=" + Arrays.toString(logo) + "]";
	}

	
	
	
	
}
