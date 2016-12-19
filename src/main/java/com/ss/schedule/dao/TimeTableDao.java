package com.ss.schedule.dao;

import com.ss.schedule.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 10.12.16.
 */
public class TimeTableDao extends AbstractDao<TimeTable> {



    @Override
    public List<TimeTable> getAll() {

        List <TimeTable> result = new ArrayList<>();
        String sql = "SELECT * FROM timetable";
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                result.add(getTimeTable(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(result);
        return result;
    }


    private TimeTable getTimeTable(ResultSet rs) throws SQLException{

        TeacherDao teacherDao = new TeacherDao();
        SubjectDao subjectDao = new SubjectDao();
        ClassroomDao classroomDao = new ClassroomDao();
        GroupDao groupDao = new GroupDao();


        TimeTable timeTable = new TimeTable();
        timeTable.setId(rs.getInt("id"));
        timeTable.setSubject(subjectDao.getById(rs.getInt("subjects_id")));
        timeTable.setTeacher(teacherDao.getById(rs.getInt("teachers_id")));
        timeTable.setClassroom(classroomDao.getById(rs.getInt("classrooms_id")));
        timeTable.setGroup(groupDao.getById(rs.getInt("groups_id")));
        timeTable.setPair(Pair.getById(rs.getLong("pairs_id")));
        timeTable.setOddnessOfWeek(OddnessOfWeek.getById(rs.getLong("oddnes_id")));
        timeTable.setDay(DayOfWeek.getById(rs.getLong("dayofweeks_id")));

        return timeTable;
    }

    @Override
    public TimeTable getById(long id) {
        TimeTable result = new TimeTable();
        String sql = "SELECT * FROM timetable where id = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                result = getTimeTable(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public TimeTable update(TimeTable entity) {


        String sql = "update timetable set subjects_id = ?, pairs_id = ?, teachers_id = ?, groups_id = ?, dayofweeks_id = ?, oddnes_id = ?, classrooms_id = ? where id = ?";

        try( PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, entity.getSubject().getId());
            ps.setLong(2, entity.getPair().getId());
            ps.setLong(3, entity.getTeacher().getId());
            ps.setLong(4, entity.getGroup().getId());
            ps.setLong(5, entity.getDay().getId());
            ps.setLong(6, entity.getOddnessOfWeek().getId());
            ps.setLong(7, entity.getClassroom().getId());
            ps.setLong(8, entity.getId());


            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getById(entity.getId());
    }

    @Override
    public boolean delete(long id) {

        String sql = "DELETE FROM timetable WHERE id = ?";
        System.out.println(id);
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Timetable was not deleted!");
    }

    public boolean deleteByClassroom(long id) {

        String sql = "DELETE FROM timetable WHERE classrooms_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Timetables were not deleted!");
    }

    @Override
    public TimeTable add(TimeTable entity) {



            String sql = "insert into timetable (subjects_id, pairs_id, teachers_id, groups_id, dayofweeks_id, oddnes_id, classrooms_id) values (?,?,?,?,?,?,?)";

        long id = 0;
            try( PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setLong(1, entity.getSubject().getId());
                ps.setLong(2, entity.getPair().getId());
                ps.setLong(3, entity.getTeacher().getId());
                ps.setLong(4, entity.getGroup().getId());
                ps.setLong(5, entity.getDay().getId());
                ps.setLong(6, entity.getOddnessOfWeek().getId());
                ps.setLong(7, entity.getClassroom().getId());


                ps.executeUpdate();
                ResultSet generatedKeys = ps.getGeneratedKeys();
                while (generatedKeys.next()){
                    id = generatedKeys.getLong(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return getById(id);
    }

    public List<TimeTable> getByClassroom(long id){
        String sql = "select * from timetable where classrooms_id = ?";
        List<TimeTable> result = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                result.add(getTimeTable(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(result);
        return result;
    }

    public boolean isTimeTable(long subjectId, long groupId) {
        int count = 0;
        String sql = "select count(id) as c from timetable where subjects_id = ? and groups_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, subjectId);
            ps.setLong(2, groupId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                count = rs.getInt("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 0 ? false : true;
    }

    public List<TimeTable> getTimeTableByDayPair(DayOfWeek day, Pair pair, OddnessOfWeek oddness) {
        List<TimeTable> result = new ArrayList<>();
        for(TimeTable timeTable : getAll()){
            if(timeTable.getDay().equals(day) && timeTable.getPair().equals(pair)){
                if (oddness == OddnessOfWeek.ALL) {
                    result.add(timeTable);
                } else if(oddness == OddnessOfWeek.EVEN){
                    if (timeTable.getOddnessOfWeek() == OddnessOfWeek.ALL || timeTable.getOddnessOfWeek() == OddnessOfWeek.EVEN){
                        result.add(timeTable);
                    }
                } else {
                    if (timeTable.getOddnessOfWeek() == OddnessOfWeek.ALL || timeTable.getOddnessOfWeek() == OddnessOfWeek.ODD){
                        result.add(timeTable);
                    }
                }
            }
        }
        return result;

    }

    public List<TimeTable> getByGroup(long groupId, long dayId, long oddnesId) {
        String sql = "select * from timetable where groups_id = ? and dayofweeks_id = ? and oddnes_id in (?, 3)";
        List<TimeTable> result = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, groupId);
            ps.setLong(2, dayId);
            ps.setLong(3, oddnesId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                result.add(getTimeTable(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(result);
        return result;
    }

    public long getMaxPair(long groupId, long oddnesId) {

        String sql = "select max(pairs_id) as maxPair from timetable where groups_id = ? and oddnes_id in (?, 3)";
        long result = 0;
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, groupId);
            ps.setLong(2, oddnesId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                result = rs.getInt("maxPair");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public long getMaxTeacherPair(long teacherId, long oddnesId) {
        String sql = "select max(pairs_id) as maxPair from timetable where teachers_id = ? and oddnes_id in (?, 3)";
        long result = 0;
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, teacherId);
            ps.setLong(2, oddnesId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                result = rs.getInt("maxPair");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<TimeTable> getByTeacher(long teacherId, int dayId, long oddnesId) {

        String sql = "select * from timetable where teachers_id = ? and dayofweeks_id = ? and oddnes_id in (?, 3)";
        List<TimeTable> result = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, teacherId);
            ps.setLong(2, dayId);
            ps.setLong(3, oddnesId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                result.add(getTimeTable(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(result);
        return result;
    }
}
