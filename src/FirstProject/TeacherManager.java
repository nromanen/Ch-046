package FirstProject;

import java.util.ArrayList;
import java.util.Comparator;

public class TeacherManager {
	private Teacher teacher;

	public ArrayList<Subject> addSubject(Subject lesson) {
		teacher.getList().add(lesson);
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

	public ArrayList<Subject> getSubjectsByType(LessonType type) {
		ArrayList<Subject> listByLessonType = new ArrayList<>();
		for (Subject les : teacher.getList()) {
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

	public ArrayList<Subject> getOnlyLectures(ArrayList<Subject> fullListofLessons) {
		ArrayList<Subject> list = teacher.getList();
		ArrayList<Subject> listOnlyLectures = new ArrayList<>();
		for (Subject lesson : fullListofLessons) {
			for (Subject les : list) {
				if (lesson.getName().equals(les.getName())) {
					if (!lesson.getType().equals(les.getType()))
						listOnlyLectures.add(lesson);
				}
			}
		}
		for (int i = 0; i < listOnlyLectures.size(); i++) {
			for (Subject les : list) {
				if (listOnlyLectures.get(i).getName().equals(les.getName())
						&& listOnlyLectures.get(i).getType().equals(les.getType())) {
					listOnlyLectures.remove(i);
					i = 0;
				}
			}
		}
		for (int i = 0; i < listOnlyLectures.size(); i++) {
			int pair = 0;
			for (int j = 0; j < listOnlyLectures.size(); j++) {
				if (listOnlyLectures.get(i).equals(listOnlyLectures.get(j))) {
					pair++;
				}
				if (pair == 2) {
					listOnlyLectures.remove(j);
					pair = 0;
				}
			}
		}
		return listOnlyLectures;
	}
}
