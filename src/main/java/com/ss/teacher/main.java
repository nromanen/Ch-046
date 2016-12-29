package com.ss.teacher;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.TeacherDao;

public class main {
	public static void main(String[] args) {

		Teacher teacher = new Teacher();
		teacher.setFirstName("Joe");
		teacher.setLastName("Frezereee");

		TeacherDao teacherDao = new TeacherDao();
		teacherDao.add(teacher);
		System.out.println(teacherDao.getById(59));
		/*
		 * SessionFactory sessionFactory = new
		 * Configuration().configure().buildSessionFactory(); Session session =
		 * sessionFactory.openSession();
		 * 
		 * session.beginTransaction(); session.save(teacher);
		 * 
		 * session.getTransaction().commit(); session.close();
		 */

		/*
		 * TeacherDao td = new TeacherDao(); List<Teacher> list =
		 * TeacherDao.getAll(); System.out.println(list.size());
		 * System.out.println(td.isExist("Ivanov", "Ivan"));
		 */

	}
}
