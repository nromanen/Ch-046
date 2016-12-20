package com.ss.schedule.servlets;

import com.ss.schedule.dao.*;
import com.ss.schedule.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oleg on 18.12.16.
 */
@WebServlet("/deleteTimetable")
public class DeleteTimetableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DayOfWeek> all = new DayOfWeekDao().getAll();
        req.setAttribute("days",all);
        List<Group> groups=new GroupDao().getAll();
        req.setAttribute("groups",groups);
        List<OddnessOfWeek> oddnessOfWeeks = new OddnessOfWeekDao().getAll();
        req.setAttribute("oddnesses",oddnessOfWeeks);
        req.getRequestDispatcher("/WEB-INF/view/deleteTimetable.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        TimeTable timeTable=new TimeTableDao().getById(Long.parseLong(req.getParameter("timetable")));
        req.setAttribute("oddness_of_week",timeTable.getOddnessOfWeek());

        List<DayOfWeek> all = new DayOfWeekDao().getAll();
        req.setAttribute("days",all);
        List<Group> groups=new GroupDao().getAll();
        req.setAttribute("groups",groups);
        List<OddnessOfWeek> oddnessOfWeeks = new OddnessOfWeekDao().getAll();
        req.setAttribute("oddnesses",oddnessOfWeeks);
        List<Classroom> allClassrooms = new ClassroomDao().getAll();
        req.setAttribute("allClassrooms",allClassrooms);
        req.setAttribute("allPairs", Pair.values());

        StudentCommunity studentCommunity=timeTable.getStudentCommunity();
        TimeTableDao timeTableDao=new TimeTableDao();
        req.setAttribute("group",studentCommunity);
        long time_id= Long.parseLong(req.getParameter("timetable"));
        boolean delete = new TimeTableDao().delete(time_id);

        LinkedHashMap<DayOfWeek, TimeTable[]> weeklyTimetablesForGroup = timeTableDao.getWeeklyTimetablesForGroup(
                studentCommunity, (timeTable.getOddnessOfWeek()));
        req.setAttribute("timetables",weeklyTimetablesForGroup);
        req.setAttribute("allPairs", Pair.values());

        req.getRequestDispatcher("/WEB-INF/view/daily_timetable_for_group.jsp").forward(req,resp);
    }
}
