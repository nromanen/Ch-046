package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.DayOfWeek;
import model.Group;
import model.OddnessOfWeek;
import model.Pair;
import model.Subject;
import model.Teacher;
import model.TimeTable;
import model.TypeOfLesson;
import util.TimeTableBuilder;

/**
 * Created by vnovohatskyy on 22.11.16.
 */
public class InputOutputTimeTableTxt<T> implements InputOutput<T> {
	/* Example: Algebra(LAB), FIRST, [Ann, Parkinson], 101, MONDAY, All */
	public static final String TIMETABLES_PATTERN = "(^[A-Z][a-z]{2,15}+)\\(([A-Z]{2,15})\\)" +
	/* The first group is the subject's title, the second-- it's type; */
			",\\s([A-Z]+)" +
			/* the third -- it's ENUM number */
			",\\s\\[([A-Z][a-z]+),\\s([A-Z][a-z]+)\\]," +
			/* the fourth -- professor's name, the fifth -- professor's surname */
			"\\s(\\d{1,3})," +
			/* the sixth -- group number */
			"\\s([A-Z]+)," +
			/* the seventh -- day of week */
			"\\s([A-Z][a-z]{2}|[A-Z]{4})";
	/* the eighth -- oddness of week */
	
	@SuppressWarnings("unchecked")
	@Override
	public void writeToFile(String path, T t) {

		File file = new File(path);
		// file.createNewFile();

		OutputStream stream;

		try {

			stream = new FileOutputStream(file);

			for (TimeTable subj : (ArrayList<TimeTable>) t) {
				stream.write(subj.toString().getBytes());

				stream.write(System.getProperty("line.separator").getBytes());

			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T readFromFile(String path) {

		Pattern pattern = Pattern.compile(TIMETABLES_PATTERN);
		File file = new File(path);
		String s2;
		List<TimeTable> timeTables = new ArrayList<>();
		List<Pair> restriction = new ArrayList<>();
		restriction.add(Pair.FIRST);
		restriction.add(Pair.SECOND);
		restriction.add(Pair.THIRD);
		restriction.add(Pair.FORTH);
		restriction.add(Pair.FIFTH);
		restriction.add(Pair.SIXTH);
		restriction.add(Pair.SEVENTH);
		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				s2 = scanner.nextLine();
				Matcher matcher1 = pattern.matcher(s2);
				while (matcher1.find()) {
					String subjectName = matcher1.group(1);
					String subjectType = matcher1.group(2);
					String pair = matcher1.group(3);
					String teacherFirstName = matcher1.group(4);
					String teacherLastName = matcher1.group(5);
					String groupName = matcher1.group(6);
					String day = matcher1.group(7);
					String oddnessOfWeek = matcher1.group(8);
					Subject subject = new Subject();
					subject.setName(subjectName);
					subject.setType(TypeOfLesson.valueOf(subjectType));
					Teacher teacher = new Teacher();
					teacher.setFirstName(teacherFirstName);
					teacher.setLastName(teacherLastName);
					Group group = new Group();
					group.setName(groupName);

					TimeTable tt = new TimeTableBuilder(restriction).buildSubject(subject).buildPair(Pair.valueOf(pair))
							.buildTeacher(teacher).buildGroup(group).buildDayOfWeek(DayOfWeek.valueOf(day))
							.buildOddnessOfWeek(OddnessOfWeek.valueOf(oddnessOfWeek)).build();
					timeTables.add(tt);
				}

			}
		} catch (FileNotFoundException e) {
			System.out.println("not found");
		}
		return (T) timeTables;
	}

}
