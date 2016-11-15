package entities;

import java.util.List;

public class Teacher {
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
