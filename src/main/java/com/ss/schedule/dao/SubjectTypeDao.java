package com.ss.schedule.dao;

import com.ss.schedule.model.SubjectType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 27.11.16.
 */
public class SubjectTypeDao extends AbstractDao<SubjectType, Integer>{

    @Override
    public List<SubjectType> getAll() {

        return null;
    }

    @Override
    public SubjectType getById(Integer id) {
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
    public SubjectType update(SubjectType entity) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean add(SubjectType entity) {
        return false;
    }
}
