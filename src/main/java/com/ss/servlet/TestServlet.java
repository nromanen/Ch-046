package com.ss.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ss.dao.SubjectDao;
import com.ss.dao.TeacherDao;
import com.ss.dao.TeachersSubjectsDao;
import com.ss.teacher.Subject;
import com.ss.teacher.Teacher;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/hello" })
public class TestServlet extends HttpServlet {

	// displays teachers list on main page
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Teacher> list = TeacherDao.getAll();
		req.setAttribute("teachers", list);
		req.getRequestDispatcher("/WEB-INF/view/test.jsp").forward(req, resp);

	}

	// adds subject connected with teacher in teachers_subjects
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SubjectDao sd = new SubjectDao();
		TeachersSubjectsDao tsd = new TeachersSubjectsDao();
		TeacherDao td = new TeacherDao();
		int teacherId = Integer.parseInt(request.getParameter("teacherId"));
		int subjectId = Integer.parseInt(request.getParameter("subject"));
		String teacherName = td.getById(teacherId).toString();
		String firstname = td.getById(teacherId).getFirstName();
		String lastname = td.getById(teacherId).getLastName();
		tsd.setSubject(teacherId, subjectId);
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
