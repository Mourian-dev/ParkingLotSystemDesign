# ParkingLotSystemDesign

This project models a parking lot with strategy-based spot assignment and pricing.

## Design upgrades

- Strategy pattern is now complete with concrete implementations:
	- `NearestSpotAssignmentStrategy`
	- `HourlyPricingStrategy`
- Repository pattern has working in-memory implementations:
	- `InMemorySpotRepository`
	- `InMemoryTicketRepository`
- `ParkingService` now performs a complete lifecycle:
	- prevents duplicate active tickets for the same vehicle
	- parks and unparks spots correctly
	- computes fee and records payment
- Core models include constructor validation and safer state transitions.

## Run demo

```bash
cd /Users/mourian-17388/Documents/Repo/ParkingLotSystemDesign
javac $(find source -name "*.java")
java -cp source com.parkingLot.ParkingLotDemo
```

The demo parks one vehicle, unparks it, and prints ticket and payment details.
