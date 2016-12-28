package com.ss.schedule.formatter;

import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vyach on 27.12.2016.
 */
public class SubjectFormatter implements Formatter<Subject> {

	private final String regex =
			"^Subject:\\s\\{\\sid:\\s(\\d+),\\sname:\\s(\\w+),\\stype:\\s(\\w+),\\scourse:\\s(\\d)\\s\\}";

	@Override
	public Subject parse(String text, Locale locale) throws ParseException {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);

		if (matcher.find()) {
			Subject subject = new Subject();
			subject.setId(Long.valueOf(matcher.group(1)));
			subject.setName(matcher.group(2));
			subject.setType(SubjectType.valueOf(matcher.group(3)));
			subject.setCourse(Integer.valueOf(matcher.group(4)));
			return subject;
		}

		return null;
	}

	@Override
	public String print(Subject subject, Locale locale) {
		return subject.toString();
	}
}
