package com.ss.schedule.validator;

import com.ss.schedule.model.Group;
import com.ss.schedule.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vyach on 15.12.2016.
 */

@Component
public class GroupFormValidator implements Validator {

	private static final int MIN_GROUP_NAME_NUMBER = 10;
	private static final int MAX_GROUP_NAME_NUMBER = 59;
	private static final int MAX_GROUP_COUNT = 100;

	private final String groupNamePattern = "^\\s*\\d{2}\\s*$"; // for groups with 2 numbers in name

	@Autowired
	private GroupService groupService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Group.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Group group = (Group) target;

		validateGroupName(group, errors);
		validateGroupCount(group, errors);
	}

	private void validateGroupName(Group group, Errors errors) {
		Pattern pattern = Pattern.compile(groupNamePattern);
		Matcher matcher = pattern.matcher(group.getName());

		if (matcher.find()) {
			int groupNumber = Integer.valueOf(group.getName() != null ? group.getName() : "0");

			if (groupNumber < MIN_GROUP_NAME_NUMBER || groupNumber > MAX_GROUP_NAME_NUMBER) {
				errors.rejectValue("name", "WrongName.groupForm.name");

			} else if (group.getId() > 0 && isGroupCourseChanged(group)) {
				errors.rejectValue("name", "AnotherCourse.groupForm.course");
			} else {
				Group checkGroup =  groupService.getGroupByName(group.getName());
				if (checkGroup != null && checkGroup.getName().equals(group.getName()) &&
						checkGroup.getId() != group.getId()) {
					errors.rejectValue("name", "AlreadyExisted.groupForm.name");
				}
			}
		} else {
			errors.rejectValue("name", "WrongName.groupForm.name");
		}
	}

	private boolean isGroupCourseChanged(Group group) {
		int previousGroupCourse = getPreviousGroupCourse(group);
		int newGroupCourse = group.getName().charAt(0) - '0';
		return previousGroupCourse != newGroupCourse;
	}

	private int getPreviousGroupCourse(Group group) {
		if (group.getSubjects() != null && group.getSubjects().size() > 0) {
			return group.getSubjects().get(0).getCourse();
		} else {
			return Integer.valueOf(group.getName().substring(0, 1));
		}
	}

	private void validateGroupCount(Group group, Errors errors) {
		if (group.getCount() < 1 || group.getCount() > MAX_GROUP_COUNT) {
			errors.rejectValue("count", "WrongCount.groupForm.count");
		}
	}
}
