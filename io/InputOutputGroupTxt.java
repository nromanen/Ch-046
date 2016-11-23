package com.ss.schedule.io;

import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputOutputGroupTxt implements InputOutput<List<Group>> {

	private static final String ERROR_MESSAGE_EMPTY_LIST = "[ERROR] List of groups is empty!";

	private final String groupPattern = "^Group\\s+((\\d{2})|(\\d{2}\\-\\d))\\s+\\((\\d{0,2})\\):\\s*\\n*";
	private final String subjectPattern = "^([A-Za-z\\.\\-\\+#]+)\\(([A-Z]*)\\)\\s*((,\\s*(\\d)\\s*\\n*)|(\\n*))";

	@Override
	public List<Group> readFromFile(File file) {
		List<Group> groups = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String lineFromFile = br.readLine();
			Pattern groupPattern = Pattern.compile(this.groupPattern);
			Pattern subjectPattern = Pattern.compile(this.subjectPattern);
			Matcher groupMatcher;
			Matcher subjectMatcher;
			Group group = null;
			int lineCount = 1;

			while (lineFromFile != null) {
				if (lineFromFile.equals("")) {
					groups.add(group);
					lineFromFile = br.readLine();
					lineCount++;
					continue;
				}

				groupMatcher = groupPattern.matcher(lineFromFile);
				subjectMatcher = subjectPattern.matcher(lineFromFile);

				if (groupMatcher.find()) {
					group = initializeGroupState(groupMatcher);
				} else if (subjectMatcher.find()) {
					group.getSubjects().add(createSubject(subjectMatcher));
				} else {
					throw new IllegalArgumentException("[ERROR] Invalid record in line: " + lineCount);
				}

				lineFromFile = br.readLine();
				lineCount++;
			}
		} catch (IOException ex) {
			//TODO
			ex.printStackTrace();
		}

		return groups;
	}

	private Group initializeGroupState(Matcher groupMatcher) {
		String groupName = groupMatcher.group(1);
		int groupAmount = Integer.valueOf(groupMatcher.group(4));

		return new Group(groupName, groupAmount, new ArrayList<>());
	}

	private Subject createSubject(Matcher subjectMatcher) {
		String subjectName = subjectMatcher.group(1);
		String subjectCourse = subjectMatcher.group(5);
		SubjectType subjectType = SubjectType.valueOf(subjectMatcher.group(2));
		int course = 0;

		if (subjectCourse != null) {
			course = Integer.valueOf(subjectCourse);
		}

		return new Subject(subjectName, course, subjectType);
	}

	@Override
	public void writeToFile(File file, List<Group> groups) {
		if (groups.size() == 0) {
			throw new IllegalArgumentException(ERROR_MESSAGE_EMPTY_LIST);
		}

		List<String> formattedGroups = new ArrayList<>();
		for (Group group : groups) {
			formattedGroups.add(convertGroupToString(group));
		}

		writeStringsToFile(file, formattedGroups);
	}

	private String convertGroupToString(Group group) {
		StringBuilder sb = new StringBuilder();
		sb.append("Group ").append(group.getName()).append(" (").append(group.getCount()).append("):\n");

		for (Subject subject : group.getSubjects()) {
			sb.append(subject.getName()).append("(").append(subject.getType()).append(")");

			if (subject.getCourse() != 0) {
				sb.append(", ").append(subject.getCourse());
			}
			sb.append("\n");
		}

		sb.append("\n");
		return sb.toString();
	}

	private void writeStringsToFile(File file, List<String> groups) {
		try (Writer writer = new BufferedWriter(new FileWriter(file))) {
			for (String group : groups) {
				writer.write(group);
			}
		} catch (IOException e) {
			//TODO
			e.printStackTrace();
		}
	}
}
