package com.example.robot.exception;

public class ForbiddenMoveException extends RuntimeException {

    public ForbiddenMoveException(String error) {
        super(error);
    }
}
