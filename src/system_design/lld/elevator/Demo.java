package system_design.lld.elevator;

import java.util.List;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        int numElevators = 3;
        ElevatorSystem elevatorSystem = ElevatorSystem.getInstance(numElevators);

        List<Floor> floors = List.of(new Floor(1), new Floor(2), new Floor(3),
                                     new Floor(4), new Floor(5));
        
        elevatorSystem.addElevator(new Elevator(1, floors.get(0)));
        elevatorSystem.addElevator(new Elevator(2, floors.get(0)));
        elevatorSystem.addElevator(new Elevator(3, floors.get(1)));

        floors.forEach(floor -> elevatorSystem.addFloor(floor));
        
        // Start the elevator system
        elevatorSystem.start();
        System.out.println("Elevator system started. ConsoleDisplay is observing.\n");

        int selectedElevatorId = elevatorSystem.requestElevator(3, Direction.UP);
        Thread.sleep(100); // Wait for the elevator to start moving

        elevatorSystem.requestFloor(selectedElevatorId, 5);
        Thread.sleep(100); // Wait for the elevator to start moving

        selectedElevatorId = elevatorSystem.requestElevator(2, Direction.DOWN);
        Thread.sleep(300);

        // 4. Internal Request: User in E2 presses 1.
        elevatorSystem.requestFloor(selectedElevatorId, 1);

        // Let the simulation run for a while to observe the display updates
        System.out.println("\n--- Letting simulation run for 1 second ---");
        Thread.sleep(1000);

        // Shutdown the system
        elevatorSystem.shutdown();
        System.out.println("\n--- SIMULATION END ---");
    }
}
