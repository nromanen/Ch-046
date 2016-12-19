package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 13.12.2016.
 */
public class TeacherSubjectDao {

    private Connection connection = DBConnector.getConnection();

    public List<Subject> getSubjectsByTeacherId(long id) {
        List<Subject> result = new ArrayList<>();
        SubjectDao subjectDao = new SubjectDao();

        String sql = "select subjects_id from teachers_subjects where teachers_id = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                result.add(subjectDao.getById(rs.getLong("subjects_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
