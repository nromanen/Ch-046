package com.ss.schedule.validator;

import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.model.SubjectType;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by vyach on 16.12.2016.
 */
public class GroupFormValidatorTest {

	HttpSession session;

	@BeforeMethod
	public void setUp() {
		session = Mockito.mock(HttpSession.class);
	}

	@Test
	public void testWithoutErrors() {
		doNothing().when(session).setAttribute(any(), any());
		Group group = new Group("23", 40, new ArrayList<>());

		assertFalse(GroupFormValidator.hasErrors(session, group));

		verifyZeroInteractions(session);
	}

	@Test
	public void testHasErrorInGroupNameGreaterMaxGroupNumber() {
		doNothing().when(session).setAttribute(any(), any());

		Group group = new Group("60", 40, new ArrayList<>());
		assertTrue(GroupFormValidator.hasErrors(session, group));

		verify(session, times(1)).setAttribute(eq("gr_name_error"), anyString());
		verifyNoMoreInteractions(session);
	}

	@Test
	public void testHasErrorInGroupNameFewerMinGroupNumber() {
		doNothing().when(session).setAttribute(any(), any());

		Group group = new Group("9", 40, new ArrayList<>());
		assertTrue(GroupFormValidator.hasErrors(session, group));

		verify(session, times(1)).setAttribute(eq("gr_name_error"), anyString());
		verifyNoMoreInteractions(session);
	}

	@Test
	public void testHasErrorInGroupCountGreaterMaxGroupCount() {
		doNothing().when(session).setAttribute(any(), any());

		Group group = new Group("10", 101, new ArrayList<>());
		assertTrue(GroupFormValidator.hasErrors(session, group));

		verify(session, times(1)).setAttribute(eq("gr_count_error"), anyString());
		verifyNoMoreInteractions(session);
	}

	@Test
	public void testHasErrorInGroupCountFewerMinGroupCount() {
		doNothing().when(session).setAttribute(any(), any());

		Group group = new Group("10", 0, new ArrayList<>());
		assertTrue(GroupFormValidator.hasErrors(session, group));

		verify(session, times(1)).setAttribute(eq("gr_count_error"), anyString());
		verifyNoMoreInteractions(session);
	}

	@Test
	public void testHasErrorsInGroupNameAndCount() {
		doNothing().when(session).setAttribute(any(), any());

		Group group = new Group("60", 101, new ArrayList<>());
		assertTrue(GroupFormValidator.hasErrors(session, group));

		verify(session, times(1)).setAttribute(eq("gr_name_error"), anyString());
		verify(session, times(1)).setAttribute(eq("gr_count_error"), anyString());
		verifyNoMoreInteractions(session);
	}

	@Test
	public void testHasErrorWhenChangeGroupCourseAndDoNotDeletePreviousSubjects() {
		doNothing().when(session).setAttribute(any(), any());

		List<Subject> subjects = new ArrayList<>();
		subjects.add(new Subject("Algebra", SubjectType.LECTURE, 2));

		Group group = new Group("10", 25, subjects);
		group.setId(3);
		assertTrue(GroupFormValidator.hasErrors(session, group));

		verify(session, times(1)).setAttribute(eq("gr_course_error"), anyString());
		verifyNoMoreInteractions(session);
	}
}