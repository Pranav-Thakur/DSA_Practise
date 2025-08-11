package system_design.lld.chess.piece;

import system_design.lld.chess.Cell;
import system_design.lld.chess.Color;

import java.util.List;

public abstract class Piece {
    private final String name;
    private final Color color;

    public Piece(Color color) {
        this("Pawn", color);
    }

    public Piece(String name, Color color) {
        this.color = color;
        this.name = name;
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public abstract List<int[]> getPossibleMoves(Cell from);
}
