package com.ss.schedule.model;

import com.ss.schedule.institute.TimeTableManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 27.11.16.
 */
public class Stream extends StudentCommunity {
    List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Stream(String name, int count, List<Subject> subjects) {
        super(name, count, subjects);
    }
    public Stream(){
        subjects=new ArrayList<>();
        groups=new ArrayList<>();
    }

    @Override
    public boolean canBeAddedToTimetableManager(TimeTableManager timeTableManager) {
        for (TimeTable timeTable:timeTableManager.getTimeTables()){
            if (this.isPresentOrEquals(timeTable.getStudentCommunity())) return false;
        }
        return true;
    }

    @Override
    public boolean isPresentOrEquals(StudentCommunity studentCommunity) {
        if (studentCommunity instanceof Stream){
            Stream stream = (Stream) studentCommunity;
            //?????????????????????????????????????????
            if (stream.containsAnyGroupOf(this)) return true;
            //if (this.getGroups().containsAll(stream.getGroups())) return true;
        }else if (studentCommunity instanceof Group)
        {
            Group group = (Group) studentCommunity;
            if (this.getGroups().contains(group)) return true;
        } else if (studentCommunity instanceof Subgroup)
        {
            Subgroup subgroup = (Subgroup) studentCommunity;
            if (this.containsSubgroup(subgroup)) return true;
        }
        return false;
    }

    public boolean containsSubgroup(Subgroup subgroup){
        for (Group group:groups){
            if (group.getSubgroups().contains(subgroup))
                return true;
        }
        return false;
    }

    private boolean containsAnyGroupOf(Stream stream){
        for (Group streamGroup:stream.groups){
            for (Group thisGroup:
                 this.groups) {
                if (streamGroup.equals(thisGroup))
                    return true;
            }
        }
        return false;
    }
}
