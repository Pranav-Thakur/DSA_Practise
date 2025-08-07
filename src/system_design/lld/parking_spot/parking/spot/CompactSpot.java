package system_design.lld.parking_spot.parking.spot;

import system_design.lld.parking_spot.vehicle.Vehicle;
import system_design.lld.parking_spot.vehicle.VehicleType;

class CompactSpot extends Spot {

    public CompactSpot(int spotId) {
        super(spotId, SpotType.COMPACT);
    }

    @Override
    public boolean canFitVehicle(Vehicle vehicle) {
        return vehicle.getVehicleType() == VehicleType.CAR;
    }
}
