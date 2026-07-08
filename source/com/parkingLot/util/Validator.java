package com.parkingLot.util;

import com.parkingLot.exception.InvalidInputException;

import java.util.Collection;

public final class Validator {

    private Validator() {}

    public static void requireNonNull(Object value, String fieldName) {
        if (value == null) {
            throw InvalidInputException.missingField(fieldName);
        }
    }

    public static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw InvalidInputException.missingField(fieldName);
        }
    }

    public static void requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw InvalidInputException.mustBePositive(fieldName);
        }
    }

    public static void requireNonNegative(int value, String fieldName) {
        if (value < 0) {
            throw InvalidInputException.mustBeNonNegative(fieldName);
        }
    }

    public static void requirePositive(double value, String fieldName) {
        if (value <= 0) {
            throw InvalidInputException.mustBePositive(fieldName);
        }
    }

    public static void requireNonNegative(double value, String fieldName) {
        if (value < 0) {
            throw InvalidInputException.mustBeNonNegative(fieldName);
        }
    }

    public static void requireNonEmpty(Collection<?> list, String fieldName) {
        if (list == null || list.isEmpty()) {
            throw InvalidInputException.mustBeNonEmpty(fieldName);
        }
    }
}
