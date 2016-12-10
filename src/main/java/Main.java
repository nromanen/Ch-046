import com.ss.schedule.controllers.GroupsBundle;
import com.ss.schedule.dao.ClassroomDao;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by rmochetc on 23.11.2016.
 */
public class Main {


    public static void main(String[] args) {

        List<Group> groups = GroupsBundle.getAll();
        for (Group group :
                getSubGroup(groups)) {
            System.out.println(group.getName() + " " + group.getSubjects().get(0).getName()+ "/" + group.getSubjects().get(0).getType() + "  "+group.getCount() + " || patent: " + group.getParent().getName());
        }

        ClassroomDao classroomDao = new ClassroomDao();

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
