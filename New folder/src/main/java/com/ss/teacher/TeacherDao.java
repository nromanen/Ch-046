package com.ss.teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao {
    public static List<Teacher> getAll() {
        List<Teacher> TeachersList = new ArrayList<>();
        try {
            PreparedStatement ps = DBConnector.getConnection().prepareStatement("SELECT * FROM teachers order by lastname");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getString("firstname"), rs.getString("lastname"), rs.getInt("id"));
                TeachersList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return TeachersList;
    }
    public Teacher getById(int id) {
		try {
			PreparedStatement ps = DBConnector.getConnection().prepareStatement("select* from teachers where id=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			Teacher teacher = null;
			while (rs.next()) {
				teacher = new Teacher(rs.getString("firstname"), rs.getString("lastname"), rs.getInt("id"));
				teacher.setList(new TeachersSubjectsDao().getSubjects(teacher));
			}
			return teacher;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
    public Teacher update(Teacher teacher) {
		 PreparedStatement ps;
	        try {
	            ps = DBConnector.getConnection().prepareStatement("UPDATE teachers set firstname=?,lastname=? where id=?");
	            ps.setString(1, teacher.getFirstName());
	            ps.setString(2, teacher.getLastName());
	            ps.setLong(3, teacher.getId());
	            ps.executeUpdate();
	            return teacher;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
    
}
