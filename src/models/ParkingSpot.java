package models;

import enums.VehicleType;
import utils.IdGenerator;

public class ParkingSpot {
    private int id = IdGenerator.generateParkingSpotId();
    private int vehicleId;
    private String vehicleNo;
    private int floor;
    private VehicleType spotType;
    private boolean isOccupied;
    private long checkInTime;

    public ParkingSpot() {}

    public ParkingSpot(int vehicleId, VehicleType spotType, boolean isOccupied, long checkInTime) {
        this.vehicleId = vehicleId;
        this.spotType = spotType;
        this.isOccupied = isOccupied;
        this.checkInTime = checkInTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
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

    public long getCheckInTime() {
        return this.checkInTime;
    }

    public void setCheckInTime(long checkInTime) {
        this.checkInTime = checkInTime;
    }

    @Override
    public String toString() {
        System.out.printf("%n%-8s %-6s %-6s %-12s %-12s %-10s%n", "SpotId", "FLOOR", "TYPE", "OCCUPIED", "VehicleId", "CheckInTime");
        System.out.println("-------------------------------------------------------------");
        return String.format("%-8d %-6d %-6s %-12s %-12s %-10s",
                this.id,
                this.floor,
                this.spotType,
                this.isOccupied,
                this.vehicleId,
                this.checkInTime);
    }
}
