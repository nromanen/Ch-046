package com.ss.schedule.dao.jdbc;

import com.ss.schedule.dao.AbstractDao;
import com.ss.schedule.model.SubjectType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vyach on 30.11.2016.
 */
public class JdbcSubjectTypeDao extends AbstractDao<SubjectType> {

	public JdbcSubjectTypeDao() throws SQLException {}

	@Override
	public SubjectType add(SubjectType subjectType) throws SQLException {
		String request = "INSERT INTO subject_types (name, amount) VALUES (?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setString(1, subjectType.toString());
		preparedStatement.setInt(2, subjectType.getMaxStudentAmount());
		preparedStatement.execute();
		subjectType.setId(getSubjectTypeId(subjectType.toString()));
		return subjectType;
	}

	private long getSubjectTypeId(String subjectTypeName) throws SQLException {
		String request = "SELECT id FROM subject_types WHERE name = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setString(1, subjectTypeName);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		return rs.getLong("id");
	}

	@Override
	public SubjectType update(SubjectType subjectType) throws SQLException {
		String request = "UPDATE subject_types SET name = ?, amount = ? WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setString(1, subjectType.toString());
		preparedStatement.setInt(2, subjectType.getMaxStudentAmount());
		preparedStatement.setLong(3, subjectType.getId());
		preparedStatement.executeUpdate();
		return getById(subjectType.getId());
	}

	@Override
	public boolean delete(long id) throws SQLException {
		String request = "DELETE FROM subject_types WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setLong(1, id);
		return preparedStatement.executeUpdate() == 1;
	}

	@Override
	public SubjectType getById(long id) throws SQLException {
		String request = "SELECT * FROM subject_types WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setLong(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		return SubjectType.valueOf(rs.getString("name"));
	}

	@Override
	public List<SubjectType> getAll() throws SQLException {
		String request = "SELECT * FROM subject_types ORDER BY id ASC";
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = statement.executeQuery(request);

		List<SubjectType> subjectTypes = new ArrayList<>();
		while (rs.next()) {
			subjectTypes.add(SubjectType.valueOf(rs.getString("name")));
		}

		return subjectTypes;
	}
}
