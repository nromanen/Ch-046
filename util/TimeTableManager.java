package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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

	public static Comparator<TimeTable> TimeTableComparator = new Comparator<TimeTable>() {

		public int compare(TimeTable n1, TimeTable n2) {
			Pair pair1 = n1.getPair();
			Pair pair2 = n2.getPair();
			return pair1.compareTo(pair2);

		}
	};

}
