package system_design.lld.chess;

import java.util.UUID;

public class Player {
    private final String name;
    private final String id;
    private final Color color;

    public Player(String name, Color color) {
        this.color = color;
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }
}
