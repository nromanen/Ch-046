package com.ss.schedule.controllers;

import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.model.Classroom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 02.12.16.
 */
@WebServlet(urlPatterns = "/classrooms")
public class ClassroomsController extends HttpServlet {

    private ClassroomDao classroomDao = new ClassroomDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setAttribute("isMessage", false);
        List<Classroom> classrooms = classroomDao.getAll();
        req.setAttribute("classrooms", classrooms);
        req.getRequestDispatcher("/WEB-INF/view/classrooms.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));
        String name = classroomDao.getById(id).getName();
        System.out.println(id);
        classroomDao.delete(id);
        req.setAttribute("isMessage", true);
        req.setAttribute("message", "Classroom " + name + " deleted successfully!");
        List<Classroom> classrooms = classroomDao.getAll();
        req.setAttribute("classrooms", classrooms);
        req.getRequestDispatcher("/WEB-INF/view/classrooms.jsp").forward(req, resp);

    }
}
