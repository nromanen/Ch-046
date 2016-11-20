package model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "TimeTable")
@XmlType(propOrder = { "subject", "pair", "teacher", "group", "day", "oddnessOfWeek", })
@SuppressWarnings("serial")
public class TimeTable implements Serializable {
	private Subject subject;
	private Pair pair;
	private Teacher teacher;
	private Group group;
	private DayOfWeek day;
	private OddnessOfWeek oddnessOfWeek;

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

	@Override
	public String toString() {
		return subject.getName() + "(" + subject.getType() + ")" + ", " + pair + ", [" + teacher.getFirstName() + ", "
				+ teacher.getLastName() + "], " + group.getName() + ", " + day + ", " + oddnessOfWeek;
	}

}
