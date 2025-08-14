package system_design.lld.elevator.selection;

import system_design.lld.elevator.Elevator;
import system_design.lld.elevator.Request;

import java.util.List;
import java.util.Optional;

public interface SelectionStrategy {
    Optional<Elevator> select(List<Elevator> elevators, Request request);
}
