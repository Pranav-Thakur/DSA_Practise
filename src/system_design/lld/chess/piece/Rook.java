package system_design.lld.chess.piece;

import system_design.lld.chess.Cell;
import system_design.lld.chess.Color;

import java.util.List;

public class Rook extends Piece {

    public Rook(Color color) {
        super("Rook", color);
    }

    @Override
    public List<int[]> getPossibleMoves(Cell from) {
        return List.of();
    }
}
