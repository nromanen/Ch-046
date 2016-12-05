package com.ss.schedule.model;

public enum DayOfWeek {
MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5);

    private long id;

    DayOfWeek(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
