package com.ss.schedule.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement(name = "TimeTable")
@XmlType(propOrder = { "subject", "pair", "teacher", "group", "day", "oddnessOfWeek", })
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
	private Group group;
	private DayOfWeek day;
	private OddnessOfWeek oddnessOfWeek;
	private Classroom classroom;

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

//	@Override
//	public String toString() {
//		return subject.getName() + "(" + subject.getType() + ")" + ", " + pair + ", [" + teacher.getFirstName() + ", "
//				+ teacher.getLastName() + "], " + group.getName() + ", " + day + ", " + oddnessOfWeek;
//	}


	@Override
	public String toString() {
		return "TimeTable{" +
				"subject=" + subject +
				", pair=" + pair +
				", group=" + group +
				", day=" + day +
				", oddnessOfWeek=" + oddnessOfWeek +
				", classroom=" + classroom +
				'}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((oddnessOfWeek == null) ? 0 : oddnessOfWeek.hashCode());
		result = prime * result + ((pair == null) ? 0 : pair.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeTable other = (TimeTable) obj;
		if (day != other.day)
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (oddnessOfWeek != other.oddnessOfWeek)
			return false;
		if (pair != other.pair)
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}

}
