package com.parkingLot.pojo;

import com.parkingLot.exception.InvalidTicketException;
import com.parkingLot.exception.SpotTypeMismatchException;
import com.parkingLot.util.Validator;

public class Spot {
    private final int slotNo;
    private final int floorNo;
    private final VehicleType vehicleType;
    private boolean isOccupied;
    private Vehicle vehicle;

    private Spot(int slotNo, int floorNo, VehicleType vehicleType) {
        Validator.requirePositive(slotNo, "slotNo");
        Validator.requireNonNegative(floorNo, "floorNo");
        Validator.requireNonNull(vehicleType, "vehicleType");
        this.slotNo = slotNo;
        this.floorNo = floorNo;
        this.vehicleType = vehicleType;
        this.isOccupied = false;
    }

    public static Spot of(int slotNo, int floorNo, VehicleType vehicleType) {
        return new Spot(slotNo, floorNo, vehicleType);
    }

    public int getSlotNo() {
        return slotNo;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public synchronized boolean tryPark(Vehicle vehicle) {
        Validator.requireNonNull(vehicle, "vehicle");
        if (vehicle.getVehicleType() != this.vehicleType) {
            throw new SpotTypeMismatchException(vehicle.getVehicleType(), this.vehicleType);
        }
        if (this.isOccupied) {
            return false;
        }
        this.vehicle = vehicle;
        this.isOccupied = true;
        return true;
    }

    public synchronized void unPark() {
        if (!this.isOccupied) {
            throw new InvalidTicketException("Spot is already empty.");
        }
        this.vehicle = null;
        this.isOccupied = false;
    }
}
