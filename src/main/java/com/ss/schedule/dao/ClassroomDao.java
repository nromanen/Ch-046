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
        classroomsSubjectTypeDao.createClassroomsClassroomTypesTableIfNotExist();
    }

    @Override
    public List<Classroom> getAll() {
        List <Classroom> list = new ArrayList<>();
        try {
            ResultSet rs = getStatement().executeQuery("SELECT * FROM classrooms order by capacity");
            while (rs.next()){
                list.add(getClassroom(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Classroom getById(int id) {

        Classroom classroom = null;

        try {
            PreparedStatement ps = getPrepareStatement("SELECT * FROM classrooms WHERE id = ?");
            ps.setInt(1, id);

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
    public boolean update(Classroom entity) {


        PreparedStatement ps = getPrepareStatement("UPDATE classrooms SET name = ?, capacity = ?, description = ? where id = ?");
        try {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getCapacity());
            ps.setString(3, entity.getDescription());
            ps.setInt(4, entity.getId());
            ps.executeUpdate();

            classroomsSubjectTypeDao.update(entity.getId(), entity.getTypes());
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {

        PreparedStatement ps = getPrepareStatement("DELETE FROM classrooms WHERE id = ?");
        try {
            classroomsSubjectTypeDao.deleteByClassroomId(id);
            ps.setInt(1, id);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Classroom was not deleted!");
    }

    @Override
    public boolean add(Classroom entity) {

        List<Classroom> classrooms = getAll();
        if (!classrooms.contains(entity)) {

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

                for (SubjectType st :  entity.getTypes()) {
                    classroomsSubjectTypeDao.add(classroomId, subjectTypeDao.getEntityIdByName(st.toString()));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
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

        Classroom classroom = new Classroom();
        classroom.setId(rs.getInt("id"));
        classroom.setName(rs.getString("name"));
        classroom.setCapacity(rs.getInt("capacity"));
        classroom.setDescription(rs.getString("description"));

        classroom.setTypes(subjectTypeDao.getEntitiesByClassroomId(rs.getInt("id")));

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
