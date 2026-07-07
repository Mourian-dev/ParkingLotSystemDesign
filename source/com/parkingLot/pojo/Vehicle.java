package com.parkingLot.pojo;

import com.parkingLot.util.Validator;

public class Vehicle {

    private final String vehicleNumber;
    private final VehicleType vehicleType;

    private Vehicle(String vehicleNumber, VehicleType vehicleType) {
        Validator.requireNonBlank(vehicleNumber, "vehicleNumber");
        Validator.requireNonNull(vehicleType, "vehicleType");
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public static Vehicle of(String vehicleNumber, VehicleType vehicleType) {
        return new Vehicle(vehicleNumber, vehicleType);
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
