import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.dao.ClassroomsSubjectTypeDao;
import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.model.Classroom;

import java.sql.Connection;
import java.util.List;

/**
 * Created by rmochetc on 23.11.2016.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("It is working now!");

        System.out.println("=================================");

//        InputOutputJson<ArrayList<Classroom>> classroomManager = new InputOutputJson<>(
//                new TypeReference<ArrayList<Classroom>>() {
//                });
//
//        ArrayList<Classroom> classrooms = classroomManager.readFromFile("room.json");
//
//        System.out.println(classrooms);

        Connection con = DBConnector.getConnection();

        ClassroomDao classroomDao = new ClassroomDao();

        List<Classroom> classroomList = classroomDao.getAll();

        System.out.println(classroomList);

//        System.out.println(new SubjectTypeDao().getEntityById(1));
//        System.out.println(new SubjectTypeDao().getEntityById(2));
//        System.out.println(new SubjectTypeDao().getEntityById(3));
//        System.out.println(new SubjectTypeDao().getEntityById(4));

        System.out.println(new ClassroomsSubjectTypeDao().getSubjectTypesIdByClassroomId(3));
    }
}
