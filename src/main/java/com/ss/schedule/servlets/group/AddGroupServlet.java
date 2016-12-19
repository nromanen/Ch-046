package com.ss.schedule.servlets.group;

import com.ss.schedule.model.Group;
import org.apache.taglibs.standard.lang.jstl.GreaterThanOperator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by oleg on 10.12.16.
 */
public class AddGroupServlet extends HttpServlet {
    Group groupToAdd=new Group();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        groupToAdd.setName(req.getParameter("name"));
        groupToAdd.setCount(Integer.parseInt(req.getParameter("count")));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
