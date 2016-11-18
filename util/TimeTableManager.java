package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.DayOfWeek;
import model.OddnessOfWeek;
import model.Pair;
import model.TimeTable;

@SuppressWarnings("serial")
@XmlRootElement(name = "TimeTableManager")
@XmlType(propOrder = { "timeTable" })
public class TimeTableManager implements Serializable {
	private List<TimeTable> timeTable;

	public List<TimeTable> getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(List<TimeTable> timeTable) {
		this.timeTable = timeTable;
	}

	public List<TimeTable> getLessonsByDayAndWeek(OddnessOfWeek oddnessOfWeek, DayOfWeek dayOfWeek) {
		List<TimeTable> res = new ArrayList<>();
		for (TimeTable tt : timeTable) {
			if (tt.getOddnessOfWeek().equals(oddnessOfWeek) && tt.getDay().equals(dayOfWeek))
				res.add(tt);
		}
		return res;
	}

	public List<TimeTable> getLessonByTeacher(String lastName) {
		List<TimeTable> tempList = new ArrayList<>();
		for (TimeTable in : timeTable) {
			if (lastName == in.getTeacher().getLastName()) {
				tempList.add(in);
			}
		}
		return tempList;
	}

	public List<TimeTable> getlessonByGroup(String groupName) {
		List<TimeTable> tempList = new ArrayList<>();
		for (TimeTable in : timeTable) {
			if (groupName == in.getGroup().getName()) {
				tempList.add(in);
			}
		}
		return tempList;
	}

	public List<Pair> getFreePairs(DayOfWeek dayOfWeek) {
		Pair[] pairs = Pair.values();
		List<Pair> tempList = new ArrayList<Pair>(Arrays.asList(pairs));
		for (int i = 0; i < timeTable.size(); i++) {
			if (timeTable.get(i).getDay().equals(dayOfWeek))
				for (int j = 0; j < tempList.size(); j++) {
					if (tempList.get(j) == timeTable.get(i).getPair()) {
						tempList.remove(tempList.get(j));
					}
				}
		}
		return tempList;
	}

	public void writeObjToFileJson(TimeTableManager manager, String filePath) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(filePath), manager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<TimeTable> readObjFromFileJson(String filePath) {
		List<TimeTable> temp = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		TimeTableManager manager = null;
		try {
			manager = mapper.readValue(new File("timeTable.json"), TimeTableManager.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		temp = manager.getTimeTable();
		return temp;
	}

	public void writeObjectToFile(String filePath) {
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(timeTable);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TimeTable> readObjectFromFile(String filePath) {
		List<TimeTable> tempWorkersList = new ArrayList<TimeTable>();
		try {
			FileInputStream fis = new FileInputStream(filePath);
			ObjectInputStream ois = new ObjectInputStream(fis);
			tempWorkersList = (List<TimeTable>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return tempWorkersList;
	}

	public void jaxbObjectToXML(TimeTableManager manager, String filePath) {
		try {
			JAXBContext context = JAXBContext.newInstance(TimeTableManager.class);
			Marshaller m = context.createMarshaller();
			// for pretty-print XML in JAXB
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to System.out for debugging
			// m.marshal(emp, System.out);

			// Write to File
			m.marshal(manager, new File(filePath));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public List<TimeTable> jaxbXMLToObject(String filePATH) {
		try {
			JAXBContext context = JAXBContext.newInstance(TimeTableManager.class);
			Unmarshaller un = context.createUnmarshaller();
			TimeTableManager ttm = (TimeTableManager) un.unmarshal(new File(filePATH));
			List<TimeTable> list = new ArrayList<>();
			list = ttm.getTimeTable();
			return list;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

}
