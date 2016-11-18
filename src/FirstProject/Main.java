package FirstProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Subject")
@XmlType(propOrder = {"name", "type"})
public class Main {

	public static void main(String[] args) throws IOException {

		Teacher teacher1 = new Teacher();
		TeacherManager manage = new TeacherManager();
		Subject algebra = new Subject("Algebra", LessonType.LECTURE);
		teacher1.setQualification(Qualification.LABORANT);

		ArrayList<Subject> fullListofLessons = new ArrayList<Subject>();
		fullListofLessons.add(new Subject("Algebra", LessonType.PRACTICAL));
		fullListofLessons.add(new Subject("Algebra", LessonType.LAB));
		fullListofLessons.add(new Subject("Algebra", LessonType.LECTURE));
		fullListofLessons.add(new Subject("Algebra", LessonType.SEMINAR));
		fullListofLessons.add(new Subject("Geometry", LessonType.PRACTICAL));
		fullListofLessons.add(new Subject("Geometry", LessonType.LAB));
		fullListofLessons.add(new Subject("Geometry", LessonType.LECTURE));
		fullListofLessons.add(new Subject("Geometry", LessonType.SEMINAR));
		fullListofLessons.add(new Subject("Programming", LessonType.PRACTICAL));
		fullListofLessons.add(new Subject("Programming", LessonType.LAB));
		fullListofLessons.add(new Subject("Programming", LessonType.LECTURE));
		fullListofLessons.add(new Subject("Programming", LessonType.SEMINAR));
		fullListofLessons.add(new Subject("Mathematics", LessonType.PRACTICAL));
		fullListofLessons.add(new Subject("Mathematics", LessonType.LAB));
		fullListofLessons.add(new Subject("Mathematics", LessonType.LECTURE));
		fullListofLessons.add(new Subject("Mathematics", LessonType.SEMINAR));
		
		//ObjectToXML toX = new ObjectToXML();
		//System.out.println(toX.write(fullListofLessons, "d:/practice.xml"));
		
		ObjectTotxt totxt = new ObjectTotxt();
		List<Subject> subjects = new ArrayList<>();

		subjects = totxt.read("d:/practice.txt");
		for (Subject j : subjects)
			System.out.println(j);

		/*
		 * ObjectToJson obj = new ObjectToJson(); for (Object j :
		 * obj.read("d:/practice.json")) System.out.println(j); ObjectTotxt
		 * totxt = new ObjectTotxt();
		 * System.out.println(totxt.write(obj.read("d:/practice.json"),
		 * "d:/practice.txt"));
		 */
		/*
		 * System.out.println(obj.write(fullListofLessons, "d:/practice.json"));
		 */

	}
}
