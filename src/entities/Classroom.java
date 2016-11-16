package entities;

public class Classroom implements Comparable<Classroom>{
	private String number;
	private long capacity;
	private Type type;

	public Classroom() {
		super();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public long getCapacity() {
		return capacity;
	}

	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Classroom{" +
				"number='" + number + '\'' +
				", capacity=" + capacity +
				", type=" + type +
				'}';
	}

	@Override
	public int compareTo(Classroom o) {
		return (int)(this.capacity - o.getCapacity());
	}
}
