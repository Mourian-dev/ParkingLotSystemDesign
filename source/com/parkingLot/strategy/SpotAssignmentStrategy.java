package com.parkingLot.strategy;

import com.parkingLot.pojo.Ticket;
import com.parkingLot.pojo.Vehicle;

public interface SpotAssignmentStrategy {

    Ticket assignSpot(Vehicle vehicle);
}
