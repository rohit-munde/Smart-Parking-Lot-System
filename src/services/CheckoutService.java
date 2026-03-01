package services;

import models.ParkingSpot;
import models.Ticket;

import java.util.Scanner;

public class CheckoutService {
    private final Scanner sc;

    public CheckoutService(Scanner scanner) {
        this.sc = scanner;
    }

    public void checkoutFromParkingSpot() {
        ParkingSpot spot = takeUserInputForCheckout();
        double totalFare = FareCalculationService.getInstance().calculateFare(spot.getCheckInTime(), System.currentTimeMillis(), spot.getSpotType());
        Ticket ticket = new Ticket(spot.getVehicleId(), spot.getCheckInTime(), System.currentTimeMillis(), totalFare);
        ParkingSpotService.getInstance().vacateSpot(spot.getId());
        System.out.println(ticket.toString());
    }

    private ParkingSpot takeUserInputForCheckout() {
        System.out.println("Enter spotID to checkout: ");
        int spotId = sc.nextInt();
        sc.nextLine();

        ParkingSpotService parkingSpotService = ParkingSpotService.getInstance();
        ParkingSpot spot = parkingSpotService.getParkingSpotById(spotId);

        if(spot == null) {
            throw new RuntimeException("Invalid spot ID: " + spotId);
        }

        if(!spot.isOccupied()) {
            throw new RuntimeException("Parking spot " + spotId + " is already vacant.");
        }

        return spot;
    }
}
