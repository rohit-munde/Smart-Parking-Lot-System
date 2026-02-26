package utils;

public class IdGenerator {
    private static int vehicleIdCounter = 1;
    private static int parkingSpotIdCounter = 1;
    private static int ticketIdCounter = 1;

    public IdGenerator() {}

    public static int generateVehicleId() {
        return vehicleIdCounter++;
    }

    public static int generateParkingSpotId() {
        return parkingSpotIdCounter++;
    }

    public static int generateTicketId() {
        return ticketIdCounter++;
    }
}
