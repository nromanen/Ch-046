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
    public SubjectType getById(int id) {
        try {
            PreparedStatement ps = getPrepareStatement("SELECT * FROM subject_types WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                return SubjectType.valueOf(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SubjectType> getEntitiesByClassroomId(Integer id) {

        ClassroomsSubjectTypeDao cDao = new ClassroomsSubjectTypeDao();
        List<SubjectType> subjectTypes = new ArrayList<>();
        try {

                PreparedStatement ps = getPrepareStatement("select name from subjecttypes join classrooms_subjecttypes on classroom_id = ? and subject_types_id = subjecttypes.id");
                ps.setInt(1, id);

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
    public boolean update(SubjectType entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean add(SubjectType entity) {
        return false;
    }


    public void createSubjectTypesTableIfNotExist(){
        if (!DBUtil.tableExist(connection, "subjecttypes")) {
            try {
                Statement statement = connection.createStatement();

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
        PreparedStatement ps = getPrepareStatement("Insert into subjectTypes (name, maxStudentAmount) values (?, ?)");
        try {

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
