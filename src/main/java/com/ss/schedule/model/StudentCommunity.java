package com.ss.schedule.model;

import java.util.ArrayList;
import java.util.List;
import com.ss.schedule.institute.TimeTableManager;

/**
 * Created by oleg on 27.11.16.
 */
public abstract class StudentCommunity {
    protected String name;
    protected int count;
    protected List<Subject> subjects=new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    protected long id;

    public StudentCommunity(String name, int count, List<Subject> subjects, long id) {
        this.name = name;
        this.count = count;
        this.subjects = subjects;
        this.id = id;
    }

    public StudentCommunity(String name, int count, List<Subject> subjects) {
        this.name = name;
        this.count = count;
        this.subjects = subjects;
    }

    public StudentCommunity(String name, int count) {
        subjects=new ArrayList<>();
        this.name = name;
        this.count = count;
    }

    public StudentCommunity(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public abstract boolean canBeAddedToTimetableManager(TimeTableManager timeTableManager);

    public abstract boolean isPresentOrEquals(StudentCommunity studentCommunity);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentCommunity)) return false;

        StudentCommunity that = (StudentCommunity) o;

        if (count != that.count) return false;
        //if (id != that.id) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + count;
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
