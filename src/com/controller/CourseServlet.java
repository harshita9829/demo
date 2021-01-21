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

import com.model.Course;
import com.model.CourseDAO;
import com.model.CourseDAOImpl;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet({ "/CourseServlet", "/course" })
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btn=request.getParameter("btn");
		String id = "";
		CourseDAO cdao=new CourseDAOImpl();
		String cid=null;
		String name="";
		String proficiency="";
		int price=0;
		String description="";
		byte[] logo= null;
		if(btn==null)
		{
			btn="Delete";
		}
		switch(btn)
		{
		case "Delete":
			cid=request.getParameter("id");
			if(cid==null || cid.equals(""))
			{
				System.out.println("id is null while trying to delete a course");
				return;
			}
			int no=0;
//			id=Integer.parseInt(request.getParameter("id"));
			id= request.getParameter("id");
			try {
				no=cdao.delete(id);
				System.out.println(no);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			//add or update
			cid=request.getParameter("id");
			if(cid==null)
			{
				return;
			}
			Course course=new Course();
			course.setName(request.getParameter("name"));
//			int price1 = Integer.parseInt(price);
			String price1 = String.valueOf(price);
//			course.setPrice(request.getParameter(price1));
			course.setProficiency(request.getParameter(proficiency));
			course.setDescription(request.getParameter(description));
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date duration=new Date();
			try {
				duration=sdf.parse(request.getParameter("duration"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			course.setDuration(duration);
//			student.setGender(request.getParameter("gender"));
			if(cid.equals(""))
			{
				//new record to be created
				try {
					cdao.create(course);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}else
			{
				//update existing record
				course.setId(cid);
				try {
					cdao.update(course);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		}
		response.sendRedirect("Course.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
