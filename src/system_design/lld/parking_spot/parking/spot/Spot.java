package system_design.lld.parking_spot.parking.spot;

import system_design.lld.parking_spot.vehicle.Vehicle;

public abstract class Spot {
    private int spotId;
    private SpotType spotType;
    private boolean isOccupied;
    private Vehicle vehicle;

    public Spot(int spotId) {
        this(spotId, SpotType.COMPACT);
    }

    public Spot(int spotId, SpotType spotType) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.isOccupied = false;
    }

    public synchronized boolean isAvailable() {
        return !isOccupied;
    }

    public synchronized boolean parkVehicle(Vehicle vehicle) {
        if (isOccupied) return false;
        this.vehicle = vehicle;
        return isOccupied = true;
    }

    public synchronized void unparkVehicle() {
        if (!isOccupied) return;
        this.vehicle = null;
        isOccupied = false;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public abstract boolean canFitVehicle(Vehicle vehicle);
}
