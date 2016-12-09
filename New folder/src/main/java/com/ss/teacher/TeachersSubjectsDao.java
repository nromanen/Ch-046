package com.ss.teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.teacher.DBConnector;


public class TeachersSubjectsDao {
	public void add(Teacher teacher) {
		PreparedStatement pis;
		try {
			pis = DBConnector.getConnection()
					.prepareStatement("INSERT INTO teachers_subjects (subject_id,teacher_id) VALUES (?,?)");
			for (Subject subject : teacher.getList()) {
				pis.setLong(1, subject.getId());
				pis.setLong(2, teacher.getId());
				pis.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void setSubject(int teacherId, int subjectId) {
		PreparedStatement pis;
		try {
			pis = DBConnector.getConnection()
					.prepareStatement("INSERT INTO teachers_subjects (subject_id,teacher_id) VALUES (?,?)");

			pis.setLong(1, subjectId);
			pis.setLong(2, teacherId);
			pis.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean delete(int subjectId, int teacherId) {
		PreparedStatement ps;
		try {
			ps = DBConnector.getConnection()
					.prepareStatement("delete from teachers_subjects where teacher_id=? and subject_id=?");
			ps.setInt(1, teacherId);
			ps.setInt(2, subjectId);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean delete(Teacher teacher, Subject subject) {
		PreparedStatement ps;
		try {
			ps = DBConnector.getConnection()
					.prepareStatement("delete from teachers_subjects where teacher_id=? and subject_id=?");
			ps.setInt(1, subject.getId());
			ps.setInt(2, teacher.getId());
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
						.prepareStatement("select subjects.id,name,type,course from subjects join teachers_subjects  on subject_id=subjects.id where teacher_id=?");
				ps.setInt(1, teacher.getId());
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
	 
	 public List<Subject> getSubjects(int id)
	 {
		 List<Subject> subjectsList = new ArrayList<>();
		 try {
				PreparedStatement ps =DBConnector.getConnection()
						.prepareStatement("select subjects.id,name,type,course from subjects join teachers_subjects  on subject_id=subjects.id where teacher_id=?");
				ps.setInt(1, id);
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
}

