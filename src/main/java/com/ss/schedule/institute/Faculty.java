package com.ss.schedule.institute;

import com.ss.schedule.model.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Faculty {

	private static final String ERROR_MESSAGE_ADD_SUBJECT = "[ERROR] This subject has already existed!";
	private static final String ERROR_MESSAGE_DELETE_SUBJECT = "[ERROR] Subject doesn't no exist!";
	private static final String ERROR_MESSAGE_EMPTY_LIST = "[ERROR] List is empty!";

	private String name;
	private List<Group> groups;

	private List<Subject> subjects;
	private List<Teacher> teachers;
	private static List<Classroom> classrooms;

	public Faculty() {

	}

	public Faculty(String name, List<Group> groups, List<Subject> subjects, List<Teacher> teachers,List<Classroom> classrooms) {
		this.name = name;
		this.groups = groups;
		this.subjects = subjects;
		this.teachers = teachers;
		Faculty.classrooms=classrooms;
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
		Faculty.classrooms = classrooms;
	}


	//зо якщо повертати не ліст з 1 потоком,а ліст груп, а десь потім формувати потоки???
    //поліморфізм.Хай це робить предмет в залежності від свого типу
	//у потоку завжди один предмет, то чи не логічніше в базовому класі мати сабжет,а ліст сабжектів лище в
	//похідних класах?
	//Ще не забудь запитати про переповнення стеку в джейсонах!!!
	public LinkedHashMap<Subject, List<? extends StudentCommunity>> getGroupsSubgroupsStreams() {
		LinkedHashMap<Subject, List<? extends StudentCommunity>> groupsSubgroupsStreams = new LinkedHashMap<>();
        List<? extends StudentCommunity> students=new ArrayList<>();
		for (Subject subject : subjects) {
			if (subject.getType().getMaxStudentAmount() > 0) {
				students = createSubgroups(subject);
			} else {
				students = createStream(subject);
			}
			groupsSubgroupsStreams.put(subject, students);
		}
		return groupsSubgroupsStreams;
	}

	public List<Subgroup> createSubgroups(Subject subject) {
		List<Subgroup> subgroups = new ArrayList<>();
		int maxStudentAmount = subject.getType().getMaxStudentAmount();
		for (Group group : groups) {
			if (group.getSubjects().contains(subject)) {
				int count = group.getCount();
				if (maxStudentAmount >= count) {
					Subgroup subgroup=new Subgroup(group.getName(),group.getCount(),group);
                    subgroup.getSubjects().add(subject);
                    group.getSubgroups().add(subgroup);
					subgroups.add(subgroup);
				} else {
					for (int i = 2; ; i++) {
						if ((i * maxStudentAmount) >= count) {
							int subGroupAmount = count / i;
							for (int j = 1; j < i; j++) {
								subgroups.add(formSubGroup(group, subGroupAmount, j,subject));
							}
							int remainStudentAmount = count - ((count / i) * (i - 1));
							subgroups.add(formSubGroup(group, remainStudentAmount, i,subject));
							break;
						}
					}
				}
			}
		}

		return subgroups;
	}

	private Subgroup formSubGroup(Group group, int subGroupAmount, int subGroupIndex,Subject subject) {
		Subgroup subgroup=new Subgroup(group.getName()+ "-"+subGroupIndex,subGroupAmount,group);
		subgroup.getSubjects().add(subject);
        group.getSubgroups().add(subgroup);
		return subgroup;
	}

	public List<Stream> createStream(Subject subject) {
        List<Stream> streams=new ArrayList<>();
        Stream stream = new Stream();
		int streamAmount = 0;
		StringBuilder nameBuilder = new StringBuilder();
		for (Group group : groups) {
			if (group.getSubjects().contains(subject)) {
				nameBuilder = buildStreamName(nameBuilder, group.getName());
				streamAmount += group.getCount();
                stream.getGroups().add(group);
			}
		}

		String streamName = nameBuilder.toString();

		if (!stream.getGroups().isEmpty()) {
            stream.setName(streamName);
            stream.setCount(streamAmount);
            stream.getSubjects().add(subject);
            streams.add(stream);
			return streams;
		}

		return null;
	}

	private StringBuilder buildStreamName(StringBuilder streamName, String name) {
        if (streamName.length() == 0) {
			streamName.append(name);
		} else  {
			streamName.append("_").append(name);
		}
		return streamName;
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
