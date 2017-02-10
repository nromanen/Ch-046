package ua.cv.tim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.cv.tim.exception.EntityNotUniqueException;
import ua.cv.tim.service.UserService;

import javax.mail.MessagingException;

/**
 * Created by mmaksymtc on 23.01.2017.
 */
@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = EntityNotUniqueException.class)
    public String entityNotUniqueHandler(Exception e) {
        logger.error("ExceptionHandlerClass.EntityNotUniqueException: {}", e);
        return e.getMessage();
    }
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = MessagingException.class)
    public String messageExceptionHandler(MessagingException e) {
        logger.error("Exception: {}", e);
        return e.getMessage();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public String illegalArgumentException(IllegalArgumentException e) {
        logger.error("IllegalArgumentException: {}", e);
        e.printStackTrace();
        return e.getMessage();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public String generalExceptionHandler(Exception e) {
        logger.error("Exception: {}", e.getMessage());
        e.printStackTrace();
        return e.getMessage();
    }

//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(value = VillageNotUniqueException.class)
//    public String villageNotUniqueHandler(VillageNotUniqueException e){
//        logger.error("VillageNotUniqueException: {}", e.getMessage());
//        e.printStackTrace();
//        return e.getMessage();
//    }

}
