package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Subject implements Serializable {
	private String name;
	private Type type;
	private int courseN;
	private List<Subject> list = new ArrayList<>();

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

	public List<Subject> getList() {
		return list;
	}

	public void setList(List<Subject> list) {
		this.list = list;
	}
}
