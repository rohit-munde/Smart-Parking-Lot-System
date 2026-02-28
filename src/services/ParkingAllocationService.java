package services;

import enums.VehicleType;
import models.ParkingSpot;
import models.Vehicle;

import java.util.Scanner;

public class ParkingAllocationService {
    private final ParkingSpotService parkingSpotService = ParkingSpotService.getInstance();
    private final Scanner sc = new Scanner(System.in);

    public void allocateParkingSpot() {
        Vehicle vehicle = getVehicleDetailsFromUser();
        int spotId = parkingSpotService.findAppropriateSpot(vehicle.getVehicleType());
        if (spotId > 0) {
            ParkingSpot parkingSpot = parkingSpotService.assignSpot(spotId, vehicle.getId());
            System.out.println("\n✅ Parking Spot Allocated Successfully!");
            System.out.println(parkingSpot.toString());
        } else {
            throw new RuntimeException("No parking spot available for " + vehicle.getVehicleType());
        }
    }

    public Vehicle getVehicleDetailsFromUser() {
        System.out.println("\n--- Parking Lot Management ---");
        System.out.println("Vehicle Registration Number: ");
        String vehicleNo = sc.nextLine();
        System.out.println("Vehicle Type (1. Truck, 2. Car, 3. Bike): ");
        int typeChoice = sc.nextInt();
        sc.nextLine();
        return new Vehicle(vehicleNo, VehicleType.getVehicleTypeFromChoice(typeChoice));
    }
}
