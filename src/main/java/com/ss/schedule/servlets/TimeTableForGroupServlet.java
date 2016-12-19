package com.ss.schedule.servlets;

import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.dao.TimeTableDao;
import com.ss.schedule.institute.TimeTableManager;
import com.ss.schedule.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oleg on 01.12.16.
 */
@WebServlet("/forGroup")
public class TimeTableForGroupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long group_id=Long.parseLong(req.getParameter("group"));
        TimeTableDao timeTableDao=new TimeTableDao();
        Group group = new GroupDao().getById(group_id);
        req.setAttribute("group",group);
        TimeTableManager timeTableManager=new TimeTableManager();
        Map<String, String[]> parameterMap = req.getParameterMap();
        if (req.getParameter("day_of_week").equals("")){
            LinkedHashMap<DayOfWeek, TimeTable[]> weeklyTimetablesForGroup = timeTableDao.getWeeklyTimetablesForGroup(
                    group, OddnessOfWeek.valueOf(req.getParameter("oddness_of_week")));
            req.setAttribute("timetables",weeklyTimetablesForGroup);
        } else
        {
            TimeTable[] timetablesForGroup = timeTableDao.getDayTimetableOfGroup(
                    group,
                    DayOfWeek.valueOf(req.getParameter("day_of_week")),
                    OddnessOfWeek.valueOf(req.getParameter("oddness_of_week")));

            req.setAttribute("timetables",timetablesForGroup);
        }
        req.setAttribute("allPairs", Pair.values());
        req.getRequestDispatcher("/WEB-INF/view/daily_timetable_for_group.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
