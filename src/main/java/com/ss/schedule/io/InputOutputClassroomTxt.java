package com.ss.schedule.io;

import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.SubjectType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputOutputClassroomTxt implements InputOutput<List<Classroom>> {

    private static final String ERROR_MESSAGE_EMPTY_LIST = "[ERROR] List is empty!";

    private String pattern = "(^[A-Z]?[a-z0-9-_\\.]{1,15} )(\\[[A-Z ,\\]]{1,50})(, )([0-9]{1,3})(, )?(\\\"[A-Za-z \\.,-\\?!]+\\\")?;";
    private Pattern p = Pattern.compile(pattern);
    private Matcher m = null;

    @Override
    public void writeToFile(String fileName, List<Classroom> classrooms) {

        if (classrooms == null || classrooms.size() == 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE_EMPTY_LIST);
        }

        String rooms = new String();
        File file = new File(fileName);

        rooms = getStringRepresentationOfClassrooms(classrooms);

        try(BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            br.write(rooms);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    private String getStringRepresentationOfClassrooms(List<Classroom> classrooms) {

        StringBuilder sb = new StringBuilder();
        for (Classroom classroom: classrooms){
            sb.append(classroom.getName()).append(" ")
                    .append(classroom.getTypes())
                    .append(", ")
                    .append(classroom.getCapacity())
                    .append((classroom.getDescription()==null? "" : ", \"" + classroom.getDescription() + "\""))
                    .append(";")
                    .append("\n");
        }
        return sb.toString();
    }

    @Override
    public List<Classroom> readFromFile(String fileName) {

        List<Classroom> classroomList = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line !=null){
                classroomList.add(getClassroomFromString(line));
                line = br.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return classroomList;
    }

    private Classroom getClassroomFromString(String line) {
        m = p.matcher(line);
        m.matches();

        Classroom classroom = new Classroom();
        classroom.setName(m.group(1).trim());
        classroom.setTypes(getTypes(m.group(2)));
        classroom.setCapacity(Integer.parseInt(m.group(4)));
        classroom.setDescription((m.group(6) != null? m.group(6).substring(1, m.group(6).length()-1) : null));

        return classroom;
    }

    private List<SubjectType> getTypes(String groupText) {

        List<SubjectType> typesList = new ArrayList<>();
        for (String s : groupText.substring(1, groupText.length()-1).split(", ")) {
            typesList.add(SubjectType.valueOf(s));
        }
        return typesList;
    }
}
