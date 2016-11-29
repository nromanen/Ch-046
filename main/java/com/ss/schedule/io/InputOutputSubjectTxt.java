package com.ss.schedule.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;

public class InputOutputSubjectTxt<T> implements InputOutput<T> {

	@SuppressWarnings("unchecked")
	@Override
	public void writeToFile(String path, T t) {
		File file = new File(path);
		OutputStream stream;
		try {
			stream = new FileOutputStream(file);
			for (Subject subj : (ArrayList<Subject>) t) {
				try {
					stream.write(subj.toString().getBytes());
					stream.write(System.getProperty("line.separator").getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T readFromFile(String path) {
		ArrayList<Subject> newList = new ArrayList<>();
		Scanner scn = null;
		try {
			scn = new Scanner(new FileInputStream(path));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		String p1="[A-Za-z0-9]{2,15}\\{[A-Za-z0-9]{2,15}\\=\\'([A-Za-z0-9]{2,15})\\'\\,\\s[A-Za-z0-9]{2,15}\\=([0-9]{1,4})\\,\\s[A-Za-z0-9]{2,15}\\=([A-Z]{2,10})\\}";
		String p2="([^\\(.]*)\\(([A-Z]*)\\)\\,([0-9])";
		Pattern pattern = Pattern.compile(p1);
		while (scn.hasNext()) {
			String subject = scn.nextLine();
			Matcher matcher = pattern.matcher(subject);
			matcher.matches();
			String name = matcher.group(1);
			String type = matcher.group(3);
			int course = Integer.parseInt(matcher.group(2));
			Subject ss = new Subject();
			ss.setName(name);
			ss.setType(SubjectType.valueOf(type));
			ss.setCourse(course);
			newList.add(ss);
		}
		scn.close();
		return (T) newList;
	}

}
