package com.ss.schedule.servlets;

import com.ss.schedule.dao.*;
import com.ss.schedule.exceptions.TimetableException;
import com.ss.schedule.institute.TimeTableManager;
import com.ss.schedule.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by oleg on 12.12.16.
 */
@WebServlet("/addTimetable")
public class AddTimetableServlet extends HttpServlet {
    GroupDao groupDao=new GroupDao();
    TimeTableDao timeTableDao=new TimeTableDao();
    TimeTableManager timeTableManager=new TimeTableManager();
    Groups_subjectsDao groups_subjectsDao=new Groups_subjectsDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Enumeration<String> attributeNames = req.getAttributeNames();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Enumeration<String> attributeNames = req.getAttributeNames();
        int group_id= Integer.parseInt(req.getParameter("groupAdd"));
        Group group = groupDao.getById(group_id);
        long stud_comm_id=Long.parseLong(req.getParameter("studentCommunityAdd"));
        Stream stream=new Stream();
        StudentCommunity studentCommunity=groupDao.getStudentCommunityById(stud_comm_id);
        Subject subject=new JdbcSubjectDao().getById(Long.parseLong(req.getParameter("subjectAdd")));
        TimeTable timeTable=new TimeTable();
        timeTable.setClassroom(new ClassroomDao().getById(Long.parseLong(req.getParameter("classroom"))));
        timeTable.setDay(DayOfWeek.valueOf(req.getParameter("day")));
        timeTable.setSubject(new JdbcSubjectDao().getById(Long.parseLong(req.getParameter("subjectAdd"))));
        timeTable.setOddnessOfWeek(OddnessOfWeek.valueOf(req.getParameter("oddnessAdd")));
        timeTable.setPair(Pair.valueOf(req.getParameter("pair")));
        try {
            timeTable.setTeacher(new TeachersDao().getById(Long.parseLong(req.getParameter("teacher"))));
        } catch (NumberFormatException e){}
        if (subject.getType().getMaxStudentAmount()==0){
             stream.setGroups(groups_subjectsDao.getGroupsOfStream(subject));
             timeTable.setStudentCommunity(stream);
            try {
                timeTableManager.addTimetable(timeTable);
            } catch (TimetableException e){
                req.setAttribute("error",e.toString());
            }
        } else{
            timeTable.setStudentCommunity(studentCommunity);
            try {
                timeTableManager.addTimetable(timeTable);
            } catch (TimetableException e){
                req.setAttribute("error",e.toString());
            }
        }

        String error = (String)req.getAttribute("error");
        req.getRequestDispatcher("WEB-INF/view/success.jsp").forward(req,resp);
    }

}


