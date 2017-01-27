package ua.cv.tim.exception;

/**
 * Created by mmaksymtc on 24.01.2017.
 */
public class EntityNotUniqueException extends Exception {


    public EntityNotUniqueException() {
    }

    //Constructor that accepts a message
    public EntityNotUniqueException(String message) {
        super(message);
    }
}

