package com.ss.schedule.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.dao.TimeTableDao;
import com.ss.schedule.io.InputOutputClassroomTxt;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.io.InputOutputXml;
import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.SubjectType;
import com.ss.schedule.model.TimeTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 18.12.16.
 */
public class ClassroomService {
    private ClassroomDao classroomDao = new ClassroomDao();
    private TimeTableDao timeTableDao = new TimeTableDao();

    public List<Classroom> getAll() {
        List<Classroom> classrooms = classroomDao.getAll();
        return classrooms;
    }

    public void delete(long id) {
        classroomDao.delete(id);
    }

    public boolean canDeleteClassroom(long id) {

        List<TimeTable> timeTables = timeTableDao.getByClassroom(id);
        if(!timeTables.isEmpty()) {
            return false;
        }
        return true;
    }

    public Classroom getClassroomById(long id){
        return classroomDao.getById(id);
    }

    public List<TimeTable> getTimeTableByClassroom(long id) {
        List<TimeTable> timeTables = timeTableDao.getByClassroom(id);
        return timeTables;
    }

    public void update(Classroom classroom) {
        classroomDao.update(classroom);
    }

    public Classroom add(Classroom classroom) {
        return classroomDao.add(classroom);
    }

    public boolean addClassroomsToDB(String path) {

            List<Classroom> classrooms = new ArrayList<>();
            if(path.endsWith(".json")) {
                try {
                    InputOutputJson<List<Classroom>> classroomManager = new InputOutputJson<>(
                            new TypeReference<List<Classroom>>() {
                            });

                    classrooms = (ArrayList<Classroom>) classroomManager.readFromFile(path);

                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            } else if (path.endsWith(".xml")){
                try {
                    InputOutputXml<List<Classroom>> classroomManager = new InputOutputXml<>(
                            new TypeReference<List<Classroom>>() {
                            });

                    classrooms = (ArrayList<Classroom>) classroomManager.readFromFile(path);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            } else if(path.endsWith(".txt")){
                try {
                    InputOutputClassroomTxt classroomManager = new InputOutputClassroomTxt();
                    classrooms = (ArrayList<Classroom>) classroomManager.readFromFile(path);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            } else {
                return false;
            }

            try {
                ClassroomDao classroomDao = new ClassroomDao();
                for (Classroom room : classrooms) {
                    classroomDao.add(room);
                }
            } catch (Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }

    public List<Classroom> getByTypeAndCapacity(SubjectType type, int count) {
        return classroomDao.getByTypeAndCapacity(type, count);
    }

    public Classroom getById(long id) {
        return classroomDao.getById(id);
    }
}

