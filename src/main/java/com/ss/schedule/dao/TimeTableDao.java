package com.ss.schedule.dao;

import com.ss.schedule.model.TimeTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Admin on 10.12.16.
 */
public class TimeTableDao extends AbstractDao<TimeTable> {



    @Override
    public List<TimeTable> getAll() {
        return null;
    }

    @Override
    public TimeTable getById(long id) {
        return null;
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
}
