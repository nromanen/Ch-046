package FirstProject;

import java.util.ArrayList;
import java.util.Comparator;

public class Teacher {
	private String firstName;
	private String lastName;
	private Qualification qualification;
	private ArrayList<Subject> subjects = new ArrayList<>();

	public Teacher() {

	}

	public Teacher(String firstName, String lastName, Qualification qualification) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.qualification = qualification;
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

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
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
