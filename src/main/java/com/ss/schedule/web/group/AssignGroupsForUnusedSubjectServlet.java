package com.ss.schedule.web.group;

import com.ss.schedule.model.Subject;
import com.ss.schedule.service.GroupService;
import com.ss.schedule.service.SubjectService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vyach on 07.12.2016.
 */
@WebServlet(urlPatterns = "/groups/unused-subjects")
public class AssignGroupsForUnusedSubjectServlet extends HttpServlet {

	private static final String PROPERTIES_FILE_PATH = "hibernate.cfg.xml";

	private GroupService groupService;
	private SubjectService subjectService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Subject> unusedSubjects = subjectService.getUnusedSubjects();
			req.setAttribute("subjects", unusedSubjects);
		} catch (SQLException e) {
			//todo
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/group/unused_subjects.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	public void init() {
		try {
			if (subjectService == null) {
				subjectService = new SubjectService(PROPERTIES_FILE_PATH);
			}
			if (groupService == null) {
				groupService = new GroupService(PROPERTIES_FILE_PATH);
			}
		} catch (SQLException e) {
			// todo
			e.printStackTrace();
		}
	}
}
