package com.ss.schedule.controllers.classroom;

import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.SubjectType;
import com.ss.schedule.services.ClassroomService;
import com.ss.schedule.validator.ClassroomErrors;
import com.ss.schedule.validator.ClassroomValidator;

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

    private static final String NEW_CLASSROOM_MESSAGE = "Create new Classroom";
    private static final String EDIT_CLASSROOM_MESSAGE = "Edit classroom ";
    private static final String UPDATE_SUCCESSFUL = "Classroom updated successfully!";
    private static final String CREATE_SUCCESSFUL = "New classroom  created successfully!";

    private ClassroomService classroomService = new ClassroomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = 0L;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NullPointerException | NumberFormatException e){
            e.printStackTrace();
        }

        Classroom classroom = new Classroom();
        if (id != 0) {
            classroom = classroomService.getClassroomById(id);
        }

        SubjectType[] types = SubjectType.values();
        req.setAttribute("types", types);
        req.setAttribute("classroom", classroom);
        req.setAttribute("link", "classroomUpdate");
        if(id != 0){
            req.setAttribute("message",  EDIT_CLASSROOM_MESSAGE + classroom.getName());
        } else {
            req.setAttribute("message", NEW_CLASSROOM_MESSAGE);
        }
        req.getRequestDispatcher("/WEB-INF/view/classroomUpdate.jsp").forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Classroom classroom = getClassroom(req);
        ClassroomValidator validator = new ClassroomValidator(classroom);
        if(!validator.validation()) {
            SubjectType[] types = SubjectType.values();
            ClassroomErrors errors = validator.getErrors();

            req.setAttribute("types", types);
            req.setAttribute("errors", errors);
            req.setAttribute("classroom", classroom);
            req.setAttribute("link", "classroomUpdate");
            req.setAttribute("message", NEW_CLASSROOM_MESSAGE);
            req.getRequestDispatcher("/WEB-INF/view/classroomUpdate.jsp").forward(req, resp);
        } else {
            if (classroom.getId() != 0) {
                classroomService.update(classroom);
            } else {
               classroom = classroomService.add(classroom);
            }

            req.setAttribute("message", classroom.getId() != 0 ? UPDATE_SUCCESSFUL : CREATE_SUCCESSFUL);
            List<Classroom> classrooms = classroomService.getAll();
            req.setAttribute("classrooms", classrooms);
            req.setAttribute("updateClassroom", classroom);
            req.getRequestDispatcher("/WEB-INF/view/classrooms.jsp").forward(req, resp);
        }
    }

    /**
     *
     * @param  req
     * create Classroom from HttpServletRequest req
     * @return Classroom
     */


    private Classroom getClassroom(HttpServletRequest req) {
        Classroom classroom = new Classroom();

        try {
            classroom.setName(req.getParameter("name"));
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        try {
            classroom.setCapacity(Integer.parseInt(req.getParameter("capacity")));
        } catch (NullPointerException | NumberFormatException e){
            classroom.setCapacity(0);
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

        try {
            String[] types = req.getParameterValues("types");
            List<SubjectType> classroomTypes = new ArrayList<>();

            for (String t : types) {
                SubjectType type = SubjectType.valueOf(t);

                classroomTypes.add(type);
            }

            classroom.setTypes(classroomTypes);
        } catch (NullPointerException e){
            e.printStackTrace();
            classroom.setTypes(null);
        }
        return classroom;
    }
}
