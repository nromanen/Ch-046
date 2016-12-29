package hibernate;

import org.hibernate.Session;

import com.ss.teacher.Teacher;

public class TeacherDao {

	public void add(Teacher teacher) {
		Session session = Utils.getSession();
		session.beginTransaction();
		session.save(teacher);
		session.getTransaction().commit();
	}

	public Teacher getById(int id) {
		Session session = Utils.getSession();

		return session.get(Teacher.class, id);
	}

	public Teacher delete(int id) {
		Session session = Utils.getSession();
		Teacher teacher = session.get(Teacher.class, id);
		session.delete(teacher);
		return teacher;
	}

	public Teacher update(Teacher teacher) {
		Session session = Utils.getSession();
		session.update(teacher);
		return teacher;

	}
}
