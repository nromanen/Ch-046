package FirstProject;

import java.io.File;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
 public ArrayList<Teacher> getTeachersfromJson(String filePath)
 {
	 Teachers teacherList = new Teachers();
 ObjectMapper mapper = new ObjectMapper();
	/*
	 * try { // mapper.writeValue(new File("test.json"), carFleet);//Plain
	 * JSON mapper.writerWithDefaultPrettyPrinter().writeValue(new
	 * File("result.json"), carFleet);//Prettified JSON } catch (Exception
	 * e) { e.printStackTrace(); } Car value = null;
	 */
	try {
		teacherList = mapper.readValue(new File(filePath), Teachers.class);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return teacherList.getTeachers();
}
}
