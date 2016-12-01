package com.ss.schedule.controllers;


import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.SubjectType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/classrooms")
public class ClassroomsController extends HttpServlet {

    ClassroomDao classroomDao = new ClassroomDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                System.out.println("doGet 4");
                List<Group> groups = GroupsBundle.getAll();
                req.setAttribute("groups", groups);
                System.out.println(groups);
                req.setAttribute("isResult", false);
                req.getRequestDispatcher("/WEB-INF/view/classrooms.jsp").forward(req, resp);
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

            req.setAttribute("classrooms", classrooms);
        }
        req.setAttribute("isResult", true);
        req.setAttribute("subject", GroupsBundle.getSubjectById(Long.parseLong(subject[0].trim())));

        req.setAttribute("group",GroupsBundle.getGroupById(Long.parseLong(group[0])));
        req.getRequestDispatcher("/WEB-INF/view/classrooms.jsp").forward(req, resp);
    }

}
