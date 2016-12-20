package com.ss.schedule.dao;

import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vyach on 28.11.2016.
 */
public class JdbcSubjectDao extends AbstractDao<Subject> {

    public JdbcSubjectDao()  {
    }

    @Override
    public Subject add(Subject subject)  {
        String request = "INSERT INTO subjects (name, type_id, course) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setLong(2, subject.getType().getId());
            preparedStatement.setInt(3, subject.getCourse());
            preparedStatement.execute();
            subject.setId(getSubjectId(subject));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subject;
    }

    private long getSubjectId(Subject subject) throws SQLException {
        String request = "SELECT id FROM subjects WHERE name = ? AND type_id = ? AND course = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        preparedStatement.setString(1, subject.getName());
        preparedStatement.setLong(2, subject.getType().getId());
        preparedStatement.setInt(3, subject.getCourse());
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        return rs.getLong("id");
    }

    @Override
    public Subject update(Subject subject)  {
        String request = "UPDATE subjects SET name = ?, type_id = ?, course = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setLong(2, subject.getType().getId());
            preparedStatement.setInt(3, subject.getCourse());
            preparedStatement.setLong(4, subject.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getById(subject.getId());

    }

    @Override
    public boolean delete(long id)  {
        String request = "DELETE FROM subjects WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Subject getById(long id)  {
        String request = "SELECT  s.id, s.name, t.name, s.course FROM subjects  AS s " +
                "JOIN subject_types AS t " +
                "ON s.type_id = t.id " +
                "WHERE s.id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            return createSubject(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<Subject> getAll()  {
        String request = "SELECT s.id, s.name, t.name, s.course FROM subjects AS s " +
                "JOIN subject_types AS t " +
                "ON s.type_id = t.id " +
                "ORDER BY s.id ASC";
        Statement statement ;
        List<Subject> subjects=null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(request);
             subjects = new ArrayList<>();
            while (rs.next()) {
                subjects.add(createSubject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }


    private Subject createSubject(ResultSet rs) throws SQLException {
        Subject subject = new Subject();
        subject.setId(rs.getLong(1));
        subject.setName(rs.getString(2));
        subject.setType(SubjectType.valueOf(rs.getString(3)));
        subject.setCourse(rs.getInt(4));
        return subject;
    }
}