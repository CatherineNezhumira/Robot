package com.example.robot.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RobotExceptionsHandler {

    @ExceptionHandler(MissingRobotException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String missingRobot(MissingRobotException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ForbiddenMoveException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public String forbiddenRobotMove(ForbiddenMoveException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String notValidParameter(MethodArgumentNotValidException ex) {
        ObjectError error = ex.getBindingResult().getAllErrors().get(0);
        return error.getDefaultMessage();
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String notValidParameter(InvalidFormatException ex) {
        return ex.getMessage().substring(0, ex.getMessage().indexOf("\n"));
    }

}