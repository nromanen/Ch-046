package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import com.ss.schedule.model.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class TeachersSubjectsDao {
    public void add(Teacher teacher) {
        PreparedStatement pis;
        try {
            pis = DBConnector.getConnection()
                    .prepareStatement("INSERT INTO teachers_subjects (subject_id,teacher_id) VALUES (?,?)");
            for (Subject subject : teacher.getSubjects()) {
                pis.setLong(1, subject.getId());
                pis.setLong(2, teacher.getId());
                pis.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(Teacher teacher, Subject subject) {
        PreparedStatement ps;
        try {
            ps = DBConnector.getConnection()
                    .prepareStatement("delete from teachers_subjects where teacher_id=? and subject_id=?");
            ps.setLong(1, subject.getId());
            ps.setLong(2, teacher.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public List<Subject> getSubjects(Teacher teacher)
    {
        List<Subject> subjectsList = new ArrayList<>();
        try {
            PreparedStatement ps =DBConnector.getConnection()
                    .prepareStatement("select subjects.id,name,type_id,course from subjects join teachers_subjects  on subject_id=subjects.id where teacher_id=?");
            ps.setLong(1, teacher.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject(rs.getString("name"), rs.getInt("course"),new SubjectTypeDao().getById(rs.getLong("type_id")));
                subject.setId(rs.getInt("id"));
                subjectsList.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjectsList;
    }

    public List<Teacher> getTeachersOfSubject(Subject subject){
        List<Teacher> teachers=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=DBConnector.getConnection().prepareStatement(
                    "SELECT firstname, lastname, teachers.id as t_id FROM teachers_subjects " +
                            "JOIN teachers ON teachers_subjects.teacher_id=teachers.id WHERE teachers_subjects.subject_id=?"
            );
            preparedStatement.setLong(1,subject.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Teacher teacher = new Teacher(
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname")
                );
                teacher.setId(resultSet.getLong("t_id"));
                //List<Subject> subjects = getSubjects(teacher);
                //teacher.setSubjects(subjects);
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }
}