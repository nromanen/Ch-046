package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBUtil;
import com.ss.schedule.model.Subject;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by rmochetc on 30.11.2016.
 */
public class SubjectDao extends AbstractDao<Subject> {

    SubjectTypeDao subjectTypeDao = new SubjectTypeDao();

    public SubjectDao() {
        createSubjectTableIfNotExist();
    }

    @Override
    public List<Subject> getAll() {
        return null;
    }

    @Override
    public Subject getById(long id) {
        return null;
    }

    @Override
    public Subject update(Subject entity) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Subject add(Subject entity) {
        List<Subject> subjects = getAll();
        if (!subjects.contains(entity)) {

            String sql = "INSERT INTO subjects " +
                    "(name, type_id, course) " +
                    "VALUES (?, ?, ?)";

            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, entity.getName());
                ps.setLong(2, subjectTypeDao.getEntityIdByName(entity.getType().toString()) );
                ps.setInt(3, entity.getCourse());

                ps.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }
            return getById(getEntityIdByName(entity.getName()));
        }
        return null;
    }

    private long getEntityIdByName(String name) {
        return 0;
    }

    private void createSubjectTableIfNotExist() {
        if (!DBUtil.tableExist(connection, "subjects")) {
            try {
                Statement stmt = connection.createStatement();

                String sql = "CREATE TABLE subjects " +
                        "(id SERIAL, " +
                        " name VARCHAR(30), " +
                        " type_id INTEGER references subjecttypes(id), " +
                        " course int, " +
                        " PRIMARY KEY ( id ))";

                stmt.executeUpdate(sql);
                System.out.println("Table subjects created!");
            } catch (SQLException e) {
                System.out.println("ERROR! Table subjects did not created!");
            }
        }
    }
}
