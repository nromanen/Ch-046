package util;

import java.util.ArrayList;
import java.util.List;

import model.DayOfWeek;
import model.Group;
import model.OddnessOfWeek;
import model.Pair;
import model.Subject;
import model.Teacher;
import model.TimeTable;
import model.Type;

public class App {
	public static void main(String[] args) {
		TimeTableManager manager = new TimeTableManager();
		Pair[] range = new Pair[] { Pair.FIRST, Pair.SECOND, Pair.THIRD, Pair.FORTH, Pair.FIFTH, Pair.SIXTH,
				Pair.SEVENTH };

		List<TimeTable> list = new ArrayList<>();

		// set teachers
		Teacher teacher1 = new Teacher();
		teacher1.setFirstName("Ann");
		teacher1.setLastName("Parkinson");

		Teacher teacher2 = new Teacher();
		teacher2.setFirstName("Ben");
		teacher2.setLastName("Alington");

		Teacher teacher3 = new Teacher();
		teacher3.setFirstName("Peter");
		teacher3.setLastName("Jason");

		Teacher teacher4 = new Teacher();
		teacher4.setFirstName("Kate");
		teacher4.setLastName("Peterson");

		Teacher teacher5 = new Teacher();
		teacher5.setFirstName("Poll");
		teacher5.setLastName("Anderson");

		// set groups
		Group group1 = new Group();
		group1.setName("101");
		group1.setAmount(21);

		// set subjects
		Subject subject1 = new Subject();
		subject1.setName("Algebra");
		subject1.setType(Type.LESSON);
		subject1.setCourseN(1);

		Subject subject2 = new Subject();
		subject2.setName("Geometria");
		subject2.setType(Type.SEMINAR);
		subject2.setCourseN(1);

		Subject subject3 = new Subject();
		subject3.setName("Physics");
		subject3.setType(Type.LAB);
		subject3.setCourseN(1);

		Subject subject4 = new Subject();
		subject4.setName("Philosophy");
		subject4.setType(Type.LESSON);
		subject4.setCourseN(1);

		Subject subject5 = new Subject();
		subject5.setName("English");
		subject5.setType(Type.PRACTICE);
		subject5.setCourseN(1);

		// set tables
		TimeTable table1 = new TimeTableBuilder(range).buildSubject(subject1).buildTeacher(teacher1).buildGroup(group1)
				.buildPair(Pair.FIRST).buildOddnessOfWeek(OddnessOfWeek.ODD).buildDayOfWeek(DayOfWeek.MONDAY).build();

		TimeTable table2 = new TimeTableBuilder(range).buildSubject(subject2).buildTeacher(teacher2).buildGroup(group1)
				.buildPair(Pair.THIRD).buildOddnessOfWeek(OddnessOfWeek.ODD).buildDayOfWeek(DayOfWeek.MONDAY).build();

		TimeTable table3 = new TimeTableBuilder(range).buildSubject(subject3).buildTeacher(teacher3).buildGroup(group1)
				.buildPair(Pair.FIFTH).buildOddnessOfWeek(OddnessOfWeek.ODD).buildDayOfWeek(DayOfWeek.MONDAY).build();

		TimeTable table4 = new TimeTableBuilder(range).buildSubject(subject4).buildTeacher(teacher4).buildGroup(group1)
				.buildPair(Pair.SECOND).buildOddnessOfWeek(OddnessOfWeek.ODD).buildDayOfWeek(DayOfWeek.TUESDAY).build();

		TimeTable table5 = new TimeTableBuilder(range).buildSubject(subject5).buildTeacher(teacher5).buildGroup(group1)
				.buildPair(Pair.FORTH).buildOddnessOfWeek(OddnessOfWeek.ODD).buildDayOfWeek(DayOfWeek.TUESDAY).build();

		TimeTable table6 = new TimeTableBuilder(range).buildSubject(subject1).buildTeacher(teacher1).buildGroup(group1)
				.buildPair(Pair.SEVENTH).buildOddnessOfWeek(OddnessOfWeek.ODD).buildDayOfWeek(DayOfWeek.TUESDAY)
				.build();

		list.add(table1);
		list.add(table2);
		list.add(table3);
		list.add(table4);
		list.add(table5);
		list.add(table6);

		// set List
		manager.setTimeTable(list);
		System.out.println(manager.getTimeTable());

		// write obj to file json
		manager.writeObjToFileJson(manager, "timeTable.json");
		// read obj from file json
		List<TimeTable> list1 = new ArrayList<>();
		list1 = manager.readObjFromFileJson("timeTable.json");
		System.out.println(list1);

		// write obj to file txt
		manager.writeObjectToFile("file.txt");
		// read obj from file txt
		List<TimeTable> list2 = new ArrayList<>();
		list2 = manager.readObjectFromFile("file.txt");
		System.out.println(list2);

		// write obj to filexml
		manager.jaxbObjectToXML(manager, "filexml");
		// read obj from filexml
		List<TimeTable> list3 = new ArrayList<>();
		list3 = manager.jaxbXMLToObject("filexml");
		System.out.println(list3);

	}

}
