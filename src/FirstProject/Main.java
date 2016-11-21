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
		TeacherManager manage = new TeacherManager(teacher1);
		Subject algebra = new Subject("Algebra", SubjectType.LECTURE);
		
		manage.addSubject(new Subject("Algebra", SubjectType.PRACTICAL));
		manage.addSubject(new Subject("Geometry", SubjectType.LECTURE));
		manage.addSubject(new Subject("Programming", SubjectType.LECTURE,2));
		manage.addSubject(new Subject("Programming", SubjectType.PRACTICAL,2));
System.out.println(manage.getOnlyLectures(teacher1));
		ArrayList<Subject> fullListofLessons = new ArrayList<Subject>();
		fullListofLessons.add(new Subject("Algebra", SubjectType.PRACTICAL));
		fullListofLessons.add(new Subject("Algebra", SubjectType.LAB));
		fullListofLessons.add(new Subject("Algebra", SubjectType.LECTURE));
		fullListofLessons.add(new Subject("Algebra", SubjectType.SEMINAR));
		fullListofLessons.add(new Subject("Geometry", SubjectType.PRACTICAL));
		fullListofLessons.add(new Subject("Geometry", SubjectType.LAB));
		fullListofLessons.add(new Subject("Geometry", SubjectType.LECTURE));
		fullListofLessons.add(new Subject("Geometry", SubjectType.SEMINAR));
		fullListofLessons.add(new Subject("Programming", SubjectType.PRACTICAL));
		fullListofLessons.add(new Subject("Programming", SubjectType.LAB));
		fullListofLessons.add(new Subject("Programming", SubjectType.LECTURE));
		fullListofLessons.add(new Subject("Programming", SubjectType.SEMINAR));
		fullListofLessons.add(new Subject("Mathematics", SubjectType.PRACTICAL));
		fullListofLessons.add(new Subject("Mathematics", SubjectType.LAB));
		fullListofLessons.add(new Subject("Mathematics", SubjectType.LECTURE));
		fullListofLessons.add(new Subject("Mathematics", SubjectType.SEMINAR));
		
		//ObjectToXML toX = new ObjectToXML();
		//System.out.println(toX.write(fullListofLessons, "d:/practice.xml"));
		
		/*ObjectTotxt totxt = new ObjectTotxt();
		List<Subject> subjects = new ArrayList<>();

		subjects = totxt.read("d:/practice.txt");
		for (Subject j : subjects)
			System.out.println(j);
*/
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
