package com.ss.schedule.dbutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	public static boolean tableExist(Connection conn, String tableName) {
        boolean tExists = false;
        try (ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)) {
            while (rs.next()) {
                String tName = rs.getString("TABLE_NAME");

                if (tName != null && tName.equalsIgnoreCase(tableName)) {
                    tExists = true;
                    break;
                }
            }
        } catch (SQLException e){
            System.out.println("ERROR!!");
            throw new RuntimeException();
        }
        return tExists;
}
}
