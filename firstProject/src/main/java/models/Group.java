package models;

import java.util.List;

public class Group {
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

	@Override
	public String toString() {
		return "Group{" +
				"name='" + name + '\'' +
				", amount=" + amount +
				", list=" + list +
				'}';
	}
}
