package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class CourseDAOImpl implements CourseDAO {

	private String autoGenerateId() throws ClassNotFoundException, SQLException 
	{
		Connection con=MyConnection.getConnection();
		ResultSet rs = con.createStatement().executeQuery("select max(substr(id,2,4)) from student");
		String id="";
		if(rs.next())
		{
			int max = rs.getInt(1);
			max++;
			if(max<10)		
				id="C00"+max;			
			else if(max<100)
				id="C0"+max;			
			else
				id="C"+max;				
		}
		return id;
	}
	
	@Override
	public int create(Course course) throws ClassNotFoundException, SQLException {
		Connection con =  MyConnection.getConnection();
        PreparedStatement st = con.prepareStatement("INSERT INTO COURSE VALUES(?,?,?,?,?,?,?");
        st.setString(1, autoGenerateId());
        st.setString(2, course.getName());
        st.setString(3, course.getProficiency());
		SimpleDateFormat sdf=new SimpleDateFormat("hh-mm-ss");
		String duration = sdf.format(course.getDuration());
        st.setString(4, duration);
        st.setInt(5, course.getPrice());
        st.setBytes(6, course.getLogo());
        st.setString(7, course.getDescription());
        int no=st.executeUpdate();
		con.close();
		return no;
	}
	
	@Override
	public List<Course> read() throws ClassNotFoundException, SQLException, ParseException {
		Connection con =  MyConnection.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM Course");
		ResultSet rs = st.executeQuery();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Course> courseList = new ArrayList<>();
		while(rs.next()) {
//			Course course = new Course(rs.getString(1),rs.getString(2),rs.getString(3), sdf.parse(rs.getString(4)), rs.getInt(5), rs.getBytes(6), rs.getString(7));
		  Course course = new Course(rs.getString(1), rs.getString(2), rs.getString(3),sdf.parse(rs.getString(4)),rs.getInt(5), rs.getBytes(6), rs.getString(7));
		  courseList.add(course);
		}
		con.close();
		return courseList;  
	}
	
	@Override
	public Course read(String id) throws ClassNotFoundException, SQLException, ParseException {
		Connection con=MyConnection.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM Course WHERE id=?");
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		Course course=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		if(rs.next())
		course=new Course(rs.getString(1), rs.getString(2), rs.getString(3),sdf.parse(rs.getString(4)),rs.getInt(5), rs.getBytes(6), rs.getString(7));
		con.close();
		return course;
	}
	
	@Override
	public int update(Course course) throws ClassNotFoundException, SQLException 
	{
		Connection con=MyConnection.getConnection();
		PreparedStatement st = null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if(course.getLogo().length==0)
        {
        	st = con.prepareStatement("UPDATE Course SET name=?, proficiency=?, duration=?, price=?, description=? WHERE id=?");
        	st.setString(1,course.getName());
        	st.setString(2, course.getProficiency());
            String duration = sdf.format(course.getDuration());
        	st.setString(3, duration);
        	st.setInt(4, course.getPrice());
        	st.setString(5, course.getDescription());
        	st.setString(6, course.getId());
        }else {
        	st = con.prepareStatement("UPDATE Course SET name=?, proficiency=?, duration=?, price=?,logo=?, description=? WHERE id=?");
        	st.setString(1,course.getName());
        	st.setString(2, course.getProficiency());
            String duration = sdf.format(course.getDuration());
        	st.setString(3, duration);
        	st.setInt(4, course.getPrice());
        	st.setString(5, course.getDescription());
        	st.setBytes(6, course.getLogo());
        	st.setString(7, course.getId());
        }
        int no=st.executeUpdate();
		con.close();
		return no;
	}
	
	@Override
	public int delete(String id) throws SQLException, ClassNotFoundException
	{
		Connection con = MyConnection.getConnection();
		PreparedStatement st = con.prepareStatement("DELETE FROM Course WHERE id=?");
		st.setString(1, id);
		int no=st.executeUpdate();
		con.close();
		return no;
	}
}
