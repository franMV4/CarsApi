package com.svalero.carsapi.exception;

public class UserNotFoundException extends Throwable {
    private static final String DEFAULT_ERROR_MESSAGE = "User not found";

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

}