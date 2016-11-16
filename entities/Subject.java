package com.ss.schedule.model;

public class Subject {

	private String subjectName;
	private int courseNumber;
	private SubjectType subjectType;

	public Subject(String subjectName, int courseNumber, SubjectType subjectType) {
		this.subjectName = subjectName;
		this.courseNumber = courseNumber;
		this.subjectType = subjectType;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public SubjectType getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(SubjectType subjectType) {
		this.subjectType = subjectType;
	}

	public int getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Subject)) return false;

		Subject subject = (Subject) o;

		if (courseNumber != subject.courseNumber) return false;
		if (subjectName != null ? !subjectName.equals(subject.subjectName) : subject.subjectName != null) return false;
		return subjectType == subject.subjectType;

	}

	@Override
	public int hashCode() {
		int result = subjectName != null ? subjectName.hashCode() : 0;
		result = 31 * result + courseNumber;
		result = 31 * result + (subjectType != null ? subjectType.hashCode() : 0);
		return result;
	}
}
