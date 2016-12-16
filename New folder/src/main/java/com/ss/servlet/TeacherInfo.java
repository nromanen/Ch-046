package com.ss.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ss.teacher.Subject;
import com.ss.teacher.SubjectDao;
import com.ss.teacher.Teacher;
import com.ss.teacher.TeacherDao;
import com.ss.teacher.TeachersSubjectsDao;
import com.ss.validation.TeacherValid;

/**
 * Servlet implementation class TeacherInfo
 */
@WebServlet("/TeacherInfo")
public class TeacherInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// displays teachers subjects and teachers info
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TeachersSubjectsDao tsd = new TeachersSubjectsDao();
		TeacherDao tdo = new TeacherDao();
		SubjectDao sd = new SubjectDao();
		int i = Integer.parseInt(req.getParameter("teacher"));
		String teacherName = tdo.getById(i).toString();
		String firstname = tdo.getById(i).getFirstName();
		String lastname = tdo.getById(i).getLastName();
		TeachersSubjectsDao td = new TeachersSubjectsDao();
		List<Subject> list = td.getSubjects(i);
		List<Subject> unusedSubjects = tsd.getUnusedSubjects(i);
		req.setAttribute("allsubjects", unusedSubjects);
		req.setAttribute("subjects", list);
		req.setAttribute("name", teacherName);
		req.setAttribute("firstname", firstname);
		req.setAttribute("lastname", lastname);
		req.setAttribute("id", i);
		req.getRequestDispatcher("/WEB-INF/view/subjects.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// updates teachers info
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error;
		Teacher teacher = new Teacher();
		TeacherDao td = new TeacherDao();
		TeacherValid tv = new TeacherValid();
		
		if(tv.isNameEmpty(request.getParameter("firstname"))==false || tv.isNameEmpty(request.getParameter("lastname"))==false){
			error=tv.getError();
			request.setAttribute("error", error);
			request.getRequestDispatcher("/WEB-INF/view/TeacherError.jsp").forward(request, response);
		 }
		else if (tv.nameMatches(request.getParameter("firstname"),tv.getNamePattern())==false || tv.nameMatches(request.getParameter("lastname"), tv.getNamePattern())==false)
		{
			error="not correct data, name and lastname should start from uppercase letter and be no longer than 34 symbols";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/WEB-INF/view/TeacherError.jsp").forward(request, response);
		}
		else {
			td.update(Integer.parseInt(request.getParameter("teacherId")), request.getParameter("firstname"),
					request.getParameter("lastname")); 
		 List<Teacher> list = TeacherDao.getAll();
			request.setAttribute("teachers", list);
			request.getRequestDispatcher("/WEB-INF/view/test.jsp").forward(request, response);
		}
	
	}

}
