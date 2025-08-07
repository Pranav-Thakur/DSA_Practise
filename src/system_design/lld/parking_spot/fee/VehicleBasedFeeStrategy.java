package system_design.lld.parking_spot.fee;

import system_design.lld.parking_spot.parking.ticket.Ticket;
import system_design.lld.parking_spot.vehicle.VehicleType;

import java.util.Map;

public class VehicleBasedFeeStrategy implements FeeStrategy {

    private final Map<VehicleType, Double> rates = Map.of(
            VehicleType.CAR, 20d,
            VehicleType.BIKE, 10d,
            VehicleType.TRUCK, 30d
    );

    @Override
    public double calculateFee(Ticket ticket) {
        long duration = ticket.getExitTimestamp() - ticket.getEntryTimestamp();
        long hr = duration / (1000*60*60L) + 1;
        return hr*rates.get(ticket.getVehicle().getVehicleType());
    }
}
