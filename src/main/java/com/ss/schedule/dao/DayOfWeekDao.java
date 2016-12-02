package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBUtil;
import com.ss.schedule.model.DayOfWeek;
import com.ss.schedule.model.Pair;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 01.12.16.
 */
public class DayOfWeekDao extends AbstractDao<DayOfWeek> {
    public DayOfWeekDao(){
        createPairsTableIfNotExist();
    }
    @Override
    public List<DayOfWeek> getAll() {
        List<DayOfWeek> dayOfWeeks=new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM days_of_week");
            while (resultSet.next())
            dayOfWeeks.add(DayOfWeek.valueOf(resultSet.getString("name")));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return dayOfWeeks;
    }

    @Override
    public DayOfWeek getById(long id) {
        String sql="SELECT * FROM days_of_week WHERE id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return DayOfWeek.valueOf(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DayOfWeek update(DayOfWeek entity) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public DayOfWeek add(DayOfWeek entity) {
        return null;
    }

    private void createPairsTableIfNotExist() {
        if (!DBUtil.tableExist(connection, "days_of_week")) {
            try {
                Statement stmt = connection.createStatement();

                String sql = "CREATE TABLE days_of_week " +
                        "(id SERIAL, " +
                        " name text, " +
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(sql);
                System.out.println("Table pairs was created!");
            } catch (SQLException e) {
                System.out.println("ERROR! Table classroom was not created!");
            }
        }
    }

    public void addTestData() throws SQLException {
        String sql="INSERT INTO days_of_week (name) VALUES (?) ";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        for (DayOfWeek day:DayOfWeek.values()
             ) {
            preparedStatement.setString(1,day.name());
            preparedStatement.executeUpdate();
        }

    }
}
