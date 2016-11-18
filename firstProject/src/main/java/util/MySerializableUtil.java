package util;

import models.Classroom;
import models.Type;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MySerializableUtil implements MySerializable<Classroom> {

    @Override
    public List<Classroom> read(String fileName) {

        String pattern = "(^[A-Z]?[a-z0-9-_\\.]{1,15} )(\\[[A-Z ,\\]]{1,15})(, )([0-9]{1,3})(, )?(\\\"[A-Za-z \\.,-\\?!]+\\\")?;";
        Pattern p = Pattern.compile(pattern);
        Matcher m = null;

        List<Classroom> classroomList = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line !=null){

                m = p.matcher(line);
                m.matches();

                Classroom classroom = new Classroom();
                classroom.setName(m.group(1).trim());
                classroom.setTypes(getTypes(m.group(2)));
                classroom.setCapacity(Integer.parseInt(m.group(4)));
                classroom.setDescription((m.group(6) != null? m.group(6).substring(1, m.group(6).length()-1) : null));
                classroomList.add(classroom);

                line = br.readLine();
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        return classroomList;
    }

    private List<Type> getTypes(String groupText) {

        List<Type> typesList = new ArrayList<>();
        for (String s : groupText.substring(1, groupText.length()-1).split(", ")) {
            typesList.add(Type.valueOf(s));
        }
        return typesList;
    }

    @Override
    public boolean write(List<Classroom> list, String fileName) {


       StringBuilder room = new StringBuilder();
        File file = new File(fileName);

        for (Classroom classroom: list){
            room.append(classroom.toString());
            room.append("\n");
        }

        try(BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            br.write(room.toString());
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return true;
    }
}
