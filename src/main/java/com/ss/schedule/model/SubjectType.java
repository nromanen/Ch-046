package com.ss.schedule.model;


public enum SubjectType {
	LECTURE(1, 0), SEMINAR(2, 0), PRACTICE(3, 15), LAB(4, 7);

	private int maxStudentAmount;
	private long id;

	SubjectType(long id, int maxStudentAmount) {

		this.maxStudentAmount = maxStudentAmount;
		this.id = id;
	}

	public int getMaxStudentAmount() {
		return maxStudentAmount;
	}

	public long getId() {
		return id;
	}

}
