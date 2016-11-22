package model;

import java.io.Serializable;

public enum Type implements Serializable {
	LECTURE(0), SEMINAR(0), PRACTICE(15), LAB(7);

	private int maxStudentAmount;

	Type(int maxStudentAmount) {
		this.maxStudentAmount = maxStudentAmount;
	}

	public int getMaxStudentAmount() {
		return maxStudentAmount;
}
}
