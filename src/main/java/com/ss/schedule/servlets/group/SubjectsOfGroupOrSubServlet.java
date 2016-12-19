package com.ss.schedule.servlets.group;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.dao.Groups_subjectsDao;
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
 * Created by oleg on 16.12.16.
 */
@WebServlet("/subjectsOfStudCommunity")
public class SubjectsOfGroupOrSubServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Groups_subjectsDao groups_subjectsDao=new Groups_subjectsDao();
        long group_id= Long.parseLong(req.getParameter("group_id"));
        List<Subject> subjectsForGroup = groups_subjectsDao.getSubjectsForGroup(new GroupDao().getById(group_id));
        ObjectMapper objectMapper=new ObjectMapper();
        String asString = objectMapper.writeValueAsString(subjectsForGroup);
        resp.getWriter().write(asString);
    }
}
