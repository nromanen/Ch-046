package com.ss.schedule.institute;

import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.DayOfWeek;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.OddnessOfWeek;
import com.ss.schedule.model.Pair;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.TimeTable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
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

	public List<TimeTable> sortTimeTables(OddnessOfWeek oddnessOfWeek, DayOfWeek dayOfWeek) {
		List<TimeTable> timeTablesByDayAndWeek = new ArrayList<>();
		timeTablesByDayAndWeek = getLessonsByDayAndWeek(oddnessOfWeek, dayOfWeek);
		Pair[] pairs = Pair.values();
		List<Pair> listOfPairs = new ArrayList<Pair>(Arrays.asList(pairs));
		List<TimeTable> sortedTimeTables = new ArrayList<>();
		int i;
		int j;
		for (i = 0; i < listOfPairs.size(); i++) {
			for (j = 0; j < timeTablesByDayAndWeek.size(); j++) {
				if (listOfPairs.get(i) == timeTablesByDayAndWeek.get(j).getPair()) {
					sortedTimeTables.add(timeTablesByDayAndWeek.get(j));
				}
			}
		}
		return sortedTimeTables;
	}

	public List<TimeTable> getWindowsInDay(OddnessOfWeek oddnessOfWeek, DayOfWeek dayOfWeek) {
		List<TimeTable> windows = new ArrayList<>();
		List<TimeTable> sorted_timeTables = sortTimeTables(oddnessOfWeek, dayOfWeek);
		for (int i = 0; i < sorted_timeTables.size() - 1; i++) {
			int substr_result = sorted_timeTables.get(i + 1).getPair().ordinal()
					- sorted_timeTables.get(i).getPair().ordinal();
			if (substr_result > 1) {
				for (int j = sorted_timeTables.get(i).getPair().ordinal() + 1; j < sorted_timeTables.get(i + 1)
						.getPair().ordinal(); j++) {
					TimeTable t = new TimeTable();
					t.setPair(Pair.values()[j]);
					t.setDay(dayOfWeek);
					t.setOddnessOfWeek(oddnessOfWeek);
					windows.add(t);
				}
			}
		}
		return windows;
	}

	public Subject[] subjectsSetToArray(LinkedHashMap<Subject, List<Group>> groupsSubgroupsStreams) {
		Subject[] subjectsKeySet = new Subject[groupsSubgroupsStreams.keySet().size()];
		groupsSubgroupsStreams.keySet().toArray(subjectsKeySet);
		return subjectsKeySet;
	}

	public void showAvailableSubjectsInGroup(LinkedHashMap<Subject, List<Group>> groupsSubgroupsStreams) {
		System.out.println("Available subjects in groups:");
		Subject[] subjectsKeySet = subjectsSetToArray(groupsSubgroupsStreams);
		for (Subject subject : subjectsKeySet) {
			System.out.println(subject.getName() + " " + subject.getCourse());
			for (Group group : groupsSubgroupsStreams.get(subject)) {
				System.out.println(group.getName());
			}
		}
	}

	public void removeSubjectFromTimetable(Subject[] subjectsKeySet, TimeTable timeTable,
			LinkedHashMap<Subject, List<Group>> groupsSubgroupsStreams) {
		Subject subjectToPutBack = findSubjectInKeySet(subjectsKeySet, timeTable);
		groupsSubgroupsStreams.get(subjectToPutBack).add(timeTable.getGroup());
	}

	public Subject findSubjectInKeySet(Subject[] subjectsKeySet, TimeTable timeTable) {
		for (Subject subject : subjectsKeySet) {
			if (subject.equals(timeTable.getSubject()))
				return subject;
		}
		return null;
	}
	
	
	public List<Classroom> getAvailableClassroomsForTimetable(ClassroomManager classroomManager,TimeTable timeTable){
        List<Classroom> listOfAvailableRooms = classroomManager.getListOfAvailableRooms(timeTable.getSubject(), timeTable.getGroup());
        return listOfAvailableRooms;
    }

    public void getGroupsBySubject(Subject subj, LinkedHashMap<Subject, List<Group>> groupsSubgroupsStreams){
        for (Group gr : groupsSubgroupsStreams.get(subj)) {
            System.out.println(gr.getName()+" "+gr.getCount());
        }
    }
    
    
}
