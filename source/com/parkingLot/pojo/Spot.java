package com.parkingLot.pojo;

public class Spot {
    private final int slotNo;
    private final int floorNo;
    private final VehicleType vehicleType;
    private boolean isOccupied;
    private Vehicle vehicle;

    public Spot(int slotNo,int floorNo,VehicleType vehicleType) {
        this.slotNo = slotNo;
        this.floorNo = floorNo;
        this.vehicleType = vehicleType;
        this.isOccupied = false;
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

    public void park(Vehicle vehicle) {
        if(!this.isOccupied && vehicle.getVehicleType() == this.vehicleType) {
            this.vehicle = vehicle;
            this.isOccupied = true;
        } else {
            throw new IllegalStateException("Slot is already occupied or vehicle type does not match.");
        }
    }

    public void unPark() {
        if(this.isOccupied) {
            this.vehicle = null;
            this.isOccupied = false;
        } else {
            throw new IllegalStateException("Slot is already empty.");
        }
    }
}
