package com.ss.schedule.institute;

import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 17.11.2016.
 */
public class ClassroomUtil {

    //return list of rooms that are available for subject(have the same type) and groups (have equals or more capacity)
    public List<Classroom> getListOfAvailableRooms(Subject subject, Group group){

        List<Classroom> availableClassrooms = new ArrayList<>();

        for (Classroom room: Faculty.classrooms) {
                if (room.getCapacity() >= group.getAmount() && room.getTypes().contains(subject.getType())){
                    availableClassrooms.add(room);
            }
        }
        Collections.sort(availableClassrooms);
        return availableClassrooms;
    }
}
