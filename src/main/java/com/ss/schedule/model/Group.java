package com.ss.schedule.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@JacksonXmlRootElement(localName = "group")
public class Group {

	private static final String ERROR_MESSAGE_EMPTY_LIST = "[ERROR] List is empty!";
	private static final String ERROR_MESSAGE_ADD_SUBJECT = "[ERROR] This subject has already existed!";

	@JacksonXmlProperty
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id")
	private long id;

	@JacksonXmlProperty
	@Column(name = "name", unique = true, nullable = false, length = 10)
	private String name;

	@JacksonXmlProperty
	@Column(name = "count", nullable = false)
	private int count;

	@JacksonXmlElementWrapper(localName = "subjects")
	@JacksonXmlProperty(localName = "subject")
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(
			name = "group_subject",
			joinColumns = @JoinColumn(name = "group_id"),
			inverseJoinColumns = @JoinColumn(name = "subject_id")
	)
	private List<Subject> subjects;

	public Group() {
		subjects = new ArrayList<>();
	}

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
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Group: { id: ").append(id)
				.append(", name: ").append(name)
				.append(", count: ").append(count)
				.append(", subjects: [ ");
		for (int i = 0; i < subjects.size(); i++) {
			sb.append("{").append(subjects.get(i).toString()).append("}");
			if (i < subjects.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append(" ] }");
		return sb.toString();
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
