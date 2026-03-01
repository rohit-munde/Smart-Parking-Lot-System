import services.CheckoutService;
import services.ParkingAllocationService;
import utils.ErrorHandler;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ParkingAllocationService parkingAllocationService = new ParkingAllocationService(scanner);
    private static final CheckoutService checkoutService = new CheckoutService(scanner);
    public static void main(String[] args) {

        utils.DataStore<models.ParkingSpot> parkingSpotStore = new utils.DataStore<>("Parking Spot");
        services.ParkingSpotService parkingSpotService = services.ParkingSpotService.getInstance();
        parkingSpotService.initStore(parkingSpotStore);
        parkingSpotService.ensureSeeded(5, 20);


        boolean running = true;

        while (running){
            try {
                System.out.println("\n--- Parking Lot Management ---");
                System.out.println("1. View Parking Spots");
                System.out.println("2. Book a Parking Spot");
                System.out.println("3. Checkout from Parking Spot");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> parkingSpotService.printSpotTable();
                    case 2 -> parkingAllocationService.allocateParkingSpot();
                    case 3 -> checkoutService.checkoutFromParkingSpot();
                    case 0 -> {
                        System.out.println("Exiting...");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                ErrorHandler.handle(e);
            }
        }
    }
}