package util;

import models.Classroom;

import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 17.11.16.
 */
public class Main {
    public static void main(String[] args) {

        JsonSerializable jsonSerializable = new JsonSerializable();

        List<Classroom> rooms = jsonSerializable.read("test_write.json");
        Collections.sort(rooms);
//
//        for (Classroom room : rooms) {
//            System.out.println(room);
//        }




      //  jsonSerializable.write(rooms, "test_write.json");

//        Subject subject = new Subject();
//        subject.setName("Java");
//        subject.setType(Type.LEC);
//        subject.setCourseN(1);
//
//        Group group = new Group();
//        group.setName("124");
//        group.setAmount(15);
//
//        Group group1 = new Group();
//        group.setName("125");
//        group.setAmount(12);
//
//        Util util = new Util();
//
//        List<Classroom> classrooms = util.getListOfAvailableRooms(jsonSerializable.read("room.json"), subject, group, group);
//        Collections.sort(classrooms);
//
//        for (Classroom c :
//                classrooms) {
//            System.out.println(c);
//        }
//
//        XmlSerializable xmlSerializable = new XmlSerializable();
//
//        xmlSerializable.write(rooms, "room.xml");

//        List<Classroom> classrooms = xmlSerializable.read("room.xml");
//
//        for (Classroom c :
//                classrooms) {
//            System.out.println(c);
//        }

        MySerializable myUtil = new MySerializableUtil();

        //myUtil.write(rooms,"test.txt");
        List<Classroom> list = myUtil.read("test.txt");

        for (Classroom room: list){
            System.out.println(room);
        }



    }
}
