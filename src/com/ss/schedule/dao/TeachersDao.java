package com.ss.schedule.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ss.schedule.model.Subject;
import com.ss.schedule.model.Teacher;

public class TeachersDao extends AbstractDao<Teacher> {

	public void createTable() {

		try {
			PreparedStatement create = connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS teachers(id serial, lastname varchar(64), firstname varchar(64), PRIMARY KEY(id))");
			create.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void addAll(List<Teacher> list) {

		for (Teacher teacher : list) {
			try {
				PreparedStatement s = connection.prepareStatement(
						"INSERT INTO teachers (lastname, firstname) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
				s.setString(1, teacher.getLastName());
				s.setString(2, teacher.getFirstName());
				s.executeUpdate();
				ResultSet generatedKeys = s.getGeneratedKeys();
				int id = 0;
				while (generatedKeys.next()) {
					id = generatedKeys.getInt(1);
				}
				teacher.setId(id);
				new TeachersSubjectsDao().add(teacher);
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
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM teachers order by lastname");
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
	public Teacher update(Teacher teacher) {
		 PreparedStatement ps;
	        try {
	            ps = connection.prepareStatement("UPDATE teachers set firstname=?,lastname=? where id=?");
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

	@Override
	public boolean delete(Teacher teacher) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM teachers WHERE id=?");
			ps.setLong(1, teacher.getId());
			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Teacher getById(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement("select* from teachers where id=?");
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

	@Override
	public boolean add(Teacher teacher) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO teachers (firstname,lastname) VALUES (?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, teacher.getFirstName());
			ps.setString(2, teacher.getLastName());
			ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
			int id = 0;
			while (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}
			teacher.setId(id);
			new TeachersSubjectsDao().add(teacher);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}