package com.ss.schedule.dao;

import com.ss.schedule.institute.TimeTableManager;
import com.ss.schedule.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 30.11.16.
 */
public class TimeTableDao extends AbstractDao<TimeTable> {

    @Override
    public List<TimeTable> getAll() {
    List<TimeTable> timeTables=new ArrayList<>();
        String query="select * FROM timetables1";
        try {
            Statement statement=
                    connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);

            while (resultSet.next()){
                timeTables.add(getTimetable(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeTables;
    }

    @Override
    public TimeTable getById(long id) {
        TimeTable timetable=null;
        String sql="SELECT * FROM timetables1 WHERE id=(?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
             timetable = getTimetable(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timetable;
    }

    @Override
    public TimeTable update(TimeTable entity) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public TimeTable add(TimeTable entity) {
        return null;
    }

    public TimeTable getTimetable(ResultSet rs){
        TimeTable timeTable=new TimeTable();
        try {
            timeTable.setSubject(new SubjectDao().getById(rs.getLong("subject_id")));
            timeTable.setPair(new PairsDao().getById(rs.getInt("pair_id")));
            timeTable.setTeacher(new Teacher(rs.getString("teacher_id"),"last name"));
            timeTable.setStudentCommunity
                    (new Group(rs.getString("student_community_id"),9,new ArrayList<>()));
            timeTable.setDay(new DayOfWeekDao().getById(rs.getInt("day_of_week_id")));
            timeTable.setOddnessOfWeek(new OddnessOfWeekDao().getById(rs.getInt("oddness_of_week_id")));
            timeTable.setClassroom(new ClassroomDao().getById(rs.getInt("classroom_id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeTable;
    }
}
