package com.ss.schedule.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.ss.schedule.institute.TimeTableManager;


import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "group")
public class Group extends StudentCommunity {
	@JsonManagedReference
    private List<Subgroup> subgroups=new ArrayList<>();

    public List<Subgroup> getSubgroups() {
        return subgroups;
    }

    public void setSubgroups(List<Subgroup> subgroups) {
        this.subgroups = subgroups;
    }

    public Group() {;
	}

	public Group(String name, int count, List<Subject> subjects, long id) {
		super(name, count, subjects, id);
	}

	public Group(String name, int count, List<Subject> subjects) {
		super(name,count,subjects);
	}

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
	public boolean isPresentOrEquals(StudentCommunity studentCommunity) {
		if (studentCommunity instanceof Group){
			Group group = (Group) studentCommunity;
			if (this.equals(group)) return true;
		}else if (studentCommunity instanceof Subgroup){
			Subgroup subgroup = (Subgroup) studentCommunity;
			if (this.getSubgroups().contains(subgroup)) return true;
		} else if (studentCommunity instanceof Stream)
		{
			Stream stream = (Stream) studentCommunity;
			if (stream.getGroups().contains(this)) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Group)) return false;

		Group group = (Group) o;

		return name != null ? name.equals(group.name) : group.name == null;

	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}
}
