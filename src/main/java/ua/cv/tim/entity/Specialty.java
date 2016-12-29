package main.java.ua.cv.travian.entity;

public enum Specialty {

	Defense("def"), //
	Offense("off"), //
	Machinery("mech"),//
	Scouting("scout");

	private String name;

	private Specialty(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
