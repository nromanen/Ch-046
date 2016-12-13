package com.ss.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ss.teacher.Teacher;
import com.ss.teacher.TeacherDao;
import com.ss.validation.TeacherValid;

/**
 * Servlet implementation class TeacherAdd
 */
@WebServlet("/TeacherAdd")
public class TeacherAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#HttpServlet()
	 */
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/AddTeacher.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	// adds new teacher to the list
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String error;
		 TeacherValid tv = new TeacherValid();
		 if(tv.isNameEmpty(request.getParameter("firstame"))==false || tv.isNameEmpty(request.getParameter("lastname"))==false){
				error=tv.getError();
				request.setAttribute("error", error);
				request.getRequestDispatcher("/WEB-INF/view/TeacherError.jsp").forward(request, response);
			 }
			else if (tv.nameMatches(request.getParameter("firstame"),tv.getNamePattern())==false || tv.nameMatches(request.getParameter("lastname"), tv.getNamePattern())==false)
			{
				error="not correct data, name and lastname should start from uppercase letter and be no longer than 34 symbols";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/WEB-INF/view/TeacherError.jsp").forward(request, response);
			}		 
		else{
		TeacherDao td = new TeacherDao();
		Teacher teacher = new Teacher(request.getParameter("firstame"),request.getParameter("lastname"));
		td.add(teacher);
		 List<Teacher> list = TeacherDao.getAll();
	        request.setAttribute("teachers",list);
	        request.getRequestDispatcher("/WEB-INF/view/test.jsp").forward(request, response);
		 }		
	}

}
