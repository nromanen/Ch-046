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
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM subject_types WHERE id = ?")) {

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

        ClassroomsSubjectTypeDao cDao = new ClassroomsSubjectTypeDao();
        List<SubjectType> subjectTypes = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement("select name from subjecttypes join classrooms_subjecttypes on classroom_id = ? and subject_types_id = subjecttypes.id")) {

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

    public int getEntityIdByName(String name) {
        try {
            PreparedStatement ps = getPrepareStatement("SELECT id FROM subjectTypes WHERE name = ?");
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                return rs.getInt("id");
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
        PreparedStatement ps = getPrepareStatement("Insert into subjectTypes (name, maxStudentAmount) values (?, ?)");
        try {
                ps.setString(1, entity.toString());
                ps.setInt(2, entity.getMaxStudentAmount());
                ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getEntityIdByName(entity.toString());
    }


    public void createSubjectTypesTableIfNotExist(){
        if (!DBUtil.tableExist(connection, "subjecttypes")) {
            try {
                Statement statement = getStatement();

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

        try {

            for (SubjectType subjectType : SubjectType.values()) {
                ps.setString(1, subjectType.toString());
                ps.setInt(2, subjectType.getMaxStudentAmount());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
