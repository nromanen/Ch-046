package FirstProject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJson implements InputOutput<Subject> {
	
	
	// read objects from file
	@Override
	public List<Subject> read(String filePath) {
		ObjectMapper mapper = new ObjectMapper();
		List list =  new ArrayList<Subject>(); 
		try {
          list = (List) mapper.readValue(new File(filePath), new TypeReference<List<Subject>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        } 

		return list;
	}

	// write objects to file
	Teacher teacher = new Teacher();
	
	List<Subject> list = teacher.getList();

	String filePath = "d:/practice.json";
	

	
	@Override
	public boolean write(List list, String filePath) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			try {
				mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath),list);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}

}
