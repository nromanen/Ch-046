package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.StudentCommunity;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 03.12.16.
 */
public class GroupsSubjectsDao {
    Connection connection= DBConnector.getConnection();
    public void addSubjectsOfGroup(StudentCommunity group){
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "INSERT INTO groups_subjects ( group_id, subject_id) VALUES (?,?)"
            );
            for (Subject s: group.getSubjects()
                 ) {
                preparedStatement.setLong(1,group.getId());
                preparedStatement.setLong(2,s.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Subject> getSubjectsForGroup(StudentCommunity group){
        List<Subject> subjects=new ArrayList<>();
        try {
//            String sql="SELECT subject_id FROM groups_subjects " +
//                    "  JOIN subjects ON groups_subjects.subject_id=subjects.id WHERE groups_subjects.group_id=? "  ;
//            connection.prepareCall(sql);

            PreparedStatement ps=connection.prepareStatement(
                    "SELECT " +
                            " subjects.name AS subject_name, subjects.course, " +
                            "subject_types.name AS sub_type_name, subjects.id as sub_id" +
                            " FROM groups_subjects " +
                            "JOIN subjects ON groups_subjects.subject_id=subjects.id " +
                            "JOIN subject_types ON subjects.type_id=subject_types.id " +
                            "WHERE groups_subjects.group_id=? ");
            ps.setLong(1,group.getId());
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                subjects.add(new Subject(resultSet.getString("subject_name"),
                        resultSet.getInt("course"),
                        SubjectType.valueOf(resultSet.getString("sub_type_name")),
                        resultSet.getLong("sub_id")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public List<Group> getGroupsOfStream(Subject subject){
        List<Group> groups=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "SELECT * FROM groups_subjects WHERE  subject_id=?"
            );
            preparedStatement.setLong(1,subject.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                groups.add(new GroupDao().getById(resultSet.getLong("group_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    public void deleteSubjectsOfGroup(long id){
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "DELETE FROM groups_subjects WHERE group_id=?"
            );
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
