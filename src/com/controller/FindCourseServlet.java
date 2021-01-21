package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Course;
import com.model.CourseDAO;
import com.model.CourseDAOImpl;


/**
 * Servlet implementation class FindCourseServlet
 */
@WebServlet({ "/FindCourseServlet", "/findc", "/findC", "/findCourse" })
public class FindCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Find Course Servlet");
		String id = request.getParameter("id");
		System.out.println(id);
		CourseDAO cdao=new CourseDAOImpl();
		Course course=null;
		try {
			course=cdao.read(id);
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			e.printStackTrace();
		}
		//ajax will be able to get responseText only if servlet prints output
		PrintWriter out=response.getWriter();
		Gson gson = new GsonBuilder()
				   .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(course);
		out.print(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
