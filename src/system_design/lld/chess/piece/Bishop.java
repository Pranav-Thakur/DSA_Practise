package system_design.lld.chess.piece;

import system_design.lld.chess.Cell;
import system_design.lld.chess.Color;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super("Bishop", color);
    }

    @Override
    public List<int[]> getPossibleMoves(Cell from) {
        return List.of();
    }
}
