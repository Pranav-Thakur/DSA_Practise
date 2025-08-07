package system_design.lld.parking_spot;

import system_design.lld.parking_spot.fee.VehicleBasedFeeStrategy;
import system_design.lld.parking_spot.parking.floor.Floor;
import system_design.lld.parking_spot.parking.lot.Lot;
import system_design.lld.parking_spot.parking.spot.SpotType;
import system_design.lld.parking_spot.parking.spot.SpotFactory;
import system_design.lld.parking_spot.parking.ticket.Ticket;
import system_design.lld.parking_spot.vehicle.Bike;
import system_design.lld.parking_spot.vehicle.Car;
import system_design.lld.parking_spot.vehicle.Vehicle;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Lot parkingLot = Lot.getInstanceFaster();
        createFloor(0, parkingLot);
        //createFloor(1, parkingLot);
        //createFloor(2, parkingLot);
        parkingLot.setFeeStrategy(new VehicleBasedFeeStrategy());

        // Create vehicles
        Vehicle car1 = new Car("ABC123");
        Vehicle car2 = new Car("XYZ789");
        Vehicle bike1 = new Bike("M1234");

        try {
            Ticket parkingTicket1 = parkingLot.parkVehicle(car1);
            System.out.println("Car 1 parked, Ticket Id: " + parkingTicket1.getTicketId());

            Ticket parkingTicket2 = parkingLot.parkVehicle(car2);
            System.out.println("Car 2 parked, Ticket Id: " + parkingTicket2.getTicketId());

            Ticket parkingTicket3 = parkingLot.parkVehicle(bike1);
            System.out.println("Bike 1 parked, Ticket Id: " + parkingTicket3.getTicketId());

            // Try parking another car when spots are full
            Vehicle car3 = new Car("DL0432");
            parkingLot.parkVehicle(car3);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            double fee = parkingLot.unparkVehicle(car1); // valid ticket ID
            System.out.println("Vehicle: " + car1.getNumberPlate() + ", Fee: " + fee);

            Vehicle car3 = new Car("DL0432");
            fee = parkingLot.unparkVehicle(car3); // invalid license number
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void createFloor(int floorId, Lot lot) {
        int floorNumber = (floorId+1)*10;
        Floor floor = new Floor(floorId, List.of(
                SpotFactory.createSpot(floorNumber + 1, SpotType.SMALL),
                SpotFactory.createSpot(floorNumber + 2, SpotType.COMPACT),
                SpotFactory.createSpot(floorNumber + 3, SpotType.LARGE),
                SpotFactory.createSpot(floorNumber + 4, SpotType.COMPACT)
        ));
        lot.addFloor(floor);
    }
}
