package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBUtil;
import com.ss.schedule.model.SubjectType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 27.11.16.
 */
public class SubjectTypeDao extends AbstractDao<SubjectType>{

    @Override
    public List<SubjectType> getAll() {

        return null;
    }

    @Override
    public SubjectType getById(long id) {

        String sql = "SELECT * FROM subject_types WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                return SubjectType.valueOf(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SubjectType> getEntitiesByClassroomId(Long id) {

        List<SubjectType> subjectTypes = new ArrayList<>();
        String sql = "select name from subjecttypes join classrooms_subjecttypes on " +
                "classroom_id = ? and subject_types_id = subjecttypes.id";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                subjectTypes.add(SubjectType.valueOf(rs.getString("name")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectTypes;
    }

    public long getEntityIdByName(String name) {
        String sql = "SELECT id FROM subjectTypes WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                return rs.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public SubjectType update(SubjectType entity) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public SubjectType add(SubjectType entity) {
        String sql = "Insert into subjectTypes (name, maxStudentAmount) values (?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.toString());
            ps.setInt(2, entity.getMaxStudentAmount());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }


    public void createSubjectTypesTableIfNotExist(){
        if (!DBUtil.tableExist(connection, "subjecttypes")) {
            try(Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE subjectTypes " +
                        "(id SERIAL, " +
                        " name VARCHAR(30), " +
                        " maxStudentAmount INTEGER, " +
                        "PRIMARY KEY (id)) ";
                statement.executeUpdate(sql);
                fillSubjectTypesTable();
                System.out.println("Table subjectTypes created!");
            } catch (SQLException e) {
                System.out.println("ERROR! Table subjectTypes did not add!");
            }

        }
    }

    public void fillSubjectTypesTable() {
        String sql = "Insert into subjectTypes (name, maxStudentAmount) values (?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {

            SubjectType[] types = SubjectType.values();
            for (SubjectType subjectType : types) {
                ps.setString(1, subjectType.toString());
                ps.setInt(2, subjectType.getMaxStudentAmount());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
