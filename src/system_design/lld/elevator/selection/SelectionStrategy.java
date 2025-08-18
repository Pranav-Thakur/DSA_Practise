package system_design.lld.elevator.selection;

import system_design.lld.elevator.Elevator;
import system_design.lld.elevator.Request;

import java.util.Collection;
import java.util.Optional;

public interface SelectionStrategy {
    Optional<Elevator> select(Collection<Elevator> elevators, Request request);
}
