package com.ss.schedule.dao;

import com.ss.schedule.dbutil.DBUtil;
import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.SubjectType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 27.11.16.
 */
public class ClassroomDao extends AbstractDao<Classroom, Integer> {

    public ClassroomDao() {
        initClassroomTable();
    }

    private void initClassroomTable() {

        if (!DBUtil.tableExist(connection, "classrooms")){
            createClassroomsTable();
        }

        if (!DBUtil.tableExist(connection, "subjectTypes")){
            createSubjectTypesTable();
            fillSubjectTypesTable();
        }
        if (!DBUtil.tableExist(connection, "classrooms_subjectTypes")){
            createClassroomsClassroomTypesTable();
        }



    }


    @Override
    public List<Classroom> getAll() {

        List <Classroom> list = new ArrayList<>();

        try {
            ResultSet rs = getStatement().executeQuery("SELECT * FROM classrooms");
            while (rs.next()){
                list.add(getClassroom(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Classroom getById(Integer id) {
        return null;
    }

    @Override
    public Classroom update(Classroom entity) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean add(Classroom entity) {

        String sql = "INSERT INTO classrooms " +
                "(name, capacity, description) " +
                "VALUES (?, ?, ?)";

        PreparedStatement ps = getPrepareStatement(sql);
        try {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getCapacity());
            ps.setString(3, entity.getDescription());

            ps.executeUpdate();

            int classroomId = getEntityIdByName(entity.getName());
            ClassroomsSubjectTypeDao cDao = new ClassroomsSubjectTypeDao();
            SubjectTypeDao sDao = new SubjectTypeDao();

            for (SubjectType st :
                    entity.getTypes()) {
                cDao.create(classroomId, sDao.getEntityIdByName(st.toString()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;


    }

    public int getEntityIdByName(String name) {
        try {
            PreparedStatement ps = getPrepareStatement("SELECT id FROM classrooms WHERE name = ?");
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private Classroom getClassroom (ResultSet rs) throws SQLException{

        SubjectTypeDao sDao = new SubjectTypeDao();
        Classroom classroom = new Classroom();
        classroom.setId(rs.getInt("id"));
        classroom.setName(rs.getString("name"));
        classroom.setCapacity(rs.getInt("capacity"));
        classroom.setDescription(rs.getString("description"));

        classroom.setTypes(sDao.getEntitiesByClassroomId(rs.getInt("id")));

        return classroom;
    }

    private void createClassroomsTable(){
        try {
            Statement stmt = connection.createStatement();

            String sql = "CREATE TABLE CLASSROOMS " +
                    "(id SERIAL, " +
                    " name VARCHAR(30), " +
                    " capacity INTEGER, " +
                    " description VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            System.out.println("Table classrooms created!");
        } catch (SQLException e){
            System.out.println("ERROR! Table classroom did not created!");
        }
    }

    private void createSubjectTypesTable(){
        try {
            Statement statment = connection.createStatement();

            String sql = "CREATE TABLE subjectTypes " +
                    "(id SERIAL, " +
                    " name VARCHAR(30), "+
                    " maxStudentAmount INTEGER, " +
                    "PRIMARY KEY (id)) ";
            statment.executeUpdate(sql);
            System.out.println("Table subjectTypes created!");
        } catch (SQLException e){
            System.out.println("ERROR! Table subjectTypes did not create!");
        }
    }

    private void fillSubjectTypesTable() {
        PreparedStatement ps = getPrepareStatement("Insert into subjectTypes (name, maxStudentAmount) values (?, ?)");
        try {
            ps.setString(1, "LECTURE");
            ps.setInt(2, 0);
            ps.executeUpdate();
            ps.setString(1, "SEMINAR");
            ps.setInt(2, 0);
            ps.executeUpdate();
            ps.setString(1, "PRACTICE");
            ps.setInt(2, 15);
            ps.executeUpdate();
            ps.setString(1, "LAB");
            ps.setInt(2, 7);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createClassroomsClassroomTypesTable() {

        try {
            Statement stmt = connection.createStatement();

            String sql = "create table classrooms_subjectTypes " +
                    "(id serial, " +
                    "classroom_id integer references classrooms(id), " +
                    "subject_types_id integer references subjectTypes(id))";

            stmt.executeUpdate(sql);
            System.out.println("Table classrooms_subject_types created!");
        } catch (SQLException e){
            System.out.println("ERROR! Table classrooms_subject_types did not create!");
        }


    }

}
