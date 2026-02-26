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
}
