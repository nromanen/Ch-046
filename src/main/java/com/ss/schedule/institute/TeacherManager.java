package com.ss.schedule.institute;

import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import com.ss.schedule.model.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.List;

// author Maksym Maksymenko last commit 23.11 18:02

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
		if(!teacher.getSubjects().contains(lesson)){
		teacher.getSubjects().add(lesson);}
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

	public List<Subject> getSubjectsByType( SubjectType type) {
		List<Subject> listByLessonType = new ArrayList<>();
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

	public List<Subject> getOnlyLectures(Teacher teacher) {
		List<Subject> result = new ArrayList<>();

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
}
