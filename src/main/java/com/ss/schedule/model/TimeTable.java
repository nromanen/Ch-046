package com.ss.schedule.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "TimeTable")
@XmlType(propOrder = { "subject", "pair", "teacher", "day", "oddnessOfWeek", })
@SuppressWarnings("serial")
public class TimeTable implements Serializable {
	/* Example: Algebra(LAB), FIRST, [Ann, Parkinson], 101, MONDAY, All */
	public static final String TIMETABLES_PATTERN = "(^[A-Z][a-z]{2,15}+)\\(([A-Z]{2,15})\\)" +
	/* The first group is the subject's title, the second-- it's type; */
			",\\s([A-Z]+)" +
			/* the third -- it's ENUM number */
			",\\s\\[([A-Z][a-z]+),\\s([A-Z][a-z]+)\\]," +
			/* the fourth -- professor's name, the fifth -- professor's surname */
			"\\s(\\d{1,3})," +
			/* the sixth -- group number */
			"\\s([A-Z]+)," +
			/* the seventh -- day of week */
			"\\s([A-Z][a-z]{2}|[A-Z]{4})";
	/* the eighth -- oddness of week */
	private Subject subject;
	private Pair pair;
	private Teacher teacher;
	private StudentCommunity studentCommunity;
    private DayOfWeek day;
	private OddnessOfWeek oddnessOfWeek;
	private Classroom classroom;

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
//		groupsSubgroupsStreams.get(subjectsKeySet[1]).remove(0);
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Pair getPair() {
		return pair;
	}

	public void setPair(Pair pair) {
		this.pair = pair;
	}

	public OddnessOfWeek getOddnessOfWeek() {
		return oddnessOfWeek;
	}

	public void setOddnessOfWeek(OddnessOfWeek oddnessOfWeek) {
		this.oddnessOfWeek = oddnessOfWeek;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public StudentCommunity getStudentCommunity() {
		return studentCommunity;
	}

	public void setStudentCommunity(StudentCommunity studentCommunity) {
		this.studentCommunity = studentCommunity;
	}

	@Override
	public String toString() {
		return subject.getName() + "(" + subject.getType() + ")" + ", " + pair + ", [" + teacher.getFirstName() + ", "
				+ teacher.getLastName() + "], " + /*getGroup().getName() +*/ ", " + day + ", " + oddnessOfWeek;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TimeTable)) return false;

		TimeTable timeTable = (TimeTable) o;

		if (subject != null ? !subject.equals(timeTable.subject) : timeTable.subject != null) return false;
		if (pair != timeTable.pair) return false;
		if (teacher != null ? !teacher.equals(timeTable.teacher) : timeTable.teacher != null) return false;
		if (studentCommunity != null ? !studentCommunity.equals(timeTable.studentCommunity) : timeTable.studentCommunity != null)
			return false;
		if (day != timeTable.day) return false;
		if (oddnessOfWeek != timeTable.oddnessOfWeek) return false;
		return classroom != null ? classroom.equals(timeTable.classroom) : timeTable.classroom == null;

	}

	@Override
	public int hashCode() {
		int result = subject != null ? subject.hashCode() : 0;
		result = 31 * result + (pair != null ? pair.hashCode() : 0);
		result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
		result = 31 * result + (studentCommunity != null ? studentCommunity.hashCode() : 0);
		result = 31 * result + (day != null ? day.hashCode() : 0);
		result = 31 * result + (oddnessOfWeek != null ? oddnessOfWeek.hashCode() : 0);
		result = 31 * result + (classroom != null ? classroom.hashCode() : 0);
		return result;
	}
}
