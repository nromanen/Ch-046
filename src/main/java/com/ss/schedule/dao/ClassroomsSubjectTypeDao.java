package com.ss.schedule.dao;


import com.ss.schedule.dbutil.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Admin on 27.11.16.
 */
public class ClassroomsSubjectTypeDao {

    private Connection connection = DBConnector.getConnection();

    public void create(int classroomId, int subjectTypeId){

        try {
            PreparedStatement ps = connection.prepareStatement("Insert into classrooms_subjectTypes (classroom_id, subject_types_id) values (?, ?)");
            ps.setInt(1,classroomId );
            ps.setInt(2, subjectTypeId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
