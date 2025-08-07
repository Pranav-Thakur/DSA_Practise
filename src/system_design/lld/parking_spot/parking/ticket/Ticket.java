package system_design.lld.parking_spot.parking.ticket;

import system_design.lld.parking_spot.parking.spot.Spot;
import system_design.lld.parking_spot.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

public class Ticket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final Spot spot;
    private final long entryTimestamp;
    private long exitTimestamp;

    public Ticket(Vehicle vehicle, Spot spot) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.spot = spot;

        LocalDateTime now = LocalDateTime.now();
        // Convert LocalDateTime to ZonedDateTime using the system's default time zone
        ZonedDateTime zdt = now.atZone(ZoneId.systemDefault());

        // Get the milliseconds since the epoch from the ZonedDateTime
        this.entryTimestamp = zdt.toInstant().toEpochMilli();
        this.exitTimestamp = -1L;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Spot getSpot() {
        return spot;
    }

    public long getEntryTimestamp() {
        return entryTimestamp;
    }

    public long getExitTimestamp() {
        return exitTimestamp;
    }

    public void setExitTimestamp(long exitTimestamp) {
        this.exitTimestamp = exitTimestamp;
    }
}
