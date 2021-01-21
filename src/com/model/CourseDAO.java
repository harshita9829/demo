package com.model;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface CourseDAO {

	int create(Course course) throws ClassNotFoundException, SQLException;

	List<Course> read() throws ClassNotFoundException, SQLException, ParseException;

	Course read(String id) throws ClassNotFoundException, SQLException, ParseException;

	int update(Course course) throws ClassNotFoundException, SQLException;

	int delete(String id) throws SQLException, ClassNotFoundException;

}