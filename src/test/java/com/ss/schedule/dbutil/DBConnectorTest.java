package com.ss.schedule.dbutil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.dao.SubjectDao;
import com.ss.schedule.dao.TimeTableDao;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.TimeTable;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by oleg on 29.11.16.
 */
public class DBConnectorTest {
    @Test
    public void testGetConnection() throws Exception {

//        SubjectTypeDao subjectTypeDao=new SubjectTypeDao();
//        subjectTypeDao.createSubjectTypesTableIfNotExist();

        InputOutputJson<List<Classroom>> inputOutputJson=new
                InputOutputJson<>(new TypeReference<List<Classroom>>() {
        });
        List<Classroom> classrooms = inputOutputJson.readFromFile("/media/oleg/D254AF9D54AF8339/" +
                "Users/Oleg/OneDrive/Education3/JavaFx/TimeTable1/" +
                "Ch-046 (copy)/src/test/resources/room.json");
//        ClassroomDao classroomDao=new ClassroomDao();
//        for (Classroom classroom:classrooms){
//            classroomDao.add(classroom);
//        }

        SubjectDao subjectDao=new SubjectDao();

//        ArrayList<Subject> subjects = subjectDao.populateSubjects("/media/oleg/D254AF9D54AF8339/" +
//                "Users/Oleg/OneDrive/Education3/JavaFx/TimeTable1/" +
//                "Ch-046 (copy)/src/test/resources/subject.json", "json");
//        subjectDao.createTable();
//        subjectDao.addAll(subjects);
        TimeTableDao tableDaoTEST=new TimeTableDao();
        List<TimeTable> all = tableDaoTEST.getAll();
        System.out.println(all);

    }



}