package com.ss.schedule.model;


public enum SubjectType {
	LECTURE(0), SEMINAR(0), PRACTICE(15), LAB(7);

	private long id;
	private int maxStudentAmount;

	SubjectType(int maxStudentAmount) {
		this.maxStudentAmount = maxStudentAmount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMaxStudentAmount() {
		return maxStudentAmount;
	}

	public void setMaxStudentAmount(int maxStudentAmount) {
		this.maxStudentAmount = maxStudentAmount;
	}
}
