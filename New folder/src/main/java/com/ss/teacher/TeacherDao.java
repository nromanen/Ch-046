package com.ss.teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 07.12.2016.
 */
public class TeacherDao {
    public static List<Teacher> getAll() {
        List<Teacher> TeachersList = new ArrayList<>();
        try {
            PreparedStatement ps = DBConnector.getConnection().prepareStatement("SELECT * FROM teachers order by lastname");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getString("firstname"), rs.getString("lastname"), rs.getInt("id"));
                TeachersList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return TeachersList;
    }
}
