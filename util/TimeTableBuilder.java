package util;

import java.util.Arrays;

import model.DayOfWeek;
import model.Group;
import model.OddnessOfWeek;
import model.Pair;
import model.Subject;
import model.Teacher;
import model.TimeTable;

public class TimeTableBuilder {
	private Subject subject;
	private Teacher teacher;
	private Group group;
	private Pair pair;
	private OddnessOfWeek oddnessOfWeek;
	private DayOfWeek day;
	private Pair[] restriction;

	public TimeTableBuilder(Pair[] restriction) {
		this.restriction = restriction;
	}

	TimeTableBuilder buildSubject(Subject subject) {
		this.subject = subject;
		return this;
	}

	TimeTableBuilder buildTeacher(Teacher teacher) {
		this.teacher = teacher;
		return this;
	}

	TimeTableBuilder buildGroup(Group group) {
		this.group = group;
		return this;
	}

	TimeTableBuilder buildPair(Pair pair) {
		this.pair = pair;
		return this;
	}

	TimeTableBuilder buildOddnessOfWeek(OddnessOfWeek oddnessOfWeek) {
		this.oddnessOfWeek = oddnessOfWeek;
		return this;
	}

	TimeTableBuilder buildDayOfWeek(DayOfWeek day) {
		this.day = day;
		return this;
	}

	TimeTable build() {
		TimeTable timeTable = new TimeTable();
		timeTable.setSubject(subject);
		timeTable.setTeacher(teacher);
		timeTable.setGroup(group);
		timeTable.setPair(pair);
		if (!Arrays.asList(restriction).contains(pair))
			throw new IllegalStateException("Lesson " + pair + " is not in the range.. ");
		timeTable.setOddnessOfWeek(oddnessOfWeek);
		timeTable.setDay(day);
		return timeTable;
	}
}
