package FirstProject;

import java.util.ArrayList;
import java.util.Comparator;

public class Teacher {
	private String firstName;
	private String lastName;
	private ArrayList<Subject> subjects = new ArrayList<>();

	public Teacher() {

	}

	public Teacher(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Teacher name " + getLastName() + " " + getFirstName();
	}

	public ArrayList<Subject> getList() {
		return subjects;
	}

	public void setList(ArrayList<Subject> listofLessons) {
		this.subjects = listofLessons;
	}
}
