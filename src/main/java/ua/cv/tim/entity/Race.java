package main.java.ua.cv.travian.entity;


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
