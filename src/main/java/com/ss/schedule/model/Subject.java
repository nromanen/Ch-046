package com.ss.schedule.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "subjects", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"name", "type", "course"}),
})
@JacksonXmlRootElement(localName = "subject")
public class Subject {

	@JacksonXmlProperty
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id")
	private long id;

	@JacksonXmlProperty
	@Column(name = "name", nullable = false)
	private String name;

	@JacksonXmlProperty
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private SubjectType type;

	@JacksonXmlProperty
	@Column(name = "course", nullable = false)
	private int course;

	public Subject() {
	}

	public Subject(String name, SubjectType type, int courseNumber) {
		this.name = name;
		this.course = courseNumber;
		this.type = type;
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

	public SubjectType getType() {
		return type;
	}

	public void setType(SubjectType type) {
		this.type = type;
	}

	public int getCourse() {
		return course;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Subject: { id: ").append(id)
				.append(", name: ").append(name)
				.append(", type: ").append(type.toString())
				.append(", course: ").append(course)
				.append(" }");

		return sb.toString();
	}

	public void setCourse(int course) {
		this.course = course;
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
}
