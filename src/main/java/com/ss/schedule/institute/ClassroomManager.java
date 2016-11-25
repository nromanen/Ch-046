package com.ss.schedule.institute;

import com.ss.schedule.model.Classroom;
import com.ss.schedule.model.Group;
import com.ss.schedule.model.Subject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rmochetc on 17.11.2016.
 */
public class ClassroomManager {

    //return list of rooms that are available for subject(have the same type) and groupSet (have equals or more capacity)
    public List<Classroom> getListOfAvailableRooms(Subject subject, Group group){

        List<Classroom> availableClassrooms = new ArrayList<>();
        Faculty faculty = new Faculty();

        for (Classroom room: faculty.getClassrooms()) {
                if (room.getCapacity() >= group.getCount() && room.getTypes().contains(subject.getType())){
                    availableClassrooms.add(room);
            }
        }
        Collections.sort(availableClassrooms);
        return availableClassrooms;
    }
}
