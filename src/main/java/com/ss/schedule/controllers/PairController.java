package com.ss.schedule.controllers;

import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.model.DayOfWeek;
import com.ss.schedule.model.OddnessOfWeek;
import com.ss.schedule.model.Pair;
import com.ss.schedule.model.TimeTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        System.out.println("Subj" + subject[0]);
        System.out.println("Group" + group[0]);
        System.out.println("Day" + day[0]);
        System.out.println("Pair" + pair[0]);
        System.out.println("Oddn" + oddnes[0]);
        System.out.println("Class" + classroom[0]);

        TimeTable timeTable = new TimeTable();
        timeTable.setDay(DayOfWeek.valueOf(day[0]));
        timeTable.setOddnessOfWeek(OddnessOfWeek.valueOf(oddnes[0]));
        timeTable.setGroup(GroupsBundle.getGroupById(Long.parseLong( group[0])));
        timeTable.setPair(Pair.valueOf(pair[0]));
        timeTable.setClassroom(classroomDao.getById(Long.parseLong(classroom[0])));
        timeTable.setSubject(GroupsBundle.getSubjectById(Long.parseLong(subject[0])));

        System.out.println(timeTable);


        req.setAttribute("timeTable", timeTable);

        req.getRequestDispatcher("/WEB-INF/view/timetable.jsp").forward(req, resp);
    }
}
