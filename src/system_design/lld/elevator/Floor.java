package system_design.lld.elevator;

public class Floor implements Observer {
    private int id;
    private String display;
    private Direction up, down;

    public Floor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void press(Direction direction) {
        if (direction == Direction.UP) up = direction;
        else if (direction == Direction.DOWN) down = direction;
        else {
            up = down = null;
        }
    }

    @Override
    public void update(Elevator elevator) {
        System.out.println("[DISPLAY] Elevator " + elevator.getId() +
                " | Current Floor: " + elevator.getCurrentFloor() +
                " | Direction: " + elevator.getDirection());
    }
}
