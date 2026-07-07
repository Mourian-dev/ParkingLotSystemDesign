package com.parkingLot.pojo;

public class SpotInfo {
    private final int slotNo;
    private final int floorNo;
    private final VehicleType vehicleType;

    public SpotInfo(int slotNo, int floorNo, VehicleType vehicleType) {
        this.slotNo = slotNo;
        this.floorNo = floorNo;
        this.vehicleType = vehicleType;
    }

    public static SpotInfo from(Spot spot) {
        return new SpotInfo(spot.getSlotNo(), spot.getFloorNo(), spot.getVehicleType());
    }

    public int getSlotNo() { return slotNo; }
    public int getFloorNo() { return floorNo; }
    public VehicleType getVehicleType() { return vehicleType; }

    @Override
    public String toString() {
        return "floor=" + floorNo + ", slot=" + slotNo + ", type=" + vehicleType;
    }
}
