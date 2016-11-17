package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Classroom;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 17.11.16.
 */
public class JsonSerializable implements MySerializable<Classroom> {

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


    // write list of rooms to JSON file
    @Override
    public boolean write (List<Classroom> classrooms, String fileName) {

        Rooms rooms = new Rooms();
        rooms.setRooms(classrooms);

        ObjectMapper mapper = new ObjectMapper();
        try {
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), rooms);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception  e) {
            e.printStackTrace();
        }

        return true;
    }

    // read list of rooms from JSON file
    @Override
    public List<Classroom> read(String fileName){

        Rooms rooms = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            rooms = mapper.readValue(new File(fileName), Rooms.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rooms.getRooms();
    }



}
