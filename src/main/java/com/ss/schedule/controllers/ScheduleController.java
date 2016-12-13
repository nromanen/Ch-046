package com.ss.schedule.controllers;


import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.dao.TimeTableDao;
import com.ss.schedule.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/index.html", "/schedule"})
public class ScheduleController extends HttpServlet {

    private ClassroomDao classroomDao = new ClassroomDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GroupDao groupDao = new GroupDao();
        //List<Group> groups = groupDao.getAll();
        List<Group> groups = GroupsBundle.getAll();
        System.out.println("Before sending Groups:" + groups);
        req.setAttribute("isResult", false);
        req.setAttribute("groups", groups);
        req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        long groupId = Long.parseLong(req.getParameter("group"));
        long subjectId = Long.parseLong(req.getParameter("subject"));


        TimeTableDao timeTableDao = new TimeTableDao();
        if (timeTableDao.isTimeTable(subjectId, groupId)) {
            System.out.println("Is timeTable");
            req.setAttribute("warningMessage", "<b>Warning!</b> This TimeTable is in schedule now." +
                    "Are you sure yo want to add it again?");
        } else {
            System.out.println("No timeTable");
        }

        SubjectType type = GroupsBundle.getSubjectById(subjectId).getType();
        Subject subject1 = GroupsBundle.getSubjectById(subjectId);
        long capacity = GroupsBundle.getGroupById(groupId).getCount();

        List<Group> stream;
        if (type == SubjectType.LECTURE) {
            System.out.println("type" + type);
            stream = GroupsBundle.getGroupsBySubject(subject1);
            System.out.println("streM " + stream);
            if(!stream.isEmpty()){
                String streamGroup = "" ;
                for (Group g: stream){
                    streamGroup += g.getName() + "; ";
                }
                System.out.println(streamGroup);
                req.setAttribute("warningStreamMessage", "<b>Warning!</b> This is Stream Subject." +
                        " TimeTable will be create for all stream's groups " + streamGroup);
            }
        }


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
        List<Teacher> teachers = GroupsBundle.getTeachersBySubject(GroupsBundle.getSubjectById(subjectId));
        System.out.println(teachers);
        req.setAttribute("subject", subject1);
        req.setAttribute("teachers", teachers);
        req.setAttribute("pairs", pairs);
        req.setAttribute("days", days);
        req.setAttribute("oddness", oddness);
        req.setAttribute("currentOddness", OddnessOfWeek.ALL);

        req.setAttribute("group",GroupsBundle.getGroupById(groupId));
        req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);
    }

}
