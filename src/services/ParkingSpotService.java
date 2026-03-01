package services;

import enums.VehicleType;
import models.ParkingSpot;
import utils.DataStore;

import java.util.Map;

public class ParkingSpotService {
    private static final ParkingSpotService INSTANCE = new ParkingSpotService();
    private DataStore<ParkingSpot> parkingSpotStore;

    private ParkingSpotService() {}

    public static ParkingSpotService getInstance() {
        return INSTANCE;
    }

    public void initStore(DataStore<ParkingSpot> parkingSpotStore) {
        if (this.parkingSpotStore == null) {
            this.parkingSpotStore = parkingSpotStore;
        }
    }

    private void ensureStoreReady() {
        if (parkingSpotStore == null) {
            throw new IllegalStateException("ParkingSpotService not initialized with DataStore.");
        }
    }

    public void ensureSeeded(int floorCount, int spotsPerFloor) {
        ensureStoreReady();
        if (!parkingSpotStore.readAll().isEmpty()) {
            return;
        }
        if (floorCount <= 0 || spotsPerFloor <= 0) {
            return;
        }

        for (int floor = 1; floor <= floorCount; floor++) {
            VehicleType floorType = resolveFloorType(floor);
            for (int spotIndex = 1; spotIndex <= spotsPerFloor; spotIndex++) {
                ParkingSpot spot = new ParkingSpot();
                spot.setSpotType(floorType);
                spot.setOccupied(false);
                spot.setFloor(floor);
                parkingSpotStore.create(spot.getId(), spot);
            }
        }
    }

    public Map<Integer, ParkingSpot> getAllSpots() {
        ensureStoreReady();
        return parkingSpotStore.readAll();
    }

    public void printSpotTable() {
        ensureStoreReady();
        Map<Integer, ParkingSpot> allSpots = parkingSpotStore.readAll();
        if (allSpots.isEmpty()) {
            System.out.println("No Parking Spots in library.");
            return;
        }

        System.out.printf("%n%-6s %-6s %-12s %-10s%n", "ID", "FLOOR", "TYPE", "OCCUPIED");
        System.out.println("----------------------------------");
        for (ParkingSpot spot : allSpots.values()) {
            System.out.printf("%-6d %-6d %-12s %-10s%n",
                    spot.getId(),
                    spot.getFloor(),
                    spot.getSpotType(),
                    spot.isOccupied());
        }
    }

    private VehicleType resolveFloorType(int floor) {
        if (floor == 1) {
            return VehicleType.TRUCK;
        }
        if (floor == 2) {
            return VehicleType.MOTORCYCLE;
        }
        return VehicleType.CAR;
    }

    public int findAppropriateSpot(VehicleType vehicleType) {
        ensureStoreReady();
        return parkingSpotStore.readAll().values().stream()
                .filter(spot -> !spot.isOccupied() && spot.getSpotType() == vehicleType)
                .map(ParkingSpot::getId)
                .findFirst()
                .orElse(-1);
    }

    public boolean isVehicleAlreadyParked(String vehicleNo) {
        ensureStoreReady();
        return parkingSpotStore.readAll().values().stream()
                .anyMatch(spot -> spot.isOccupied() && vehicleNo.equalsIgnoreCase(spot.getVehicleNo()));
    }

    public ParkingSpot assignSpot(int spotId, int vehicleId, String vehicleNo) {
        ensureStoreReady();
        ParkingSpot spot = parkingSpotStore.read(spotId);
        if (spot != null && !spot.isOccupied()) {
            spot.setOccupied(true);
            spot.setVehicleId(vehicleId);
            spot.setVehicleNo(vehicleNo);
            spot.setCheckInTime(System.currentTimeMillis());
            parkingSpotStore.update(spotId, spot);
            return spot;
        }
        return null;
    }

    public ParkingSpot getParkingSpotById(int spotId) {
        ensureStoreReady();
        return parkingSpotStore.read(spotId);
    }

    public void vacateSpot(int spotId) {
        ensureStoreReady();
        ParkingSpot spot = parkingSpotStore.read(spotId);
        if (spot != null && spot.isOccupied()) {
            spot.setOccupied(false);
            spot.setVehicleId(0);
            spot.setVehicleNo(null);
            spot.setCheckInTime(0);
            parkingSpotStore.update(spotId, spot);
        }
    }
}
