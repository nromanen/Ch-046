package com.ss.schedule.controllers;


import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.dao.SubjectTypeDao;
import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.SubjectType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/classroomUpdate")
public class ClassroomUpdateController extends HttpServlet {

    ClassroomDao classroomDao  = new ClassroomDao();
    SubjectTypeDao subjectTypeDao = new SubjectTypeDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = 0L;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NullPointerException e){
            System.out.println("parse id");
            e.printStackTrace();
        }

        Classroom classroom = new Classroom();
        try {
            classroom = classroomDao.getById(id);
        } catch (Exception e){
            System.out.println("get classroom from db");
            e.printStackTrace();
        }

        List<SubjectType> types = subjectTypeDao.getAll();
        System.out.println(types);

        req.setAttribute("types", types);
        req.setAttribute("classroom", classroom);
        req.setAttribute("link", "classroomUpdate");
        try {
            req.setAttribute("message",  "Edit classroom " + classroom.getName());
        } catch (NullPointerException e){
            req.setAttribute("message", "Create new Classroom!!");
        }
        req.getRequestDispatcher("/WEB-INF/view/classroomUpdate.jsp").forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Classroom classroom = getClassroom(req);
        if (classroom.getId() != 0) {
            classroomDao.update(classroom);
        }  else {
            classroomDao.add(classroom);
        }
        req.setAttribute("isMessage", true);

        req.setAttribute("message", classroom.getId()!=0?"Classroom " + classroom.getName() + " update successfully!" : "New classroom " + classroom.getName() + " create successfully!");
        List<Classroom> classrooms = classroomDao.getAll();
        req.setAttribute("classrooms", classrooms);
        req.getRequestDispatcher("/WEB-INF/view/classrooms.jsp").forward(req, resp);
    }

    private Classroom getClassroom(HttpServletRequest req) {
        Classroom classroom = new Classroom();

        try {
            classroom.setName(req.getParameter("name"));
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        try {
            classroom.setCapacity(Integer.parseInt(req.getParameter("capacity")));
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        try {
            classroom.setDescription(req.getParameter("description"));
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        try {
            classroom.setId(Long.parseLong(req.getParameter("id")));
        } catch (NullPointerException | NumberFormatException e){
            e.printStackTrace();
            classroom.setId(0);
        }

        String[] types = req.getParameterValues("types");
        List<SubjectType> classroomTypes = new ArrayList<>();

        for (String t: types) {
            SubjectType type = SubjectType.valueOf(t);

            classroomTypes.add(type);
        }

        classroom.setTypes(classroomTypes);
        return classroom;
    }
//
//        req.setAttribute("employee", employee);
//        req.setAttribute("errors", validator.getErrors());
//        req.setAttribute("data", dList);
//        req.setAttribute("positions", positions);
//        req.setAttribute("message", "Create new Employee");
//        req.setAttribute("link", "employeeUpdate");
//        req.setAttribute("message", "Edit profile of ");
//        req.getRequestDispatcher("/WEB-INF/jsps/employeeUpdate.jsp").forward(req, resp);



}
