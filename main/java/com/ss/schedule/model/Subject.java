package com.ss.schedule.model;

import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "subject")
public class Subject {

	@JacksonXmlProperty
	private String name;
	@JacksonXmlProperty
	private SubjectType type;
	@JacksonXmlProperty
	private int course;
	@XmlTransient
	@JsonIgnore
	private int id;

	public Subject() {}

	public Subject(String name, SubjectType type) {
		this.name = name;
		this.type = type;
	}

	public Subject(String name, SubjectType type, int courseNumber) {
		this.name = name;
		this.course = courseNumber;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SubjectType getType() {
		return type;
	}

	public void setType(SubjectType type) {
		this.type = type;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}
	
	public int getId() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Subject)) return false;

		Subject subject = (Subject) o;

		if (course != subject.course) return false;
		if (name != null ? !name.equals(subject.name) : subject.name != null) return false;
		return type == subject.type;

	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + course;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Subject{" +
				"name='" + name + '\'' +
				", course=" + course +
				", type=" + type +
				'}';
	}

}
