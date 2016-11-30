package com.ss.schedule.controllers;



import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.SubjectType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/classrooms")
public class ClassroomsController extends HttpServlet {

    ClassroomDao classroomDao = new ClassroomDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("isResult", false);
            req.getRequestDispatcher("/WEB-INF/view/classrooms.jsp").forward(req, resp);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] subject = req.getParameterValues("subject");
        String[] group = req.getParameterValues("group");

        SubjectType type = SubjectType.LECTURE;
        int capacity = 29;
        List<Classroom> classrooms =  classroomDao.getByTypeAndCapacity(type, capacity);
        if(classrooms.size() != 0) {
            req.setAttribute("isResult", true);
            req.setAttribute("classrooms", classrooms);
        }
        req.setAttribute("subject", subject[0]);
        req.setAttribute("group", group[0]);
        req.getRequestDispatcher("/WEB-INF/view/classrooms.jsp").forward(req, resp);
    }
}
