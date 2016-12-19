package com.ss.schedule.model;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
	long id;

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

	private String firstName;
	private String lastName;

	public Teacher( String firstName, String lastName,long id) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	private List<Subject> subjects = new ArrayList<>();

	public Teacher() {

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Teacher)) return false;

		Teacher teacher = (Teacher) o;

		if (firstName != null ? !firstName.equals(teacher.firstName) : teacher.firstName != null) return false;
		return lastName != null ? lastName.equals(teacher.lastName) : teacher.lastName == null;

	}

	@Override
	public int hashCode() {
		int result = firstName != null ? firstName.hashCode() : 0;
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		return result;
	}

	public Teacher(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		
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
