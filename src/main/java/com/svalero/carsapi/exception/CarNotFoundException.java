package com.svalero.carsapi.exception;

public class CarNotFoundException extends Exception {
    private static final String DEFAULT_ERROR_MESSAGE = "Car not found";

    public CarNotFoundException(String message) {
        super(message);
    }

    public CarNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

}
