package ua.cv.tim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.cv.tim.service.UserService;

import javax.mail.MessagingException;

/**
 * Created by mmaksymtc on 23.01.2017.
 */
@RestControllerAdvice
public class ExceptionHandlerClass {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NullPointerException.class)
    public String nullPointerHandler(NullPointerException e) {
        logger.error("Exception: {}", e);
        return e.getMessage();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = MessagingException.class)
    public String messageExceptionHandler(MessagingException e) {
        logger.error("Exception: {}", e);
        return e.getMessage();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public String generalExceptionHandler(Exception e) {
        logger.error("Exception: {}", e.getMessage());
        return e.getMessage();
    }
}
