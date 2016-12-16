package com.ss.schedule.validator;

import com.ss.schedule.model.Group;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vyach on 15.12.2016.
 */
public class GroupFormValidator {

	private final static int MIN_GROUP_NAME_NUMBER = 10;
	private final static int MAX_GROUP_NAME_NUMBER = 59;
	private final static int MAX_GROUP_COUNT = 100;

	public static boolean hasErrors(HttpSession session, Group group) {
		boolean isGroupFormDataHasErrors = false;

		if (!isGroupNameValid(session, group)) {
			isGroupFormDataHasErrors = true;
		}

		if (!isGroupCountValid(session, group)) {
			isGroupFormDataHasErrors = true;
		}

		return isGroupFormDataHasErrors;
	}

	private static boolean isGroupNameValid(HttpSession session, Group group) {
		String groupNamePattern = "^\\s*\\d{2}\\s*$";
		Pattern pattern = Pattern.compile(groupNamePattern);
		Matcher matcher = pattern.matcher(group.getName());
		String nameErrorMessage = "Wrong group name! Group name consists of 2 digits " +
				"from " + MIN_GROUP_NAME_NUMBER + " to " + MAX_GROUP_NAME_NUMBER + " inclusive";

		if (matcher.find()) {
			int groupNumber = Integer.valueOf(group.getName());
			if (groupNumber < MIN_GROUP_NAME_NUMBER || groupNumber > MAX_GROUP_NAME_NUMBER) {
				session.setAttribute("gr_name_error", nameErrorMessage);
				return false;
			} else if (group.getId() > 0 && isGroupCourseChanged(group)) {
				session.setAttribute("gr_course_error", "Updating is impossible! The course " +
						"(first character in name) must be similar to previous group course or unchecked all subjects");
				return false;
			}
		} else {
			session.setAttribute("gr_name_error", nameErrorMessage);
			return false;
		}

		return true;
	}

	private static boolean isGroupCourseChanged(Group group) {
		if (group.getSubjects().size() > 0) {
			int previousGroupCourse = group.getSubjects().get(0).getCourse();
			if ((group.getName().charAt(0) - '0') != previousGroupCourse) {
				return true;
			}
		}
		return false;
	}

	private static boolean isGroupCountValid(HttpSession session, Group group) {
		if (group.getCount() < 1 || group.getCount() > MAX_GROUP_COUNT) {
			session.setAttribute("msg", "Wrong group count! Group count cannot be fewer 1 and " +
					"greater " + MAX_GROUP_COUNT);
			return false;
		}

		return true;
	}
}
