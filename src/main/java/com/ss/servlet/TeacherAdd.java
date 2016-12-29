package com.ss.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ss.dao.TeacherDao;
import com.ss.teacher.Teacher;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/AddTeacher.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	// adds new teacher to the list

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error;
		String error2;
		TeacherDao td = new TeacherDao();
		TeacherValid tv = new TeacherValid();
		StringBuilder errorFirstName = new StringBuilder();
		StringBuilder errorLastName = new StringBuilder();

		if (tv.isNameEmpty(request.getParameter("firstname")) == false) {

			errorFirstName.append(tv.getError());
			request.setAttribute("error", tv.getError());
		} else if (tv.nameMatches(request.getParameter("firstname"), tv.getNamePattern()) == false) {

			error2 = " name should start from uppercase letter and be no longer than 34 symbols";
			errorFirstName.append(error2);
			request.setAttribute("error", errorFirstName);
			/*
			 * request.getRequestDispatcher("/WEB-INF/view/AddTeacher.jsp").
			 * forward(request, response);
			 */ }

		if (tv.isNameEmpty(request.getParameter("lastname")) == false) {

			errorLastName.append(tv.getError());
			request.setAttribute("error2", tv.getError());

		} else if (tv.nameMatches(request.getParameter("lastname"), tv.getNamePattern()) == false) {

			error = " lastname should start from uppercase letter and be no longer than 34 symbols";
			errorLastName.append(error);
			request.setAttribute("error2", error);
		}

		if (!errorFirstName.toString().isEmpty() || !errorLastName.toString().isEmpty()) {
			request.setAttribute("error", errorFirstName);
			request.setAttribute("error2", errorLastName);
			request.getRequestDispatcher("/WEB-INF/view/AddTeacher.jsp").forward(request, response);

		} else if (td.isExist(request.getParameter("lastname"), request.getParameter("firstname"))) {
			error = "teacher with same firstname and lastname already exist";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/WEB-INF/view/AddTeacher.jsp").forward(request, response);
		} else {

			Teacher teacher = new Teacher(request.getParameter("firstname"), request.getParameter("lastname"));
			td.add(teacher);
			List<Teacher> list = TeacherDao.getAll();
			request.setAttribute("teachers", list);
			request.getRequestDispatcher("/WEB-INF/view/test.jsp").forward(request, response);
		}
	}

}
