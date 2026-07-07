package com.parkingLot.observer;

public class ConsoleParkingLogger implements ParkingEventListener {

    @Override
    public void onEvent(ParkingEvent event) {
        System.out.printf("[%s] %s | ticket=%s | spot=%s%n",
                event.getType(),
                event.getTicket().getVehicle().getVehicleNumber(),
                event.getTicket().getTicketId(),
                event.getTicket().getSpotInfo());
    }
}
