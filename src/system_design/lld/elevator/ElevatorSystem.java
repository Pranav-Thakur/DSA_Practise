package system_design.lld.elevator;

import java.util.ArrayList;
import java.util.List;

public class System {
    private static final class Holder {
        private static final System INSTANCE = new System();
    }

    public static System getInstance() {
        return Holder.INSTANCE;
    }

    private List<Elevator> elevators;
    private List<Floor> floors;

    private System() {
        elevators = new ArrayList<>();
        floors = new ArrayList<>();
    }

    public synchronized void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }

    public synchronized void addFloor(Floor floor) {
        floors.add(floor);
    }
}
