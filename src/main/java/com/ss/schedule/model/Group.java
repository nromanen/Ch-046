package com.ss.schedule.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


import java.util.List;

@JacksonXmlRootElement(localName = "group")
public class Group {

	private long id;

	private Group parent;

	@JacksonXmlProperty
	private String name;

	@JacksonXmlProperty
	private int count;

	@JacksonXmlElementWrapper(localName = "subjects")
	@JacksonXmlProperty(localName = "subject")
	private List<Subject> subjects;

	public Group() {}

	public Group(String name, int count, List<Subject> subjects) {
		this.name = name;
		this.count = count;
		this.subjects = subjects;
	}

	public Group(long id, String name, int count, List<Subject> subjects) {
		this(name, count, subjects);
		this.id = id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Group getParent() {
		return parent;
	}

	public void setParent(Group parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Group{" +
				"id=" + id +
				", name='" + name + '\'' +
				", count=" + count +
				", subjects=" + subjects +
				'}';
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
