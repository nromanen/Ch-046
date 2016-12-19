package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.dbutil.DBUtil;
import com.ss.schedule.model.*;
import sun.misc.Timeable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 04.12.16.
 */
public class Group_timetableDao {

    public void addGroupOrSubgroupOfTimetable(TimeTable timeTable) {
        try {
            PreparedStatement preparedStatement = DBConnector.getConnection().prepareStatement(
                    "INSERT INTO group_timetable (group_id, timetable_id) VALUES (?,?)"
            );
                preparedStatement.setLong(1, timeTable.getStudentCommunity().getId());
                preparedStatement.setLong(2, timeTable.getId());
                preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    StudentCommunity getStudentCommunityOfTimetable(TimeTable timeTable){
        String sql = "SELECT *  FROM group_timetable WHERE timetable_id=? ";
        StudentCommunity studentCommunity = null;
        SubgroupsDao subgroupsDao=new SubgroupsDao();
        int i=0;
        try {
            PreparedStatement preparedStatement = DBConnector.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, timeTable.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            int count=0;
            while (resultSet.next())
            {
                count++;
            }

            String sql1="SELECT parent_id, groups.id as smth FROM group_timetable JOIN groups ON group_id=groups.id WHERE timetable_id=?";
            PreparedStatement preparedStatement1=DBConnector.getConnection().prepareStatement(sql1);
            preparedStatement1.setLong(1,timeTable.getId());
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            if (count==1){
                boolean is_group=false;
                if (resultSet1.next()){
                    if (resultSet1.getLong("parent_id")==0)
                        is_group=true;
                    if (is_group){
                        studentCommunity=new GroupDao().getById(resultSet1.getLong("smth"));
                    } else {
                         i=resultSet1.getInt("smth");
                        studentCommunity=new GroupDao().getSubgroupById(resultSet1.getLong("smth"));
                    }
                }

            } else {
                Stream stream=new Stream();
                int counter=0;
                stream.setSubjects(new ArrayList<>());
                GroupDao groupDao=new GroupDao();
                while (resultSet1.next()){
                    Group group=groupDao.getById(resultSet1.getLong("smth"));
                    //name=name.append(group.getName()).append("_");
                    stream.getGroups().add(group);
                    counter=counter+group.getCount();
                    stream.setSubjects(new Groups_subjectsDao().getSubjectsForGroup(group));
                }
                //name.deleteCharAt(name.lastIndexOf("_"));
                stream.setName("");
                studentCommunity=stream;
            }

        }
        catch(
                SQLException e)
        {
            e.printStackTrace();
        }
        return studentCommunity;
    }

    public boolean deleteGroupsOfTimetable(long id){
        try {
            PreparedStatement preparedStatement=DBConnector.getConnection().prepareStatement(
                    "DELETE FROM group_timetable WHERE timetable_id=?"
            );
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createClassroomsTableIfNotExist() {
        if (!DBUtil.tableExist(DBConnector.getConnection(), "classrooms")) {
            try {
                Statement stmt = DBConnector.getConnection().createStatement();

                String sql = "CREATE TABLE CLASSROOMS " +
                        "(id SERIAL, " +
                        " group_id int, " +
                        " timetable_id int, " +
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(sql);
                System.out.println("Table classrooms created!");
            } catch (SQLException e) {
                System.out.println("ERROR! Table classroom did not created!");
            }
        }
    }

}
