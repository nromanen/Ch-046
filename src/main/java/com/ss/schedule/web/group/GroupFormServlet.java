package com.ss.schedule.web.group;

import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import com.ss.schedule.service.GroupService;
import com.ss.schedule.service.SubjectService;
import com.ss.schedule.validator.GroupFormValidator;

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

@WebServlet(urlPatterns = {"/groups/add", "/groups/update"})
public class GroupFormServlet extends HttpServlet {

	private static final String PROPERTIES_FILE_PATH = "hibernate.cfg.xml";

	private GroupService groupService;
	private SubjectService subjectService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String groupId = req.getParameter("group_id");

		try {
			Group group = new Group();
			req.setAttribute("action", "Add");
			if (groupId != null) {
				group = groupService.getGroupById(Long.valueOf(groupId));
				req.setAttribute("subjects", getCourseSubjectsDoNotUsedInGroup(group.getName().charAt(0) - '0', group.getSubjects()));
				req.setAttribute("group_subjects", group.getSubjects());
				req.setAttribute("action", "Update");
			}
			req.setAttribute("group", group);
		} catch (SQLException e) {
			//todo
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/group/group_form.jsp");
		dispatcher.forward(req, resp);
	}

	private List<Subject> getCourseSubjectsDoNotUsedInGroup(int course, List<Subject> groupSubject) throws SQLException {
		List<Subject> courseSubjects = subjectService.getSubjectsByCourse(course);
		List<Subject> subjects = new ArrayList<>();
		for (Subject subject : courseSubjects) {
			if (!groupSubject.contains(subject)) {
				subjects.add(subject);
			}
		}
		return subjects;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("groupName", req.getParameter("main_name"));

		try {
			Group group = getGroup(req);
			if (GroupFormValidator.hasErrors(session, group) || isGroupExists(session, group)) {
				setSessionAttributes(session, group);
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/group/group_form.jsp");
				dispatcher.forward(req, resp);
				return;
			} else {

				if (group.getId() == 0) {
					String groupName = executeAddGroup(req);
					session.setAttribute("css", "success");
					session.setAttribute("msg", "Group " + groupName + " has added successfully!");
				} else {
					String groupName = executeUpdateGroup(req);
					session.setAttribute("css", "success");
					session.setAttribute("msg", "Group " + groupName + " has updated successfully!");
				}
			}
		} catch (SQLException ex) {
			//todo
			ex.printStackTrace();
			session.setAttribute("css", "danger");
			session.setAttribute("msg", "The operation was failed!");
		}

		resp.setStatus(HttpServletResponse.SC_FOUND);
		resp.sendRedirect(req.getContextPath() + "/groups");
	}

	private Group getGroup(HttpServletRequest req) throws SQLException {
		long groupId = Long.valueOf(req.getParameter("group_id"));
		Group group = new Group();

		String groupName = req.getParameter("gr_name").trim();
		String groupCount = req.getParameter("gr_count").trim();

		group.setId(groupId);
		group.setName(groupName.equals("") ? "0" : groupName);
		group.setCount(Integer.valueOf((groupCount.equals("")) ? "0" : groupCount));
		group.setSubjects(createSubjectsList(req.getParameterValues("gr_subject")));

		return group;
	}

	private boolean isGroupExists(HttpSession session, Group group) {
		Group checkingGroup = groupService.getGroupByName(group.getName());
		String mainGroupName = (String) session.getAttribute("groupName");
		if (checkingGroup != null && !mainGroupName.equals(group.getName())) {
			session.setAttribute("msg", "Failed! Group " + group.getName() + " has already existed");
			return true;
		}
		return false;
	}

	private void setSessionAttributes(HttpSession session, Group group) throws SQLException {
		session.setAttribute("group", group);
		session.setAttribute("action", "Add");
		if (group.getId() != 0) {
			session.setAttribute("subjects", getCourseSubjectsDoNotUsedInGroup(group.getName().charAt(0) - '0', group.getSubjects()));
			session.setAttribute("group_subjects", group.getSubjects());
			session.setAttribute("action", "Update");
		}
	}

	private String executeAddGroup(HttpServletRequest req) throws SQLException {
		Group group = new Group();
		group.setName(req.getParameter("gr_name").trim());
		group.setCount(Integer.valueOf(req.getParameter("gr_count").trim()));
		groupService.addGroup(group);
		return group.getName();
	}

	private String executeUpdateGroup(HttpServletRequest req) throws SQLException {
		Group group = new Group();
		group.setId(Long.valueOf(req.getParameter("group_id")));
		group.setName(req.getParameter("gr_name"));
		group.setCount(Integer.valueOf(req.getParameter("gr_count")));
		group.setSubjects(createSubjectsList(req.getParameterValues("gr_subject")));
		groupService.updateGroup(group);
		return group.getName();
	}

	private List<Subject> createSubjectsList(String[] subjects) throws SQLException {
		List<Subject> subjectsList = new ArrayList<>();

		if (subjects != null) {
			for (String subjectData : subjects) {
				String[] subjectParameters = subjectData.split(" ");
				Subject subject = subjectService.getSubject(subjectParameters[0],
						SubjectType.valueOf(subjectParameters[1]), Integer.valueOf(subjectParameters[2]));
				if (subject != null) {
					subjectsList.add(subject);
				}
			}
		}

		return subjectsList;
	}

	@Override
	public void init() {
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
