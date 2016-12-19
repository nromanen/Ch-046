package com.ss.schedule.servlets;

import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.dao.JdbcSubjectDao;
import com.ss.schedule.institute.Util;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by oleg on 10.12.16.
 */
@WebServlet("/success")
public class SuccessServlet extends HttpServlet {
    private GroupDao groupDao=new GroupDao();
    private JdbcSubjectDao jdbcSubjectDao=new JdbcSubjectDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Group> groups = groupDao.getAll();
        List<Subject> subjects = jdbcSubjectDao.getAll();
        Util util=new Util();
        util.setGroups(groups);
        util.setSubjects(subjects);
        util.getGroupsSubgroupsStreams();
        for (Group group : util.getGroups()) {
            groupDao.addSubgroupsOfGroup(group);
        }
            req.getRequestDispatcher("/WEB-INF/view/success.jsp").forward(req,resp);
    }
}
