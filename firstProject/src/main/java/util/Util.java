package util;

import models.Classroom;
import models.Group;
import models.Subject;
import models.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 17.11.2016.
 */
public class Util {

    //return list of rooms that are available for subject(have the same type) and groups (have equals or more capacity)
    public List<Classroom> getListOfAvailableRooms(List<Classroom> classrooms, Subject subject, Group... groups){
        int capacity = 0;

        for (Group group: groups){
            capacity+=group.getAmount();
        }

        List<Classroom> result = new ArrayList<>();

        for (Classroom room: classrooms) {

            if (room.getCapacity() >= capacity){
                for (Type t: room.getTypes()) {
                    if(t.equals(subject.getType())){
                        result.add(room);
                    }
                }

            }
        }

        return result;
    }
}
