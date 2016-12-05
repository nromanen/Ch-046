package com.ss.schedule.validator;

import com.ss.schedule.controllers.GroupsBundle;
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
    private boolean isValide = true;

    public TimeTableValidator(TimeTable currentTimeTable) {
        this.currentTimeTable = currentTimeTable;
    }

    private void init() {
        System.out.println("itin1");
        timeTebles = GroupsBundle.getTimeTableByDayPair(currentTimeTable.getDay(), currentTimeTable.getPair());
        System.out.println("init2");
        System.out.println(timeTebles);
        System.out.println(currentTimeTable);
        for (TimeTable table: timeTebles){

            try {
                teachers.add(table.getTeacher());

                System.out.println("init teachers: " + table.getTeacher());
                System.out.println("Teachers: " + teachers);
            } catch (NullPointerException e){
                System.out.println("In teacher catch");
            }
            try {
                classrooms.add(table.getClassroom());
                System.out.println("init classrooms: " + table.getClassroom());
                System.out.println("Classrooms: " + classrooms);

            } catch (NullPointerException e){
                System.out.println("In classrooms catch");
            }


            try {
                groups.add(table.getGroup());
                System.out.println("init groups: " + table.getGroup());
                System.out.println("Groups: " + groups);
            } catch (NullPointerException e){
                System.out.println("In groups catch");
            }

        }
    }

    public boolean validation(){
        try {
            System.out.println("init");
            init();
        } catch (NullPointerException e){
            e.printStackTrace();
            return true;
        }
        System.out.println("work");
        if (classrooms.contains(currentTimeTable.getClassroom())) {
            timeTableErrors.setClassroomError("Classroom " + currentTimeTable.getClassroom().getName() + " is not available now.");
            isValide = false;
        }

        System.out.println("currentTimeTable: " + currentTimeTable);
        if (groups.contains(currentTimeTable.getGroup())) {
            System.out.println("currentTimeTable.getGroup(): " + currentTimeTable.getGroup());
            timeTableErrors.setGroupError("Group" + currentTimeTable.getGroup().getName() + " has another pair now.");
            isValide = false;
        }
        if (teachers.contains(currentTimeTable.getTeacher())) {
            timeTableErrors.setTeacherError("Teacher " + currentTimeTable.getTeacher().getFirstName() + " " +
                    currentTimeTable.getTeacher().getLastName() + " has another pair now.");
            isValide = false;
        }
        return isValide;
    }

    public TimeTableErrors getTimeTableErrors() {
        return timeTableErrors;
    }
}
