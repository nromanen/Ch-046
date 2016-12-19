package com.ss.schedule.validator;

import com.ss.schedule.dao.TimeTableDao;
import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Teacher;
import com.ss.schedule.model.TimeTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 04.12.16.
 */
public class TimeTableValidator {

    private List<TimeTable> timeTebles;

    private TimeTable currentTimeTable;

    private TimeTableErrors timeTableErrors = new TimeTableErrors();

    private List<Group> groups = new ArrayList<>();

    private List<Teacher> teachers = new ArrayList<>();

    private List<Classroom> classrooms = new ArrayList<>();

    private TimeTableDao timeTableDao = new TimeTableDao();
    private boolean isValid = true;
    private String type = "add";
    long id = 0;

    public TimeTableValidator(TimeTable currentTimeTable, String type, long id) {
        this.currentTimeTable = currentTimeTable;
        this.type = type;
        this.id = id;
    }

    public TimeTableValidator(TimeTable currentTimeTable) {
        this.currentTimeTable = currentTimeTable;
    }

    private void init() {

        timeTebles = timeTableDao.getTimeTableByDayPair(currentTimeTable.getDay(), currentTimeTable.getPair(), currentTimeTable.getOddnessOfWeek());

        if (type.equals("update")){
            System.out.println(currentTimeTable);

            removebyId(timeTebles,id);
            System.out.println("===============================");
            System.out.println(timeTebles);
        }

        for (TimeTable table: timeTebles){

            try {
                teachers.add(table.getTeacher());

            } catch (NullPointerException e){
                e.printStackTrace();
            }
            try {
                classrooms.add(table.getClassroom());

            } catch (NullPointerException e){
                e.printStackTrace();
            }


            try {
                groups.add(table.getGroup());

            } catch (NullPointerException e){
                e.printStackTrace();
            }

        }
    }

    private void removebyId(List<TimeTable> timeTebles, long id) {
        for (int i = 0; i < timeTebles.size(); i++){
            if(timeTebles.get(i).getId() == id){
                timeTebles.remove(i);
                return;
            }
        }
    }

    public boolean validation(){
        try {

            init();
        } catch (NullPointerException e){
            e.printStackTrace();
            return true;
        }
  ;
        if (classrooms.contains(currentTimeTable.getClassroom())) {
            timeTableErrors.setClassroomError("Classroom " + currentTimeTable.getClassroom().getName() + " is not available now.");
            isValid = false;
        }

        if (groups.contains(currentTimeTable.getGroup())) {

            timeTableErrors.setGroupError("Group" + currentTimeTable.getGroup().getName() + " has another pair now.");
            isValid = false;
        }
        if (teachers.contains(currentTimeTable.getTeacher())) {
            timeTableErrors.setTeacherError("Teacher " + currentTimeTable.getTeacher().getFirstName() + " " +
                    currentTimeTable.getTeacher().getLastName() + " has another pair now.");
            isValid = false;
        }
        return isValid;
    }

    public TimeTableErrors getTimeTableErrors() {
        return timeTableErrors;
    }
}
