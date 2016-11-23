package com.ss.schedule.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


import java.util.List;

@JacksonXmlRootElement(localName = "group")
public class Group {

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
