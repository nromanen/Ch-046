package com.ss.schedule.dao.jdbc;

import com.ss.schedule.dao.AbstractDao;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vyach on 28.11.2016.
 */
public class JdbcSubjectDao extends AbstractDao<Subject> {

	public JdbcSubjectDao(String propertiesFilePath) throws SQLException {
		super(propertiesFilePath);
	}

	@Override
	public Subject add(Subject subject) throws SQLException {
		getConnection();
		String request = "INSERT INTO subjects (name, type_id, course) VALUES (?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setString(1, subject.getName());
		preparedStatement.setLong(2, subject.getType().getId());
		preparedStatement.setInt(3, subject.getCourse());
		preparedStatement.execute();
		subject.setId(getSubjectId(subject));
		return subject;
	}

	private long getSubjectId(Subject subject) throws SQLException {
		String request = "SELECT id FROM subjects WHERE name = ? AND type_id = ? AND course = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setString(1, subject.getName());
		preparedStatement.setLong(2, subject.getType().getId());
		preparedStatement.setInt(3, subject.getCourse());
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		return rs.getLong("id");
	}

	@Override
	public Subject update(Subject subject) throws SQLException {
		getConnection();
		String request = "UPDATE subjects SET name = ?, type_id = ?, course = ? WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setString(1, subject.getName());
		preparedStatement.setLong(2, subject.getType().getId());
		preparedStatement.setInt(3, subject.getCourse());
		preparedStatement.setLong(4, subject.getId());
		preparedStatement.executeUpdate();
		return getById(subject.getId());
	}

	@Override
	public boolean delete(long id) throws SQLException {
		getConnection();
		String request = "DELETE FROM subjects WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setLong(1, id);
		return preparedStatement.executeUpdate() == 1; // because method executeUpdate() return 1 if operation finished successfully
	}

	@Override
	public Subject getById(long id) throws SQLException {
		getConnection();
		String request = "SELECT s.id, s.name, t.name, s.course FROM subjects AS s " +
				"JOIN subject_types AS t " +
				"ON s.type_id = t.id " +
				"WHERE s.id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setLong(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		return createSubject(rs);
	}

	@Override
	public List<Subject> getAll() throws SQLException {
		getConnection();
		String request = "SELECT s.id, s.name, t.name, s.course FROM subjects AS s " +
				"JOIN subject_types AS t " +
				"ON s.type_id = t.id " +
				"ORDER BY s.course ASC, s.name ASC";
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = statement.executeQuery(request);

		List<Subject> subjects = new ArrayList<>();
		while (rs.next()) {
			subjects.add(createSubject(rs));
		}

		return subjects;
	}

	public List<Subject> getSubjectsByGroupId(long groupId) throws SQLException {
		getConnection();
		String request = "SELECT s.id, s.name, t.name, s.course " +
				"FROM subjects AS s " +
				"JOIN group_subject_map AS map ON s.id = map.subject_id " +
				"JOIN subject_types AS t ON s.type_id = t.id " +
				"WHERE map.group_id = ?" +
				"ORDER BY s.name";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setLong(1, groupId);
		ResultSet rs = preparedStatement.executeQuery();

		List<Subject> subjects = new ArrayList<>();
		while (rs.next()) {
			subjects.add(createSubject(rs));
		}

		return subjects;
	}

	public List<Subject> getSubjectsByCourse(int course) throws SQLException {
		getConnection();
		String request = "SELECT s.id, s.name, t.name, s.course " +
				"FROM subjects AS s " +
				"JOIN subject_types AS t ON s.type_id = t.id " +
				"WHERE s.course = ?" +
				"ORDER BY s.name";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setInt(1, course);

		ResultSet rs = preparedStatement.executeQuery();

		List<Subject> subjects = new ArrayList<>();
		while (rs.next()) {
			subjects.add(createSubject(rs));
		}

		return subjects;
	}

	public Subject getSubject(String name, String type, int course) throws SQLException {
		getConnection();
		String request = "SELECT s.id, s.name, t.name, s.course " +
				"FROM subjects AS s " +
				"JOIN subject_types AS t ON s.type_id = t.id " +
				"WHERE s.name = ? AND t.name = ? AND s.course = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, type);
		preparedStatement.setInt(3, course);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		return createSubject(rs);
	}

	public List<Subject> getUnusedSubjects() throws SQLException {
		getConnection();
		String request = "SELECT s.id, s.name, t.name, s.course, map.group_id FROM subjects AS s " +
				"LEFT JOIN group_subject_map AS map " +
				"ON s.id = map.subject_id " +
				"JOIN subject_types AS t " +
				"ON s.type_id = t.id " +
				"WHERE map.group_id IS NULL " +
				"ORDER BY s.course, s.name";
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = statement.executeQuery(request);

		List<Subject> subjects = new ArrayList<>();
		while (rs.next()) {
			subjects.add(createSubject(rs));
		}

		return subjects;
	}

	private Subject createSubject(ResultSet rs) throws SQLException {
		Subject subject = new Subject();
		subject.setId(rs.getLong(1));
		subject.setName(rs.getString(2));
		subject.setType(SubjectType.valueOf(rs.getString(3)));
		subject.setCourse(rs.getInt(4));
		return subject;
	}
}
