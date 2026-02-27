package models;

import enums.VehicleType;
import utils.IdGenerator;

public class ParkingSpot {
    private int id = IdGenerator.generateParkingSpotId();
    private int floor;
    private VehicleType spotType;
    private boolean isOccupied;

    public ParkingSpot() {}

    public ParkingSpot(VehicleType spotType, boolean isOccupied) {
        this.spotType = spotType;
        this.isOccupied = isOccupied;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public VehicleType getSpotType() {
        return spotType;
    }

    public void setSpotType(VehicleType spotType) {
        this.spotType = spotType;
    }

    @Override
    public String toString() {
        System.out.printf("%n%-8s %-6s %-12s %-10s%n", "SpotId", "FLOOR", "TYPE", "OCCUPIED");
        System.out.println("--------------------------------------");
        return String.format("%-8d %-6d %-12s %-10s",
                this.id,
                this.floor,
                this.spotType,
                this.isOccupied);
    }
}
