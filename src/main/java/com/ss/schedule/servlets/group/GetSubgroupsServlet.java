package com.ss.schedule.servlets.group;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.schedule.dao.AbstractDao;
import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by oleg on 12.12.16.
 */
@WebServlet("/subgroups")
public class GetSubgroupsServlet extends HttpServlet {
    private AbstractDao<Group> groupDao=new GroupDao();
    private Group group=new Group();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int groupId;
        ObjectMapper objectMapper=new ObjectMapper();
        String asString="" ;
        String reqParameter = req.getParameter("group");
        if(req.getParameter("group")!=null) {
            groupId = Integer.parseInt(req.getParameter("group"));
            this.group = groupDao.getById(groupId);
            asString = objectMapper.writeValueAsString(this.group);
        }

        req.setAttribute("jsonGroup",78);

        //req.getRequestDispatcher("/WEB-INF/view/schedule.jsp").forward(req, resp);
        resp.getWriter().write(asString);
    }
}
