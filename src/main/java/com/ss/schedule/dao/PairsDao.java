package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBUtil;
import com.ss.schedule.model.Pair;
import com.ss.schedule.model.SubjectType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PairsDao extends AbstractDao<Pair> {

    public PairsDao(){
        createPairsTableIfNotExist();
    }
    @Override
    public List<Pair> getAll() {
        List<Pair> pairs=new ArrayList<>();
        String query="SELECT * FROM pairs";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                pairs.add(Pair.valueOf(resultSet.getString("name")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return pairs;
    }

    long getIdByName(Pair pair){
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "SELECT * FROM pairs WHERE name=?"
            );
            preparedStatement.setString(1,pair.name());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Pair getById(long id) {
        String query="SELECT * FROM pairs WHERE id=?";
        Pair pair=null;

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                pair=Pair.valueOf(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pair;
    }

    @Override
    public Pair update(Pair entity) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Pair add(Pair entity) {
        return null;
    }

    public void addTestData(){
        String query="INSERT INTO pairs (NAME ) VALUES (?)";
        try(PreparedStatement prepareStatement=connection.prepareStatement(query)){;
            for (Pair p: Pair.values()
                    ) {
                prepareStatement.setString(1,p.name());
                prepareStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createPairsTableIfNotExist() {
        if (!DBUtil.tableExist(connection, "pairs")) {
            try {
                Statement stmt = connection.createStatement();

                String sql = "CREATE TABLE pairs " +
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

    public long getEntitiIdByName(String name){
        String sql = "SELECT id FROM pairs WHERE name = ?";
        return new DBUtil().getEntityIdByNameExecQuery(connection,sql,name);
    }

    public long getPairIdByName(String name){
        String sql="Select * from pairs where name = (?)";
        return new DBUtil().getEntityIdByNameExecQuery(connection,sql,name);
    }
}
