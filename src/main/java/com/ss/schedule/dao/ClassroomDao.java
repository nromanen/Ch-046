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
public class ClassroomDao extends AbstractDao<Classroom> {

    private ClassroomsSubjectTypeDao classroomsSubjectTypeDao = new ClassroomsSubjectTypeDao();
    private SubjectTypeDao subjectTypeDao = new SubjectTypeDao();

    public ClassroomDao() {
        initDBForWorkWithClassrooms();
    }

    private void initDBForWorkWithClassrooms() {
        createClassroomsTableIfNotExist();
        subjectTypeDao.createSubjectTypesTableIfNotExist();
        classroomsSubjectTypeDao.createClassroomsSubjectTypesTableIfNotExist();
    }

    public List<Classroom> getByTypeAndCapacity(SubjectType type, long capacity){
        List <Classroom> list = new ArrayList<>();
        System.out.println(type);
        long TypeInt = subjectTypeDao.getEntityIdByName(type.toString());
        String sql = "select classrooms.id, name, capacity, description from classrooms " +
                "join classrooms_subjecttypes " +
                "on subject_types_id = ? and classrooms.id = classroom_id " +
                "where capacity >= ? group by classrooms.id, name, capacity, description  order by capacity";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, TypeInt);
            ps.setLong(2, capacity);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(getClassroom(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Classroom> getAll() {
        List <Classroom> list = new ArrayList<>();
        String sql = "SELECT * FROM classrooms order by capacity";
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                list.add(getClassroom(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Classroom getById(long id) {

        Classroom classroom = null;
        String sql = "SELECT * FROM classrooms WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                classroom = getClassroom(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classroom;
    }

    @Override
    public Classroom update(Classroom entity) {
        String sql = "UPDATE classrooms SET name = ?, capacity = ?, description = ? where id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getCapacity());
            ps.setString(3, entity.getDescription());
            ps.setLong(4, entity.getId());
            ps.executeUpdate();

            classroomsSubjectTypeDao.update(entity.getId(), entity.getTypes());
            return entity;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(long id) {

        String sql = "DELETE FROM classrooms WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql);) {
            classroomsSubjectTypeDao.deleteByClassroomId(id);
            ps.setLong(1, id);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Classroom was not deleted!");
    }

    @Override
    public Classroom add(Classroom entity) {

        List<Classroom> classrooms = getAll();
        if (!classrooms.contains(entity)) {

            String sql = "INSERT INTO classrooms " +
                    "(name, capacity, description) " +
                    "VALUES (?, ?, ?)";

            try( PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, entity.getName());
                ps.setInt(2, entity.getCapacity());
                ps.setString(3, entity.getDescription());

                ps.executeUpdate();

                long classroomId = getEntityIdByName(entity.getName());

                for (SubjectType st :  entity.getTypes()) {
                    classroomsSubjectTypeDao.add(classroomId, subjectTypeDao.getEntityIdByName(st.toString()));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return getById(getEntityIdByName(entity.getName()));
        }
        return null;
    }

    public long getEntityIdByName(String name) {
        String sql = "SELECT id FROM classrooms WHERE name = ?";
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

    private Classroom getClassroom (ResultSet rs) throws SQLException{

        Classroom classroom = new Classroom();
        classroom.setId(rs.getInt("id"));
        classroom.setName(rs.getString("name"));
        classroom.setCapacity(rs.getInt("capacity"));
        classroom.setDescription(rs.getString("description"));

        classroom.setTypes(subjectTypeDao.getEntitiesByClassroomId(rs.getLong("id")));

        return classroom;
    }

    private void createClassroomsTableIfNotExist() {
        if (!DBUtil.tableExist(connection, "classrooms")) {
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
            } catch (SQLException e) {
                System.out.println("ERROR! Table classroom did not created!");
            }
        }
    }
}
