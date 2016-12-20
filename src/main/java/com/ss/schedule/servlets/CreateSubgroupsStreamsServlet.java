package com.ss.schedule.servlets;

import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.dao.JdbcSubjectDao;
import com.ss.schedule.institute.Util;
import com.ss.schedule.model.*;
import org.apache.tools.ant.taskdefs.condition.Http;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by oleg on 09.12.16.
 */
@WebServlet("/confirm")
public class CreateSubgroupsStreamsServlet extends HttpServlet {
    private GroupDao groupDao=new GroupDao();
    private JdbcSubjectDao jdbcSubjectDao=new JdbcSubjectDao();
    private boolean streamsAndSubgroupsExist=false;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Group> groups = groupDao.getAll();
        for (Group group:groups) {
            if ( group.getSubgroups().size()!=0){
                streamsAndSubgroupsExist=true;
                break;
            }
        }

        if (streamsAndSubgroupsExist){
            new GroupDao().deleteAllSubgroups();
        }
        List<Subject> subjects = jdbcSubjectDao.getAll();
        Util util=new Util();
        util.setGroups(groups);
        util.setSubjects(subjects);
        util.getGroupsSubgroupsStreams();
        for (Group group : util.getGroups()) {
            groupDao.addSubgroupsOfGroup(group);
        }

        LinkedHashMap<Subject, List<? extends StudentCommunity>> groupsSubgroupsStreams = util.getGroupsSubgroupsStreams();
        LinkedHashMap<Group,List<Subgroup>> groupSubgroupMap=new LinkedHashMap<>();
        LinkedHashMap<Subject,List<Group>> subjectGroupMap = new LinkedHashMap<>();
        for (Group group:util.getGroups()){
            groupSubgroupMap.put(group,group.getSubgroups());
        }

        for (Subject subject:subjects){
            if (subject.getType().getMaxStudentAmount()==0) {
                Stream stream = (Stream) groupsSubgroupsStreams.get(subject).get(0);
                subjectGroupMap.put(subject,stream.getGroups());
            }
        }
        if (streamsAndSubgroupsExist)
            req.setAttribute("streamsAndSubgroupsExist",true);
        else req.setAttribute("streamsAndSubgroupsExist",false);
        req.setAttribute("streams",subjectGroupMap);
        req.setAttribute("subgroups",groupSubgroupMap);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Group> groups = groupDao.getAll();
        List<Subject> subjects = jdbcSubjectDao.getAll();
        Util util=new Util();
        util.setGroups(groups);
        util.setSubjects(subjects);

        for (Group group:groups) {
            if ( group.getSubgroups().size()!=0){
                streamsAndSubgroupsExist=true;
                break;
            }
        }

        if (streamsAndSubgroupsExist){
            req.setAttribute("streamsAndSubgroupsExist",true);
        } else req.setAttribute("streamsAndSubgroupsExist",false);
        util.getGroupsSubgroupsStreams();

        LinkedHashMap<Subject, List<? extends StudentCommunity>> groupsSubgroupsStreams = util.getGroupsSubgroupsStreams();
        LinkedHashMap<Group,List<Subgroup>> groupSubgroupMap=new LinkedHashMap<>();
        LinkedHashMap<Subject,List<Group>> subjectGroupMap = new LinkedHashMap<>();
        for (Group group:util.getGroups()){
            groupSubgroupMap.put(group,group.getSubgroups());
        }

        for (Subject subject:subjects){
            if (subject.getType().getMaxStudentAmount()==0) {
                try {
                    Stream stream = (Stream) groupsSubgroupsStreams.get(subject).get(0);
                    subjectGroupMap.put(subject, stream.getGroups());
                } catch (IndexOutOfBoundsException e){}
            }
        }
        req.setAttribute("streams",subjectGroupMap);
        req.setAttribute("subgroups",groupSubgroupMap);
        req.getRequestDispatcher("/WEB-INF/view/confirm.jsp").forward(req,resp);
    }
}
