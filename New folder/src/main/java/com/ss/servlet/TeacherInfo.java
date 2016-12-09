package com.ss.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ss.teacher.DBConnector;
import com.ss.teacher.Subject;
import com.ss.teacher.SubjectDao;
import com.ss.teacher.Teacher;
import com.ss.teacher.TeacherDao;
import com.ss.teacher.TeachersSubjectsDao;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // displays teachers subjects and teachers info
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TeacherDao tdo = new TeacherDao();
		SubjectDao sd = new SubjectDao();
        int i = Integer.parseInt(req.getParameter("teacher"));
        String teacherName = tdo.getById(i).toString();
        String firstname = tdo.getById(i).getFirstName();
        String lastname = tdo.getById(i).getLastName();
        TeachersSubjectsDao td = new TeachersSubjectsDao();
        List<Subject> list = td.getSubjects(i);
        List<Subject> allSubjects = sd.getAll();
        req.setAttribute("allsubjects",allSubjects);
        req.setAttribute("subjects",list);
        req.setAttribute("name",teacherName);
        req.setAttribute("firstname",firstname);
        req.setAttribute("lastname",lastname);
        req.setAttribute("id",i);
         req.getRequestDispatcher("/WEB-INF/view/subjects.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// updates teachers info
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Teacher teacher = new Teacher();
		TeacherDao td = new TeacherDao();
		teacher.setFirstName(request.getParameter("firstame"));
		teacher.setLastName(request.getParameter("lastname"));
		teacher.setId(Integer.parseInt(request.getParameter("id")));
		td.update(teacher);
		
		System.out.println("start");
		System.out.println(request.getParameter("firstame"));
		System.out.println(request.getParameter("lastname"));
		System.out.println(Integer.parseInt(request.getParameter("id")));
       
        System.out.println("finish");
        List<Teacher> list = TeacherDao.getAll();
        request.setAttribute("teachers",list);
        request.getRequestDispatcher("/WEB-INF/view/test.jsp").forward(request, response);
        
		//doGet(request, response);
	}

}
