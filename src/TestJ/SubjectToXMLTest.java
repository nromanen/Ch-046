package TestJ;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ss.schedule.institute.Subject;
import com.ss.schedule.institute.SubjectToJson;
import com.ss.schedule.institute.SubjectType;
import com.ss.schedule.institute.Teacher;
import com.ss.schedule.institute.TeacherManager;



public class SubjectToXMLTest {
  @Test
  public void readWriteTest() {
	  SubjectToJson json = new SubjectToJson();
	  Teacher teacher1 = new Teacher();
	  Subject algebra = new Subject("Algebra", SubjectType.PRACTICE,0);
	  TeacherManager manage = new TeacherManager(teacher1);
	  manage.addSubject(algebra);
	  System.out.println("writen?-"+json.write(teacher1.getList(), "d:/subject.xml"));
	  Assert.assertTrue(json.read("d:/subject.xml").equals(teacher1.getList()));
  }
}
