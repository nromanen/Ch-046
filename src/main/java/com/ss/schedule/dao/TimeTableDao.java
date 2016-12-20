package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.dbutil.DBUtil;
import com.ss.schedule.model.*;
import org.postgresql.util.PSQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by oleg on 30.11.16.
 */
public class TimeTableDao extends AbstractDao<TimeTable> {

    public TimeTableDao(){

    }


    @Override
    public List<TimeTable> getAll() {
    List<TimeTable> timeTables=new ArrayList<>();
        String query="select * FROM timetables";
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
        String sql="SELECT * FROM timetables WHERE id=(?)";
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
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "UPDATE timetables SET subject_id=?, pair_id=?," +
                            "teacher_id=?, day_of_week_id=?," +
                            "oddness_of_week_id=?,classroom_id=? WHERE id=?"
            );
            preparedStatement.setLong(1,entity.getSubject().getId());
            preparedStatement.setLong(2,entity.getPair().getId());
            preparedStatement.setLong(3,entity.getTeacher().getId());
            preparedStatement.setLong(4,entity.getDay().getId());
            preparedStatement.setLong(5,entity.getOddnessOfWeek().getId());
            preparedStatement.setLong(6,entity.getClassroom().getId());
            preparedStatement.setLong(7,entity.getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
                new GroupTimetableDao().deleteGroupsOfTimetable(entity.getId());
                new GroupTimetableDao().addGroupOrSubgroupOfTimetable(entity);
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(long id) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "DELETE FROM timetables WHERE id=?"
            );
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            new GroupTimetableDao().deleteGroupsOfTimetable(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



     public List<TimeTable> getTimetablesAtPreciseTime(TimeTable timeTable){
               List<TimeTable> timeTables=new ArrayList<>();
               try {
                   PreparedStatement preparedStatement=connection.prepareStatement(
                           "SELECT *,timetables.id AS tmetable_id FROM timetables WHERE pair_id=? AND day_of_week_id=?"
                   );
                   preparedStatement.setLong(1,timeTable.getPair().getId());
                   preparedStatement.setLong(2,timeTable.getDay().getId());
                   ResultSet resultSet = preparedStatement.executeQuery();
                   while (resultSet.next()){
                       TimeTable timetable = getTimetable(resultSet);
                       timeTables.add(timetable);
                   }
               } catch (SQLException e) {
                   e.printStackTrace();
               }
               return timeTables;
           }

    @Override
    public TimeTable add(TimeTable entity) {
        String sql="INSERT INTO timetables ( subject_id, pair_id, teacher_id," +
                " day_of_week_id, oddness_of_week_id, classroom_id) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,entity.getSubject().getId());
            preparedStatement.setLong(2,entity.getPair().getId());
            preparedStatement.setLong(3,entity.getTeacher().getId());
            preparedStatement.setLong(4,entity.getDay().getId());
            preparedStatement.setLong(5,entity.getOddnessOfWeek().getId());
            preparedStatement.setLong(6,entity.getClassroom().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int id;
            if (rs.next()){
                id=rs.getInt(1);
                entity.setId(id);
                new GroupTimetableDao().addGroupOrSubgroupOfTimetable(entity);
            }
            return entity;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public TimeTable getTimetable(ResultSet rs){
        TimeTable timeTable=new TimeTable();
        try {
            timeTable.setSubject(new JdbcSubjectDao().getById(rs.getLong("subject_id")));
            timeTable.setPair(new PairsDao().getById(rs.getInt("pair_id")));
            timeTable.setTeacher(new TeachersDao().getById(rs.getLong("teacher_id")));
            try {
                timeTable.setId(rs.getLong("id"));

            } catch (PSQLException e){
                System.out.println("Error");
            }
            timeTable.setDay(new DayOfWeekDao().getById(rs.getInt("day_of_week_id")));
            timeTable.setOddnessOfWeek(new OddnessOfWeekDao().getById(rs.getInt("oddness_of_week_id")));
            timeTable.setClassroom(new ClassroomDao().getById(rs.getInt("classroom_id")));
            GroupTimetableDao group_timetableDao = new GroupTimetableDao();
            timeTable.setStudentCommunity(group_timetableDao.getStudentCommunityOfTimetable(timeTable));
            }
         catch (SQLException e) {
            e.printStackTrace();
        }
        return timeTable;
    }

    public List<TimeTable> getTimetablesOfGroupOrSubgroup(TimeTable timeTable){
        List<TimeTable> timeTables=new ArrayList<>();
        String sql= "SELECT timetables.id as tmetable_id, subject_id, pair_id,teacher_id," +
                " day_of_week_id, oddness_of_week_id, classroom_id FROM group_timetable JOIN timetables ON timetable_id=timetables.id " +
                "WHERE pair_id=? AND day_of_week_id=? AND (oddness_of_week_id=3 OR oddness_of_week_id=?) " +
                "AND group_id IN (SELECT groups.id FROM groups WHERE id=? " +
                "OR parent_id=?)";
        if (timeTable.getStudentCommunity() instanceof Subgroup){
            Subgroup studentCommunity = (Subgroup) timeTable.getStudentCommunity();
            String sql1= "SELECT timetables.id as tmetable_id, subject_id, pair_id,teacher_id," +
                    " day_of_week_id, oddness_of_week_id, classroom_id FROM group_timetable JOIN timetables ON timetable_id=timetables.id " +
                    "WHERE pair_id=? AND day_of_week_id=? AND (oddness_of_week_id=3 OR oddness_of_week_id=?) " +
                    "AND group_id IN (SELECT groups.id FROM groups WHERE id=? " +
                    "OR id=?)";
            try {
                PreparedStatement preparedStatement=connection.prepareStatement(sql1);
                preparedStatement.setLong(1, timeTable.getPair().getId());
                preparedStatement.setLong(2,timeTable.getDay().getId());
                preparedStatement.setLong(3,timeTable.getOddnessOfWeek().getId());
                preparedStatement.setLong(4,studentCommunity.getId());
                preparedStatement.setLong(5,studentCommunity.getGroup().getId());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    TimeTable timetable = getTimetable(resultSet);
                    timetable.setId(resultSet.getLong("tmetable_id"));
                    timeTables.add(timetable);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (timeTable.getStudentCommunity() instanceof Stream){
            Stream studentCommunity = (Stream) timeTable.getStudentCommunity();
            for (Group group:studentCommunity.getGroups()
                 ) {
                try {
                    PreparedStatement preparedStatement= DBConnector.getConnection().prepareStatement(
                            sql
                    );
                    preparedStatement.setLong(1, timeTable.getPair().getId());
                    preparedStatement.setLong(2,timeTable.getDay().getId());
                    preparedStatement.setLong(3,timeTable.getOddnessOfWeek().getId());
                    preparedStatement.setLong(4,group.getId());
                    preparedStatement.setLong(5,group.getId());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        TimeTable timetable = getTimetable(resultSet);
                        timetable.setId(resultSet.getLong("tmetable_id"));
                        timeTables.add(timetable);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else
        try {
            PreparedStatement preparedStatement= DBConnector.getConnection().prepareStatement(
                    sql
            );
            preparedStatement.setLong(1, timeTable.getPair().getId());
            preparedStatement.setLong(2,timeTable.getDay().getId());
            preparedStatement.setLong(3,timeTable.getOddnessOfWeek().getId());
            preparedStatement.setLong(4,timeTable.getStudentCommunity().getId());
            preparedStatement.setLong(5,timeTable.getStudentCommunity().getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                TimeTable timetable = getTimetable(resultSet);
                timetable.setId(resultSet.getLong("tmetable_id"));
                timeTables.add(timetable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeTables;
    }

    public LinkedHashMap<DayOfWeek,TimeTable[]> getWeeklyTimetablesForGroup(StudentCommunity group,OddnessOfWeek oddnessOfWeek){
        List<TimeTable> timeTables=new ArrayList<>();
        LinkedHashMap<DayOfWeek,TimeTable[]> timetablesMap=new LinkedHashMap<>();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "SELECT timetables.id as tmetable_id, subject_id, pair_id,teacher_id," +
                            " day_of_week_id, oddness_of_week_id, classroom_id FROM group_timetable JOIN timetables on timetable_id=timetables.id WHERE group_id =? " +
                            "AND timetables.oddness_of_week_id=? ORDER BY pair_id ASC "
            );
            for (DayOfWeek dayOfWeek:DayOfWeek.values()){
                timetablesMap.put(dayOfWeek,new TimeTable[10]);
            }
            preparedStatement.setLong(1,group.getId());
            preparedStatement.setLong(2,oddnessOfWeek.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                TimeTable timetable = getTimetable(resultSet);
                timetable.setId(resultSet.getLong("tmetable_id"));
                GroupTimetableDao group_timetableDao = new GroupTimetableDao();
                timetable.setStudentCommunity(group_timetableDao.getStudentCommunityOfTimetable(timetable));
                timetablesMap.get(timetable.getDay())[timetable.getPair().ordinal()]=timetable;
                timeTables.add(timetable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timetablesMap;
    }

    public TimeTable[] getDayTimetableOfGroup(StudentCommunity group,DayOfWeek dayOfWeek,OddnessOfWeek oddnessOfWeek){
        TimeTable[] timeTables=new TimeTable[10];
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "SELECT *, timetables.id as ttid FROM group_timetable JOIN timetables on timetable_id=timetables.id WHERE group_id =? " +
                            "AND day_of_week_id=? AND timetables.oddness_of_week_id=? "
            );
            preparedStatement.setLong(1,group.getId());
            preparedStatement.setLong(2,dayOfWeek.getId());
            preparedStatement.setLong(3,oddnessOfWeek.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                TimeTable timetable = getTimetable(resultSet);
                GroupTimetableDao group_timetableDao = new GroupTimetableDao();
                timetable.setStudentCommunity(group_timetableDao.getStudentCommunityOfTimetable(timetable));
                timetable.setId(resultSet.getLong("ttid"));
                timeTables[timetable.getPair().ordinal()]=timetable;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeTables;
    }

    private void createTimetablesTableIfNotExist() {
        if (!DBUtil.tableExist(connection, "timetables")) {
            try {
                Statement stmt = connection.createStatement();

                String sql = "CREATE TABLE IF NOT EXISTS timetables( id SERIAL NOT NULL PRIMARY KEY,\n" +
                        " subject_id INT NOT NULL,\n" +
                        "pair TEXT NOT NULL, \n" +
                        "teacher_id INT NOT NULL, \n" +
                        "student_community_id INT NOT NULL,\n" +
                        "day_of_week TEXT NOT NULL, \n" +
                        "oddness_of_week TEXT NOT NULL,\n" +
                        " classroom_id INT NOT NULL)";
                stmt.executeUpdate(sql);
                System.out.println("Table timetables created!");
            } catch (SQLException e) {
                System.out.println("ERROR! Table timetables did not created!");
            }
        }
    }
}
