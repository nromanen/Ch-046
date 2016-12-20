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
}
