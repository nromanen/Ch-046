import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.dao.SubjectDao;
import com.ss.schedule.io.InputOutputClassroomTxt;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.io.InputOutputTimeTableTxt;
import com.ss.schedule.io.InputOutputXml;
import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.SubjectType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 23.11.2016.
 */
public class Main {


    public static void main(String[] args) {

        ClassroomDao classroomDao = new ClassroomDao();
//
//
        InputOutputJson<List<Classroom>> classroomManager = new InputOutputJson<>(
                new TypeReference<List<Classroom>>() {
                });

        List<Classroom> classrooms = (ArrayList<Classroom>) classroomManager.readFromFile("room.json");

        InputOutputXml<List<Classroom>> xmlManager = new InputOutputXml<>(
                new TypeReference<List<Classroom>>() {
                });
        xmlManager.writeToFile("room.xml", classrooms);

        InputOutputClassroomTxt txtManager = new InputOutputClassroomTxt();

        txtManager.writeToFile("room.txt", classrooms);

        List<Classroom> testList = txtManager.readFromFile("room.txt");


//
//        System.out.println("=================================");
//
//
//        for (Classroom room :   classrooms) {
//            classroomDao.add(room);
//        }

//        List<Classroom> classroomList = classroomDao.getAll();
//
//        for (Classroom c: classroomList){
////            c.setCapacity(1000 + c.getCapacity());
////            c.setDescription("Test_desc");
//            List<SubjectType> s = new ArrayList<>();
//            s.add(SubjectType.SEMINAR);
//            c.setTypes(s);
//            classroomDao.update(c);
//        }

        SubjectDao subjectDao = new SubjectDao();
        List<Classroom> classroomList = classroomDao.getByTypeAndCapacity(SubjectType.LECTURE, 30);
        System.out.println(classroomList);

    }
}
