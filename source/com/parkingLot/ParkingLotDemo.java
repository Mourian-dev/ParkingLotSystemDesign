package com.parkingLot;

import com.parkingLot.observer.PaymentLogger;
import com.parkingLot.pojo.Floor;
import com.parkingLot.pojo.ParkingLot;
import com.parkingLot.pojo.Payment;
import com.parkingLot.pojo.PaymentMethod;
import com.parkingLot.pojo.Spot;
import com.parkingLot.pojo.Vehicle;
import com.parkingLot.pojo.VehicleType;
import com.parkingLot.repository.PaymentRepository;
import com.parkingLot.repository.SpotRepository;
import com.parkingLot.repository.TicketRepository;
import com.parkingLot.service.ParkingService;
import com.parkingLot.service.PaymentService;
import com.parkingLot.strategy.HourlyPricingStrategy;
import com.parkingLot.strategy.NearestSpotAssignmentStrategy;
import com.parkingLot.strategy.PricingStrategy;
import com.parkingLot.strategy.SpotAssignmentStrategy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLotDemo {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.of(List.of(
                Floor.of(0, List.of(
                        Spot.of(1, 0, VehicleType.BIKE),
                        Spot.of(2, 0, VehicleType.CAR)
                )),
                Floor.of(1, List.of(
                        Spot.of(1, 1, VehicleType.CAR),
                        Spot.of(2, 1, VehicleType.TRUCK)
                ))
        ));

        SpotRepository spotRepository = SpotRepository.getInstance(parkingLot);
        TicketRepository ticketRepository = TicketRepository.getInstance();
        Map<String, Payment> paymentStore = new ConcurrentHashMap<>();
        PaymentRepository paymentRepository = PaymentRepository.getInstance(paymentStore);

        SpotAssignmentStrategy assignmentStrategy = new NearestSpotAssignmentStrategy(spotRepository);
        PricingStrategy pricingStrategy = new HourlyPricingStrategy(40.0)
                .withRate(VehicleType.BIKE, 20.0)
                .withRate(VehicleType.TRUCK, 80.0);
        PaymentService paymentService = new PaymentService(paymentRepository);

        ParkingService parkingService = new ParkingService(
                assignmentStrategy,
                ticketRepository,
                spotRepository,
                pricingStrategy,
                paymentService
        );

        parkingService.registerListener(e -> System.out.printf("[%s] %s | ticket=%s | spot=%s%n",
                e.getType(),
                e.getTicket().getVehicle().getVehicleNumber(),
                e.getTicket().getTicketId(),
                e.getTicket().getSpotInfo()));

        paymentService.registerListeners(new PaymentLogger());

        var ticket = parkingService.parkVehicle(Vehicle.of("KA-01-AA-1234", VehicleType.CAR));
        try {
            var payment = parkingService.unparkVehicle(ticket, PaymentMethod.UPI);
            System.out.println("Paid: " + payment.getAmount() + " via " + payment.getPaymentMethod());
        } catch (Exception e) {
            System.err.println("❌ Error during unpark: " + e.getMessage());
        }
    }
}
