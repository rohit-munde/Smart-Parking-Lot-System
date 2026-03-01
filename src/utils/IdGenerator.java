package utils;

public final class IdGenerator {
    private static int vehicleIdCounter = 1;
    private static int parkingSpotIdCounter = 1;
    private static int ticketIdCounter = 1;

    private IdGenerator() {
        // Private constructor to prevent instantiation
    }

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
