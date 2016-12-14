package com.ss.schedule.web.group;

import com.ss.schedule.model.Group;
import com.ss.schedule.service.GroupService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vyach on 01.12.2016.
 */
@WebServlet(urlPatterns = "/groups")
public class ShowGroupsServlet extends HttpServlet {

	private static final String PROPERTIES_FILE_PATH = "hibernate.cfg.xml";

	private GroupService groupService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Group> groups = new ArrayList<>();
		try {
			groups = groupService.getAllGroups();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		req.setAttribute("groups", groups);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/group/groups.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	public void init() {
		try {
			if (groupService == null) {
				groupService = new GroupService(PROPERTIES_FILE_PATH);
			}
		} catch (SQLException e) {
			// todo
			e.printStackTrace();
		}
	}
}
