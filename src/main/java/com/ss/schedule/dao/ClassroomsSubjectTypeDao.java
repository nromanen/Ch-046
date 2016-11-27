package com.ss.schedule.dao;


import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.model.SubjectType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 27.11.16.
 */
public class ClassroomsSubjectTypeDao {

    private Connection connection = DBConnector.getConnection();

    public void create(int classroomId, int subjectTypeId){

        try {
            PreparedStatement ps = connection.prepareStatement("Insert into classrooms_subject_types (classroom_id, subject_types_id) values (?, ?)");
            ps.setInt(1,classroomId );
            ps.setInt(2, subjectTypeId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getSubjectTypesIdByClassroomId(Integer id) {
        List<Integer> subjectTypesId = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT subject_types_id from classrooms_subject_types WHERE classroom_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                subjectTypesId.add(rs.getInt("subject_types_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectTypesId;
    }
}
