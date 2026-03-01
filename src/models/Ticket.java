package models;

import utils.IdGenerator;

public class Ticket {
    private int id = IdGenerator.generateTicketId();
    private int vehicleId;
    private long entryTime;
    private long exitTime;
    private double totalFare;

    public Ticket() {}

    public Ticket(int vehicleId, long entryTime, long exitTime, double totalFare) {
        this.vehicleId = vehicleId;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
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

    @Override
    public String toString() {
        String entryTimeFormatted = java.time.Instant.ofEpochMilli(entryTime)
                .atZone(java.time.ZoneId.systemDefault())
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String exitTimeFormatted = java.time.Instant.ofEpochMilli(exitTime)
                .atZone(java.time.ZoneId.systemDefault())
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        long durationMinutes = (exitTime - entryTime) / (1000 * 60);

        return String.format("""
                +------------------------------------+
                |        PARKING RECEIPT             |
                +------------------------------------+
                |  Ticket ID    :  %-17d |
                |  Vehicle ID   :  %-17d |
                +------------------------------------+
                |  Entry Time   :                    |
                |    %-30s  |
                |  Exit Time    :                    |
                |    %-30s  |
                |  Duration     :  %-14d min|
                +------------------------------------+
                |  TOTAL FARE   :  Rs %-14.2f |
                +------------------------------------+
                """, id, vehicleId, entryTimeFormatted, exitTimeFormatted, durationMinutes, totalFare);
    }
}
