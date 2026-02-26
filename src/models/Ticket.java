package models;

import utils.IdGenerator;

public class Ticket {
    private int id = IdGenerator.generateTicketId();
    private int vehicleId;
    private long entryTime;
    private long exitTime;
    private double totalFare;

    public Ticket() {}

    public Ticket(int vehicleId, long entryTime, double totalFare) {
        this.vehicleId = vehicleId;
        this.entryTime = entryTime;
        this.totalFare = totalFare;
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

    public long getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(long entryTime) {
        this.entryTime = entryTime;
    }

    public long getExitTime() {
        return exitTime;
    }

    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }
}
