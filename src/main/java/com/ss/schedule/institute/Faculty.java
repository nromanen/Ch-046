package com.ss.schedule.institute;

import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.Teacher;
import com.ss.schedule.model.Classroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Faculty {

	private static final String ERROR_MESSAGE_ADD_SUBJECT = "[ERROR] This subject has already existed!";
	private static final String ERROR_MESSAGE_DELETE_SUBJECT = "[ERROR] Subject doesn't no exist!";
	private static final String ERROR_MESSAGE_EMPTY_LIST = "[ERROR] List is empty!";

	private String name;
	private List<Group> groups;
	private List<Subject> subjects;
	private List<Teacher> teachers;
	private List<Classroom> classrooms;

	public Faculty() {

	}

	public Faculty(String name, List<Group> groups, List<Subject> subjects, List<Teacher> teachers) {
		this.name = name;
		this.groups = groups;
		this.subjects = subjects;
		this.teachers = teachers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(List<Classroom> classrooms) {
		this.classrooms = classrooms;
	}

	public HashMap<Subject, List<Group>> getGroupsSubgroupsStreamsBySubject() {
		HashMap<Subject, List<Group>> groupsSubgroupsStreams = new HashMap<>();
		for (Subject subject : subjects) {
			List<Group> groups;
			if (subject.getType().getMaxStudentAmount() > 0) {
				groups = createSubgroups(subject);
			} else {
				groups = createStream(subject);
			}
			groupsSubgroupsStreams.put(subject, groups);
		}
		return groupsSubgroupsStreams;
	}

	private List<Group> createSubgroups(Subject subject) {
		List<Group> subgroups = new ArrayList<>();
		int maxStudentAmount = subject.getType().getMaxStudentAmount();

		for (Group group : groups) {
			if (group.getSubjects().contains(subject)) {
				int count = group.getCount();
				if (maxStudentAmount >= count) {
					subgroups.add(group);

				} else {
					for (int i = 2; ; i++) {
						if ((i * maxStudentAmount) >= count) {
							int subGroupAmount = count / i;
							for (int j = 1; j < i; j++) {
								subgroups.add(formGroup(group.getName(), subGroupAmount, j));
							}
							int remainStudentAmount = count - ((count / i) * (i - 1));
							subgroups.add(formGroup(group.getName(), remainStudentAmount, i));
							break;
						}
					}
				}
			}
		}

		return subgroups;
	}

	private Group formGroup(String groupName, int subGroupAmount, int subGroupIndex) {
		StringBuilder subGroupName = new StringBuilder();
		subGroupName.append(groupName).append("-").append(subGroupIndex);
		return new Group(subGroupName.toString(), subGroupAmount, subjects);
	}

	private List<Group> createStream(Subject subject) {
		int streamAmount = 0;
		StringBuilder nameBuilder = new StringBuilder();

		for (Group group : groups) {
			if (group.getSubjects().contains(subject)) {
				nameBuilder = buildStreamName(nameBuilder, group.getName());
				streamAmount += group.getCount();
			}
		}

		String streamName = nameBuilder.toString();

		if (!streamName.isEmpty()) {
			List<Subject> subjects = new ArrayList<>();
			subjects.add(subject);

			List<Group> stream = new ArrayList<>();
			stream.add(new Group(nameBuilder.toString(), streamAmount, subjects));
			return stream;
		}

		return new ArrayList<>();
	}

	private StringBuilder buildStreamName(StringBuilder streamName, String name) {
		StringBuilder sb = streamName;
		if (sb.length() == 0) {
			sb.append(name);
		} else  {
			sb.append("_").append(name);
		}
		return sb;
	}

	/*
	* @return number of subjects that have been added to the main list of subjects.
	* Subjects that have not been added already exist in the list
	* */
	public int addListOfSubjects(List<Subject> subjectList) {
		int addedSubjectCount = 0;
		if (subjectList.size() != 0) {
			for (Subject subject : subjectList) {
				if (!subjects.contains(subject)) {
					subjects.add(subject);
					addedSubjectCount++;
				}
			}
		} else {
			throw new IllegalArgumentException(ERROR_MESSAGE_EMPTY_LIST);
		}
		return addedSubjectCount;
	}

	public void addSubject(Subject subject) {
		if (!subjects.contains(subject)) {
			subjects.add(subject);
		} else {
			throw new IllegalArgumentException(ERROR_MESSAGE_ADD_SUBJECT);
		}
	}

	public void deleteSubject(Subject subject) {
		if (subjects.contains(subject)) {
			subjects.remove(subject);
		} else {
			throw new IllegalArgumentException(ERROR_MESSAGE_DELETE_SUBJECT);
		}
	}

	public List<Subject> getUnusedSubjects() {
		List<Subject> subjectsThatDoNotUse = new ArrayList<>();

		for (Subject subject : subjects) {
			boolean isSubjectUse = false;

			for (Group group : groups) {
				List<Subject> groupSubjects = group.getSubjects();
				if (groupSubjects.contains(subject)) {
					isSubjectUse = true;
					break;
				}
			}

			if (!isSubjectUse) {
				subjectsThatDoNotUse.add(subject);
			}
		}

		return subjectsThatDoNotUse;
	}
}
