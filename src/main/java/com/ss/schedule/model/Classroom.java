package com.ss.schedule.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@JacksonXmlRootElement(localName = "classroom")
public class Classroom implements Comparable<Classroom>{

	@XmlTransient
	@JsonIgnore
	private int id;
	@JacksonXmlProperty
	private String name;
	@JacksonXmlProperty
	private int capacity;
	@JacksonXmlProperty
	private List<SubjectType> types;
	@JacksonXmlProperty
	private String description;


	public Classroom() {
		super();
	}

	public Classroom(String name, int capacity, List<SubjectType> types) {
		this.name = name;
		this.capacity = capacity;
		this.types = types;
	}

	public Classroom(String name, int capacity, List<SubjectType> types, String description) {
		this(name, capacity, types);
		this.description = description;
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

	public List<SubjectType> getTypes() {
		return types;
	}

	public void setTypes(List<SubjectType> types) {
		this.types = types;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return types != null ? types.equals(classroom.types) : classroom.types == null;

	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + capacity;
		result = 31 * result + (types != null ? types.hashCode() : 0);
		return result;
	}
}