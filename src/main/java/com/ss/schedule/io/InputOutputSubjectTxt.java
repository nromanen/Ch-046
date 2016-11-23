package com.ss.schedule.io;

import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputOutputSubjectTxt implements InputOutput<List<Subject>> {

	private static final String ERROR_MESSAGE_EMPTY_LIST = "[ERROR] List of groups is empty!";

	private final String subjectPattern = "^Subject:\\s*([A-Za-z\\.\\-\\+#]+)\\(([A-Z]*)\\)\\s*((,\\s*(\\d)\\s*\\n*;*)|\\n*;*)";

	@Override
	public List<Subject> readFromFile(String filePath) {
		List<Subject> subjects = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
			String lineFromFile = br.readLine();
			Pattern subjectPattern = Pattern.compile(this.subjectPattern);
			Matcher subjectMatcher;
			int lineCount = 1;

			while (lineFromFile != null) {

				subjectMatcher = subjectPattern.matcher(lineFromFile);

				if (subjectMatcher.find()) {
					subjects.add(createSubject(subjectMatcher));
				} else {
					throw new IllegalArgumentException("[ERROR] Invalid record in line: " + lineCount);
				}

				lineFromFile = br.readLine();
				lineCount++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return subjects;
	}

	private Subject createSubject(Matcher subjectMatcher) {
		String subjectName = subjectMatcher.group(1);
		SubjectType subjectType = SubjectType.valueOf(subjectMatcher.group(2));
		String subjectCourse = subjectMatcher.group(5);
		int course = 0;

		if (subjectCourse != null) {
			course = Integer.valueOf(subjectCourse);
		}

		return new Subject(subjectName, course, subjectType);
	}

	@Override
	public void writeToFile(String filePath, List<Subject> subjects) {
		if (subjects.size() == 0) {
			throw new IllegalArgumentException(ERROR_MESSAGE_EMPTY_LIST);
		}

		List<String> formattedSubjects = new ArrayList<>();
		for (Subject Subject : subjects) {
			formattedSubjects.add(convertSubjectToString(Subject));
		}

		writeStringsToFile(new File(filePath), formattedSubjects);
	}

	private String convertSubjectToString(Subject subject) {
		StringBuilder sb = new StringBuilder();

		sb.append("Subject: ").append(subject.getName()).append("(").append(subject.getType()).append(")");

		if (subject.getCourse() != 0) {
			sb.append(", ").append(subject.getCourse());
		}
		sb.append(";\n");

		return sb.toString();
	}

	private void writeStringsToFile(File file, List<String> subjects) {
		try (Writer writer = new BufferedWriter(new FileWriter(file))) {
			for (String subject : subjects) {
				writer.write(subject);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
