package com.ss.schedule.controllers.schedule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.schedule.services.TimeTableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by rmochetc on 12.12.2016.
 */
@WebServlet(urlPatterns = "/checkTimetable")
public class CheckTimetable extends HttpServlet  {

    private TimeTableService timeTableService= new TimeTableService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long groupId = Long.parseLong(req.getParameter("group"));
        long subjectId = Long.parseLong(req.getParameter("subject"));

        Map<String, String> options = new LinkedHashMap<>();
        if (timeTableService.isTimeTable(subjectId, groupId)) {
            options.put("result", "true");
        } else {
            options.put("result", "false");
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(options);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(jsonInString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }


}
