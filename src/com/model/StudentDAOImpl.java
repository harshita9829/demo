package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

	private String autoGenerateId() throws ClassNotFoundException, SQLException 
	{
		Connection con=MyConnection.getConnection();
		ResultSet rs = con.createStatement().executeQuery("select max(substr(id,2,4)) from Student1");
		String id="";
		if(rs.next())
		{
			int max = rs.getInt(1);
			max++;
			if(max<10)		
				id="S00"+max;			
			else if(max<100)
				id="S0"+max;			
			else
				id="S"+max;				
		}
		return id;
	}
	
	@Override
	public int create(Student student) throws ClassNotFoundException, SQLException {
		Connection con =  MyConnection.getConnection();
		PreparedStatement st = con.prepareStatement("INSERT INTO Student1 VALUES (?,?,?,?,?)");
		st.setString(1, autoGenerateId());
		st.setString(2, student.getName());
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yy");
		String dob=sdf.format(student.getDob());
		st.setString(3, dob);
		st.setString(4, student.getGender());
		st.setInt(5, student.getDepartmentId());
//		st.setInt(6, student.getCourseId());
        int no = st.executeUpdate();
        System.out.println(no+"rows updated");
        con.close();
        return no;
	}
	
	@Override
	public List<Student> read() throws ClassNotFoundException, SQLException, ParseException {
		Connection con = MyConnection.getConnection();
		ResultSet rs = con.createStatement().executeQuery("Select * From Student1");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Student> studentList = new ArrayList<>();
		while(rs.next()) {
			Student student = new Student(rs.getString(1),rs.getString(2),sdf.parse(rs.getString(3)),rs.getString(4),rs.getInt(5));
			studentList.add(student);
		}
		con.close();
		return studentList;
	}
	
	@Override
	public Student read(String id) throws ClassNotFoundException, SQLException, ParseException {
		Connection con =  MyConnection.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM Student1 WHERE id=?");
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Student student=null;
		if(rs.next())
			student = new Student(rs.getString(1),rs.getString(2),sdf.parse(rs.getString(3)),rs.getString(4),rs.getInt(5));
		con.close();
		return student;

	}
	
	@Override
	public int update(Student student) throws ClassNotFoundException, SQLException {
		Connection con =  MyConnection.getConnection();
		PreparedStatement st = con.prepareStatement("UPDATE Student1 SET name=?, dob=?, gender=?, department_Id=? WHERE id=?");
				st.setString(1, student.getName());
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yy");
		String dob=sdf.format(student.getDob());
		st.setString(2, dob);
		st.setString(3, student.getGender());
		st.setInt(4, student.getDepartmentId());
//		st.setInt(5, student.getCourseId());
		st.setString(5, student.getId());

        int no = st.executeUpdate();
        System.out.println(no+"rows updated");
        con.close();
        return no;
	}
	
    @Override
	public int delete(String id) throws ClassNotFoundException, SQLException {
    	Connection con = MyConnection.getConnection();
		PreparedStatement st = con.prepareStatement("DELETE FROM Student1 WHERE id=?");
		st.setString(1, id);
		int no=st.executeUpdate();
		con.close();
		System.out.println(no+" row(s) deleted");
		return no;

    }

//	@Override
//	public Student read(int id) throws ClassNotFoundException, SQLException, ParseException {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}
