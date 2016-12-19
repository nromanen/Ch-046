package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subgroup;
import com.ss.schedule.model.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 04.12.16.
 */
public class SubgroupsDao {
    void addSubgroupsOfGroup(Group group){
        String sql="INSERT INTO subgroups (name, parent_id) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement= DBConnector.getConnection().prepareStatement(sql);
            for (Subgroup subgroup :
                 group.getSubgroups()){
                preparedStatement.setString(1,subgroup.getName());
                preparedStatement.setLong(2,group.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    List<Subgroup> getSubgroupsOfGroup(Group group){
        try {
            PreparedStatement statement=DBConnector.getConnection().prepareStatement(
                    "SELECT * FROM subgroups WHERE subgroups.parent_id = ?"
            );
            statement.setLong(1,group.getId());
            ResultSet resultSet = statement.executeQuery();
            return formSubgroups(resultSet,group);
        }
         catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Subgroup> formSubgroups(ResultSet resultSet,Group group){
        List<Subgroup> subgroups=new ArrayList<>();
        try {
            while (resultSet.next()){
                subgroups.add(new Subgroup(
                        resultSet.getString("name"),
                        0,
                        new ArrayList<>(),
                        group,
                        resultSet.getLong("id")
                ));
            }
            return subgroups;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    Subgroup getById(long id){
        try {
            PreparedStatement preparedStatement=DBConnector.getConnection().prepareStatement(
                    "SELECT  * FROM subgroups WHERE id=?"
            );
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return formSubgroup(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Subgroup formSubgroup(ResultSet resultSet){
        try {
            return new Subgroup(
                    resultSet.getString("name"),
                    0,
                    new ArrayList<>(),
                    new GroupDao().getById(resultSet.getLong("parent_id")),
                    resultSet.getLong("id")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
