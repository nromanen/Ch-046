package com.ss.schedule.validator;

import com.ss.schedule.model.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vyach on 15.12.2016.
 */
public class GroupFormValidator {

	private static final Logger logger = LoggerFactory.getLogger(GroupFormValidator.class);

	private static final int MIN_GROUP_NAME_NUMBER = 10;
	private static final int MAX_GROUP_NAME_NUMBER = 59;
	private static final int MAX_GROUP_COUNT = 100;

	public static boolean hasErrors(HttpSession session, Group group) {
		boolean isGroupFormDataHasErrors = false;

		if (!isGroupNameValid(session, group)) {
			isGroupFormDataHasErrors = true;
			logger.warn("VALIDATOR: Group name - \"{}\" - doesn't valid!", group.getName());
		}

		if (!isGroupCountValid(session, group)) {
			isGroupFormDataHasErrors = true;
			logger.warn("VALIDATOR: Group count - {} - doesn't valid!", group.getCount());
		}

		return isGroupFormDataHasErrors;
	}

	private static boolean isGroupNameValid(HttpSession session, Group group) {
		String groupNamePattern = "^\\s*\\d{2}\\s*$";
		Pattern pattern = Pattern.compile(groupNamePattern);
		Matcher matcher = pattern.matcher(group.getName());
		String nameErrorMessage = "Wrong group name! Group name consists of 2 digits " +
				"from " + MIN_GROUP_NAME_NUMBER + " to " + MAX_GROUP_NAME_NUMBER + " inclusive";
		String previousGroupName =(String) session.getAttribute("groupName");

		if (matcher.find()) {
			int groupNumber = Integer.valueOf(group.getName());
			if (groupNumber < MIN_GROUP_NAME_NUMBER || groupNumber > MAX_GROUP_NAME_NUMBER) {
				session.setAttribute("gr_name_error", nameErrorMessage);
				return false;
			} else if (group.getId() > 0 && isGroupCourseChanged(group,  previousGroupName) &&
					isSubjectsFromAnotherGroup(group, previousGroupName)) {
				session.setAttribute("msg", "Updating is impossible! The course " +
						"(first character in name) must be similar to previous group course or unchecked all subjects");
				return false;
			}
		} else {
			session.setAttribute("gr_name_error", nameErrorMessage);
			return false;
		}

		return true;
	}

	private static boolean isGroupCourseChanged(Group group, String groupName) {
		int previousGroupCourse = groupName.charAt(0) - '0';
		int newGroupCourse = group.getName().charAt(0) - '0';
		return previousGroupCourse != newGroupCourse;
	}

	private static boolean isSubjectsFromAnotherGroup(Group group,String groupName) {
		int previousGroupCourse = groupName.charAt(0) - '0';
		if (group.getSubjects().size() > 0) {
			return group.getSubjects().get(0).getCourse() != previousGroupCourse;
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
