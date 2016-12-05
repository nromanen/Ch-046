package com.ss.schedule.controllers;

import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.model.*;
import com.ss.schedule.validator.TimeTableErrors;
import com.ss.schedule.validator.TimeTableValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by rmochetc on 02.12.2016.
 */

@WebServlet(urlPatterns = "/pair", loadOnStartup = 1)
public class PairController  extends HttpServlet {

    private ClassroomDao classroomDao = new ClassroomDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//
//        List<Group> groups = GroupsBundle.getAll();
//        req.setAttribute("groups", groups);
//        System.out.println(groups);
//        req.setAttribute("isResult", false);
//        req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {








        String[] subject = req.getParameterValues("subjectAdd");
        String[] group = req.getParameterValues("groupAdd");
        String[] day = req.getParameterValues("day");
        String[] pair = req.getParameterValues("pair");
        String[] oddnes = req.getParameterValues("oddnes");
        String[] classroom = req.getParameterValues("classroom");
        String[] teacher = req.getParameterValues("teacher");

        TimeTable timeTable = new TimeTable();
        timeTable.setDay(DayOfWeek.valueOf(day[0]));
        timeTable.setOddnessOfWeek(OddnessOfWeek.valueOf(oddnes[0]));
        timeTable.setGroup(GroupsBundle.getGroupById(Long.parseLong( group[0])));
        timeTable.setPair(Pair.valueOf(pair[0]));
        timeTable.setClassroom(classroomDao.getById(Long.parseLong(classroom[0])));
        timeTable.setSubject(GroupsBundle.getSubjectById(Long.parseLong(subject[0])));
        timeTable.setTeacher(GroupsBundle.getTeacherById(Long.parseLong(teacher[0])));

        System.out.println(timeTable);

        TimeTableValidator validator = new TimeTableValidator(timeTable);
        boolean isValid = validator.validation();
        TimeTableErrors errors = validator.getTimeTableErrors();

        if(isValid){
            req.setAttribute("timeTable", timeTable);
            GroupsBundle.addTimeTable(timeTable);
            req.getRequestDispatcher("/WEB-INF/view/timetable.jsp").forward(req, resp);
        } else
        {
            req.setAttribute("errors", errors);

            List<Classroom> classrooms =  classroomDao.getByTypeAndCapacity(timeTable.getSubject().getType(), timeTable.getGroup().getCount());

            if(classrooms.size() != 0) {

                Classroom firstClassroom = timeTable.getClassroom();
                req.setAttribute("classrooms", classrooms);
                req.setAttribute("currentClassroom", firstClassroom);
            }

            DayOfWeek[] days = DayOfWeek.values();
            Pair[] pairs = Pair.values();
            OddnessOfWeek[] oddness = OddnessOfWeek.values();
            req.setAttribute("isResult", true);
            List<Teacher> teachers = GroupsBundle.getTeachersBySubject(timeTable.getSubject());
            req.setAttribute("subject", GroupsBundle.getSubjectById(Long.parseLong(subject[0].trim())));
            req.setAttribute("teachers", teachers);
            req.setAttribute("currentTeacher", timeTable.getTeacher());
            req.setAttribute("currentDay", timeTable.getDay());
            req.setAttribute("currentPair", timeTable.getPair());
            req.setAttribute("pairs", pairs);
            req.setAttribute("days", days);
            req.setAttribute("oddness", oddness);
            req.setAttribute("currentOddness", timeTable.getOddnessOfWeek());

            req.setAttribute("group",timeTable.getGroup());
            req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);


        }




    }
}
