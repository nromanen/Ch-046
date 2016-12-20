package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBUtil;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.StudentCommunity;
import com.ss.schedule.model.Subgroup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 03.12.16.
 */
public class GroupDao extends AbstractDao<Group> {
 private GroupsSubjectsDao groups_subject_dao=new GroupsSubjectsDao();
    int i;
    public GroupDao(){
        createClassroomsTableIfNotExist();
    }
    @Override
    public List<Group> getAll() {
        List<Group> groups=new ArrayList<>();
        Group group;
        String sql = "SELECT * FROM groups WHERE parent_id IS NULL ";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                group=getGroup(resultSet);
                group.setSubjects(new GroupsSubjectsDao().getSubjectsForGroup(group));
                group.setSubgroups(getSubgroupsOfGroup(group));
                groups.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }



    @Override
    public Group getById(long id) {
        Group group=null;
        String sql = "SELECT * FROM groups WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                group = getGroup(rs);
                group.setSubjects(new GroupsSubjectsDao().getSubjectsForGroup(group));
                group.setSubgroups(getSubgroupsOfGroup(group));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    private Group getGroup(ResultSet rs) throws SQLException {
        return new Group(rs.getString("name"),rs.getInt("count"),new ArrayList<>(),rs.getLong("id"));
    }

    public Subgroup getSubgroupById(long id){
        String sql="SELECT * FROM groups WHERE id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Subgroup subgroup=new Subgroup(
                        resultSet.getString("name"),
                        resultSet.getInt("count"),
                        new ArrayList<>(),
                        new GroupDao().getById(resultSet.getLong("parent_id")),
                        resultSet.getLong("id")

                );
                GroupsSubjectsDao groups_subjectsDao=new GroupsSubjectsDao();
                subgroup.setSubjects(groups_subjectsDao.getSubjectsForGroup(subgroup));
                return subgroup;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public StudentCommunity getStudentCommunityById(long id){
        String sql="SELECT * FROM groups WHERE id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                if (resultSet.getLong("parent_id")!=0) {
                    Subgroup subgroup = new Subgroup(
                            resultSet.getString("name"),
                            resultSet.getInt("count"),
                            new ArrayList<>(),
                            new GroupDao().getById(resultSet.getLong("parent_id")),
                            resultSet.getLong("id")
                    );
                    GroupsSubjectsDao groups_subjectsDao = new GroupsSubjectsDao();
                    subgroup.setSubjects(groups_subjectsDao.getSubjectsForGroup(subgroup));
                    return subgroup;
                } else {
                    Group group=new Group(
                            resultSet.getString("name"),
                            resultSet.getInt("count"),
                            new ArrayList<>(),
                            resultSet.getLong("id")
                    );
                    GroupsSubjectsDao groups_subjectsDao = new GroupsSubjectsDao();
                    group.setSubjects(groups_subjectsDao.getSubjectsForGroup(group));
                    group.setSubgroups(getSubgroupsOfGroup(group));
                    return group;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Group update(Group entity) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        new GroupsSubjectsDao().deleteSubjectsOfGroup(id);
        deleteSubgroupsOfGroup(id);
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "DELETE FROM groups WHERE  id=?"
            );
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Group add(Group entity) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "INSERT INTO public.groups (name, count) VALUES (?,?)",
            Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setInt(2,entity.getCount());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int id;
            if (rs.next()){
                id=rs.getInt(1);
                entity.setId(id);
                addSubgroupsOfGroup(entity);
                new GroupsSubjectsDao().addSubjectsOfGroup(entity);
            }
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Subgroup> addSubgroupsOfGroup(Group group){

        String sql="INSERT into groups (name, count, parent_id) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            for (Subgroup sub:group.getSubgroups()
                    ) {
                preparedStatement.setString(1,sub.getName());
                preparedStatement.setInt(2,sub.getCount());
                preparedStatement.setLong(3,sub.getGroup().getId());
                preparedStatement.executeUpdate();
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                while (generatedKeys.next()){
                    long id=generatedKeys.getInt(1);
                    sub.setId(id);
                }
                new GroupsSubjectsDao().addSubjectsOfGroup(sub);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

        public void deleteAllSubgroups(){
            try {
                PreparedStatement preparedStatement=connection.prepareStatement(
                        "DELETE FROM groups WHERE parent_id NOTNULL "
                );
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public List<Subgroup> getSubgroupsOfGroup(Group group){
            List<Subgroup> subgroups=new ArrayList<>();
            try {
                PreparedStatement preparedStatement=connection.prepareStatement(
                        "SELECT * FROM groups WHERE parent_id=?"
                );
                preparedStatement.setLong(1,group.getId());
                ResultSet resultSet = preparedStatement.executeQuery();
                GroupsSubjectsDao groups_subjectsDao=new GroupsSubjectsDao();
                while (resultSet.next()){
                    Subgroup subgroup=new Subgroup(
                            resultSet.getString("name"),
                            resultSet.getInt("count"),
                            new ArrayList<>(),
                            group,
                            resultSet.getLong("id")

                    );
                    subgroup.setSubjects(groups_subjectsDao.getSubjectsForGroup(subgroup));
                    subgroups.add(subgroup);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return subgroups;
        }

        private void deleteSubgroupsOfGroup(long id){
            try {
                PreparedStatement preparedStatement=connection.prepareStatement(
                        "DELETE FROM groups WHERE parent_id=?"
                );
                preparedStatement.setLong(1,id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {

            }
        }

    private void createClassroomsTableIfNotExist() {
        if (!DBUtil.tableExist(connection, "groups")) {
            try {
                Statement stmt = connection.createStatement();
                String sql = "CREATE TABLE groups " +
                        "(id SERIAL, " +
                        " name VARCHAR(30), " +
                        " capacity INTEGER, " +
                        " description VARCHAR(255), " +
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(sql);
                System.out.println("Table groups created!");
            } catch (SQLException e) {
                System.out.println("ERROR! Table groups did not created!");
            }
        }
    }


}
