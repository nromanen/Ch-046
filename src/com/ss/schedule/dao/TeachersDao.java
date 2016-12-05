package com.ss.schedule.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.schedule.institute.Teacher;

public class TeachersDao extends AbstractDao<Teacher> {

	public void createTable() {

		try {
			PreparedStatement create = getPrepareStatement(
					"CREATE TABLE IF NOT EXISTS teachers(id serial, lastname varchar(64), firstname varchar(64), PRIMARY KEY(id))");
			create.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void addAll(List<Teacher> list)  {

		for (Teacher teacher : list) {
			PreparedStatement s = getPrepareStatement("INSERT INTO teachers (lastname, firstname) VALUES(?,?)");
			try {
				s.setString(1, teacher.getLastName());
				s.setString(2, teacher.getFirstName());
				s.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * return sorted by lastname teachers list
	 */
	public List<Teacher> getAll() {
		List<Teacher> TeachersList = new ArrayList<>();
		try {
			PreparedStatement ps = getPrepareStatement("SELECT * FROM teachers order by lastname");
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

	@Override
	public boolean delete(Teacher teacher) {

		try {
			PreparedStatement ps = getPrepareStatement("DELETE FROM teachers WHERE id=?");
			ps.setInt(1, teacher.getId());
			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean add(Teacher teacher) {
		try {
			PreparedStatement ps = getPrepareStatement("INSERT INTO teachers (firstname,lastname) VALUES (?,?)");
			ps.setString(1, teacher.getFirstName());
			ps.setString(2, teacher.getLastName());
			ps.executeUpdate();
			/*PreparedStatement pis = getPrepareStatement("INSERT INTO teachers_subjects (subject_id,teacher_id) VALUES (?,?)");
			for (Subject subject: teacher.getList() )
			{
			pis.setLong(1,sugject.getId());
			pis.setLong(2,teacher.getId());
			pis.executeUpdate();
			}*/
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public Teacher getById(int id) {
		try {
			PreparedStatement ps = getPrepareStatement("select* from teachers where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Teacher teacher = null;
			while (rs.next()) {
				 teacher = new Teacher(rs.getString("firstname"), rs.getString("lastname"), rs.getInt("id"));
			}
			return teacher;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Teacher update(Teacher teacher) {
		PreparedStatement ps;
		try {
			ps = getPrepareStatement("UPDATE teachers set firstname=?,lastname=? where id=?");
			ps.setString(1, teacher.getFirstName());
			ps.setString(2, teacher.getLastName());
			ps.setInt(3, teacher.getId());
			ps.executeUpdate();
			return teacher;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
