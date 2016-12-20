package com.ss.schedule.servlets.group;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.dao.GroupsSubjectsDao;
import com.ss.schedule.model.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by oleg on 16.12.16.
 */
@WebServlet("/subjectsOfStudCommunity")
public class SubjectsOfGroupOrSubServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GroupsSubjectsDao groups_subjectsDao=new GroupsSubjectsDao();
        long group_id= Long.parseLong(req.getParameter("group_id"));
        List<Subject> subjectsForGroup = groups_subjectsDao.getSubjectsForGroup(new GroupDao().getById(group_id));
        ObjectMapper objectMapper=new ObjectMapper();
        String asString = objectMapper.writeValueAsString(subjectsForGroup);
        resp.getWriter().write(asString);
    }
}
