package com.ss.schedule.dao;


import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.dbutil.DBUtil;
import com.ss.schedule.model.SubjectType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Admin on 27.11.16.
 */
public class ClassroomsSubjectTypeDao {

    private Connection connection = DBConnector.getConnection();
    private SubjectTypeDao subjecttypeDao = new SubjectTypeDao();

    public void add(long classroomId, long subjectTypeId){

        try (PreparedStatement ps = connection.prepareStatement("Insert into classrooms_subjectTypes (classroom_id, subject_types_id) values (?, ?)");){
            ps.setLong(1,classroomId );
            ps.setLong(2, subjectTypeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createClassroomsSubjectTypesTableIfNotExist() {
        if (!DBUtil.tableExist(connection, "classrooms_subjecttypes")) {

            try( Statement stmt = connection.createStatement()) {

                String sql = "create table classrooms_subjectTypes " +
                        "(id serial, " +
                        "classroom_id integer references classrooms(id), " +
                        "subject_types_id integer references subjectTypes(id))";

                stmt.executeUpdate(sql);
                System.out.println("Table classrooms_subject_types created!");
            } catch (SQLException e) {
                System.out.println("ERROR! Table classrooms_subject_types did not add!");
            }
        }
    }

    public boolean deleteByClassroomId(long id) {

        try(PreparedStatement ps = connection.prepareStatement("DELETE FROM classrooms_subjecttypes WHERE classroom_id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(long classroom_id, List<SubjectType> types) {

        deleteByClassroomId(classroom_id);
        for (SubjectType type : types) {
            add(classroom_id, subjecttypeDao.getEntityIdByName(type.toString()));
        }
        return true;
    }
}