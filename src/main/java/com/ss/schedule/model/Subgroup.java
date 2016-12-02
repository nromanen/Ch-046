package com.ss.schedule.model;

import com.ss.schedule.institute.TimeTableManager;

import java.util.List;

/**
 * Created by oleg on 27.11.16.
 */
public class Subgroup extends StudentCommunity {
    private Group group;

    public Subgroup(String name, int count, Group group) {
        super(name, count);
        this.group = group;
    }

    public Group getGroup() {

        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Subgroup(String name, int count, List<Subject> subjects) {
        super(name, count, subjects);
    }

    @Override
    public boolean canBeAddedToTimetableManager(TimeTableManager timeTableManager) {
        for (TimeTable timeTable:timeTableManager.getTimeTables()){
            if (isPresentOrEquals(timeTable.getStudentCommunity())){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subgroup)) return false;
        if (!super.equals(o)) return false;

        Subgroup subgroup = (Subgroup) o;

        return group != null ? group.equals(subgroup.group) : subgroup.group == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }

    @Override
    public boolean isPresentOrEquals(StudentCommunity studentCommunity){
        if(studentCommunity instanceof Subgroup){
            Subgroup subgroup = (Subgroup) studentCommunity;
            if (this.equals(subgroup)) return true;
        } else if (studentCommunity instanceof Group){
            Group group = (Group) studentCommunity;
            if (group.getSubgroups().contains(this)){
                return true;
            }
        } else if (studentCommunity instanceof Stream)
        {
            Stream stream = (Stream) studentCommunity;
            if (stream.containsSubgroup(this))
                return true;
        }
        return false;
    }



}
