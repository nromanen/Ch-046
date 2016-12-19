package com.ss.schedule.controllers.schedule;

import com.ss.schedule.model.*;
import com.ss.schedule.services.PairService;
import com.ss.schedule.services.TeacherService;
import com.ss.schedule.services.TimeTableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 16.12.16.
 */
@WebServlet("/teacherSchedule")
public class TeacherScheduleController extends HttpServlet{

    TimeTableService timeTableService = new TimeTableService();
    PairService pairService = new PairService();
    TeacherService teacherService = new TeacherService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Teacher> teachers = teacherService.getAll();
        req.setAttribute("isResult", false);
        req.setAttribute("teachers", teachers);
        req.getRequestDispatcher("/WEB-INF/view/teacherSchedule.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long teacherId = Long.parseLong(req.getParameter("teacher"));
        long oddnesId = Long.parseLong(req.getParameter("oddnes"));

        long maxPair = timeTableService.getMaxTeacherPair(teacherId, oddnesId);

        List<TimeTable> timeTables1 = timeTableService.getByTeacherDayOddnes(teacherId, 1, oddnesId);
        List<TimeTable> timeTables2 = timeTableService.getByTeacherDayOddnes(teacherId, 2, oddnesId);
        List<TimeTable> timeTables3 = timeTableService.getByTeacherDayOddnes(teacherId, 3, oddnesId);
        List<TimeTable> timeTables4 = timeTableService.getByTeacherDayOddnes(teacherId, 4, oddnesId);
        List<TimeTable> timeTables5 = timeTableService.getByTeacherDayOddnes(teacherId, 5, oddnesId);



        List<Pair> pairs = pairService.getPair(maxPair);


        req.setAttribute("isResult", true);
        req.setAttribute("timeTable1", timeTables1);
        req.setAttribute("timeTable2", timeTables2);
        req.setAttribute("timeTable3", timeTables3);
        req.setAttribute("timeTable4", timeTables4);
        req.setAttribute("timeTable5", timeTables5);
        req.setAttribute("pairs", pairs);
        req.setAttribute("teacher", teacherService.getById(teacherId));
        req.setAttribute("oddnes", OddnessOfWeek.getById(oddnesId));

        req.getRequestDispatcher("/WEB-INF/view/teacherSchedule.jsp").forward(req, resp);
    }
}
