package com.ss.schedule;



//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.ss.schedule.institute.ClassroomManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.institute.Util;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ss.schedule.model.SubjectType.LECTURE;

//import com.ss.schedule.institute.GroupDependenciesCreator;
//import com.ss.schedule.institute.TimeTableManager;
//import com.ss.schedule.io.InputOutputJson;
//import com.ss.schedule.model.*;
//
//import java.util.*;
//
//import static com.ss.schedule.model.SubjectType.LECTURE;
//
///**
// * Created by rmochetc on 23.11.2016.
// */
public class Main {
//
    private static Util FCS;
//
    public static void main(String[] args) {
////
//        InputOutputJson<ArrayList<Classroom>> iojClassRooms = new InputOutputJson<>(
//                new TypeReference<ArrayList<Classroom>>() {
//                });
//        ArrayList<Classroom> classrooms = iojClassRooms.readFromFile("room.json");
//        InputOutputJson<List<Group>>iojGroups=new InputOutputJson<>(new TypeReference<List<Group>>() {
//        });
//        InputOutputJson<List<Subject>>iojSubjects=new InputOutputJson<>(new TypeReference<List<Subject>>() {
//        });
//        InputOutputJson<List<Teacher>>iojTeachers=new InputOutputJson<>(new TypeReference<List<Teacher>>() {
//        });
//
//        List<Group> groups = iojGroups.readFromFile("/media/oleg/D254AF9D54AF8339/Users/Oleg/" +
//                "OneDrive/Education3/JavaFx/TimeTable1/Ch-046/src/main/resourses/group.json");
//        System.out.println(groups.get(0).getName());
//
//        List<Subject> subjects = iojSubjects.readFromFile("/media/oleg/D254AF9D54AF8339/Users/Oleg/" +
//                "OneDrive/Education3/JavaFx/TimeTable1/Ch-046/src/main/resourses/subject.json");
//        List<Teacher> teachers=iojTeachers.readFromFile("/media/oleg/D254AF9D54AF8339/Users/Oleg/OneDrive/" +
//                "Education3/JavaFx/TimeTable1/Ch-046/src/main/resourses/teacher.json");
//
//        TimeTableManager timeTableManager=new TimeTableManager();
//
//
//        FCS=new Util("Util of computer science",groups,subjects,teachers,classrooms);
//        //FCS.setClassrooms(classrooms);
//        FCS.getGroups().add(0,new Group("23_24",9,new LinkedList<>()));
//        LinkedHashMap<Subject, List<? extends StudentCommunity>> groupsSubgroupsStreams = FCS.getGroupsSubgroupsStreams();
//        System.out.println(groupsSubgroupsStreams);
//        ClassroomManager classroomManager1=new ClassroomManager();
//        Subject[] subjectsKeySet=timeTableManager.subjectsSetToArray(groupsSubgroupsStreams);
//
//        TimeTable timeTable=new TimeTable();
//        timeTable.setStudentCommunity(new ArrayList<>());
//        System.out.println("Select subject , please");
//        timeTableManager.showAvailableSubjectsInGroup(groupsSubgroupsStreams);
//        Scanner scanner=new Scanner(System.in);
//        int i = scanner.nextInt();
//        System.out.println("Groups of "+subjectsKeySet[i]);
////        for (Group gr : groupsSubgroupsStreams.get(subjectsKeySet[i])
////                ) {
////            System.out.println(gr.getName()+" "+gr.getCount());
////        }
//        timeTableManager.getGroupsBySubject(subjectsKeySet[i],groupsSubgroupsStreams);
//        System.out.println("Select group, please");
//        int j = scanner.nextInt();
//
//        timeTable.getGroup().add(groupsSubgroupsStreams.get(subjectsKeySet[i]).remove(j));
//        timeTable.setSubject(subjectsKeySet[i]);
//
//
//        System.out.println("\n"+"/////////////////////");
//        System.out.println("After");
//        timeTableManager.showAvailableSubjectsInGroup(groupsSubgroupsStreams);
//
//        System.out.println("I'm a stupid user.I don't know what I want so I changed my mind");
//        timeTableManager.removeStudentCommunityBySubjectInTimetable(subjectsKeySet,timeTable,groupsSubgroupsStreams,
//                timeTable.getGroup().get(0));
//        System.out.println("\n"+"/////////////////////");
//        System.out.println("Subject is back");
//        timeTableManager.showAvailableSubjectsInGroup(groupsSubgroupsStreams);
//        System.out.println(timeTableManager.getAvailableClassroomsForTimetable(classroomManager1,timeTable));
//
////        TimeTableManager timeTableManager=new TimeTableManager();
//        List<TimeTable> timetables = getTimetables();
//        timeTableManager.setTimeTables(timetables);
//        List<TimeTable> windowsInDay = timeTableManager.getWindowsInDay(OddnessOfWeek.ODD,DayOfWeek.MONDAY);
//        for (TimeTable t:windowsInDay)
//        System.out.println(t.getPair());
//
//        System.out.println(timeTableManager.isClassroomFreeNow(DayOfWeek.MONDAY,OddnessOfWeek.ODD,
//                FCS.getClassrooms().get(0),Pair.FIRST));
//
//        TimeTable tt2=new TimeTable();
//        tt2.setDay(DayOfWeek.MONDAY);
//        tt2.setOddnessOfWeek(OddnessOfWeek.ALL);
//        tt2.setPair(Pair.FIRST);
//        tt2.setSubject(new Subject("jlljk",5,LECTURE));
//        tt2.setStudentCommunity(new ArrayList<>());
//        tt2.getGroup().add(FCS.getGroups().get(0));
//        tt2.setTeacher(new Teacher());
//        tt2.setClassroom(FCS.getClassrooms().get(0));
//        tt2.setTeacher(FCS.getTeachers().get(0));
////        timeTableManager.canAddTimetable(tt2);
//        System.out.println(timeTableManager.
//                isTeacherFreeNow(DayOfWeek.MONDAY,OddnessOfWeek.ALL,FCS.getTeachers().get(0),Pair.FIRST));
////        System.out.println(timeTableManager.
////                isClassroomFreeNow(DayOfWeek.MONDAY,OddnessOfWeek.ALL,FCS.getClassrooms().get(0),Pair.FIRST));
//
//        GroupDependenciesCreator groupDependenciesCreator=new GroupDependenciesCreator(groupsSubgroupsStreams);
//        HashMap<Group, List<Group>> groupsDependencies = groupDependenciesCreator.getGroupsDependencies();
//
//        ArrayList<Group> groupsToTEst=new ArrayList<>();
//        groupsToTEst.add(FCS.getGroups().get(0));
//        boolean groupFreeNow = timeTableManager.isGroupFreeNow(DayOfWeek.MONDAY, OddnessOfWeek.ODD,
//                groupsToTEst, Pair.FIRST, groupsSubgroupsStreams, FCS);
//        System.out.println(groupFreeNow);
//        System.exit(0);

//        InputOutputJson<ArrayList<Classroom>> iojClassRooms = new InputOutputJson<>(
//                new TypeReference<ArrayList<Classroom>>() {
//                });
//        InputOutputJson<List<Group>>iojGroups=new InputOutputJson<>(new TypeReference<List<Group>>() {
//        });
//        InputOutputJson<List<Subject>>iojSubjects=new InputOutputJson<>(new TypeReference<List<Subject>>() {
//        });
//        InputOutputJson<List<Teacher>>iojTeachers=new InputOutputJson<>(new TypeReference<List<Teacher>>() {
//        });
//
//        List<Classroom> classrooms = iojClassRooms.readFromFile("room.json");
//        List<Group> groups = iojGroups.readFromFile("/media/oleg/D254AF9D54AF8339/Users/Oleg/OneDrive/Education3/" +
//                "JavaFx/TimeTable1/Ch-046 (copy)/src/main/resourses/group.json");
//        List<Subject> subjects = iojSubjects.readFromFile("/media/oleg/D254AF9D54AF8339/Users/Oleg/" +
//                "OneDrive/Education3/JavaFx/TimeTable1/Ch-046 (copy)/src/main/resourses/subject.json");
//        List<Teacher> teachers=iojTeachers.readFromFile("/media/oleg/D254AF9D54AF8339/Users/Oleg/OneDrive/" +
//                "Education3/JavaFx/TimeTable1/Ch-046 (copy)/src/main/resourses/teacher.json");
//
//        FCS=new Util("Util of computer science",groups,subjects,teachers,classrooms);
//        for (Group group:FCS.getGroups())
//            group.setSubgroups(new ArrayList<>());
//        List<Subgroup> subgroups = FCS.createSubgroups(FCS.getSubjects().get(1));

    }


    public static List<TimeTable> getTimetables(){
        Subject english=new Subject();
        english.setName("English");
        english.setType(LECTURE);
        english.setCourse(3);


        Subject spanish=new Subject();
        spanish.setName("Spanish");
        spanish.setType(LECTURE);
        spanish.setCourse(3);

        Subject chineese=new Subject();
        chineese.setName("Chineese");
        chineese.setType(LECTURE);
        chineese.setCourse(3);

        Subject math=new Subject();
        math.setName("Math");
        math.setType(LECTURE);
        math.setCourse(3);

        TimeTable tt1=new TimeTable();
        tt1.setStudentCommunity(FCS.getGroups().get(0).getSubgroups().get(0));
        tt1.setDay(DayOfWeek.MONDAY);
        tt1.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt1.setPair(Pair.FIRST);
        tt1.setSubject(english);
        tt1.setTeacher(new Teacher());
        tt1.setClassroom(FCS.getClassrooms().get(0));
        tt1.setTeacher(FCS.getTeachers().get(0));

        TimeTable tt2=new TimeTable();
        tt2.setDay(DayOfWeek.MONDAY);
        tt2.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt2.setPair(Pair.FIRST);
        tt2.setSubject(spanish);
        tt2.setStudentCommunity(new Stream());
        tt2.setTeacher(new Teacher());
        tt2.setClassroom(FCS.getClassrooms().get(3));
        tt2.setTeacher(FCS.getTeachers().get(0));

        TimeTable tt3=new TimeTable();
        tt3.setDay(DayOfWeek.MONDAY);
        tt3.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt3.setPair(Pair.SIXTH);
        tt3.setSubject(chineese);
        tt3.setStudentCommunity(new Stream());
        tt3.setTeacher(new Teacher());
        tt3.setClassroom(FCS.getClassrooms().get(3));
        tt3.setTeacher(FCS.getTeachers().get(0));


        TimeTable tt4=new TimeTable();
        tt4.setDay(DayOfWeek.MONDAY);
        tt4.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt4.setPair(Pair.SEVENTH);
        tt4.setSubject(math);
        tt4.setStudentCommunity(new Stream());
        tt4.setTeacher(new Teacher());
        tt4.setClassroom(FCS.getClassrooms().get(3));
        tt4.setTeacher(FCS.getTeachers().get(0));

        List<TimeTable> timeTables=new ArrayList<>();
        timeTables.add(tt1);
        timeTables.add(tt2);
        timeTables.add(tt3);
        timeTables.add(tt4);

        return timeTables;


    }

}
