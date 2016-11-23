package com.ss.schedule;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ss.schedule.io.InputOutputJson;
import com.ss.schedule.model.Classroom;

import java.util.ArrayList;

/**
 * Created by rmochetc on 23.11.2016.
 */
public class Main {

    public static void main(String[] args) {

        InputOutputJson<ArrayList<Classroom>> classroomManager = new InputOutputJson<>(
                new TypeReference<ArrayList<Classroom>>() {
                });

        ArrayList<Classroom> classrooms = classroomManager.readFromFile("room.json");

        System.out.println(classrooms);
    }
}
