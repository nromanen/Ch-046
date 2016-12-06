package com.ss.schedule.web.group;

import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.service.GroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vyach on 06.12.2016.
 */

@WebServlet(urlPatterns = {"/groups/add", "/groups/update"})
public class GroupFormServlet extends HttpServlet {

	private static final String PROPERTIES_FILE_PATH = "db_connection.properties";

	private GroupService groupService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		initService();

		String parameter = req.getParameter("group_id");

		if (parameter != null) {
			try {
				Group group = groupService.getGroupById(Long.valueOf(parameter));
				req.setAttribute("subjects", getCourseSubjects(group.getName().charAt(0) - '0'));
			} catch (SQLException e) {
				//todo
				e.printStackTrace();
			}
		}
	}

	private List<Subject> getCourseSubjects(int course) {
		return null;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	private void initService() {
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
