package ua.cv.tim.model;

public enum Race {
	
	ROMANS("race.romans"), TEUTONS("race.teutons"), GAULS("race.gauls");

	Race(String key) {
		this.key = key;
	}

	private String key;

	public String getKey() {
		return key;
	}

}
