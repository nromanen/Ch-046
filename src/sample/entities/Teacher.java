package sample.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Teacher {
	private String firstName;
	private String lastName;
	@JsonIgnore
	List<Subject> listOfSubject;

	public Teacher() {
		super();
	}

	public List<Subject> getListOfSubject() {
		return listOfSubject;
	}

	public void setListOfSubject(List<Subject> listOfSubject) {
		this.listOfSubject = listOfSubject;
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

}
