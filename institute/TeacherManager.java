package FirstProject;

import java.util.ArrayList;
import java.util.Comparator;

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

	public ArrayList<Subject> addSubject(Subject lesson) {
		if(!teacher.getList().contains(lesson)){
		teacher.getList().add(lesson);}
		return teacher.getList();
	}

	public void removeSubject(Subject lesson) {

		for (int i = 0; i < teacher.getList().size(); i++) {
			if (teacher.getList().get(i).equals(lesson)) {
				teacher.getList().remove(lesson);
				i--;
			}
		}
	}

	public ArrayList<Subject> getSubjectsByType( SubjectType type) {
		ArrayList<Subject> listByLessonType = new ArrayList<>();
		ArrayList<Subject> list = teacher.getList();
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

	public ArrayList<Subject> getOnlyLectures(Teacher teacher) {
		ArrayList<Subject> result = new ArrayList<>();

		ArrayList<Subject> list = teacher.getList();
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
