package com.ss.schedule.dbutil;

import com.ss.schedule.dao.AbstractDao;
import com.ss.schedule.model.DayOfWeek;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 27.11.16.
 */
public class DBUtil {

    public static boolean tableExist(Connection conn, String tableName) {
        boolean tExists = false;
        try (ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)) {
            while (rs.next()) {
                String tName = rs.getString("TABLE_NAME");
                System.out.println("Table " + tName + " exist");

                if (tName != null && tName.equals(tableName)) {
                    tExists = true;
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR!!");
            throw new RuntimeException();
        }
        return tExists;
    }

//    public <T> T getById(String sql) throws SQLException {
//        PreparedStatement preparedStatement=DBConnector.getConnection().prepareStatement(sql);
//
//    }

    public long getEntityIdByNameExecQuery(Connection connection, String sql,String name){
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return rs.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
