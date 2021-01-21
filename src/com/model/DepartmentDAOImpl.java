package com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl {

	public List<Department> read() throws ClassNotFoundException, SQLException
{
		Connection con = MyConnection.getConnection();
		ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Department");
		List<Department> departmentList = new ArrayList<Department>();
		while(rs.next()) {
			Department department = new Department(rs.getInt(1),rs.getString(2));
			departmentList.add(department);
		}
		return departmentList;
	}
}
