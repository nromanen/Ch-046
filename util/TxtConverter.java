package util;

import java.io.File;
import java.io.FileInputStream;
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
import model.Type;

public class TxtConverter implements InputOutputManager<TimeTable> {

	@Override
	public void writeToFile(List<TimeTable> list, String filePath) {
		File file = new File(filePath);
		OutputStream stream;
		try {
			stream = new FileOutputStream(file);
			for (TimeTable subj : list) {
				try {
					stream.write(subj.toString().getBytes());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					stream.write(System.getProperty("line.separator").getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TimeTable> readFromFile(String filePath) {
		ArrayList<TimeTable> newList = new ArrayList<>();
		List<Pair> restriction = new ArrayList<>();
		restriction.add(Pair.FIRST);
		restriction.add(Pair.SECOND);
		restriction.add(Pair.THIRD);
		restriction.add(Pair.FORTH);
		restriction.add(Pair.FIFTH);
		restriction.add(Pair.SIXTH);
		restriction.add(Pair.SEVENTH);
		Scanner scn = null;
		try {
			scn = new Scanner(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String regEx = "([A-Za-z0-9]{2,15})(\\()([A-Za-z0-9]{2,15})(\\))(\\,\\s)([A-Za-z0-9]{2,15})(\\,\\s)(\\[)([A-Za-z]{2,15})(\\,\\s|\\s)([A-Za-z]{2,15})(\\])(\\,\\s)([A-Za-z0-9]{2,4})(\\,\\s)([A-Za-z]{2,15})(\\,\\s)([A-Za-z]{2,5})";
		Pattern pattern = Pattern.compile(regEx);
		while (scn.hasNext()) {
			String timeTable = scn.nextLine();
			Matcher matcher = pattern.matcher(timeTable);
			matcher.matches();
			String subjectName = matcher.group(1);
			String subjectType = matcher.group(3);
			String pair = matcher.group(6);
			String teacherFirstName = matcher.group(9);
			String teacherLastName = matcher.group(11);
			String groupName = matcher.group(14);
			String day = matcher.group(16);
			String oddnessOfWeek = matcher.group(18);
			Subject subject = new Subject();
			subject.setName(subjectName);
			subject.setType(Type.valueOf(subjectType));
			Teacher teacher = new Teacher();
			teacher.setFirstName(teacherFirstName);
			teacher.setLastName(teacherLastName);
			Group group = new Group();
			group.setName(groupName);

			TimeTable tt = new TimeTableBuilder(restriction)
					.buildSubject(subject)
					.buildPair(Pair.valueOf(pair))
					.buildTeacher(teacher)
					.buildGroup(group)
					.buildDayOfWeek(DayOfWeek.valueOf(day))
					.buildOddnessOfWeek(OddnessOfWeek.valueOf(oddnessOfWeek))
					.build();
			newList.add(tt);
		}
		scn.close();
		return newList;
	}

}
