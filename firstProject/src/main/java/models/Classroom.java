package models;

import java.util.List;

public class Classroom implements Comparable<Classroom>{
	private String name;
	private int capacity;
	private List<Type> types;
	private String description;



	public Classroom() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return name + " " + types + ", " + capacity + (description==null? "" : ", \"" + description + "\"") +	";";
	}

	@Override
	public int compareTo(Classroom o) {
		return this.capacity - o.getCapacity();
	}
}
