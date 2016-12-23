package com.ss.schedule.web.group;

import com.ss.schedule.model.Group;
import com.ss.schedule.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by vyach on 07.12.2016.
 */
@WebServlet(urlPatterns = "/groups/delete")
public class DeleteGroupServlet extends HttpServlet {

	private static final String PROPERTIES_FILE_PATH = "hibernate.cfg.xml";
	private static final Logger logger = LoggerFactory.getLogger(DeleteGroupServlet.class);

	private GroupService groupService;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		try {
			Group group = groupService.getGroupById(Long.valueOf(req.getParameter("group_id")));
			groupService.deleteGroup(group);
			session.setAttribute("css", "success");
			session.setAttribute("msg", "Group " + group.getName() + " has deleted successfully!");
			logger.info("SERVLET: Group {} has deleted successfully", group.getName());
		} catch (SQLException ex) {
			logger.error("SERVLET: Exception {} occurred", ex.getClass().getSimpleName());
			//todo
			ex.printStackTrace();
			session.setAttribute("css", "danger");
			session.setAttribute("msg", "The operation was failed!");
		}

		resp.setStatus(HttpServletResponse.SC_FOUND);
		resp.sendRedirect(req.getContextPath() + "/groups");
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
