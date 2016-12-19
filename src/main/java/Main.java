import com.ss.schedule.dao.GroupDao;
import com.ss.schedule.dao.SubjectDao;
import com.ss.schedule.dao.TimeTableDao;
import com.ss.schedule.dto.GroupSubjectDto;
import com.ss.schedule.model.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rmochetc on 23.11.2016.
 */
public class Main {


    public static void main(String[] args) {

//        TimeTableDao timeTableDao = new TimeTableDao();

//        List<TimeTable> timeTables = timeTableDao.getAll();
//        Collections.sort(timeTables);
//        for (TimeTable t: timeTables){
//            System.out.println("Group: " + t.getGroup().getName() + "; Day: " + t.getDay() + "; Pair: "+ t.getPair());
//        }
//        String logFile = "c:/log4j.properties";
//
//        PropertyConfigurator.configure(logFile);
//        Logger log = Logger.getRootLogger();
//
//
//        log.debug("message text");
//        try{
//           throw  new RuntimeException("Testing log4j");
//        } catch (Exception e) {
//            log.error("message text", e);
//        }
//        log.info("message text");
//        //log.info("message text", ex);
//        log.warn("message text");
//       // log.warn("message text", ex);
//        log.error("message text");
//       // log.error("message text", ex);
//        log.fatal("message text");
//       // log.fatal("message text", ex);

//        GroupDao grouptDao = new GroupDao();
//        SubjectDao subjecDao = new SubjectDao();
//        List<Group> groups = grouptDao.getAll();
//        List<GroupSubjectDto> groupSubjectDtos = new ArrayList<>();
//
//        for (Group g: groups){
//            for (Subject s: g.getSubjects()){
//                if(!timeTableDao.isTimeTable(s.getId(), g.getId())) {
//                    GroupSubjectDto  groupSubjectDto = new GroupSubjectDto();
//                    groupSubjectDto.setGroup(g);
//                    groupSubjectDto.setSubject(s);
//                    groupSubjectDtos.add(groupSubjectDto);
//                }
//            }
//        }
//        for (GroupSubjectDto g :
//                groupSubjectDtos) {
//           // System.out.println("Group: " + grouptDao.getById(g.getGroupId()).getName() + "; Subject: " + subjecDao.getById(g.getSubjectId()).getName());
//
//        }

        TimeTableDao timeTableDao = new TimeTableDao();

        long groupId = 1;
        long oddnesId = 1;
        long maxPair = timeTableDao.getMaxPair(groupId, oddnesId);

        System.out.println(maxPair);



        List<TimeTable> timeTables1 = new ArrayList<>();
        List<TimeTable> timeTables2 = new ArrayList<>();
        List<TimeTable> timeTables3 = new ArrayList<>();
        List<TimeTable> timeTables4 = new ArrayList<>();
        List<TimeTable> timeTables5 = new ArrayList<>();
        List<TimeTable> timeTables = timeTableDao.getByGroup(groupId, 1, oddnesId);
        System.out.println(timeTables);

        for (int i = 0; i < maxPair; i ++) {
            timeTables1.add(getByPairId(timeTables, i+1));
        }
        for (int i = 0; i < maxPair; i ++) {
            timeTables2.add(getByPairId(timeTableDao.getByGroup(groupId, 2, oddnesId), i+1));
        }
        for (int i = 0; i < maxPair; i ++) {
            timeTables3.add(getByPairId(timeTableDao.getByGroup(groupId, 3, oddnesId), i+1));
        }
        for (int i = 0; i < maxPair; i ++) {
            timeTables4.add(getByPairId(timeTableDao.getByGroup(groupId, 4, oddnesId), i+1));
        }
        for (int i = 0; i < maxPair; i ++) {
            timeTables5.add(getByPairId(timeTableDao.getByGroup(groupId, 5, oddnesId), i+1));
        }

        List<Pair> pairs = new ArrayList<>();

        for (long i = 0; i < maxPair; i ++) {
            pairs.add(Pair.getById(i+1));
        }
        System.out.println(timeTables1);
        System.out.println(timeTables2);
        System.out.println(timeTables3);
        System.out.println(timeTables4);
        System.out.println(timeTables5);







    }

    private static TimeTable getByPairId(List<TimeTable> timeTables, long pairId) {

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

    public static List<Group> getSubGroup(List<Group> groups){


        List<Group> subGroups = new ArrayList<>();
        for (Group group :groups ) {
            for (Subject subject: group.getSubjects()){
                if(subject.getType().equals(SubjectType.LAB) || subject.getType().equals(SubjectType.PRACTICE)){
                    int n = group.getCount()/subject.getType().getMaxStudentAmount();
                    //System.out.println(group.getName() + "||||||"+subject.toString() + n + "");
                    if(n!=0) {
                        for (int i = 0; i < n ; i++) {
                            Group sGroup = new Group();
                            List<Subject> sGroupSubject = new ArrayList<>();
                            sGroupSubject.add(subject);
                            sGroup.setSubjects(sGroupSubject);
                            sGroup.setName(group.getName() + "_" + (i+1));
                            sGroup.setCount(group.getCount() / (n+1));
                            sGroup.setParent(group);
                            subGroups.add(sGroup);
                        }

                        Group sGroup = new Group();
                        List<Subject> sGroupSubject = new ArrayList<>();
                        sGroupSubject.add(subject);
                        sGroup.setSubjects(sGroupSubject);
                        sGroup.setName(group.getName() + "_" + (n + 1));
                        sGroup.setCount(group.getCount() - (group.getCount() / (n+1)) * n);
                        sGroup.setParent(group);
                        subGroups.add(sGroup);
                    }

                }
            }
        }
        return subGroups;

    }
}
