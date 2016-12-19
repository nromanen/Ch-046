package com.ss.schedule.controllers.classroom;

import com.ss.schedule.model.Classroom;
import com.ss.schedule.services.ClassroomService;

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

    private final static String CANT_DELETE_MESSAGE = "Classroom can't be deleted. You have to edit or delete below timetables!";
    private final static String DELETE_MESSAGE = "Classroom deleted successfully!";

    private ClassroomService classroomService = new ClassroomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Classroom> classrooms = classroomService.getAll();
        req.setAttribute("isMessage", false);
        req.setAttribute("classrooms", classrooms);
        req.getRequestDispatcher("/WEB-INF/view/classrooms.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));

        if(!classroomService.canDeleteClassroom(id)){
            req.setAttribute("errorMessage", CANT_DELETE_MESSAGE);
            req.setAttribute("timeTables", classroomService.getTimeTableByClassroom(id));
            req.setAttribute("classroomId", id);
            req.getRequestDispatcher("/WEB-INF/view/deleteClassroom.jsp").forward(req, resp);
            return;
        }

        classroomService.delete(id);
        req.setAttribute("isMessage", true);
        req.setAttribute("message", DELETE_MESSAGE);
        req.setAttribute("classrooms", classroomService.getAll());
        req.getRequestDispatcher("/WEB-INF/view/classrooms.jsp").forward(req, resp);

    }
}
