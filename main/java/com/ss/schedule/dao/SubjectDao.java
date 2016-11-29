package com.ss.schedule.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.io.InputOutputSubjectTxt;
import com.ss.schedule.io.InputOutputXml;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;

public class SubjectDao extends AbstractDao<Subject, Integer> {

	public void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS SUBJECTS " + "(ID SERIAL NOT NULL PRIMARY KEY,"
				+ " NAME TEXT NOT NULL, " + " SUBJECT_TYPE TEXT NOT NULL, " + " COURSE INT NOT NULL)";
		try {
			Statement create = getStatement();
			create.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Subject> populateSubjectsFromJson(String filePath) {
		InputOutputJson<ArrayList<Subject>> reader = new InputOutputJson<>(new TypeReference<ArrayList<Subject>>() {
		});
		ArrayList<Subject> listOfSubjects = new ArrayList<>();
		listOfSubjects = reader.readFromFile(filePath);
		return listOfSubjects;
	}

	public List<Subject> populateSubjectFromXml(String filePath) {
		InputOutputXml<ArrayList<Subject>> reader = new InputOutputXml<>(new TypeReference<ArrayList<Subject>>() {
		});
		ArrayList<Subject> listOfSubjects = new ArrayList<>();
		listOfSubjects = reader.readFromFile(filePath);
		return listOfSubjects;
	}

	public List<Subject> populateSubjectsFromTxt(String filePath) {
		InputOutputSubjectTxt<ArrayList<Subject>> reader = new InputOutputSubjectTxt<>();
		ArrayList<Subject> listOfSubjects = new ArrayList<>();
		listOfSubjects = reader.readFromFile(filePath);
		return listOfSubjects;
	}

	public void setAllSubjects(ArrayList<Subject> listOfSubjects) {
		for (int i = 0; i < listOfSubjects.size(); i++) {
			PreparedStatement s = getPrepareStatement(
					"INSERT INTO SUBJECTS (NAME, SUBJECT_TYPE, COURSE) VALUES(?,?,?)");
			try {
				s.setString(1, listOfSubjects.get(i).getName());
				s.setString(2, listOfSubjects.get(i).getType().toString());
				s.setInt(3, listOfSubjects.get(i).getCourse());
				s.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Subject> getAll() {
		List<Subject> subjects = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = getStatement().executeQuery("SELECT NAME, SUBJECT_TYPE, COURSE FROM SUBJECTS");
			while (rs.next()) {
				String subjectName = rs.getString("NAME");
				String subjectType = rs.getString("SUBJECT_TYPE");
				int subjectCourse = rs.getInt("COURSE");
				Subject subject = new Subject();
				subject.setName(subjectName);
				subject.setType(SubjectType.valueOf(subjectType));
				subject.setCourse(subjectCourse);
				subjects.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjects;
	}

	@Override
	public Subject getById(Integer id) {
		Subject subject = new Subject();
		try {
			PreparedStatement ps = getPrepareStatement("SELECT NAME, SUBJECT_TYPE, COURSE FROM SUBJECTS WHERE ID=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String subjectName = rs.getString("NAME");
				String subjectType = rs.getString("SUBJECT_TYPE");
				int subjectCourse = rs.getInt("COURSE");
				subject.setName(subjectName);
				subject.setType(SubjectType.valueOf(subjectType));
				subject.setCourse(subjectCourse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subject;
	}

	@Override
	public Subject update(Subject entity) {
		PreparedStatement ps = getPrepareStatement("UPDATE SUBJECTS SET NAME=?, SUBJECT_TYPE=?, COURSE=? WHERE ID=?");
		try {
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getType().toString());
			ps.setInt(3, entity.getCourse());
			ps.setInt(4, entity.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public boolean delete(Integer id) {
		Statement stmt = getStatement();
		String sql = "DELETE FROM SUBJECTS WHERE ID=" + id;
		try {
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean add(Subject entity) {
		PreparedStatement s = getPrepareStatement("INSERT INTO SUBJECTS (NAME, SUBJECT_TYPE, COURSE) VALUES(?,?,?)");
		try {
			s.setString(1, entity.getName());
			s.setString(2, entity.getType().toString());
			s.setInt(3, entity.getCourse());
			s.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
