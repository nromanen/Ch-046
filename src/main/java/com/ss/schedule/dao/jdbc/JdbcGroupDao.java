package com.ss.schedule.dao.jdbc;

import com.ss.schedule.dao.AbstractDao;
import com.ss.schedule.model.Group;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vyach on 28.11.2016.
 */
public class JdbcGroupDao extends AbstractDao<Group> {

	public JdbcGroupDao() throws SQLException {}

	@Override
	public Group add(Group group) throws SQLException {
		String request = "INSERT INTO groups (name, count) VALUES (?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setString(1, group.getName());
		preparedStatement.setInt(2, group.getCount());
		preparedStatement.execute();
		group.setId(getGroupId(group.getName()));
		return group;
	}

	private long getGroupId(String groupName) throws SQLException {
		String request = "SELECT id FROM groups WHERE name = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setString(1, groupName);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		return rs.getLong("id");
	}

	@Override
	public Group update(Group group) throws SQLException {
		String request = "UPDATE groups SET name = ?, count = ? WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setString(1, group.getName());
		preparedStatement.setInt(2, group.getCount());
		preparedStatement.setLong(3, group.getId());
		preparedStatement.executeUpdate();
		return getById(group.getId());
	}

	@Override
	public boolean delete(long id) throws SQLException {
		String request = "DELETE FROM groups WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setLong(1, id);
		return preparedStatement.executeUpdate() == 1;
	}

	@Override
	public Group getById(long id) throws SQLException {
		String request = "SELECT * FROM groups WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(request);
		preparedStatement.setLong(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		return createGroup(rs);
	}

	@Override
	public List<Group> getAll() throws SQLException {
		String request = "SELECT * FROM groups ORDER BY id ASC";
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = statement.executeQuery(request);

		List<Group> groups = new ArrayList<>();
		while (rs.next()) {
			groups.add(createGroup(rs));
		}

		return groups;
	}

	private Group createGroup(ResultSet rs) throws SQLException {
		Group group = new Group();
		group.setId(rs.getLong("id"));
		group.setName(rs.getString("name"));
		group.setCount(rs.getInt("count"));
		return group;
	}
}
