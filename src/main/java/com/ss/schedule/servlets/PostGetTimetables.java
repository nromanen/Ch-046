package com.ss.schedule.servlets;

import com.ss.schedule.dao.*;
import com.ss.schedule.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by oleg on 20.12.16.
 */
public class PostGetTimetables extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentCommunity studentCommunity;
        if (!req.getParameter("subgroup").equals("0"))
            studentCommunity=new GroupDao().getStudentCommunityById(Long.parseLong(req.getParameter("subgroup")));
        else studentCommunity=new GroupDao().getStudentCommunityById(Long.parseLong(req.getParameter("group")));
        TimeTableDao timeTableDao=new TimeTableDao();
        req.setAttribute("group",studentCommunity);
        LinkedHashMap<DayOfWeek, TimeTable[]> weeklyTimetablesForGroup = timeTableDao.getWeeklyTimetablesForGroup(
                studentCommunity, OddnessOfWeek.valueOf(req.getParameter("oddness_of_week")));
        req.setAttribute("timetables",weeklyTimetablesForGroup);
        req.setAttribute("allPairs", Pair.values());

        List<DayOfWeek> days = new DayOfWeekDao().getAll();
        req.setAttribute("days",days);
        List<Group> groups=new GroupDao().getAll();
        req.setAttribute("groups",groups);
        List<OddnessOfWeek> oddnessOfWeeks = new OddnessOfWeekDao().getAll();
        req.setAttribute("oddnesses",oddnessOfWeeks);
        List<Classroom> allClassrooms = new ClassroomDao().getAll();
        req.setAttribute("classrooms",allClassrooms);
        req.setAttribute("teachers",new TeachersDao().getAll());
        req.setAttribute("pairs",Pair.values());
        req.setAttribute("timetables1",new TimeTableDao().getAll());
        req.getRequestDispatcher("/WEB-INF/view/daily_timetable_for_group.jsp").forward(req,resp);
    }
}
