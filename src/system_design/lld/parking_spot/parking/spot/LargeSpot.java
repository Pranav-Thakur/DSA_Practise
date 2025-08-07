package system_design.lld.parking_spot.parking.spot;

import system_design.lld.parking_spot.vehicle.Vehicle;
import system_design.lld.parking_spot.vehicle.VehicleType;

class LargeSpot extends Spot {

    public LargeSpot(int spotId) {
        super(spotId, SpotType.LARGE);
    }

    @Override
    public boolean canFitVehicle(Vehicle vehicle) {
        return vehicle.getVehicleType() == VehicleType.TRUCK;
    }
}
