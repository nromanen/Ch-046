package com.ss.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.teacher.Subject;
import com.ss.teacher.SubjectType;



public class SubjectDao extends AbstractDao<Subject> {
	
	public void createTable() {

		try {
			PreparedStatement create = DBConnector.getConnection().prepareStatement(
					"CREATE TABLE IF NOT EXISTS subjects(id serial, name varchar(64), type varchar(64), course int, PRIMARY KEY(id))");
			create.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void SubjectsToDB(List<Subject> list) throws SQLException {

		for (Subject subject : list) {
			PreparedStatement s = DBConnector.getConnection().prepareStatement("INSERT INTO subjects (name, type, course) VALUES(?,?,?)");
			s.setString(1, subject.getName());
			s.setString(2, subject.getType().toString());
			s.setInt(3, subject.getCourse());
			s.executeUpdate();
		}
	}
	
	@Override
	public List<Subject> getAll() {
		List<Subject> subjectsList = new ArrayList<>();
		try {
			PreparedStatement ps = DBConnector.getConnection().prepareStatement("SELECT * FROM subjects order by name");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Subject subject = new Subject(rs.getString("name"), SubjectType.valueOf(rs.getString("type")), rs.getInt("course"));
				subject.setId(rs.getInt("id"));
				subjectsList.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subjectsList;
	}

	@Override
	public Subject getById(int id) {
		try {
			PreparedStatement ps = DBConnector.getConnection().prepareStatement("select* from subjects where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Subject subject;
			subject = new Subject(rs.getString("name"), SubjectType.valueOf(rs.getString("type")), rs.getInt("course"));
			return subject;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Subject update(Subject subject) {
		PreparedStatement ps;
		try {
			ps = DBConnector.getConnection().prepareStatement("UPDATE teachers set firstname=?,lastname=?,course=? where id=?");
			ps.setString(1, subject.getName());
			ps.setString(2, subject.getType().toString());
			ps.setInt(3, subject.getCourse());
			ps.setInt(4, subject.getId());
			ps.executeUpdate();
			return subject;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(Subject subject) {
		try {
			PreparedStatement ps = DBConnector.getConnection().prepareStatement("DELETE FROM teachers WHERE id=?");
			ps.setInt(1, subject.getId());
			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean add(Subject subject) {
		try {
			PreparedStatement ps = DBConnector.getConnection().prepareStatement("INSERT INTO subjects (name,type,course) VALUES (?,?,?)");
			ps.setString(1, subject.getName());
			ps.setString(2, subject.getType().toString());
			ps.setInt(3, subject.getCourse());
			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}