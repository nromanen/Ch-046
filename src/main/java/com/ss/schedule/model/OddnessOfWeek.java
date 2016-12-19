package com.ss.schedule.model;

public enum OddnessOfWeek {
ODD(1), EVEN(2), ALL(3);

    private long id;

    OddnessOfWeek(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static OddnessOfWeek getById(long id) {
        for(OddnessOfWeek e : values()) {
            if(e.id == id) return e;
        }
        return null;
    }
}
