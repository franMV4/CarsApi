package com.svalero.carsapi.exception;

public class GarageNotFoundException extends Exception {
    private static final String DEFAULT_ERROR_MESSAGE = "Garage not found";

    public GarageNotFoundException(String message) {
        super(message);
    }

    public GarageNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

}
