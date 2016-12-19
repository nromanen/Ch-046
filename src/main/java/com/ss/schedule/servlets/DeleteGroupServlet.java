package com.ss.schedule.servlets;

import com.ss.schedule.dao.GroupDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by oleg on 10.12.16.
 */
@WebServlet("/deleteGroup")
public class DeleteGroupServlet extends HttpServlet {
    private GroupDao groupDao=new GroupDao();
    int i=0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("groups",groupDao.getAll());
        i++;
        req.getRequestDispatcher("/WEB-INF/view/deleteGroup.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupToDelete = req.getParameter("groupToDelete");
        //long idToDelete= Long.parseLong(groupToDelete);
        //groupDao.delete(idToDelete);
        req.setAttribute("groups",groupDao.getAll());
        i--;
        //req.getRequestDispatcher("/WEB-INF/view/deleteGroup.jsp").forward(req,resp);
    }
}
