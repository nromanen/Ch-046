package com.ss.schedule.servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.schedule.dao.*;
import com.ss.schedule.institute.ClassroomManager;
import com.ss.schedule.institute.Util;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.model.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by oleg on 03.12.16.
 */
@WebServlet("/populateTimetable")
public class PopulateTimetableServlet extends HttpServlet {
    PairsDao pairsDao=new PairsDao();
    List<Pair> pairs = pairsDao.getAll();
    List<DayOfWeek> dayOfWeeks= new DayOfWeekDao().getAll();
    List<OddnessOfWeek> oddnessOfWeeks= new OddnessOfWeekDao().getAll();
    List<Group> groups = new GroupDao().getAll();
    private GroupDao groupDao=new GroupDao();
    private GroupsSubjectsDao groups_subjectsDao=new GroupsSubjectsDao();
    private JdbcSubjectDao subjectDao=new JdbcSubjectDao();
    private ClassroomManager classroomManager=new ClassroomManager();
    private Group group=null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int groupId = Integer.parseInt(req.getParameter("group"));
        StudentCommunity studentCommunity=groupDao.getStudentCommunityById(groupId);
         group = groupDao.getById(groupId);
        int subgroupId;
        String subgroup = req.getParameter("subgroup");
        if(!req.getParameter("subgroup").equals("0")) {
            subgroupId = Integer.parseInt(req.getParameter("subgroup"));
            studentCommunity=groupDao.getStudentCommunityById(subgroupId);
        }
        int subject_id=Integer.parseInt(req.getParameter("subject"));
        Subject subject = subjectDao.getById(subject_id);
        Util util=new Util();
        util.setSubjects(subjectDao.getAll());
        util.setGroups(groupDao.getAll());
        util.setClassrooms(new ClassroomDao().getAll());
        List<Classroom> listOfAvailableRooms=new ArrayList<>();
        if (subject.getType().getMaxStudentAmount()==0)
        {
            Stream stream=new Stream();
            int count=0;
            List<Group> groupsOfStream = groups_subjectsDao.getGroupsOfStream(subject);
            for (Group group :
                    groupsOfStream) {
                count+=group.getCount();
                stream.getGroups().add(group);
            }
            stream.setCount(count);
            listOfAvailableRooms=classroomManager.getListOfAvailableRooms(subject,stream);
            req.setAttribute("streamGroups",groupsOfStream);
        } else
            listOfAvailableRooms=classroomManager.getListOfAvailableRooms(subject,studentCommunity);

        req.setAttribute("isResult",true);
        req.setAttribute("pairs",pairs);
        req.setAttribute("days_of_week",dayOfWeeks);
        req.setAttribute("oddneses_of_week",oddnessOfWeeks);
        req.setAttribute("groups",groups);
        req.setAttribute("group",group);
        req.setAttribute("subject",subject);
        req.setAttribute("studentCommunity",studentCommunity);
        req.setAttribute("teachers",new TeachersSubjectsDao().getTeachersOfSubject(subject));
        req.setAttribute("classrooms",listOfAvailableRooms);
        req.setAttribute("subgroup",studentCommunity);
        req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int groupId;
        ObjectMapper objectMapper=new ObjectMapper();
        String asString="";
        String reqParameter = req.getParameter("group");
        if(req.getParameter("group")!=null) {
             groupId = Integer.parseInt(req.getParameter("group"));
            this.group = groupDao.getById(groupId);
             asString = objectMapper.writeValueAsString(this.group);
        }
        req.setAttribute("pairs",pairs);
        req.setAttribute("isResult", false);
        req.setAttribute("days_of_week",dayOfWeeks);
        req.setAttribute("oddneses_of_week",oddnessOfWeeks);
        req.setAttribute("groups",groups);

        req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);
    }
}
