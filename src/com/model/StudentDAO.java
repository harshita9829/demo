package com.model;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface StudentDAO {

	int create(Student student) throws ClassNotFoundException, SQLException;

	List<Student> read() throws ClassNotFoundException, SQLException, ParseException;

	int update(Student student) throws ClassNotFoundException, SQLException;

	int delete(String id) throws ClassNotFoundException, SQLException;

	Student read(String id) throws ClassNotFoundException, SQLException, ParseException;

}