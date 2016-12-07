package com.ss.teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/hello")
public class TestServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> list = TeacherDao.getAll();
        req.setAttribute("teachers",list);
        req.getRequestDispatcher("/WEB-INF/view/test.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int i = Integer.parseInt(req.getParameter("t"));
        TeachersSubjectsDao td = new TeachersSubjectsDao();
        List<Subject> list = td.getSubjects(i);
        req.setAttribute("subjects",list);
         req.getRequestDispatcher("/WEB-INF/view/subjects.jsp").forward(req, resp);
      //  System.out.println(teachers[0]);
    }
}
