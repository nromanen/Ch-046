package com.ss.schedule.controllers.schedule;

/**
 * Created by Admin on 14.12.16.
 */

import com.ss.schedule.model.TimeTable;
import com.ss.schedule.services.ClassroomService;
import com.ss.schedule.services.TimeTableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/timetables")

public class TimeTableController extends HttpServlet{


    private static final String CANT_DELETE_MESSAGE = "Classroom can't be deleted. You have to edit or delete below timetables!";
    private static final String TIMETABLE_DELETE_MESSAGE= "Timetable deleted successfully!";
    private static final String TIMETABLES_DELETE_MESSAGE= "Timetables deleted successfully!";

    TimeTableService timeTableService = new TimeTableService();
    private ClassroomService classroomService = new ClassroomService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       List<TimeTable> timeTables = timeTableService.getAll();

        req.setAttribute("timeTables", timeTables);
        req.getRequestDispatcher("/WEB-INF/view/timetable.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = 0L;
        long classroomId = 0L;
        String fromPage = "";
        try{
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        try{
            classroomId = Long.parseLong(req.getParameter("classroomId"));
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        try{
            fromPage = req.getParameter("fromPage");
        } catch (NumberFormatException e){
            e.printStackTrace();
        }

        if (id != 0 && fromPage.equals("deleteClassroom")) {
            timeTableService.delete(id);
            List<TimeTable> timeTables = classroomService.getTimeTableByClassroom(classroomId);
            System.out.println(timeTables);
            if(timeTables.isEmpty()) {
                req.setAttribute("message", "All timetable where used this classroom deleted successfully. You can delete classroom now!" );
                req.setAttribute("classroomId", classroomId);
                req.getRequestDispatcher("/WEB-INF/view/deleteClassroom.jsp").forward(req, resp);
                return;
            }
            req.setAttribute("message", TIMETABLE_DELETE_MESSAGE);
            req.setAttribute("errorMessage", CANT_DELETE_MESSAGE);
            req.setAttribute("timeTables", timeTables);
            req.setAttribute("classroomId", classroomId);
            req.getRequestDispatcher("/WEB-INF/view/deleteClassroom.jsp").forward(req, resp);
            return;
        } else if( classroomId != 0 && fromPage.equals("deleteClassroom")){
            timeTableService.deleteByClassroom(classroomId);
            req.setAttribute("message", "All timetable where used this classroom deleted successfully. You can delete classroom now!" );
            req.setAttribute("classroomId", classroomId);
            req.getRequestDispatcher("/WEB-INF/view/deleteClassroom.jsp").forward(req, resp);
            return;
        }
        timeTableService.delete(id);
        req.setAttribute("message", TIMETABLE_DELETE_MESSAGE);
        List<TimeTable> timeTables = timeTableService.getAll();
        req.setAttribute("timeTables", timeTables);

        req.getRequestDispatcher("/WEB-INF/view/timetable.jsp").forward(req, resp);
    }
}
