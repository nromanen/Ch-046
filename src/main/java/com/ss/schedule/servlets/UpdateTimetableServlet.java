package com.ss.schedule.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.schedule.dao.*;
import com.ss.schedule.model.*;

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
@WebServlet("/updateTimetable")
public class UpdateTimetableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DayOfWeek> all = new DayOfWeekDao().getAll();
        req.setAttribute("days",all);
        List<Group> groups=new GroupDao().getAll();
        req.setAttribute("groups",groups);
        List<OddnessOfWeek> oddnessOfWeeks = new OddnessOfWeekDao().getAll();
        req.setAttribute("oddnesses",oddnessOfWeeks);
        List<Classroom> allClassrooms = new ClassroomDao().getAll();
        req.setAttribute("allClassrooms",allClassrooms);
        req.setAttribute("allPairs",Pair.values());
        req.getRequestDispatcher("/WEB-INF/view/updateTimetable.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TimeTableDao timeTableDao = new TimeTableDao();
        TimeTable timeTable=new TimeTable();
        timeTable.setId(Long.parseLong(req.getParameter("table_id")));
        timeTable.setDay(DayOfWeek.valueOf(req.getParameter("day_of_week")));
        timeTable.setOddnessOfWeek(OddnessOfWeek.valueOf(req.getParameter("oddness")));
        timeTable.setSubject(new JdbcSubjectDao().getById(Long.parseLong(req.getParameter("subject_box"))));
        timeTable.setTeacher(new TeachersDao().getById(Long.parseLong(req.getParameter("teacher_id"))));
        timeTable.setClassroom(new ClassroomDao().getById(Long.parseLong(req.getParameter("classroom"))));
        timeTable.setPair(Pair.valueOf(req.getParameter("pair")));
        GroupDao groupDao=new GroupDao();
        if(!req.getParameter("subgroup").equals("0"))
        timeTable.setStudentCommunity(groupDao.getStudentCommunityById(Long.parseLong(req.getParameter("subgroup"))));
        else timeTable.setStudentCommunity(groupDao.getStudentCommunityById(Long.parseLong(req.getParameter("group"))));
        TimeTable update = timeTableDao.update(timeTable);
        ObjectMapper objMapeer=new ObjectMapper();
        String asString = objMapeer.writeValueAsString(update);
//        resp.getWriter().write(asString);
        List<DayOfWeek> all = new DayOfWeekDao().getAll();
        req.setAttribute("days",all);
        List<Group> groups=new GroupDao().getAll();
        req.setAttribute("groups",groups);
        List<OddnessOfWeek> oddnessOfWeeks = new OddnessOfWeekDao().getAll();
        req.setAttribute("oddnesses",oddnessOfWeeks);
        List<Classroom> allClassrooms = new ClassroomDao().getAll();
        req.setAttribute("allClassrooms",allClassrooms);
        req.setAttribute("allPairs",Pair.values());
        req.getRequestDispatcher("/WEB-INF/view/daily_timetable_for_group.jsp");
    }
}
