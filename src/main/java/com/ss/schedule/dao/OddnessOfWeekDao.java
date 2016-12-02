package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBUtil;
import com.ss.schedule.model.DayOfWeek;
import com.ss.schedule.model.OddnessOfWeek;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OddnessOfWeekDao extends AbstractDao<OddnessOfWeek> {

    public OddnessOfWeekDao(){
        createPairsTableIfNotExist();
    }
    @Override
    public List<OddnessOfWeek> getAll() {
        List<OddnessOfWeek> oddnessOfWeeks=new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM oddness_of_week");
            while (resultSet.next())
                oddnessOfWeeks.add(OddnessOfWeek.valueOf(resultSet.getString("name")));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return oddnessOfWeeks;
    }

    @Override
    public OddnessOfWeek getById(long id) {
        String sql="select * from oddness_of_week where id =(?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return OddnessOfWeek.valueOf(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OddnessOfWeek update(OddnessOfWeek entity) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public OddnessOfWeek add(OddnessOfWeek entity) {
        return null;
    }

    private void createPairsTableIfNotExist() {
        if (!DBUtil.tableExist(connection, "oddness_of_week")) {
            try {
                Statement stmt = connection.createStatement();

                String sql = "CREATE TABLE oddness_of_week " +
                        "(id SERIAL, " +
                        " name text, " +
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(sql);
                System.out.println("Table oddness_of_week was created!");
            } catch (SQLException e) {
                System.out.println("ERROR! Table classroom was not created!");
            }
        }
    }

    public void addTestData() throws SQLException {
        String sql="INSERT INTO oddness_of_week (name) VALUES (?) ";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        for (OddnessOfWeek oddnessOfWeek: OddnessOfWeek.values()
                ) {
            preparedStatement.setString(1,oddnessOfWeek.name());
            preparedStatement.executeUpdate();
        }
    }
}
