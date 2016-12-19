package com.ss.schedule.controllers.schedule;

import com.ss.schedule.dto.GroupSubjectDto;
import com.ss.schedule.services.TimeTableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 14.12.16.
 */
@WebServlet(urlPatterns = "/notInSchedule")
public class NotInScheduleController extends HttpServlet {

    private TimeTableService timeTableService = new TimeTableService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<GroupSubjectDto> groupSubjectDtos = timeTableService.getUnsetSubjects();

        if (!groupSubjectDtos.isEmpty()) {
            req.setAttribute("groupsubject", groupSubjectDtos);
            req.getRequestDispatcher("/WEB-INF/view/notInTimetable.jsp").forward(req, resp);
            return;
        } else {
            resp.sendRedirect("/index.html");
        }
    }
}
