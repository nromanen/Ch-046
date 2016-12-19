package com.ss.schedule.dao;

import com.ss.schedule.model.Subject;
import com.ss.schedule.model.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 13.12.2016.
 */
public class TeacherDao extends AbstractDao<Teacher>{

    @Override
    public List<Teacher> getAll() {
        List <Teacher> result = new ArrayList<>();
        String sql = "SELECT * FROM teachers";
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                result.add(getTeacher(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Teacher getById(long id) {



        Teacher teacher = null;
        String sql = "SELECT * FROM teachers WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                teacher = getTeacher(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    private Teacher getTeacher(ResultSet rs) throws SQLException{

       TeacherSubjectDao teacherSubjectDao = new TeacherSubjectDao();

        Teacher teacher = new Teacher();
        teacher.setId(rs.getInt("id"));
        teacher.setFirstName(rs.getString("firstname"));
        teacher.setLastName(rs.getString("lastname"));
        teacher.setSubjects(teacherSubjectDao.getSubjectsByTeacherId(rs.getInt("id")));

        return teacher;
    }



    @Override
    public Teacher update(Teacher entity) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Teacher add(Teacher entity) {
        return null;
    }


    public List<Teacher> getTeachersBySubject(Subject subject){

        List<Teacher> result = new ArrayList<>();
        for (Teacher teacher : getAll()){
            if (teacher.getSubjects().contains(subject)){
                result.add(teacher);
            }
        }
        return result;
    }
}
