package com.ss.schedule.model;

import com.ss.schedule.dao.OddnessOfWeekDao;

public enum OddnessOfWeek {
ODD, EVEN, ALL;
    long id;
OddnessOfWeek(){
    this.id=new OddnessOfWeekDao().getIdByName(this.name());
}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
