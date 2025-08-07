package system_design.lld.parking_spot.parking.floor;

import system_design.lld.parking_spot.parking.spot.Spot;
import system_design.lld.parking_spot.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public class Floor {
    private int floorNumber;
    private List<Spot> spots;

    public Floor(int floorNumber, List<Spot> spots) {
        this.floorNumber = floorNumber;
        this.spots = spots;
    }

    public synchronized Optional<Spot> getSpot(Vehicle vehicle) {
        return spots.stream()
                .filter(spot -> spot.isAvailable() && spot.canFitVehicle(vehicle))
                .findFirst();
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
