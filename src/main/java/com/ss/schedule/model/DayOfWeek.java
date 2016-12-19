package com.ss.schedule.model;

import com.ss.schedule.dao.DayOfWeekDao;

public enum DayOfWeek {
MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;
    long id;
    DayOfWeek(){
        this.id=new DayOfWeekDao().getIdByName(name());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
