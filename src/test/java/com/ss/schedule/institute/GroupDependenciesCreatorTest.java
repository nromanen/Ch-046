package com.ss.schedule.institute;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by vyach on 24.11.2016.
 */
public class GroupDependenciesCreatorTest {

	private final File groupJson = new File("src/test/resources/testfiles/groups.json");
	private final File subjectJson = new File("src/test/resources/testfiles/subjects.json");


	@Test
	public void testGetGroupsDependencies() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<Group> groups = mapper.readValue(groupJson, new TypeReference<List<Group>>() {});
		List<Subject> subjects = mapper.readValue(subjectJson, new TypeReference<List<Subject>>() {});

		Faculty faculty = new Faculty("Programmers", groups, subjects, new ArrayList<>());
		HashMap<Subject, List<Group>> subgroupsStreams = faculty.getGroupsSubgroupsStreams();

		assertEquals(subgroupsStreams.size(), 20);

		GroupDependenciesCreator groupDependenciesCreator = new GroupDependenciesCreator(subgroupsStreams);
		HashMap<Group, List<Group>> groupDependencies = groupDependenciesCreator.getGroupsDependencies();

		System.out.println(groupDependencies);
	}

}