package system_design.lld.chess.piece;

import system_design.lld.chess.Cell;
import system_design.lld.chess.Color;

import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super("Knight", color);
    }

    @Override
    public List<int[]> getPossibleMoves(Cell from) {
        return List.of();
    }
}
