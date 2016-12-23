package com.ss.schedule.web.group;

import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.service.GroupService;
import com.ss.schedule.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vyach on 06.12.2016.
 */

@WebServlet(urlPatterns = "/groups/unused-subjects/add")
public class UnusedSubjectFormServlet extends HttpServlet {

	private static final String PROPERTIES_FILE_PATH = "hibernate.cfg.xml";
	private static final Logger logger = LoggerFactory.getLogger(UnusedSubjectFormServlet.class);

	private GroupService groupService;
	private SubjectService subjectService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Subject subject = subjectService.getSubjectById(Long.valueOf(req.getParameter("subject_id")));
			List<Group> groups = groupService.getGroupsByCourse(subject.getCourse());
			req.setAttribute("groups", groups);
			req.setAttribute("subject", subject);
			logger.info("SERVLET: Show all groups for unused subject request - {} - has processed successfully", subject);
		} catch (SQLException ex) {
			logger.error("SERVLET: Exception {} occurred", ex.getClass().getSimpleName());
			//todo
			ex.printStackTrace();
		}

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/view/group/unused_subject_form.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String[] groupIds = req.getParameterValues("group_id");

		if (groupIds == null) {
			req.setAttribute("msg", "No one group was selected! Please select at least one group.");
			doGet(req, resp);
			logger.warn("SERVLET: No one group was selected for unused subject");
			return;
		} else {
			try {
				Subject subject = subjectService.getSubjectById(Long.valueOf(req.getParameter("subject_id")));
				List<Group> groups = getGroupsList(groupIds);

				executeUpdateGroups(groups, subject);
				session.setAttribute("css", "success");
				session.setAttribute("msg", createMessage(groups, subject));
				logger.info("SERVLET: {} has added to {} successfully", subject, groups);
			} catch (SQLException ex) {
				logger.error("SERVLET: Exception {} occurred", ex.getClass().getSimpleName());
				//todo
				ex.printStackTrace();
				session.setAttribute("css", "danger");
				session.setAttribute("msg", "The operation was failed!");
			}
		}

		resp.setStatus(HttpServletResponse.SC_FOUND);
		resp.sendRedirect(req.getContextPath() + "/groups/unused-subjects");
	}

	private List<Group> getGroupsList(String[] groupIds) throws SQLException {
		List<Group> groups = new ArrayList<>();

		for (String groupId : groupIds) {
			groups.add(groupService.getGroupById(Long.valueOf(groupId)));
		}

		return groups;
	}

	private void executeUpdateGroups(List<Group> groups, Subject subject) throws SQLException {
		for (Group group : groups) {
			group.addSubject(subject);
			groupService.updateGroup(group);
		}
	}

	private String createMessage(List<Group> groups, Subject subject) {
		StringBuilder sb = new StringBuilder();
		sb.append("Subject ").append(subject.getName()).append("(").append(subject.getType()).append(") ")
				.append(subject.getCourse()).append(" course had been added into:");

		for (Group group : groups) {
			sb.append("<br>Group ").append(group.getName()); // <br> - new line for html
		}

		return sb.toString();
	}

	@Override
	public void init() throws ServletException {
		try {
			if (groupService == null) {
				groupService = new GroupService(PROPERTIES_FILE_PATH);
			}
			if (subjectService == null) {
				subjectService = new SubjectService(PROPERTIES_FILE_PATH);
			}
		} catch (SQLException e) {
			// todo
			e.printStackTrace();
		}
	}
}
