package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Student;
import com.model.StudentDAO;
import com.model.StudentDAOImpl;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet({ "/StudentServlet", "/student" })
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btn=request.getParameter("btn");
//		int id=0;
		String id = "";
		StudentDAO sdao=new StudentDAOImpl();
		String sid=null;
		String name="";
		String gender="";
		int departmentId=0;
		if(btn==null)
		{
			btn="Delete";
		}
		switch(btn)
		{
		case "Delete":
			sid=request.getParameter("id");
			if(sid==null || sid.equals(""))
			{
				System.out.println("id is null while trying to delete a student");
				return;
			}
			int no=0;
//			id=Integer.parseInt(request.getParameter("id"));
			id= request.getParameter("id");
			try {
				no=sdao.delete(id);
				System.out.println(no);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			//add or update
			sid=request.getParameter("id");
			if(sid==null)
			{
				return;
			}
			Student student=new Student();
			student.setName(request.getParameter("name"));
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date dob=new Date();
			try {
				dob=sdf.parse(request.getParameter("dob"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			student.setDob(dob);
			student.setGender(request.getParameter("gender"));
			student.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
			if(sid.equals(""))
			{
				//new record to be created
				try {
					sdao.create(student);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
			{
				//update existing record
				student.setId(sid);
				try {
					sdao.update(student);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}
		response.sendRedirect("Student.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
