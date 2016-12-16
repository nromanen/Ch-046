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
import com.ss.teacher.TeacherDao;
import com.ss.teacher.TeachersSubjectsDao;

/**
 * Servlet implementation class SubjectEdit
 */
@WebServlet("/SubjectEdit")
public class SubjectEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubjectEdit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*System.out.println(request.getParameter("teacherId"));
		System.out.println(request.getParameter("subjectId"));
		TeachersSubjectsDao tsd = new TeachersSubjectsDao();
		TeacherDao td = new TeacherDao();
		int teacherId = Integer.parseInt(request.getParameter("teacherId"));
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		String teacherName = td.getById(teacherId).toString();
		tsd.delete(subjectId, teacherId);
		List<Subject> list = tsd.getSubjects(teacherId);
		request.setAttribute("name",teacherName);
		request.setAttribute("subjects", list);
		request.getRequestDispatcher("/WEB-INF/view/subjects.jsp").forward(request, response);*/
		
		SubjectDao sd = new SubjectDao();
		TeachersSubjectsDao tsd = new TeachersSubjectsDao();
		TeacherDao td = new TeacherDao();
		int teacherId = Integer.parseInt(request.getParameter("teacherId"));
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		String teacherName = td.getById(teacherId).toString();
		String firstname = td.getById(teacherId).getFirstName();
		String lastname = td.getById(teacherId).getLastName();
		tsd.delete(subjectId, teacherId);
		List<Subject> list = tsd.getSubjects(teacherId);
		List<Subject> unusedSubjects = tsd.getUnusedSubjects(teacherId);
		request.setAttribute("allsubjects", unusedSubjects);
		request.setAttribute("subjects", list);
		request.setAttribute("name", teacherName);
		request.setAttribute("firstname", firstname);
		request.setAttribute("lastname", lastname);
		request.setAttribute("id", teacherId);
		request.getRequestDispatcher("/WEB-INF/view/subjects.jsp").forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {				
		System.out.println(request.getParameter("teacherId"));
		System.out.println(request.getParameter("subjectId"));
		SubjectDao sd = new SubjectDao();
		TeachersSubjectsDao tsd = new TeachersSubjectsDao();
		TeacherDao td = new TeacherDao();
		int teacherId = Integer.parseInt(request.getParameter("teacherId"));
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		String teacherName = td.getById(teacherId).toString();
		String firstname = td.getById(teacherId).getFirstName();
		String lastname = td.getById(teacherId).getLastName();
		tsd.delete(subjectId, teacherId);
		List<Subject> list = tsd.getSubjects(teacherId);
		List<Subject> unusedSubjects = tsd.getUnusedSubjects(teacherId);
		request.setAttribute("allsubjects", unusedSubjects);
		request.setAttribute("subjects", list);
		request.setAttribute("name", teacherName);
		request.setAttribute("firstname", firstname);
		request.setAttribute("lastname", lastname);
		request.setAttribute("id", teacherId);
		request.getRequestDispatcher("/WEB-INF/view/subjects.jsp").forward(request, response);
	}

}
