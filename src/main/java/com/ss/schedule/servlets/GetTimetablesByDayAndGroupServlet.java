package com.ss.schedule.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.dao.TimeTableDao;
import com.ss.schedule.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by oleg on 18.12.16.
 */
@WebServlet("/timetablesOfGroupByDay")
public class GetTimetablesByDayAndGroupServlet extends HttpServlet {
    @Override
    //get precise timetable
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int timetableId= Integer.parseInt(req.getParameter("timetable"));
        TimeTable timeTable = new TimeTableDao().getById(timetableId);
        ObjectMapper objectMapper=new ObjectMapper();
        String asString = objectMapper.writeValueAsString(timeTable);
        resp.getWriter().write(asString);
    }

    @Override
    //getDaylyTimetables
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentCommunity group=new GroupDao().getStudentCommunityById(Long.parseLong(req.getParameter("group")));
        Map<String, String[]> parameterMap = req.getParameterMap();
        TimeTable[] groups = new TimeTableDao().getDayTimetableOfGroup(
                group,
                DayOfWeek.valueOf(req.getParameter("day_of_week")),
                OddnessOfWeek.valueOf(req.getParameter("oddness"))
                );
        ObjectMapper objectMapper=new ObjectMapper();
        String asString = objectMapper.writeValueAsString(groups);
        resp.getWriter().write(asString);
    }
}
