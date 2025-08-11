package system_design.lld.chess.piece;

import system_design.lld.chess.Cell;
import system_design.lld.chess.Color;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Color color) {
        super("King", color);
    }

    @Override
    public List<int[]> getPossibleMoves(Cell from) {
        if (from.getPiece() == null || !(from.getPiece() instanceof King)) return List.of();

        List<int[]> moves = new ArrayList<>();
        int[][] possibleMoves = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        int i = from.getRow(), j = from.getCol();
        for (int[] d : possibleMoves) {
            int x = i + d[0];
            int y = j + d[1];
            if (isValid(x, y)) {
                moves.add(new int[]{x, y});
            }
        }

        return moves;
    }
}
