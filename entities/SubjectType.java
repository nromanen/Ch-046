package com.ss.schedule.model;


public enum SubjectType {
	LECTURE(0), SEMINAR(0), PRACTICE(15), LAB(7);

	private int maxStudentAmount;

	SubjectType(int maxStudentAmount) {
		this.maxStudentAmount = maxStudentAmount;
	}

	public int getMaxStudentAmount() {
		return maxStudentAmount;
	}
}
