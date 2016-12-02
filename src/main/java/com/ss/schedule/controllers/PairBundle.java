package com.ss.schedule.controllers;

import com.ss.schedule.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 02.12.2016.
 */
public class PairBundle {

    private static List<TimeTable> timeTables = new ArrayList<>();

    public static List<TimeTable> getTimeTables() {
        return timeTables;
    }

    public static void addTimeTable(TimeTable timeTable) {
        timeTables.add(timeTable);
    }


}
