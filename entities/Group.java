package com.ss.schedule.model;

import java.util.ArrayList;
import java.util.List;

public class Group {

	private String groupName;
	private int groupAmount;
	private List<Subject> subjects;

	public Group(String groupName, int groupAmount, List<Subject> subjects) {
		this.groupName = groupName;
		this.groupAmount = groupAmount;
		this.subjects = subjects;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getGroupAmount() {
		return groupAmount;
	}

	public void setGroupAmount(int groupAmount) {
		this.groupAmount = groupAmount;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<Group> getAllSubgroups(Subject subject) {
		if (subject.getSubjectType().getMaxStudentAmount() >= groupAmount) {
			List<Group> groups = new ArrayList<>();
			groups.add(this);
			return groups;
		}

		return createSubGroups(subject);
	}

	private List<Group> createSubGroups(Subject subject) {
		int maxStudentAmount = subject.getSubjectType().getMaxStudentAmount();
		List<Group> groups = new ArrayList<>();

		for (int i = 2; ; i++) {
			if ((i * maxStudentAmount) >= groupAmount) {
				String subGroupName;
				for (int j = 1; j < i; j++) {
					subGroupName = groupName + "-" + j;
					int subGroupAmount = groupAmount / i;
					groups.add(new Group(subGroupName, subGroupAmount, subjects));
				}
				subGroupName = groupName + "-" + i;
				int remainStudentAmount = groupAmount - ((groupAmount / i) * (i - 1));
				groups.add(new Group(subGroupName, remainStudentAmount, subjects));

				break;
			}
		}

		return groups;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Group)) return false;

		Group group = (Group) o;

		return groupName != null ? groupName.equals(group.groupName) : group.groupName == null;

	}

	@Override
	public int hashCode() {
		return groupName != null ? groupName.hashCode() : 0;
	}
}
