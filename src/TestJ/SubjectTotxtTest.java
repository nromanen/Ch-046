package TestJ;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ss.schedule.institute.Subject;
import com.ss.schedule.institute.SubjectTotxt;
import com.ss.schedule.institute.SubjectType;
import com.ss.schedule.institute.Teacher;
import com.ss.schedule.institute.TeacherManager;



public class SubjectTotxtTest {
	@BeforeMethod
	public void createFile() throws IOException
	{
		File ff = new File("d:/subject.txt");
		ff.createNewFile();
		System.out.println(ff.exists());
	}
  @Test
  public void readTest() {
	  SubjectTotxt txt = new SubjectTotxt();
	  Teacher teacher1 = new Teacher();
	  Subject algebra = new Subject("Algebra", SubjectType.PRACTICE,0);
	  TeacherManager manage = new TeacherManager(teacher1);
	  manage.addSubject(algebra);
	  txt.write(teacher1.getList(),"d:/subject.txt");
	  
	 
	  List<Subject> list = txt.read("d:/subject.txt");
	  AssertJUnit.assertTrue(txt.read("d:/subject.txt").containsAll(teacher1.getList())); 
  }
 @AfterMethod
  public void afterMethod() throws Exception
  {
  File file = new File("d:/subject.txt");
  System.out.println("deleted?"+file.delete());
  System.out.println("finish");
  }
}
