package com.ss.schedule.model;

/**
 * Created by oleg on 27.11.16.
 */

public class EnumTimetableCharacteristic {
    DayOfWeek dayOfWeek;
    OddnessOfWeek oddnessOfWeek;
    Pair pair;

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public OddnessOfWeek getOddnessOfWeek() {
        return oddnessOfWeek;
    }

    public void setOddnessOfWeek(OddnessOfWeek oddnessOfWeek) {
        this.oddnessOfWeek = oddnessOfWeek;
    }

    public Pair getPair() {
        return pair;
    }

    public void setPair(Pair pair) {
        this.pair = pair;
    }

    public EnumTimetableCharacteristic(DayOfWeek dayOfWeek, OddnessOfWeek oddnessOfWeek, Pair pair) {

        this.dayOfWeek = dayOfWeek;
        this.oddnessOfWeek = oddnessOfWeek;
        this.pair = pair;
    }
}
