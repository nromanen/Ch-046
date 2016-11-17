package models;

public class TimeTable {
	private Subject subject;
	private Pair pair;
	private OddnessOfWeek oddnessOfWeek;
	private DayOfWeek day;

	public TimeTable() {
		super();
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
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
}
