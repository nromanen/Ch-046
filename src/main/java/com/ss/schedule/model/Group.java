package com.ss.schedule.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


import java.util.List;

@JacksonXmlRootElement(localName = "group")
public class Group {

	private static final String ERROR_MESSAGE_EMPTY_LIST = "[ERROR] List is empty!";
	private static final String ERROR_MESSAGE_ADD_SUBJECT = "[ERROR] This subject has already existed!";

	@JacksonXmlProperty
	private long id;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public int addSubjects(List<Subject> subjectList) {
		int addedSubjectCount = 0;
		if (subjectList.size() != 0) {
			for (Subject subject : subjectList) {
				if (!subjects.contains(subject)) {
					addedSubjectCount++;
					subjects.add(subject);
				}
			}
		} else {
			throw new IllegalArgumentException(ERROR_MESSAGE_EMPTY_LIST);
		}
		return addedSubjectCount;
	}

	public void addSubject(Subject subject) {
		if (!subjects.contains(subject)) {
			subjects.add(subject);
		} else {
			throw new IllegalArgumentException(ERROR_MESSAGE_ADD_SUBJECT);
		}
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
