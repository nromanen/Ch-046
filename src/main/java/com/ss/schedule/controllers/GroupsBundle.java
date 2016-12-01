package com.ss.schedule.controllers;

import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 01.12.16.
 */
public class GroupsBundle {

    private static List<Group> groups = new ArrayList<>();
    private static List<Subject> subjects = new ArrayList<>();

    static {
        List<Subject> subjects1 = new ArrayList<>();
        subjects1.add(new Subject(1, "Algebra", SubjectType.LECTURE, 1));
        subjects1.add(new Subject(2, "Algebra", SubjectType.PRACTICE, 1));
        subjects1.add(new Subject(3, "Philosophy", SubjectType.LECTURE));
        subjects1.add(new Subject(4, "Philosophy", SubjectType.SEMINAR));

        groups.add(new Group(1, "11", 20, subjects1));
        groups.add(new Group(2, "12", 22, subjects1));
        groups.add(new Group(3, "13", 5, subjects1));
        groups.add(new Group(4, "14", 7, subjects1));

        List<Subject> subjects2 = new ArrayList<>();
        subjects2.add(new Subject(5, "Python", SubjectType.LECTURE));
        subjects2.add(new Subject(6, "Python", SubjectType.LAB));
        subjects2.add(new Subject(7, "Python", SubjectType.PRACTICE));

        groups.add(new Group(5, "21", 10, subjects2));
        groups.add(new Group(6, "22", 9, subjects2));

        List<Subject> subjects3 = new ArrayList<>();
        subjects3.add(new Subject(8, "Algebra", SubjectType.LECTURE, 2));
        subjects3.add(new Subject(9, "Algebra", SubjectType.PRACTICE, 2));

        groups.add(new Group(7, "23", 5, subjects3));
        groups.add(new Group(8, "24", 4, subjects3));

        List<Subject> subjects4 = new ArrayList<>();
        subjects4.add(new Subject(10, ".Net", SubjectType.LECTURE));
        subjects4.add(new Subject(11, ".Net", SubjectType.PRACTICE));

        groups.add(new Group(9, "31", 12, subjects4));
        groups.add(new Group(10, "32", 7, subjects4));

        List<Subject> subjects5 = new ArrayList<>();
        subjects5.add(new Subject(12, "Transformation", SubjectType.SEMINAR));

        groups.add(new Group(11, "33", 6, subjects5));

        List<Subject> subjects6 = new ArrayList<>();
        subjects6.add(new Subject(14, "MA", SubjectType.PRACTICE, 3));

        groups.add(new Group(12, "34", 7, subjects6));

        subjects.addAll(subjects1);
        subjects.addAll(subjects2);
        subjects.addAll(subjects3);
        subjects.addAll(subjects4);
        subjects.addAll(subjects5);
        subjects.addAll(subjects6);
    }

    public static List<Group> getAll(){
        return groups;
    }

    public static Subject getSubjectById(long id){


        System.out.println(subjects);

        for (Subject subject : subjects){
            if (subject.getId() == id) {
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
}
