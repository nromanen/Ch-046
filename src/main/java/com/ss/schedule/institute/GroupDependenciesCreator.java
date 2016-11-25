package com.ss.schedule.institute;

import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vyach on 24.11.2016.
 */
public class GroupDependenciesCreator {

	private HashMap<Subject, List<Group>> groupsSubgroupsStreams;
	private HashMap<Group, List<Group>> groupsDependencies;
	private Set<Group> groupSet;

	private Pattern groupPattern;
	private Pattern subgroupPattern;
	private Pattern streamPattern;
	private Matcher groupMatcher;
	private Matcher subgroupMatcher;
	private Matcher streamMatcher;

	public GroupDependenciesCreator(HashMap<Subject, List<Group>> groupsSubgroupsStreams) {
		this.groupsSubgroupsStreams = groupsSubgroupsStreams;
		this.groupsDependencies = new HashMap<>();
	}

	public HashMap<Group, List<Group>> getGroupsDependencies() {
		findAllGroupsSubgroupsStreams();
		
		for (Group group : groupSet) {
			if (group.getName().length() == 2) {
				groupsDependencies.put(group, getGroupDependencies(group.getName()));
			} else if (group.getName().length() == 4) {
				groupsDependencies.put(group, getSubgroupDependencies(group.getName()));
			} else if (group.getName().length() >= 5) {
				groupsDependencies.put(group, getStreamDependencies(group.getName()));
			} else {
				//TODO
				throw new RuntimeException();
			}
		}
		return groupsDependencies;
	}

	private void findAllGroupsSubgroupsStreams() {
		groupSet = new TreeSet<>();
		Collection<List<Group>> groupsFromMap = groupsSubgroupsStreams.values();
		for (List<Group> groupList : groupsFromMap) {
			groupSet.addAll(groupList);
		}
	}

	private List<Group> getGroupDependencies(String groupName) {
		groupPattern = Pattern.compile(groupName);
		subgroupPattern = Pattern.compile(createSubgroupPattern(groupName));
		streamPattern = Pattern.compile(createStreamPattern(groupName));
		List<Group> foundGroups = new ArrayList<>();

		for (Group groupFromSet : groupSet) {
			groupMatcher = groupPattern.matcher(groupFromSet.getName());
			subgroupMatcher = subgroupPattern.matcher(groupFromSet.getName());
			streamMatcher = streamPattern.matcher(groupFromSet.getName());
			
			if (groupMatcher.find() || subgroupMatcher.find() || streamMatcher.find() && 
					!foundGroups.contains(groupFromSet)) {
				foundGroups.add(groupFromSet);
			}
		}

		return foundGroups;
	}

	private String createStreamPattern(String groupName) {
		StringBuilder pattern = new StringBuilder();
		pattern.append("(.*\\d{2}_").append(groupName).append("_\\d{2}.*)|")
				.append("(").append(groupName).append("_\\d{2}.*)|")
				.append("(.*\\d{2}_").append(groupName).append(")");

		return pattern.toString();
	}

	private String createSubgroupPattern(String groupName) {
		StringBuilder pattern = new StringBuilder();
		pattern.append(groupName).append("-\\d");

		return pattern.toString();
	}

	private List<Group> getSubgroupDependencies(String subGroupName) {
		groupPattern = Pattern.compile(subGroupName.substring(0, 2));
		subgroupPattern = Pattern.compile(subGroupName);
		streamPattern = Pattern.compile(createStreamPattern(subGroupName.substring(0, 2)));
		List<Group> foundGroups = new ArrayList<>();

		for (Group groupFromSet : groupSet) {
			groupMatcher = groupPattern.matcher(groupFromSet.getName());
			subgroupMatcher = subgroupPattern.matcher(groupFromSet.getName());
			streamMatcher = streamPattern.matcher(groupFromSet.getName());

			if (groupMatcher.find() || subgroupMatcher.find() || streamMatcher.find() &&
					!foundGroups.contains(groupFromSet)) {
				foundGroups.add(groupFromSet);
			}
		}

		return foundGroups;
	}

	private List<Group> getStreamDependencies(String streamName) {
		List<String> splitStreamName = Arrays.asList(streamName.split("_"));
		List<Group> foundGroups = new ArrayList<>();

		for (String groupName : splitStreamName) {
			for (Group group : getGroupDependencies(groupName)) {
				if (!foundGroups.contains(group)) {
					foundGroups.add(group);
				}
			}
		}

		return foundGroups;
	}


}
