package com.ss.schedule.services;

import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.dao.TeacherDao;
import com.ss.schedule.dao.TimeTableDao;
import com.ss.schedule.dto.GroupSubjectDto;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.TimeTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 18.12.16.
 */
public class TimeTableService {

    private TimeTableDao timeTableDao = new TimeTableDao();

    public boolean isTimeTable(long subjectId, long groupId) {
        return timeTableDao.isTimeTable(subjectId, groupId);
    }

    public long getMaxPair(long groupId, long oddnesId) {
        return timeTableDao.getMaxPair(groupId, oddnesId);
    }

    public List<TimeTable> getByGroupDayOddnes(long groupId, long dayId, long oddnesId) {

        List<TimeTable> timeTablesByDay = timeTableDao.getByGroup(groupId, dayId, oddnesId);
        List<TimeTable> timeTables = new ArrayList<>();
        for (int i = 0; i < getMaxPair(groupId, oddnesId); i ++) {
            timeTables.add(getByPairId(timeTablesByDay, i+1));
        }
        return timeTables;
    }

    private TimeTable getByPairId(List<TimeTable> timeTables, long pairId) {

        if (timeTables == null){
            return null;
        }
        for (TimeTable timeTable : timeTables){

            if (timeTable.getPair().getId() == pairId){
                return timeTable;
            }
        }
        return null;
    }

    public List<GroupSubjectDto> getUnsetSubjects() {
        GroupDao groupDao = new GroupDao();
        List<Group> groups = groupDao.getAll();
        List<GroupSubjectDto> groupSubjectDtos = new ArrayList<>();
        TimeTableDao timeTableDao = new TimeTableDao();

        for (Group g : groups) {
            for (Subject s : g.getSubjects()) {
                if (!timeTableDao.isTimeTable(s.getId(), g.getId())) {
                    GroupSubjectDto groupSubjectDto = new GroupSubjectDto();
                    groupSubjectDto.setGroup(g);
                    groupSubjectDto.setSubject(s);
                    groupSubjectDtos.add(groupSubjectDto);
                }
            }
        }
        return groupSubjectDtos;
    }

    public List<TimeTable> getAll() {
        return timeTableDao.getAll();
    }

    public void delete(long id) {
        timeTableDao.delete(id);
    }

    public void deleteByClassroom(long classroomId) {
        timeTableDao.deleteByClassroom(classroomId);
    }

    public long getMaxTeacherPair(long teacherId, long oddnesId) {
        return timeTableDao.getMaxTeacherPair(teacherId, oddnesId);
    }

    public List<TimeTable> getByTeacherDayOddnes(long teacherId, int dayId, long oddnesId) {

        List<TimeTable> timeTablesByDay = timeTableDao.getByTeacher(teacherId, dayId, oddnesId);
        List<TimeTable> timeTables = new ArrayList<>();
        for (int i = 0; i < getMaxTeacherPair(teacherId, oddnesId); i ++) {
            timeTables.add(getByPairId(timeTablesByDay, i+1));
        }
        return timeTables;
    }

    public TimeTable getById(long id) {
        return timeTableDao.getById(id);
    }

    public void update(TimeTable timeTable) {
        timeTableDao.update(timeTable);
    }

    public void add(TimeTable timeTable) {
        timeTableDao.add(timeTable);
    }
}
