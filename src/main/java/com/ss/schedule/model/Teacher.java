package com.ss.schedule.model;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
	private long id;
	private String firstName;
	private String lastName;
	private List<Subject> subjects = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public Teacher() {

	}

	public Teacher(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		
	}

	public Teacher(long id, String firstName, String lastName, List<Subject> subjects) {
		this(firstName, lastName);
		this.id = id;
		this.subjects = subjects;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Teacher name " + getLastName() + " " + getFirstName();
	}

}
