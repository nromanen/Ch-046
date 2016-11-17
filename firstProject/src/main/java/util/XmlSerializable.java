package util;

import models.Classroom;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.util.List;

/**
 * Created by rmochetc on 17.11.2016.
 */
public class XmlSerializable implements MySerializable<Classroom> {

    @XmlRootElement(name = "Rooms")
    @XmlType(propOrder = {"rooms"})
    private static class Rooms {

        private List<Classroom> rooms;
        public Rooms() {
        }

        public List<Classroom> getRooms() {
            return rooms;
        }

        public void setRooms(List<Classroom> rooms) {
            this.rooms = rooms;
        }
    }


    @Override
    public  boolean write(List<Classroom> classrooms, String fileName) {
        try {

            Rooms rooms = new Rooms();
            rooms.setRooms(classrooms);

            JAXBContext context = JAXBContext.newInstance(Rooms.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Write to System.out for debugging
            // m.marshal(emp, System.out);

            // Write to File
            m.marshal(rooms, new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public  List<Classroom> read(String fileName) {

        Rooms rooms = null;
        try {

            JAXBContext context = JAXBContext.newInstance(Rooms.class);
            Unmarshaller un = context.createUnmarshaller();
            rooms = (Rooms) un.unmarshal(new File(fileName));

        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        return rooms.getRooms();
    }

}
