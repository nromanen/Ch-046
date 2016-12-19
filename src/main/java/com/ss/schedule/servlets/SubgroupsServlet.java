package com.ss.schedule.servlets;

import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.dao.Groups_subjectsDao;
import com.ss.schedule.dao.JdbcSubjectDao;
import com.ss.schedule.institute.Util;
import com.ss.schedule.model.*;

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
@WebServlet("/groupsAndStreams")
public class SubgroupsServlet extends HttpServlet {
   private JdbcSubjectDao jdbcSubjectDao=new JdbcSubjectDao();
    GroupDao groupDao=new GroupDao();
    Groups_subjectsDao groups_subjectsDao=new Groups_subjectsDao();
   private boolean streamsAndSubgroupsExist=false;
    private Util util=new Util();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Group> groups = groupDao.getAll();
        List<Subject> subjects = jdbcSubjectDao.getAll();
        util.setGroups(groups);
        util.setSubjects(jdbcSubjectDao.getAll());

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
        req.setAttribute("streams",subjectGroupMap);
        req.setAttribute("subgroups",groupSubgroupMap);



        for (Group group:groups) {
            //groupDao.add(group);
            if ( group.getSubgroups().size()!=0){
                streamsAndSubgroupsExist=true;
                break;
            }
        }


        if (streamsAndSubgroupsExist)
            req.setAttribute("streamsAndSubgroupsExist",true);
        else req.setAttribute("streamsAndSubgroupsExist",false);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Group> groups = groupDao.getAll();
        List<Subject> subjects = jdbcSubjectDao.getAll();
        util.setGroups(groups);
        util.setSubjects(jdbcSubjectDao.getAll());
        LinkedHashMap<Subject, List<? extends StudentCommunity>> groupsSubgroupsStreams = util.getGroupsSubgroupsStreams();
        LinkedHashMap<Group,List<Subgroup>> groupSubgroupMap=new LinkedHashMap<>();
        LinkedHashMap<Subject,List<Group>> subjectGroupMap = new LinkedHashMap<>();
        for (Group group:util.getGroups()){
            List<Subgroup>subgroups=null;
            try {
                subgroups=group.getSubgroups();
            } catch (NullPointerException e){

            }
            groupSubgroupMap.put(group,subgroups);
        }

        for (Subject subject:subjects){
            if (subject.getType().getMaxStudentAmount()==0) {
                try {
                    Stream stream = (Stream) groupsSubgroupsStreams.get(subject).get(0);
                    subjectGroupMap.put(subject, stream.getGroups());
                } catch (IndexOutOfBoundsException e){

                }
            }
        }
        req.setAttribute("streams",subjectGroupMap);
        req.setAttribute("subgroups",groupSubgroupMap);


        for (Group group:groups) {
            //groupDao.add(group);
            if ( group.getSubgroups().size()!=0){
                streamsAndSubgroupsExist=true;
                break;
            }
        }

        if (streamsAndSubgroupsExist)
            req.setAttribute("streamsAndSubgroupsExist",true);
        else req.setAttribute("streamsAndSubgroupsExist",false);


        req.getRequestDispatcher("/WEB-INF/view/SubgroupsAndStreams.jsp").forward(req,resp);
    }
}
