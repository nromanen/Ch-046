package com.ss.schedule.model;

public enum Pair {
FIRST(1), SECOND(2), THIRD(3), FORTH(4), FIFTH(5), SIXTH(6), SEVENTH(7), EIGHTS(8), NINTH(9), TENTH(10);

    private long id;

    Pair(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static Pair getById(Long id) {
        for(Pair e : values()) {
            if(e.id == id) return e;
        }
        return null;
    }
}
