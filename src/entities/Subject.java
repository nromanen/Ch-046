package entities;

public class Subject {
	private String name;
	private Type type;
	private int courseN;
	private long studentsAmount;

	public Subject() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getCourseN() {
		return courseN;
	}

	public void setCourseN(int courseN) {
		this.courseN = courseN;
	}

	public long getStudentsAmount() {
		return studentsAmount;
	}

	public void setStudentsAmount(long studentsAmount) {
		this.studentsAmount = studentsAmount;
	}
}