package util;

import java.io.IOException;
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
	public static void main(String[] args) throws IOException {
		TimeTableManager manager = new TimeTableManager();
		List <Pair> restriction = new ArrayList<>();
		restriction.add(Pair.FIRST);
		restriction.add(Pair.SECOND);
		restriction.add(Pair.THIRD);
		restriction.add(Pair.FORTH);
		restriction.add(Pair.FIFTH);
		restriction.add(Pair.SIXTH);
		restriction.add(Pair.SEVENTH);
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
		subject1.setType(Type.PRACTICE);
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
		subject4.setType(Type.LECTURE);
		subject4.setCourseN(1);

		Subject subject5 = new Subject();
		subject5.setName("English");
		subject5.setType(Type.PRACTICE);
		subject5.setCourseN(1);

		// set tables
		TimeTable table1 = new TimeTableBuilder(restriction)
				.buildSubject(subject1)
				.buildPair(Pair.FIRST)
				.buildTeacher(teacher1)
				.buildGroup(group1)
				.buildDayOfWeek(DayOfWeek.MONDAY)
				.build();

		TimeTable table2 = new TimeTableBuilder(restriction)
				.buildSubject(subject2)
				.buildPair(Pair.FIFTH)
				.buildTeacher(teacher2)
				.buildGroup(group1)			
				.buildDayOfWeek(DayOfWeek.MONDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD)
				.build();

		TimeTable table3 = new TimeTableBuilder(restriction)
				.buildSubject(subject3)
				.buildPair(Pair.THIRD)
				.buildTeacher(teacher3)
				.buildGroup(group1)
				.buildDayOfWeek(DayOfWeek.MONDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD)
				.build();

		TimeTable table4 = new TimeTableBuilder(restriction)
				.buildSubject(subject4)
				.buildPair(Pair.SECOND)
				.buildTeacher(teacher4)
				.buildGroup(group1)
				.buildDayOfWeek(DayOfWeek.TUESDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD)
				.build();

		TimeTable table5 = new TimeTableBuilder(restriction)
				.buildSubject(subject5)
				.buildPair(Pair.FORTH)
				.buildTeacher(teacher5)
				.buildGroup(group1)
				.buildDayOfWeek(DayOfWeek.TUESDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD)
				.build();

		TimeTable table6 = new TimeTableBuilder(restriction)
				.buildSubject(subject1)
				.buildPair(Pair.SEVENTH)
				.buildTeacher(teacher1)
				.buildGroup(group1)
				.buildDayOfWeek(DayOfWeek.TUESDAY)
				.buildOddnessOfWeek(OddnessOfWeek.ODD)
				.build();
		
		//add to List<TimeTable>
		list.add(table1);
		list.add(table2);
		list.add(table3);
		list.add(table4);
		list.add(table5);
		list.add(table6);
		
		
		//Collections.sort(list, TimeTableManager.TimeTableComparator);
		// set List
		manager.setTimeTable(list);
		System.out.println("Set list: "+manager.getTimeTable());
		
		//method getFreePairs
		List <Pair> freePairs = new ArrayList<>();
		freePairs=manager.getFreePairs(DayOfWeek.MONDAY);
		System.out.println("Free pairs of day "+DayOfWeek.MONDAY+" are: "+freePairs);
		
		//get Lessons by day and odnessOfweek
		List <TimeTable> lessons = new ArrayList<>();
		lessons=manager.getLessonsByDayAndWeek(OddnessOfWeek.ODD, DayOfWeek.MONDAY);
		System.out.println("Lessons of day "+DayOfWeek.MONDAY+" are: "+lessons);
		
		
		System.out.println("Checking JsonConverter methods: ");
		JsonConverter json = new JsonConverter();
		json.writeToFile(list, "jsonFile.json");
		List <TimeTable> jsonList = new ArrayList<>();
		jsonList=json.readFromFile("jsonFile.json");
		System.out.println(jsonList);
		
		System.out.println("Cheking JsonConverterWithTimeTableManager: ");
		JsonConverterWithTimeTableManager jsonttm = new JsonConverterWithTimeTableManager();
		jsonttm.writeToFile(list, "jsonttmFile.json");
		List <TimeTable> jsonttmList = new ArrayList<>();
		jsonttmList=jsonttm.readFromFile("jsonttmFile.json");
		System.out.println(jsonttmList);
		
		System.out.println("Cheking XmlConverter methods: ");
		XmlConverter xml = new XmlConverter();
		xml.writeToFile(list, "xmlFile.xml");
		List <TimeTable> xmlList = new ArrayList<>();
		xmlList=xml.readFromFile("xmlFile.xml");
		System.out.println(xmlList);
		
		System.out.println("Cheking TxtConverter methods: ");
		TxtConverter txt = new TxtConverter();
		txt.writeToFile(list, "linefile.txt");
		List <TimeTable> txtList = new ArrayList<>();
		txtList=txt.readFromFile("linefile.txt");
		System.out.println(txtList);
	}

}
