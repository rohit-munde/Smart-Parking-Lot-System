package enums;

public enum VehicleType {
    TRUCK(20),
    CAR(10),
    MOTORCYCLE(5);

    private final int ratePerMinute;

    VehicleType(int ratePerMinute) {
        this.ratePerMinute = ratePerMinute;
    }

    public int getRatePerMinute() {
        return ratePerMinute;
    }

    public static VehicleType getVehicleTypeFromChoice(int choice) {
        return switch (choice) {
            case 1 -> TRUCK;
            case 2 -> CAR;
            case 3 -> MOTORCYCLE;
            default -> throw new IllegalArgumentException("Invalid choice for VehicleType: " + choice);
        };
    }
}
