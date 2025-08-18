package system_design.lld.elevator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import system_design.lld.elevator.selection.SelectionStrategy;
import system_design.lld.elevator.selection.NearestSelectionStrategy;

public class ElevatorSystem {
    private static final class Holder {
        private static ElevatorSystem INSTANCE;
        
        static ElevatorSystem gElevatorSystem(int numElevators) {
            if (INSTANCE == null)
                INSTANCE = new ElevatorSystem(numElevators);
            
            return INSTANCE;
        }
    }

    public static ElevatorSystem getInstance(int numElevators) {
        return Holder.gElevatorSystem(numElevators);
    }

    private final Map<Integer, Elevator> elevators;
    private final Map<Integer, Floor> floors;
    private final ExecutorService executorService;
    private final SelectionStrategy selectionStrategy;

    private ElevatorSystem(int numElevators) {
        this.elevators = new HashMap<>();
        this.floors = new HashMap<>();
        this.selectionStrategy = new NearestSelectionStrategy();
        this.executorService = Executors.newFixedThreadPool(numElevators);
    }

    public void addElevator(Elevator elevator) {
        elevators.put(elevator.getId(), elevator);
    }

    public void addFloor(Floor floor) {
        elevators.forEach((id, elevator) -> elevator.addObserver(floor));
        floors.put(floor.getId(), floor);
    }

    public void start() {
        elevators.forEach((id, elevator) -> {
            elevator.start();
            executorService.submit(elevator);
        });
    }

    public void shutdown() {
        System.out.println("Shutting down elevator system...");
        elevators.forEach((id, elevator) -> {
            elevator.stopElevator();
        });
        executorService.shutdown();
    }

    public int requestElevator(int floor, Direction direction) {
        System.out.println("\n>> EXTERNAL Request: User at floor " + floor + " wants to go " + direction);
        Request request = new Request(direction, RequestSource.EXTERNAL);
        request.setSourceFloor(floors.get(floor));

        Optional<Elevator> selectedElevator = selectionStrategy.select(elevators.values(), request);
        if (selectedElevator.isPresent()) {
            selectedElevator.get().addRequest(request);
            return selectedElevator.get().getId();
        }

        return -1;
    }

    public void requestFloor(int elevator, int floor) {
        Elevator selectedElevator = elevators.get(elevator);
        System.out.println("\n>> INTERNAL Request: User to floor " + floor + " wants to go " + selectedElevator.getDirection());
        Request request = new Request(selectedElevator.getDirection(), RequestSource.INTERNAL);
        request.setSourceFloor(floors.get(floor));
        selectedElevator.addRequest(request);
    }
}
