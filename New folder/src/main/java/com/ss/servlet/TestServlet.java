package com.ss.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ss.teacher.Subject;
import com.ss.teacher.Teacher;
import com.ss.teacher.TeacherDao;
import com.ss.teacher.TeachersSubjectsDao;

import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = {"/hello", "/"})
public class TestServlet extends HttpServlet {

	// displays teachers list on main page
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> list = TeacherDao.getAll();
        req.setAttribute("teachers",list);
        req.getRequestDispatcher("/WEB-INF/view/test.jsp").forward(req, resp);

    }

    // adds subject connected with teacher in teachers_subjects
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("started");
    	TeacherDao td = new TeacherDao();
    	TeachersSubjectsDao tsd = new TeachersSubjectsDao();
    	 int teacherId = Integer.parseInt(req.getParameter("teacher"));
    	 int subjectId = Integer.parseInt(req.getParameter("subject"));
    	 String teacherName = td.getById(teacherId).toString();
    	 tsd.setSubject(teacherId,subjectId);
    	 List<Subject> list = tsd.getSubjects(teacherId);
    	 System.out.println("teacherid"+teacherId);
    	 System.out.println("subjectId"+subjectId);
    	 System.out.println(list);
    	 req.setAttribute("subjects",list);
    	 req.getRequestDispatcher("/WEB-INF/view/subjectView.jsp").forward(req, resp);
    }
}
