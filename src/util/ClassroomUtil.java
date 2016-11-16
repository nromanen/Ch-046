package util;

import entities.Classroom;
import entities.Subject;
import entities.Type;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 16.11.16.
 */
public class ClassroomUtil {

    public static void main(String[] args) {

        ClassroomUtil util = new ClassroomUtil();

        List<Classroom> classrooms = util.readClassroom("D:/ariezz/SoftServe_java/Ch-046/src/util/test.json");

        Classroom cRom = new Classroom();
        cRom.setNumber("323");
        cRom.setCapacity(39);
        cRom.setType(Type.LAB);

        classrooms.add(cRom);

        Subject subject = new Subject();
        subject.setType(Type.LAB);
        subject.setName("C++");
        subject.setStudentsAmount(12);
        subject.setCourseN(2);

        System.out.println(util.getClassroms(classrooms,subject));

        util.writeClassroom(classrooms, "D:/ariezz/SoftServe_java/Ch-046/src/util/test1.json");


    }


    public List<Classroom> readClassroom(String fileName) {

        JSONParser parser = new JSONParser();
        List<Classroom> classrooms = new ArrayList<>();

        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray classRooms = (JSONArray) jsonObject.get("classrooms");

            Iterator<JSONObject> iterator = classRooms.iterator();
            while (iterator.hasNext()) {

                JSONObject jObj = iterator.next();

                Classroom classroom = new Classroom();
                classroom.setNumber((String)jObj.get("name"));
                classroom.setCapacity((Long) jObj.get("capacity"));
                classroom.setType(Type.valueOf((String)jObj.get("type")));

                classrooms.add(classroom);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return classrooms;
    }

    public boolean writeClassroom(List<Classroom> classrooms, String fileName) {

        JSONObject obj = new JSONObject();
        JSONArray jsonList = new JSONArray();

        for (Classroom classroom : classrooms) {
            JSONArray list = new JSONArray();
            list.add(classroom.getNumber());
            list.add(classroom.getCapacity());
            list.add(classroom.getType().toString());

            jsonList.add(list);
        }



        obj.put("classrooms", jsonList);

        try(FileWriter file = new FileWriter(fileName)){

            file.write(obj.toJSONString());
            file.flush();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }

    public List<Classroom> getClassroms(List<Classroom> classromms, Subject subject){
        List<Classroom> result = new ArrayList<>();

        for (Classroom c: classromms){
            if (c.getType().equals(subject.getType()) && c.getCapacity() >= subject.getStudentsAmount()){
                result.add(c);
            }
        }

        Collections.sort(result);

        return result;
    }

}
