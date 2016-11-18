package model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Teacher implements Serializable {
	private String firstName;
	private String lastName;
	List<Subject> listOfSubject;

	public Teacher() {
		super();
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
