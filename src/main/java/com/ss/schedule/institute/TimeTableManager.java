package com.ss.schedule.institute;

import com.ss.schedule.model.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@SuppressWarnings("serial")
//@XmlRootElement(name = "TimeTableManager")
//@XmlType(propOrder = {"timeTables"})
public class TimeTableManager implements Serializable {
    private List<TimeTable> timeTables;

    public List<TimeTable> getTimeTables() {
        return timeTables;
    }

    public void setTimeTables(List<TimeTable> timeTables) {
        this.timeTables = timeTables;
    }

    public List<TimeTable> getLessonsByWeek(OddnessOfWeek oddnessOfWeek) {
        List<TimeTable> res = new ArrayList<>();
        for (TimeTable tt : timeTables) {
            if (tt.getOddnessOfWeek().equals(oddnessOfWeek))
                res.add(tt);
        }
        return res;
    }

    public List<TimeTable> getLessonsByDayAndWeek(OddnessOfWeek oddnessOfWeek, DayOfWeek dayOfWeek) {
        List<TimeTable> res = new ArrayList<>();
        for (TimeTable tt : timeTables) {
            if (tt.getOddnessOfWeek().equals(oddnessOfWeek) && tt.getDay().equals(dayOfWeek))
                res.add(tt);
        }
        return res;
    }

    public List<Pair> getFreeDaylyPairs(DayOfWeek dayOfWeek) {
        Pair[] pairs = Pair.values();
        List<Pair> tempList = new ArrayList<Pair>(Arrays.asList(pairs));
        for (int i = 0; i < timeTables.size(); i++) {
            if (timeTables.get(i).getDay().equals(dayOfWeek))
                for (int j = 0; j < tempList.size(); j++) {
                    if (tempList.get(j) == timeTables.get(i).getPair()) {
                        tempList.remove(tempList.get(j));
                    }
                }
        }
        return tempList;
    }

    public List<TimeTable> getLessonByTeacher(String lastName) {
        List<TimeTable> tempList = new ArrayList<>();
        for (TimeTable in : timeTables) {
            if (lastName .equals(in.getTeacher().getLastName())) {
                tempList.add(in);
            }
        }
        return tempList;
    }

    public List<TimeTable> getlessonByGroup(String groupName) {
        List<TimeTable> tempList = new ArrayList<>();
        for (TimeTable timeTable : timeTables) {
           
            if (groupName.equals(timeTable.getStudentCommunity().getName())) {
                tempList.add(timeTable);
            }
        }
        return tempList;
    }

    public void showAvailableSubjectsInGroup(LinkedHashMap<Subject, List<? extends StudentCommunity>> groupsSubgroupsStreams) {
        System.out.println("Available subjects in groups:");
        Subject[] subjectsKeySet = subjectsSetToArray(groupsSubgroupsStreams);
        for (Subject subject : subjectsKeySet) {
            System.out.println(subject.getName() + " " + subject.getCourse());
            for (StudentCommunity community : groupsSubgroupsStreams.get(subject)) {
                System.out.println(community.getName());
            }
        }
    }

    public Subject[] subjectsSetToArray(LinkedHashMap<Subject, List<? extends StudentCommunity>> groupsSubgroupsStreams) {
        Subject[] subjectsKeySet = new Subject[groupsSubgroupsStreams.keySet().size()];
        groupsSubgroupsStreams.keySet().toArray(subjectsKeySet);
        return subjectsKeySet;
    }
////refactor
//    public void removeStudentCommunityBySubjectInTimetable(Subject[] subjectsKeySet, TimeTable timeTable,
//                                                           LinkedHashMap<Subject, List<? extends StudentCommunity>> groupsSubgroupsStreams,
//                                                           StudentCommunity studentCommunity) {
//            Subject subjectToPutBackKey=findSubjectInKeySet(subjectsKeySet,timeTable);
//            Group group=null;
//        List<? extends StudentCommunity> studentCommunities = groupsSubgroupsStreams.get(subjectToPutBackKey);
//        StudentCommunity community = studentCommunities.get(0);
//        StudentCommunity st = new StudentCommunity() {
//            @Override
//            public boolean canBeAddedToTimetableManager(TimeTableManager timeTableManager) {
//                return false;
//            }
//
//            @Override
//            public boolean isPresentOrEquals(StudentCommunity studentCommunity) {
//                return false;
//            }
//        };
//        studentCommunities.add(st);
//        groupsSubgroupsStreams.get(subjectToPutBackKey).add(studentCommunity);
//            timeTable.setStudentCommunity(null);
//    }

    public Subject findSubjectInKeySet(Subject[] subjectsKeySet, TimeTable timeTable) {
        for (Subject subject : subjectsKeySet) {
            if (subject.equals(timeTable.getSubject()))
                return subject;
        }
        return null;
    }

//    public List<Classroom> getAvailableClassroomsForTimetable(ClassroomManager classroomManager,
//                                                              TimeTable timeTable){
//        List<Classroom> listOfAvailableRooms = classroomManager
//                .getListOfAvailableRooms(timeTable.getSubject(), timeTable.getGroup());
//        return listOfAvailableRooms;
//    }
//
//    public void getGroupsBySubject(Subject subj, LinkedHashMap<Subject, List<? extends StudentCommunity>> groupsSubgroupsStreams){
//        for (Group gr : groupsSubgroupsStreams.get(subj)
//                ) {
//            System.out.println(gr.getName()+" "+gr.getCount());
//        }
//    }
//Katya dura
    public List<TimeTable> sortTimeTables(List<TimeTable> timeTables){
        Pair[] pairs = Pair.values();
        List<Pair> listOfPairs = new ArrayList<Pair>(Arrays.asList(pairs));
        List<TimeTable> sortedTimeTables = new ArrayList<>();
        int i;
        int j;
        for(i=0; i<listOfPairs.size(); i++){
            for(j=0; j<timeTables.size(); j++){
                if(listOfPairs.get(i)==timeTables.get(j).getPair()){
                    sortedTimeTables.add(timeTables.get(j));
                }
            }
        }
        return sortedTimeTables;
    }

    public List<TimeTable> getWindowsInDay(OddnessOfWeek oddnessOfWeek,
                                           DayOfWeek dayOfWeek){
        List<TimeTable> windows=new ArrayList<>();
        List<TimeTable> lessonsByDayAndWeek = getLessonsByDayAndWeek(oddnessOfWeek,dayOfWeek);
        List<TimeTable> sorted_timeTables = sortTimeTables(lessonsByDayAndWeek);
        for (int i = 0; i <sorted_timeTables.size()-1 ; i++) {
            int substr_result=sorted_timeTables.get(i+1).getPair().ordinal()
                    -sorted_timeTables.get(i).getPair().ordinal();
            if (substr_result>1){
                for (int j =sorted_timeTables.get(i).getPair().ordinal()+1 ;
                     j < sorted_timeTables.get(i+1).getPair().ordinal() ; j++) {
                    TimeTable t=new TimeTable();
                    t.setPair(Pair.values()[j]);
                    windows.add(t);
                }
            }

        }
        return windows;
    }



    public boolean isClassroomFreeNow(DayOfWeek dayOfWeek,OddnessOfWeek oddnessOfWeek,Classroom classroom,Pair pair){
        for (TimeTable timeTable:timeTables
             ) {
            if (timeTable.getClassroom().equals(classroom)&&(timeTable.getOddnessOfWeek().equals(oddnessOfWeek)|| timeTable.getOddnessOfWeek().equals(OddnessOfWeek.ALL)
            ||(oddnessOfWeek.equals(OddnessOfWeek.ALL)&&timeTable.getOddnessOfWeek().equals(OddnessOfWeek.ODD))
                    ||(oddnessOfWeek.equals(OddnessOfWeek.ALL)&&timeTable.getOddnessOfWeek().equals(OddnessOfWeek.EVEN)))
                    && timeTable.getDay().equals(dayOfWeek)&&timeTable.getPair().equals(pair)){
                return false ;
            }
        }
        return true;
    }

    public boolean isTeacherFreeNow(DayOfWeek dayOfWeek,OddnessOfWeek oddnessOfWeek,Teacher teacher,Pair pair){
        for (TimeTable timeTable:timeTables){
            if (timeTable.getTeacher().equals(teacher)&&(timeTable.getOddnessOfWeek().equals(oddnessOfWeek)|| timeTable.getOddnessOfWeek().equals(OddnessOfWeek.ALL)
                    ||(oddnessOfWeek.equals(OddnessOfWeek.ALL)&&timeTable.getOddnessOfWeek().equals(OddnessOfWeek.ODD))
                    ||(oddnessOfWeek.equals(OddnessOfWeek.ALL)&&timeTable.getOddnessOfWeek().equals(OddnessOfWeek.EVEN)))
                    && timeTable.getDay().equals(dayOfWeek)&&timeTable.getPair().equals(pair)){
                return false ;
            }
        }
        return true;
    }
//
////    public boolean isTeacherFreeNow(DayOfWeek dayOfWeek,OddnessOfWeek oddnessOfWeek,Teacher teacher,Pair pair){
////        for (TimeTable timeTable:timeTables){
////            if (timeTable.getTeacher().equals(teacher)&& isThisTimetable(dayOfWeek,oddnessOfWeek,pair,timeTable)){
////                return false ;
////            }
////        }
////        return true;
////    }
//
//    public boolean isGroupFreeNow(DayOfWeek dayOfWeek, OddnessOfWeek oddnessOfWeek,
//                                  List<Group> groups, Pair pair,
//                                  LinkedHashMap<Subject, List<Group>> groupsSubgroupsStreams, Faculty faculty){
//        GroupDependenciesCreator groupDependenciesCreator=new GroupDependenciesCreator(groupsSubgroupsStreams);
//        HashMap<Group, List<Group>> groupsDependencies = groupDependenciesCreator.getGroupsDependencies();
//        //group is not free if groupdependencies values by key current group contains
//        Pattern groupNamePattern=Pattern.compile("(\\d{2})");
//        System.out.println("is");
////        System.out.println(groupsDependencies.get(group)+"     "+group);
//
//
//        //for (TimeTable timeTable:timeTables){
////            if ((timeTable.getOddnessOfWeek().equals(oddnessOfWeek)|| timeTable.getOddnessOfWeek().equals(OddnessOfWeek.ALL)
////                    ||(oddnessOfWeek.equals(OddnessOfWeek.ALL)&&timeTable.getOddnessOfWeek().equals(OddnessOfWeek.ODD))
////                    ||(oddnessOfWeek.equals(OddnessOfWeek.ALL)&&timeTable.getOddnessOfWeek().equals(OddnessOfWeek.EVEN)))
////                    && timeTable.getDay().equals(dayOfWeek)&&timeTable.getPair().equals(pair)){
////                return true ;
////            }
//        //}
//
//        for (TimeTable timeTable:timeTables){
//
//                for (Group group : timeTable.getGroup())
//                    for (Group gr : groups)
//                        if (group.equals(gr))
//                            if (
//                                    (timeTable.getOddnessOfWeek().equals(oddnessOfWeek)|| timeTable.getOddnessOfWeek().equals(OddnessOfWeek.ALL)
//                                    ||(oddnessOfWeek.equals(OddnessOfWeek.ALL)&&timeTable.getOddnessOfWeek().equals(OddnessOfWeek.ODD)
//                                    ||(oddnessOfWeek.equals(OddnessOfWeek.ALL)&&timeTable.getOddnessOfWeek().equals(OddnessOfWeek.EVEN))))
//                                    && timeTable.getDay().equals(dayOfWeek)&&timeTable.getPair().equals(pair)) {
//
//                                return false;
//                            }
//
//        }
//
////        if (group.getName().contains("-")) {
//            for (Group group:groups)
//            for (TimeTable timetable :
//                    timeTables) {
//                for (Group gr:timetable.getGroup())
//                if (groupsDependencies.get(group).contains(gr)) {
//                    if (
//                            (timetable.getOddnessOfWeek().equals(oddnessOfWeek)|| timetable.getOddnessOfWeek().equals(OddnessOfWeek.ALL)
//                                    ||(oddnessOfWeek.equals(OddnessOfWeek.ALL)&&timetable.getOddnessOfWeek().equals(OddnessOfWeek.ODD)
//                                    ||(oddnessOfWeek.equals(OddnessOfWeek.ALL)&&timetable.getOddnessOfWeek().equals(OddnessOfWeek.EVEN))))
//                                    && timetable.getDay().equals(dayOfWeek)&&timetable.getPair().equals(pair)) {
//
//                        return false;
//                    }
//                }
//            }
////        }else
//
////        if (group.getName().length()>2)
////        {
////            Matcher matcher=groupNamePattern.matcher(group.getName());
////            while (matcher.find()){
////                String current_group_name=matcher.group(0);
////                Group groupByName = findGroupByName(faculty, current_group_name);
////                System.out.println(matcher.group(0));
////                for (TimeTable timeTable:timeTables){
////                    if (groupsDependencies.get(groupByName).contains(timeTable.getGroup()))
////                        return false;
////                }
////            }
////        }
//
//        return true;
//    }
//
    public void addTimeTable(TimeTable timeTable,LinkedHashMap<Subject,List<Group>> groupSubgroupStream,Faculty faculty){
        if (isClassroomFreeNow(timeTable.getDay(),
                timeTable.getOddnessOfWeek(),timeTable.getClassroom(),timeTable.getPair())
                &&
                isTeacherFreeNow(timeTable.getDay(),
                        timeTable.getOddnessOfWeek(),timeTable.getTeacher(),timeTable.getPair())
                && timeTable.getStudentCommunity().canBeAddedToTimetableManager(this)){
            System.out.println("ClassRoom was added");
        } else System.out.println("Classroom is already taken");
    }

//    private boolean isThisTimetable(DayOfWeek dayOfWeek,OddnessOfWeek oddnessOfWeek,Pair pair,TimeTable timeTable){
//        return !(timeTable.getOddnessOfWeek().equals(oddnessOfWeek) || timeTable.getOddnessOfWeek().equals(OddnessOfWeek.ALL)
//                || (oddnessOfWeek.equals(OddnessOfWeek.ALL) && timeTable.getOddnessOfWeek().equals(OddnessOfWeek.ODD))
//                || (oddnessOfWeek.equals(OddnessOfWeek.ALL) && timeTable.getOddnessOfWeek().equals(OddnessOfWeek.EVEN))
//                && timeTable.getDay().equals(dayOfWeek) && timeTable.getPair().equals(pair));
//    }

    public Group findGroupByName(Faculty faculty,String name){
        for (Group group:faculty.getGroups()){
            if (group.getName().equals(name))
                return group;
        }
        return null;
    }



//    public void method(Group group){
//        for (Group )
//    }
}
