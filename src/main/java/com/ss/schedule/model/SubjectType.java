package com.ss.schedule.model;


import com.ss.schedule.dao.SubjectTypeDao;

public enum SubjectType {
	LECTURE(0), SEMINAR(0), PRACTICE(15), LAB(7);



	public void setMaxStudentAmount(int maxStudentAmount) {
		this.maxStudentAmount = maxStudentAmount;
	}

	public long
	getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private long id;
	private int maxStudentAmount;

	SubjectType(int maxStudentAmount) {
		this.maxStudentAmount = maxStudentAmount;
        this.id=new SubjectTypeDao().getEntityIdByName(this.name());
	}

	public int getMaxStudentAmount() {
		return maxStudentAmount;
	}
}
