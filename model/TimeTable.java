package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="TimeTable")
@XmlType(propOrder = {"subject","pair","oddnessOfWeek","day","teacher","group"})
@SuppressWarnings("serial")
public class TimeTable implements Serializable {
	private Subject subject;
	private Teacher teacher;
	private Group group;
	private Pair pair;
	private OddnessOfWeek oddnessOfWeek;
	private DayOfWeek day;

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
		return "TimeTable [subject=" + subject.getName() + ", teacher= " + teacher.getLastName() + ", group= "
				+ group.getName() + ", pair=" + pair + ", oddnessOfWeek=" + oddnessOfWeek + ", day=" + day + "]";
	}

}
