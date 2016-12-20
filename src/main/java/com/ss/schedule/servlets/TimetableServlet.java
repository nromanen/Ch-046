package com.ss.schedule.servlets;

import com.ss.schedule.dao.DayOfWeekDao;
import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.dao.OddnessOfWeekDao;
import com.ss.schedule.institute.TimeTableManager;
import com.ss.schedule.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ss.schedule.model.SubjectType.LECTURE;

/**
 * Created by oleg on 01.12.16.
 */
@WebServlet("/chooseTimetable")
public class TimetableServlet extends HttpServlet {
    private Group group=new Group("31",35,null);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TimeTable> timeTablesForGroup = createTimeTablesForGroup();
        TimeTableManager timeTableManager=new TimeTableManager();
        timeTableManager.setTimeTables(timeTablesForGroup);
        DayOfWeekDao dayOfWeekDao = new DayOfWeekDao();
        List<DayOfWeek> allDays = dayOfWeekDao.getAll();
        req.setAttribute("group",group);
        req.setAttribute("days_of_week",allDays);
        req.setAttribute("oddness_of_weeks",new OddnessOfWeekDao().getAll());
        List<Group> groups = new GroupDao().getAll();
        req.setAttribute("groups",groups);

        req.getRequestDispatcher("WEB-INF/view/timetable.jsp").forward(req,resp);


    }

    public List<TimeTable> createTimeTablesForGroup(){
        List<Group> groups=new ArrayList<>();


        Subject english=new Subject();
        english.setName("English");
        english.setType(LECTURE);
        english.setCourse(3);


        Subject spanish=new Subject();
        spanish.setName("Spanish");
        spanish.setType(LECTURE);
        spanish.setCourse(3);

        Subject chineese=new Subject();
        chineese.setName("Chineese");
        chineese.setType(LECTURE);
        chineese.setCourse(3);

        Subject math=new Subject();
        math.setName("Math");
        math.setType(LECTURE);
        math.setCourse(3);

        TimeTable tt1=new TimeTable();
        tt1.setStudentCommunity(group);
        tt1.setDay(DayOfWeek.MONDAY);
        tt1.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt1.setPair(Pair.FIRST);
        tt1.setSubject(english);
        tt1.setTeacher(new Teacher("Olena","LN1"));
        tt1.setClassroom(new Classroom("40"));

        TimeTable tt2=new TimeTable();
        tt2.setDay(DayOfWeek.MONDAY);
        tt2.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt2.setPair(Pair.SECOND);
        tt2.setSubject(spanish);
        tt2.setStudentCommunity(group);
        tt2.setTeacher(new Teacher("Name","LN2"));
        tt2.setClassroom(new Classroom("39"));

        TimeTable tt3=new TimeTable();
        tt3.setDay(DayOfWeek.MONDAY);
        tt3.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt3.setPair(Pair.SIXTH);
        tt3.setSubject(chineese);
        tt3.setStudentCommunity(group);
        tt3.setTeacher(new Teacher("Name1","LN3"));
        tt3.setClassroom(new Classroom("50"));


        TimeTable tt4=new TimeTable();
        tt4.setDay(DayOfWeek.MONDAY);
        tt4.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt4.setPair(Pair.SEVENTH);
        tt4.setSubject(math);
        tt4.setStudentCommunity(group);
        tt4.setTeacher(new Teacher());
        tt4.setClassroom(new Classroom("52"));;

        List<TimeTable> timeTables=new ArrayList<>();
        timeTables.add(tt1);
        timeTables.add(tt2);
        timeTables.add(tt3);
        timeTables.add(tt4);

        return timeTables;

    }
}
