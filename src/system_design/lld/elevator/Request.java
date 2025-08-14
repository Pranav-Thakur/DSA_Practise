package system_design.lld.elevator;

public class Request {
    private Direction direction;
    private RequestSource source;
    private Floor targetFloor;
    private Floor sourceFloor;

    public Request(Direction direction, RequestSource source) {
        this.direction = direction;
        this.source = source;
        this.targetFloor = null;
        this.sourceFloor = null;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public RequestSource getSource() {
        return source;
    }

    public void setSource(RequestSource source) {
        this.source = source;
    }

    public Floor getTargetFloor() {
        return targetFloor;
    }

    public void setTargetFloor(Floor targetFloor) {
        this.targetFloor = targetFloor;
    }

    public Floor getSourceFloor() {
        return sourceFloor;
    }

    public void setSourceFloor(Floor sourceFloor) {
        this.sourceFloor = sourceFloor;
    }

    @Override
    public String toString() {
        return source + " Request to floor " + targetFloor +
                (source == RequestSource.EXTERNAL ? " going " + direction : "");
    }
}
