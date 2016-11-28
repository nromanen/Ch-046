import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.dao.ClassroomsSubjectTypeDao;
import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.SubjectType;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 23.11.2016.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("It is working now!");

        InputOutputJson<List<Classroom>> classroomManager = new InputOutputJson<>(
                new TypeReference<List<Classroom>>() {
                });

        ArrayList<Classroom> classrooms = (ArrayList<Classroom>) classroomManager.readFromFile("room.json");

        System.out.println(classrooms);

        System.out.println("=================================");


        //Connection con = DBConnector.getConnection();

        ClassroomDao classroomDao = new ClassroomDao();

        for (Classroom room :   classrooms) {
            classroomDao.create(room);
        }

        List<Classroom> classroomList = classroomDao.getAll();

        System.out.println(classroomList);

        classroomManager.writeToFile("rooms.json", classroomList);

    }
}
