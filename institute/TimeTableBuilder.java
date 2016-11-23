package util;

import java.util.List;

import model.DayOfWeek;
import model.Group;
import model.OddnessOfWeek;
import model.Pair;
import model.Subject;
import model.Teacher;
import model.TimeTable;

public class TimeTableBuilder {
	private Subject subject;
	private Pair pair;
	private Teacher teacher;
	private Group group;
	private DayOfWeek day;
	private OddnessOfWeek oddnessOfWeek;
	private List<Pair> restriction;

	public List<Pair> getRestriction() {
		return restriction;
	}

	public void setRestriction(List<Pair> restriction) {
		this.restriction = restriction;
	}
	
	public TimeTableBuilder(List<Pair> restriction) {
		this.restriction = restriction;
	}

	public TimeTableBuilder buildSubject(Subject subject) {
		this.subject = subject;
		return this;
	}

	public TimeTableBuilder buildTeacher(Teacher teacher) {
		this.teacher = teacher;
		return this;
	}

	public TimeTableBuilder buildGroup(Group group) {
		this.group = group;
		return this;
	}

	public TimeTableBuilder buildPair(Pair pair) {
		this.pair = pair;
		return this;
	}

	public TimeTableBuilder buildOddnessOfWeek(OddnessOfWeek oddnessOfWeek) {
		this.oddnessOfWeek = oddnessOfWeek;
		return this;
	}

	public TimeTableBuilder buildDayOfWeek(DayOfWeek day) {
		this.day = day;
		return this;
	}

	public TimeTable build() {
		TimeTable timeTable = new TimeTable();
		timeTable.setSubject(subject);
		timeTable.setPair(pair);
		if (!restriction.contains(pair))
			throw new IllegalStateException("Lesson " + pair + " is not in the range.. ");
		timeTable.setTeacher(teacher);
		timeTable.setGroup(group);
		timeTable.setDay(day);
		if (this.oddnessOfWeek == null) {
			timeTable.setOddnessOfWeek(OddnessOfWeek.All);
		} else {
			timeTable.setOddnessOfWeek(oddnessOfWeek);
		}
		return timeTable;
	}
}
