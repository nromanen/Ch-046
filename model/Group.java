package model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Group implements Serializable {

	private String name;
	private int amount;
	List<Subject> list;

	public Group() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
