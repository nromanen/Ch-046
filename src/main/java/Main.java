import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.SubjectType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 23.11.2016.
 */
public class Main {

    public static void main(String[] args) {

        ClassroomDao classroomDao = new ClassroomDao();

        List<Classroom> classroomList = classroomDao.getAll();

        for (Classroom c: classroomList){
//            c.setCapacity(1000 + c.getCapacity());
//            c.setDescription("Test_desc");
            List<SubjectType> s = new ArrayList<>();
            s.add(SubjectType.SEMINAR);
            c.setTypes(s);
            classroomDao.update(c);
        }

        classroomList = classroomDao.getAll();
        System.out.println(classroomList);

    }
}
