package com.ss.schedule.controllers;

import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 01.12.16.
 */
public class GroupsBundle {

    private static List<Group> groups = new ArrayList<>();
    private static List<Subject> subjects = new ArrayList<>();
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<TimeTable> timeTables = new ArrayList<>();

    static {
        Subject subject1 = new Subject(1, "Algebra", SubjectType.LECTURE, 1);
        Subject subject2 = new Subject(2, "Algebra", SubjectType.PRACTICE, 1);
        Subject subject3 = new Subject(3, "Philosophy", SubjectType.LECTURE);
        Subject subject4 = new Subject(4, "Philosophy", SubjectType.SEMINAR);
        Subject subject5 = new Subject(5, "Python", SubjectType.LECTURE);
        Subject subject6 = new Subject(6, "Python", SubjectType.LAB);
        Subject subject7 = new Subject(7, "Python", SubjectType.PRACTICE);
        Subject subject8 = new Subject(8, "Algebra", SubjectType.LECTURE, 2);
        Subject subject9 = new Subject(9, "Algebra", SubjectType.PRACTICE, 2);
        Subject subject10 = new Subject(10, ".Net", SubjectType.LECTURE);
        Subject subject11 = new Subject(11, ".Net", SubjectType.PRACTICE);
        Subject subject12 = new Subject(12, "Transformation", SubjectType.SEMINAR);
        Subject subject13 = new Subject(13, "MA", SubjectType.PRACTICE, 3);

        List<Subject> subjects1 = new ArrayList<>();
        subjects1.add(subject1);
        subjects1.add(subject2);
        subjects1.add(subject3);
        subjects1.add(subject4);

        List<Subject> subjects2 = new ArrayList<>();
        subjects2.add(subject5);
        subjects2.add(subject6);
        subjects2.add(subject7);

        List<Subject> subjects3 = new ArrayList<>();
        subjects3.add(subject8);
        subjects3.add(subject9);


        List<Subject> subjects4 = new ArrayList<>();
        subjects4.add(subject10);
        subjects4.add(subject11);

        List<Subject> subjects5 = new ArrayList<>();
        subjects5.add(subject12);

        List<Subject> subjects6 = new ArrayList<>();
        subjects6.add(subject13);

        Group group1 = new Group(1, "11", 20, subjects1);
        Group group2 = new Group(2, "12", 22, subjects1);
        Group group3 = new Group(3, "13", 5, subjects1);
        Group group4 = new Group(4, "14", 7, subjects1);
        Group group5 = new Group(5, "21", 10, subjects2);
        Group group6 = new Group(6, "22", 9, subjects2);
        Group group7 = new Group(7, "23", 5, subjects3);
        Group group8 = new Group(8, "24", 4, subjects3);
        Group group9 = new Group(9, "31", 12, subjects4);
        Group group10 = new Group(10, "32", 7, subjects4);
        Group group11 = new Group(11, "33", 6, subjects5);
        Group group12 = new Group(12, "34", 7, subjects6);

        groups.add(group1);
        groups.add(group2);
        groups.add(group3);
        groups.add(group4);
        groups.add(group5);
        groups.add(group6);
        groups.add(group7);
        groups.add(group8);
        groups.add(group9);
        groups.add(group10);
        groups.add(group11);
        groups.add(group12);

        subjects.addAll(subjects1);
        subjects.addAll(subjects2);
        subjects.addAll(subjects3);
        subjects.addAll(subjects4);
        subjects.addAll(subjects5);
        subjects.addAll(subjects6);

        List<Subject> teacherSubjects1 = new ArrayList<>();
        List<Subject> teacherSubjects2 = new ArrayList<>();
        List<Subject> teacherSubjects3 = new ArrayList<>();
        List<Subject> teacherSubjects4 = new ArrayList<>();
        List<Subject> teacherSubjects5 = new ArrayList<>();
        List<Subject> teacherSubjects6 = new ArrayList<>();

        teacherSubjects1.add(subject1);
        teacherSubjects1.add(subject5);
        teacherSubjects1.add(subject10);
        teacherSubjects2.add(subject1);
        teacherSubjects2.add(subject5);
        teacherSubjects2.add(subject12);
        teacherSubjects3.add(subject1);
        teacherSubjects3.add(subject5);
        teacherSubjects3.add(subject10);
        teacherSubjects4.add(subject6);
        teacherSubjects4.add(subject11);
        teacherSubjects4.add(subject13);
        teacherSubjects5.add(subject2);
        teacherSubjects5.add(subject3);
        teacherSubjects5.add(subject12);
        teacherSubjects5.add(subject10);
        teacherSubjects5.add(subject11);
        teacherSubjects5.add(subject13);
        teacherSubjects6.add(subject11);
        teacherSubjects6.add(subject10);
        teacherSubjects6.add(subject9);
        teacherSubjects6.add(subject6);
        teacherSubjects6.add(subject7);

        Teacher teacher1 = new Teacher(1, "Ivan", "Petrov", teacherSubjects1);
        Teacher teacher2 = new Teacher(2, "Iryna", "Shvets", teacherSubjects2);
        Teacher teacher3 = new Teacher(3, "Leonid", "Shvets", teacherSubjects3);
        Teacher teacher4 = new Teacher(4, "Ivan", "Dolin", teacherSubjects4);
        Teacher teacher5 = new Teacher(5, "Galyna", "Dorosh", teacherSubjects5);
        Teacher teacher6 = new Teacher(6, "Petro", "Ponchyuk", teacherSubjects6);

        teachers.add(teacher1);
        teachers.add(teacher2);
        teachers.add(teacher3);
        teachers.add(teacher4);
        teachers.add(teacher5);
        teachers.add(teacher6);

        TimeTable timeTable = new TimeTable();
        timeTable.setClassroom(new Classroom());
        timeTable.setDay(DayOfWeek.MONDAY);
        timeTable.setOddnessOfWeek(OddnessOfWeek.ALL);
        timeTable.setPair(Pair.FIRST);
        timeTable.setGroup(group1);
        timeTable.setTeacher(teacher1);
        timeTable.setSubject(subject1);
        timeTables.add(timeTable);

    }


    public static List<Group> getAll(){
        return groups;
    }

    public static Subject getSubjectById(long id){


        System.out.println(subjects);

        for (Subject subject : subjects){
            if (subject.getId() == id) {;
                System.out.println(subject);
                System.out.println(subject.getType());
                return subject;
            }
        }

        return null;
    }

    public static Group getGroupById(long id){

        for (Group group : groups){
            if (group.getId() == id) {
                return group;
            }
        }
        return null;
    }

    public static Teacher getTeacherById(long id){

        for (Teacher teacher : teachers){
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }

    public static List<Teacher> getTeachersBySubject(Subject subject){

        List<Teacher> result = new ArrayList<>();
        for (Teacher teacher : teachers){
            if (teacher.getSubjects().contains(subject)){
                result.add(teacher);
            }
        }
        System.out.println("return " + result);
        return result;
    }

    public static List<TimeTable> getTimeTableByDayPair(DayOfWeek day, Pair pair) {
        List<TimeTable> result = new ArrayList<>();
        for(TimeTable timeTable : timeTables){
            System.out.println("Day " + day);
            System.out.println("Pair " + pair);
            System.out.println("All day " + timeTable.getDay());
            System.out.println("All pair " + timeTable.getPair());
            if(timeTable.getDay().equals(day) && timeTable.getPair().equals(pair)){
                System.out.println("Add timetable " + timeTable);
                result.add(timeTable);
            }
        }
        return result;

    }

    public static void addTimeTable(TimeTable timeTable) {
        timeTables.add(timeTable);
    }
}
