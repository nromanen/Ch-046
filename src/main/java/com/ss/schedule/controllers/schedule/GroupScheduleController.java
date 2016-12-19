package com.ss.schedule.controllers.schedule;

import com.ss.schedule.model.OddnessOfWeek;
import com.ss.schedule.model.Pair;
import com.ss.schedule.model.TimeTable;
import com.ss.schedule.services.GroupService;
import com.ss.schedule.services.PairService;
import com.ss.schedule.services.TimeTableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 15.12.16.
 */
@WebServlet("/groupSchedule")
public class GroupScheduleController extends HttpServlet {

    private GroupService groupService = new GroupService();
    private PairService pairService = new PairService();
    private TimeTableService timeTableService = new TimeTableService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("isResult", false);
        req.setAttribute("groups", groupService.getAll());
        req.getRequestDispatcher("/WEB-INF/view/groupSchedule.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long groupId = Long.parseLong(req.getParameter("group"));
        long oddnesId = Long.parseLong(req.getParameter("oddnes"));

        long maxPair = timeTableService.getMaxPair(groupId, oddnesId);

        List<TimeTable> timeTables1 = timeTableService.getByGroupDayOddnes(groupId, 1, oddnesId);
        List<TimeTable> timeTables2 = timeTableService.getByGroupDayOddnes(groupId, 2, oddnesId);
        List<TimeTable> timeTables3 = timeTableService.getByGroupDayOddnes(groupId, 3, oddnesId);
        List<TimeTable> timeTables4 = timeTableService.getByGroupDayOddnes(groupId, 4, oddnesId);
        List<TimeTable> timeTables5 = timeTableService.getByGroupDayOddnes(groupId, 5, oddnesId);

        List<Pair> pairs = pairService.getPair(maxPair);

        req.setAttribute("isResult", true);
        req.setAttribute("timeTable1", timeTables1);
        req.setAttribute("timeTable2", timeTables2);
        req.setAttribute("timeTable3", timeTables3);
        req.setAttribute("timeTable4", timeTables4);
        req.setAttribute("timeTable5", timeTables5);
        req.setAttribute("pairs", pairs);
        req.setAttribute("groupName", groupService.getById(groupId).getName());
        req.setAttribute("oddnes", OddnessOfWeek.getById(oddnesId));

        req.getRequestDispatcher("/WEB-INF/view/groupSchedule.jsp").forward(req, resp);
    }


}
