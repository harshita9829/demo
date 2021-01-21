package com.model;

import java.util.Date;

public class StudentCourse {

//	CREATE TABLE STUDENTCOURSE
//	(
//	    STUDENT_ID INT,
//	    COURSE_ID INT,
//	    ENROLLMENT_DATE DATE,
//	    ACTIVE varchar2(5)
//	);
	
	private int studentId;
	private int courseId;
	private Date enrollmentDate;
	private boolean active;

	public StudentCourse() {}

	public StudentCourse(int studentId, int courseId, Date enrollmentDate, boolean active) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
		this.enrollmentDate = enrollmentDate;
		this.active = active;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Date getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "StudentCourse [studentId=" + studentId + ", courseId=" + courseId + ", enrollmentDate=" + enrollmentDate
				+ ", active=" + active + "]";
	}
	
	
}
