package system_design.lld.parking_spot.fee;

import system_design.lld.parking_spot.parking.ticket.Ticket;

public interface FeeStrategy {
    double calculateFee(Ticket ticket);
}
