package com.ss.schedule.web.group;

import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import com.ss.schedule.service.GroupService;
import com.ss.schedule.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vyach on 06.12.2016.
 */

@Controller
@RequestMapping(value = {"/", "/groups"})
public class GroupServlet {

	private static final Logger logger = LoggerFactory.getLogger(GroupServlet.class);

	@Autowired
	private GroupService groupService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private Validator groupFormValidator;

	@InitBinder({"groupForm"})
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(groupFormValidator);
	}

	@RequestMapping(value = {"/", "/groups"}, method = RequestMethod.GET)
	public ModelAndView showAllGroups() {
		ModelAndView model = new ModelAndView();
		model.addObject("groups", groupService.getAllGroups());
		model.setViewName("group/groups");
		return model;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String showAddGroupForm(Model model) {
		Group group = new Group();
		model.addAttribute("groupForm", group);
		model.addAttribute("action", "Add");
		return "group/group_form";
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateGroupForm(@PathVariable("id") long groupId, Model model, HttpServletRequest request) {
		Group group = groupService.getGroupById(groupId);

		String primaryGroupName = getPrimaryGroupName(request, group.getName());
		model.addAttribute("groupName", primaryGroupName);

		model.addAttribute("groupForm", group);
		populateModel(model, group, primaryGroupName);
		return "group/group_form";
	}

	@RequestMapping(value = "/action", method = RequestMethod.POST)
	public String addOrUpdateGroup(@ModelAttribute("groupForm") @Validated Group group, BindingResult result,
								   Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String primaryGroupName = getPrimaryGroupName(request, group.getName());
		model.addAttribute("groupName", primaryGroupName);

		if (result.hasErrors()) {
			populateModel(model, group, primaryGroupName);
			return "group/group_form";
		}

		if (group.getId() == 0) {
			groupService.addGroup(group);
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Group " + group.getName() + " has added successfully!");
			logger.info("SERVLET: Group {} has added successfully", group.getName());
		} else {
			groupService.updateGroup(group);
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Group " + group.getName() + " has updated successfully!");
			logger.info("SERVLET: Group {} has updated successfully", group.getName());
		}

		return "redirect:/groups";
	}

	private String getPrimaryGroupName(HttpServletRequest request, String groupName) {
		String mainGroupName = request.getParameter("main_name");
		return mainGroupName != null ? mainGroupName : groupName;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteGroup(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Group group = groupService.getGroupById(Long.valueOf(request.getParameter("group_id")));
		groupService.deleteGroup(group);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Group " + group.getName() + " has deleted successfully!");
		logger.info("SERVLET: Group {} has deleted successfully", group);
		return "redirect:/groups";
	}

	@RequestMapping(value = "/unused-subjects", method = RequestMethod.GET)
	public String showUnusedSubjectsList(Model model) {
		model.addAttribute("subjects", subjectService.getUnusedSubjects());
		return "group/unused_subjects";
	}

	@RequestMapping(value = "/unused-subjects/{id}/add", method = RequestMethod.GET)
	public String showUnusedSubjectForm(@PathVariable("id") long subjectId, Model model) {
		Subject subject = subjectService.getSubjectById(subjectId);
		List<Group> groups = groupService.getGroupsByCourse(subject.getCourse());
		model.addAttribute("groups", groups);
		model.addAttribute("subject", subject);
		return "group/unused_subject_form";
	}

	@RequestMapping(value = "/unused-subjects/{id}/action", method = RequestMethod.POST)
	public String assignSubject(@PathVariable("id") long subjectId, Model model,
								RedirectAttributes redirectAttributes, HttpServletRequest request) {
		List<Group> groups = getGroupsList(request);
		if (groups != null && groups.size() > 0) {
			Subject subject = subjectService.getSubjectById(subjectId);
			addSubjectIntoGroups(groups, subject);
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", createMessage(groups, subject));
			logger.info("SERVLET: {} has added to {} successfully", subject, groups);
			return "redirect:/unused-subjects";
		} else {
			model.addAttribute("msg", "No one group was selected! Please select at least one group.");
			return showUnusedSubjectForm(subjectId, model);
		}
	}

	private List<Group> getGroupsList(HttpServletRequest request) {
		String[] groupsIds = request.getParameterValues("group_id");
		if (groupsIds != null && groupsIds.length > 0) {
			List<Group> groups = new ArrayList<>();
			for (String groupId : groupsIds) {
				groups.add(groupService.getGroupById(Long.valueOf(groupId)));
			}
			return groups;
		}
		return null;
	}

	private void addSubjectIntoGroups(List<Group> groups, Subject subject) {
		for (Group group : groups) {
			group.addSubject(subject);
			groupService.updateGroup(group);
		}
	}

	private String createMessage(List<Group> groups, Subject subject) {
		StringBuilder sb = new StringBuilder();
		sb.append("Subject ").append(subject.getName()).append("(").append(subject.getType()).append(") ")
				.append(subject.getCourse()).append(" course had been added into:");

		for (Group group : groups) {
			sb.append("<br>Group ").append(group.getName()); // <br> - new line for html
		}

		return sb.toString();
	}

	private void populateModel(Model model, Group group, String groupName) {
		model.addAttribute("action", "Add");
		if (group.getId() != 0) {
			int course = Integer.valueOf(groupName.substring(0, 1));
			model.addAttribute("subjects", subjectService.getSubjectsByCourse(course));
			model.addAttribute("action", "Update");
		}
	}
}
