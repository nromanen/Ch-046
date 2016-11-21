package sample.entities;

public class Subject {
	private String name;
	private TypeOfLesson type;
	private int courseN;

	public Subject(String name, TypeOfLesson type) {
		this.name = name;
		this.type = type;

	}

	public Subject() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TypeOfLesson getType() {
		return type;
	}

	public void setType(TypeOfLesson type) {
		this.type = type;
	}

	public int getCourseN() {
		return courseN;
	}

	public void setCourseN(int courseN) {
		this.courseN = courseN;
	}
}