package system_design.lld.parking_spot.fee;

import system_design.lld.parking_spot.parking.ticket.Ticket;

public class FlatFeeStrategy implements FeeStrategy {
    private static final double RATE_PER_HOUR = 10.0;

    @Override
    public double calculateFee(Ticket ticket) {
        long duration = ticket.getExitTimestamp() - ticket.getEntryTimestamp();
        long hr = duration / (1000*60*60L) + 1;
        return hr*RATE_PER_HOUR;
    }
}
