package models;

import enums.VehicleType;
import utils.IdGenerator;

public class Vehicle {
    private int id = IdGenerator.generateVehicleId();
    private String vehicleNo;
    private VehicleType vehicleType;

    public Vehicle() {}

    public Vehicle(String vehicleNo, VehicleType vehicleType) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
