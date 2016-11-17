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

        List<Classroom> rooms = jsonSerializable.read("room.json");
        Collections.sort(rooms);

        for (Classroom room : rooms) {
            System.out.println(room);
        }


//        Classroom c1 = new Classroom();
//        c1.setName("101");
//        c1.setCapacity(25);
//
//        List<Type> type = new ArrayList<>();
//        type.add(Type.LEC);
//        type.add(Type.PR);
//
//
//        c1.setTypes(type);
//        c1.setDescription("without proector");
//
//        Classroom c2 = new Classroom();
//        c2.setName("102");
//        c2.setCapacity(12);
//        List<Type> type1 = new ArrayList<>();
//        type1.add(Type.LAB);
//        c2.setTypes(type1);
//        rooms.add(c2);
//
//        util.write(rooms, "test_write.json");

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

        XmlSerializable xmlSerializable = new XmlSerializable();

        xmlSerializable.write(rooms, "room.xml");

//        List<Classroom> classrooms = xmlSerializable.read("room.xml");
//
//        for (Classroom c :
//                classrooms) {
//            System.out.println(c);
//        }


    }
}
