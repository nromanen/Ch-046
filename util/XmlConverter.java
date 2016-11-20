package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.TimeTable;

public class XmlConverter implements InputOutputManager<TimeTable> {

	@Override
	public void writeToFile(List<TimeTable> list, String filePath) {
		TimeTableManager manager = new TimeTableManager();
		manager.setTimeTable(list);
		try {
			JAXBContext context = JAXBContext.newInstance(TimeTableManager.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(manager, new File(filePath));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<TimeTable> readFromFile(String filePath) {
		TimeTableManager manager = new TimeTableManager();
		List<TimeTable> list = new ArrayList<>();
		try {
			JAXBContext context = JAXBContext.newInstance(TimeTableManager.class);
			Unmarshaller un = context.createUnmarshaller();
			manager = (TimeTableManager) un.unmarshal(new File(filePath));
			list=manager.getTimeTable();
			return list;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

}
