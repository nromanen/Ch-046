package FirstProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectTotxt implements InputOutput {

	@Override
	public List<Subject> read(String filePath) {
		ArrayList<Subject> newList = new ArrayList<>();
		Scanner scn = null;
		try {
			scn = new Scanner(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		Pattern pattern = Pattern.compile("([^\\(.]*)\\(([A-Z]*)\\)\\,([0-9]);");
		while (scn.hasNext()) {
			String subject = scn.nextLine();
			Matcher matcher = pattern.matcher(subject);
			matcher.matches();
			String name = matcher.group(1);
			String type = matcher.group(2);
			int course = Integer.parseInt(matcher.group(3));
			Subject ss = new Subject();
			ss.setName(name);
			ss.setType(LessonType.valueOf(type));
			ss.setCourse(course);
			newList.add(ss);

		}
		scn.close();
		return newList;
	}

	@Override
	public boolean write(List list, String filePath) {
		File file = new File(filePath);
		OutputStream stream;
		try {
			stream = new FileOutputStream(file);
			for (Object subj : list) {

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
		return true;
	}
}
