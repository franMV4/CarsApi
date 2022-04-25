package com.svalero.carsapi.exception;

public class ReparationNotFoundException extends Exception {
    private static final String DEFAULT_ERROR_MESSAGE = "Reparation not found";

    public ReparationNotFoundException(String message) {
        super(message);
    }

    public ReparationNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

}
