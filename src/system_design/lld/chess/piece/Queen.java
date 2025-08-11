package system_design.lld.chess.piece;

import system_design.lld.chess.Cell;
import system_design.lld.chess.Color;

import java.util.List;

public class Queen extends Piece {

    public Queen(Color color) {
        super("Queen", color);
    }

    @Override
    public List<int[]> getPossibleMoves(Cell from) {
        return List.of();
    }
}
