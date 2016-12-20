package com.ss.schedule.exceptions;

/**
 * Created by oleg on 14.12.16.
 */
public class TimetableException extends RuntimeException {
    String error;
    @Override
    public String toString() {
        return error;

    }

    public TimetableException(String message) {
        super(message);
        error=message;
    }
}
