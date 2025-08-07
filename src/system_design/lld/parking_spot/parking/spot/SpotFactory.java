package system_design.lld.parking_spot.parking.spot;

public class SpotFactory {

    public static Spot createSpot(int spotId, SpotType type) {
        switch (type) {
            case LARGE : return new LargeSpot(spotId);
            case SMALL : return new SmallSpot(spotId);
            case COMPACT : return new CompactSpot(spotId);
            default : throw new IllegalStateException("Unknown parking spot type: " + type);
        }
    }
}
