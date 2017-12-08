package com.example.robot.exception;

public class MissingRobotException extends RuntimeException {

    public MissingRobotException(String error) {
        super(error);
    }
}
