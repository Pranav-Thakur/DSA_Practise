package system_design.lld.parking_spot.parking.lot;

import system_design.lld.parking_spot.fee.FeeStrategy;
import system_design.lld.parking_spot.fee.FlatFeeStrategy;
import system_design.lld.parking_spot.parking.floor.Floor;
import system_design.lld.parking_spot.parking.spot.Spot;
import system_design.lld.parking_spot.parking.ticket.Ticket;
import system_design.lld.parking_spot.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Lot {
    // eager instantiation of lot class.. jaisi hi class load hua obj ready but can be unused so lazy init
    // thread-safe by JVM specification
    private static final Lot INSTANCE_EAGER = new Lot();

    // this is thread safe lazy init, but slow
    private static volatile Lot INSTANCE_LAZY;
    public static synchronized Lot slowGetInstance() {
        if (INSTANCE_LAZY == null)
            INSTANCE_LAZY = new Lot();
        return INSTANCE_LAZY;
    }

    // this is double check locking, lazy init, thread safe, faster than above but slower than BillPugh
    private static volatile Lot INSTANCE;
    public static Lot getInstance() {
        if (INSTANCE == null) {
            synchronized (Lot.class) {
                if (INSTANCE == null)
                    INSTANCE = new Lot();
            }
        }

        return INSTANCE;
    }

    // Bill Pugh, thread safe, Lazy init => without sync, faster than above 2
    private static class Holder {
        private static final Lot INSTANCE = new Lot();
    }

    public static Lot getInstanceFaster() {
        return Holder.INSTANCE;
    }

    private int lotId;
    private final List<Floor> floors;
    private final Map<String, Ticket> activeTickets;
    private FeeStrategy feeStrategy;

    public Lot() {
        this.lotId = 0;
        this.floors = new ArrayList<>();
        feeStrategy = new FlatFeeStrategy();
        activeTickets = new ConcurrentHashMap<>();
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public synchronized Ticket parkVehicle(Vehicle vehicle) throws Exception {
        for (Floor floor : floors) {
            Optional<Spot> spotIf = floor.getSpot(vehicle);
            if (spotIf.isPresent()) {
                Spot spot = spotIf.get();
                if (spot.parkVehicle(vehicle)) {
                    Ticket ticket = new Ticket(vehicle, spot);
                    activeTickets.put(vehicle.getNumberPlate(), ticket);
                    return ticket;
                }
            }
        }

        throw new Exception("No available spot for " + vehicle.getVehicleType());
    }

    public synchronized double unparkVehicle(Vehicle vehicle) throws Exception {
        Ticket ticket = activeTickets.remove(vehicle.getNumberPlate());
        if (ticket == null)
            throw new Exception("No ticket available for " + vehicle.getVehicleType());

        ticket.setExitTimestamp(System.currentTimeMillis());
        double fee = feeStrategy.calculateFee(ticket);
        ticket.getSpot().unparkVehicle();
        System.out.println("The parking fee is " + fee);
        return fee;
    }
}
