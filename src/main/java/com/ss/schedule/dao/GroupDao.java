package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBUtil;
import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 08.12.16.
 */
public class GroupDao extends AbstractDao<Group> {

    public GroupDao() {
        super();
        createGroupTableIfNotExist();
    }


    private void createGroupTableIfNotExist() {
        if (!DBUtil.tableExist(connection, "groups")) {

            try( Statement stmt = connection.createStatement()) {

                String sql = "create table groups(\n" +
                        "id serial not null,\n" +
                        "name varchar(30),\n" +
                        "count int,\n" +
                        "PRIMARY KEY ( id ),\n" +
                        "parent_id integer references groups(id)\n" +
                        ")";

                stmt.executeUpdate(sql);
                System.out.println("Table groups created!");
            } catch (SQLException e) {
                System.out.println("ERROR! Table groups did not add!");
            }
        }
    }

    @Override
    public List<Group> getAll() {
        List <Group> list = new ArrayList<>();
        String sql = "SELECT * FROM group";
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                list.add(getGroup(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Group getGroup(ResultSet rs) throws SQLException{
        Group group = new Group();
        group.setId(rs.getInt("id"));
        group.setName(rs.getString("name"));
        group.setCount(rs.getInt("count"));
        group.setParent(getById(rs.getInt("parent_id")));

        return group;
    }

    @Override
    public Group getById(long id) {
        return null;
    }

    @Override
    public Group update(Group entity) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Group add(Group entity) {
        return null;
    }
}
