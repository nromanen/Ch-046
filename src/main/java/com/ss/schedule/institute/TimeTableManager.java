package com.ss.schedule.institute;

import com.ss.schedule.model.DayOfWeek;
import com.ss.schedule.model.OddnessOfWeek;
import com.ss.schedule.model.Pair;
import com.ss.schedule.model.TimeTable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	public List<TimeTable> getLessonsByWeek(OddnessOfWeek oddnessOfWeek) {
		List<TimeTable> res = new ArrayList<>();
		for (TimeTable tt : timeTable) {
			if (tt.getOddnessOfWeek().equals(oddnessOfWeek))
				res.add(tt);
		}
		return res;
	}

	public List<TimeTable> getLessonsByDayAndWeek(OddnessOfWeek oddnessOfWeek, DayOfWeek dayOfWeek) {
		List<TimeTable> res = new ArrayList<>();
		for (TimeTable tt : timeTable) {
			if (tt.getOddnessOfWeek().equals(oddnessOfWeek) && tt.getDay().equals(dayOfWeek))
				res.add(tt);
		}
		return res;
	}

	public List<Pair> getFreeDaylyPairs(DayOfWeek dayOfWeek) {
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
}
