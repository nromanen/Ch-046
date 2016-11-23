package com.ss.schedule.model;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "classroom")
public class Classroom implements Comparable<Classroom>{

	@JacksonXmlProperty
	private String name;
	@JacksonXmlProperty
	private int capacity;
	@JacksonXmlProperty
	private List<Type> types;
	@JacksonXmlProperty
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
		return "Classroom{" +
				"name='" + name + '\'' +
				", capacity=" + capacity +
				", types=" + types +
				", description='" + description + '\'' +
				'}' + "\n";
	}

	@Override
	public int compareTo(Classroom o) {
		return this.capacity - o.getCapacity();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Classroom classroom = (Classroom) o;

		if (capacity != classroom.capacity) return false;
		if (name != null ? !name.equals(classroom.name) : classroom.name != null) return false;
		if (types != null ? !types.equals(classroom.types) : classroom.types != null) return false;
		return description != null ? description.equals(classroom.description) : classroom.description == null;

	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + capacity;
		result = 31 * result + (types != null ? types.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}
}