package com.ss.schedule.formatter;

import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vyach on 25.12.2016.
 */

public class GroupFormatter implements Formatter<Group> {

	private final String groupRegex = "^Group:\\s\\{\\sid:\\s(\\d+),\\sname:\\s(\\d{2}),\\scount:\\s(\\d{1,3})";
	private final String subjectRegex =
			"\\{Subject:\\s\\{\\sid:\\s(\\d+),\\sname:\\s(\\w+),\\stype:\\s(\\w+),\\scourse:\\s(\\d)\\s\\}\\}";

	@Override
	public Group parse(String text, Locale locale) throws ParseException {
		Pattern pattern = Pattern.compile(groupRegex);
		Matcher matcher = pattern.matcher(text);

		if (matcher.find()) {
			Group group = new Group();
			group.setId(Long.valueOf(matcher.group(1)));
			group.setName(matcher.group(2));
			group.setCount(Integer.valueOf(matcher.group(3)));
			group.setSubjects(parseSubjects(text));
			return group;
		}

		return null;
	}

	private List<Subject> parseSubjects(String text) {
		Pattern pattern = Pattern.compile(subjectRegex);
		Matcher matcher = pattern.matcher(text);

		List<Subject> subjects = new ArrayList<>();
		while (matcher.find()) {
			Subject subject = new Subject();
			subject.setId(Long.valueOf(matcher.group(1)));
			subject.setName(matcher.group(2));
			subject.setType(SubjectType.valueOf(matcher.group(3)));
			subject.setCourse(Integer.valueOf(matcher.group(4)));
			subjects.add(subject);
		}

		return subjects;
	}

	@Override
	public String print(Group group, Locale locale) {
		return group.toString();
	}
}
