package com.ss.schedule.institute;

import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.dao.JdbcSubjectDao;
import com.ss.schedule.dao.TimeTableDao;
import com.ss.schedule.dbutil.DBConnector;
import com.ss.schedule.exceptions.TimetableException;
import com.ss.schedule.model.*;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

//@SuppressWarnings("serial")
//@XmlRootElement(name = "TimeTableManager")
//@XmlType(propOrder = {"timeTables"})
public class TimeTableManager implements Serializable {
    private List<TimeTable> timeTables=new ArrayList<>();

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

    public List<TimeTable> getTimetablesForGroup(StudentCommunity studentCommunity,
                                                 DayOfWeek dayOfWeek,OddnessOfWeek oddnessOfWeek) {
        List<TimeTable> tempList = new ArrayList<>();
        for (TimeTable timeTable : timeTables) {
            if (timeTable.getStudentCommunity().equals(studentCommunity)
                    &&(timeTable.getOddnessOfWeek().equals(oddnessOfWeek))
                    &&(timeTable.getDay().equals(dayOfWeek))) {
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



    public boolean isClassroomFreeNow(TimeTable timeTable){
        TimeTableDao timeTableDao=new TimeTableDao();
        List<TimeTable> timetablesInPreciseTime = timeTableDao.getTimetablesAtPreciseTime(timeTable);
        for (TimeTable tmeTable:timetablesInPreciseTime
             ) {
            if (timeTable.getClassroom().equals(tmeTable.getClassroom())&&(timeTable.getOddnessOfWeek().equals(tmeTable.getOddnessOfWeek())
                    || tmeTable.getOddnessOfWeek().equals(OddnessOfWeek.ALL)
            ||(timeTable.getOddnessOfWeek().equals(OddnessOfWeek.ALL)&&tmeTable.getOddnessOfWeek().equals(OddnessOfWeek.ODD))
                    ||(timeTable.getOddnessOfWeek().equals(OddnessOfWeek.ALL)&&tmeTable.getOddnessOfWeek().equals(OddnessOfWeek.EVEN)))
                  ){
                return false ;
            }
        }
        return true;
    }

    public boolean isTeacherFreeNow(TimeTable timeTable){
        TimeTableDao timeTableDao=new TimeTableDao();
        List<TimeTable> timetablesOfGroupInPreciseTime = timeTableDao.getTimetablesAtPreciseTime(timeTable);
        for (TimeTable tmeTable:timetablesOfGroupInPreciseTime
                ) {
            if (timeTable.getTeacher().equals(tmeTable.getTeacher())&&(timeTable.getOddnessOfWeek().equals(tmeTable.getOddnessOfWeek())
                    || tmeTable.getOddnessOfWeek().equals(OddnessOfWeek.ALL)
                    ||(timeTable.getOddnessOfWeek().equals(OddnessOfWeek.ALL)&&tmeTable.getOddnessOfWeek().equals(OddnessOfWeek.ODD))
                    ||(timeTable.getOddnessOfWeek().equals(OddnessOfWeek.ALL)&&tmeTable.getOddnessOfWeek().equals(OddnessOfWeek.EVEN)))
                    ){
                return false ;
            }
        }
        return true;
    }


    public boolean canAddTimetable(TimeTable timeTable) throws RuntimeException{
        if (!isClassroomFreeNow(timeTable)){
            throw new TimetableException("Classroom is already taken!");
        }
        if (!isTeacherFreeNow(timeTable)){
           throw  new TimetableException("Teacher is busy now!");
        }

        if (!isStudentCommunityFreeNow(timeTable)) {
            throw new TimetableException("Student community is busy now!");
        }
        return true;



    }



    public Group findGroupByName(Util util, String name){
        for (Group group: util.getGroups()){
            if (group.getName().equals(name))
                return group;
        }
        return null;
    }

    public void addTimetable(TimeTable timeTable) {
        if (canAddTimetable(timeTable)) {
            if (timeTable.getStudentCommunity() instanceof Stream) {
                Stream stream = (Stream) timeTable.getStudentCommunity();
                for (Group gr :
                        stream.getGroups()) {
                    TimeTable time = new TimeTable();
                    time.setTeacher(timeTable.getTeacher());
                    time.setSubject(timeTable.getSubject());
                    time.setPair(timeTable.getPair());
                    time.setOddnessOfWeek(timeTable.getOddnessOfWeek());
                    time.setDay(timeTable.getDay());
                    time.setStudentCommunity(gr);
                    time.setClassroom(timeTable.getClassroom());
                    this.getTimeTables().add(time);
                    new TimeTableDao().add(time);
                }
            }
            {
                getTimeTables().add(timeTable);
                new TimeTableDao().add(timeTable);
            }
        }
    }

    public void addTimetableFromDB(TimeTable timeTable){
        if (canAddTimetable(timeTable))
        {
            if (timeTable.getStudentCommunity() instanceof Stream){
                Stream stream = (Stream) timeTable.getStudentCommunity();
                for (Group gr :
                        stream.getGroups()) {
                    TimeTable time = new TimeTable();
                    time.setTeacher(timeTable.getTeacher());
                    time.setSubject(timeTable.getSubject());
                    time.setPair(timeTable.getPair());
                    time.setOddnessOfWeek(timeTable.getOddnessOfWeek());
                    time.setDay(timeTable.getDay());
                    time.setStudentCommunity(gr);
                    time.setClassroom(timeTable.getClassroom());
                    this.getTimeTables().add(time);
                }
            } else
            {
                getTimeTables().add(timeTable);
            }
        }
    }

    List<String> addGroupsOfStreamBySubject(Util util, TimeTable timeTable){
        LinkedHashMap<Subject, List<? extends StudentCommunity>> groupsSubgroupsStreams = util.getGroupsSubgroupsStreams();
        List<Stream> streams = (List<Stream>) groupsSubgroupsStreams.get(timeTable.getSubject());
        Stream streamToAdd=null;
        List<String> groupNames=new ArrayList<>();
        for (Stream stream:streams)
        {
            if (stream.getSubjects().contains(timeTable.getSubject()))
                streamToAdd=stream;
            break;

        }
        timeTable.setStudentCommunity(streamToAdd);
        addTimetable(timeTable);
        for (Group group:streamToAdd.getGroups()){
            groupNames.add(group.getName());
        }
        return groupNames;
    }


    public void createSubgroups(){
        Util util =new Util();
        util.setSubjects(new JdbcSubjectDao().getAll());
        util.setGroups(new GroupDao().getAll());
            util.getGroupsSubgroupsStreams();
            for (Group group:util.getGroups())
            new GroupDao().add(group);

    }

    public boolean isStudentCommunityFreeNow(TimeTable timeTable){

        List<TimeTable> timetablesOfGroupOrSubgroup = new TimeTableDao().getTimetablesOfGroupOrSubgroup(timeTable);

        return timetablesOfGroupOrSubgroup.size() == 0;

    }

    public TimeTable[] getTimetablesWithWindows(List<TimeTable> timeTables){
        TimeTable[] timeTables1=new TimeTable[10];
        for (Pair pair:Pair.values()){
            for (TimeTable timeTable:timeTables){
                timeTables1[pair.ordinal()]=null;
               if (timeTable.getPair().equals(pair)){
                   timeTables1[pair.ordinal()] = timeTable;
                   break;
               }
            }
        }
        return timeTables1;
    }
}
