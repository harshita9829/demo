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
import com.model.Student;
import com.model.StudentDAO;
import com.model.StudentDAOImpl;

/**
 * Servlet implementation class FindStudentServlet
 */
@WebServlet({ "/FindStudentServlet", "/finds", "/students" })
public class FindStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Find student  servlet:");
//		int id=Integer.parseInt(request.getParameter("id"));
		String id = request.getParameter("id");
		System.out.println(id);
		StudentDAO sdao=new StudentDAOImpl();
		Student student=null;
		try {
			student=sdao.read(id);
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ajax will be able to get responseText only if servlet prints output
		PrintWriter out=response.getWriter();
//		out.print(student);
		//going to convert java object into json
		Gson gson = new GsonBuilder()
				   .setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(student);
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
