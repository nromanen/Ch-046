package com.ss.schedule.controllers.schedule;


import com.ss.schedule.model.*;
import com.ss.schedule.services.ClassroomService;
import com.ss.schedule.services.GroupService;
import com.ss.schedule.services.SubjectService;
import com.ss.schedule.services.TeacherService;

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

    private GroupService groupService = new GroupService();
    private TeacherService teacherService = new TeacherService();
    private SubjectService subjectService = new SubjectService();
    private ClassroomService classroomService = new ClassroomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Group> groups = groupService.getAll();
        req.setAttribute("isResult", false);
        req.setAttribute("groups", groups);
        req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long groupId = Long.parseLong(req.getParameter("group"));
        long subjectId = Long.parseLong(req.getParameter("subject"));


        SubjectType type = subjectService.getById(subjectId).getType();
        Subject subject = subjectService.getById(subjectId);
        int capacity = groupService.getById(groupId).getCount();

        req.setAttribute("group",groupService.getById(groupId));
        req.setAttribute("groupName","Group " + groupService.getById(groupId).getName());

        List<Long> stream = new ArrayList<>();
        if (type == SubjectType.LECTURE) {
                capacity = 0;
                String streamGroup = "" ;
                for (Group g: groupService.getGroupsBySubject(subject)){
                    stream.add(g.getId());
                    streamGroup += g.getName() + "; ";
                    capacity += g.getCount();
                }

                req.setAttribute("warningStreamMessage", "<b>Warning!</b> This is Stream Subject." +
                        " TimeTable will be create for all stream's groups " + streamGroup);


            req.setAttribute("groupName","Stream (" + streamGroup + ")");
            req.setAttribute("group", null);
            req.setAttribute("stream", stream);

        }


        List<Classroom> classrooms =  classroomService.getByTypeAndCapacity(type, capacity);

        if(classrooms.size() != 0) {

            Classroom firstClassroom = classrooms.get(0);
            req.setAttribute("currentClassroom", firstClassroom);
            req.setAttribute("classrooms", classrooms);
        }

        DayOfWeek[] days = DayOfWeek.values();
        Pair[] pairs = Pair.values();
        OddnessOfWeek[] oddness = OddnessOfWeek.values();
        req.setAttribute("isResult", true);
        req.setAttribute("capacity", capacity);
        List<Teacher> teachers = teacherService.getTeachersBySubject(subjectService.getById(subjectId));
        req.setAttribute("subject", subject);
        req.setAttribute("teachers", teachers);
        req.setAttribute("pairs", pairs);
        req.setAttribute("days", days);
        req.setAttribute("oddness", oddness);
        req.setAttribute("currentOddness", OddnessOfWeek.ALL);


        req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);
    }

}
