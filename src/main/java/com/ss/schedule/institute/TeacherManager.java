package com.ss.schedule.institute;

import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import com.ss.schedule.model.Teacher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TeacherManager {
	private Teacher teacher;

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public TeacherManager(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Subject> addSubject(Subject lesson) {
		if (!teacher.getSubjects().contains(lesson)) {
			teacher.getSubjects().add(lesson);
		}
		return teacher.getSubjects();
	}

	public void removeSubject(Subject lesson) {

		for (int i = 0; i < teacher.getSubjects().size(); i++) {
			if (teacher.getSubjects().get(i).equals(lesson)) {
				teacher.getSubjects().remove(lesson);
				i--;
			}
		}
	}
	
// returns list with selected type of subjects 
	public ArrayList<Subject> getSubjectsByType(SubjectType type) {
		ArrayList<Subject> listByLessonType = new ArrayList<>();
		List<Subject> list = teacher.getSubjects();
		for (Subject les : list) {
			if (les.getType().equals(type))
				listByLessonType.add(les);
		}
		return listByLessonType;
	}

	public static Comparator<Subject> compareByType = new Comparator<Subject>() {
		@Override
		public int compare(Subject l1, Subject l2) {

			return l1.getType().compareTo(l2.getType());
		}
	};
	
// returns subjects where teacher has only lectures(and  has not PR||Lab||Seminar)
	public ArrayList<Subject> getOnlyLectures(Teacher teacher) {
		ArrayList<Subject> result = new ArrayList<>();

		List<Subject> list = teacher.getSubjects();
		for (Subject lecture : list) {
			if (lecture.getType().equals(SubjectType.LECTURE)) {
				String name = lecture.getName();
				int course = lecture.getCourse();
				boolean onlyLecture = true;
				for (Subject subject : list) {
					if (subject.getName().equals(name) && !subject.getType().equals(SubjectType.LECTURE)
							&& subject.getCourse() == course) {
						onlyLecture = false;
						break;
					}
				}
				if (onlyLecture) {
					result.add(lecture);
				}
			}
		}

		return result;
	}
	
//returns list of subjects except teachers subjects
	public ArrayList<Subject> noTeacherSubjects( ArrayList<Subject> fullList) {  
		List<Subject> teacherList = teacher.getSubjects();
		ArrayList<Subject> noTeacherSubjects = new ArrayList<Subject>();
		for(Subject subject:fullList)
		{
			if(!teacherList.contains(subject))
				noTeacherSubjects.add(subject);
		}
		return noTeacherSubjects;
	}
}
