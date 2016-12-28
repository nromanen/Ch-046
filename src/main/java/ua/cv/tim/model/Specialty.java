package ua.cv.tim.model;

public enum Specialty {

	Defense("def"),
	Offense("off"),
	Machinery("mech"),
	Scouting("scout");

	private String name;

	Specialty(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
