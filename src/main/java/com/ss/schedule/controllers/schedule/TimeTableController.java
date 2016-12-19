package com.ss.schedule.controllers.schedule;

/**
 * Created by Admin on 14.12.16.
 */

import com.ss.schedule.model.TimeTable;
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

    private static final String TIMETABLE_DELETE_MESSAGE= "Timetable deleted successfully!";
    private static final String TIMETABLES_DELETE_MESSAGE= "Timetables deleted successfully!";
;
    TimeTableService timeTableService = new TimeTableService();
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

        if (id != 0) {
            timeTableService.delete(id);
            req.setAttribute("message", TIMETABLE_DELETE_MESSAGE);
        } else if( classroomId != 0){
            timeTableService.deleteByClassroom(classroomId);
            req.setAttribute("message", TIMETABLES_DELETE_MESSAGE );
        }
        List<TimeTable> timeTables = timeTableService.getAll();
        req.setAttribute("timeTables", timeTables);

        req.getRequestDispatcher("/WEB-INF/view/timetable.jsp").forward(req, resp);
    }
}
