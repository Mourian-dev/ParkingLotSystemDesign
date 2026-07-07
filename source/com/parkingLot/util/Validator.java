package com.parkingLot.util;

import com.parkingLot.exception.InvalidInputException;

public final class Validator {

    private Validator() {}

    public static void requireNonNull(Object value, String fieldName) {
        if (value == null) {
            throw new InvalidInputException(fieldName + " is required.");
        }
    }

    public static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new InvalidInputException(fieldName + " is required.");
        }
    }

    public static void requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be positive.");
        }
    }

    public static void requireNonNegative(int value, String fieldName) {
        if (value < 0) {
            throw new InvalidInputException(fieldName + " must be >= 0.");
        }
    }

    public static void requirePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be > 0.");
        }
    }

    public static void requireNonNegative(double value, String fieldName) {
        if (value < 0) {
            throw new InvalidInputException(fieldName + " cannot be negative.");
        }
    }

    public static void requireNonEmpty(java.util.List<?> list, String fieldName) {
        if (list == null || list.isEmpty()) {
            throw new InvalidInputException(fieldName + " must not be empty.");
        }
    }
}
