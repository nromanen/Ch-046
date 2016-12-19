package com.ss.schedule.controllers.schedule;

import com.ss.schedule.dao.*;
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
import java.util.Collections;
import java.util.List;

/**
 * Created by rmochetc on 02.12.2016.
 */

@WebServlet(urlPatterns = "/pair", loadOnStartup = 1)
public class PairController  extends HttpServlet {

    private ClassroomService classroomService = new ClassroomService();
    private TeacherService teacherService = new TeacherService();
    private GroupService groupService = new GroupService();
    private SubjectService subjectService = new SubjectService();
    private TimeTableService timeTableService = new TimeTableService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/index.html");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        String[] stream = req.getParameterValues("stream");
        String[] subject = req.getParameterValues("subjectAdd");

        List<TimeTableErrors> errors = new ArrayList<>();
        TimeTable timeTable = new TimeTable();
        List<TimeTable> streamTimeTables = new ArrayList<>();

        int capacity = 0;



        boolean isValid = true;
        if (stream!= null){
            String streamGroup = "" ;

            TimeTable localTimeTable = new TimeTable();
            for (String grIg: stream){
                localTimeTable = getTimeTable(req, Long.parseLong(grIg));
                streamTimeTables.add(localTimeTable);
                TimeTableValidator validator = new TimeTableValidator(localTimeTable);
                if (!validator.validation()){
                    errors.add(validator.getTimeTableErrors());
                    isValid = false;
                }
                capacity += localTimeTable.getGroup().getCount();
                streamGroup += localTimeTable.getGroup().getName() + "; ";
            }

            req.setAttribute("warningStreamMessage", "<b>Warning!</b> This is Stream Subject." +
                    " TimeTable will be create for all stream's groups " + streamGroup);

            req.setAttribute("groupName","Stream (" + streamGroup + ")");
            req.setAttribute("group", null);
            req.setAttribute("stream", stream);
            req.setAttribute("currentTeacher", localTimeTable.getTeacher());
            req.setAttribute("currentDay", localTimeTable.getDay());
            req.setAttribute("currentPair", localTimeTable.getPair());
            req.setAttribute("currentOddness", localTimeTable.getOddnessOfWeek());
            req.setAttribute("currentClassroom", localTimeTable.getClassroom());
            req.setAttribute("capacity", capacity);

        } else {
            timeTable = getTimeTable(req, 0);
            TimeTableValidator validator = new TimeTableValidator(timeTable);
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
        }

        if(errors.size()>1){
            for (int i = 1; i < errors.size(); i++){
                if(errors.get(i).getClassroomError() != null && errors.get(i).getClassroomError().equals(errors.get(0).getClassroomError())){
                    errors.get(i).setClassroomError(null);
                }
                if(errors.get(i).getTeacherError() != null && errors.get(i).getTeacherError().equals(errors.get(0).getTeacherError())){
                    errors.get(i).setTeacherError(null);
                }
            }
        }

        if(isValid){
            if (stream != null){
                for (TimeTable t:  streamTimeTables) {
                    timeTableService.add(t);
                }
                    List<Group> groups = groupService.getAll();
                    req.setAttribute("isResult", false);
                    req.setAttribute("groups", groups);
                    req.setAttribute("message", "Timetables added successfully");
                    req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);
            } else {
                timeTableService.add(timeTable);
                List<Group> groups = groupService.getAll();
                req.setAttribute("isResult", false);
                req.setAttribute("groups", groups);
                req.setAttribute("message", "Timetable added successfully");
                req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errors", errors);

            List<Classroom> classrooms =  classroomService.getByTypeAndCapacity(subjectService.getById(Long.parseLong(req.getParameterValues("subjectAdd")[0])).getType(), capacity);

            if(classrooms.size() != 0) {

                req.setAttribute("classrooms", classrooms);

            }

            req.setAttribute("stream", stream);
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


            req.setAttribute("group",timeTable.getGroup());
            req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);
        }

    }

    private  TimeTable getTimeTable(HttpServletRequest req, long groupId){

        TimeTable timeTable = new TimeTable();
        if (groupId != 0){
            timeTable.setGroup(groupService.getById(groupId));
        } else{
            String[] group = req.getParameterValues("groupAdd");
            timeTable.setGroup(groupService.getById(Long.parseLong( group[0])));

        }
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
