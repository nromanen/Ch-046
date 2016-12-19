package com.ss.schedule.servlets;

import com.ss.schedule.dao.DayOfWeekDao;
import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.dao.OddnessOfWeekDao;
import com.ss.schedule.dao.TimeTableDao;
import com.ss.schedule.model.DayOfWeek;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.OddnessOfWeek;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        long time_id= Long.parseLong(req.getParameter("timetable"));
//        if (!req.getParameter("subgroup").equals("0")){
//            time_id=Long.parseLong(req.getParameter("subgroup"));
//        } else time_id=Long.parseLong(req.getParameter("group"));
        new TimeTableDao().delete(time_id);
    }
}
