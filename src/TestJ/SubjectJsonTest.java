package TestJ;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ss.schedule.institute.Subject;
import com.ss.schedule.institute.SubjectToJson;
import com.ss.schedule.institute.SubjectType;
import com.ss.schedule.institute.Teacher;
import com.ss.schedule.institute.TeacherManager;



public class SubjectJsonTest {
	@BeforeMethod
	public void createFile() throws IOException
	{
		File ff = new File("d:/subject.json");
		ff.createNewFile();
		System.out.println("file exists?-"+ff.exists());
	}
  @Test
  public void readTest() {
	  SubjectToJson json = new SubjectToJson();
	  Teacher teacher1 = new Teacher();
	  Subject algebra = new Subject("Algebra", SubjectType.PRACTICE,0);
	  TeacherManager manage = new TeacherManager(teacher1);
	  manage.addSubject(algebra);
	  System.out.println("writen?-"+json.write(teacher1.getList(), "d:/subject.json"));
	  Assert.assertTrue(json.read("d:/subject.json").containsAll(teacher1.getList()));
  }
  @AfterMethod
  public void afterMethod() throws Exception
  {
  File file = new File("d:/subject.json");
  System.out.println("deleted?"+file.delete());
  }
}
