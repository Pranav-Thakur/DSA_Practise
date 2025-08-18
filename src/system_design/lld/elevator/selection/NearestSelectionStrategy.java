package system_design.lld.elevator.selection;

import system_design.lld.elevator.Direction;
import system_design.lld.elevator.Elevator;
import system_design.lld.elevator.Request;

import java.util.Collection;
import java.util.Optional;

public class NearestSelectionStrategy implements SelectionStrategy {

    @Override
    public Optional<Elevator> select(Collection<Elevator> elevators, Request request) {
        Optional<Elevator> elevatorSelected = elevators.stream()
                 .filter(elevator -> isSuitable(elevator, request))
                 .min((elevator1, elevator2) -> {
                    int distance1 = Math.abs(elevator1.getCurrentFloor().getId() - request.getTargetFloor().getId());
                    int distance2 = Math.abs(elevator2.getCurrentFloor().getId() - request.getTargetFloor().getId());
                    return Integer.compare(distance1, distance2);
                 });
        
        return elevatorSelected;
    }

    private boolean isSuitable(Elevator elevator, Request request) {
        if (elevator.getDirection() == Direction.IDLE) return true;
        if (elevator.getDirection() != request.getDirection()) return false;

        if (elevator.getDirection() == Direction.UP
                && elevator.getCurrentFloor().getId() <= request.getTargetFloor().getId()) return true;

        return elevator.getDirection() == Direction.DOWN
                && elevator.getCurrentFloor().getId() >= request.getTargetFloor().getId();
    }
}
