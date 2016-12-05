package com.ss.schedule.controllers;


import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/$", "/schedule"}, loadOnStartup = 1)
public class ScheduleController extends HttpServlet {

    private ClassroomDao classroomDao = new ClassroomDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                List<Group> groups = GroupsBundle.getAll();
                System.out.println("Before sending Groups:" + groups);
                req.setAttribute("isResult", false);
                req.setAttribute("groups", groups);
                req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String[] subject = req.getParameterValues("subject");
        String[] group = req.getParameterValues("group");

        //System.out.println(subject);
       //System.out.println(group);

        SubjectType type = GroupsBundle.getSubjectById(Long.parseLong(subject[0].trim())).getType();
        long capacity = GroupsBundle.getGroupById(Long.parseLong(group[0])).getCount();

        List<Classroom> classrooms =  classroomDao.getByTypeAndCapacity(type, capacity);
//        System.out.println(capacity);
//        System.out.println(type);
//        System.out.println(classrooms);
        if(classrooms.size() != 0) {

            Classroom firstClassroom = classrooms.get(0);
            req.setAttribute("currentClassroom", firstClassroom);
            req.setAttribute("classrooms", classrooms);
        }

        DayOfWeek[] days = DayOfWeek.values();
        Pair[] pairs = Pair.values();
        OddnessOfWeek[] oddness = OddnessOfWeek.values();
        req.setAttribute("isResult", true);
        List<Teacher> teachers = GroupsBundle.getTeachersBySubject(GroupsBundle.getSubjectById(Long.parseLong(subject[0])));
        System.out.println(teachers);
        req.setAttribute("subject", GroupsBundle.getSubjectById(Long.parseLong(subject[0].trim())));
        req.setAttribute("teachers", teachers);
        req.setAttribute("pairs", pairs);
        req.setAttribute("days", days);
        req.setAttribute("oddness", oddness);
        req.setAttribute("currentOddness", OddnessOfWeek.ALL);

        req.setAttribute("group",GroupsBundle.getGroupById(Long.parseLong(group[0])));
        req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);
    }

}
