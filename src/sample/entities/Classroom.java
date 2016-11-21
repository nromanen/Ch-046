package sample.entities;

public class Classroom {
	private int number;
	private int capacity;
	private TypeOfLesson type;

	public Classroom() {
		super();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public TypeOfLesson getType() {
		return type;
	}

	public void setType(TypeOfLesson type) {
		this.type = type;
	}

}
