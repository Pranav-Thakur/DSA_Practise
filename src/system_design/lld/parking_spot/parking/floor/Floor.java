package system_design.lld.parking_spot.parking.floor;

import system_design.lld.parking_spot.parking.spot.Spot;
import system_design.lld.parking_spot.parking.spot.SpotType;
import system_design.lld.parking_spot.vehicle.Vehicle;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Floor {
    private int floorNumber;
    private List<Spot> spots;
    private Map<SpotType, PriorityQueue<Spot>> priorityQueueMap;

    public Floor(int floorNumber, List<Spot> spots) {
        this.floorNumber = floorNumber;
        this.spots = spots;
        this.priorityQueueMap = new ConcurrentHashMap<>();

        this.priorityQueueMap.putIfAbsent(SpotType.SMALL, new PriorityQueue<>(Comparator.comparing(Spot::getSpotId)));
        this.priorityQueueMap.putIfAbsent(SpotType.COMPACT, new PriorityQueue<>(Comparator.comparing(Spot::getSpotId)));
        this.priorityQueueMap.putIfAbsent(SpotType.LARGE, new PriorityQueue<>(Comparator.comparing(Spot::getSpotId)));

        spots.forEach(spot -> {
           PriorityQueue<Spot> pq = this.priorityQueueMap.get(spot.getSpotType());
           pq.offer(spot);
        });
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
