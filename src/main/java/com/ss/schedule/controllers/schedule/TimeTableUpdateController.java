package com.ss.schedule.controllers.schedule;

import com.ss.schedule.model.*;
import com.ss.schedule.services.*;
import com.ss.schedule.validator.TimeTableErrors;
import com.ss.schedule.validator.TimeTableValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 14.12.16.
 */

@WebServlet("/timetableUpdate")
public class TimeTableUpdateController extends HttpServlet {

    private ClassroomService classroomService = new ClassroomService();
    private TeacherService teacherService = new TeacherService();
    private TimeTableService timeTableService = new TimeTableService();
    private GroupService groupService = new GroupService();
    private SubjectService subjectService = new SubjectService();
    long id = 0L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        id = Long.parseLong(req.getParameter("id"));

        TimeTable timeTable = timeTableService.getById(id);
        List<Classroom> classrooms =  classroomService.getByTypeAndCapacity(timeTable.getSubject().getType(), timeTable.getGroup().getCount());
        if(classrooms.size() != 0) {
            Classroom firstClassroom = timeTable.getClassroom();
            req.setAttribute("classrooms", classrooms);
            req.setAttribute("currentClassroom", firstClassroom);
        }

        DayOfWeek[] days = DayOfWeek.values();
        Pair[] pairs = Pair.values();
        OddnessOfWeek[] oddness = OddnessOfWeek.values();
        req.setAttribute("isResult", true);
        List<Teacher> teachers = teacherService.getTeachersBySubject(timeTable.getSubject());
        req.setAttribute("subject", timeTable.getSubject());
        req.setAttribute("teachers", teachers);
        req.setAttribute("currentTeacher", timeTable.getTeacher());
        req.setAttribute("currentDay", timeTable.getDay());
        req.setAttribute("currentPair", timeTable.getPair());
        req.setAttribute("pairs", pairs);
        req.setAttribute("days", days);
        req.setAttribute("oddness", oddness);
        req.setAttribute("currentOddness", timeTable.getOddnessOfWeek());
        req.setAttribute("groupName", "Group" + timeTable.getGroup().getName());
        req.setAttribute("capacity", timeTable.getGroup().getCount());
        req.setAttribute("link", "timetableUpdate");
        req.setAttribute("group",timeTable.getGroup());
        req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] subject = req.getParameterValues("subjectAdd");
        List<TimeTableErrors> errors = new ArrayList<>();
        TimeTable timeTable;
        int capacity = 0;
        boolean isValid = true;
        timeTable = getTimeTable(req);
        TimeTableValidator validator = new TimeTableValidator(timeTable, "update", id);
        req.setAttribute("group",timeTable.getGroup());
        req.setAttribute("groupName","Group " + timeTable.getGroup().getName());
        capacity = timeTable.getGroup().getCount();
        if(!validator.validation()){
            errors.add(validator.getTimeTableErrors());
            isValid = false;
        }
        req.setAttribute("currentTeacher", timeTable.getTeacher());
        req.setAttribute("currentDay", timeTable.getDay());
        req.setAttribute("currentPair", timeTable.getPair());
        req.setAttribute("currentOddness", timeTable.getOddnessOfWeek());
        req.setAttribute("currentClassroom", timeTable.getClassroom());
        req.setAttribute("capacity", capacity);

        if(isValid){
            timeTable.setId(id);
            timeTableService.update(timeTable);
            List<Group> groups = groupService.getAll();
            id = 0L;
            req.setAttribute("isResult", false);
            req.setAttribute("groups", groups);
            req.setAttribute("message", "Timetable updated successfully");
            req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);

        } else {
            req.setAttribute("errors", errors);

            List<Classroom> classrooms =  classroomService.getByTypeAndCapacity(subjectService.getById(Long.parseLong(req.getParameterValues("subjectAdd")[0])).getType(), capacity);

            if(classrooms.size() != 0) {

                req.setAttribute("classrooms", classrooms);

            }


            DayOfWeek[] days = DayOfWeek.values();
            Pair[] pairs = Pair.values();
            OddnessOfWeek[] oddness = OddnessOfWeek.values();
            req.setAttribute("isResult", true);
            List<Teacher> teachers = teacherService.getTeachersBySubject(subjectService.getById(Long.parseLong(req.getParameterValues("subjectAdd")[0])));
            req.setAttribute("subject", subjectService.getById(Long.parseLong(subject[0].trim())));
            req.setAttribute("teachers", teachers);


            req.setAttribute("pairs", pairs);
            req.setAttribute("days", days);
            req.setAttribute("oddness", oddness);
            req.setAttribute("link", "/timetableUpdate");


            req.setAttribute("group",timeTable.getGroup());
            req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);
        }

    }

    private  TimeTable getTimeTable(HttpServletRequest req){

        TimeTable timeTable = new TimeTable();

        String[] group = req.getParameterValues("groupAdd");
        timeTable.setGroup(groupService.getById(Long.parseLong( group[0])));


        String[] subject = req.getParameterValues("subjectAdd");
        String[] day = req.getParameterValues("day");
        String[] pair = req.getParameterValues("pair");
        String[] oddnes = req.getParameterValues("oddnes");
        String[] classroom = req.getParameterValues("classroom");
        String[] teacher = req.getParameterValues("teacher");


        timeTable.setDay(DayOfWeek.valueOf(day[0]));
        timeTable.setOddnessOfWeek(OddnessOfWeek.valueOf(oddnes[0]));

        timeTable.setPair(Pair.valueOf(pair[0]));
        timeTable.setClassroom(classroomService.getById(Long.parseLong(classroom[0])));
        timeTable.setSubject(subjectService.getById(Long.parseLong(subject[0])));
        timeTable.setTeacher(teacherService.getById(Long.parseLong(teacher[0])));

        return timeTable;
    }



}
