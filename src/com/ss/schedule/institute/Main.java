package com.ss.schedule.institute;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.schedule.dao.DBConnector;
import com.ss.schedule.dao.SubjectsDao;
import com.ss.schedule.dao.TeachersDao;
import com.ss.schedule.dao.TeachersSubjectsDao;

@XmlRootElement(name = "Subject")
@XmlType(propOrder = { "name", "type" })
public class Main {

	public static void main(String[] args) throws IOException, SQLException {

		SubjectsDao sd = new SubjectsDao();
		List<Subject> subjectList = sd.getAll();
		TeachersDao td = new TeachersDao();
		List<Teacher> teacherList = td.getAll();
		TeacherManager tm = new TeacherManager(teacherList.get(0));
		/*tm.addSubject(subjectList.get(0));
		tm.addSubject(subjectList.get(1));
		tm.addSubject(subjectList.get(2));*/
		TeachersSubjectsDao tsd= new TeachersSubjectsDao();
		System.out.println(teacherList.get(0).getId());
		System.out.println(tsd.getSubjects(teacherList.get(0)));
				}
}
