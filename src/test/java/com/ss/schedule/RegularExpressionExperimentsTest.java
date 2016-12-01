package com.ss.schedule;

import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RegularExpressionExperimentsTest {
	private final String exampleGroup1 = "Group 23 (5):\n";
	private final String exampleGroup2 = "Group 23-1 (5):\n";

	private final String exampleSubject1 = "Algebra(PRACTICE), 2\n";
	private final String exampleSubject2 = "C++(LECTURE), 1\n";
	private final String exampleSubject3 = ".Net-C++(LAB)\n";

	@Test
	public void testSplitOnlySpaces() {
		String string = "Group 11";
		String pattern = "(\\s+)";

		String[] splitString = string.split(pattern);
		assertEquals(splitString.length, 2);

		string = "Group     11";
		splitString = string.split(pattern);
		assertEquals(splitString.length, 2);
	}

	@Test
	public void testSpacesAndBraces() {
		String string = "Group (11)";
		String pattern = "([\\s()]+)";

		String[] splitString = string.split(pattern);
		assertEquals(splitString.length, 2);

		string = "Group   ((( (11))) (";
		splitString = string.split(pattern);
		assertEquals(splitString.length, 2);
	}

	@Test
	public void testSpacesBracesComasNewLine() {
		String string = "Group 23 (5):" +
				"C++(LECTURE), 2\n" +
				"Algebra(PRACTICE), 0";
		String pattern = "([\\s():,\n0]+)";

		String[] splitString = string.split(pattern);
		assertEquals(splitString.length, 8);
	}

	@Test
	public void testFindGroup() {
		String regexGroupLine = "^Group";
		Pattern pattern = Pattern.compile(regexGroupLine);
		Matcher matcher = pattern.matcher(exampleGroup1);
		assertTrue(matcher.find());
	}

	@Test
	public void testFindGroupNumberWithoutSubgroups() {
		String regexGroupLine = "\\d{2}";
		Pattern pattern = Pattern.compile(regexGroupLine);
		Matcher matcher = pattern.matcher(exampleGroup1);
		assertTrue(matcher.find());
	}

	@Test
	public void testFindGroupNumberWithSubgroups() {
		String regexGroupLine = "\\d{2}-\\d";
		Pattern pattern = Pattern.compile(regexGroupLine);
		Matcher matcher = pattern.matcher(exampleGroup2);
		assertTrue(matcher.find());
	}

	@Test
	public void testFindGroupNumberWithOrWithoutSubgroups() {
		String regexGroupLine = "((\\d{2})|(\\d{2}-\\d))";
		Pattern pattern = Pattern.compile(regexGroupLine);
		Matcher matcher = pattern.matcher(exampleGroup2);
		assertTrue(matcher.find());
		matcher = pattern.matcher(exampleGroup1);
		assertTrue(matcher.find());
	}

	@Test
	public void testFindGroupAndGroupNumberWithOrWithoutSubgroups() {
		String regexGroupLine = "^Group\\s+((\\d{2})|(\\d{2}-\\d))\\s+";
		Pattern pattern = Pattern.compile(regexGroupLine);
		Matcher matcher = pattern.matcher(exampleGroup2);
		assertTrue(matcher.find());
		matcher = pattern.matcher(exampleGroup1);
		assertTrue(matcher.find());
	}

	@Test
	public void testFindStudentAmount() {
		String regexGroupLine = "\\(\\d{0,2}\\)";
		Pattern pattern = Pattern.compile(regexGroupLine);
		Matcher matcher = pattern.matcher(exampleGroup2);
		assertTrue(matcher.find());
		matcher = pattern.matcher(exampleGroup1);
		assertTrue(matcher.find());
	}

	@Test
	public void testSearchGroupPattern() {
		String regexGroupLine = "^Group\\s+((\\d{2})|(\\d{2}-\\d))\\s+\\(\\d{0,2}\\):\\s*\\n";
		Pattern pattern = Pattern.compile(regexGroupLine);
		Matcher matcher = pattern.matcher(exampleGroup1);
		assertTrue(matcher.find());
	}

	@Test
	public void testReadGroupNumber() {
		String regexGroupLine = "^Group\\s+((\\d{2})|(\\d{2}-\\d))\\s+\\((\\d{0,2})\\):\\s*\\n";
		Pattern pattern = Pattern.compile(regexGroupLine);
		Matcher matcher = pattern.matcher(exampleGroup2);
		assertTrue(matcher.find());
		String groupNumber = matcher.group(1);
		assertEquals(groupNumber, "23-1");

		matcher = pattern.matcher(exampleGroup1);
		assertTrue(matcher.find());
		groupNumber = matcher.group(1);
		assertEquals(groupNumber, "23");
	}

	@Test
	public void testReadGroupAmount() {
		String regexGroupLine = "^Group\\s+((\\d{2})|(\\d{2}\\-\\d))\\s+\\((\\d{0,2})\\):\\s*\\n";
		Pattern pattern = Pattern.compile(regexGroupLine);
		Matcher matcher = pattern.matcher(exampleGroup2);
		assertTrue(matcher.find());
		String groupNumber = matcher.group(1);
		String groupAmount = matcher.group(4);
		assertEquals(groupNumber, "23-1");
		assertEquals(groupAmount, "5");

		matcher = pattern.matcher(exampleGroup1);
		assertTrue(matcher.find());
		groupNumber = matcher.group(1);
		groupAmount = matcher.group(4);
		assertEquals(groupNumber, "23");
		assertEquals(groupAmount, "5");
	}

	@Test
	public void testSearchSubjectPattern() {
		String regexGroupLine = "^([A-Za-z\\.\\-\\+#]+)\\(([A-Z]*)\\)\\s*((,\\s*(\\d)\\s*\\n)|(\\n))";
		Pattern pattern = Pattern.compile(regexGroupLine);
		Matcher matcher = pattern.matcher(exampleSubject1);
		assertTrue(matcher.find());
		matcher = pattern.matcher(exampleSubject2);
		assertTrue(matcher.find());
		matcher = pattern.matcher(exampleSubject3);
		assertTrue(matcher.find());
	}

	@Test
	public void testReadSubjectNameAndType() {
		String regexGroupLine = "^([A-Za-z\\.\\-\\+#]+)\\(([A-Z]*)\\)\\s*((,\\s*(\\d)\\s*\\n)|(\\n))";
		Pattern pattern = Pattern.compile(regexGroupLine);
		Matcher matcher = pattern.matcher(exampleSubject1);
		assertTrue(matcher.find());
		String subjectName = matcher.group(1);
		String subjectType = matcher.group(2);
		assertEquals(subjectName, "Algebra");
		assertEquals(subjectType, "PRACTICE");

		matcher = pattern.matcher(exampleSubject2);
		assertTrue(matcher.find());
		subjectName = matcher.group(1);
		subjectType = matcher.group(2);
		assertEquals(subjectName, "C++");
		assertEquals(subjectType, "LECTURE");

		matcher = pattern.matcher(exampleSubject3);
		assertTrue(matcher.find());
		subjectName = matcher.group(1);
		subjectType = matcher.group(2);
		assertEquals(subjectName, ".Net-C++");
		assertEquals(subjectType, "LAB");
	}

	@Test
	public void testReadSubjectCourse() {
		String regexGroupLine = "^([A-Za-z\\.\\-\\+#]+)\\(([A-Z]*)\\)\\s*((,\\s*(\\d)\\s*\\n)|(\\n))";
		Pattern pattern = Pattern.compile(regexGroupLine);
		Matcher matcher = pattern.matcher(exampleSubject1);
		assertTrue(matcher.find());
		String subjectCourse = matcher.group(5);
		assertEquals(subjectCourse, "2");

		matcher = pattern.matcher(exampleSubject2);
		assertTrue(matcher.find());
		subjectCourse = matcher.group(5);
		assertEquals(subjectCourse, "1");

		matcher = pattern.matcher(exampleSubject3);
		assertTrue(matcher.find());
		subjectCourse = matcher.group(5);
		assertEquals(subjectCourse, null);
	}
}
