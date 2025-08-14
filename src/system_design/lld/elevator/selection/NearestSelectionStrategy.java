package system_design.lld.elevator.selection;

import system_design.lld.elevator.Direction;
import system_design.lld.elevator.Elevator;
import system_design.lld.elevator.Request;

import java.util.List;
import java.util.Optional;

public class NearestSelectionStrategy implements SelectionStrategy {

    @Override
    public Optional<Elevator> select(List<Elevator> elevators, Request request) {
        final Elevator elevatorSelected;
        elevators.stream()
                 .filter(elevator -> isSuitable(elevator, request))
                 .forEach(elevator -> );
        elevators.forEach(elevator -> {
            if (request.getDirection() == elevator.getDirection()) {
                if (Math.abs(elevator.getCurrentFloor().getId() - request.getTargetFloor().getId()))
            }
        });

        return Optional.ofNullable(elevatorSelected);
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
