package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.TimeTable;

public class JsonConverter implements InputOutputManager<TimeTable> {

	@Override
	public void writeToFile(List<TimeTable> list, String filePath) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TimeTable> readFromFile(String filePath) {
		List<TimeTable> list = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		//TimeTableManager manager = null;
		try {
			list = (List<TimeTable>) mapper.readValue(new File(filePath), new TypeReference<List<TimeTable>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
