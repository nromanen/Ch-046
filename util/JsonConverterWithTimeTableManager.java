package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.TimeTable;

public class JsonConverterWithTimeTableManager implements InputOutputManager<TimeTable> {

	@Override
	public void writeToFile(List<TimeTable> list, String filePath) {
		TimeTableManager manager = new TimeTableManager();
		manager.setTimeTable(list);
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), manager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<TimeTable> readFromFile(String filePath) {
		List<TimeTable> temp = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		TimeTableManager manager = null;
		try {
			manager = mapper.readValue(new File(filePath), TimeTableManager.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		temp = manager.getTimeTable();
		return temp;
	}

}
