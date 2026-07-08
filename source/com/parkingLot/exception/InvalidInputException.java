package com.parkingLot.exception;

public class InvalidInputException extends ParkingException {

    private InvalidInputException(String message) {
        super(message);
    }

    public static InvalidInputException missingField(String fieldName) {
        return new InvalidInputException(fieldName + " is required.");
    }

    public static InvalidInputException mustBePositive(String fieldName) {
        return new InvalidInputException(fieldName + " must be positive.");
    }

    public static InvalidInputException mustBeNonNegative(String fieldName) {
        return new InvalidInputException(fieldName + " must be >= 0.");
    }

    public static InvalidInputException mustBeNonEmpty(String fieldName) {
        return new InvalidInputException(fieldName + " must not be empty.");
    }
}
