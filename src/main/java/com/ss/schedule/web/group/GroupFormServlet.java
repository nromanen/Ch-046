package com.ss.schedule.web.group;

import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.service.GroupService;
import com.ss.schedule.service.SubjectService;

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

	private static final String PROPERTIES_FILE_PATH = "db_connection.properties";

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
		long groupId = Long.valueOf(req.getParameter("group_id"));
		HttpSession session = req.getSession();

		try {
			if (groupId == 0) {
				executeAddGroup(req);
				session.setAttribute("css", "success");
				session.setAttribute("msg", "Group has added successfully!");
			} else {
				executeUpdateGroup(req);
				session.setAttribute("css", "success");
				session.setAttribute("msg", "Group has updated successfully!");
			}
		} catch (SQLException ex) {
			//todo
			ex.printStackTrace();
			session.setAttribute("css", "danger");
			session.setAttribute("msg", "The operation was failed!");
		}

		resp.setStatus(HttpServletResponse.SC_FOUND);
		resp.sendRedirect("/groups");
	}

	private void executeAddGroup(HttpServletRequest req) throws SQLException {
		Group group = new Group();
		group.setName(req.getParameter("gr_name").trim());
		group.setCount(Integer.valueOf(req.getParameter("gr_count").trim()));
		groupService.addGroup(group);
	}

	private void executeUpdateGroup(HttpServletRequest req) throws SQLException {
		Group group = new Group();
		group.setId(Long.valueOf(req.getParameter("group_id")));
		group.setName(req.getParameter("gr_name"));
		group.setCount(Integer.valueOf(req.getParameter("gr_count")));
		int course = group.getName().charAt(0) - '0';
		group.setSubjects(createSubjectsList(req.getParameterValues("gr_subject"), course));
		groupService.updateGroup(group);
	}

	private List<Subject> createSubjectsList(String[] subjects, int course) throws SQLException {
		List<Subject> subjectsList = new ArrayList<>();

		if (subjects != null) {
			for (String subject : subjects) {
				String[] subjectNameAndType = subject.split(" ");
				subjectsList.add(subjectService.getSubject(subjectNameAndType[0], subjectNameAndType[1], course));
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
